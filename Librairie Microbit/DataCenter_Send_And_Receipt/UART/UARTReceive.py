import serial
import os
import signal

SERIALPORT = "COM6"
BAUDRATE = 115200
ser = serial.Serial()
ListUpdate = []
msg = ""

ser.port = SERIALPORT
def initUART():
    ser.baudrate = BAUDRATE
    ser.bytesize = serial.EIGHTBITS  # number of bits per bytes
    ser.parity = serial.PARITY_NONE  # set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE  # number of stop bits
    ser.timeout = None  # block read

    ser.xonxoff = False  # disable software flow control
    ser.rtscts = False  # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False  # disable hardware (DSR/DTR) flow control
    print('Starting Up Serial Monitor')
    try:
        ser.open()
    except serial.SerialException:
        print("Serial {} port not available".format(SERIALPORT))
        exit()

def newUpdate(msg, QueueMsg, pid) :
    QueueMsg.put(msg)
    print("Nouveau message ajouté : " + msg)
    os.kill(pid, signal.SIGUSR1)

def receiveUartMessage() :
    return ser.read()

def main(QueueMsg, pid):
    initUART()
    try:
        while ser.isOpen():
            character = receiveUartMessage().decode()
            msg += character
            if '\n' in msg :
                print(msg + "\n")
                newUpdate(msg.split('\n')[0], QueueMsg,pid)
                if len(msg.split('\n')) > 1:
                    msg = msg.split('\n', 1)[1]
                else:
                    msg = ""
    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()
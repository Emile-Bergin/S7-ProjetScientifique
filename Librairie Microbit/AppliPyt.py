from time import sleep
import serial

SERIALPORT = "COM6"
BAUDRATE = 115200
ser = serial.Serial()

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

def receiveUartMessage() :
    return ser.read()

if __name__ == '__main__':
    initUART()
    try:
        while ser.isOpen():
            sleep(1000)
            msg = receiveUartMessage()
            print(msg)
            if msg != None :
                print("oh, hi mark : " + str(msg))

    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()
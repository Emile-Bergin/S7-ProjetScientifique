from time import sleep
import queue
import serial

# send serial message
SERIALPORT = "COM5"
BAUDRATE = 115200
ser = serial.Serial()
list_msg = queue.Queue()
list_msg.put("15:4")
list_msg.put("31:2")
list_msg.put("23:1")
list_msg.put("3:6")
list_msg.put("60:8")
list_msg.put("15:2")
envoi_msg = queue.Queue()
init = True

def initUART():
    ser.port = SERIALPORT
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

def newMsg(msg):
    id = msg.split(':')[0]
    if msg.split(':')[1] == "OK":
        message = envoi_msg.get()
        if id != message.split(':')[0]:
            envoi_msg.put()

def sendUARTMessage(msg):
    ser.write(msg.encode())

def receiveUartMessage() :
    return ser.read()

if __name__ == '__main__':
    initUART()
    try:
        while ser.isOpen():
            character = receiveUartMessage().decode()
            msg = msg + character
            if '\n' in msg :
                newMsg(msg.split('\n')[0])
                if len(msg.split('\n')) > 1:
                    msg = msg.split('\n', 1)[1]
                else:
                    msg = ""
            if envoi_msg.empty() and not list_msg.empty():
                envoi_msg.put(list_msg.get())
            elif not envoi_msg.empty :
                msg_to_send = envoi_msg
                wait(2)
                sendUARTMessage(msg_to_send)

    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()
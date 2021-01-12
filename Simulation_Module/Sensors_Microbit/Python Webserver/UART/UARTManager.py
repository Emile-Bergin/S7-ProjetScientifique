from time import sleep
import queue
import serial
import time
from threading import Thread

# send serial message
SERIALPORT = "/dev/ttyACM0"
BAUDRATE = 115200
ser = serial.Serial()

def initUART():
    ser.port = SERIALPORT
    ser.baudrate = BAUDRATE
    ser.bytesize = serial.EIGHTBITS  # number of bits per bytes
    ser.parity = serial.PARITY_NONE  # set parity check: no parity
    ser.stopbits = serial.STOPBITS_ONE  # number of stop bits
    ser.timeout = 5  # block read

    ser.xonxoff = False  # disable software flow control
    ser.rtscts = False  # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False  # disable hardware (DSR/DTR) flow control
    print('Starting Up Serial Monitor')
    try:
        ser.open()
    except serial.SerialException:
        print("Serial {} port not available".format(SERIALPORT))
        exit()

def sendUARTMessage(msg):
    ser.write(msg.encode())

def receiveUartMessage(size):
    return ser.read(size)

def launch(Messages):
    received = True
    initUART()
    message = []
    try:
        while ser.isOpen():
            if received:
                message = Messages.get()
                print(message)
            sendUARTMessage(message)
            msg = str(receiveUartMessage(10))
            print("msg reçu" + str(msg))
            if msg.split('\'')[1] == "receipt:OK":
                received = True
            else:
                received = False

    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()


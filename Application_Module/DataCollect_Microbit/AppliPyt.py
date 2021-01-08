from time import sleep
from serial import *
from queue import Queue
import requests

SERIALPORT = "/dev/ttyACM1"
BAUDRATE = 115200
ser = Serial()
ListUpdate = Queue()
msg = ""

url = 'http://192.168.1.25:5001/api/updateSensor/'

ser.port = SERIALPORT
def initUART():
    ser.baudrate = BAUDRATE
    ser.bytesize = EIGHTBITS  # number of bits per bytes
    ser.parity = PARITY_NONE  # set parity check: no parity
    ser.stopbits = STOPBITS_ONE  # number of stop bits
    ser.timeout = None  # block read

    ser.xonxoff = False  # disable software flow control
    ser.rtscts = False  # disable hardware (RTS/CTS) flow control
    ser.dsrdtr = False  # disable hardware (DSR/DTR) flow control
    print('Starting Up Serial Monitor')
    try:
        ser.open()
    except SerialException:
        print("Serial {} port not available".format(SERIALPORT))
        exit()

def newMsg(msg) :
    id = msg.split(':')[0]
    ListUpdate.put(msg)

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
            if not ListUpdate.empty():
                pending = ListUpdate.get()
                obj = {"intensity": pending.split(':')[1], "id": pending.split(':')[0]}
                x = requests.post(url, data=obj, timeout=1)
                if x:
                    print("Response OK")
                else:
                    print("Response Failed")

    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()
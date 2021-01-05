from time import sleep
import serial

SERIALPORT = "COM6"
BAUDRATE = 115200
ser = serial.Serial()
ListUpdate = []
msg = ""

liste_capteurs = []
keepalive_dict = dict()

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

def newMsg(msg) :
    id = msg.split(':')[0]
    if msg.split(':')[1] != "alive":
        ListUpdate.append(msg)
        if id not in liste_capteurs:
            liste_capteurs.append(id)
            keepalive_dict[id] = 2
        print("Nouveau message ajouté : " + msg)
        print(ListUpdate)
    else :
        keepalive_dict[id] = 0


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
    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()
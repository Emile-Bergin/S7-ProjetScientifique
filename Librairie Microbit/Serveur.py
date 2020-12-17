from time import sleep
import serial

# send serial message
SERIALPORT = "COM6"
BAUDRATE = 115200
ser = serial.Serial()


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

def sendUARTMessage(msg):
    ser.write(msg.encode())

if __name__ == '__main__':
    initUART()
    try:
        while ser.isOpen():
            sleep(10)
            buffer = "15:3"
            sendUARTMessage(buffer)
            print("Buffer Envoyé 0_0")

    except (KeyboardInterrupt, SystemExit):
        ser.close()
        exit()
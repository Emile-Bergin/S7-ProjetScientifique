#3BC Protocol for radio communication between microbit.
#Author:  Corentin BORDES.
#This library will allow to set your microbit radio material and send message using 3BG protocol.
#Data will be coded with Ceasar Code using 18 character difference.

from microbit import *
import radio as radio

def setRadioOnAndSetting(channel, address, group, lenght):
    # Set radio on.
    radio.on()
    # Frequency configuration.
    radio.config(channel=channel)
    # Address configuration.
    radio.config(address=address)
    # Group configuration.
    radio.config(group=group)
    # Message length configuration.
    radio.config(length=lenght)

def createMessage(msgType, Dest, Src, Data):
    return "" + msgType + ":" + Dest + ":" + Src + ":" + Data

def getTypeMsg(msg):
    return str(msg.split(":")[0])

def getDestFromMsg(msg):
    return str(msg.split(":")[1])

def getSourceFromMsg(msg):
    return str(msg.split(":")[2])

def getValueFromMsg(msg):
    return str(msg.split(":")[3])

def codeMsg(msg):
    cMsg = ""
    for i in range(0,len(msg)):
        cMsg += chr(ord(msg[i]) + 18)
    return cMsgg

def decodeMsg(msg):
    dMsg = ""
    for i in range(0, len(msg)):
        dMsg += chr(ord(msg[i])-18)
    return dMsg

def ReceiveMsg():
    msg = radio.receive()
    if msg :
       return decodeMsg(msg)
    else :
        return None

def SendMsg(msg):
    radio.send(codeMsg(msg))
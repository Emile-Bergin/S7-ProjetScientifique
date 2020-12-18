import UART.UARTReceive
from multiprocessing import Process, Queue
import requests
import os
import signal
import time
import json

url = 'url'

def jsonParse(msg):
    x = '{"id":"' + msg.split(":")[0] + '", "intensity":"' + msg.split(":")[1] + '"}'
    return json.dumps(x)

def SendHTTPRequest():
    jsonmsg = jsonParse(queueMsg.get())
    x = requests.post(url, data=jsonmsg)

if __name__ == '__main__':
    queueMsg = Queue()
    UARTListener = Process(target=UART.UARTReceive.main, args=(queueMsg, os.getpid(),))
    UARTListener.start()
    signal.signal(signal.SIGUSR1, SendHTTPRequest)
    while True:
        time.sleep(1)
    UARTListener.join()
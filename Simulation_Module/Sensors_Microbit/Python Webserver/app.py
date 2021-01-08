from flask import Flask
from flask_cors import CORS
from UART.UARTManager import launch
from queue import Queue
from threading import Thread

# Change host ip address here
ipAdd = "192.168.1.25"

app = Flask(__name__)
app.debug = False
CORS(app)
pendingMessages = Queue()

def main():

    UARTManager = Thread(target=launch, args=(pendingMessages,)).start()
    app.run(host=ipAdd)

import API.updateSensors
from flask import Flask, jsonify
from flask_cors import CORS
from UART.UARTManager import launch
from queue import Queue
from threading import Thread

app = Flask(__name__)
app.debug = False
CORS(app)
pendingMessages = Queue()



def main():

    UARTManager = Thread(target=launch, args=(pendingMessages,)).start()
    API = Thread(target=app.run).start()

import API.updateSensors
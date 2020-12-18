from flask import Flask, jsonify
from flask_cors import CORS
from multiprocessing import Process, Queue
from UART.WebServer import sendUARTMsg
import os

app = Flask(__name__)
CORS(app)

def main():
    sendUARTMsg(15,4)
    app.run(debug=True)

import API.sensors


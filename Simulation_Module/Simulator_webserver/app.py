# -*- coding: utf-8 -*-
from flask import Flask, jsonify
from flask_cors import CORS
import os

IP = "192.168.1.7"

app = Flask(__name__)
CORS(app)

@app.route("/")
def hello():
    return "Hello World!"

def main():
    app.run(debug=True, host=IP, port=5003)
    
#A ne pas toucher => Après avoir déclaré app
import api.fire
import api.sensor
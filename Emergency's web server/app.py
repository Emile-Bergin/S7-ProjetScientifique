# -*- coding: utf-8 -*-
from flask import Flask, jsonify
from flask_cors import CORS
import os


app = Flask(__name__)
CORS(app)

@app.route("/")
def hello():
    return "Hello World!"

def main():
    app.run(debug=True)

#A ne pas touché => Après avoir déclaré app
import api.fire
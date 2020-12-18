from flask import Flask, jsonify, request
from UART.WebServer import sendUARTMsg
from app import app

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    sendUARTMsg(request.form.get("id"), request.form.get("intensity"))
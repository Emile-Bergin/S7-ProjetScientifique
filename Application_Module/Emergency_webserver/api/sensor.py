from flask import Flask, jsonify, request
from app import app
from MQTT.service import sendSensorMQTT
import database.service as db

@app.route('/api/getSensors/')
def getSensorAPI():
    return jsonify(db.getSensors())

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    db.updateSensor(request.form.get("id"), request.form.get("intensity"))
    sendSensorMQTT(request.form.get("id"), request.form.get("intensity"))
    return ''

@app.route("/api/reportDeadSensor/", methods=["POST"])
def reportDeadSensorAPI():
    db.reportDeadSensor(request.form.get("id"))
    return ''
from flask import Flask, jsonify, request
from app import app
from MQTT.service import sendSensorMQTT
import database.service as db
import json

@app.route('/api/getSensors/')
def getSensorAPI():
    data = db.get(['id','sensor_column','sensor_line','longitude','latitude','intensity','alive'],"public.sensors")
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_column" : datum["sensor_column"],
            "m_line" : datum["sensor_line"],
            "m_longitude" : datum["longitude"],
            "m_latitude" : datum["latitude"],
            "m_intensity" : datum["intensity"],
            "m_alive" : datum["alive"]
        })   
    print(data2) 
    return jsonify(data2)

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    db.updateSensor(request.form.get("id"), request.form.get("intensity"))
    sendSensorMQTT(request.form.get("id"), request.form.get("intensity"))
    return ''

@app.route("/api/reportDeadSensor/", methods=["POST"])
def reportDeadSensorAPI():
    db.reportDeadSensor(request.form.get("id"))
    return ''
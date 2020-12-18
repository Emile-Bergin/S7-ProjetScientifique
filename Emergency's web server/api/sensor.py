from flask import Flask, jsonify, request
from app import app
from database.service import getSensors
from database.service import updateSensor

@app.route('/api/getSensors/')
def getSensorAPI():
    return jsonify(getSensors())

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    updateSensor(request.form.get("intensity"), request.form.get("id"))
    return ''
from flask import Flask, jsonify, request
from app import app
from database.service import getSensors_Fires
from database.service import createSensor_Fire

@app.route('/api/getSensorsFires/')
def getSensor_FireAPI():
    return jsonify(getSensors_Fires())

@app.route("/api/createSensorFire/", methods=["POST"])
def createSensor_FireAPI():
    createSensor_Fire(request.form.get("id_sensor"), request.form.get("id_fire"))
    return ''


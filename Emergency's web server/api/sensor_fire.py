from flask import Flask, jsonify, request
from app import app
from database.service import getSensors_Fires
from database.service import createSensors_Fires

@app.route('/api/getSensorsFires/')
def getSensor_FireAPI():
    return jsonify(getSensors_Fires())

@app.route("/api/createSensorsFires/", methods=["POST"])
def createSensors_FiresAPI():
    createSensors_Fires(request.form.get("id_sensor"), request.form.get("id_fire"))
    return ''


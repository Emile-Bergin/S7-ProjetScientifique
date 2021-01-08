from flask import Flask, jsonify, request
from app import app
import database.service as db

@app.route('/api/getSensorsFires/')
def getSensor_FireAPI():
    return jsonify(db.get(('id_sensor','id_fire'),"public.sensors__fires"))

@app.route("/api/createSensorFire/", methods=["POST"])
def createSensor_FireAPI():
    db.createSensor_Fire(request.form.get("id_sensor"), request.form.get("id_fire"))
    return ''


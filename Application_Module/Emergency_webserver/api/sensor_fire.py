from flask import Flask, jsonify, request
from app import app
import database.service as db
import json

@app.route('/api/getSensorsFires/')
def getSensor_FireAPI():
    data = db.get(['id_sensor','id_fire'],"public.sensors__fires")
    data2 = []
    for datum in data:
        data2.append({
            "m_idSensor" : datum["id_sensor"],
            "m_idFire" : datum["id_fire"]
        })    
    return jsonify(data2)

@app.route("/api/createSensorFire/", methods=["POST"])
def createSensor_FireAPI():
    print(request.data)
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    
    db.createSensor_Fire(dataJson["m_idSensor"], dataJson["m_idFire"])
    return ''


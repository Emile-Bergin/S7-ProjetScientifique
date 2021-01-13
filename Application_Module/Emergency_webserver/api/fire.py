from flask import Flask, jsonify, request
import json
from app import app
import database.service as db
from MQTT.service import sendFireMQTT
from datetime import datetime

@app.route('/api/getFires/')
def getFireAPI():
    data = db.get(['id','date','longitude','latitude','intensity'],"public.fires")
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_date" : datum["date"].isoformat()+"Z",
            "m_longitude" : datum["longitude"],
            "m_latitude" : datum["latitude"],
            "m_intensity" : datum["intensity"]
        })    
    print(data2)
    return jsonify(data2)

@app.route("/api/createFire/", methods=["POST"])
def createFireAPI():
    print("test")
    data = request.data.decode("UTF8")
    print("test2")
    print(data)
    dataJson = json.loads(data)
    db.createFire(dataJson["m_date"], dataJson["m_longitude"], dataJson["m_latitude"], dataJson["m_intensity"])
    sendFireMQTT(dataJson["m_date"], dataJson["m_longitude"], dataJson["m_latitude"], dataJson["m_intensity"])
    return ''


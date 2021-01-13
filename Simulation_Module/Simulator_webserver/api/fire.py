from flask import Flask, jsonify, request
import json
from app import app
import database.service as db
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
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    db.createFire(dataJson["m_date"], dataJson["m_longitude"], dataJson["m_latitude"], dataJson["m_intensity"])
    return ''

@app.route("/api/updateFire/", methods=["POST"])
def updateFireAPI():
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    db.updateFire(dataJson["m_id"], dataJson["m_intensity"])
    return ''

@app.route("/api/deleteFire/", methods=["POST"])
def deleteFireAPI():
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    db.deleteFire(dataJson["m_id"])
    return ''


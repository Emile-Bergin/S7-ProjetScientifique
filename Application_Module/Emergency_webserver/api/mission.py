from flask import Flask, jsonify, request
from app import app
import database.service as db
import json

@app.route('/api/getMissions/')
def getMissionsAPI():
    data = db.get(['id','id_fire','id_truck','date','processed'],"fireworker.missions")
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_fire" : datum["id_fire"],
            "m_truck" : datum["id_truck"],
            "m_date" : datum["date"],
            "m_processed" : datum["processed"]
        })    
    return jsonify(data2)

@app.route("/api/createMission/", methods=["POST"])
def createMissionAPI():
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    db.createMission(dataJson["m_fire"], dataJson["m_truck"], dataJson["m_date"])
    return ''


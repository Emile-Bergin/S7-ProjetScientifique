from flask import Flask, jsonify, request
from app import app
import database.service as db

@app.route('/api/getMissions/')
def getMissionsAPI():
    data = jsonify(db.get(('id','id_fire','id_truck','date','processed'),"fireworker.missions"))
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
    db.createMission(request.form.get("m_fire"), request.form.get("m_truck"), request.form.get("m_date"))
    return ''


from flask import Flask, jsonify, request
from app import app
import database.service as db
from MQTT.service import sendFireMQTT

@app.route('/api/getFires/')
def getFireAPI():
    data = jsonify(db.get(('id','date','longitude','latitude','intensity'),"public.fires"))
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_date" : datum["date"],
            "m_longitude" : datum["longitude"],
            "m_latitude" : datum["latitude"],
            "m_intensity" : datum["intensity"]
        })    
    return jsonify(data2)

@app.route("/api/createFire/", methods=["POST"])
def createFireAPI():
    db.createFire(request.form.get("m_date"), request.form.get("m_longitute"), request.form.get("m_latitude"), request.form.get("m_intensity"))
    sendFireMQTT(request.form.get("m_date"), request.form.get("m_longitute"), request.form.get("m_latitude"), request.form.get("m_intensity"))
    return ''


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
    db.createFire(request.form.get("date"), request.form.get("longitute"), request.form.get("latitude"), request.form.get("intensity"))
    sendFireMQTT(request.form.get("date"), request.form.get("longitute"), request.form.get("latitude"), request.form.get("intensity"))
    return ''


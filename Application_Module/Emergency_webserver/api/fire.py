from flask import Flask, jsonify, request
from app import app
import database.service as db
from MQTT.service import sendFireMQTT

@app.route('/api/getFires/')
def getFireAPI():
    return jsonify(db.getFires())

@app.route("/api/createFire/", methods=["POST"])
def createFireAPI():
    db.createFire(request.form.get("date"), request.form.get("longitute"), request.form.get("latitude"), request.form.get("intensity"))
    sendFireMQTT(request.form.get("date"), request.form.get("longitute"), request.form.get("latitude"), request.form.get("intensity"))
    return ''


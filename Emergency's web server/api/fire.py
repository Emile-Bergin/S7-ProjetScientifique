from flask import Flask, jsonify, request
from app import app
from database.service import getFires
from database.service import createFire
from MQTT.service import sendFireMQTT

@app.route('/api/getFires/')
def getFireAPI():
    return jsonify(getFires())

@app.route("/api/createFire/", methods=["POST"])
def createFireAPI():
    sendFireMQTT(request.form.get("date"), request.form.get("longitute"), request.form.get("latitude"), request.form.get("intensity"))
    createFire(request.form.get("date"), request.form.get("longitute"), request.form.get("latitude"), request.form.get("intensity"))
    return ''


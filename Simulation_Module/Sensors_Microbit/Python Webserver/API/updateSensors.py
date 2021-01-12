from flask import Flask, jsonify, request
from app import app, pendingMessages

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    newMsg = request.form.get("m_id") + ":" + request.form.get("m_intensity")
    pendingMessages.put(newMsg)
    return ""
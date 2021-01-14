from flask import Flask, jsonify, request
from app import app, pendingMessages
import json

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    newMsg = str(dataJson["m_id"]) + ":" + str(dataJson["m_intensity"])
    pendingMessages.put(newMsg)
    return ""
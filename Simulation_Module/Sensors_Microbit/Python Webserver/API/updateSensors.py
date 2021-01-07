from flask import Flask, jsonify, request
from app import app, pendingMessages

@app.route("/api/updateSensor/", methods=["POST"])
def updateSensorAPI():
    newMsg = request.form.get("id") + ":" + request.form.get("intensity")
    print("receipt" + newMsg)
    pendingMessages.put(newMsg)
    return ""
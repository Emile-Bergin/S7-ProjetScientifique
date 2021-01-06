from flask import Flask, jsonify, request
from app import app
import database.service as db

@app.route('/api/getMissions/')
def getMissionsAPI():
    return jsonify(db.getMissions())

@app.route("/api/createMission/", methods=["POST"])
def createMissionAPI():
    db.createMission(request.form.get("id_fire"), request.form.get("id_truck"), request.form.get("date"))
    return ''


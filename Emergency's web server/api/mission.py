from flask import Flask, jsonify, request
from app import app
from database.service import getMissions
from database.service import createMission

@app.route('/api/getMissions/')
def getMissionsAPI():
    return jsonify(getMissions())

@app.route("/api/createMission/", methods=["POST"])
def createMissionAPI():
    createMission(request.form.get("id_fire"), request.form.get("id_truck"), request.form.get("date"))
    return ''


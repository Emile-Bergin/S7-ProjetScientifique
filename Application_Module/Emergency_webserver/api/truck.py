from flask import Flask, jsonify, request
from app import app
import database.service as db
import json

@app.route('/api/getTrucks/')
def getTrucksAPI():
    data = db.get(['id','id_barrack','longitude','latitude'],"fireworker.trucks")
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_barrack" : datum["id_barrack"],
            "m_longitude" : datum["longitude"],
            "m_latitude" : datum["latitude"]
        })    
    return jsonify(data2)

@app.route("/api/updateTruck/", methods=["POST"])
def updateTruckAPI():
    data = request.data.decode("UTF8")
    dataJson = json.loads(data)
    print(dataJson)
    db.updateTruck(dataJson["m_id"], dataJson["m_longitude"], dataJson["m_latitude"])
    return ''


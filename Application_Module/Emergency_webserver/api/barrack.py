from flask import Flask, jsonify
from app import app
import database.service as db

@app.route('/api/getBarracks/')
def getBarracksAPI():
    data = db.get(['id','longitude','latitude'],"fireworker.barracks")
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_longitude" : datum["longitude"],
            "m_latitude" : datum["latitude"]
        })    
    return jsonify(data2)


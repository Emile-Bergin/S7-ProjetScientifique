from flask import Flask, jsonify
from app import app
import database.service as db

@app.route('/api/getTrucks/')
def getTrucksAPI():
    data = jsonify(db.get(('id','id_barrack','longitude','latitude'),"fireworker.trucks"))
    data2 = []
    for datum in data:
        data2.append({
            "m_id" : datum["id"],
            "m_barrack" : datum["id_barrack"],
            "m_longitude" : datum["longitude"],
            "m_latitude" : datum["latitude"]
        })    
    return jsonify(data2)


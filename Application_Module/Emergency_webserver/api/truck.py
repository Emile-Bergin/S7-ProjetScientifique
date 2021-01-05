from flask import Flask, jsonify
from app import app
import database.service as db

@app.route('/api/getTrucks/')
def getTrucksAPI():
    return jsonify(db.getTrucks())


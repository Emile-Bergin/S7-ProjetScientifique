from flask import Flask, jsonify
from app import app
from database.service import getTrucks

@app.route('/api/getTrucks/')
def getTrucksAPI():
    return jsonify(getTrucks())


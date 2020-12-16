from flask import Flask, jsonify
from app import app

@app.route('/api/fire/')
def getFire():
    dictionnaire = {
        'type': 'feux',
        'longitude': [10, 25, 50],
        'latitude': [50, 25, 10],
        'intensite': [1, 5, 9]
    }
    return jsonify(dictionnaire)
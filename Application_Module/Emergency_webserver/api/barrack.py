from flask import Flask, jsonify
from app import app
import database.service as db

@app.route('/api/getBarracks/')
def getBarracksAPI():
    return jsonify(db.getBarracks())


from flask import Flask, jsonify
from app import app
from database.service import getBarracks

@app.route('/api/getBarracks/')
def getBarracksAPI():
    return jsonify(getBarracks())


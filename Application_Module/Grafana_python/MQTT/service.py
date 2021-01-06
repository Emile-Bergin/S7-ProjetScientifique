import paho.mqtt.client as mqtt
import database.service as db
import time
import json
from datetime import datetime

#http://www.steves-internet-guide.com/into-mqtt-python-client/


def on_message(client, userdata, message):
    if message.topic == "fire":
        jsonmsg = json.loads(str(message.payload.decode("utf-8")))
        db.createFire(jsonmsg["date"], jsonmsg["position"][0], jsonmsg["position"][1], jsonmsg["intensity"])
    elif message.topic == "sensor":
        print(datetime.now())
        jsonmsg = json.loads(str(message.payload.decode("utf-8")))
        #print(jsonmsg)
        db.createSensors(datetime.now(), jsonmsg["id"], jsonmsg["intensity"])

# Informations de connexion à votre broker MQTT : 
# adresse IP
broker = "127.0.0.1"
# on se connecte au broker et on publie le message sur le topic
client = mqtt.Client("S1")
client.on_message=on_message #attach function to callback
client.connect(broker)
client.loop_start()


def readBroker():
    while True:
        client.subscribe([("fire", 0), ("sensor", 0)])
        time.sleep(1)




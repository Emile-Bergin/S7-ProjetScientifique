import paho.mqtt.client as mqtt
import json
#http://www.steves-internet-guide.com/into-mqtt-python-client/

# Informations de connexion à votre broker MQTT : 
# adresse IP
broker = "127.0.0.1"
# on se connecte au broker et on publie le message sur le topic
client = mqtt.Client("P1")
client.connect(broker)

def test():
    client.publish("test", "HelloWorld")

def sendSensorMQTT(id, intensity):
    client.publish("sensor", json.dumps({'id': id, 'intensity': intensity}, sort_keys=True, indent=4))

def sendFireMQTT(date, longitude, latitude, intensity):
    client.publish("fire", json.dumps({'date': date, 'position': [longitude, latitude], 'intensity': intensity}, sort_keys=True, indent=4))

import paho.mqtt.client as mqtt
import json
#http://www.steves-internet-guide.com/into-mqtt-python-client/

# Informations de connexion à votre broker MQTT : 
# adresse IP
broker = "192.168.1.25"
# on se connecte au broker et on publie le message sur le topic
client = mqtt.Client("P1")
client.connect(broker)
client.publish("test", "HelloWorld")
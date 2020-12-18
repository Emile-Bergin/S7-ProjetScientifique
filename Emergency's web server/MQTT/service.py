import paho.mqtt.client as mqtt
#http://www.steves-internet-guide.com/into-mqtt-python-client/

# Informations de connexion à votre broker MQTT : 
# adresse IP
broker = "127.0.0.1"
# on se connecte au broker et on publie le message sur le topic
client = mqtt.Client("P1")
client.connect(broker)

def test():
    client.publish("test", "Je suis le message")

def sendSensorMQTT(id, intensity):
    client.publish("sensor", str(id) + ":" + str(intensity))

def sendFireMQTT(date, longitude, latitude, intensity):
    client.publish("fire", date + ":[" + str(longitude) + str(latitude) + "]:" + str(intensity))

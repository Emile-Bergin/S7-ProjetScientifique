import paho.mqtt.client as mqtt
#http://www.steves-internet-guide.com/into-mqtt-python-client/

def test():
    # Informations de connexion à votre broker MQTT : 
    # adresse IP
    broker = "127.0.0.1"
    #
    # on se connecte au broker et on publie le message sur le topic
    #
    client = mqtt.Client("P1")
    client.connect(broker)
    client.publish("/", "Je suis le message")
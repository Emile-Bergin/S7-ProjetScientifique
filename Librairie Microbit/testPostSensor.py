import requests

url = 'http://192.168.1.25:5000/api/updateSensor/'
myobj = {'m_id': 42,'m_intensity': 42}
print(myobj)
x = requests.post(url, data = myobj)



print(x.text)
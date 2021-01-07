import requests

url = 'http://127.0.0.1:5000/api/updateSensor/'
myobj = {'id': 42,'intensity': 42}
print(myobj)
x = requests.post(url, data = myobj)



print(x.text)
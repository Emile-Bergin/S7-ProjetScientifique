import requests



url = 'http://127.0.0.1:5000/api/updateSensor/'
myobj = {'id' : 2, 'intensity' : 30}

print(myobj)
x = requests.post(url, data = myobj)



print(x.text)
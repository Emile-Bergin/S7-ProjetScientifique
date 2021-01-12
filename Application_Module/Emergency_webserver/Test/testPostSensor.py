import requests



url = 'http://192.168.1.7:5000/api/updateSensor/'
myobj = {'id' : 2, 'intensity' : 30}

print(myobj)
x = requests.post(url, data = myobj)



print(x.text)
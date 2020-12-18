import psycopg2

# https://riptutorial.com/fr/python/example/18257/acces-a-la-base-de-donnees-postgresql-avec-psycopg2

conn = psycopg2.connect(database="emergency", 
                        user="postgres",
                        host="localhost",
                        password="admin",
                        port="5432") 

cur = conn.cursor()

#======================================================#
#SENSORS
def getSensors():
    sql = 'SELECT * FROM public.sensors'
    cur.execute(sql)
    return cur.fetchall()

def updateSensor(id, intensity):
    sql = 'UPDATE public.sensors SET intensity = %s WHERE id = %s'
    args = (intensity, id)
    cur.execute(sql, args)

#======================================================#
#FIRES
def getFires():
    sql = 'SELECT * FROM public.fires'
    cur.execute(sql)
    return cur.fetchall()

def createFire(date, longitude, latitude, intensity):
    sql = 'INSERT INTO public.fires (date, longitude, latitude, intensity) VALUES (%s, %s, %s, %s)'
    args = (date, longitude, latitude, intensity)
    cur.execute(sql, args)

#======================================================#
#SENSOR_FIRE
def getSensors_Fires():
    sql = 'SELECT * FROM public.sensors__fires'
    cur.execute(sql)
    return cur.fetchall()

def createSensor_Fire(id_sensor, id_fire):
    sql = 'INSERT INTO public.sensors__fires (id_sensor, id_fire) VALUES (%s, %s)'
    args = (id_sensor, id_fire)
    cur.execute(sql, args)

#======================================================#
#TRUCKS
def getTrucks():
    sql = 'SELECT * FROM fireworker.trucks'
    cur.execute(sql)
    return cur.fetchall()

#======================================================#
#MISSIONS
def getMissions():
    sql = 'SELECT * FROM fireworker.missions'
    cur.execute(sql)
    return cur.fetchall()

def createMission(id_fire, id_truck, date):
    sql = 'INSERT INTO fireworker.missions (id_fire, id_truck, date) VALUES (%s, %s, %s)'
    args = (id_fire, id_truck, date)
    cur.execute(sql, args)

#======================================================#
#BARRACKS
def getBarracks():
    sql = 'SELECT * FROM fireworker.barracks'
    cur.execute(sql)
    return cur.fetchall()



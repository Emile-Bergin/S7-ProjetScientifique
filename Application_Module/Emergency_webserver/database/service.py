import psycopg2
import json
from app import IP

# https://riptutorial.com/fr/python/example/18257/acces-a-la-base-de-donnees-postgresql-avec-psycopg2

conn = psycopg2.connect(database="emergency", 
                        user="postgres",
                        host=IP,
                        password="admin",
                        port="5433") 

cur = conn.cursor()

def get(dataWanted, table):
    sql = 'SELECT '+ ",".join(dataWanted) +' FROM '+ table
    cur.execute(sql)
    data = cur.fetchall()
    ret = list()
    for datum in data:
        obj = {}
        for datumWanted in enumerate(dataWanted):
            obj[dataWanted[datumWanted[0]]] = datum[datumWanted[0]]
        ret.append(obj)
    return ret

#======================================================#
#SENSORS
def updateSensor(id, intensity):
    sql = 'UPDATE public.sensors SET intensity = %s WHERE id = %s'
    args = (intensity, id)
    cur.execute(sql, args)
    conn.commit()

def reportDeadSensor(id):
    sql = 'UPDATE public.sensors SET alive = false WHERE id = %s'
    args = (id)
    cur.execute(sql, args)
    conn.commit()

#======================================================#
#FIRES
def createFire(date, longitude, latitude, intensity):
    sql = 'INSERT INTO public.fires (date, longitude, latitude, intensity) VALUES (%s, %s, %s, %s)'
    args = (date, longitude, latitude, intensity)
    cur.execute(sql, args)
    conn.commit()

#======================================================#
#SENSOR_FIRE
def createSensor_Fire(id_sensor, id_fire):
    sql = 'INSERT INTO public.sensors__fires (id_sensor, id_fire) VALUES (%s, %s)'
    args = (id_sensor, id_fire)
    cur.execute(sql, args)
    conn.commit()

#======================================================#
#MISSIONS

def createMission(id_fire, id_truck, date):
    sql = 'INSERT INTO fireworker.missions (id_fire, id_truck, date) VALUES (%s, %s, %s)'
    args = (id_fire, id_truck, date)
    cur.execute(sql, args)
    conn.commit()

#======================================================#
#BARRACKS

#======================================================#
#TRUCKS

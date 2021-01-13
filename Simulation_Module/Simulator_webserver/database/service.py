import psycopg2
import json
from app import IP

conn = psycopg2.connect(database="simulator", 
                        user="postgres",
                        host=IP,
                        password="admin",
                        port="5435") 

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

def updateFire(id, intensity):
    sql = 'UPDATE public.fires SET intensity = %s WHERE id = %s'
    args = (intensity, id)
    cur.execute(sql, args)
    conn.commit()

def deleteFire(id):
    sql = 'DELETE FROM public.fires WHERE id = %s'
    args = (id, )
    cur.execute(sql, args)
    conn.commit()
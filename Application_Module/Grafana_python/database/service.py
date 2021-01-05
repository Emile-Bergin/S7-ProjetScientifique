import psycopg2

# https://riptutorial.com/fr/python/example/18257/acces-a-la-base-de-donnees-postgresql-avec-psycopg2

conn = psycopg2.connect(database="grafana", 
                        user="postgres",
                        host="localhost",
                        password="admin",
                        port="5432") 

cur = conn.cursor()

#======================================================#
#SENSORS
def createSensors(date, id, intensity):
    sql = 'INSERT INTO public.sensors (date, id, intensity) VALUES (%s, %s, %s)'
    args = (date, id, intensity)
    cur.execute(sql, args)
    conn.commit()

#======================================================#
#FIRES
def createFire(date, longitude, latitude, intensity):
    sql = 'INSERT INTO public.fires (date, longitude, latitude, intensity) VALUES (%s, %s, %s, %s)'
    args = (date, longitude, latitude, intensity)
    cur.execute(sql, args)
    conn.commit()





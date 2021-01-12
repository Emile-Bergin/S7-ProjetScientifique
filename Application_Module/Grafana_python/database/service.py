import psycopg2

IP = "192.168.1.25"

conn = psycopg2.connect(database="grafana", 
                        user="postgres",
                        host=IP,
                        password="admin",
                        port="5434") 

cur = conn.cursor()

#======================================================#
#SENSORS
def createSensors(date, id, intensity):
    sql = 'INSERT INTO public.sensors_history (date, id, intensity) VALUES (%s, %s, %s)'
    args = (date, id, intensity)
    cur.execute(sql, args)
    conn.commit()

#======================================================#
#FIRES
def createFire(date, longitude, latitude, intensity):
    sql = 'INSERT INTO public.fires_history (date, longitude, latitude, intensity) VALUES (%s, %s, %s, %s)'
    args = (date, longitude, latitude, intensity)
    cur.execute(sql, args)
    conn.commit()





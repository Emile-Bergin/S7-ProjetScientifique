package com.FireManager;

import com.Connector.Api;
import com.MissionManager.MissionManager;
import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Sensor;
import com.Objects.SensorFire;
import com.SuperCompany.Manager;
import com.SuperCompany.Mode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FireManager implements Manager {
    MissionManager m_missionManager;
    Api m_api;
    List<Sensor> m_sensors = new ArrayList<Sensor>();
    List<Fire> m_fires = new ArrayList<Fire>();
    List<SensorFire> m_sensorsFires = new ArrayList<SensorFire>();

    public FireManager(Api api, MissionManager mm){
        Mode.println("Création FireManager");
        m_api =api;
        m_missionManager=mm;
        Mode.println("Fin Création FireManager");
    }

    @Override
    public void update() {
        Mode.println("FireManager: update");
        updateSensorsFires();
        //SENSORS
        updateSensors();
        Mode.println(m_sensors.toString());
        //FIRES
        updateFires();
        DetectNewAndIncreaseFire();
        //UPLOAD UPDATES
        m_missionManager.update();
    }

    private void updateSensorsFires() {
        m_sensorsFires = m_api.getSensorsFires();
        Mode.println("FireManager: updateSensorsFires");
        Mode.println(m_sensorsFires.toString());
    }

    void updateSensors() { //add New Sensors If Not Present And Update Intensity
        m_sensors = m_api.getSensors();
        Mode.println("FireManager: updateSensors");
    }

    void compareSensors(List<Sensor> sensorsUpdated) {
        Boolean isPresent=Boolean.FALSE;
        if(sensorsUpdated != null){
            for (Sensor sensorUptaded: sensorsUpdated){
                isPresent= Boolean.FALSE;
                for (Sensor sensor: m_sensors){
                    if(sensorUptaded.getM_id()==sensor.getM_id()){   //Si le sensor existe on ne l'ajoute pas mais le met a jour
                        isPresent=Boolean.TRUE;
                        try {
                            sensor.setM_intensity(sensorUptaded.getM_intensity());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(!isPresent){
                    m_sensors.add(sensorUptaded);
                }
            }
        }
    }

    private void updateFires() {
        m_fires = m_api.getFires();
        Mode.println("FireManager: updateFires");
        Mode.println(m_fires.toString());
    }

    void DetectNewAndIncreaseFire() { //Aucun traitement pour l'instant Sensors = Fires
        Mode.println("FireManager: DetectNewAndIncreaseFire");
        Boolean isPresent=Boolean.FALSE;
        for(Sensor s: m_sensors) {                                     //parcours les sensors
            isPresent= Boolean.FALSE;                                   //par defaut le feu n'est pas présent
            for(Fire f: m_fires){                                       //parcours les feux
                for(SensorFire sf: m_sensorsFires){
                    if(f.getM_id()==sf.getM_idFire() && s.getM_id()==sf.getM_idSensor()) {                           //si les id correspondent
                        if(f.getM_intensity()!=s.getM_intensity()) {        //si les intensité sont différentes
                            System.out.println(s.toString());
                            f.setM_intensity(s.getM_intensity());           //met a jour l'intensité
                            m_api.updateFires(f);
                        }
                        isPresent=Boolean.TRUE;
                    }

                }
            }
            if(!isPresent && s.getM_intensity()>0) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Mode.println("Je creer un feu en lien avec le capteur " + s.getM_id());
                m_api.createFires(new Fire(null, timestamp, s.getM_longitude(), s.getM_latitude(), s.getM_intensity()));
                updateFires();
                for(Fire f:m_fires){
                    List<Integer> ids_sensorFire = new ArrayList<Integer>();
                    for (SensorFire sf : m_sensorsFires){
                        ids_sensorFire.add(sf.getM_idFire());
                    }
                    if(f.getM_date().compareTo(timestamp) == 1 && !ids_sensorFire.contains(f.getM_id())){
                        m_api.createSensorFire(new SensorFire(s.getM_id(), f.getM_id()));
                        updateSensorsFires();
                    }
                }
            }
        }
    }
}

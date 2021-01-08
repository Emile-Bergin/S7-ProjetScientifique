package com.FireManager;

import com.Connector.Api;
import com.MissionManager.MissionManager;
import com.Objects.Fire;
import com.Objects.Sensor;
import com.SuperCompany.Manager;
import com.SuperCompany.Mode;

import java.util.ArrayList;
import java.util.List;

public class FireManager implements Manager {
    MissionManager m_missionManager;
    Api m_api;
    List<Sensor> m_sensors = new ArrayList<Sensor>();
    List<Fire> m_fires = new ArrayList<Fire>();

    public FireManager(Api api, MissionManager mm){
        Mode.println("Création FireManager");
        m_api =api;
        m_missionManager=mm;
        Mode.println("Fin Création FireManager");
    }

    @Override
    public void update() {
        Mode.println("FireManager: update");
        //SENSORS
        updateSensors();
        Mode.println(m_sensors.toString());
        //FIRES
        updateFires();
        DetectNewAndIncreaseFire();
        //UPLOAD UPDATES
        m_missionManager.update(m_fires);
    }

    void updateSensors() { //add New Sensors If Not Present And Update Intensity
        List<Sensor> sensorsUpdated = m_api.getSensors();
        Mode.println("FireManager: updateSensors");
        compareSensors(sensorsUpdated);
    }

    void compareSensors(List<Sensor> sensorsUpdated) {
        Boolean isPresent=Boolean.FALSE;
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

    private void updateFires() {
        List<Fire> updatedFires = m_api.getFires();
        Mode.println("FireManager: updateFires");
        compareFires(updatedFires);
    }

    void compareFires(List<Fire> firesUpdated) {
        //TODO
        // +tests
    }

    void DetectNewAndIncreaseFire() { //Aucun traitement pour l'instant Sensors = Fires
        Mode.println("FireManager: DetectNewAndIncreaseFire");
        areThereNewFires();
        Mode.println(m_fires.toString());
    }

    void areThereNewFires(){
        Mode.println("FireManager: areThereNewFires?");
        Boolean isPresent=Boolean.FALSE;
        for (Sensor s: m_sensors) {                                     //parcours les sensors
            isPresent= Boolean.FALSE;                                   //par defaut le feu n'est pas présent
            for(Fire f: m_fires){                                       //parcours les feux
                if(f.getM_id()==s.getM_id()){                           //si les id correspondent
                    if(f.getM_intensity()!=s.getM_intensity()) {        //si les intensité sont différentes
                        if(f.getM_intensity()<s.getM_intensity()){      //si l'intensité du feu est inférieur a celui du capteur
                            f.setM_increase(Boolean.TRUE);              //met a jour increase
                        }
                        f.setM_intensity(s.getM_intensity());           //met a jour l'intensité
                    }
                    isPresent=Boolean.TRUE;
                }
            }
            if(!isPresent && s.getM_intensity()>0) {
                Fire f = new Fire(s.getM_id(), new java.util.Date(), s.getM_longitude(), s.getM_latitude(), s.getM_intensity());
                m_api.createFires(f);
                m_fires.add(f);
            }
        }
    }
}

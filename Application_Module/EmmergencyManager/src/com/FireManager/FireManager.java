package com.FireManager;

import com.MissionManager.MissionManager;
import com.SuperCompany.*;

import java.util.ArrayList;
import java.util.List;

public class FireManager implements Manager {
    MissionManager m_missionManager;
    WebServerConnector m_webServerManager;
    List<Sensor> m_sensors = new ArrayList<Sensor>();
    List<Fire> m_fires = new ArrayList<Fire>();

    public FireManager(WebServerConnector wsc, MissionManager mm){
        Debug.println("Création FireManager");
        m_webServerManager=wsc;
        m_missionManager=mm;
        Debug.println("Fin Création FireManager");
    }

    @Override
    public void update() {
        Debug.println("FireManager: update");
        List<Sensor> sensorsUpdated = m_webServerManager.getSensors();
        Debug.println(sensorsUpdated.toString());
        DetectNewAndIncreaseFire(sensorsUpdated);
        m_missionManager.update(m_fires);
    }

    void DetectNewAndIncreaseFire(List<Sensor> sensorsUpdated) { //Aucun traitement pour l'instant Sensors = Fires
        Debug.println("FireManager: DetectNewAndIncreaseFire");
        updateSensors(sensorsUpdated);
        Debug.println(m_sensors.toString());
        updateFires();
        Debug.println(m_fires.toString());
    }

    void updateSensors(List<Sensor> sensorsUpdated) { //add New Sensors If Not Present And Update Intensity
        Debug.println("FireManager: updateSensors");
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

    void updateFires() {
        Debug.println("FireManager: updateFires");
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
                m_fires.add(new Fire(s.getM_id(), new java.util.Date(), s.getM_longitude(), s.getM_latitude(), s.getM_intensity()));
            }
        }
    }
}

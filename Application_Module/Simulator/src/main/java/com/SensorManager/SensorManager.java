package com.SensorManager;

import com.Connectors.ApiEmergencyWebServer;
import com.Connectors.ApiMicroBitWebServer;
import com.Connectors.ApiSimulatorWebServer;
import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Sensor;
import com.Objects.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensorManager {
    private ApiEmergencyWebServer m_apiEmergencyWebServer;
    private ApiMicroBitWebServer m_apiMicroBitWebServer;
    private ApiSimulatorWebServer m_apiSimulatorWebServer;
    private List<Fire> m_fires;
    private List<Sensor> m_sensor;
    private List<Mission> m_missions;

    public SensorManager(){
        m_apiEmergencyWebServer =  new ApiEmergencyWebServer();
        m_apiMicroBitWebServer = new ApiMicroBitWebServer();
        m_apiSimulatorWebServer = new ApiSimulatorWebServer();
    }

    public void update() {
        System.exit(1);
        m_fires = m_apiEmergencyWebServer.getFires();
        m_sensor = m_apiSimulatorWebServer.getSensors();
        m_missions = m_apiEmergencyWebServer.getMissions();
        //updateSensor();
        //postUpdatedSensor();

    }

    /*private void updateSensor() { //Ne prends pas en compte l'intensité du feux et le nombre de camion sur place, une chance sur deux que le feux augmente
        m_sensorsToUpdate = new ArrayList<Sensor>();
        if(m_sensor!=null) {
            for (Sensor s : m_sensor) {
                if(s.getM_intensity()!=0){                              // Si il y a deja une intensité
                    Mission m=getMissionLinkToSensor(s);                //On recupere la mission
                    if (m!=null){
                        if(m.getM_trucks().size()>0){                   //Si il y a des camions affecté a la mission
                            int sum=0;
                            for (Truck t : m.getM_trucks()){
                                sum+=t.getM_type();
                            }
                            if(sum>=s.getM_intensity()){
                                try {
                                    s.setM_intensity(s.getM_intensity() - 1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else{
                                try {
                                    s.setM_intensity(s.getM_intensity() + 1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            try {
                                s.setM_intensity(s.getM_intensity() + 1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else{                                      // 10% de chance que le capteur declenche un nouveau feu
                    if(new Random().nextFloat()<0.2){
                        try {
                            s.setM_intensity(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


                /*if (new Random().nextBoolean()) {
                    if (s.getM_intensity() < 8) {
                        try {
                            s.setM_intensity(s.getM_intensity() + 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            s.setM_intensity(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        s.setM_intensity(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
/*
            }
        }
    }

    private Mission getMissionLinkToSensor(Sensor s) {
        for(Mission m: m_missions){
            if(m.getM_fire().getM_id()==s.getM_id()){
                return m;
            }
        }
        return null;
    }

    private void postUpdatedSensor() {
        if(m_sensorsToUpdate.size()>0){
            for(Sensor s: m_sensorsToUpdate){
                m_apiSimulatorWebServer.updateSensor(s);
                m_apiMicroBitWebServer.updateSensor(s);
            }
        }

    }*/
}

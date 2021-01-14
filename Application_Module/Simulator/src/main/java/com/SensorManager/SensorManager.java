package com.SensorManager;

import com.Connectors.ApiEmergencyWebServer;
import com.Connectors.ApiMicroBitWebServer;
import com.Connectors.ApiSimulatorWebServer;
import com.Objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensorManager {
    private ApiEmergencyWebServer m_apiEmergencyWebServer;
    private ApiMicroBitWebServer m_apiMicroBitWebServer;
    private ApiSimulatorWebServer m_apiSimulatorWebServer;
    private List<Fire> m_fires;
    private List<Sensor> m_sensors;
    private List<Mission> m_missions;
    private List<SensorFire> m_sensorsfires;

    public SensorManager(){
        m_apiEmergencyWebServer =  new ApiEmergencyWebServer();
        m_apiMicroBitWebServer = new ApiMicroBitWebServer();
        m_apiSimulatorWebServer = new ApiSimulatorWebServer();
    }

    public void update() {
        m_fires = m_apiEmergencyWebServer.getFires();
        m_sensors = m_apiSimulatorWebServer.getSensors();
        m_missions = m_apiEmergencyWebServer.getMissions();
        m_sensorsfires = m_apiEmergencyWebServer.getSensorsFires();

        updateSensor();

    }

    private void updateSensor() { //Ne prends pas en compte l'intensité du feux, une chance sur deux que le feux augmente
        for (Sensor s : m_sensors) {
            if (s.getM_intensity() != 0) {                              // Si il y a deja une intensité
                Mission m = getMissionLinkToSensor(s);                  //On recupere la mission
                if (m != null) {                                        //Si un camion a été envoyé
                    s.setM_intensity(s.getM_intensity()-1);             //On réduit de 1 l'intensité
                } else {
                    s.setM_intensity(s.getM_intensity() + 2);
                }
                m_apiMicroBitWebServer.updateSensor(s);
            } else {                                      // 10% de chance que le capteur declenche un nouveau feu
                if (new Random().nextFloat() < 0.1) {
                    s.setM_intensity(1);
                    m_apiMicroBitWebServer.updateSensor(s);
                }

            }

        }
    }

    private Mission getMissionLinkToSensor(Sensor s) {
        for(Mission m: m_missions){
            for(SensorFire sf : m_sensorsfires){
                if(sf.getM_idSensor() == s.getM_id() && sf.getM_idFire() == m.getM_idfire()){
                    return m;
                }
            }
        }
        return null;
    }
}

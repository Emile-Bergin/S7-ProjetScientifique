package com.SensorManager;

import com.Connectors.ApiEmergencyWebServer;
import com.Connectors.ApiMicroBitWebServer;
import com.Connectors.ApiSimulatorWebServer;
import com.Objects.Fire;
import com.Objects.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensorManager {
    private ApiEmergencyWebServer m_apiEmergencyWebServer;
    private ApiMicroBitWebServer m_apiMicroBitWebServer;
    private ApiSimulatorWebServer m_apiSimulatorWebServer;
    private List<Fire> m_fires;
    private List<Sensor> m_sensor;
    private List<Sensor> m_sensorsToUpdate;

    public SensorManager(){
        m_apiEmergencyWebServer =  new ApiEmergencyWebServer();
        m_apiMicroBitWebServer = new ApiMicroBitWebServer();
        m_apiSimulatorWebServer = new ApiSimulatorWebServer();
    }

    public void update() {
        m_fires = m_apiEmergencyWebServer.getFires();
        m_sensor = m_apiSimulatorWebServer.getSensors();
        updateSensor();
        postUpdatedSensor();

    }

    private void updateSensor() { //Ne prends pas en compte l'intensité du feux et le nombre de camion sur place, une chance sur deux que le feux augmente
        m_sensorsToUpdate = new ArrayList<Sensor>();
        if(m_sensor!=null) {
            for (Sensor s : m_sensor) {
                if (new Random().nextBoolean()) {
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
                }

            }
        }
    }

    private void postUpdatedSensor() {
        if(m_sensorsToUpdate.size()>0){
            for(Sensor s: m_sensorsToUpdate){
                m_apiSimulatorWebServer.updateSensor(s);
                m_apiMicroBitWebServer.updateSensor(s);
            }
        }

    }
}

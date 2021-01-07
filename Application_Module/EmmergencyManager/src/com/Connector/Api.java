package com.Connector;

import com.Objects.*;

import java.io.IOException;
import java.util.List;

public class Api{
    private WebServerConnector m_webSeverConnector;

    public Api(WebServerConnector wsc){
        m_webSeverConnector = wsc;
    }

    public List<Fire> getFires(){
        try {
            return m_webSeverConnector.getFires().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Truck> getTrucks(){
        try {
            return m_webSeverConnector.getTrucks().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Barrack> getBarracks(){
        try {
            return m_webSeverConnector.getBarracks().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Sensor> getSensors(){
        try {
            return m_webSeverConnector.getSensors().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Mission> getMissions() {
        try {
            return m_webSeverConnector.getMissions().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createFires(Fire f){
        try {
            m_webSeverConnector.createFire(f).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMission(Mission m){
        try {
            m_webSeverConnector.createMission(m).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSensorFire(Sensor s, Fire f){
        try {
            m_webSeverConnector.createSensorFire(s.getM_id().toString(),f.getM_id().toString()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMission(Mission m) {
        try {
            m_webSeverConnector.updateMission(m).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTruck(Truck t) {
        try {
            m_webSeverConnector.updateTruck(t).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

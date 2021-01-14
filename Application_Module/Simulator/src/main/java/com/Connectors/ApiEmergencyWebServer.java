package com.Connectors;

import com.Objects.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ApiEmergencyWebServer {
    private EmergencyWebServerConnector m_emergencyWebServerConnector;
    private Retrofit m_retrofit;

    public ApiEmergencyWebServer(){
        m_retrofit = new Retrofit.Builder().baseUrl("http://164.4.1.1:5001/").addConverterFactory(GsonConverterFactory.create()).build();
        m_emergencyWebServerConnector = m_retrofit.create(EmergencyWebServerConnector.class);
    }

    public List<Truck> getTrucks(){
        if(Mode.USEREELAPI){
            try {
                return m_emergencyWebServerConnector.getTrucks().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }

    public List<Mission> getMissions(){
        if(Mode.USEREELAPI){
            try {
                return m_emergencyWebServerConnector.getMissions().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }

    public List<Fire> getFires(){
        if(Mode.USEREELAPI){
            try {
                return m_emergencyWebServerConnector.getFires().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }

    public void  updateTruck(Truck t){
        if(Mode.USEREELAPI){
            try {
                m_emergencyWebServerConnector.updateTruck(t).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<SensorFire> getSensorsFires() {
        if (Mode.USEREELAPI) {
            try {
                return m_emergencyWebServerConnector.getSensorsFires().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }


}

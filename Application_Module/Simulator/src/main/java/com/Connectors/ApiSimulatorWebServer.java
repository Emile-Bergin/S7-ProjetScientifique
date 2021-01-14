package com.Connectors;

import com.Objects.Mission;
import com.Objects.Mode;
import com.Objects.Sensor;
import com.Objects.Truck;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ApiSimulatorWebServer {
    private SimulatorWebServerConnector m_simulatorWebServerConnector;
    private Retrofit m_retrofit;

    public ApiSimulatorWebServer(){
        m_retrofit = new Retrofit.Builder().baseUrl("http://164.4.1.1:5001/").addConverterFactory(GsonConverterFactory.create()).build();
        m_simulatorWebServerConnector= m_retrofit.create(SimulatorWebServerConnector.class);
    }

    public void  updateTruck(Truck t){
        if(Mode.USEREELAPI){
            try {
                m_simulatorWebServerConnector.updateTruck(t).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void  updateMission(Mission m){
        if(Mode.USEREELAPI){
            try {
                m_simulatorWebServerConnector.updateMission(m).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void  updateSensor(Sensor s){
        if(Mode.USEREELAPI){
            try {
                m_simulatorWebServerConnector.updateSensor(s).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Sensor> getSensors() {
        if(Mode.USEREELAPI){
            try {
                return m_simulatorWebServerConnector.getSensors().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }
}

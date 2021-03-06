package com.Connector;

import com.Objects.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;
import java.util.List;

public interface WebServerConnector{
    @GET("api/getBarracks/")
    Call<List<Barrack>> getBarracks();

    @GET("api/getSensors/")
    Call<List<Sensor>> getSensors();

    @GET("api/getSensorsFires/")
    Call<List<SensorFire>> getSensorsFires();

    @GET("api/getFires/")
    Call<List<Fire>> getFires();

    @GET("api/getMissions/")
    Call<List<Mission>>getMissions();

    @GET("api/getTrucks/")
    Call<List<Truck>>getTrucks();

    @POST("api/createFire/")
    Call<Void> createFire(@Body Fire fire);

    @POST("api/updateFire/")
    Call<Void> updateFire(@Body Fire fire);

    @POST("api/deleteFire/")
    Call<Void> deleteFire(@Body Fire fire);

    @POST("api/createMission/")
    Call<Void> createMission(@Body Mission Mission);

    @POST("api/deleteMission/")
    Call<Void> deleteMission(@Body Mission m);

    @POST("api/updateMission/")
    Call<Void> updateMission(@Body Mission m);

    @POST("api/updateTruck/")
    Call<Void> updateTruck(@Body Truck t);

    @POST("api/createSensorFire/")
    Call<Void> createSensorFire(@Body SensorFire sf);

    @POST("api/deleteSensorFire/")
    Call<Void> deleteSensorFire(@Body SensorFire sf);
}

/*
public class WebServerConnector {
    private FireManager m_fireManager;
    private DataFactory m_dataFactory;
    private ObjectMapper m_objectMapper;
    //private ObjectMapper m_objectMapper;
    //Var API
    private String m_ip = "192.168.1.1";
    private Integer m_port = 3300;
    private String m_server="http://127.0.0.1:5000/";
    //private String m_user = "userName";
    //private String m_Password = "BeatifulPassword";

    public WebServerConnector(){
        Mode.println("Création WebServerConnector");
        m_dataFactory= new DataFactory();
        m_objectMapper = new ObjectMapper();
        Mode.println("Fin Création WebServerConnector");
    }

    public List<Fire> getFires(){
        if(Mode.USEREELAPI){
            Request request = new Request(m_server+"",Methode.GET);
            Response response = request.get();
            List<Fire> fires = null;
            try {
                fires = m_objectMapper.readValue(response.m_retour, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return
        }else{
            return null;
        }
    }

    public List<Barrack> getBarracks(){
        if(Mode.USEREELAPI){
            return null;
        }else{
            return m_dataFactory.getBarracks();
        }
    }

    public List<Mission> getMissions(){
        if(Mode.USEREELAPI){
            return null;
        }else{
            return null;
        }
    }

    public List<Truck> getTrucks(){
        if(Mode.USEREELAPI){
            return null;
        }else{
            return m_dataFactory.getTrucks();
        }
    }

    public List<Sensor> getSensors() {
        Mode.println("WebServerConnector: getSensors");
        if(Mode.USEREELAPI){
            return null;
        }else{
            return m_dataFactory.getSensors();
        }
    }

    public void postFires(List<Fire> m_fires) {
    }

    public void postMissions(List<Mission> m_missions) {
    }

    public void postTrucks(List<Truck> m_trucks) {
    }
}*/

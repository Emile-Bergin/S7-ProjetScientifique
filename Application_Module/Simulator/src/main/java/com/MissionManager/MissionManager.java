package com.MissionManager;

import com.Connectors.ApiEmergencyWebServer;
import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Truck;
import com.SensorManager.SensorManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MissionManager {
    private ApiEmergencyWebServer m_apiEmergencyWebServer;
    private List<Truck> m_trucks;
    private List<Mission> m_missions;
    private List<Truck> m_trucksToUpdate;
    private SensorManager m_sensormanager;

    public  MissionManager(SensorManager sensorManager){
        m_apiEmergencyWebServer =  new ApiEmergencyWebServer();
        m_sensormanager= sensorManager;
    }

    public void update() {
        m_trucks= m_apiEmergencyWebServer.getTrucks();
        m_missions= m_apiEmergencyWebServer.getMissions();
        updateTrucks();
        postUpdatesTrucks();
        m_sensormanager.update();
    }

    void updateTrucks() {
        m_trucksToUpdate =new ArrayList<Truck>();
        if(m_missions!=null) {
            for (Mission m : m_missions) {
                if (m.getM_trucks().size() != 0) {
                    for (Truck t : m.getM_trucks()) {
                        //bringTruckCloserToFire(t,m.getM_fire());
                        t.setM_latitude(new Random().nextDouble());     //Position Bidon
                        t.setM_longitude(new Random().nextDouble());    //Position Bidon
                        m_trucksToUpdate.add(t);
                    }
                }
            }
        }
    }

    private void bringTruckCloserToFire(Truck t, Fire f) {

    }

    void postUpdatesTrucks() {
        if(m_trucksToUpdate.size()>0){
            for(Truck t : m_trucksToUpdate){
                m_apiEmergencyWebServer.updateTruck(t);
            }
        }
    }
}

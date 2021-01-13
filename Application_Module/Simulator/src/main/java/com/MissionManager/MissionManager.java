package com.MissionManager;

import com.Connectors.ApiEmergencyWebServer;
import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Mode;
import com.Objects.Truck;
import com.SensorManager.SensorManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MissionManager {
    private ApiEmergencyWebServer m_apiEmergencyWebServer;
    private List<Truck> m_trucks;
    private List<Mission> m_missions;
    private List<Fire> m_fires;
    private SensorManager m_sensormanager;

    public  MissionManager(SensorManager sensorManager){
        m_apiEmergencyWebServer =  new ApiEmergencyWebServer();
        m_sensormanager= sensorManager;
    }

    public void update() {
        Mode.println("MissionManager: updateTrucks");
        m_trucks= m_apiEmergencyWebServer.getTrucks();
        Mode.println(m_trucks.toString());

        Mode.println("MissionManager: updateMissions");
        m_missions= m_apiEmergencyWebServer.getMissions();
        Mode.println(m_missions.toString());

        Mode.println("MissionManager: updateFires");
        m_fires= m_apiEmergencyWebServer.getFires();
        Mode.println(m_fires.toString());

        updateTrucks();
        m_sensormanager.update();
    }

    void updateTrucks() {
        for (Mission m : m_missions) {
            for(Fire f : m_fires){
                if(m.getM_idfire() == f.getM_id()){
                    for (Truck t : m_trucks) {
                        if(m.getM_idtruck() == t.getM_id()) {
                            //bringTruckCloserToFire(t, f);
                            t.setM_latitude(new Random().nextDouble()*10);     //Position Bidon
                            t.setM_longitude(new Random().nextDouble()*10);    //Position Bidon
                            m_apiEmergencyWebServer.updateTruck(t);
                        }
                    }
                }
            }

        }

    }

    private void bringTruckCloserToFire(Truck t, Fire f) {
        Double distanceLongitude= Math.abs(t.getM_longitude()-f.getM_longitude());
        if(t.getM_longitude()<f.getM_longitude()){
            t.setM_longitude(t.getM_longitude()+distanceLongitude/2);
        }else{
            t.setM_longitude(t.getM_longitude()-distanceLongitude/2);
        }

        Double distanceLatitude= Math.abs(t.getM_longitude()-f.getM_longitude());
        if(distanceLatitude<0.0001)
        if(t.getM_latitude()<f.getM_latitude()) {
            if (t.getM_latitude() < f.getM_latitude()) {
                t.setM_latitude(t.getM_latitude() + distanceLatitude / 2);
            } else {
                t.setM_latitude(t.getM_latitude() - distanceLatitude / 2);
            }
        }
    }
}

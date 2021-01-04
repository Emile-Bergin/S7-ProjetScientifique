package com.SuperCompany;

import java.util.ArrayList;
import java.util.List;

public class MissionManager implements Manager {
    private List<Mission> m_missions= new ArrayList<Mission>();
    private List<Barrack> m_barracks= new ArrayList<Barrack>();
    private List<Truck> m_trucks= new ArrayList<Truck>();
    private WebServerConnector m_webServerConnector;

    MissionManager(WebServerConnector wsc){
        Debug.println("Création MissionManager");
        m_webServerConnector=wsc;
        Debug.println("Fin Création MissionManager");
    }

    @Override
    public void update() {
        Debug.println("MissionManager: updateCaptors");
        List<Barrack> Barracks = m_webServerConnector.getBarracks();
        m_trucks = m_webServerConnector.getTrucks();
        Debug.println(m_trucks.toString());
        //List<Mission> missions = updateMissions();
        updateTrucksState();
        newFire();
        detectIntensityFireIncreased();
        detectIntensityEqualZero();
    }

    private void updateTrucksState() {
        Debug.println("MissionManager: updateTrucksState");
        List<Truck> toRemove = new ArrayList<Truck>();
        for(Mission m : m_missions){
            toRemove = new ArrayList<Truck>();
            for(Truck t: m.getM_trucks()){
                for(Truck updatedTruck: m_trucks){
                    if( t.getM_id()==updatedTruck.getM_id()){
                        if(updatedTruck.getM_isBusy()==Boolean.FALSE){
                            toRemove.add(t);//Le camion n'est pas sur la mission, on l'eneleve donc
                        }else {
                            t.setM_latitude(updatedTruck.getM_latitude());
                            t.setM_longitude(updatedTruck.getM_longitude());
                            t.setM_isBusy(updatedTruck.getM_isBusy());
                        }
                    }
                }
            }
            if(toRemove.size()!=0){
                List<Truck> newListWithRemovedTrucks=m.getM_trucks();
                newListWithRemovedTrucks.removeAll(toRemove);
                m.setM_trucks(newListWithRemovedTrucks);
            }
        }
    }

    private void newFire() {
        Debug.println("MissionManager: newFire");

    }

    private void detectIntensityFireIncreased() {
        Debug.println("MissionManager: detectIntensityFireIncreased");
    }

    private void detectIntensityEqualZero() {
        Debug.println("MissionManager: detectIntensityEqualZero");
    }




}

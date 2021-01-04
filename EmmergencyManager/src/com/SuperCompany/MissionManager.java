package com.SuperCompany;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {
    private List<Fire> m_fires= new ArrayList<Fire>();
    private List<Mission> m_missions= new ArrayList<Mission>();
    private List<Barrack> m_barracks= new ArrayList<Barrack>();
    private List<Truck> m_trucks= new ArrayList<Truck>();
    private WebServerConnector m_webServerConnector;

    MissionManager(WebServerConnector wsc){
        Debug.println("Création MissionManager");
        m_webServerConnector=wsc;
        Debug.println("Fin Création MissionManager");
    }

    public void update(List<Fire> fires) {
        Debug.println("MissionManager: updateCaptors");
        m_fires=fires;
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
        List<Fire> newFires= new ArrayList<Fire>();
        Boolean isPresent=Boolean.FALSE;
        for(Fire f : m_fires){
            isPresent=Boolean.FALSE;
            for(Mission m :m_missions){
                if(f.getM_id()==m.getM_fire().getM_id()){
                    isPresent=Boolean.TRUE;
                }
            }
            if (!isPresent){
                newFires.add(f);
            }
        }
        if (newFires.size()>0){
            for(Fire f :newFires){
                Mission newMission = new Mission(f);
                Truck t =isTruckFree(f.getM_intensity());
                if(t!=null){
                    t.setM_isBusy(Boolean.TRUE);
                    List<Truck> trucks = new ArrayList<Truck>();
                    trucks.add(t);
                    newMission.setM_trucks(trucks);
                }
            }
            updateMission();
        }

    }

    private void updateMission() {
    }

    private Truck isTruckFree(Integer intensity) {
        List<Truck> potentialTrucks=new ArrayList<Truck>();
        for(Truck t : m_trucks){
            if(t.getM_isBusy()==Boolean.FALSE){
                potentialTrucks.add(t);
            }
        }
        Truck finalTruck=null;
        int previous=10000;
        for(Truck t : potentialTrucks){
            int dist= Math.abs(intensity-t.getM_type());
            if(dist<=previous){
                previous=dist;
                finalTruck=t;
            }
        }
        return finalTruck;
    }

    private void detectIntensityFireIncreased() {
        Debug.println("MissionManager: detectIntensityFireIncreased");
        for(Fire f : m_fires){
            if (f.getM_increase()){

            }
        }
    }

    private void detectIntensityEqualZero() {
        Debug.println("MissionManager: detectIntensityEqualZero");
    }




}

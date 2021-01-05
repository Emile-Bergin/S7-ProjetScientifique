package com.MissionManager;

import com.SuperCompany.*;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {
    List<Fire> m_fires= new ArrayList<Fire>();
    List<Mission> m_missions= new ArrayList<Mission>();
    private List<Barrack> m_barracks= new ArrayList<Barrack>();
    List<Truck> m_trucks= new ArrayList<Truck>();
    private WebServerConnector m_webServerConnector;

    public MissionManager(WebServerConnector wsc){
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
        Debug.println(m_missions.toString());
    }

    void updateTrucksState() {
        Debug.println("MissionManager: updateTrucksState");
        List<Truck> toRemove = new ArrayList<Truck>();
        for(Mission m : m_missions){                                                //Parcours les mission
            toRemove = new ArrayList<Truck>();                                      //Liste des camions a enlever
            if(m.getM_trucks()!=null) {                                             //Si il y a des camions
                for (Truck t : m.getM_trucks()) {                                   //parcours les camions des missions
                    for (Truck updatedTruck : m_trucks) {                           //parcours les camions de la classe (mis a jour préalablement)
                        if (t.getM_id() == updatedTruck.getM_id()) {                //Si les camions correspondent
                            if (updatedTruck.getM_isBusy() == Boolean.FALSE) {      //si le camion qui est a jour n'est pas occupé, on l'enleve de la mission
                                toRemove.add(t);                                    //Le camion n'est pas sur la mission, on l'eneleve donc
                            } else {                                                //Si non, on le met à jour
                                t.setM_latitude(updatedTruck.getM_latitude());
                                t.setM_longitude(updatedTruck.getM_longitude());
                                t.setM_isBusy(updatedTruck.getM_isBusy());
                            }
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

    void newFire() {
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
                launchTruckOnMission(newMission);
                m_missions.add(newMission);
            }
            updateMission();
        }

    }

    Boolean launchTruckOnMission(Mission m) {
        Truck t =isTruckFree(m.getM_fire().getM_intensity());
        if(t!=null){
            t.setM_isBusy(Boolean.TRUE);
            List<Truck> trucks = new ArrayList<Truck>();
            trucks.add(t);
            m.setM_trucks(trucks);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    Truck isTruckFree(Integer intensity) {
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

    void detectIntensityFireIncreased() {
        Debug.println("MissionManager: detectIntensityFireIncreased");
        for(Fire f : m_fires){
            if (f.getM_increase()){                                 //Si le feu augmente
                for(Mission m : m_missions){                        //On cherche la mission qui lui est rattaché
                    if(m.getM_fire().getM_id()== f.getM_id()){
                        Boolean launchTruck = launchTruckOnMission(m);          //On lance un camion supplémentaire
                        if(launchTruck==Boolean.TRUE){                          //Si un camion a été lancé on eneleve Increase sur le feux
                            f.setM_increase(Boolean.FALSE);
                        }
                    }
                }
            }
        }
        updateMission();
    }

    void detectIntensityEqualZero() {
        Debug.println("MissionManager: detectIntensityEqualZero");
        Fire fireOut=null;
        Mission missionFinished=null;
        for(Mission m: m_missions){
            if(m.getM_fire().getM_intensity()==0){                  //Si plus d'intensité
                if(m.getM_trucks()!=null){
                    for(Truck t : m.getM_trucks()){
                        t.setM_isBusy(Boolean.FALSE);                   //Tous les camions ne sont plus occupés
                    }
                }
                fireOut=m.getM_fire();
                missionFinished=m;
            }
        }
        if(fireOut!=null){
            m_fires.remove(fireOut);
        }
        if(missionFinished!=null){
            m_missions.remove(missionFinished);
        }
        updateMission();
        updateFire();
        updateTrucks();
    }

    private void updateMission() { //Send mission to data base via WebServerConnector
    }

    private void updateFire() {
    }
    private void updateTrucks() {
    }


}

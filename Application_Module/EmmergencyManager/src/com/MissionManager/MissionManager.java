package com.MissionManager;

import com.Connector.Api;
import com.Objects.Barrack;
import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Truck;
import com.SuperCompany.*;
import java.util.ArrayList;
import java.util.List;

public class MissionManager {
    List<Fire> m_fires= new ArrayList<Fire>();
    List<Mission> m_missions= new ArrayList<Mission>();
    private List<Barrack> m_barracks= new ArrayList<Barrack>();
    List<Truck> m_trucks= new ArrayList<Truck>();
    private Api m_api;

    public MissionManager(Api api){
        Mode.println("Création MissionManager");
        m_api =api;
        Mode.println("Fin Création MissionManager");
    }

    public void update(List<Fire> fires) {
        Mode.println("MissionManager: updateCaptors");
        m_fires=fires;

        updatedBarracks();
        updateMissions();
        updateTrucks();

        newFire();
        detectIntensityFireIncreased();
        detectIntensityEqualZero();

        Mode.println(m_missions.toString());
    }

    private void updatedBarracks() {
        Mode.println("MissionManager: updateBarracks");
        List<Barrack> updatedBarracks = m_api.getBarracks();
        compareBarracks(updatedBarracks);
    }

    private void compareBarracks(List<Barrack> updatedBarracks) {
        Mode.println("MissionManager: updateBarracks");
        //TODO
    }

    private void updateMissions() {
        List<Mission> UpdatedMissions = m_api.getMissions();
        compareMissions();
        updateMissionState(UpdatedMissions);
    }

    private void compareMissions() {
        //TODO
    }


    private void updateMissionState(List<Mission> missions) {
        Mode.println("MissionManager: updateMissionState");
        //TODO
    }

    private void updateTrucks() {
        m_trucks = m_api.getTrucks();
        Mode.println(m_trucks.toString());
        updateTrucksState();
    }

    void updateTrucksState() {
        Mode.println("MissionManager: updateTrucksState");
        List<Truck> toRemove = new ArrayList<Truck>();
        for(Mission m : m_missions){                                                //Parcours les mission
            toRemove = new ArrayList<Truck>();                                      //Liste des camions a enlever
            if(m.getM_trucks()!=null) {                                             //Si il y a des camions
                for (Truck t : m.getM_trucks()) {                                   //parcours les camions des missions
                    for (Truck updatedTruck : m_trucks) {                           //parcours les camions de la classe (mis a jour préalablement)
                        if (t.getM_id() == updatedTruck.getM_id()) {                //Si les camions correspondent
                            if (updatedTruck.getM_isBusy() == Boolean.FALSE) {      //si le camion qui est a jour n'est pas occupé, on l'enleve de la mission
                                toRemove.add(t);                                    //Le camion n'est pas sur la mission, on l'eneleve donc
                                m_api.updateMission(m);
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
        Mode.println("MissionManager: newFire");
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
                m_api.createMission(newMission);
            }
        }

    }

    Boolean launchTruckOnMission(Mission m) {
        Truck t =isTruckFree(m.getM_fire().getM_intensity());
        if(t!=null){
            t.setM_isBusy(Boolean.TRUE);
            List<Truck> trucks = new ArrayList<Truck>();
            trucks.add(t);
            m.setM_trucks(trucks);
            m_api.updateMission(m);
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
        Mode.println("MissionManager: detectIntensityFireIncreased");
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
    }

    void detectIntensityEqualZero() {
        Mode.println("MissionManager: detectIntensityEqualZero");
        Fire fireOut=null;
        Mission missionFinished=null;
        for(Mission m: m_missions){
            if(m.getM_fire().getM_intensity()==0){                  //Si plus d'intensité
                if(m.getM_trucks()!=null){
                    for(Truck t : m.getM_trucks()){
                        t.setM_isBusy(Boolean.FALSE);                   //Tous les camions ne sont plus occupés
                        m_api.updateTruck(t);
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
    }
}

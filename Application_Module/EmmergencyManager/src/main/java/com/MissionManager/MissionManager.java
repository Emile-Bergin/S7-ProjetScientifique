package com.MissionManager;

import com.Connector.Api;
import com.Objects.Barrack;
import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Truck;
import com.SuperCompany.Manager;
import com.SuperCompany.Mode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MissionManager implements Manager {
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

    public void update() {
        m_fires = m_api.getFires();
        Mode.println("MissionManager: updateFires");
        Mode.println(m_fires.toString());

        updatedBarracks();
        updateMissions();
        updateTrucks();

        newMission();
        detectIntensityEqualZero();

        Mode.println(m_missions.toString());
    }

    private void updatedBarracks() {
        m_barracks = m_api.getBarracks();
        Mode.println("MissionManager: updateBarracks");
        Mode.println(m_barracks.toString());
    }


    private void updateMissions() {
        m_missions = m_api.getMissions();
        Mode.println("MissionManager: updateMissions");
        Mode.println(m_missions.toString());
    }

    private void updateTrucks() {
        m_trucks = m_api.getTrucks();
        Mode.println("MissionManager: updateTrucks");
        Mode.println(m_trucks.toString());
    }

    void newMission() {
        Mode.println("MissionManager: newMission");
        Boolean beingProcessed;
        for (Fire f : m_fires) {
            beingProcessed = Boolean.FALSE;
            for (Mission m : m_missions) {                    //On regarde si le feu est traité par une mission
                if (f.getM_id() == m.getM_idfire()) {
                    beingProcessed = Boolean.TRUE;
                }
            }
            if (!beingProcessed) {                               //N'est pas traité : On créer une mission si un camion est disponible
                Boolean created = Boolean.FALSE;
                List<Integer> ids_truck = new ArrayList<Integer>();
                for (Mission m : m_missions){
                    ids_truck.add(m.getM_idtruck());
                }
                for (Truck t : m_trucks) {
                    if (!created && !ids_truck.contains(t.getM_id()) && f.getM_intensity()>0){
                        m_api.createMission(new Mission(null, f.getM_id(), t.getM_id(), new Timestamp(System.currentTimeMillis())));       //Création de la mission
                        created = Boolean.TRUE;
                    }
                }
                updateMissions();
            }
        }
    }

    void detectIntensityEqualZero() {
        Mode.println("MissionManager: detectIntensityEqualZero");
        for(Mission m: m_missions){
            for(Fire f : m_fires){
                if(m.getM_idfire() == f.getM_id() && f.getM_intensity() == 0){                  //Si plus d'intensité
                    m_api.deleteMission(m);
                }
            }
        }
    }
}

package com.SuperCompany;

import com.MissionManager.MissionManager;
import com.SensorManager.SensorManager;

public class GeneralManager {
    private MissionManager m_missionManager;
    private SensorManager m_sensorManager;

    public GeneralManager(){
        m_sensorManager= new SensorManager();
        m_missionManager=new MissionManager(m_sensorManager);
    }

    public void update() {
        m_missionManager.update();
    }
}

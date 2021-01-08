package com.MissionManager;

import com.SensorManager.SensorManager;
import org.junit.Before;
import org.junit.Test;

public class MissionMangerTest {
    private SensorManager m_sensorManager;
    private MissionManager m_missionManager;

    @Before
    public void Before(){
        m_sensorManager = new SensorManager();
        m_missionManager = new MissionManager(m_sensorManager);
    }

    @Test
    public void checkUpdateTruck(){
        //m_missionManager.
    }
}

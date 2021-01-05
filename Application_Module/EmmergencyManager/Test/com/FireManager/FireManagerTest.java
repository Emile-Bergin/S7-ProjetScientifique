package com.FireManager;


import com.MissionManager.MissionManager;
import com.SuperCompany.Fire;
import com.SuperCompany.Mission;
import com.SuperCompany.Sensor;
import com.SuperCompany.WebServerConnector;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FireManagerTest {
    private FireManager fm;
    private MissionManager mm;
    private WebServerConnector wsc;

    @Before
    public void Before(){
        wsc=new WebServerConnector();
        mm = new MissionManager(wsc);
        fm = new FireManager(wsc, mm);
    }

    @Test
    public void checkCreateFires() throws Exception {
        Sensor s1 = new Sensor(0, 0, 0, 45.0, 8.0, 0);
        Sensor s2 = new Sensor(1, 0, 1, 45.0, 8.0, 1);
        Sensor s3 = new Sensor(2, 1, 0, 45.0, 8.0, 2);
        Sensor s4 = new Sensor(3, 1, 1, 45.0, 8.0, 3);
        Sensor s5 = new Sensor(4, 2, 0, 45.0, 8.0, 4);
        Sensor s6 = new Sensor(5, 2, 1, 45.0, 8.0, 5);
        List<Sensor> sensors = new ArrayList<Sensor>();
        sensors.add(s1);
        sensors.add(s2);
        sensors.add(s3);
        sensors.add(s4);
        sensors.add(s5);
        sensors.add(s6);

        fm.DetectNewAndIncreaseFire(sensors);

        assertEquals(fm.m_fires.size(),5);
    }

    @Test
    public void checkUpdateSensorsAddNewSensor(){
        List<Sensor>sensorsWithNewOnes= new ArrayList<Sensor>();
        for(int i=0;i<=5;i++){
            try {
                Sensor s= new Sensor(i, i,i,45.0, 8.0, 2);
                sensorsWithNewOnes.add(s);
                fm.m_sensors.add(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            sensorsWithNewOnes.add( new Sensor(6,6,6,45.0, 8.0, 0));
            sensorsWithNewOnes.add( new Sensor(7,6,6,45.0, 8.0, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        fm.updateSensors(sensorsWithNewOnes);

        assertEquals(fm.m_sensors.size(),8);
    }

    @Test
    public void checkUpdateSensorsUpdateIntensity() throws Exception {
        List<Sensor>sensorsWithNewOnes= new ArrayList<Sensor>();
        for(int i=0;i<=5;i++){
            try {
                Sensor s= new Sensor(i, i,i,45.0, 8.0, 2);
                sensorsWithNewOnes.add(s);
                fm.m_sensors.add(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sensorsWithNewOnes.get(0).setM_intensity(new Integer(3));
        sensorsWithNewOnes.get(1).setM_intensity(new Integer(1));
        sensorsWithNewOnes.get(2).setM_intensity(new Integer(5));
        sensorsWithNewOnes.get(3).setM_intensity(new Integer(6));

        fm.updateSensors(sensorsWithNewOnes);

        assertEquals(fm.m_sensors.get(0).getM_intensity(), new Integer(3));
        assertEquals(fm.m_sensors.get(1).getM_intensity(), new Integer(1));
        assertEquals(fm.m_sensors.get(2).getM_intensity(), new Integer(5));
        assertEquals(fm.m_sensors.get(3).getM_intensity(), new Integer(6));
        assertEquals(fm.m_sensors.get(4).getM_intensity(), new Integer(2));
        assertEquals(fm.m_sensors.get(5).getM_intensity(), new Integer(2));
    }

    @Test
    public void checkIncreaseFire(){
        for(int i=0;i<=5;i++){
            fm.m_fires.add(new Fire(i, null, 45.0,8.0,1));
            try {
                fm.m_sensors.add(new Sensor(i, i,i,45.0, 8.0, 2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(fm.m_sensors.toString());
        System.out.println(fm.m_fires.toString());
        fm.updateFires();
        System.out.println(fm.m_fires.toString());

        for(Fire f: fm.m_fires){
            assertEquals(f.getM_increase(),Boolean.TRUE);
            assertEquals(f.getM_intensity(), new Integer(2));
        }

    }


}

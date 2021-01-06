package com.MissionManager;

import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Truck;
import com.Connector.WebServerConnector;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MissionManagerTest {
    private WebServerConnector wsc;
    private MissionManager mm;

    @Before
    public void Before(){
        wsc=new WebServerConnector();
        mm= new MissionManager(wsc);
    }

    @Test
    public void checkUpdateTrucksStateWhenUpdatedTruckIsNotBusy(){
        List<Mission> missions= new ArrayList<Mission>();
        for(int i=0;i<=1;i++){
            Fire f = new Fire(1,null,45.0, 8.0,2);
            List<Truck> trucks = new ArrayList<Truck>();
            Truck t1= new Truck(0+i,1,1,45.0,8.0);
            Truck t2= new Truck(10+i,1,1,45.0,8.0);
            t1.setM_isBusy(Boolean.TRUE);
            t2.setM_isBusy(Boolean.TRUE);
            trucks.add(t1);
            trucks.add(t2);
            Mission m =new Mission(f);
            m.setM_trucks(trucks);
            missions.add(m);

            t1.setM_isBusy(Boolean.FALSE);
            mm.m_trucks.add(t1);
            mm.m_trucks.add(t2);
        }

        mm.updateTrucksState();

        for( Mission m: mm.m_missions){
            assertEquals(m.getM_trucks().size(),1);
        }
    }

    @Test
    public void checkUpdateTrucksStateWhenUpdatedTruckIsNotUpdateInMission(){///met a jour le camion dans la mission
        List<Mission> missions= new ArrayList<Mission>();
        for(int i=0;i<=1;i++){
            Fire f = new Fire(1,null,45.0, 8.0,2);
            List<Truck> trucks = new ArrayList<Truck>();
            Truck t1= new Truck(0+i,1,1,45.0,8.0);
            Truck t2= new Truck(10+i,1,1,45.0,8.0);
            t1.setM_isBusy(Boolean.FALSE);
            t2.setM_isBusy(Boolean.TRUE);
            trucks.add(t1);
            trucks.add(t2);
            Mission m =new Mission(f);
            m.setM_trucks(trucks);
            missions.add(m);

            t1.setM_longitude(50.0);
            t1.setM_latitude(60.0);
            t1.setM_isBusy(Boolean.TRUE);
            t2.setM_longitude(50.0);
            t2.setM_latitude(60.0);
            mm.m_trucks.add(t1);
            mm.m_trucks.add(t2);
        }

        mm.updateTrucksState();

        for( Mission m: mm.m_missions){
            for(Truck t: m.getM_trucks()){
                assertEquals(t.getM_latitude(), new Double(60.0));
                assertEquals(t.getM_longitude(), new Double(50.0));
                assertEquals(t.getM_isBusy(),Boolean.TRUE);
            }
        }
    }

    @Test
    public void checkNewFire(){
        for(int i=0;i<=1;i++) {
            mm.m_fires.add(new Fire(i,null,45.0,8.0,2));
        }
        mm.m_missions.add(new Mission(new Fire(0, null,45.0, 8.0, 2)));

        mm.newFire();

        assertEquals(mm.m_missions.size(),2);
    }

    @Test
    public void checkLaunchTruckOnMissionWhenNoTruckDisponible(){
        mm.m_missions.add(new Mission(new Fire(0,null,45.0,8.0,5)));
        Truck t= new Truck(0,0,5,45.0, 8.0);
        t.setM_isBusy(Boolean.TRUE);
        mm.m_trucks.add(t);
        Boolean result = mm.launchTruckOnMission(mm.m_missions.get(0));

        assertEquals(mm.m_trucks.size(), 1);
        assertEquals(result, Boolean.FALSE);
    }

    @Test
    public void checkLaunchTruckOnMissionWhenTruckDisponible(){
        mm.m_missions.add(new Mission(new Fire(0,null,45.0,8.0,5)));
        mm.m_trucks.add(new Truck(0,0,5,45.0, 8.0));

        Boolean result = mm.launchTruckOnMission(mm.m_missions.get(0));

        assertEquals(mm.m_trucks.size(), 1);
        assertEquals(result, Boolean.TRUE);
        assertEquals(mm.m_missions.get(0).getM_trucks().get(0).getM_id(), new Integer(0));
    }

    @Test
    public void checkIsTruckFree1(){
        mm.m_trucks.add(new Truck(0,0,8, 45.0, 8.0));
        mm.m_trucks.add(new Truck(1,0,2, 45.0, 8.0));

        Truck t=mm.isTruckFree(3);

        assertEquals(t.getM_id(),new Integer(1));
    }

    @Test
    public void checkIsTruckFree2(){
        mm.m_trucks.add(new Truck(0,0,8, 45.0, 8.0));
        mm.m_trucks.add(new Truck(1,0,2, 45.0, 8.0));

        Truck t=mm.isTruckFree(6);

        assertEquals(t.getM_id(),new Integer(0));
    }

    @Test
    public void checkIsTruckFreeWhenNoTruck(){
       Truck t=mm.isTruckFree(6);

       assertEquals(mm.m_trucks.size(), 0);
       assertEquals(t, null);
    }

    @Test
    public void checkDetectIntensityFireIncreasedWhenNoTruckAvailable(){
        Fire f1= new Fire(0,null, 45.0, 8.0,2);
        f1.setM_increase(Boolean.TRUE);
        mm.m_fires.add(f1);
        mm.m_missions.add(new Mission(f1));

        mm.detectIntensityFireIncreased();

        assertEquals(mm.m_fires.get(0).getM_increase(), Boolean.TRUE);
    }

    @Test
    public void checkDetectIntensityFireIncreasedWhenTruckAvailable(){
        mm.m_trucks.add(new Truck(0,0,5,45.0,8.0));
        Fire f1= new Fire(0,null, 45.0, 8.0,2);
        f1.setM_increase(Boolean.TRUE);
        mm.m_fires.add(f1);
        mm.m_missions.add(new Mission(f1));

        mm.detectIntensityFireIncreased();

        assertEquals(mm.m_fires.get(0).getM_increase(), Boolean.FALSE);
    }

    @Test
    public void checkDetectIntensityEqualZeroWhenFireIntensityEqualZero(){
        Fire f=new Fire(0,null, 48.0 ,5.0 ,0);
        mm.m_missions.add(new Mission(f));

        mm.detectIntensityEqualZero();

        assertEquals(mm.m_missions.size(), 0);
    }

    @Test
    public void checkDetectIntensityEqualZeroWhenFireIntensityNotEqualZero(){
        Fire f=new Fire(0,null, 48.0 ,5.0 ,1);
        mm.m_missions.add(new Mission(f));

        mm.detectIntensityEqualZero();

        assertEquals(mm.m_missions.size(), 1);
    }
}

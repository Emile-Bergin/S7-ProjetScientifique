package com.MissionManager;

import com.FireManager.FireManager;
import com.SuperCompany.Fire;
import com.SuperCompany.Mission;
import com.SuperCompany.Truck;
import com.SuperCompany.WebServerConnector;
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

    private void checkUpdateTrucksStateWhenUpdatedTruckIsNotUpdateInMission(){

    }
}

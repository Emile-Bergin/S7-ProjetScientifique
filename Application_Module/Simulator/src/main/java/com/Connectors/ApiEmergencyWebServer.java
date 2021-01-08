package com.Connectors;

import com.Objects.Fire;
import com.Objects.Mission;
import com.Objects.Mode;
import com.Objects.Truck;

import java.util.List;

public class ApiEmergencyWebServer {
    private EmergencyWebServerConnector m_emergencyWebServerConnector;

    public ApiEmergencyWebServer(EmergencyWebServerConnector ewsc){
        m_emergencyWebServerConnector =ewsc;
    }

    public List<Truck> getTrucks(){
        if(Mode.USEREELAPI){
            //TODO
            return null;
        }else
            return null;
    }

    public List<Mission> getMissions(){
        if(Mode.USEREELAPI){
            //TODO
            return null;
        }else
            return null;
    }

    public void  updateTruck(Truck t){
        if(Mode.USEREELAPI){
            //TODO
        }
    }

    public List<Fire> getFires(){
        if(Mode.USEREELAPI){
            //TODO
            return null;
        }else
            return null;
    }
}

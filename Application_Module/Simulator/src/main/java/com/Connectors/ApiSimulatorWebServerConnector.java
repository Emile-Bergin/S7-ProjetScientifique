package com.Connectors;

import com.Objects.Mission;
import com.Objects.Mode;
import com.Objects.Sensor;
import com.Objects.Truck;

public class ApiSimulatorWebServerConnector {
    private SimulatorWebServerConnector m_simulatorWebServerConnector;

    public ApiSimulatorWebServerConnector(SimulatorWebServerConnector swsc){
        m_simulatorWebServerConnector=swsc;
    }

    public void  updateTruck(Truck t){
        if(Mode.USEREELAPI){
            //TODO
        }
    }

    public void  updateMission(Mission m){
        if(Mode.USEREELAPI){
            //TODO
        }
    }

    public void  updateSensor(Sensor s){
        if(Mode.USEREELAPI){
            //TODO
        }
    }
}

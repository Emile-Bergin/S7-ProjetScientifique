package com.EmergencyWebServerConnectors;

import com.Objects.Mode;
import com.Objects.Sensor;

public class ApiMicroBitWebServerConnector {
    private MicroBitWebServerConnector m_microBitWebServerConnector;

    public ApiMicroBitWebServerConnector(MicroBitWebServerConnector mbwsc){
        m_microBitWebServerConnector=mbwsc;
    }

    public void updateSensor(Sensor s) {
        if (Mode.USEREELAPI) {
            //TODO
        }
    }
}

package com.Connectors;

import com.Objects.Mode;
import com.Objects.Sensor;

public class ApiMicroBitWebServer {
    private MicroBitWebServerConnector m_microBitWebServerConnector;

    public ApiMicroBitWebServer(MicroBitWebServerConnector mbwsc){
        m_microBitWebServerConnector=mbwsc;
    }

    public void updateSensor(Sensor s) {
        if (Mode.USEREELAPI) {
            //TODO
        }
    }
}

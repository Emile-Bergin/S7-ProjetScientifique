package com.Connectors;

import com.Objects.Mode;
import com.Objects.Sensor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMicroBitWebServer {
    private MicroBitWebServerConnector m_microBitWebServerConnector;
    private Retrofit m_retrofit;

    public ApiMicroBitWebServer(){
        m_retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:5000/").addConverterFactory(GsonConverterFactory.create()).build();
        m_microBitWebServerConnector= m_retrofit.create(MicroBitWebServerConnector.class);

    }

    public void updateSensor(Sensor s) {
        if (Mode.USEREELAPI) {
            //TODO
        }
    }
}
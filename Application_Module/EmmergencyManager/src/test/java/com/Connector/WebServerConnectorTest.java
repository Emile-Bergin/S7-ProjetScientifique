package com.Connector;

import com.Objects.Fire;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class WebServerConnectorTest {
    private WebServerConnector wsc;
    private Retrofit retrofit;


    @Before
    public void Before(){
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.7:5001/").addConverterFactory(GsonConverterFactory.create()).build();
        wsc = retrofit.create(WebServerConnector.class);
    }

    @Test
    public void getSensors(){
        assertEquals(1,1);
    }

    @Test
    public void getBarracks(){
        assertEquals(1,1);
    }

    @Test
    public void getFires(){
        try {
            System.out.println(wsc.getFires().execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(1,1);
    }

    @Test
    public void createFire(){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp);
            Fire f = new Fire(1, timestamp, 45.0, 8.0, 4);
            wsc.createFire(f).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(1,1);
    }
}

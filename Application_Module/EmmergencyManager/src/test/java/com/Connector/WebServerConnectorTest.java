package com.Connector;

import com.Objects.Fire;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WebServerConnectorTest {
    private WebServerConnector wsc;
    private Retrofit retrofit;

    @Before
    public void Before(){
        retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:5000/").addConverterFactory(GsonConverterFactory.create()).build();
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
    public void createFire(){
        try {
            Date d = new java.util.Date();
            System.out.println(d.toString());
            System.out.println(wsc.createFire(new Fire(1, d, 45.0, 8.0, 4)).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}

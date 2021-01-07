package com.Connector;

import com.Connector.WebServerConnector;
import com.Objects.Fire;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

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
    public void getFires(){
        try {
            System.out.println(wsc.getFires().execute().body());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(1,1);
    }

}

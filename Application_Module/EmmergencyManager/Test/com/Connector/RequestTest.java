package com.Connector;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class RequestTest extends TestCase {
    private Request requestGET;
    private Request requestPOST;

    @Before
    public void Before(){
        requestGET = new Request("",Methode.GET);
        requestPOST = new Request("127.0.0.1",Methode.POST);
    }

    @Test
    public void test(){
        //FakeServer fs= ;
        //requestPOST.run();
        Request r= new Request("http://localhost:8080/echoPost",Methode.POST);
        r.post();
        assertEquals(1,1);
    }
}

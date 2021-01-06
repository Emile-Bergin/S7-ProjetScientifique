package com.Connector;

import com.Connector.WebServerConnector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class WebServerConnectorTest extends TestCase {
    private WebServerConnector wsc;

    @Before
    public void Before(){
        wsc = new WebServerConnector();
    }

    @Test
    public void test(){
        assertEquals(1,1);
    }

}

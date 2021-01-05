package com.SuperCompany;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class SensorTest {
    private Sensor s;

    @Before
    public void Before() throws Exception {
        s = new Sensor(1,1,1,45.0,8.0,0);
    }

    @Test(expected = Exception.class)
    public void checkIntensityLessThanZeroThrowException() throws Exception {
        Sensor s1 = new Sensor(1,1,1,45.0,8.0,-1);
    }

    @Test(expected = Exception.class)
    public void checkIntensityMoreThanHeightThrowException() throws Exception {
        Sensor s4 = new Sensor(1, 1, 1, 45.0, 8.0, 9);
    }

    @Test
    public void checkCreationOkay() throws Exception {
        Sensor s2 = new Sensor(1,1,1,45.0,8.0,0);
        Sensor s3 = new Sensor(1,1,1,45.0,8.0,8);
    }
}
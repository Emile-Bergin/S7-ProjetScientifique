package com.SuperCompany;

import junit.framework.TestCase;
import org.junit.Test;



public class GeneralManagerTest extends TestCase {

    @Test
    public void makeTheCall() {
        GeneralManager gm = new GeneralManager();
        gm.update();
        assertEquals(1,1);
    }

}

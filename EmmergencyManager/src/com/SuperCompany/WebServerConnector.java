package com.SuperCompany;

import com.FireManager.FireManager;

import java.util.List;

public class WebServerConnector {
    private FireManager m_fireManager;
    private DataFactory m_dataFactory;
    //Var connections BD
    private String m_ip = "192.168.1.1";
    private Integer m_port = 3300;
    private String m_user = "userName";
    private String m_Password = "BeatifulPassword";

    public WebServerConnector(){
        Debug.println("Création WebServerConnector");
        m_dataFactory= new DataFactory();
        Debug.println("Fin Création WebServerConnector");
    }

    protected List<Fire> getFires(){
        return null;
    }

    public List<Barrack> getBarracks(){
        return m_dataFactory.getBarracks();
    }

    protected List<Truck> getMissions(){
        return null;
    }

    public List<Truck> getTrucks(){
        List<Truck> trucks = m_dataFactory.getTrucks(); //A modifier, provisoir
        return trucks;
    }

    public List<Sensor> getSensors() {
        Debug.println("WebServerConnector: getSensors");
        List<Sensor> sensors = m_dataFactory.getSensors(); //A modifier, provisoir
        return sensors;
    }

}

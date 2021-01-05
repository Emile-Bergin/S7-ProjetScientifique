package com.SuperCompany;

import java.util.*;

public class DataFactory {
    private Integer m_column=1;
    private Integer m_line=1;
    private Boolean firstSensors=Boolean.TRUE;
    private List<Sensor> m_Sensors= new ArrayList<Sensor>();
    private Boolean trucksLocation = Boolean.TRUE;

    DataFactory(){}

    public List<Sensor> getSensors() {
        Integer id=0;
        Random rd = new Random();
        m_Sensors=new ArrayList<Sensor>();
        if(firstSensors){
            for (int i=0;i<=m_column;i++){
                for (int k=0;k<=m_line;k++){
                    try {
                        m_Sensors.add(new Sensor(id, i, k, new Double(i), new Double(k), 0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    firstSensors=Boolean.FALSE;
                    id++;
                }
            }
        }else{
            for (int i=0;i<=m_column;i++){
                for (int k=0;k<=m_line;k++){
                    try {
                        m_Sensors.add(new Sensor(id, i, k, new Double(i), new Double(k), rd.nextInt(8)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    firstSensors=Boolean.FALSE;
                    id++;
                }
            }
        }
        return m_Sensors;
    }

    public List<Truck> getTrucks(){
        List Trucks = new ArrayList<Truck>();
        Random rd = new Random();
        for(int i=0; i<=5; i++ ){
            Truck t = new Truck(i,0,0,rd.nextDouble(), rd.nextDouble());
            t.setM_isBusy(rd.nextBoolean());
            Trucks.add(t);
        }
        return Trucks;
    }

    public List<Barrack> getBarracks() {
        List<Barrack> barracks=new ArrayList<>();
        Barrack b= new Barrack(0, 45.0, 8.0);
        barracks.add(b);
        return barracks;
    }
}

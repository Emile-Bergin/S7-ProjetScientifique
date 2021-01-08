package com.Objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mission {
    private Fire m_fire;
    private List<Truck> m_trucks= new ArrayList<Truck>();
    private Date m_date;

    public Mission(Fire m_fire) {
        this.m_fire = m_fire;
        this.m_trucks = null;
        m_date = new Date();
    }

    public Mission(Fire m_fire, List<Truck> m_trucks) {
        this.m_fire = m_fire;
        this.m_trucks = m_trucks;
    }

    public Fire getM_fire() {
        return m_fire;
    }

    public void setM_fire(Fire m_fire) {
        this.m_fire = m_fire;
    }

    public List<Truck> getM_trucks() {
        return m_trucks;
    }

    public void setM_trucks(List<Truck> m_trucks) {
        this.m_trucks = m_trucks;
    }

    @Override
    public String toString() {
        if(m_trucks==null){
            return "\nMission{" +
                    "m_fire=" + m_fire.toString() +
                    '}';
        }else{
            return "\nMission{" +
                    "m_fire=" + m_fire.toString() +
                    ", m_trucks=" + m_trucks.toString() +
                    '}';
        }

    }
}

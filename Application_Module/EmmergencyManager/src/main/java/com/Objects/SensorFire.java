package com.Objects;

public class SensorFire implements PhysiqueElement {
    private Integer m_idSensor;
    private Integer m_idFire;

    public SensorFire(Integer m_idSensor, Integer m_idFire) {
        this.m_idSensor = m_idSensor;
        this.m_idFire = m_idFire;
    }

    @Override
    public String toString() {
        return "SensorFire{" +
                "m_idSensor=" + m_idSensor +
                ", m_idFire=" + m_idFire +
                '}';
    }

    public Integer getM_idSensor() {
        return m_idSensor;
    }

    public Integer getM_idFire() {
        return m_idFire;
    }
}

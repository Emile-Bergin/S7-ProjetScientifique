package com.Objects;

public class Barrack {
    private Integer m_id;
    private Double m_longitude;
    private Double m_latitude;

    public Barrack(Integer m_id, Double m_longitude, Double m_latitude) {
        this.m_id = m_id;
        this.m_longitude = m_longitude;
        this.m_latitude = m_latitude;
    }

    @Override
    public String toString() {
        return "Barrack{" +
                "m_id=" + m_id +
                ", m_longitude=" + m_longitude +
                ", m_latitude=" + m_latitude +
                '}';
    }
}

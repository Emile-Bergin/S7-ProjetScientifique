package com.SuperCompany;

public class Sensor implements PhysiqueElement{
    private Integer m_id;
    private Integer m_column;
    private Integer m_line;
    private Double m_longitude;
    private Double m_latitude;
    private Integer m_intensity;

    public Sensor(Integer m_id, Integer m_column, Integer m_line, Double m_longitude, Double m_latitude, Integer m_intensity) {
        this.m_id = m_id;
        this.m_column = m_column;
        this.m_line = m_line;
        this.m_longitude = m_longitude;
        this.m_latitude = m_latitude;
        this.m_intensity = m_intensity;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "m_id=" + m_id +
                ", m_column=" + m_column +
                ", m_line=" + m_line +
                ", m_longitude=" + m_longitude +
                ", m_latitude=" + m_latitude +
                ", m_intensity=" + m_intensity +
                "}\n";
    }

    public Integer getM_id() {
        return m_id;
    }

    public Double getM_longitude() {
        return m_longitude;
    }

    public Double getM_latitude() {
        return m_latitude;
    }

    public Integer getM_intensity() {
        return m_intensity;
    }

    public void setM_intensity(Integer m_intensity) {
        this.m_intensity = m_intensity;
    }
}

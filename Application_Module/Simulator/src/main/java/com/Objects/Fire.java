package com.Objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fire implements PhysiqueElement {
    private Integer m_id;
    private Date m_date;
    private Double m_longitude;
    private Double m_latitude;
    private Integer m_intensity;

    public Fire(Integer m_id, Date m_date, Double m_longitude, Double m_latitude, Integer m_intensity) {
        this.m_id = m_id;
        this.m_date = m_date;
        this.m_longitude = m_longitude;
        this.m_latitude = m_latitude;
        this.m_intensity = m_intensity;
    }

    @Override
    public String toString() {
        return "Fire{" +
                "m_id=" + m_id +
                ", m_date=" + m_date +
                ", m_longitude=" + m_longitude +
                ", m_latitude=" + m_latitude +
                ", m_intensity=" + m_intensity +
                "}\n";
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_intensity(Integer newIntensity) {
        this.m_intensity = newIntensity;
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
}

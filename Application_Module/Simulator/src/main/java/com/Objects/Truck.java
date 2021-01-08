package com.Objects;

public class Truck implements PhysiqueElement {
    private Integer m_id;
    private Integer m_idCaserne;
    private Integer m_type;
    private Double m_longitude;
    private Double m_latitude;
    private Boolean m_isBusy;

    public Truck(Integer m_id, Integer m_idCaserne, Integer m_type, Double m_longitude, Double m_latitude) {
        this.m_id = m_id;
        this.m_idCaserne = m_idCaserne;
        this.m_type = m_type;
        this.m_longitude = m_longitude;
        this.m_latitude = m_latitude;
        this.m_isBusy=false;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "m_id=" + m_id +
                ", m_idCaserne=" + m_idCaserne +
                ", m_type=" + m_type +
                ", m_longitude=" + m_longitude +
                ", m_latitude=" + m_latitude +
                ", m_isBusy=" + m_isBusy +
                "}\n";
    }

    public Boolean getM_isBusy() {
        return m_isBusy;
    }

    public void setM_isBusy(Boolean m_isBusy) {
        this.m_isBusy = m_isBusy;
    }

    public Double getM_longitude() {
        return m_longitude;
    }

    public void setM_longitude(Double m_longitude) {
        this.m_longitude = m_longitude;
    }

    public Double getM_latitude() {
        return m_latitude;
    }

    public void setM_latitude(Double m_latitude) {
        this.m_latitude = m_latitude;
    }

    public Integer getM_id() {
        return m_id;
    }

    public Integer getM_idCaserne() {
        return m_idCaserne;
    }

    public Integer getM_type() {
        return m_type;
    }
}

package com.Objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mission {
    private Integer m_id;
    private Integer m_idfire;
    private Integer m_idtruck;
    private Date m_date;

    public Mission(Integer m_id, Integer m_idfire, Integer m_idtruck, Date m_date) {
        this.m_id = m_id;
        this.m_idfire = m_idfire;
        this.m_idtruck = m_idtruck;
        this.m_date = m_date;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "m_id=" + m_id +
                ", m_idfire=" + m_idfire +
                ", m_idtruck=" + m_idtruck +
                ", m_date=" + m_date +
                '}';
    }

    public Integer getM_id() {
        return m_id;
    }

    public Integer getM_idfire() {
        return m_idfire;
    }

    public Integer getM_idtruck() {
        return m_idtruck;
    }

    public Date getM_date() {
        return m_date;
    }

    public void setM_idfire(Integer m_idfire) {
        this.m_idfire = m_idfire;
    }

    public void setM_idtruck(Integer m_idtruck) {
        this.m_idtruck = m_idtruck;
    }

    public void setM_date(Date m_date) {
        this.m_date = m_date;
    }
}

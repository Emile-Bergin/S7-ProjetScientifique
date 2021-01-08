package com.Connector;

import com.Objects.*;
import com.SuperCompany.DataFactory;
import com.SuperCompany.Mode;

import java.io.IOException;
import java.util.List;

public class Api {
    private WebServerConnector m_webSeverConnector;
    private DataFactory m_dataFactory;

    public Api(WebServerConnector wsc) {
        m_webSeverConnector = wsc;
        m_dataFactory =  new DataFactory();
    }

    public List<Fire> getFires() {
        if (Mode.USEREELAPI){
            try {
                return m_webSeverConnector.getFires().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }

    public List<Truck> getTrucks() {
        if (Mode.USEREELAPI) {
            try {
                return m_webSeverConnector.getTrucks().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return m_dataFactory.getTrucks();
    }

    public List<Barrack> getBarracks() {
        if (Mode.USEREELAPI) {
            try {
                return m_webSeverConnector.getBarracks().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return m_dataFactory.getBarracks();
    }


    public List<Sensor> getSensors() {
        if (Mode.USEREELAPI) {
            try {
                return m_webSeverConnector.getSensors().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return m_dataFactory.getSensors();
    }

    public List<Mission> getMissions() {
        if (Mode.USEREELAPI) {
            try {
                return m_webSeverConnector.getMissions().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }

    public void createFires(Fire f) {
        if (Mode.USEREELAPI) {
            try {
                m_webSeverConnector.createFire(f).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createMission(Mission m) {
        if (Mode.USEREELAPI) {
            try {
                m_webSeverConnector.createMission(m).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createSensorFire(Sensor s, Fire f) {
        if (Mode.USEREELAPI) {
            try {
                m_webSeverConnector.createSensorFire(s.getM_id().toString(), f.getM_id().toString()).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMission(Mission m) {
        if (Mode.USEREELAPI) {
            try {
                m_webSeverConnector.updateMission(m).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTruck(Truck t) {
        if (Mode.USEREELAPI) {
            try {
                m_webSeverConnector.updateTruck(t).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.SuperCompany;

public class GeneralManager implements Manager{
    //private RefreshData m_refreshData;
    private WebServerConnector m_webServerConnector;
    private FireManager m_fireManager;
    private MissionManager m_missionManager;

    GeneralManager(){
        Debug.println("Création Manager");
        m_webServerConnector= new WebServerConnector();
        m_missionManager = new MissionManager(m_webServerConnector);
        m_fireManager = new FireManager(m_webServerConnector,m_missionManager);
        Debug.println("Fin Création Manager");
    }

    @Override
    public void update() {
        Debug.println("GenralManager : update");
        m_fireManager.update();
    }
}

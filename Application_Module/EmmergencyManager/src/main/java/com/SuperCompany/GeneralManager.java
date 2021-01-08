package com.SuperCompany;

import com.Connector.Api;
import com.Connector.WebServerConnector;
import com.FireManager.FireManager;
import com.MissionManager.MissionManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeneralManager implements Manager{
    //private RefreshData m_refreshData;
    private WebServerConnector m_webServerConnector;
    private FireManager m_fireManager;
    private MissionManager m_missionManager;
    private Retrofit m_retrofit;
    private Api m_api;

    GeneralManager(){
        Mode.println("Création Manager");
        m_retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:5000/").addConverterFactory(GsonConverterFactory.create()).build();
        m_webServerConnector = m_retrofit.create(WebServerConnector.class);
        m_api= new Api(m_webServerConnector);
        m_missionManager = new MissionManager(m_api);
        m_fireManager = new FireManager(m_api,m_missionManager);
        Mode.println("Fin Création Manager");
    }

    @Override
    public void update() {
        Mode.println("GenralManager : update");
        m_fireManager.update();
    }
}

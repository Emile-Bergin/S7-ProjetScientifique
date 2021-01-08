package com.SuperCompany;

import com.Objects.Mode;

public class RefreshData {
    //Class voué a disparaître, remplacé par  ApiService
    private GeneralManager m_generalManager;
    private Integer m_refreshTime = 5; //En secondes

    RefreshData(){
        Mode.println("Création RefreshData");
        m_generalManager= new GeneralManager();
        this.refresh();
        Mode.println("Fin Création RefreshData");
    }

    private void refresh(){

        Thread t = new Thread(){
          public void run(){
              while(true) {
                  Mode.println("");
                  Mode.println("#####################################################################");
                  Mode.println("Trigger Refresh Data");
                  m_generalManager.update();
                  try {
                      Thread.sleep(m_refreshTime*1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };

        t.start();
    }
}

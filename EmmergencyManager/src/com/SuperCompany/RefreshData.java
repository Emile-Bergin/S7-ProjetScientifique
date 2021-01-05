package com.SuperCompany;

public class RefreshData {
    //Class voué a disparaître, remplacé par  ApiService
    private GeneralManager m_generalManager;
    private Integer m_refreshTime = 5; //En secondes

    RefreshData(){
        Debug.println("Création RefreshData");
        m_generalManager= new GeneralManager();
        this.refresh();
        Debug.println("Fin Création RefreshData");
    }

    private void refresh(){

        Thread t = new Thread(){
          public void run(){
              while(true) {
                  Debug.println("");
                  Debug.println("#####################################################################");
                  Debug.println("Trigger Refresh Data");
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

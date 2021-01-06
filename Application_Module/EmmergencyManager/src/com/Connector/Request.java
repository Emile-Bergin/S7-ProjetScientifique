package com.Connector;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class Request {
    private String m_url;
    private Methode m_methode;

    public Request(String url, Methode methode){
        m_url=url;
        m_methode=methode;
    }

    public void get(){
        try {
            URL url = new URL(m_url);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(m_methode.toString());
            int status = con.getResponseCode();
            if(status!=200){
                throw new Exception("Probleme requete");
            }
            if(m_methode==Methode.GET) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println(content);
                in.close();
            }
            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void post() {
        try {
            URL url = new URL(m_url);
            URLConnection con = null;
            con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)con;
            http.setRequestMethod(m_methode.toString()); // PUT is another valid option
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            http.setDoOutput(true);
            String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

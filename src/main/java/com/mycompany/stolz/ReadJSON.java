/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stolz;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;


/**
 *
 * @author markus
 */
public class ReadJSON
{

   
    public String callURL(String myURL)
    {
        System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try
        {
            URL url = new URL(myURL);
            urlConn = url.openConnection();

            if (urlConn != null)
            {
                urlConn.setReadTimeout(60 * 1000);
            }
            if (urlConn != null && urlConn.getInputStream() != null)
            {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null)
                {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1)
                    {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (IOException e)
        {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        } catch (Exception e)
        {
            throw new RuntimeException("Exception while calling URL:" + myURL, e);
        }

        return sb.toString();
    }

    public Modul[] parseToModul(String JSON)
    {
        Gson gson= new Gson();
   
        return  gson.fromJson(JSON, Modul[].class);

    }

    public class Modul
    {
       String m_name,m_icon,m_finished,m_showInProgress,chargeable_c_id,masterplan_m_name;     

        @Override
        public String toString()
        {
            return "Name: "+this.m_name+"  Icon: "+this.m_icon+"\n";
        }

       
    }
    
    
    public static void main(String[] args)
    {
        ReadJSON readJSON=new ReadJSON();
        
       String res= readJSON.callURL("http://cms.alexandertechnik-schultka.de/schnittstelle/getAllModuls");
       
        System.out.println("fdsfsf: " +res);
        
       Modul[] res2= readJSON.parseToModul(res);
       
        System.out.println(res2.length);
     //  System.out.println(res2.length);
    }

}

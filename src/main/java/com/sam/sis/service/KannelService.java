/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import org.springframework.stereotype.Service;
/**
 *
 * @author Sam
 */
@Service
public class KannelService {
    //http://localhost:13013/cgi-bin/sendsms?username=unifun&password=unifun&from=122&to=98411&text=hellofrom&dlr-mask=31&dlr-url=http%3A%2F%2Flocalhost%3A8080%2Fsms%2Fapi%2Fdelivery%3FCampaignID%3D17065%26MSISDN%3D98546413%26Response%3D%25d%26phone%3D%25p
    
     private static HttpURLConnection con;
     //String kannelUrl = "http://localhost:13013/cgi-bin/sendsms?username=unifun&password=unifun&from=100&to=2121&text=hellofrom";
     String responseKannel;
    public int sendRequest(String number, String smstext) throws MalformedURLException,
            ProtocolException, IOException {
        String drlUrl = "http://localhost:8080/sms/delivery?CampaignID=1&MSISDN="+ number + "&Response=%d&phone=%p"; 
        String url = "http://localhost:13013/cgi-bin/sendsms?username=unifun&password=unifun&from=100&to="+number+"&text=" + smstext;// +"&dlr-mask=31&dlr-url=";
        String encodedUrl = url + URLEncoder.encode(drlUrl, "UTF-8");
        System.out.println("kannelserice number is "+ number);
        System.out.println("encodedurl is "+url);
        try {

            URL myurl = new URL(encodedUrl);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            
            responseKannel = content.toString();
            responseKannel = responseKannel.substring(0, 1);
            System.out.println("response is" + responseKannel);
        } finally {
            
            con.disconnect();
        }
        
        int result = Integer.parseInt(responseKannel);
        System.out.println("REsponse is" + result);
        return result;
    }
}

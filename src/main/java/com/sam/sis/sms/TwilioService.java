package com.sam.sis.sms.controller;

import com.sam.sis.entity.Messages;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import com.sam.sis.sms.Numbers;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sam
 */
@Service
public class TwilioService {

    public static final String ACCOUNT_SID = "AC0feb29e6610f931cede7eb7c8b43a5ac";
    public static final String AUTH_TOKEN = "9bf388df92c19edb61a332df3afdb15f";

    public String postSms(String smsmessage, Numbers numbers) {
        String sid = "";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
       
            System.out.print("message is" + smsmessage);
            System.out.print(numbers.getId() + "\n");
            System.out.println(numbers.getNumber());

             Message message = Message.creator(new PhoneNumber("+" + numbers.getNumber()),//to 
                new PhoneNumber("+15005550006"),//from live number"+17038841639"
                smsmessage).setStatusCallback("https://twilio-response1.herokuapp.com").create();

             
            System.out.println("sid is" + message.getSid());//eg sid SM637dbb65bd774dd89bc60e01d0352d30
            //ApiException: Resource not accessible with Test Account Credentials
            /*
            message = Message.fetcher(message.getSid()).fetch();
            System.out.println("message body is" + message.getBody());
            */
            sid = message.getSid();
       


        return sid;
        
    }
   
  
}

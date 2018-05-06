/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.dao.CampaignDetailDAO;
import com.sam.sis.entity.Messages;
import com.sam.sis.dao.MessagesDAO;
import com.sam.sis.dao.NumberDAO;
import com.sam.sis.entity.CampaignDetail;
import com.sam.sis.entity.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Sam
 */
@RestController
@RequestMapping(value = "/api")
public class ClientAPIController {

    @Autowired
    MessagesDAO messageDAO;
    @Autowired
    NumberDAO numberDAO;
    @Autowired
    CampaignDetailDAO campaignDAO;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index() {

        return ResponseEntity.ok(numberDAO.getAll());
    }

    

    @CrossOrigin
    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public @ResponseBody
    List<Numbers> getNumber() {
        List<Numbers> numbers = numberDAO.getAll();
        System.out.println(numbers);
        //do business logic
        return numbers;
    }
    
    @CrossOrigin
    @RequestMapping(value = "/campaign", method = RequestMethod.GET)
    public @ResponseBody
    List<CampaignDetail> getCampaign() {
        List<CampaignDetail> campaignList = campaignDAO.getAll();
        System.out.println(campaignList);
        //do business logic
        return campaignList;
    }

    @CrossOrigin
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public @ResponseBody
    List<Messages> getMessage() {
        List<Messages> messages = messageDAO.getAll();
        System.out.println(messages);
        //do business logic
        return messages;
    }

    @CrossOrigin
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    Messages udpateMessage(@RequestBody Messages messagebody) {
       
        return messagebody;
    }

    @CrossOrigin
    @RequestMapping(value = "/delivery")
    public void deliveryWebHook(@RequestParam(value = "MessageSid") String messageSid,
            @RequestParam(value = "MessageStatus") String messageStatus) {
        System.out.println("SID: " + messageSid + ", Status:" + messageStatus);
        Messages message = new Messages();
        message.setSid(messageSid);
        message.setStatus(messageStatus);
        messageDAO.update(message);
        
     
    }

}

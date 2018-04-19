/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.entity.Messages;
import com.sam.sis.sms.MessagesDAO;
import com.sam.sis.sms.NumberDAO;
import com.sam.sis.sms.Numbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.web.bind.annotation.PathVariable;
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
    TwilioService twilioService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index() {

        return ResponseEntity.ok(numberDAO.getAll());
    }

    @CrossOrigin
    @RequestMapping(value = "/post/{smsmessage}", method = RequestMethod.POST)
    public @ResponseBody
    List<Messages> addNewWorker(@RequestBody List<Numbers> numberList, @PathVariable String smsmessage) {
        //System.out.println(jsonArr);
        for(Numbers item: numberList){
            twilioService = new TwilioService();
            String sid = twilioService.postSms(smsmessage, item);
            Messages message = new Messages();
            message.setBody(smsmessage);
            message.setSid(sid);
            
            Numbers num = new Numbers();
            num.setId(item.getId());
            
            message.setNumberId(num);
            messageDAO.insert(message);
        
        }
        return messageDAO.getAll();
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
        System.out.println(messagebody.getNumberId().getNumber());
        Messages message = new Messages();
        message.setSid(messagebody.getSid());
        message.setBody(messagebody.getBody());
        message.setStatus(messagebody.getStatus());
        /*
        Numbers numbers = new Numbers();
        numbers.setId(messagebody.getNumberId().getId());
        numbers.setNumber(messagebody.getNumberId().getNumber());
        
         */
        message.setNumberId(messagebody.getNumberId());

        //message.setNumberId(messagebody.getNumberId());
        System.out.print("message getNumberId" + messagebody.getNumberId());

        messageDAO.update(message);
        return message;
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

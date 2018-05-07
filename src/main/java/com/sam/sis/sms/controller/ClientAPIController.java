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
import com.sam.sis.service.KannelService;
import com.sam.sis.service.LoadtoSql;
import java.io.IOException;
import java.net.ProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    KannelService kannelService;
    @Autowired
    LoadtoSql loadtoSql;
        

    @CrossOrigin
    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public @ResponseBody
    List<Numbers> getNumber() {
          Numbers num = new Numbers();
           
       
        List<Numbers> numbers = numberDAO.getAll();
   
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
    @RequestMapping(value = "/delivery")
    public void deliveryWebHook(@RequestParam(value = "CampaignId") int campaignId,
            @RequestParam(value = "MSISDN") String mobileNumber,
            @RequestParam(value = "Response") int deliveryResponse) {
            
        
            Numbers number = new Numbers();
            
            CampaignDetail campaign = new CampaignDetail();
            campaign.setId(campaignId);
            number.setCampaignId(campaign);
            number.setDeliveryResponse(deliveryResponse);
            numberDAO.update(number);
     
    }

}

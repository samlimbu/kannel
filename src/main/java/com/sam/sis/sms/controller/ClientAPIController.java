/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.dao.CampaignDetailDAO;
import com.sam.sis.dao.NumberDAO;
import com.sam.sis.entity.CampaignDetail;
import com.sam.sis.entity.Numbers;
import com.sam.sis.service.KannelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 *
 * @author Sam
 */
@RestController
@RequestMapping(value = "/api")
public class ClientAPIController {

    @Autowired
    NumberDAO numberDAO;
    @Autowired
    CampaignDetailDAO campaignDAO;
    @Autowired
    KannelService kannelService;
         

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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.dao.CampaignActionDAO;
import com.sam.sis.dao.CampaignDetailDAO;
import com.sam.sis.entity.Action;
import com.sam.sis.entity.CampaignDetail;
import com.sam.sis.entity.Numbers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sam
 */
@RestController
@RequestMapping(value = "/")
public class DefaultController {
    @Autowired
    CampaignActionDAO actionDAO;
    @Autowired
    CampaignDetailDAO campaignDAO;
    
    @CrossOrigin
    @RequestMapping(value = "/stopCampaign", method = RequestMethod.GET)
    public String stopCampaign(@RequestParam (value = "campaignId") int campaignId) {
             Action action = new Action();
             action.setCampaignId(campaignId);
             action.setAction(1);//stop
             actionDAO.insert(action);
           return "stop";            
    }
    
    @CrossOrigin
    @RequestMapping(value = "/deleteCampaign", method = RequestMethod.GET)
    public String deleteCampaign(@RequestParam (value = "campaignId") int campaignId) {
            CampaignDetail campaign = new CampaignDetail();
            campaign = (CampaignDetail) campaignDAO.getbyId(campaignId);
            if(campaign.getStatus()==2){ //2 means completed
                campaign.setDeleted(true);
            }
            campaignDAO.update(campaign);
            
           return "deleted";            
    }
}

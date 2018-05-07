/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.dao.CampaignActionDAO;
import com.sam.sis.dao.CampaignDetailDAO;
import com.sam.sis.dao.NumberDAO;
import com.sam.sis.entity.Action;
import com.sam.sis.entity.CampaignDetail;
import com.sam.sis.entity.Numbers;
import com.sam.sis.service.KannelService;
import com.sam.sis.sms.controller.ClientAPIController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sam
 */
@RestController
@RequestMapping
public class CsvtoSql {

    @Autowired
    NumberDAO numberDAO;
    @Autowired
    KannelService kannelService;
    @Autowired
    CampaignDetailDAO campaignDAO;
    @Autowired
    CampaignActionDAO actionDAO;

    @RequestMapping(value = "/CsvtoSql", method = RequestMethod.GET)
    String loadNumbers(@RequestParam(value = "campaignId") int campaignId,
            @RequestParam(value = "smsText") String smsText) throws IOException {

        String fileNameDefined = "C:/Users/Sam/Documents/numbers.csv";
        //updating running status
        CampaignDetail campaign = new CampaignDetail();
        campaign.setId(campaignId);
        campaign.setStatus(1);
        campaign.setSms(smsText);
        campaignDAO.update(campaign);

        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);
        System.out.println(campaignId + " " + smsText);
        try {
            // -read from filePooped with Scanner class
            Scanner inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                //read single line, put in string
                String number = inputStream.next();
                System.out.println(number);
                ;

                //get response delivery code fron Kannel
                int resCode = requestKannel(number, smsText);

                CampaignDetail campaign1 = new CampaignDetail();
                campaign1.setId(campaignId);

                Numbers numberObj = new Numbers();

                numberObj.setMobileNumber(number);
                numberObj.setSmsText(smsText);
                numberObj.setCampaignId(campaign1);
                System.out.println(resCode);
                numberObj.setSendstatus(resCode);
                numberDAO.insert(numberObj);
            }
            // after loop, close scanner
            inputStream.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        CampaignDetail campaign3 = new CampaignDetail();
        campaign3.setId(campaignId);
        campaign3.setStatus(2);//2 is code for completed
        campaignDAO.update(campaign3);
        return "success";
    }

    public int requestKannel(String number, String smstext) throws ProtocolException, IOException {
        int responseCode;

        responseCode = kannelService.sendRequest(number, smstext);
        return responseCode;
    }
}

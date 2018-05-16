/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;
import java.util.*; 
import com.sam.sis.dao.NumberDAO;
import com.sam.sis.entity.CampaignDetail;
import com.sam.sis.entity.Numbers;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.Transaction;
/**
 *
 * @author sam
 */
@RestController
@RequestMapping
public class DeliveryController {
    @Autowired
    NumberDAO numberDAO;
    @Autowired
    private SessionFactory factory; 
    private Session session;
    
     @CrossOrigin
    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    public void deliveryWebHook(@RequestParam(value = "CampaignID") int campaignId,
            @RequestParam(value = "MSISDN") String mobileNumber,
            @RequestParam(value = "Response") int deliveryResponse) {
            
            int id = listNumberssEntity(mobileNumber, campaignId);
            // int id =  searchNumber.listNumberssEntity(mobileNumber, campaignId);
        
        
            System.out.println("delivery"+ campaignId + deliveryResponse);
            Numbers number = new Numbers();
            
            CampaignDetail campaign = new CampaignDetail();
            campaign.setId(campaignId);
            
            number.setId(id);
            number.setCampaignId(campaign);
            number.setDeliveryResponse(deliveryResponse);
     
            numberDAO.update(number);
     
    }
    
     public int listNumberssEntity(String mobileNumber, int campaign_id){
                 try{
                     session = factory.getCurrentSession();
                 } 
                 catch(HibernateException e){
                     session = factory.openSession();
                 }
        
      Transaction tx = null;
      int id = -1;
      try {
         tx = session.beginTransaction();
         String sql = "SELECT * FROM `numbers` WHERE mobile_number = " + mobileNumber+ " and campaign_id = "
                + campaign_id;
         SQLQuery query = session.createSQLQuery(sql);
         query.addEntity(Numbers.class);
          List numbers = query.list();

         for (Iterator iterator = numbers.iterator(); iterator.hasNext();){
            Numbers number1 = (Numbers) iterator.next(); 
            System.out.print("no id: " + number1.getId()); 
            id=number1.getId();
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      return id;
   }
}

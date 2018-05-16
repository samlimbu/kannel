/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.dao.CampaignDetailDAO;
import com.sam.sis.dao.CampaignDetailDAOImpl;
import com.sam.sis.entity.CampaignDetail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping
//Max uploaded file size ( 200 MB)
@MultipartConfig(fileSizeThreshold = 209715200)
public class FileUpload {
   
    @Autowired
    CampaignDetailDAO campaignDAO;
    
    @CrossOrigin
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(
            
            @RequestParam("campaignId") String campaignId,
            @RequestParam("campaignName") String campaignName,
            @RequestParam("smsText") String smsText,
            @RequestParam("uploadedFile") MultipartFile uploadedFileRef ) {
    
     
        
         
 
    // Get name of uploaded file.
    String fileName = uploadedFileRef.getOriginalFilename();
    //test
    
    
    // Path where the uploaded file will be stored.
    //C:\Users\Sam\Documents\
    // /home/sam/Downloads
    String path = "/home/sam/Downloads/" + fileName;
    System.out.println("file path is " + path);
    //
    
    //insert into campaign
        CampaignDetail campaign = new CampaignDetail();
        campaign.setId(Integer.parseInt(campaignId));
        campaign.setCampaignName(campaignName);
        campaign.setSms(smsText);
        campaign.setFilePath(path);
    campaignDAO.insert(campaign);
    // This buffer will store the data read from 'uploadedFileRef'
    byte[] buffer = new byte[1000];
    
    // Now create the output file on the server.
    File outputFile = new File(path);

    InputStream reader = null;
    FileOutputStream writer = null;
    int totalBytes = 0;
    try {
        outputFile.createNewFile();

        // Create the input stream to uploaded file to read data from it.
        reader = uploadedFileRef.getInputStream();

        // Create writer for 'outputFile' to write data read from
        // 'uploadedFileRef'
        writer = new FileOutputStream(outputFile);

        // Iteratively read data from 'uploadedFileRef' and write to
        // 'outputFile';            
        int bytesRead = 0;
        while ((bytesRead = reader.read(buffer)) != -1) {
            writer.write(buffer);
            totalBytes += bytesRead;
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
           
        return "success";
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms.controller;

import com.sam.sis.dao.NumberDAO;
import com.sam.sis.entity.Numbers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginService {

    @Autowired
    NumberDAO numberDAO;
    
   @CrossOrigin
  @RequestMapping(value="/data",method = RequestMethod.GET)
   public @ResponseBody
    List<Numbers> getNumber() {
          Numbers num = new Numbers();
           
       
        List<Numbers> numbers = numberDAO.getAll();
   
        return numbers;
    }
    @CrossOrigin
  @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody verifyObject verify(@RequestParam(value="username")String username,
                            @RequestParam(value="password")String password
            ) {
        if(username.equals("admin")&& password.equals("admin")){
   
            return new verifyObject("true");
        } 
        else{
            return new verifyObject("false");
        }
    }
    public class verifyObject{
        
        String response;

        public verifyObject(String response) {
            this.response = response;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
        
    }
}
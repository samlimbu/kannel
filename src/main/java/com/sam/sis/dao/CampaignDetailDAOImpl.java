/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.dao;

import com.sam.sis.entity.CampaignDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sam
 */

@Repository
@Transactional
public class CampaignDetailDAOImpl extends GenericDAOImpl<CampaignDetail> implements CampaignDetailDAO<CampaignDetail>{
    
}

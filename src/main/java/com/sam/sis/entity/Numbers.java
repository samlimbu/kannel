/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Sam
 */
@Entity
@Table(name = "numbers")
@NamedQueries({
    @NamedQuery(name = "Numbers.findAll", query = "SELECT n FROM Numbers n")})
public class Numbers implements Serializable {

    @Column(name = "sendstatus", updatable = false)
    private Integer sendstatus;
    @Column(name = "delivery_response")
    private Integer deliveryResponse;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mobile_number",updatable = false)
    private String mobileNumber;
    @Size(max = 128)
    @Column(name = "sms_text", updatable = false)
    private String smsText;
    @Basic(optional = false)

    @Column(name = "sent_date_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDateTime;
    @Basic(optional = false)

    @Column(name = "delivery_datetime")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDatetime;
    @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    
    
    @ManyToOne
    private CampaignDetail campaignId;

    public Numbers() {
    }

    public Numbers(Integer id) {
        this.id = id;
    }

    public Numbers(Integer id, Date sentDateTime, Date deliveryDatetime) {
        this.id = id;
        this.sentDateTime = sentDateTime;
        this.deliveryDatetime = deliveryDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public Date getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(Date sentDateTime) {
        this.sentDateTime = sentDateTime;
    }


    public Date getDeliveryDatetime() {
        
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(Date deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }

    public Integer getDeliveryResponse() {
        return deliveryResponse;
    }

    public void setDeliveryResponse(Integer deliveryResponse) {
        this.deliveryResponse = deliveryResponse;
    }

    public CampaignDetail getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(CampaignDetail campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Numbers)) {
            return false;
        }
        Numbers other = (Numbers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{id:" + id +","+"campaignName:" + campaignId.getCampaignName() +","+  "}";
    }

    public Integer getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(Integer sendstatus) {
        this.sendstatus = sendstatus;
    }


    
}

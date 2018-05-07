/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sam
 */
@Entity
@Table(name = "campaign_details")
@NamedQueries({
    @NamedQuery(name = "CampaignDetail.findAll", query = "SELECT c FROM CampaignDetail c")})
public class CampaignDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "campaign_name", updatable=false)
    private String campaignName;
    @Basic(optional = false)

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
  
    @Column(name = "schedule_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleDate;
    @Size(max = 128)
    @Column(name = "SMS", updatable=false)
    private String sms;
    @Column(name = "status")
    private Integer status;
    @Column(name = "deleted")
    private Boolean deleted;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "campaignDetail")
    private Action action;
    
    @JsonIgnore
    @OneToMany(mappedBy = "campaignId")
    private List<Numbers> numbersList;

    public CampaignDetail() {
    }

    public CampaignDetail(Integer id) {
        this.id = id;
    }

    public CampaignDetail(Integer id, Date createdDate, Date scheduleDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.scheduleDate = scheduleDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Numbers> getNumbersList() {
        return numbersList;
    }

    public void setNumbersList(List<Numbers> numbersList) {
        this.numbersList = numbersList;
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
        if (!(object instanceof CampaignDetail)) {
            return false;
        }
        CampaignDetail other = (CampaignDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sam.sis.entity.CampaignDetail[ id=" + id + " ]";
    }
    
}

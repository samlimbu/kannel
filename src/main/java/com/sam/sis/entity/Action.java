/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Sam
 */
@Entity
@Table(name = "campaign_action")
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a")})
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "campaign_id")
    private Integer campaignId;
    @Column(name = "action")
    private Boolean action;
    @JoinColumn(name = "campaign_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CampaignDetail campaignDetail;

    public Action() {
    }

    public Action(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }

    public CampaignDetail getCampaignDetail() {
        return campaignDetail;
    }

    public void setCampaignDetail(CampaignDetail campaignDetail) {
        this.campaignDetail = campaignDetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campaignId != null ? campaignId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.campaignId == null && other.campaignId != null) || (this.campaignId != null && !this.campaignId.equals(other.campaignId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sam.sis.entity.Action[ campaignId=" + campaignId + " ]";
    }
    
}

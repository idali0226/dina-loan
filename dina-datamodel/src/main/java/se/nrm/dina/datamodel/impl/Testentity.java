/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.impl;

import java.math.BigDecimal;
import java.util.List;
import se.nrm.dina.datamodel.BaseEntity; 

/**
 *
 * @author idali
 */
public class Testentity extends BaseEntity {
    
    private int id;
    private Agent createdByAgentID;
    private List<String> tetList;
    private BigDecimal bgDecimal;
    
    public Testentity() {
        
    }
    
    public Testentity(int id) {
        this.id = id;
    }

    @Override
    public String getIdentityString() {
        return String.valueOf(id);
    }

    @Override
    public int getEntityId() {
        return id;
    }

    public int getId() {
        return id;
    } 

    public Agent getCreatedByAgentID() {
        return createdByAgentID;
    }

    public void setCreatedByAgentID(Agent createdByAgentID) {
        this.createdByAgentID = createdByAgentID;
    } 

    public BigDecimal getBgDecimal() {
        return bgDecimal;
    }

    public void setBgDecimal(BigDecimal bgDecimal) {
        this.bgDecimal = bgDecimal;
    }
     

    
    public List<String> getTetList() {
        return tetList;
    }

    public void setTetList(List<String> tetList) {
        this.tetList = tetList;
    }
    
    
}

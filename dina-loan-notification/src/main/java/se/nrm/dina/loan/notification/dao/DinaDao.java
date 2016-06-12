/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.loan.notification.dao;

import java.util.List; 
import se.nrm.dina.datamodel.EntityBean;

/**
 *
 * @author idali
 * @param <T>{@link BaseEntity}
 */
public interface DinaDao<T extends EntityBean> {
  
    /**
     * Find a list of instances from an entity by named query 
     * @return 
     */
    public List getOverdueLoans();
 
}

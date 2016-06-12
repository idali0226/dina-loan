/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.nrm.dina.loan.notification.dao;

import java.io.Serializable;     
import java.util.Date;
import java.util.List;  
import javax.ejb.Stateless;   
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;   
import javax.persistence.PersistenceContext;
import javax.persistence.Query;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;   
import se.nrm.dina.datamodel.*; 

/**
 * CRUD operations to database
 *
 * @author idali
 * @param <T>
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DinaDaoImpl<T extends EntityBean> implements DinaDao<T>, Serializable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @PersistenceContext(unitName = "jpaPU")                  //  persistence unit connect to production database  
    private EntityManager entityManager;
 

    public DinaDaoImpl() {

    }

    public DinaDaoImpl(EntityManager entityManager, Query query) {
        this.entityManager = entityManager; 
    }  
        
  
    @Override
    public List getOverdueLoans() {
        logger.info("getOverdueLoans");
         
        Query query = entityManager.createNamedQuery("Loan.findByOverdueLoan"); 
        query.setParameter("currentDueDate", new Date());
        return query.getResultList(); 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.loan.notification.vo;
 
import java.util.Date;  
import se.nrm.dina.datamodel.impl.Agent;


/**
 *
 * @author idali
 */
public final class OverdueLoan {
     
    private String loanNumber;
    private Date dueDate;
    private Date loanDate;
    private Agent borrower; 
    private Agent secondaryBorrower;
    private Agent preparedBy; 
    
    public OverdueLoan() {
        
    }
    
    public OverdueLoan(String loanNumber, Date dueDate, Date loanDate, Agent borrower, Agent secondaryBorrower, Agent preparedBy) {
        this.loanNumber = loanNumber;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        this.borrower = borrower; 
        this.secondaryBorrower = secondaryBorrower;
        this.preparedBy = preparedBy;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }
     
    public Agent getBorrower() {
        return borrower;
    }

    public Agent getSecondaryBorrower() {
        return secondaryBorrower;
    }

    public Agent getPreparedBy() {
        return preparedBy;
    } 
}

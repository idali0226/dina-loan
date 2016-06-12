/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.loan.notification.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;    
import se.nrm.dina.datamodel.impl.Agent;
import se.nrm.dina.datamodel.impl.Loan;
import se.nrm.dina.datamodel.impl.Loanagent;
import se.nrm.dina.loan.notification.dao.DinaDao;
import se.nrm.dina.loan.notification.email.DinaMail; 
import se.nrm.dina.loan.notification.vo.OverdueLoan;
 

/**
 *
 * @author idali
 */
@Stateless
public class DinaLoan {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @EJB
    private DinaDao dao; 
    
    @Inject
    private DinaMail mail;

    private Agent preparedBy;
    private Agent primaryBorrower;
    private Agent secondaryBorrower;
    private Agent borrower;
  
    private void setAgentsType(Loanagent loanAgent) {
        Agent agent = loanAgent.getAgentID();
        String role = loanAgent.getRole().trim();
        switch (role) {
            case "preparer":
                preparedBy = agent;
                break;
            case "borrower primary": 
                primaryBorrower = agent;
                break;
            case "borrower secondary":
                secondaryBorrower = agent;
                break;
            case "borrower":
                borrower =agent;
                break;
        }

    }

    public void getOverDueLoan() {
        logger.info("getOverDueLoan");

        List<Loan> loans = dao.getOverdueLoans();
        Map<String, List<OverdueLoan>> map = new HashMap<>();

        loans.stream()
                .forEach(l -> {
                    l.getLoanagentList().stream()
                    .forEach(a -> {
                        setAgentsType(a);
                    });

                    if (primaryBorrower == null) {
                        primaryBorrower = secondaryBorrower == null ? borrower == null ? null : borrower : secondaryBorrower;
                        secondaryBorrower = null;
                    }

                    if (preparedBy != null) {
                        OverdueLoan voerDueLoan = new OverdueLoan(l.getLoanNumber(), l.getCurrentDueDate(), l.getLoanDate(), primaryBorrower, secondaryBorrower, preparedBy);
                        List<OverdueLoan> list = new ArrayList<>();
                         
                        if(preparedBy.getEmail() != null) {
                            if (map.containsKey(preparedBy.getEmail())) {
                                list = map.get(preparedBy.getEmail()); 
                            } 
                            list.add(voerDueLoan);
                            map.put(preparedBy.getEmail(), list); 
                        }
                        
                    }
                });  
        mail.sendMail(map); 
    } 
}

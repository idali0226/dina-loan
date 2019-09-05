/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.loan.notification.logic;
 
import java.util.ArrayList;
import java.util.Date;
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
    private static final String PREPARED = "preparer";
    private static final String PRIMARY_BORROWER = "borrower primary";
    private static final String SECONDARY_BORROWER = "borrower secondary";
    private static final String BORROWER = "borrower";

    @EJB
    private DinaDao dao;

    @Inject
    private DinaMail mail;
 
    public void getOverDueLoan() {
        logger.info("getOverDueLoan");

        List<Loan> loans = dao.getOverdueLoans();
        Map<String, List<OverdueLoan>> map = new HashMap<>();

        loans.stream()
                .forEach(loan -> {
                    Agent preparedAgent;
                    Agent primary = null;
                    Agent secondary = null;

                    List<Loanagent> loanAgents = loan.getLoanagentList();

                    Loanagent prepared = loanAgents.stream()
                            .filter(a -> a.getRole().equals(PREPARED))
                            .findFirst().orElse(null);

                    Loanagent primaryBorrower = loanAgents.stream()
                            .filter(a -> a.getRole().equals(PRIMARY_BORROWER))
                            .findFirst().orElse(null);

                    Loanagent secondaryBorrower = loanAgents.stream()
                            .filter(a -> a.getRole().equals(SECONDARY_BORROWER))
                            .findFirst().orElse(null);

                    Loanagent borrower = loanAgents.stream()
                            .filter(a -> a.getRole().equals(BORROWER))
                            .findFirst().orElse(null);

                    if (primaryBorrower != null) {
                        primary = primaryBorrower.getAgentID();
                        if (secondaryBorrower != null) {
                            secondary = secondaryBorrower.getAgentID();
                        } else if (borrower != null) {
                            secondary = borrower.getAgentID();
                        }
                    } else if (secondaryBorrower != null) {
                        primary = secondaryBorrower.getAgentID();
                    } else if (borrower != null) {
                        primary = borrower.getAgentID();
                    }

                    if (prepared != null) { 
                        String loanNumber = loan.getLoanNumber();
                        Date currentDueDate = loan.getCurrentDueDate();
                        Date loanDate = loan.getLoanDate();
                        preparedAgent = prepared.getAgentID();
                        OverdueLoan voerDueLoan = new OverdueLoan(loanNumber, 
                                currentDueDate, loanDate, primary, secondary, preparedAgent);
                        List<OverdueLoan> list = new ArrayList<>();
                        String email = preparedAgent.getEmail();
                        if (email != null) {
                            if (map.containsKey(email)) {
                                list = map.get(email);
                            }
                            list.add(voerDueLoan);
                            map.put(email, list);
                        } 
                    }
                }); 
        mail.sendMail(map);
    }
}

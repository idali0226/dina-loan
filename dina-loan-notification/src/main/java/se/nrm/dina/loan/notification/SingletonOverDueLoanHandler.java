package se.nrm.dina.loan.notification;
  
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton; 
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import se.nrm.dina.loan.notification.logic.DinaLoan;

/**
 *
 * @author idali
 */
//@Startup                            // initialize ejb at deployment time
@Singleton
@LocalBean
public class SingletonOverDueLoanHandler {
    
    private final Logger logger = LoggerFactory.getLogger(SingletonOverDueLoanHandler.class.getName());
    
    @Inject
    private DinaLoan loan;
    
    public SingletonOverDueLoanHandler() { 
    }
    
    /**
     * Start ejb every time deploy into application server or application server start
     */
//    @PostConstruct
//    void init() {
//       getOverDueLoans();
//    }
     
//    @Schedule(dayOfMonth = "1", hour = "2")                 // Schedule first day of month at 2:00 am
    @Schedule(hour = "12", minute = "20")
    public void getOverDueLoans() {
        logger.info("getOverDueLoans");
        
        loan.getOverDueLoan();  
    }
}

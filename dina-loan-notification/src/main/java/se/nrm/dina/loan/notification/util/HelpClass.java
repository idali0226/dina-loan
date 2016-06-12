/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.loan.notification.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author idali
 */
public class HelpClass {
     private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     
    
    private static HelpClass instance = null; 

    public static synchronized HelpClass getInstance() {
        if (instance == null) {
            instance = new HelpClass();
        }
        return instance;
    }
    
    public java.sql.Date getTodaysDate() {
        LocalDate today = LocalDate.now();
        
        return java.sql.Date.valueOf(today);
    }


    /**
     * Convert Date to String with "yyyy-MM-dd" format
     *
     * @param date - Date
     * @return String
     */
    public String dateToString(Date date) {
         
         
        if (date == null) {
            return null;
        } else {
            return df.format(date);
        }
    } 
}

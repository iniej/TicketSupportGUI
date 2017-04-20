package com.Iniebiyo;

import java.util.Date;

/**
 * Created by Iniebiyo Joshua on 4/19/2017.
 */
public class ResolvedTickets extends Tickets{

    private static  int ticketIdCounter = 1;
    public void setTicketID(int ticketID) {
        TicketID = ticketID;

    }
    private String res;

    private int TicketID;

    public String getRes() {
        return res;
    }


    public ResolvedTickets(String desc, String rep,String p, String resol, Date date) {
        super(desc, rep, p, date);
        this.res = resol;
        this.ticketID = ticketIdCounter;
    }

    @Override
    public String toString() {
        return ("ID : " + this.ticketID + ",   Issue : "+ this.description +
                ",    Priority : " + this.priority +  ",  Reported by :  "+ this.reporter +
                ",     Resolution for :     "+ res+ ",      Reported on    " +
                this.date);
    }
}



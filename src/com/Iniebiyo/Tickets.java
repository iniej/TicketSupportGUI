package com.Iniebiyo;

import java.util.Date;

/**
 * Created by Iniebiyo Joshua on 4/19/2017.
 */
public class Tickets {

        String description;
        String reporter ;
        String  priority;
        Date date;

        private static int ticketIdCounter = 1;


        protected  int ticketID;
        public Tickets(String desc, String rep, String  p, Date date){
            this.description = desc;
            this.reporter = rep;
            this.date = date;
            this.priority = p;
            this.ticketID = ticketIdCounter;
            ticketIdCounter ++;

        }

        public String getDescription() {
            return description;
        }

        public String getReporter() {
            return reporter;
        }

        public String  getPriority() {
            return priority;
        }

        public Date getDate() {
            return date;
        }
        public int getTicketID(){return ticketID;}

        @Override
        public String toString() {
            return ("ID : " + this.ticketID + ",   Issue : "+ this.description +
                    ",    Priority : " + this.priority +  ",  Reported by "+ this.reporter + ",      Reported on    " +
                    this.date);
        }
    }



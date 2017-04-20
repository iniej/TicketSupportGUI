package com.Iniebiyo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;

import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.showInputDialog;

/**
 * Created by Iniebiyo Joshua on 4/19/2017.
 */
public class TicketSupportGUI extends JFrame{

    private JTextField descriptiontextField;
    private JTextField reporterTextField;
    private JList ReadSavedTickets;
    private JList OpenTickets;
    private JButton openSavedTicketsButton;
    private JButton addNewTicketButton;
    private JList ResolvedTickets;
    private JButton resolveATicketButton;
    private JButton saveAndQuitButton;
    private JButton searchButton;
    private JComboBox severitycomboBox;
    private JPanel rootPanel;


    static DefaultListModel<Tickets> openticketListModel;
    static  DefaultListModel<Tickets>resolvedTicketListModel;
    static LinkedList<Tickets> ticketQueue = new LinkedList<Tickets>();
    static LinkedList<ResolvedTickets> resTicketQue = new LinkedList<ResolvedTickets>();



    TicketSupportGUI(){
        //setTitle("TicketConstr Manager Programm !!!");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        severityLists();
        openticketListModel = new DefaultListModel<>();
        OpenTickets.setModel(openticketListModel);
        resolvedTicketListModel = new DefaultListModel<>();
        ResolvedTickets.setModel(resolvedTicketListModel);




        addNewTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String des = descriptiontextField.getText();
                String rep = reporterTextField.getText();
                if(des.length() == 0){
                    JOptionPane.showMessageDialog(TicketSupportGUI.this, "Please type  description here ");
                    return;

                }
                if(rep.length() == 0){
                    showMessageDialog(TicketSupportGUI.this, "Please, type reporter here ");
                    return;
                }

                String priority =(String ) severitycomboBox.getSelectedItem();


                Date date = new Date();
                Tickets t = new Tickets(des, rep, priority, date);
                openticketListModel.addElement(t);
                ticketQueue.add(t);

                descriptiontextField.setText("");
                reporterTextField.setText("");

            }
        });
        resolveATicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( OpenTickets.isSelectionEmpty()){
                    showMessageDialog(TicketSupportGUI.this,"Lists are empty !!!" );

                }
                else {

                    Tickets tickets = (Tickets) OpenTickets.getSelectedValue();
                    String resolution = showInputDialog("Please enter a resolution ");
                    Date date = new Date();
                    String problem = tickets.getDescription();
                    String rep = tickets.getReporter();
                    String p = tickets.getPriority();

                    ResolvedTickets reslT = new ResolvedTickets(problem, rep, p, resolution, date );
                    resolvedTicketListModel.addElement(reslT);
                    openticketListModel.removeElement(tickets);
                    resTicketQue.add(reslT);

                }
            }
        });
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int quit = showConfirmDialog(TicketSupportGUI.this,
                        "Are you sure you want to quit ? ",
                        "Quit ", OK_CANCEL_OPTION);
                if(quit == OK_OPTION){
                    for(ResolvedTickets t : resTicketQue){
                        try{
                            BufferedWriter bf = new BufferedWriter
                                    (new FileWriter("allFiles.txt", true));
                            bf.append(String.valueOf(t)+ "\n");

                            bf.close();

                        }catch (IOException i){
                            System.out.println("There is an error reading from this file");
                            i.printStackTrace();
                        }
                    }
                    System.exit(0);
                }
            }
        });
        openSavedTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel listModel = new DefaultListModel();
                try{
                    BufferedReader reader = new BufferedReader
                            (new FileReader("allFiles.txt"));
                    String line = reader.readLine();
                    while(line != null){
                        ReadSavedTickets.setModel(listModel);
                        listModel.addElement(line);

                        line = reader.readLine();
                    }
                    reader.close();
                }catch (IOException io){
                    System.out.println("File reading input error!!!!");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel listModel = new DefaultListModel();
                String problem = (showInputDialog(
                        TicketSupportGUI.this, "Please, type " +
                                "" +
                                " the issuer here :  "));

                try {
                    BufferedReader reader = new BufferedReader
                            (new FileReader("allFiles.txt"));
                    String line = reader.readLine();
                    while (line != null) {
                        String item [] = new String[line.length()];
                        for (int i = 0; i < item.length; i++) {
                            for(ResolvedTickets t : resTicketQue){
                                if(problem.contains(t.getDescription())){
                                    System.out.println(t);
                                }
                            }

                        }

                        //System.out.println(line);
                        line = reader.readLine();
                    }
                    reader.close();
                } catch (IOException io) {
                    System.out.println("File reading input error!!!!");
                }

            }

        });

    }

    private void severityLists() {

        for (int x = 1; x <= 5; x++){
            if(x == 1){
                severitycomboBox.addItem(x + " severity ");

            }
            else{
                severitycomboBox.addItem(x + " severities ");
            }

        }
    }


}



package dvdrentalsclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *DvdRentalsClient.java
 * This is our DVD Rentals Client Server Program
 * @author Lindokuhle Nini (218196504)
 * @author Sihle Jijana (216273919)
 * Date: 08 November 2020
 */
public class RentalGUI extends JFrame implements ActionListener {

    private JPanel panelNorth, panelSouth, panelCenter1, panelCenter2, panelCenter3, panelEast, panelWest;
    private JLabel lblHeading;
    private ImageIcon icon;
    private JButton btnViewAllRentals, btnOutstandingRentals, btnDailyRentals;
    private JLabel lblTitle, lblEmptySpace, lblEmptySpace2;
    private ButtonGroup btnGroup1, btnGroup2;
    private JPanel rButtons1, buttons2;
    private JTable tblShowAllRentals, tblShowOutstandingRentals, tblDayRentals;

    private JButton btnCustomers, btnDvd, btnMovies, btnRentals, btnExit;
    private Font ft1, ft2, ft3;

    public RentalGUI() {

        super("DVD Rentals App version 1.0");
        panelNorth = new JPanel();
        panelSouth = new JPanel();
        panelCenter1 = new JPanel();
        panelCenter2 = new JPanel();
        panelCenter3 = new JPanel();
        panelEast = new JPanel();
        panelWest = new JPanel();

        icon = new ImageIcon("rentalMachine-icon.png");
        lblHeading = new JLabel("DVD Rentals", icon, JLabel.CENTER);

        lblTitle = new JLabel("All rentals", JLabel.CENTER);
        lblEmptySpace = new JLabel(" ", JLabel.RIGHT);

        lblEmptySpace2 = new JLabel(" ", JLabel.RIGHT);
        buttons2 = new JPanel();

        tblShowAllRentals = new JTable();
        tblShowOutstandingRentals = new JTable();
        tblDayRentals = new JTable();

        btnViewAllRentals = new JButton("View all rentals");
        btnOutstandingRentals = new JButton("Outstanding rentals");
        btnDailyRentals = new JButton("Daily rentals");

        btnCustomers = new JButton("Customers");
        btnDvd = new JButton("DVDs");
        btnRentals = new JButton("Rentals");
        btnExit = new JButton("Exit");

        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.PLAIN, 16);
        ft3 = new Font("Arial", Font.PLAIN, 24);
    }

    public void setRentalGUI() {
        panelNorth.setLayout(new FlowLayout());
        panelSouth.setLayout(new GridLayout(1, 3));
        panelEast.setLayout(new GridLayout(1, 4));
        panelWest.setLayout(new GridLayout(4, 1, 0, 8));
        panelWest.setBorder(new EmptyBorder(50, 20, 285, 0));
        panelCenter1.setBorder(new EmptyBorder(50, 0, 120, 500));

        panelNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setPreferredSize(new Dimension(500, 150));
        lblHeading.setForeground(Color.yellow);
        panelNorth.setBackground(new Color(0, 106, 255));

        panelCenter1.setBackground(new Color(36, 145, 255));
        panelWest.setBackground(new Color(36, 145, 255));

        btnCustomers.setFont(ft3);
        btnDvd.setFont(ft3);
        btnRentals.setFont(ft3);
        btnRentals.setBackground(Color.ORANGE);
        btnExit.setFont(ft3);
        panelSouth.add(btnCustomers);
        panelSouth.add(btnDvd);
        panelSouth.add(btnRentals);
        panelSouth.add(btnExit);

        btnViewAllRentals.setFont(ft2);
        btnOutstandingRentals.setFont(ft2);
        btnDailyRentals.setFont(ft2);
        panelWest.add(btnViewAllRentals);
        panelWest.add(btnOutstandingRentals);
        panelWest.add(btnDailyRentals);

        lblTitle.setFont(ft3);
        lblTitle.setForeground(Color.BLACK);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelEast, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        btnViewAllRentals.setBackground(Color.ORANGE);

        btnCustomers.addActionListener(this);
        btnDvd.addActionListener(this);
        btnRentals.addActionListener(this);
        btnExit.addActionListener(this);
        btnViewAllRentals.addActionListener(this);
        btnOutstandingRentals.addActionListener(this);
        btnDailyRentals.addActionListener(this);

        this.showAllRentals();
        this.pack();
        this.setVisible(true);
    }

    public void showAllRentals() {

        panelCenter1.setBackground(new Color(36, 145, 255));

        //Set table title
        panelCenter1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "All Rentals",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        String[] columnNames = {"rentalNumber", "dateRented", "dateReturned", "customerNumber", "dvdNumber", "totalPenaltyCost"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // The 0 argument is number rows.
        Vector vect = new Vector();
        ArrayList<Vector> arrVec;

        try {

            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

            output.writeObject("displayallrentals");

            arrVec = (ArrayList<Vector>) input.readObject();

            for (int i = 0; i < arrVec.size(); i++) {
                vect = arrVec.get(i);
                tableModel.addRow(vect);
            }

            tblShowAllRentals = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(tblShowAllRentals);
            panelCenter1.add(sp);

            //Frame Size 
            sp.setSize(1000, 700);
            sp.setPreferredSize(new Dimension(1000, 500));
            this.add(panelCenter1, BorderLayout.CENTER);

            output.close();
            input.close();
            s1.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

    }

    public void showOustandingRentals() {

        panelCenter2.setBackground(new Color(36, 145, 255));

        //Set table title
        panelCenter2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "All Rentals",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        String[] columnNames = {"rentalNumber", "dateRented", "dateReturned", "customerNumber", "dvdNumber", "totalPenaltyCost"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // The 0 argument is number rows.
        Vector vect = new Vector();
        ArrayList<Vector> arrVec;

        try {

            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

            output.writeObject("unreturnedmovies");

            arrVec = (ArrayList<Vector>) input.readObject();

            for (int i = 0; i < arrVec.size(); i++) {
                vect = arrVec.get(i);
                tableModel.addRow(vect);
            }

            tblShowOutstandingRentals = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(tblShowOutstandingRentals);
            panelCenter2.add(sp);

            //Frame Size 
            sp.setSize(1000, 700);
            sp.setPreferredSize(new Dimension(1000, 500));
            this.add(panelCenter2, BorderLayout.CENTER);

            output.close();
            input.close();
            s1.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

    }

    public void dayRentals() {

        panelCenter3.setBackground(new Color(36, 145, 255));

        //Set table title
        panelCenter3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Rentals for 07 Novermber 2020",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        String[] columnNames = {"rentalNumber", "dateRented", "dateReturned", "customerNumber", "dvdNumber", "totalPenaltyCost"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // The 0 argument is number rows.
        Vector vect = new Vector();
        ArrayList<Vector> arrVec;

        try {

            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

            output.writeObject("dayrentals");

            arrVec = (ArrayList<Vector>) input.readObject();

            for (int i = 0; i < arrVec.size(); i++) {
                vect = arrVec.get(i);
                tableModel.addRow(vect);
            }

            tblDayRentals = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(tblDayRentals);
            panelCenter3.add(sp);

            //Frame Size 
            sp.setSize(1000, 700);
            sp.setPreferredSize(new Dimension(1000, 500));
            this.add(panelCenter3, BorderLayout.CENTER);

            output.close();
            input.close();
            s1.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Customers")) {
            new CustomerGUI().setGUI();
            this.setVisible(false);
        } else if (e.getActionCommand().equals("DVDs")) {
            new DvdGUI().setDvdGUI();
            this.setVisible(false);

        } 
        else if (e.getActionCommand().equals("Exit")){
           System.exit(0);
           
       }
        else if (e.getActionCommand().equals("View all rentals")) {
            panelCenter2.removeAll();
            panelCenter1.repaint();
            panelCenter1.setVisible(true);
            panelCenter2.setVisible(false);
            panelCenter3.setVisible(false);
            this.showAllRentals();
            btnViewAllRentals.setBackground(Color.ORANGE);
            btnDailyRentals.setBackground(UIManager.getColor("Button.background"));
            btnOutstandingRentals.setBackground(UIManager.getColor("Button.background"));
        } else if (e.getActionCommand().equals("Outstanding rentals")) {
            panelCenter1.removeAll();
            panelCenter2.repaint();
            panelCenter1.setVisible(false);
            panelCenter2.setVisible(true);
            panelCenter3.setVisible(false);
            showOustandingRentals();
            btnViewAllRentals.setBackground(UIManager.getColor("Button.background"));
            btnDailyRentals.setBackground(UIManager.getColor("Button.background"));
            btnOutstandingRentals.setBackground(Color.ORANGE);
        } else if (e.getActionCommand().equals("Daily rentals")) {
            panelCenter1.removeAll();
            panelCenter2.removeAll();
            panelCenter2.repaint();
            panelCenter1.setVisible(false);
            panelCenter2.setVisible(false);
            panelCenter3.setVisible(true);
            dayRentals();
            btnDailyRentals.setBackground(Color.ORANGE);
            btnViewAllRentals.setBackground(UIManager.getColor("Button.background"));
            btnOutstandingRentals.setBackground(UIManager.getColor("Button.background"));
        }

    }

}

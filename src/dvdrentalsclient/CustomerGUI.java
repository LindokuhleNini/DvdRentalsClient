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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import modelclasses.Customer;


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
public class CustomerGUI extends JFrame implements ActionListener{
    
    private JPanel panelNorth, panelSouth, panelCenter, panelEast, panelWest, panelCenter2;
    private JLabel lblHeading;
    private ImageIcon icon;
    private JButton btnNewCustomer, btnEditCustomer, btnDeleteCustomer, btnShowAllCustomers;
    private JButton btnSave, btnReset;
    private JLabel lblCustNumber, lblFirstName, lblSurname, lblPhoneNumber, lblCredit, lblCanRent, lblTitle, lblTitle2, lblEmptySpace, lblEmptySpace2;
    private JTextField txtCustNumber, txtFirstName, txtSurname, txtPhoneNumber, txtCredit;
    private JRadioButton radioBtnYes1,radioBtnNo1;
    private ButtonGroup btnGroup1;
    private JPanel rButtons1, buttons2;
    private JTable tblShowAllCustomers;
    
    private JButton btnCustomers, btnDvd, btnRentals, btnExit;
    private Font ft1, ft2, ft3;
    
    public CustomerGUI(){
        
        super("DVD Rentals App version 1.0");
        //North Panel
        panelNorth = new JPanel();
        icon = new ImageIcon("rentalMachine-icon.png");
        lblHeading = new JLabel("DVD Rentals", icon, JLabel.CENTER);
        
        //Center Panel(New Customer
        panelCenter = new JPanel();
        
        //Center panel 2 (Customer table)
        panelCenter2 = new JPanel();
        lblTitle2 = new JLabel("All customers", JLabel.CENTER);
        tblShowAllCustomers = new JTable();
        
        //West Panel
        panelWest = new JPanel();
        
        lblTitle = new JLabel("New Customer", JLabel.CENTER);
        lblEmptySpace = new JLabel(" ", JLabel.RIGHT);
        
        lblCustNumber = new JLabel("Customer number: ", JLabel.RIGHT);
        txtCustNumber = new JTextField();
        
        lblFirstName = new JLabel("First name: ", JLabel.RIGHT);
        txtFirstName = new JTextField();
        
        lblSurname = new JLabel("Surname: ", JLabel.RIGHT);
        txtSurname = new JTextField();
        
        lblPhoneNumber = new JLabel("Phone number: ", JLabel.RIGHT);
        txtPhoneNumber = new JTextField();
        
        lblCredit = new JLabel("Credit: ", JLabel.RIGHT);
        txtCredit = new JTextField();
        
        lblCanRent = new JLabel("Can customer rent: ", JLabel.RIGHT);
        radioBtnYes1 = new JRadioButton("Yes"+" ", true);
        radioBtnNo1 = new JRadioButton("No", false);
        btnGroup1 = new ButtonGroup();
        rButtons1 = new JPanel();
        
        lblEmptySpace2 = new JLabel(" ", JLabel.RIGHT);
        buttons2 = new JPanel();
        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");
        
        btnNewCustomer = new JButton("New Customer");
        btnEditCustomer = new JButton("Edit Customer");
        btnDeleteCustomer = new JButton("Delete Customer");
        btnShowAllCustomers = new JButton("Show All Customers");
        
        //East Panel
        panelEast = new JPanel();
        
        //South Panel
        panelSouth = new JPanel();
        btnCustomers = new JButton("Customers");
        btnDvd = new JButton("DVDs");
        btnRentals = new JButton("Rentals");
        btnExit = new JButton("Exit");
        
        //fonts
        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.PLAIN, 16);
        ft3 = new Font("Arial", Font.PLAIN, 24);
    }
    
    public void setGUI(){
        
        //North Panel
        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setPreferredSize(new Dimension(500,150));
        lblHeading.setForeground(Color.yellow);
        panelNorth.setBackground(new Color(0, 106, 255));
        
        //Center Panel (Create new customer
        panelCenter.setLayout(new GridLayout(10, 2));
        panelCenter.setBorder(new EmptyBorder(50, 0, 120, 500));
        panelCenter.setBackground(new Color(36, 145, 255));
        
        lblTitle.setFont(ft3);
        lblTitle.setForeground(Color.BLACK);
        
        lblCustNumber.setFont(ft2);
        lblCustNumber.setForeground(Color.BLACK);
        txtCustNumber.setFont(ft2); 
        
        lblFirstName.setFont(ft2);
        lblFirstName.setForeground(Color.BLACK);
        txtFirstName.setFont(ft2);  
        
        lblSurname.setFont(ft2);
        lblSurname.setForeground(Color.BLACK);
        txtSurname.setFont(ft2);
        
        lblPhoneNumber.setFont(ft2);
        lblPhoneNumber.setForeground(Color.BLACK);
        txtPhoneNumber.setFont(ft2);
        
        lblCredit.setFont(ft2);
        lblCredit.setForeground(Color.BLACK);
        txtCredit.setFont(ft2);
        
        lblCanRent.setFont(ft2);
        lblCanRent.setForeground(Color.BLACK);
        
        panelCenter.add(lblEmptySpace);
        panelCenter.add(lblTitle);
        
        panelCenter.add(lblCustNumber);
        panelCenter.add(txtCustNumber);
        
        panelCenter.add(lblFirstName);
        panelCenter.add(txtFirstName);
        
        panelCenter.add(lblSurname);
        panelCenter.add(txtSurname);
        
        panelCenter.add(lblPhoneNumber);
        panelCenter.add(txtPhoneNumber);
        
        panelCenter.add(lblCredit);
        panelCenter.add(txtCredit);
        
        rButtons1.setBackground(new java.awt.Color(36, 145, 255));
        radioBtnYes1.setBackground(new java.awt.Color(36, 145, 255));
        radioBtnNo1.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter.add(lblCanRent);
        btnGroup1.add(radioBtnYes1);
        btnGroup1.add(radioBtnNo1);
        rButtons1.add(radioBtnYes1);
        rButtons1.add(radioBtnNo1);
        rButtons1.add(Box.createHorizontalStrut(20));
        radioBtnYes1.add(Box.createRigidArea(new Dimension(100,30)));
        panelCenter.add(rButtons1, new BorderLayout(10, 0));
        btnNewCustomer.setBackground(Color.ORANGE);
        
        buttons2.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter.add(lblEmptySpace2);
        buttons2.add(btnSave);
        buttons2.add(btnReset);
        buttons2.add(Box.createHorizontalStrut(20));
        btnSave.add(Box.createRigidArea(new Dimension(50,30)));
        btnReset.add(Box.createRigidArea(new Dimension(50,30)));
        panelCenter.add(buttons2, new BorderLayout(10, 0));
             
        //West Panel
        panelWest.setLayout(new GridLayout(4,1, 0, 8));
        panelWest.setBorder(new EmptyBorder(50, 20, 285, 0));
        panelWest.setBackground(new Color(36, 145, 255));
        btnNewCustomer.setFont(ft2);
        btnEditCustomer.setFont(ft2);
        btnDeleteCustomer.setFont(ft2);
        btnShowAllCustomers.setFont(ft2);
        panelWest.add(btnNewCustomer);
//        panelWest.add(btnEditCustomer);
//        panelWest.add(btnDeleteCustomer);
        panelWest.add(btnShowAllCustomers);
        btnNewCustomer.addActionListener(this);
        btnEditCustomer.addActionListener(this);
        btnDeleteCustomer.addActionListener(this);
        btnShowAllCustomers.addActionListener(this);
        btnSave.addActionListener(this);
        
        //East Panel
        panelEast.setLayout(new GridLayout(1,4));
        
        //South Panel
        panelSouth.setLayout(new GridLayout(1, 3));
        btnCustomers.setFont(ft3);
        btnCustomers.setBackground(Color.ORANGE);
        btnDvd.setFont(ft3);
        btnRentals.setFont(ft3);
        btnExit.setFont(ft3);
        panelSouth.add(btnCustomers);
        panelSouth.add(btnDvd);
        panelSouth.add(btnRentals);
        panelSouth.add(btnExit);
        btnCustomers.addActionListener(this);
        btnDvd.addActionListener(this);
        btnRentals.addActionListener(this);
        btnExit.addActionListener(this);
        
        //Add panels to JFrame
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelEast, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //Make all components visible on JFrame
        this.pack();
        this.setVisible(true);
        //btnCustomers.doClick();
        //this.setSize(600, 600);
    }
    
    public void showCustomers(){
        
         lblTitle2.setFont(ft3);
        lblTitle2.setForeground(Color.BLACK);
        panelCenter2.setBackground(new Color(36, 145, 255));
        
        //Set table title
        panelCenter2.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                                                            "All customers",
                                                            TitledBorder.CENTER,
                                                            TitledBorder.TOP));
        
        String[] columnNames = { "custNumber", "firstName", "surname", "phoneNum", "credit", "canRent" };
                 
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // The 0 argument is number rows.
        Vector vect = new Vector();
        ArrayList<Vector> arrVec;

        try {

            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");
            
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

            output.writeObject("displaycustomers");
            			
	     arrVec = (ArrayList<Vector>) input.readObject();

            for (int i = 0; i < arrVec.size(); i++) {
                vect = arrVec.get(i);
                //System.out.println(vect+"\n");
                tableModel.addRow(vect);
                //System.out.println(tableModel);
            }
       
            tblShowAllCustomers = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(tblShowAllCustomers);
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
    
    public void resetForm(){
        
        txtCustNumber.setText("");
        txtFirstName.setText("");
        txtSurname.setText("");
        txtPhoneNumber.setText("");
        txtCredit.setText("");
        btnGroup1.clearSelection();
        radioBtnYes1.setSelected(true);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Customer")){
           this.setVisible(true);
        }
        else if (e.getActionCommand().equals("New Customer")){
           panelCenter.setVisible(true);
           panelCenter2.setVisible(false);
           btnNewCustomer.setBackground(Color.ORANGE);
           btnShowAllCustomers.setBackground(UIManager.getColor("Button.background"));
           }
           
        else if (e.getActionCommand().equals("Save")){
            
               if(txtCustNumber.getText().length()!=0 && txtFirstName.getText().length()!=0 
               && txtSurname.getText().length()!=0 && txtPhoneNumber.getText().length()!=0 && txtCredit.getText().length()!=0)
                {
 
                             boolean canRent = false;
                             
                            if (radioBtnYes1.isSelected()){
                               canRent = true;
                            }
                            else if (radioBtnNo1.isSelected()){
                               canRent = false;
                            }
                        
                    int custNum = Integer.parseInt(txtCustNumber.getText());
                    String name = txtFirstName.getText();
                    String surname = txtSurname.getText();
                    String phoneNum = txtPhoneNumber.getText();
                    Double credit = Double.parseDouble(txtCredit.getText());
                    Boolean cRent = Boolean.parseBoolean(""+canRent);
                            
                    Customer c = new Customer(custNum, name, surname, phoneNum, credit, cRent);
                    
                    DvdRentalsClient drClient = new DvdRentalsClient();
                    drClient.customerClient(c);   
                    JOptionPane.showMessageDialog(this, "Customer added successfully");
                    this.resetForm();
                       
               }      
                    
      }
         else if (e.getActionCommand().equals("Reset")){
             this.resetForm();
         }
           
           
        else if (e.getActionCommand().equals("DVDs")){
           new DvdGUI().setDvdGUI();
           this.setVisible(false);
           
       }
        else if (e.getActionCommand().equals("Rentals")){
           new RentalGUI().setRentalGUI();
           this.setVisible(false);
           
       }
        else if (e.getActionCommand().equals("Exit")){
           System.exit(0);
           
       }
         
        else if (e.getActionCommand().equals("Show All Customers")){
           showCustomers();
           panelCenter.setVisible(false);
           panelCenter2.setVisible(true);
           btnNewCustomer.setBackground(UIManager.getColor("Button.background"));
           btnShowAllCustomers.setBackground(Color.ORANGE);
       }
        
        
    }
    
  
    }

    



    


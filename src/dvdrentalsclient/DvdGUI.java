package dvdrentalsclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelclasses.Customer;
import modelclasses.DVD;
import modelclasses.Rental;

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
public class DvdGUI extends JFrame implements ActionListener {

    private JPanel panelNorth, panelSouth, panelCenter, panelEast, panelWest, panelCenter2, panelCenter3, panelCenter4;
    private JLabel lblHeading;
    private ImageIcon icon;
    private JButton btnCustomers, btnDvd, btnRentals, btnExit;
    private JButton btnNewDvd, btnRentDvd, btnReturnDvd, btnMovies;

    private JButton btnSave, btnReset;
    private JLabel lblDvdNumber, lblDvdTitle, lblCategory, lblRentalNumber, lblNewRelease, lblAvailableForRent, lblTitle, lblEmptySpace,
            lblEmptySpace2, lblEmptySpace3, lblEmptySpace4, lblEmptySpace5, lblEmptySpace6, lblEmptySpace7;
    private JTextField txtDvdNumber, txtDvdTitle, txtRentalNum;
    private JRadioButton radioBtnYes1, radioBtnNo1;
    private JRadioButton radioBtnYes2, radioBtnNo2;
    private JComboBox cbCategory;
    private ButtonGroup btnGroup1, btnGroup2;
    private JPanel rButtons1, rButtons2, buttons2, buttons3, buttons4;
    private JTable tblShowAllDvds;
    private JComboBox cboxCustomer;

    private JLabel lblExistingCustomer, lblSelectMovieCategory, lblTitle2, lblAvailableMovies, lblTitle3, lblReturnDvd;
    private JTextField txtRentalNumber;
    private JComboBox cbCategory2, cbAvailableMovies;
    private JButton btnRent, btnReset2, btnReturn;
    private JComboBox dvdCbox;
    private JTextField txtSearchView;
    private JLabel lblSearchView;
    private JComboBox cbExistingCustomer;

    private Font ft1, ft2, ft3;

    public DvdGUI() {

        super("DVD Rentals App version 1.0");
        panelNorth = new JPanel();
        panelSouth = new JPanel();
        panelCenter = new JPanel();
        panelEast = new JPanel();
        panelWest = new JPanel();
        panelCenter2 = new JPanel();
        panelCenter3 = new JPanel();
        panelCenter4 = new JPanel();

        icon = new ImageIcon("rentalMachine-icon.png");
        lblHeading = new JLabel("DVD Rentals", icon, JLabel.CENTER);

        lblEmptySpace2 = new JLabel(" ", JLabel.RIGHT);
        lblEmptySpace3 = new JLabel(" ", JLabel.RIGHT);
        lblEmptySpace4 = new JLabel(" ", JLabel.LEFT);
        lblEmptySpace5 = new JLabel(" ", JLabel.RIGHT);
        lblEmptySpace6 = new JLabel(" ", JLabel.LEFT);
        lblEmptySpace7 = new JLabel(" ", JLabel.LEFT);

        lblTitle = new JLabel("New DVD", JLabel.CENTER);
        lblEmptySpace = new JLabel(" ", JLabel.RIGHT);

        btnNewDvd = new JButton("New DVD");
        btnRentDvd = new JButton("Rent DVD");
        btnReturnDvd = new JButton("Return DVD");

        lblDvdNumber = new JLabel("DVD number: ", JLabel.RIGHT);
        txtDvdNumber = new JTextField();

        lblDvdTitle = new JLabel("Title: ", JLabel.RIGHT);
        txtDvdTitle = new JTextField();

        lblCategory = new JLabel("Category: ", JLabel.RIGHT);
        String[] options = {"horror", "", "Drama", "Romance", "Comedy", "Action", "Cartoon"};
        cbCategory = new JComboBox(options);

        lblRentalNumber = new JLabel("Rental number: ", JLabel.RIGHT);
        txtRentalNum = new JTextField();

        lblNewRelease = new JLabel("New release? ", JLabel.RIGHT);
        radioBtnYes1 = new JRadioButton("Yes" + " ", true);
        radioBtnNo1 = new JRadioButton("No", false);
        btnGroup1 = new ButtonGroup();
        rButtons1 = new JPanel();

        lblAvailableForRent = new JLabel("Available for rent? ", JLabel.RIGHT);
        radioBtnYes2 = new JRadioButton("Yes" + " ", true);
        radioBtnNo2 = new JRadioButton("No", false);
        btnGroup2 = new ButtonGroup();
        rButtons2 = new JPanel();

        lblExistingCustomer = new JLabel("Select existing customer: ", JLabel.RIGHT);
        String[] customers = {"customer1", "customer2", "customer3", "customer4", "customer5", "customer6", "customer7"};
        cbExistingCustomer = new JComboBox(customers);
        lblSelectMovieCategory = new JLabel("Select movie category: ", JLabel.RIGHT);
        String[] options2 = {"choose", "horror", "Sci-fi", "Drama", "Romance", "Comedy", "Action", "Cartoon"};
        cbCategory2 = new JComboBox(options2);
        lblAvailableMovies = new JLabel("Select movie: ", JLabel.RIGHT);
        String[] movies = {"option1", "option2", "option3", "option4"};
        cbAvailableMovies = new JComboBox(movies);
        lblTitle2 = new JLabel("Rent DVD", JLabel.CENTER);
        buttons3 = new JPanel();
        btnRent = new JButton("Rent");
        btnReset2 = new JButton("Reset");

        lblTitle3 = new JLabel("Return DVD", JLabel.CENTER);
        txtRentalNumber = new JTextField();
        lblReturnDvd = new JLabel("Enter rental number: ", JLabel.RIGHT);
        btnReturn = new JButton("Return");
        buttons4 = new JPanel();

        buttons2 = new JPanel();
        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");

        dvdCbox = new JComboBox();
        cboxCustomer = new JComboBox();

        btnCustomers = new JButton("Customers");
        btnDvd = new JButton("DVDs");
        btnMovies = new JButton("Show all movies");
        btnRentals = new JButton("Rentals");
        btnExit = new JButton("Exit");

        txtSearchView = new JTextField();
        lblSearchView = new JLabel("Search: ");

        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.PLAIN, 16);
        ft3 = new Font("Arial", Font.PLAIN, 24);

    }

    public void setDvdGUI() {

        //North Panel
        panelNorth.setLayout(new FlowLayout());
        lblHeading.setFont(ft1);
        lblHeading.setPreferredSize(new Dimension(500, 150));
        lblHeading.setForeground(Color.yellow);
        panelNorth.add(lblHeading);
        panelNorth.setBackground(new Color(0, 106, 255));
        panelNorth.setBackground(new Color(0, 106, 255));
        this.add(panelNorth, BorderLayout.NORTH);

        //Center Panel (New DVD form)
        panelCenter.setLayout(new GridLayout(13, 2));
        panelCenter.setBorder(new EmptyBorder(50, 0, 0, 500));
        panelCenter.setBackground(new Color(36, 145, 255));

        panelCenter.add(lblEmptySpace3);
        lblTitle.setFont(ft3);
        lblTitle.setForeground(Color.BLACK);
        panelCenter.add(lblTitle);

        lblDvdNumber.setFont(ft2);
        lblDvdNumber.setForeground(Color.BLACK);
        txtDvdNumber.setFont(ft2);
        panelCenter.add(lblDvdNumber);
        panelCenter.add(txtDvdNumber);

        lblDvdTitle.setFont(ft2);
        lblDvdTitle.setForeground(Color.BLACK);
        txtDvdTitle.setFont(ft2);
        panelCenter.add(lblDvdTitle);
        panelCenter.add(txtDvdTitle);

        lblCategory.setFont(ft2);
        cbCategory.setFont(ft2);
        lblCategory.setForeground(Color.BLACK);
        cbCategory.setForeground(Color.BLACK);
        panelCenter.add(lblCategory);
        cbCategory.setSelectedIndex(0);
        panelCenter.add(cbCategory);

        lblNewRelease.setFont(ft2);
        lblAvailableForRent.setFont(ft2);
        lblAvailableForRent.setForeground(Color.BLACK);
        lblNewRelease.setForeground(Color.BLACK);
        panelCenter.add(lblNewRelease);
        panelCenter.add(lblAvailableForRent);

        rButtons1.setBackground(new java.awt.Color(36, 145, 255));
        radioBtnYes1.setBackground(new java.awt.Color(36, 145, 255));
        radioBtnNo1.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter.add(lblNewRelease);
        btnGroup1.add(radioBtnYes1);
        btnGroup1.add(radioBtnNo1);
        rButtons1.add(radioBtnYes1);
        rButtons1.add(radioBtnNo1);
        rButtons1.add(Box.createHorizontalStrut(20));
        radioBtnYes1.add(Box.createRigidArea(new Dimension(100, 10)));
        panelCenter.add(rButtons1, new BorderLayout(100, 0));

        rButtons2.setBackground(new java.awt.Color(36, 145, 255));
        radioBtnYes2.setBackground(new java.awt.Color(36, 145, 255));
        radioBtnNo2.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter.add(lblAvailableForRent);
        btnGroup2.add(radioBtnYes2);
        btnGroup2.add(radioBtnNo2);
        rButtons2.add(radioBtnYes2);
        rButtons2.add(radioBtnNo2);
        rButtons2.add(Box.createHorizontalStrut(20));
        radioBtnYes2.add(Box.createRigidArea(new Dimension(100, 10)));
        panelCenter.add(rButtons2, new BorderLayout(10, 0));

        buttons2.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter.add(lblEmptySpace4);
        buttons2.add(btnSave);
        buttons2.add(btnReset);
        buttons2.add(Box.createHorizontalStrut(20));
        btnSave.add(Box.createRigidArea(new Dimension(50, 30)));
        btnReset.add(Box.createRigidArea(new Dimension(50, 30)));
        panelCenter.add(buttons2, new BorderLayout(10, 0));

        //Center Panel (Rent DVD form)
        //Center Panel 3
        panelCenter3.setLayout(new GridLayout(4, 2));
        panelCenter3.setBorder(new EmptyBorder(50, 0, 350, 500));
        panelCenter3.setBackground(new Color(36, 145, 255));

        panelCenter3.add(lblEmptySpace5);
        lblTitle3.setFont(ft3);
        lblTitle3.setForeground(Color.BLACK);
        panelCenter3.add(lblTitle3);

        lblReturnDvd.setFont(ft2);
        lblReturnDvd.setForeground(Color.BLACK);
        txtRentalNumber.setFont(ft2);
        panelCenter3.add(lblReturnDvd);
        panelCenter3.add(txtRentalNumber);

        buttons4.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter3.add(lblEmptySpace7);
        buttons4.add(btnReturn);
        buttons4.add(Box.createHorizontalStrut(20));
        btnReturn.add(Box.createRigidArea(new Dimension(50, 30)));
        panelCenter3.add(buttons4, new BorderLayout(10, 0));

        //West Panel
        panelWest.setLayout(new GridLayout(5, 1, 0, 8));
        panelWest.setBorder(new EmptyBorder(50, 20, 285, 0));
        panelWest.setBackground(new Color(36, 145, 255));

        btnNewDvd.setFont(ft2);
        btnNewDvd.setBackground(Color.ORANGE);
        btnRentDvd.setFont(ft2);
        btnReturnDvd.setFont(ft2);
        btnMovies.setFont(ft2);
        panelWest.add(btnNewDvd);
        panelWest.add(btnRentDvd);
        panelWest.add(btnReturnDvd);
        panelWest.add(btnMovies);
        lblSearchView.setFont(ft2);
        lblSearchView.setForeground(Color.BLACK);
        txtSearchView.setFont(ft2);

        this.add(panelWest, BorderLayout.WEST);

        //East Panel
        panelEast.setLayout(new GridLayout(1, 4));
        this.add(panelEast, BorderLayout.EAST);

        //South Panel
        panelSouth.setLayout(new GridLayout(1, 4));
        btnCustomers.setFont(ft3);
        btnDvd.setFont(ft3);
        btnDvd.setBackground(Color.ORANGE);
        btnRentals.setFont(ft3);
        btnExit.setFont(ft3);
        panelSouth.add(btnCustomers);
        panelSouth.add(btnDvd);
        panelSouth.add(btnRentals);
        panelSouth.add(btnExit);
        this.add(panelSouth, BorderLayout.SOUTH);
        btnCustomers.addActionListener(this);
        btnDvd.addActionListener(this);
        btnRentals.addActionListener(this);
        btnExit.addActionListener(this);

        btnNewDvd.addActionListener(this);
        btnRentDvd.addActionListener(this);
        btnRent.addActionListener(this);
        btnReturnDvd.addActionListener(this);
        btnReturn.addActionListener(this);
        btnMovies.addActionListener(this);
        btnSave.addActionListener(this);
        btnExit.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //btnNewDvd.doClick();
        this.newDvd();
        //this.setSize(600, 600);
        this.pack();
        this.setVisible(true);
    }

    public void showDvds() {

//         lblTitle2.setFont(ft3);
//        lblTitle2.setForeground(Color.BLACK);
        panelCenter4.setBackground(new Color(36, 145, 255));

        //Set table title
        panelCenter4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "All DVDs",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        String[] columnNames = {"dvdNumber", "title", "category", "phoneNum", "newRelease", "availableForRent"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        TableRowSorter sorter;
        Vector vect = new Vector();
        ArrayList<Vector> arrVec;

        try {

            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

            output.writeObject("displaydvds");

            arrVec = (ArrayList<Vector>) input.readObject();

            for (int i = 0; i < arrVec.size(); i++) {
                vect = arrVec.get(i);
                tableModel.addRow(vect);
            }

            sorter = new TableRowSorter<>(tableModel);
            tblShowAllDvds = new JTable(tableModel);

            //TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblShowAllDvds.getModel());

            txtSearchView.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(txtSearchView.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(txtSearchView.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(txtSearchView.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter(str));
                    }
                }
            });

            tblShowAllDvds.setRowSorter(sorter);

            JScrollPane sp = new JScrollPane(tblShowAllDvds);
            panelCenter4.add(sp);

            //Frame Size 
            sp.setSize(1000, 700);
            sp.setPreferredSize(new Dimension(1000, 500));
            this.add(panelCenter4, BorderLayout.CENTER);

            output.close();
            input.close();
            s1.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

    }

    public void custComboBox() {

        Vector custVect = new Vector();
        ArrayList<Vector> arrVec;
        DefaultComboBoxModel cbModel = new DefaultComboBoxModel(custVect);

        try {

            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

            output.writeObject("showexistingcustomers");

            arrVec = (ArrayList<Vector>) input.readObject();

            for (int i = 0; i < arrVec.size(); i++) {

                custVect = arrVec.get(i);
                cbModel.addElement(custVect);
            }

            lblExistingCustomer.setFont(ft2);
            lblExistingCustomer.setForeground(Color.BLACK);
            panelCenter2.add(lblExistingCustomer);
            cboxCustomer.setModel(cbModel);
            cboxCustomer.setFont(ft2);
            cboxCustomer.setForeground(Color.BLACK);
            panelCenter2.add(cboxCustomer);

            output.close();
            input.close();
            s1.close();

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

    }

    public void displayCategories() {

        lblSelectMovieCategory.setFont(ft2);
        cbCategory2.setFont(ft2);
        lblSelectMovieCategory.setForeground(Color.BLACK);
        cbCategory2.setForeground(Color.BLACK);
        panelCenter2.add(lblSelectMovieCategory);
        cbCategory.setSelectedIndex(-1);

        lblAvailableMovies.setFont(ft2);
        cbAvailableMovies.setFont(ft2);
        lblAvailableMovies.setForeground(Color.BLACK);
        cbAvailableMovies.setForeground(Color.BLACK);
        panelCenter2.add(cbCategory2);
        dvdCbox.setFont(ft2);
        dvdCbox.setForeground(Color.BLACK);
        panelCenter2.add(lblAvailableMovies);
        panelCenter2.add(dvdCbox);

        returnDvdButtons();

    }

    public void availableMovies() {

        cbCategory2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Vector dvdVect = new Vector();
                    ArrayList<Vector> arrVec;
                    DefaultComboBoxModel cbModel = new DefaultComboBoxModel(dvdVect);

                    Socket s1 = new Socket("localhost", 7800);
                    System.out.println("Connection established at port 7800");

                    ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
                    ObjectInputStream input = new ObjectInputStream(s1.getInputStream());

                    cbCategory2 = (JComboBox) e.getSource();
                    String data = String.valueOf(cbCategory2.getSelectedItem());

                    if (data.equals("horror")) 
                    {
                        output.writeObject("horror");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) {
                            dvdVect = arrVec.get(i);
                            cbModel.addElement(dvdVect);
                        }
                        output.close();
                    } 
                    else if (data.equals("Sci-fi")) 
                    {
                        output.writeObject("scifi");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) {

                            dvdVect = arrVec.get(i);
                            cbModel.addElement(dvdVect);
                        }  
                        output.close();
                    } 
                    else if (data.equals("Drama")) 
                    {
                        output.writeObject("drama");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) {

                            dvdVect = arrVec.get(i);
                            cbModel.addElement(dvdVect);
                        }   
                        output.close();
                    } 
                    else if (data.equals("Romance")) 
                    {
                        //panelCenter2.remove(dvdCbox);
                        output.writeObject("romance");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) 
                        {
                            dvdVect = arrVec.get(i);
                            cbModel.addElement(dvdVect);
                        }   
                        output.close();
                    } 
                    else if (data.equals("Comedy")) 
                    {
                        output.writeObject("comedy");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) {

                            dvdVect = arrVec.get(i);
                            //System.out.println(vect);
                            cbModel.addElement(dvdVect);
                        }   //dvdCbox.setModel(cbModel);
                        output.close();
                    } 
                    else if (data.equals("Action")) {
                        //panelCenter2.remove(dvdCbox);
                        output.writeObject("action");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) {

                            dvdVect = arrVec.get(i);
                            //System.out.println(vect);
                            cbModel.addElement(dvdVect);
                        }   //dvdCbox.setModel(cbModel);
                        output.close();
                    } 
                    else if (data.equals("Cartoon")) {
                        //panelCenter2.remove(dvdCbox);
                        output.writeObject("cartoons");
                        arrVec = (ArrayList<Vector>) input.readObject();
                        for (int i = 0; i < arrVec.size(); i++) {

                            dvdVect = arrVec.get(i);
                            //System.out.println(vect);
                            cbModel.addElement(dvdVect);
                        }   //dvdCbox.setModel(cbModel);
                        output.close();
                    }

                    dvdCbox.setModel(cbModel);
                    output.close();
                    input.close();
                    s1.close();

                } catch (IOException ex) {
                    Logger.getLogger(DvdGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DvdGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    //button colors for the rent dvd form
    public void returnDvdButtons() {
        buttons3.setBackground(new java.awt.Color(36, 145, 255));
        panelCenter2.add(lblEmptySpace2);
        buttons3.add(btnRent);
        buttons3.add(btnReset2);
        buttons3.add(Box.createHorizontalStrut(20));
        btnRent.add(Box.createRigidArea(new Dimension(50, 30)));
        btnReset2.add(Box.createRigidArea(new Dimension(50, 30)));
        panelCenter2.add(buttons3, new BorderLayout(10, 0));
    }

    //button colors for the add dvd form
    public void newDvd() {
        this.add(panelCenter, BorderLayout.CENTER);
        btnNewDvd.setBackground(Color.ORANGE);
        btnRentDvd.setBackground(UIManager.getColor("Button.background"));
        btnReturnDvd.setBackground(UIManager.getColor("Button.background"));
        btnMovies.setBackground(UIManager.getColor("Button.background"));

    }

    //GUI design for the rent dvd form
    public void rentDvd() {
        panelCenter2.setLayout(new GridLayout(6, 2));
        panelCenter2.setBorder(new EmptyBorder(40, 0, 250, 500));
        panelCenter2.setBackground(new Color(36, 145, 255));

        panelCenter2.add(lblEmptySpace6);
        lblTitle2.setFont(ft3);
        lblTitle2.setForeground(Color.BLACK);
        panelCenter2.add(lblTitle2);

        lblRentalNumber.setFont(ft2);
        lblRentalNumber.setForeground(Color.BLACK);
        txtRentalNum.setFont(ft2);
        panelCenter2.add(lblRentalNumber);
        panelCenter2.add(txtRentalNum);

        this.add(panelCenter2, BorderLayout.CENTER);
        btnRentDvd.setBackground(Color.ORANGE);
        btnNewDvd.setBackground(UIManager.getColor("Button.background"));
        btnReturnDvd.setBackground(UIManager.getColor("Button.background"));
        btnMovies.setBackground(UIManager.getColor("Button.background"));
    }

    //buttons for the return dvd form
    public void returnDvd() {
        this.add(panelCenter3, BorderLayout.CENTER);
        btnReturnDvd.setBackground(Color.ORANGE);
        btnRentDvd.setBackground(UIManager.getColor("Button.background"));
        btnNewDvd.setBackground(UIManager.getColor("Button.background"));
        btnMovies.setBackground(UIManager.getColor("Button.background"));
    }

    //send customer number from rent dvd to server 
    public void sendCustNum() {

        Vector custItems = (Vector) cboxCustomer.getSelectedItem();
        List<Integer> custList = Collections.list(custItems.elements());
        System.out.println(custList.get(0));
        int cNumber = Integer.parseInt("" + custList.get(0));

        try {
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            System.out.println("Getting data...");

            output.writeObject("customernumber");
            output.flush();
            Customer c = new Customer();
            c.setCustNumber(cNumber);
            output.writeObject(c);
            output.flush();

            output.close();
            s1.close();

            System.out.println("Data sent");
            System.out.println("Connection closed.");
        } catch (ConnectException connExcep) {
            System.out.println("Error 1: " + connExcep.getMessage());
            connExcep.printStackTrace();
        } catch (IOException ioExcep) {
            System.out.println("Error 2: " + ioExcep.getMessage());
            ioExcep.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

     //send dvd number from rent dvd to server 
    public void sendDvdNum() {

        Vector dvdItems = (Vector) dvdCbox.getSelectedItem();
        List<Integer> dvdList = Collections.list(dvdItems.elements());
        int dNumber = Integer.parseInt("" + dvdList.get(0));
        System.out.println(dNumber);

        try {
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            System.out.println("Getting data...");

            output.writeObject("dvdnumber");
            DVD dvdnr = new DVD();
            dvdnr.setDvdNumber(dNumber);
            output.writeObject(dvdnr);
            output.flush();

            output.close();
            s1.close();

            System.out.println("Data sent");
            System.out.println("Connection closed.");
        } catch (ConnectException connExcep) {
            System.out.println("Error 1: " + connExcep.getMessage());
            connExcep.printStackTrace();
        } catch (IOException ioExcep) {
            System.out.println("Error 2: " + ioExcep.getMessage());
            ioExcep.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

     //send rental number from rent dvd to server 
    public void sendRentalNum() {

        Vector custItems = (Vector) cboxCustomer.getSelectedItem();
        List<Integer> custList = Collections.list(custItems.elements());
        System.out.println(custList.get(0));
        int cNumber = Integer.parseInt("" + custList.get(0));

        Vector dvdItems = (Vector) dvdCbox.getSelectedItem();
        List<Integer> dvdList = Collections.list(dvdItems.elements());
        int dNumber = Integer.parseInt("" + dvdList.get(0));
        System.out.println(dNumber);

        try {
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            System.out.println("Getting data...");
            String text = txtRentalNum.getText().trim();
            int rentalNr = Integer.parseInt(text);

            output.writeObject("addrental");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(new Date());
            Rental rental = new Rental(rentalNr, date, cNumber, dNumber);
            output.writeObject(rental);
            output.flush();

            output.close();
            s1.close();

            System.out.println("Data sent");
            System.out.println("Connection closed.");
        } catch (ConnectException connExcep) {
            System.out.println("Error 1: " + connExcep.getMessage());
            connExcep.printStackTrace();
        } catch (IOException ioExcep) {
            System.out.println("Error 2: " + ioExcep.getMessage());
            ioExcep.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    //client for dvd return
    public void dvdReturn() {

        try {
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            System.out.println("Getting data...");

            output.writeObject("rentnum");
            Rental rntl = new Rental();
            String userRental = txtRentalNumber.getText();
            int rentalNr = Integer.parseInt(userRental);
            rntl.setRentalNumber(rentalNr);
            //System.out.println(rntl);
            output.writeObject(rntl);
            output.flush();

            output.close();
            s1.close();

            System.out.println("Data sent");
            System.out.println("Connection closed.");
        } catch (ConnectException connExcep) {
            System.out.println("Error 1: " + connExcep.getMessage());
            connExcep.printStackTrace();
        } catch (IOException ioExcep) {
            System.out.println("Error 2: " + ioExcep.getMessage());
            ioExcep.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    //code for updating customer table after dvd return
    public void custNum(){
    
        try {
            //Open connection to a server, at port 5432
            //localhost used here
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            System.out.println("Getting data...");
            
             output.writeObject("updatecustomer");
            System.out.println("customer number recieved");
            // Recieve object!
            //input.close();

            output.flush();
            output.close();
            s1.close();

            System.out.println("Data sent");
            System.out.println("Connection closed.");
        } catch (ConnectException connExcep) {
            System.out.println("Error 1: " + connExcep.getMessage());
            connExcep.printStackTrace();
        } catch (IOException ioExcep) {
            System.out.println("Error 2: " + ioExcep.getMessage());
           ioExcep.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            //ex.printStackTrace();
        }

    }
    
    //actions performed by buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Customers")) {
            new CustomerGUI().setGUI();
            this.setVisible(false);
        } else if (e.getActionCommand().equals("DVDs")) {

        } else if (e.getActionCommand().equals("Rentals")) {

            new RentalGUI().setRentalGUI();
            this.setVisible(false);

        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);

        } else if (e.getActionCommand().equals("New DVD")) {
            txtSearchView.setVisible(false);
            panelCenter2.removeAll();
            panelCenter.setVisible(true);
            panelCenter2.setVisible(false);
            panelCenter3.setVisible(false);
            panelCenter4.setVisible(false);
            this.newDvd();
        } else if (e.getActionCommand().equals("Save")) {

            if (txtDvdNumber.getText().length() != 0 && txtDvdTitle.getText().length() != 0) {

                boolean newRelease = false;
                boolean canRent = false;

                if (radioBtnYes1.isSelected()) 
                {
                    newRelease = true;

                } 
                else if (radioBtnNo1.isSelected()) 
                {
                    newRelease = false;
                }

                if (radioBtnYes2.isSelected()) 
                {
                    canRent = true;
                } 
                else if (radioBtnNo2.isSelected()) 
                {
                    canRent = false;
                }

                int dvdNumber = Integer.parseInt(txtDvdNumber.getText());
                String title = txtDvdTitle.getText();

                String category = (String) cbCategory.getSelectedItem();

                int ctgry = 0;
                if (category.equals("horror")) {
                    ctgry = 1;
                } else if (category.equals("Sci-fi")) {
                    ctgry = 2;
                } else if (category.equals("Drama")) {
                    ctgry = 3;
                } else if (category.equals("Romance")) {
                    ctgry = 4;
                } else if (category.equals("Action")) {
                    ctgry = 5;
                } else if (category.equals("Cartoon")) {
                    ctgry = 6;
                }

                Boolean recentRelease = Boolean.parseBoolean("" + newRelease);
                Boolean availableForRent = Boolean.parseBoolean("" + canRent);

                DVD dvd = new DVD(dvdNumber, title, category, recentRelease, availableForRent);

                DvdRentalsClient drClient = new DvdRentalsClient();
                drClient.dvdClient(dvd);
                JOptionPane.showMessageDialog(this, "DVD added successfully");
            }
        } else if (e.getActionCommand().equals("Rent DVD")) {
            txtSearchView.setVisible(false);

            panelCenter2.setVisible(true);
            this.add(panelCenter2, BorderLayout.CENTER);
            panelCenter.setVisible(false);
            panelCenter3.setVisible(false);
            panelCenter4.setVisible(false);
            this.rentDvd();
            custComboBox();
            displayCategories();
            availableMovies();
        } else if (e.getActionCommand().equals("Rent")) {

            this.sendCustNum();
            this.sendDvdNum();
            this.sendRentalNum();
            JOptionPane.showMessageDialog(this, "DVD rented successfully");

        } else if (e.getActionCommand().equals("Return DVD")) {
            txtSearchView.setVisible(false);
            panelCenter2.removeAll();
            panelCenter3.setVisible(true);
            panelCenter.setVisible(false);
            panelCenter2.setVisible(false);
            panelCenter4.setVisible(false);
            this.remove(panelCenter);
            this.remove(panelCenter2);
            this.remove(panelCenter4);
            this.returnDvd();
        } else if (e.getActionCommand().equals("Return")) {
            txtSearchView.setVisible(false);
            this.dvdReturn();
            this.custNum();
            
        } 
        else if (e.getActionCommand().equals("Show all movies")) {
            panelCenter2.removeAll();
            panelCenter4.repaint();
            panelCenter4.setVisible(true);
            panelCenter.setVisible(false);
            panelCenter2.setVisible(false);
            panelCenter3.setVisible(false);
            this.remove(panelCenter);
            this.remove(panelCenter2);
            this.remove(panelCenter3);
            btnMovies.setBackground(Color.ORANGE);
            btnRentDvd.setBackground(UIManager.getColor("Button.background"));
            btnNewDvd.setBackground(UIManager.getColor("Button.background"));
            btnReturnDvd.setBackground(UIManager.getColor("Button.background"));
            this.showDvds();
            panelWest.add(txtSearchView);
            txtSearchView.setVisible(true);
        }
    }

}

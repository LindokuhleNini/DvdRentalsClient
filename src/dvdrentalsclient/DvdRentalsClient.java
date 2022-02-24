/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrentalsclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import modelclasses.Customer;
import modelclasses.DVD;

/**
 *DvdRentalsClient.java
 * This is our DVD Rentals Client Server Program
 * @author Lindokuhle Nini (218196504)
 * Date: 08 November 2020
 */
public class DvdRentalsClient {

    public static void main(String[] args) {

        new CustomerGUI().setGUI();
    }

    //client for adding new customer
    public Customer customerClient(Customer c) {

        try {
            //Open connection to a server, at port 5432
            //localhost used here
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            System.out.println("Getting data...");

            output.writeObject("addcustomer");
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
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            //ex.printStackTrace();
        }
        return c;

    }

    //client for adding new dvd
    public DVD dvdClient(DVD dvd) {

        try {
            Socket s1 = new Socket("localhost", 7800);
            System.out.println("Connection established at port 7800");

            // Get output stream associated with the socket
            ObjectOutputStream output = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s1.getInputStream());
            System.out.println("Getting data...");

            output.writeObject("adddvd");
            output.writeObject(dvd);
            output.flush();
            input.close();
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
        return dvd;

    }

}

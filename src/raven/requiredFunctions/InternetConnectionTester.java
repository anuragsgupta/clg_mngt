/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.requiredFunctions;

/**
 *
 * @author anurag */
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternetConnectionTester {

    // Single instance of the class (static variable)
    private static InternetConnectionTester instance;

    // Private constructor to prevent external instantiation
    private InternetConnectionTester() {}

    // Method to provide the single instance of the class
    public static InternetConnectionTester getInstance() {
        if (instance == null) {
            synchronized (InternetConnectionTester.class) {
                if (instance == null) {
                    instance = new InternetConnectionTester();
                }
            }
        }
        return instance;
    }

    // Method to check internet connection
    public boolean isInternetConnected() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true; // Connection successful
        } catch (MalformedURLException e) {
            // If an exception occurs, the internet is not connected
            return false;
        } catch (IOException ex) {
            Logger.getLogger(InternetConnectionTester.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void main(String[] args) {
        // Obtain the single instance of InternetConnectionTester
        InternetConnectionTester tester = InternetConnectionTester.getInstance();
        
        // Check the internet connection
        if (tester.isInternetConnected()) {
            System.out.println("Internet is connected");
        } else {
            System.out.println("Internet is not connected");
        }
    }
}

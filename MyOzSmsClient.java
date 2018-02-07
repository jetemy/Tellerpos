/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.*;
import java.util.*;
import hu.ozeki.*;

public class MyOzSmsClient extends OzSmsClient {

    public MyOzSmsClient(String host, int port) throws IOException, InterruptedException {
        super(host, port);
        // TODO Auto-generated constructor stub
    }

      @Override
    public void doOnMessageAcceptedForDelivery(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message accepted for delivery. ID: "
                + osmsm.messageId);
    }

    
     @Override
    public void doOnMessageDeliveredToHandset(OzSMSMessage osmsm) {
       Date now = new Date();
        System.out.println(now.toString() + " Message delivered to handset. ID: "
                + osmsm.messageId);
    }

    @Override
    public void doOnMessageDeliveredToNetwork(OzSMSMessage osmsm) {
         Date now = new Date();
        System.out.println(now.toString() + " Message delivered to network. ID: "
                + osmsm.messageId);
    }

    @Override
    public void doOnMessageDeliveryError(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message could not be delivered. ID: "
                + osmsm.messageId + " Error message: " + osmsm.errorMessage + "\r\n");
    }

  @Override
    public void doOnMessageReceived(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message received. Sender address: "
                + osmsm.sender + " Message text: " + osmsm.messageData);
    }

    public void doOnClientConnectionError(String errorCode, String errorMessage) {
        Date now = new Date();
        System.out.println(now.toString() + " Message code: " + errorCode
                + ", Message: " + errorMessage);
    }

        public static void main(String[] args) {
            try {
                String host = "localhost";
                int port = 9500;
                String username = "admin";
                String password = "abc123";

                MyOzSmsClient osc = new MyOzSmsClient(host, port);
                osc.login(username, password);

                        String line = "Hello World";

System.out.println("SMS message:");

                        if(osc.isLoggedIn()) {
osc.sendMessage("+254706684869", line);
osc.logout();
                        }


                } catch (IOException | InterruptedException e) {
System.out.println(e.toString());
                }
        }

    @Override
    public void doOnClientConnectionError(int i, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

package backend;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chinkyminky
 */
public class ServerThread extends Thread {

    int clientID;
    Socket client;
    JTable table;
    DataOutputStream dos;
    DataInputStream dis;

    public ServerThread(int clientID, Socket client, JTable table) throws IOException {

        this.clientID = clientID;
        this.client = client;
        this.table = table;
        //Client has connected; setup streams
        dis = new DataInputStream(client.getInputStream());
        dos = new DataOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {

        try {
            DefaultTableModel Chefdtm = (DefaultTableModel) table.getModel();
            Integer noOfRows;
            //Receive number of rows in the table
            noOfRows = dis.readInt();
            System.out.println("Number of rows at table: " + noOfRows);
            for(int i=0; i<noOfRows; i++)
            {
                String item = dis.readUTF();
                System.out.println("Item in row " + i + ": " + item);
                Chefdtm.setValueAt(item, i, 0);
                int quantity = dis.readInt();
                System.out.println("Quantity in row " + i + ": " + quantity);
                Chefdtm.setValueAt(quantity, i, 1);
            }
            
            //What are you doing here?
            //You are sending an Integer only once. But you are trying to read again in the loop
            //Why? I didn't understand the thing inside the while loop, but, I know it runs limit number of times
            //Are you referring to my code? That was because on the Client side, I was sending 'limit' number
            //of messages. Eh? I fifn't remove any loops. Okay. I understand. Continue. Let's see if the message
            //from the client is being delivered?
            ///yes.// oh wait. this wont be able to print into the tables. wait.

            //System.out.println("Message from client " + clientID + ": " + message);
            //Will do? will do what? cIHansnge the objects to data? Yes. Yes Sir Will Do Okay wait

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

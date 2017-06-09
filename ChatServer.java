/**
 * Created by skald on 5/12/17.
 */
//import javax.swing.*;
//import java.util.Scanner;



import java.io.*;
import java.net.*;

public class ChatServer {
//Data Members
    private UserDataBase UserList;
    private ServerSocket sSocket;
    private boolean serverActive;

//Method Members

    public ChatServer() {
        UserList = new UserDataBase();
       /* try {
            sSocket = new ServerSocket(88888);
        } catch (IOException e) {
            System.out.println("Error! Could not start server on port.");
            System.exit(-1);
        }

        while(serverActive) {
            try {
                Socket cSocket = sSocket.accept();
                System.out.println("Client Connected");

                //ConnectionHandler cHand = new ConnectionHandler(cSocket);
            } catch (IOException e) {
                System.out.println("Something bad happened.");
            }
        }

        try {
            sSocket.close();
            System.out.println("Server no longer running.");
        } catch (Exception e) { System.exit(-1);} */
    }

    public void CreateAccount(UserData newUser) {
        UserList.insert(newUser);
    }

    public UserData login(String name, String pswd) {
        UserData find = null;
        if(UserList != null) {
            find = UserList.login(name, pswd);
        }
        return find;
    }

    public void AddNewThread(Thread toAdd) {
        UserList.addNewThread(toAdd);
    }

    public void sendMessage(Thread destination, Message toSend) {
        if(destination != null && toSend != null) {
            String title = destination.getTitle();
            String[] receivers = destination.getParticipants();

            UserList.sendMessage(receivers, title, toSend);
        }
    }


    public void ShowAllUsers() {
        UserList.showAllNames();
    }

    public static void main(String[] args) {

    }

}

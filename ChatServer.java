/**
 * Created by skald on 5/12/17.
 */
//import javax.swing.*;
//import java.util.Scanner;

public class ChatServer {
//Data Members
  //  private JFrame mainFrame;
  //  private JButton okButton;
    private UserDataBase UserList;

//Method Members

    public ChatServer() {
        /*
        mainFrame = new JFrame("This is a test.");
        mainFrame.setSize(500, 300);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        okButton = new JButton("OK");
        okButton.setBounds(300, 100, 300, 100);
        mainFrame.add(okButton);
        */
        UserList = new UserDataBase();
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
        ChatServer test = new ChatServer();
        String[] people = {"Bradley Maness", "James Hughes"};

        Thread testThread = new Thread("This is a test thread", people);
        Thread testThread2 = new Thread("Second Thread", people);

        Message testMessage = new Message("TheBradMan", "Blah Blah Blah");

      //  test.AddNewThread(testThread2);

        test.sendMessage(testThread2, testMessage);

        UserData me = test.login("Bradley Maness", "123456");

        if(me != null) {
            me.showThreads();
        }

        else
            System.out.println("Person not found!");

    }

}


import java.io.*;
import java.util.Scanner;

public class ServerTestingSuite {

    private ChatServer myServer;
    UserData me;

    public ServerTestingSuite() {
        myServer = new ChatServer();
        me = null;
    }

    public void Create3Users(Scanner input) {
        File list = new File("UserList.txt");
        if(!list.exists()) {
            System.out.println("Create 3 users to begin testing: ");
            addNewUser(input);
            addNewUser(input);
            addNewUser(input);
        }
    }

    public void addNewUser(Scanner input) {
        System.out.print("Creating New User Account:\nNew Name: ");
        String name = input.nextLine();
        System.out.print("New Username: ");
        String user = input.nextLine();
        System.out.print("New Password: ");
        String pswd = input.nextLine();

        UserData temp = new UserData(name,user,pswd);
        myServer.CreateAccount(temp);
        System.out.println("New account created. Check 'UserList.txt' and 'User_History' directory.");
    }

    public void login(Scanner input) {

        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        me = myServer.login(name, password);
        if(me != null) System.out.println("Success! You are logged in as " + me.getRealName());
        else System.out.println("Login error! Bad Name/Password");

    }

    public void logoff() {
        me = null;
        System.out.println("You have logged off.");
    }

    public void ThreadInteraction(Scanner input) {
        System.out.println("\nThis is a list of thread titles associated with this user:\n");
        Conversations myConvos = me.getChatLog();

        if(myConvos.isEmpty()) {
            System.out.println("You have no threads. Let's make some: ");

            System.out.print("Make a title: ");
            String title = input.nextLine();
            System.out.println("How many total people(self included): ");
            int num = 1;
            String [] people = new String[Integer.parseInt(input.nextLine())];
            people[0] = me.getRealName();
            while(num < people.length) {
                System.out.print("Next Name(" + (people.length - num) + "): ");
                String placeholder = input.nextLine();
                people[num] = placeholder;
                num++;
            }

            Thread testThread = new Thread(title, people);

            myServer.AddNewThread(testThread);

            System.out.println("Thread added! This new thread should be visible in\nall specified user directories in 'User_History'.");

        }

        myConvos.displayEverything();

        System.out.println();

        Thread toFind = null;

        while(toFind == null) {
            System.out.print("Type in name of thread to add message to: ");
            toFind = myConvos.getThread(input.nextLine());
            if(toFind == null)
                System.out.println("Couldn't find thread. Try again.");
        }

        System.out.println("Thread found! Now let's add some messages to it(Type QUIT to stop): ");

        boolean msg = true;

        Message testMess = null;

        while(msg) {

            System.out.print("<" + me.getUserName() + "> ");
            String text = input.nextLine();
            if(text.compareTo("QUIT") == 0) msg = false;
            else {
                testMess = new Message(me.getUserName(), text);
                myServer.sendMessage(toFind, testMess);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ServerTestingSuite test = new ServerTestingSuite();

        test.Create3Users(input);

        //test.addNewUser(input);

        while(test.me == null)
            test.login(input);

        test.ThreadInteraction(input);


        test.logoff();

    }
}

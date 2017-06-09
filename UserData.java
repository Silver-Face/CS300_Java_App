/*
    This class contains data that describes users.

    It will contain data members for their real name, username, and password.

    it will also contain references to turn it into a Binary Search Tree Node.

 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class UserData {
     //Data
    private String realName, userName, password;
    private boolean isOnline;
    private Conversations chatLogs;
    private UserData Left, Right;
    //methods
    public UserData(String rN, String uN, String pswd) {
        realName = rN;
        userName = uN;
        password = pswd;
        chatLogs = new Conversations();
        isOnline = false;
        Left = null;
        Right = null;
    }

    //public void addMessage(Message toAdd, String title)

    public void display() {
        System.out.println("Name: " + realName);
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
    }

    public Conversations getChatLog() {
        return chatLogs;
    }

    public void addThread(Thread toAdd) {
        chatLogs.addNewThread(toAdd);
    }

    public void showThreads() {
        chatLogs.displayEverything();
    }

    public Thread getThread(String name) {
        return chatLogs.getThread(name);
    }

    public void addMessage(Message toAdd, String threadTitle) {
        chatLogs.addMessage(toAdd, threadTitle);
        appendThread(realName, threadTitle, toAdd);
    }

    private void appendThread(String name, String threadTitle, Message toAdd) {
        try {
            FileOutputStream dir = new FileOutputStream("User_History/" + name + "/" + threadTitle + ".txt", true);
            PrintWriter pw = new PrintWriter(dir);

            pw.print(toAdd.getUser() + ";" + toAdd.getTimeStamp() + ";" + toAdd.getText() + "\n");
            pw.close();
        } catch (FileNotFoundException ex) {}
    }

   // public void recordNewThread(Thread toRecord) {}

    public void displayName() {
        System.out.println(realName);
    }

    public void setLeft(UserData set) {
        Left = set;
    }

    public void setRight(UserData set) {
        Right = set;
    }

    public UserData goLeft() {
        return Left;
    }

    public UserData goRight() {
        return Right;
    }

    public String getRealName(){
        return realName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void goOnline() {
        if(isOnline == false) isOnline = true;
    }
    public void goOffline() {
        if(isOnline == true) isOnline = false;
    }

    public static void main(String[] args) {
        UserData test = new UserData("Brad", "BradManForver", "12345");
        String[] people = {"Brad", "Melissa"};
        Thread dinner = new Thread("I'm hungry. Let's go eat.", people);
        Thread one = new Thread("This is a test thread", people);
        Thread two = new Thread("This a second test thread", people);
        test.addThread(one);
        test.addThread(dinner);
        test.addThread(two);

        test.showThreads();

        Message testMessage = new Message(test.getUserName(), "I'm thinking sushi or burgers. What do you have in mind?");

        test.addMessage(testMessage, "I'm hungry. Let's go eat.");

        test.showThreads();



    }
}
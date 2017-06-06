/*
    This contains the implementation for the Message class.

    It will contain data fields for containing the username of the person who sent it,
    the body of the message, and the timestamp of when it was created.

    Messages will be stored as a double-linked list in a superclass object called "Thread",
    which will contain a list of messages.

    [timestamp] <[username]> [Body of text]
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    //Data
    private String timeStamp, user, text;
    private Message next, prev;

    //Methods
    public Message(String usr, String txt) {
        timeStamp = new SimpleDateFormat("HH:mm:ss MM/dd").format(new Date());
        user = usr;
        text = txt;
        next = null;
        prev = null;
    }

    public Message(String usr, String time, String txt) {
        user = usr;
        timeStamp = time;
        text = txt;
        next = null;
        prev = null;
    }

    public void viewMessage() {
        System.out.println("<" + user + " @ " + timeStamp + "> " + text);
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setNext(Message add) {
        next = add;
    }

    public void setPrev(Message add) {
        prev = add;
    }

    public Message goNext() {
        return next;
    }

    public Message goPrev() {
        return prev;
    }

    public static void main(String[] args) {

//        Message test = new Message(args[0], args[1])
//        test.viewMessage();
    }
}


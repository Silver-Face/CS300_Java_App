/**
 * Created by skald on 5/21/17.


 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Thread {
    //Data
    private String title;
    private String[] participants;
    private String creationTime;
    private Message oldest, newest;

    private Thread next, prev;

    //Methods
    public Thread(String ttl, String[] users) {
        title = ttl;
        participants = users;
        creationTime = new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date());
        oldest = null;
        newest = null;
    }

    public void addMessage(Message toAdd) {
        if(oldest == null && newest == null) {
            oldest = toAdd;
            newest = toAdd;
        }

        else {
            toAdd.setPrev(newest);
            newest.setNext(toAdd);
            newest = toAdd;
        }
    }

    public void showAllParticipants() {
        if(participants != null) {
            for(int i = 0; i < participants.length; i++) {
                if(i != participants.length-1)
                    System.out.print(participants[i] + ", ");
                else
                    System.out.println(participants[i]);
            }
        }
    }

    public void showAllMessages() {
        System.out.println("Message Title: " + title);
        System.out.print("Message Participants: ");
        showAllParticipants();
        System.out.println("Created on " + creationTime);
        if(oldest == null && newest == null) {
            System.out.println("No messages yet. Be the first!");
        }
        else {
            showAllMessages(oldest);
        }
    }

    private void showAllMessages(Message current) {
        if(current != null) {
            current.viewMessage();
            showAllMessages(current.goNext());
        }
    }

    public Thread goNext() {
        return next;
    }

    public Thread goPrev() {
        return prev;
    }

    public void setNext(Thread set) {
        next = set;
    }

    public void setPrev(Thread set) {
        prev = set;
    }

    public static void main(String[] args) {
      /* int switchUser = 0;
        Scanner input = new Scanner(System.in);

        Thread test = new Thread("TestTitle", args);

        test.showAllMessages();
    /*
        System.out.println("You just created a new thread. Your test username will change.");

        for(int i = 0; i < 5; i++) {
            if(switchUser == 0)
                switchUser = 1;
            else
                switchUser = 0;

            System.out.print("<" + args[switchUser] + "> ");
            String text = input.nextLine();
            Message temp = new Message(args[switchUser], text);
            test.addMessage(temp);
        }

        test.showAllMessages();
    */
    }
}

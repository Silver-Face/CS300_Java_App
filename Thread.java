/**
 * Created by skald on 5/21/17.


 */

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Scanner;

public class Thread {
    //Data
    private String title;
    private String[] participants;
    private String creationTime;
    private String lastUpdated;
    private Message oldest, newest;

    private Thread next, prev;

    //Methods
    public Thread(String ttl, String[] users) {
        title = ttl;
        participants = users;
        creationTime  = new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date());
        lastUpdated = new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date());
        oldest = null;
        newest = null;
    }

    public Thread(String ttl, String[] users, String creation, String update) {
        title = ttl;
        participants = users;
        creationTime = creation;
        lastUpdated = update;
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

        lastUpdated = new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date());
    }

    public void showThreadHeading() {
        System.out.println("Title: " + title + "\nLast Updated:  " + lastUpdated + "\nFirst Created: " + creationTime);
        showAllParticipants();
    }

    private void showAllParticipants() {
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
        showThreadHeading();
        if(oldest == null && newest == null) {
            System.out.println("No messages yet. Be the first!");
        }
        else {
            showAllMessages(oldest);
        }
    }

    public boolean compareParti(String toComp) {
        if(participants != null) {
            for(int i = 0; i < participants.length - 1; i++) {
                if(participants[i].compareTo(toComp) == 0)
                    return true;
            }
        }

        return false;
    }

    private void showAllMessages(Message current) {
        if(current != null) {
            current.viewMessage();
            showAllMessages(current.goNext());
        }
    }

    public String getTitle() {
        return title;
    }

    public String[] getParticipants() {
        return participants;
    }

    public String getCreation() {
        return creationTime;
    }
    public String getLastUpdate() {
        return lastUpdated;
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
      /*  String[] people = {"me", "you"};
        Thread test = new Thread("This is a test thread", people);

        test.showThreadHeading(); */
    }
}

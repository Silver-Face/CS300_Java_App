/**
 * Created by skald on 5/22/17.
 */

import java.io.File;
import java.io.IOException;


public class Conversations {
    //Data
    private Thread MostRecent, LeastRecent;

    //Methods

    public Conversations() {
        MostRecent = null;
        LeastRecent = null;
    }

    public boolean isEmpty() {
        if(MostRecent == null && LeastRecent == null) return true;
        else return false;
    }

    public void addNewThread(Thread toAdd) {
        if (MostRecent == null && LeastRecent == null) {
            MostRecent = toAdd;
            LeastRecent = toAdd;
        } else {
            toAdd.setNext(MostRecent);
            MostRecent.setPrev(toAdd);
            MostRecent = toAdd;
        }
    }

    public void addMessage(Message toAdd, String threadTitle) {
        if(MostRecent != null && LeastRecent != null) {
            Thread temp = MostRecent;
            addMessage(temp, toAdd, threadTitle);
        }
    }

    private void addMessage(Thread current, Message toAdd, String title) {
        if(current != null) {
            if(current.getTitle().compareTo(title) == 0) {
                current.addMessage(toAdd);
                moveToFront(current);
                return;
            }
            else
                addMessage(current.goNext(), toAdd, title);

        }
    }

    private void moveToFront(Thread toMove) {
        if(MostRecent != toMove) {
            if (toMove.goNext() == null) {
                toMove.goPrev().setNext(null);
                toMove.setPrev(null);
                toMove.setNext(MostRecent);
                MostRecent.setPrev(toMove);
                MostRecent = toMove;
            } else {
                toMove.goNext().setPrev(toMove.goPrev());
                toMove.goPrev().setNext(toMove.goNext());
                toMove.setPrev(null);
                toMove.setNext(MostRecent);
                MostRecent.setPrev(toMove);
                MostRecent = toMove;

            }
        }
        return;
    }

    public void displayAllHeadings() {
        if(MostRecent != null && LeastRecent != null)
            displayAllHeadings(MostRecent);
    }

    private void displayAllHeadings(Thread current) {
        if(current != null) {
            current.showThreadHeading();
            displayAllHeadings(current.goNext());
        }
    }

    public Thread getThread(String name) {
        return getThread(MostRecent, name);
    }

    private Thread getThread(Thread current, String name) {
        if(current != null) {
            if(current.getTitle().compareTo(name) == 0)
                return current;
            else
                return getThread(current.goNext(), name);
        }
        else return null;
    }

    public void displayEverything() {
        if(MostRecent != null && LeastRecent != null) {
            displayEverything(MostRecent);
        }
        else System.out.println("No threads to display!");
    }



    private void displayEverything(Thread current) {
        if(current != null) {
            System.out.print("->" + current.getTitle() + "\n");
            displayEverything(current.goNext());
        }
    }

    public static void main(String[] args) {}
}

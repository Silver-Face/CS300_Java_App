/**
 * Created by skald on 5/22/17.
 */
public class Conversations {
    //Data
    private Thread MostRecent, LeastRecent;

    //Methods

    public Conversations() {
        MostRecent = null;
        LeastRecent = null;
    }

    public void addNewThread(Thread toAdd) {
        if(MostRecent == null && LeastRecent == null) {
            MostRecent = toAdd;
            LeastRecent = toAdd;
        }

        else {
            toAdd.setNext(MostRecent);
            MostRecent.setPrev(toAdd);
            MostRecent = toAdd;
        }
    }

    public void updateThread(String user) {
       // if(MostRecent != null &&)
    }
}

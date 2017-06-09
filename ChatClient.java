

import java.io.*;

public class ChatClient {
//Data Members
    UserData currentUser = null;
    /*
        Data members for GUI interface
    */

    /*
        Data Members for Java networking
     */

//Methods
    public ChatClient() {

    }

    public UserData login() {
        /*
            This function opens up a small window, prompts the user for their
            real name and password, and sends them over to the server which should
            return a person's user data and chat history, which can be accessed later.
         */
        return null;
    }

    public void createThread() {
        /*
            Prompts the user to create a new thread. The new thread is added to the local
            history in UserData, and sent to the server to be formally recorded.
         */
    }

    public void sendMessage() {

    }

}
import java.net.*;
import java.io.*;

public class ConnectionHandler implements Runnable{

    Socket clientSocket;
    UserDataBase dataBase;
    ObjectOutputStream toClient;
    ObjectInputStream fromClient;

    boolean isRunning = true;

    public ConnectionHandler(Socket toAdd, UserDataBase DB) {
        super();
        clientSocket = toAdd;
        dataBase = DB;
    }

    public void run() {
        try {
            toClient = new ObjectOutputStream(clientSocket.getOutputStream());
            fromClient = new ObjectInputStream(clientSocket.getInputStream());



        } catch (IOException e) {}

    }

}

/*
    This class manages a binary search tree of users organized by real names.

    When the server is started it will automatically build a list of registered users from a saved file
    and store them in the data structure.
*/

import java.io.*;
import java.util.Scanner;

public class UserDataBase {
    //Data
    private UserData root;
    private int userCount; //

    //Methods
    public UserDataBase() {
        userCount = 0;
        build();
        saveUserList();
    }

    private void build() {

        try {
            File userFile = new File("UserList.txt");
            if (userFile.exists()) {
                Scanner read = new Scanner(userFile);
                read.useDelimiter(";|\\n");

                int throwAwayInt = read.nextInt();

                while (read.hasNext()) {
                    String name = read.next();
                    String user = read.next();
                    String pswd = read.next();

                    UserData addNew = new UserData(name, user, pswd);
                    insert(addNew);
                }
            } else
                throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.out.println("Error! File Not Found!");
        }
    }

    public UserData login(String name, String pswd) {
        if(root != null)
            return login(root, name, pswd);
        else
            return null;
    }

    private UserData login(UserData root, String name, String pswd) {
        if(root == null)
            return null;
        else if(root.getRealName().compareTo(name) == 0 && root.getPassword().compareTo(pswd) == 0)
            return root;
        else if(root.getRealName().compareTo(name) > 0)
            return login(root.goLeft(), name, pswd);
        else
                return login(root.goRight(), name, pswd);
    }

    public void addNewThread(Thread toAdd) {
        if (root != null) {
            String[] names = toAdd.getParticipants();
            addNewThread(root, toAdd, names);
        }
    }

    private void addNewThread(UserData root, Thread toAdd, String[] names) {
        if (root != null) {
            addNewThread(root.goLeft(), toAdd, names);

            for(int i = 0; i < names.length; i++) {
                if(names[i].compareTo(root.getRealName()) == 0) {
                    root.addThread(toAdd);
                    saveThread(root, toAdd);
                }
            }
            addNewThread(root.goRight(), toAdd, names);
        }
    }

    private void saveThread(UserData root, Thread toSave) {
        try {
            FileOutputStream threadFile = new FileOutputStream("User_History/" + root.getRealName() + "/" + toSave.getTitle() + ".txt");
            PrintWriter pw = new PrintWriter(threadFile);

            pw.println(toSave.getTitle());

            String[] people = toSave.getParticipants();

            pw.print(people.length + ";");

            for(int i = 0; i < people.length; i++) {
                if((i+1) == people.length)
                    pw.println(people[i]);
                else
                    pw.print(people[i] + ";");
            }

            pw.println("Creation Time: \n" + toSave.getCreation());
            pw.println("Last Updated: \n" + toSave.getLastUpdate());

            pw.close();

        } catch (FileNotFoundException e) {}

    }

    private void loadSavedThreads(UserData root) {
       try {
            File dir = new File("User_History/" + root.getRealName() + "/");
            File[] threads = dir.listFiles();

            for(int i = 0; i < threads.length; i++) {
                Scanner reader = new Scanner(threads[i]);
                reader.useDelimiter(";|\\n");
                String title = reader.next();
                String[] people = new String[reader.nextInt()];
                for(int j = 0; j < people.length; j++) {
                    people[j] = reader.next();
                }
                String throwaway = reader.next();
                String creation = reader.next();
                throwaway = reader.next();
                String update = reader.next();

                Thread temp = new Thread(title, people, creation, update);
               // if(reader.hasNext()) {
                    while(reader.hasNext()) {
                        String user = reader.next();
                        String time = reader.next();
                        String text = reader.next();

                        Message tempMessage = new Message(user, time, text);

                        temp.addMessage(tempMessage);
                    }
               // }
                root.addThread(temp);
            }
        } catch (FileNotFoundException ex) {}

        return;
    }

    public void sendMessage(String[] sendTo, String threadName, Message toAdd) {
        if(root != null) {
            sendMessage(root, sendTo, threadName, toAdd);
        }
    }

    private void sendMessage(UserData root, String[] sendTo, String threadName, Message toAdd) {
        if(root != null) {

            sendMessage(root.goLeft(), sendTo, threadName, toAdd);

            for(int i = 0; i < sendTo.length; i++) {
                if(sendTo[i].compareTo(root.getRealName()) == 0) {
                    root.addMessage(toAdd, threadName);
                }
            }

            sendMessage(root.goRight(), sendTo, threadName, toAdd);
        }


    }

    private void saveUserList() {
        if (root != null) {
            FileOutputStream os = null;
            PrintWriter pw = null;
            try {
                os = new FileOutputStream("UserList.txt");
                pw = new PrintWriter(os);

                pw.write(userCount + "\n");

                saveUserList(root, pw);

                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUserList(UserData root, PrintWriter w) {
        if(root != null) {
            saveUserList(root.goLeft(), w);
            w.print(root.getRealName() + ";");
            w.print(root.getUserName() + ";");
            w.println(root.getPassword());
            saveUserList(root.goRight(), w);
        }
    }

    //display all names:
    public void showAllNames() {
        if(root != null)
            showAllNames(root);
    }

    private void showAllNames(UserData root) {
        if(root == null) return;

        showAllNames(root.goLeft());

        root.displayName();
        root.showThreads();

        showAllNames(root.goRight());
    }

    //insert methods
    public void insert(UserData add) {
        if(root == null) {
            root = add;
            HistoryCheck(root);
            userCount++;
            loadSavedThreads(root);
            saveUserList();
            return;
        }
        else {
            insert(root, add);
            saveUserList();

        }

    }

    private int insert(UserData root, UserData add) {
        if(root == null) return 0;

        else{
            if(root.getRealName().compareTo(add.getRealName()) > 0) {
                if (root.goLeft() == null) {
                    root.setLeft(add);
                    userCount++;
                    HistoryCheck(root.goLeft());
                    loadSavedThreads(root.goLeft());
                    return 0;
                }
                else
                    insert(root.goLeft(), add);

            }

            else if(root.getRealName().compareTo(add.getRealName()) == 0)
                    return 1;

            else {
                if (root.goRight() == null) {
                    root.setRight(add);
                    userCount++;
                    HistoryCheck(root.goRight());
                    loadSavedThreads(root.goRight());
                    return 0;
                }
                else
                    insert(root.goRight(), add);
            }
        }

        return 0;
    }

    private void HistoryCheck(UserData root) {
        File checkDir = new File("User_History/" + root.getRealName());
        if (!checkDir.exists())
            checkDir.mkdirs();
    }

    //search methods
    public UserData searchName(String toFind){
        if(root == null) return null;
        else
            return searchName(root, toFind);
    }

    private UserData searchName(UserData root, String toFind) {
        if(root == null) return null;

        else if(root.getRealName().compareTo(toFind) == 0) return root;

        else if(root.getRealName().compareTo(toFind) > 0) return searchName(root.goLeft(), toFind);

        else return searchName(root.goRight(), toFind);
    }

    public static void main(String[] args) {

    }
}

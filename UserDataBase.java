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
    }

    //This method reads in user data from a file "users.txt", inserts them
    //into a dynamic array of UserData items  which then creates a binary
    //search tree.
    public void build() {
        File userFile = new File("users.txt");
        try {
            if(userFile.exists()) {
                Scanner read = new Scanner(userFile);
                read.useDelimiter("\\n");
                int userNum = read.nextInt();
                System.out.println(userNum);

                while(read.hasNext()) {
                    String name = read.next();
                    System.out.println(name);
                    String user = read.next();
                    System.out.println(user);
                    String pswd = read.next();
                    System.out.println(pswd);

                    UserData addNew = new UserData(name, user, pswd);
                    insert(addNew);
                }
            }
            else
                throw new FileNotFoundException();
        }
        catch (FileNotFoundException ex) {}
    }

    public void saveUserList() {
        if(root == null) return;
        else{
            FileOutputStream os = null;
            PrintWriter pw = null;
            try{
                os = new FileOutputStream("users.txt");
                pw = new PrintWriter(os);

                pw.write(userCount + "\n");

                saveUserList(root, pw);

                pw.flush();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUserList(UserData root, PrintWriter w) {
        if(root != null) {
            saveUserList(root.goLeft(), w);
            w.println(root.getRealName());
            w.println(root.getUserName());
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

        showAllNames(root.goRight());
    }

    //insert methods
    public void insert(UserData add) {
        if(root == null) root = add;
        else {
            insert(root, add);
        }
        userCount += 1;
    }

    private void insert(UserData root, UserData add) {
        if(root == null) return;

        else{
            if(root.getRealName().compareTo(add.getRealName()) > 0) {
                if(root.goLeft() == null)
                    root.setLeft(add);
                else
                    insert(root.goLeft(), add);

            }
            else {
                if(root.goRight() == null)
                    root.setRight(add);
                else
                    insert(root.goRight(), add);
            }
        }
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

}

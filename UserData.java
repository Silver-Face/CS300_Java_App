/*
    This class contains data that describes users.

    It will contain data members for their real name, username, and password.

    it will also contain references to turn it into a Binary Search Tree Node.

 */
 public class UserData {
     //Data
    private String realName, userName, password;
    private boolean isOnline;
    private UserData Left, Right;
    //methods
    public UserData(String rN, String uN, String pswd) {
        realName = rN;
        userName = uN;
        password = pswd;
        isOnline = false;
        Left = null;
        Right = null;
    }

    public void display() {
        System.out.println("Name: " + realName);
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
    }

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
//"Getter methods for returning data for comparison and sorting in BST data structure.
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
}
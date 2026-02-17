import java.util.ArrayList;

public class Account {

    private String username;
    private int authToken;
    private ArrayList<Message> messageBox;

    public Account(String username, int authToken){
        this.username = username;
        this.authToken = authToken;
        this.messageBox = new ArrayList<>();

    }

    public ArrayList<Message> getMessages(){
        return messageBox;
    }

    public String getUsername() {
        return username;
    }

    public int getAuthToken() {
        return authToken;
    }

}



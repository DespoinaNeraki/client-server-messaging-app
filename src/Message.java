public class Message {

    private static int ID = 0;

    private boolean isRead;
    private String sender;
    private String receiver;
    private String body;
    private int id;

    public Message (String sender, String receiver, String body){
        this.isRead = false;
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        this.id = ID;
        ID++;
    }

    public int getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public boolean getIsRead(){
        return isRead;
    }

    public String getBody() {
        return body;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}

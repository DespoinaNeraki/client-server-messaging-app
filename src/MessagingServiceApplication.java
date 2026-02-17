import com.sun.xml.internal.bind.v2.model.core.ID;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class MessagingServiceApplication extends UnicastRemoteObject implements MessagingService {

    private ArrayList<Account> accounts;
    private Random tokenGenerator;

    protected MessagingServiceApplication() throws RemoteException {
        super();
        tokenGenerator = new Random();
        accounts = new ArrayList<>();

    }

    @Override
    public int createAccount(String username) {
        if (!username.matches("[a-zA-Z0-9]+"))
            return -2;
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return -1;
            }
        }
        int token = tokenGenerator.nextInt(10000);
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Account account : accounts) {
                if (account.getAuthToken() == token) {
                    token = tokenGenerator.nextInt(10000);
                    flag = true;
                    break;
                }
            }
        }
        Account acc = new Account(username, token);
        accounts.add(acc);
        return token;
    }

    @Override
    public String showAccounts(int token) {
        StringBuilder output = new StringBuilder();

        if(!authentication(token))
            return "Invalid auth token";

        for (int i = 0; i < accounts.size(); i++) {
            output.append(i + 1).append(".").append(accounts.get(i).getUsername()).append("\n");
        }


        return output.toString();
    }

    @Override
    public String sendMessage(int token, String recipient, String messageBody) {

        if(!authentication(token))
            return "Invalid auth token";

        String sender = "";
        sender = findAccount(token).getUsername();


        for (Account account : accounts) {
            if (account.getUsername().equals(recipient)) {
                account.getMessages().add(new Message(sender, recipient, messageBody));
                return "OK";
            }
        }

        return "User does not exist";
    }

    @Override
    public String showInbox(int token) {

        if(!authentication(token))
            return "Invalid auth token";

        Account account=findAccount(token);
        StringBuilder output = new StringBuilder();
        for (Message message: account.getMessages()){
           output.append(message.getId()).append(". from: ").append(message.getSender());
           if(!message.getIsRead()){
               output.append('*');
           }
           output.append("\n");

        }

        return output.toString();
    }

    @Override
    public String readMessage(int token, int messageId) {

        if(!authentication(token))
            return "Invalid auth token";

         StringBuilder output = new StringBuilder();
         Account acc = findAccount(token);
         Message mess=null;
         for(Message message:acc.getMessages()) {
             if(message.getId()==messageId){
                 mess=message;
                 break;
             }
         }
         if(mess==null){
             return "Message ID does not exist";

         }
         mess.setRead(true);
         output.append('(').append(mess.getSender()).append(')').append(mess.getBody()).append("\n");
        return output.toString();
    }

    @Override
    public String deleteMessage(int token, int messageId) {

        if(!authentication(token))
            return "Invalid auth token";

        StringBuilder output = new StringBuilder();
        Account acc = findAccount(token);
        int msg_index = -1;
        for (int i = 0; i < acc.getMessages().size(); i++) {
            if (messageId == acc.getMessages().get(i).getId()){
                msg_index = i;
                break;
            }
        }
        if(msg_index == -1)
            return "Message does not exist";

        acc.getMessages().remove(msg_index);

        return "OK";
    }


    private boolean authentication(int token) {
        return findAccount(token) != null;
    }

    private Account findAccount(int token){
        for(Account account:accounts){
            if(account.getAuthToken()==token){
                return account;
            }
        }
        return null;
    }

}

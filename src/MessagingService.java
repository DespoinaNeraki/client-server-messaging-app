import com.sun.security.ntlm.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MessagingService extends Remote {

    public int createAccount(String username) throws RemoteException;

    public String showAccounts(int token) throws RemoteException;

    public String sendMessage(int token, String recipient, String messageBody) throws RemoteException;

    public String showInbox(int token) throws RemoteException;

    public String readMessage(int token, int messageId) throws RemoteException;

    public String deleteMessage(int token, int messageId) throws RemoteException;


}






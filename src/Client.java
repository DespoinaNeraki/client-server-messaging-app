import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            String host = (args.length < 1) ? null : args[0];
            int port = (args.length < 2) ? -1 : Integer.parseInt(args[1]);
            int action = (args.length < 3) ? -1 : Integer.parseInt(args[2]);

            if(host == null) {
                System.out.println("No host is specified");
                System.exit(1);
            }else if (port == -1){
                System.out.println("No port is specified");
                System.exit(2);
            }else if (action == -1){
                System.out.println("No action is specified");
            }
            Registry rmiRegistry = LocateRegistry.getRegistry(host, port);

            MessagingService stub = (MessagingService) rmiRegistry.lookup("messenger");


            if (action == 1) {
                String username = (args.length < 4) ? null : args[3];
                if (username == null){
                    System.out.println("No username is specified");
                    System.exit(3);
                }
                int answer = stub.createAccount(username);
                if (answer == -2)
                    System.out.println("Invalid username!");
                else if (answer == -1)
                    System.out.println("Username already exists!");
                else
                    System.out.println(answer);
            } else {
                int token = (args.length < 4) ? null : Integer.parseInt(args[3]);

                if (action == 2) {
                    System.out.println(stub.showAccounts(token));
                }
                else if(action == 3){
                    String recipient = (args.length < 5) ? null : args[4];
                    String body = (args.length < 6) ? null : args[5];
                    System.out.println(stub.sendMessage(token, recipient, body));
                }
                else if(action == 4){
                    System.out.println(stub.showInbox(token));
                }
                else if(action == 5){
                    int messageId = (args.length < 5) ? null : Integer.parseInt(args[4]);
                    System.out.println(stub.readMessage(token, messageId));
                }
                else if(action == 6){
                    int messageId = (args.length < 5) ? null : Integer.parseInt(args[4]);
                    System.out.println(stub.deleteMessage(token, messageId));
                }
                else{
                    System.out.println("Invalid action number");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

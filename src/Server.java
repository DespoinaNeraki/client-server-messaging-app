import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            MessagingServiceApplication stub = new MessagingServiceApplication();
            Registry rmiRegistry = LocateRegistry.createRegistry(port);
            rmiRegistry.rebind("messenger", stub);
            System.out.println("Server is running");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}






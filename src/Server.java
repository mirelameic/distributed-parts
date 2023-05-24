import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: Server <server_name> <port>");
            System.exit(1);
        }

        String serverName = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            PartRepository repository = new PartRepositoryImpl(serverName);
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind(serverName, repository);

            System.out.println("Server bound");

        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}
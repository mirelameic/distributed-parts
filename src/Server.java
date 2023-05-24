import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: Server <repository_name>");
            System.exit(1);
        }

        String name = args[0];
        try {
            PartRepository repository = new PartRepositoryImpl(name);
            Registry registry = LocateRegistry.createRegistry(1098);
            Naming.rebind(name, repository);
            System.out.println("Server bound");
        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}
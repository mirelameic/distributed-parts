import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PartRepositoryServer{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: PartRepositoryServer <repository_name>");
            System.exit(1);
        }

        String name = args[0];
        try {
            PartRepository repository = new PartRepositoryImpl(name);
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind(name, repository);
            System.out.println("PartRepositoryServer bound");
        } catch (Exception e) {
            System.err.println("PartRepositoryServer exception:");
            e.printStackTrace();
        }
    }
}
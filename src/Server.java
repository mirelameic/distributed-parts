import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static final String REPOSITORY_PREFIX = "PartRepository_";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: Server <server_name> <port>");
            System.exit(1);
        }

        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        String serverAddress;
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            serverAddress = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Failed to determine server address");
            System.exit(1);
            return;
        }

        try {
            String repositoryName = REPOSITORY_PREFIX + serverName;
            PartRepository repository = new PartRepositoryImpl(repositoryName);
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(repositoryName, repository);

            // Registro do servidor no servidor de coordenação
            ServerRegistry.getInstance().registerServer(serverName, serverAddress);

            System.out.println("Server bound");

            ServerRegistry.getInstance().printRegisteredServers();

            // Remover o registro do servidor no servidor de coordenação ao encerrar
            ServerRegistry.getInstance().unregisterServer(serverName, serverAddress);

        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}
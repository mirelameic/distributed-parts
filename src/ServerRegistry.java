import java.util.HashMap;
import java.util.Map;

public class ServerRegistry {
    private static ServerRegistry instance;
    private static Map<String, String> serverMap;

    private ServerRegistry() {
        serverMap = new HashMap<>();
    }

    public static synchronized ServerRegistry getInstance() {
        if (instance == null) {
            instance = new ServerRegistry();
        }
        return instance;
    }

    public static synchronized void registerServer(String serverName, String serverAddress) {
        serverMap.put(serverName, serverAddress);
    }

    public static synchronized void unregisterServer(String serverName, String serverAddress) {
        serverMap.remove(serverName, serverAddress);
    }

    public static synchronized String getServerAddress(String serverName) {
        return serverMap.get(serverName);
    }

    public static synchronized Map<String, String> getRegisteredServers() {
        return new HashMap<>(serverMap);
    }

    public static synchronized void printRegisteredServers() {
        System.out.println("Registered Servers:");
        for (Map.Entry<String, String> entry : serverMap.entrySet()) {
            String serverName = entry.getKey();
            String serverAddress = entry.getValue();
            System.out.println("Server Name: " + serverName);
            System.out.println("Server Address: " + serverAddress);
        }
    }
}
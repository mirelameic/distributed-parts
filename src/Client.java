import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static PartRepository currentRepository;
    private static Part currentPart;
    private static List<Part> currentSubParts;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: Client <server_name>");
            System.exit(1);
        }

        String serverName = args[0];
        
        try {
            initialize(serverName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserInterface userInterface = new UserInterface();

        try {
            boolean running = true;

            while (running) {
                String command = userInterface.getUserCommand();

                if (command.equals("listp")) {
                    listParts();
                } else if (command.startsWith("bind ")) {
                    String newRepositoryName = command.substring(5);
                    bindRepository(newRepositoryName);
                } else if (command.startsWith("getp ")) {
                    String partCode = command.substring(5);
                    getPart(partCode);
                } else if (command.equals("showp")) {
                    showPart();
                } else if (command.equals("clearlist")) {
                    clearSubPartsList();
                } else if (command.equals("addsubpart")) {
                    addSubPart();
                } else if (command.equals("addp")) {
                    addPart();
                } else if (command.equals("quit")) {
                    running = false;
                } else {
                    UserInterface.displayMessage("Invalid command.");
                }
            }

            UserInterface.displayMessage("Client terminated.");
        } catch (Exception e) {
            userInterface.displayError("Client exception:", e);
        }
    }

    private static void initialize(String serverName) throws Exception {
        String serverAddress = ServerRegistry.getInstance().getServerAddress(serverName);
        ServerRegistry.getInstance().printRegisteredServers();
        System.out.println(serverAddress);
    
        if (serverAddress != null) {
            String serverURL = "//" + serverAddress + "/" + serverName;
            currentRepository = (PartRepository) Naming.lookup(serverURL);
        } else {
            throw new Exception("Repository not found: " + serverName);
        }
    }
    

    private static void listParts() throws Exception {
        List<Part> allParts = currentRepository.getAllParts();
        UserInterface.displayMessage("All Parts:");
        for (Part part : allParts) {
            UserInterface.displayMessage(part.getCode());
        }
    }

    private static void bindRepository(String repositoryName) throws Exception {
        PartRepository repository = (PartRepository) Naming.lookup(repositoryName);
        currentRepository = repository;
        UserInterface.displayMessage("Connected to repository: " + repositoryName);
    }

    private static void getPart(String partCode) throws Exception {
        Part part = currentRepository.getPart(partCode);
        if (part != null) {
            currentPart = part;
            UserInterface.displayMessage("Part retrieved: " + part.getName());
        } else {
            UserInterface.displayMessage("Part not found.");
        }
    }

    private static void showPart() throws RemoteException {
        if (currentPart != null) {
            UserInterface.displayMessage("Part details:");
            UserInterface.displayMessage("Code: " + currentPart.getCode());
            UserInterface.displayMessage("Name: " + currentPart.getName());
            UserInterface.displayMessage("Description: " + currentPart.getDescription());
        } else {
            UserInterface.displayMessage("No current part selected.");
        }
    }

    private static void clearSubPartsList() {
        currentSubParts = null;
        UserInterface.displayMessage("Sub-parts list cleared.");
    }

    private static void addSubPart() {
        if (currentPart != null) {
            if (currentSubParts == null) {
                currentSubParts = new ArrayList<>();
            }
            currentSubParts.add(currentPart);
            UserInterface.displayMessage("Sub-part added.");
        } else {
            UserInterface.displayMessage("No current part selected.");
        }
    }

    private static void addPart() {
        // Implement the logic to add a new part to the current repository
    }
}
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    private static PartRepository currentRepository;
    private static Part currentPart;
    private static Map<Part, Integer> currentSubParts = new HashMap<>();

    public static void main(String[] args) {
        new UserInterface();
        
        try {
            listAllServers();
            UserInterface.displayMessage("Choose one server");
            String serverName = UserInterface.getUserCommand();
            initialize(serverName);
        } catch (Exception e) {
            UserInterface.displayError("Erro initialize.", e);
            System.exit(1);
        }

        try {
            boolean running = true;

            while (running) {
                String command = UserInterface.getUserCommand();

                if (command.startsWith("bind ")) {
                    String newRepositoryName = command.substring(5);
                    bindRepository(newRepositoryName);
                } else if (command.equals("listp")) {
                    listParts();
                } else if (command.equals("lists")) {
                    listAllServers();
                } else if (command.startsWith("getp ")) {
                    String partCode = command.substring(5);
                    getPart(partCode);
                } else if (command.equals("showp")) {
                    showPart();
                } else if (command.equals("showsub")){
                    showSubParts();
                } else if (command.equals("clearlist")) {
                    clearSubPartsList();
                } else if (command.equals("addsubpart")) {
                    addSubPart();
                } else if (command.equals("addp")) {
                    addPart();
                }else if (command.equals("showinfo")){
                    showRepInfo();
                } else if (command.equals("quit")) {
                    UserInterface.displayMessage("Client terminated.");
                    System.exit(0);
                } else {
                    UserInterface.displayMessage("Invalid command.");
                }
            }
        } catch (Exception e) {
            UserInterface.displayError("Client exception.", e);
        }
    }
    
    public static void listAllServers() throws Exception{
        Registry registry = LocateRegistry.getRegistry();
        String[] registryList = registry.list();
        UserInterface.displayMessage("Available Servers: ");
        for(String element : registryList){
            UserInterface.displayMessage(element);
        }
    }

    public static void initialize(String serverName) throws Exception{
        String serverURL = "//localhost/" + serverName;
        currentRepository = (PartRepository) Naming.lookup(serverURL);
        UserInterface.displayMessage("Connected to repository: " + serverName);
    }

    private static void bindRepository(String repositoryName) {
        PartRepository repository;
        try {
            repository = (PartRepository) Naming.lookup(repositoryName);
            currentRepository = repository;
            UserInterface.displayMessage("Connected to repository: " + repositoryName);
        } catch (Exception e) {
            UserInterface.displayError("bind Exception.", e);
        }
    }
    
    private static void listParts() {
        List<Part> allParts;
        try {
            allParts = currentRepository.getAllParts();
            UserInterface.printLine();
            UserInterface.displayMessage("All Parts:");
            UserInterface.printLine();
            for (Part part : allParts) {
                part.printInfo();
            }
        } catch (RemoteException e) {
            UserInterface.displayError("listParts Exception.", e);
        }
    }

    private static void getPart(String partCode) {
        Part part;
        try {
            part = currentRepository.getPart(partCode);
            if (part != null) {
                currentPart = part;
                UserInterface.displayMessage("Part retrieved: " + part.getName());
            } else {
                UserInterface.displayMessage("Part not found.");
            }
        } catch (RemoteException e) {
            UserInterface.displayError("getPart Exception.", e);
        }
    }

    private static void showPart() {
        if (currentPart != null) {
            UserInterface.displayMessage("Current Part details:");
            try {
                currentPart.printInfo();
                UserInterface.displayMessage("Repository: " + currentRepository.getName());
                UserInterface.displayMessage("Subparts number: " + currentSubParts.size());
                UserInterface.printLine();
                UserInterface.displayMessage("Subparts list");
                showSubParts();
                UserInterface.printLine();
            } catch (RemoteException e) {
               UserInterface.displayError("showPart Exception.", e);
            }
        } else {
            UserInterface.displayMessage("No current part selected.");
        }
    }

    private static void showSubParts() {
        if (!currentSubParts.isEmpty()) {
            UserInterface.displayMessage("Current SubParts:");
            for (Map.Entry<Part, Integer> entry : currentSubParts.entrySet()) {
                Part subPart = entry.getKey();
                int quantity = entry.getValue();
    
                try {
                    UserInterface.displayMessage("Part: " + subPart.getName());
                    UserInterface.displayMessage("Code: " + subPart.getCode());
                    UserInterface.displayMessage("Description: " + subPart.getDescription());
                    UserInterface.displayMessage("Quantity: " + quantity);
                    UserInterface.printLine();
                } catch (RemoteException e) {
                    UserInterface.displayError("Error listing subParts", e);
                }
            }
        } else {
            UserInterface.displayMessage("No subparts available.");
        }
    }

    private static void clearSubPartsList() {
        currentSubParts = new HashMap<>();
        UserInterface.displayMessage("Sub-parts list cleared.");
    }

    private static void addSubPart() {
        if (currentPart != null) {
            UserInterface.displayMessage("Insert how many subparts you would like to add");
            int subpartsQuantity = Integer.parseInt(UserInterface.getUserCommand());
            if (subpartsQuantity > 0) {
                currentSubParts.put(currentPart, subpartsQuantity);
                UserInterface.displayMessage("Sub-part added.");
            } else {
                UserInterface.displayMessage("Invalid quantity, please enter a positive number.");
            }
        } else {
            UserInterface.displayMessage("No current part selected.");
        }
    }

    private static void addPart() {
        UserInterface.displayMessage("Enter the name of the new part:");
        String name = UserInterface.getUserCommand();

        UserInterface.displayMessage("Enter the description of the new part:");
        String description = UserInterface.getUserCommand();

        try {
            Part newPart = new PartImpl(name, description);
            
            if (!currentSubParts.isEmpty()) {
                for (Map.Entry<Part, Integer> entry : currentSubParts.entrySet()) {
                    Part subPart = entry.getKey();
                    int quantity = entry.getValue();
                    newPart.addSubParts(subPart, quantity);
                }
                newPart.setType(true);
            }
            currentRepository.addPart(newPart);
            currentPart = newPart;
            
            UserInterface.displayMessage("New part added with code: " + newPart.getCode());
        } catch (Exception e) {
            UserInterface.displayError("Error adding the part.", e);
        }
    }

    private static void showRepInfo() {
        try {
            UserInterface.displayMessage("Current Repository: " + currentRepository.getName());
            UserInterface.displayMessage("Parts Quantity: " + currentRepository.getPartsQuantity());
        } catch (RemoteException e) {
            UserInterface.displayError("Error getting parts quantity.", e);
        }
    }
}
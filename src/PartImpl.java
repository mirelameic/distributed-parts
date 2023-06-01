import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartImpl extends UnicastRemoteObject implements Part{
    private String code;
    private String name;
    private boolean agregada;
    private String description;
    private Map<Part, Integer> subParts;

    public PartImpl(String name, String description) throws RemoteException{
        this.code = generateUniqueCode();
        this.name = name;
        this.description = description;
        this.subParts = new HashMap<Part, Integer>();
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString();
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public boolean getType(){
        return agregada;
    }

    public String getDescription(){
        return description;
    }

    public Map<Part, Integer> getSubParts(){
        return subParts;
    }

    public void printInfo(){
        UserInterface.displayMessage("Part: " + this.getName());
        UserInterface.displayMessage("Code: " + this.getCode());
        UserInterface.displayMessage("Agregada: " + this.getType());
        UserInterface.displayMessage("Description: " + this.getDescription());
        UserInterface.printLine();
    }

    public void printSubParts() throws RemoteException{
        UserInterface.displayMessage("SubParts:");
        for (Map.Entry<Part, Integer> entry : subParts.entrySet()) {
            Part subParts = entry.getKey();
            int quantity = entry.getValue();
            UserInterface.displayMessage("Part: " + subParts.getName());
            UserInterface.displayMessage("Code: " + subParts.getCode());
            UserInterface.displayMessage("Description: " + subParts.getDescription());
            UserInterface.displayMessage("Quantity: " + quantity);
            UserInterface.printLine();
        }
    }
}
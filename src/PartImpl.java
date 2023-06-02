import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartImpl implements Part, Serializable{
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

    private String generateUniqueCode(){
        return UUID.randomUUID().toString();
    }

    public String getCode() throws RemoteException{
        return code;
    }

    public String getName() throws RemoteException{
        return name;
    }

    public boolean getType() throws RemoteException{
        return agregada;
    }

    public String getDescription() throws RemoteException{
        return description;
    }

    public Map<Part, Integer> getSubParts() throws RemoteException{
        return subParts;
    }

    public void setType(boolean newAgregada) throws RemoteException{
        this.agregada = newAgregada;
    }
    
    public void addSubParts(Part part, int subpartsQuantity) throws RemoteException{
        this.subParts.put(part, subpartsQuantity);
    }
    
    public void printInfo() throws RemoteException{
        UserInterface.displayMessage("Part: " + this.getName());
        UserInterface.displayMessage("Code: " + this.getCode());
        UserInterface.displayMessage("Agregada: " + this.getType());
        UserInterface.displayMessage("Description: " + this.getDescription());
        if(this.getType() == true){
            UserInterface.printLine();
        	printSubParts();	
        }
        UserInterface.printLine();
    }

    public void printSubParts() throws RemoteException{
        UserInterface.displayMessage("SubParts de " + getName() +":");
        for (Map.Entry<Part, Integer> entry : subParts.entrySet()) {
            Part subParts = entry.getKey();
            int quantity = entry.getValue();
            subParts.printInfo();
            UserInterface.displayMessage("Quantity: " + quantity);
            UserInterface.printLine();
        }
    }

}
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository{
    private String name;
    private Map<String, Part> parts;

    public PartRepositoryImpl(String name) throws RemoteException{
        this.name = name;
        parts = new HashMap<String, Part>();
    }

    public void addPart(Part part) throws RemoteException{
        parts.put(part.getCode(), part);
    }

    public Part getPart(String code) throws RemoteException{
        return parts.get(code);
    }

    public String getName() throws RemoteException{
        return this.name;
    }

    public List<Part> getAllParts() throws RemoteException{
        return new ArrayList<Part>(parts.values());
    }

    public void getPartsQuantity() throws RemoteException{
        if(!parts.isEmpty()){
            int totalQuantity = 0;
            
            for (Map.Entry<String, Part> entry : parts.entrySet()) {
                Part part = entry.getValue();
                UserInterface.displayMessage("Part: " + part.getName());
                
                if (part.getType()) {
                    Map<Part, Integer> subParts = part.getSubParts();
                    
                    for (Map.Entry<Part, Integer> subEntry : subParts.entrySet()) {
                        Part subPart = subEntry.getKey();
                        int quantity = subEntry.getValue();
                        UserInterface.displayMessage("  SubPart: " + subPart.getName());
                        UserInterface.displayMessage("  Quantity: " + quantity);
                        totalQuantity += quantity;
                    }
                }
                
                UserInterface.printLine();
            }
            
            UserInterface.displayMessage("Total quantity: " + totalQuantity);

        }else{
            UserInterface.displayMessage("Repository is empty.");
        }
    }
} 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartImpl extends UnicastRemoteObject implements Part{
    private String code;
    private String name;
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

    public String getDescription(){
        return description;
    }

    public Map<Part, Integer> getSubParts(){
        return subParts;
    }
}
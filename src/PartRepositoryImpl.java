import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
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

    public Map<String, Part> getParts() throws RemoteException{
        return this.parts;
    }

    public Integer getPartsQuantity() throws RemoteException{
        return parts.size();
    }
} 
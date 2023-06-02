import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface PartRepository extends Remote{
    void addPart(Part part) throws RemoteException;
    Part getPart(String code) throws RemoteException;
    String getName() throws RemoteException;
    Map<String, Part> getParts() throws RemoteException;
    Integer getPartsQuantity() throws RemoteException;
}
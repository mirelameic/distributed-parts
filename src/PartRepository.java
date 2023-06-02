import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote{
    void addPart(Part part) throws RemoteException;
    Part getPart(String code) throws RemoteException;
    String getName() throws RemoteException;
    List<Part> getAllParts() throws RemoteException;
    void getPartsQuantity() throws RemoteException;
}
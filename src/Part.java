import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Part extends Remote{
    String getCode() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    Map<Part, Integer> getSubParts() throws RemoteException;
    public void setType(boolean newAgregada) throws RemoteException;
    void printInfo() throws RemoteException;
    void printSubParts() throws RemoteException;
    void addSubParts(Part part, int subpartsQuantity) throws RemoteException;
}
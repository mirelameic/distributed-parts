import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Part extends Remote{
    String getCode() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    List<SubPart> getSubComponents() throws RemoteException;
}
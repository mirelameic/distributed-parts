import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
        // Obter informações de conexão do servidor que possui a peça
        String serverAddress = ServerRegistry.getServerAddress(code);
        try{
            // Estabelecer conexão com o servidor que possui a peça
            Registry registry = LocateRegistry.getRegistry(serverAddress);
            PartRepository remoteRepository = (PartRepository) registry.lookup(code);
            // Solicitar a peça ao servidor remoto
            return remoteRepository.getPart(code);
        }catch (NotBoundException | RemoteException e){
            e.printStackTrace();
            throw new RemoteException("Failed to retrieve part from remote repository.");
        }
    }


    public List<Part> getAllParts() throws RemoteException{
        return new ArrayList<Part>(parts.values());
    }
}
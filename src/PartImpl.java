import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartImpl extends UnicastRemoteObject implements Part{
    private String code;
    private String name;
    private String description;
    private List<SubPart> subComponents;

    public PartImpl(String code, String name, String description) throws RemoteException{
        this.code = code;
        this.name = name;
        this.description = description;
        this.subComponents = new ArrayList<SubPart>();
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

    public List<SubPart> getSubComponents(){
        return subComponents;
    }
}
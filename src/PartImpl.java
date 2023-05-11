import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PartImpl implements Part{
    private String code;
    private String name;
    private String description;
    private List<SubComponent> subComponents;

    public PartImpl(String code, String name, String description){
        this.code = code;
        this.name = name;
        this.description = description;
        this.subComponents = subComponents;
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

    public List<SubComponent> getSubComponents(){
        return subComponents;
    }
}
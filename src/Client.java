import java.rmi.Naming;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: Client <repository_name>");
            System.exit(1);
        }

        String repositoryName = args[0];

        try {
            PartRepository repository = (PartRepository) Naming.lookup(repositoryName);
            Part part = new PartImpl("Peça 1", "Descrição da Peça 1");
            repository.addPart(part);
            
            List<Part> allParts = repository.getAllParts();
            System.out.println("All Parts:");
            for (Part p : allParts) {
                System.out.println(p.getCode());
            }
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }
}
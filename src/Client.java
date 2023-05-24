import java.rmi.Naming;
import java.rmi.RemoteException;
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
            
            // Agora você pode usar os métodos remotos do repositório
            // por exemplo, adicionar uma peça
            Part part = new PartImpl("P1", "Peça 1", "Descrição da Peça 1");
            repository.addPart(part);
            
            // Recuperar uma peça pelo código
            Part retrievedPart = repository.getPart("P1");
            System.out.println("Retrieved Part: " + retrievedPart.getName());
            
            // Obter todas as peças do repositório
            List<Part> allParts = repository.getAllParts();
            System.out.println("All Parts:");
            for (Part p : allParts) {
                System.out.println(p.getName());
            }
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }
}
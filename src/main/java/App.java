import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        Inventory inv = new Inventory();

        FileReaders fr = new FileReaders(inv);
        try{
            fr.readStock("src/main/resources/initial_stock.csv");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println(inv);

    }
}

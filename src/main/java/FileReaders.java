import java.io.BufferedReader;
import java.io.FileReader;

public class FileReaders {
    Inventory inventory;

    public FileReaders(Inventory inventory) {
        this.inventory = inventory;
    }

    public void readStock(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            var split = line.split(";");
            if (split.length != 3)
                throw new Exception("NOT EXACTLY 3 THINGS");

            String type = split[0].toUpperCase();

            int amount;
            try{
                amount = Integer.parseInt(split[1]);
            }
            catch (NumberFormatException e){
                throw new Exception("NOT A NUMBER");
            }


            String shelf = split[2];

            if(amount < 0)
                throw new Exception("NOT ENOUGH AMOUNT");

            Product product;
            try{

                product = Product.valueOf(type);
            }catch (IllegalArgumentException e){
                throw new Exception("I DO NOT SUPPORT THIS PRODUCT TYPE");
            }

            Stock stock = new Stock(product, amount, shelf);
            inventory.addStock(stock);
    }
}

}

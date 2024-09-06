import java.io.BufferedReader;
import java.io.FileReader;

public class ManagementSystem {
    Inventory inventory;
    OrderHistory orderHistory;

    public ManagementSystem(Inventory inventory, OrderHistory orderHistory) {
        this.inventory = inventory;
        this.orderHistory = orderHistory;
    }

    public void readStock(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            var split = line.split(";");
            if (split.length != 3)
                throw new Exception("NOT EXACTLY 3 THINGS");

            int amount;
            try {
                amount = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new Exception("NOT A NUMBER");
            }

            if (amount < 0)
                throw new Exception("NOT ENOUGH AMOUNT");

            Product product;
            try {

                product = Product.valueOf(split[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new Exception("I DO NOT SUPPORT THIS PRODUCT TYPE");
            }
            inventory.add(new Stock(product, amount, split[2]));
        }
    }

    public void readOrders (String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            var split = line.split(";");
            if (split.length != 3)
                throw new Exception("NOT EXACTLY 3 THINGS");

            int id;
            try {
                id = Integer.parseInt(split[0]);
            } catch (NumberFormatException e) {
                throw new Exception("ID NOT A NUMBER");
            }

            int amount;
            try {
                amount = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new Exception("AMOUNT NOT A NUMBER");
            }

            Product product;
            try {
                product = Product.valueOf(split[2].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new Exception("I DO NOT SUPPORT THIS PRODUCT TYPE");
            }

            boolean orderWasFulfilled =  inventory.take(product, amount);
            if(orderWasFulfilled)
                orderHistory.add(id, new OrderLine(product, amount));
            else
                orderHistory.add(id, new OrderLine(product, amount, false));
        }
    }
}

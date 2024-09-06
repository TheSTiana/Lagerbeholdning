import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ManagementSystem {
    Inventory inventory;
    OrderHistory orderHistory;

    public ManagementSystem(Inventory inventory, OrderHistory orderHistory) {
        this.inventory = inventory;
        this.orderHistory = orderHistory;
    }
    public void addStockManually() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product type: ");
        String productType = readInput(scanner);
        Product product;
        try {
            product = Product.valueOf(productType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR - Invalid product type");
            return;
        }

        System.out.print("Enter quantity: ");
        String quantity = readInput(scanner);
        int amount;
        try {
            amount = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            System.out.println("ERROR - Invalid number");
            return;
        }
        if (amount < 0){
            System.out.println("ERROR - Negative quantity");
            return;
        }

        System.out.print("Enter Placement: ");
        String placement = readInput(scanner);

        Stock stock = new Stock(product, amount, placement);
        inventory.add(stock);
        System.out.println("SUCCESS - " + stock);
    }

    public void addOrderManually() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter order id: ");
        String idStr = readInput(scanner);
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ERROR - Invalid number");
            return;
        }
        if (id < 0) {
            System.out.println("ERROR - Negative quantity");
            return;
        }

        System.out.print("Enter quantity: ");
        String quantityStr = readInput(scanner);
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            System.out.println("ERROR - Invalid number");
            return;
        }
        if (quantity < 0) {
            System.out.println("ERROR - Negative quantity");
            return;
        }

        System.out.print("Enter product type: ");
        String productType = readInput(scanner);
        Product product;
        try {
            product = Product.valueOf(productType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR - Invalid product type");
            return;
        }

        boolean orderWasFulfilled =  inventory.take(product, quantity);
        if(orderWasFulfilled){
            OrderLine ol = new OrderLine(product, quantity);
            orderHistory.add(id, ol);
            System.out.println("SUCCESS - " + ol);
        }
        else{
            OrderLine ol = new OrderLine(product, quantity, false);
            orderHistory.add(id, ol);
            System.out.println("PROBLEM - " + ol);
        }
    }
    private String readInput(Scanner scanner) {
        return scanner.nextLine().trim().toLowerCase();
    }

    public void addStockFromFile(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            var split = line.split(";");
            if (split.length != 3)
                throw new Exception("NOT EXACTLY 3 THINGS");

            Product product;
            try {
                product = Product.valueOf(split[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new Exception("I DO NOT SUPPORT THIS PRODUCT TYPE");
            }

            int amount;
            try {
                amount = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                throw new Exception("NOT A NUMBER");
            }

            if (amount < 0)
                throw new Exception("NOT ENOUGH AMOUNT");

            inventory.add(new Stock(product, amount, split[2]));
        }
    }

    public void readOrdersFromFile(String path) throws Exception {
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

            if (amount < 0)
                throw new Exception("NOT ENOUGH AMOUNT");

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

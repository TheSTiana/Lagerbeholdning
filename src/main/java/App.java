import java.util.Scanner;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) {
        SupplyHistory sh = new SupplyHistory();
        Inventory inv = new Inventory(sh);
        OrderHistory oh = new OrderHistory();
        ManagementSystem ms = new ManagementSystem(inv, oh);
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        do {
            printMenu();
            String choice = readInput(scanner);
            switch (choice) {
                case "1":
                    System.out.println(inv);
                    break;
                case "2":
                    System.out.println(oh);
                    break;
                case "3":
                    System.out.println(sh);
                    break;
                case "4":
                    ms.addStockManually();
                    break;
                case "5":
                    ms.addOrderManually();
                    break;
                case "6":
                    try {
                        ms.addStockFromFile("src/main/resources/initial_stock.csv");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "7":
                    try {
                        ms.readOrdersFromFile("src/main/resources/orders.csv");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "8":
                    run = false;
                    break;
            }

        } while (run);

    }

    private static void printMenu() {
        System.out.println("1 - See Inventory");
        System.out.println("2 - See Order History");
        System.out.println("3 - See Supply History");
        System.out.println("4 - Manually add stock");
        System.out.println("5 - Manually add order");
        System.out.println("6 - Process stock");
        System.out.println("7 - Process orders");
        System.out.println("8 - Quit Program");
    }

    private static String readInput(Scanner scanner) {
        return scanner.nextLine().trim().toLowerCase();
    }
}

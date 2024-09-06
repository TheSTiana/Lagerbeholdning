public class App {
    public static void main(String[] args) throws Exception {
        SupplyHistory sh = new SupplyHistory();
        Inventory inv = new Inventory(sh);
        OrderHistory oh = new OrderHistory();

        ManagementSystem fr = new ManagementSystem(inv, oh);
        try{
            fr.readStock("src/main/resources/initial_stock.csv");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(inv);
        try{
            fr.readOrders("src/main/resources/orders.csv");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(oh);
        System.out.println(inv);
        System.out.println(sh);



    }
}

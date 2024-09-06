import java.util.ArrayList;

public class Order {
    private final int id;
    private final ArrayList<OrderLine> orderLines = new ArrayList<>();

    public Order(int id) {
        this.id = id;
    }

    public int getId (){return this.id;}

    private int getQuantity() {
        return orderLines.stream().mapToInt(OrderLine::getQuantity).sum();
    }

    public void add(OrderLine orderLine){
        orderLines.add(orderLine);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("INVENTORY:\n");
        for (OrderLine ol : orderLines) {
            String s = String.format("Ordre %d: Sendt %d stk. av vare [%s] %s", this.id, ol.getQuantity(), ol.getProduct(), ol.getProduct().getName());
            str.append(s).append("\n");
        }
        return str.toString();
    }
}

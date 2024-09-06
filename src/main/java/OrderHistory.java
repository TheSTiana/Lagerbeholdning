import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderHistory {
    HashMap<Integer, ArrayList<OrderLine>> orders = new HashMap<>();

    public void add(int id, OrderLine ol) {
        if (!orders.containsKey(id)) {
            ArrayList<OrderLine> ols = new ArrayList<>();
            ols.add(ol);
            orders.put(id, ols);
        } else {
            ArrayList<OrderLine> ols = orders.get(id);
            ols.add(ol);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("ORDER HISTORY:\n");
        for (Map.Entry<Integer, ArrayList<OrderLine>> entry : orders.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<OrderLine> value = entry.getValue();
            for (OrderLine ol : value) {
                if(ol.wasFulfilled()){
                    String s = String.format("Ordre %d: Sendt %d stk. av vare [%s] %s", key, ol.getQuantity(), ol.getProduct(), ol.getProduct().getName());
                    str.append(s).append("\n");
                }
                else{
                    String s = String.format("Ordre %d: På %d stk. av vare [%s] %s - IKKE UTFØRT", key, ol.getQuantity(), ol.getProduct(), ol.getProduct().getName());
                    str.append(s).append("\n");
                }
            }
        }
        return str.toString();
    }
}

import java.util.EnumMap;
import java.util.Map;

public class Inventory {
    EnumMap<Product, Stock> inventory;
    SupplyHistory supplyHistory;

    public Inventory(SupplyHistory supplyHistory) {
        this.inventory = new EnumMap<>(Product.class);
        this.supplyHistory = supplyHistory;
    }

    public void add(Stock stock) {
        // does not support different shelf placements
        inventory.put(stock.getProduct(), stock);
    }

    public boolean take(Product product, int orderAmount) {
        boolean hasProduct = inventory.containsKey(product);
        if (!hasProduct){
            return false;
        }

        Stock stock = inventory.get(product);

        boolean gotStock = stock.take(orderAmount);
        if (!gotStock){
            return false;
        }

        if(!stock.isUnderThreshold()){
            return true;
        }
        
        int threshold = product.getThreshold();
        int currentAmount = inventory.get(product).getAmount();
        if(threshold > currentAmount) {
            int refillAmount = product.getRefillNumber();
            stock.add(refillAmount);
            supplyHistory.addSupplierRecord(new SupplierRecord(refillAmount, product, currentAmount));
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("INVENTORY:\n");
        for (Map.Entry<Product, Stock> entry : inventory.entrySet()) {
            Product key = entry.getKey();
            Stock value = entry.getValue();
            String s = String.format("%s: %d", key.getName(), value.getAmount());
            str.append(s).append("\n");
        }
        return str.toString();
    }
}

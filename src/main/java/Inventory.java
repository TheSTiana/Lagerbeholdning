import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Stock> inventory = new ArrayList<>();

    public void addStock(Stock stock) {
        inventory.add(stock);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("INVENTORY:\n");
        for (Stock stock : inventory) {
            str.append(stock.toString()).append("\n");
        }
        return str.toString();
    }
}

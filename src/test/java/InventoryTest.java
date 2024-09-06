import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InventoryTest {
    @Test
    public void takeReduceStock(){
        SupplyHistory mock = Mockito.mock(SupplyHistory.class);
        Inventory inventory = new Inventory(mock);

        Product product = Product.A;
        Stock stock = new Stock(product, 50, "A1");

        inventory.add(stock);
        inventory.take(product, 5);

        assertThat(stock.getAmount()).isEqualTo(45);
    }

    @Test
    public void takeToThresholdIncreaseStockByRefillValue(){
        SupplyHistory mock = Mockito.mock(SupplyHistory.class);
        Inventory inventory = new Inventory(mock);

        Product product = Product.A;
        int threshold = product.getThreshold();

        int startingStock = 5;

        Stock stock = new Stock(product, threshold + startingStock, "A1");
        inventory.add(stock);

        inventory.take(product, threshold);

        int refillValue = product.getRefillNumber();

        assertThat(stock.getAmount()).isEqualTo(startingStock+refillValue);
    }
}

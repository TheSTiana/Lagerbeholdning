import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StockTest {

    @Test
    public void addStockIncreaseAmount(){
        Stock stock = new Stock(Product.A, 10, "A1");
        stock.add(10);
        assertThat(stock.getAmount()).isEqualTo(20);
    }

    @Test
    public void takeStockDecreaseAmount(){
        Stock stock = new Stock(Product.A, 10, "A1");
        stock.take(5);
        assertThat(stock.getAmount()).isEqualTo(5);
    }

    @Test
    public void takeMoreThanAmountInStockDoesNotDecreaseAmountAndReturnsFalse(){
        Stock stock = new Stock(Product.A, 10, "A1");
        boolean fulfilled = stock.take(20);

        assertThat(fulfilled).isFalse();
        assertThat(stock.getAmount()).isEqualTo(10);
    }

    @Test
    public void underThresholdTellsTruth(){
        Stock stock = new Stock(Product.A, 0, "A1");
        assertThat(stock.isUnderThreshold()).isTrue();
    }
}

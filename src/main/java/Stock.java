public class Stock {
    private final Product product;
    private int amount;
    private final String shelf;

    public Stock(Product product, int amount, String shelf) {
        this.product = product;
        this.amount = amount;
        this.shelf = shelf;
    }

    public Product getProduct() {return this.product;}
    public int getAmount() {return this.amount;}
    public String getShelf() {return this.shelf;}

    public boolean take(int orderAmount) {
        if(this.amountIsZero()) return false;
        if(orderAmount > this.amount) return false;
        this.amount -= orderAmount;
        return true;
    }

    public boolean amountIsZero(){
        return this.amount == 0;
    }

    @Override
    public String toString() {
        return String.format("Product: %s, Amount: %d, Shelf: %s", product, amount, shelf);
    }
}

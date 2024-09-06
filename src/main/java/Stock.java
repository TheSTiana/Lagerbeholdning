public class Stock {
    private final Product product;
    private int amount;
    private final String shelf;

    public Stock(Product product, int amount, String shelf) {
        this.product = product;
        this.amount = amount;
        this.shelf = shelf;
    }

    public Product getProduct() { return this.product; }
    public int getAmount() {return this.amount;}

    public boolean take(int orderAmount) {
        if(this.amount == 0) return false;
        if(orderAmount > this.amount) return false;
        this.amount -= orderAmount;
        return true;
    }

    public void add(int quantity){
        this.amount += quantity;
    }

    public boolean isUnderThreshold(){
        return this.amount < this.product.getThreshold();
    }

    @Override
    public String toString() {
        return String.format("%d of [%s]-%s at %s", amount, product, product.getName(), shelf);
    }
}

public class OrderLine {
    private final Product product;
    private final int quantity;
    private final boolean fulfilled;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.fulfilled = true;
    }
    public OrderLine(Product product, int quantity, boolean fulfilled) {
        this.product = product;
        this.quantity = quantity;
        this.fulfilled = false;
    }

    public Product getProduct() {return product;}
    public int getQuantity() { return quantity;}
    public boolean wasFulfilled() { return this.fulfilled; }
}

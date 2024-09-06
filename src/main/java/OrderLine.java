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
        this.fulfilled = fulfilled;
    }

    public Product getProduct() {return product;}
    public int getQuantity() { return quantity;}
    public boolean wasFulfilled() { return this.fulfilled; }

    @Override
    public String toString() {
        return String.format("Order for %d of [%s]-%s was", quantity, product, product.getName()) + (fulfilled ? " fulfilled" : " not fulfilled");
    }
}

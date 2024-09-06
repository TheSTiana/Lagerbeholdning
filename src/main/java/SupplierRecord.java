public class SupplierRecord {
    private final int suppliedQuantity;
    private final Product product;
    private final int originalQuantity;

    public SupplierRecord(int suppliedQuantity, Product product, int originalQuantity) {
        this.suppliedQuantity = suppliedQuantity;
        this.product = product;
        this.originalQuantity = originalQuantity;
    }

    @Override
    public String toString() {
        return String.format("Levert %d nye av vare {%s} %s. På lager etter leveranse: %d", this.suppliedQuantity, this.product, this.product.getName(), this.suppliedQuantity+this.originalQuantity);
    }
}

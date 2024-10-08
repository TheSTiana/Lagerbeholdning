public enum Product {
    A("phone", 10, 40),
    B("laptop", 5, 15),
    C("tablet", 5,5);

    private final String name;
    private final int threshold;
    private final int refillNumber;

    Product(String name, int threshold, int refillNumber) {
        this.name = name;
        this.threshold = threshold;
        this.refillNumber = refillNumber;
    }

    public String getName() {return name;}
    public int getThreshold() {return threshold;}
    public int getRefillNumber() {return refillNumber;}

}

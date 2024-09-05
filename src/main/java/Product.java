enum Product {
    A("phone"),
    B("laptop"),
    C("phone");

    String name;

    Product(String name) {
        this.name = name;
    }
    public String getName() {return name;}
}

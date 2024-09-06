import java.util.ArrayList;

public class SupplyHistory {
    private final ArrayList<SupplierRecord> supplierRecords = new ArrayList<>();

    public void addSupplierRecord(SupplierRecord supplierRecord) {
        supplierRecords.add(supplierRecord);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("SUPPLY HISTORY:\n");
        for (SupplierRecord supplierRecord : supplierRecords) {
            str.append(supplierRecord.toString()).append("\n");
        }
        return str.toString();
    }
}

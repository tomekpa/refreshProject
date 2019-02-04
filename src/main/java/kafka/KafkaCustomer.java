package kafka;

//JSUT EXAMPLE CUSTOMMER CLASS
public class KafkaCustomer {
    private int customerID;
    private String customerName;

    public KafkaCustomer(int ID, String name) {
        this.customerID = ID;
        this.customerName = name;
    }

    public int getID() {
        return customerID;
    }

    public String getName() {
        return customerName;
    }
}

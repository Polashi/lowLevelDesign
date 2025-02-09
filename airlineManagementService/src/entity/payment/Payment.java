package entity.payment;

public class Payment {
    private final String paymentId;
    private final String paymentMethod;
    private final int amount;
    private PaymentStatus status;

    public Payment(String paymentId, String paymentMethod, int amount) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }
    public void processPayment(){
        this.status = PaymentStatus.SUCCESS;
    }
}

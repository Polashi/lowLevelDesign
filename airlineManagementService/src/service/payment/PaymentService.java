package service.payment;

import entity.payment.Payment;

public class PaymentService {
    private static PaymentService instance;
    private PaymentService(){

    }
    public static synchronized PaymentService getInstance(){
        if(instance == null){
            return new PaymentService();
        }
        return instance;
    }

    public void processPayment(Payment payment){
        payment.processPayment();
    }
}

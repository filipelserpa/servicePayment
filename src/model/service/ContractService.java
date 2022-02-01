package model.service;

import model.entities.Contract;
import model.entities.Installment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService){
        this.onlinePaymentService = onlinePaymentService;

    }

    public void processContract(Contract contract, int months) {
        List<Double> list = new ArrayList<>();
        double basicQuota = contract.getTotalValue()/months;
        for (int i = 1; i <= months; i++){
            double updateQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
            double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
            Date dueDate = addMonths(contract.getDate(), i);
            contract.getInstallments().add(new Installment(dueDate, fullQuota));
        }
    }
    private Date addMonths (Date date, int N) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, N);
        return calendar.getTime();
    }
}
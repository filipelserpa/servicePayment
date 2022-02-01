package application;

import model.entities.Contract;
import model.entities.Installment;
import model.service.PaypalService;
import model.service.ContractService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Date dd/MM/yyyy: ");
        Date date = sdf.parse(sc.next());
        System.out.print("Total Value: ");
        double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter with number installments: ");
        int N = sc.nextInt();

        ContractService cs = new ContractService (new PaypalService());

        cs.processContract(contract, N);

        System.out.println("Installments: ");

        for(Installment it: contract.getInstallments()){
            System.out.println(it);
        }
        sc.close();
    }
}

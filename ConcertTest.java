import java.util.Scanner;
class ConcertTest {
    Scanner scnr = new Scanner(System.in);
    private Boolean testing = true;
    public static void main(String[] args) {
        System.out.println("Testing starts");
        int errors = 0;

        // instantiating a concert 
        Concert c1 = new Concert(10,19,2019, 
                "Keith Urban & Kelsea Ballerini",
                "Van Andel Arena");

        // buy 10 tickets in the floor section
        c1.buyTickets ('F', 10, 1800.0);
        if(c1.getTotalSales() != 1800.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 1800.0");
        }

        // the floor tickets available should be 390 
        if(c1.getAvailableFloorTickets() != 390){
            errors++;
            System.out.println("ERROR: available floor tickets should be 390");
        }

        // buy 1 ticket in the floor section
        // testing errors in input parameters
        c1.buyTickets ('F', -1, 180.0);
        if(c1.getTotalSales() != 1800.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 1800.0");
        }

        // invoking the method to simulate a company buying 50
        // individual tickets in random sections
        c1.simulateCompanyBuyingTickets(50);

        // invoking the report method
        c1.printReport();

        System.out.println("Testing Complete. Number of errors: " + errors);

    }



    }
    
}
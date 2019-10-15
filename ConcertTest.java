class ConcertTest {
    private int errors = 0;
    private int totalErrors = 0;
    public static void main(String[] args) {
        ConcertTest activeTest = new ConcertTest();

        System.out.println("Testing starts");

        activeTest.purchaseTesting();
        activeTest.formatTesting();
    }

    private void purchaseTesting() {
        // create purchaseTesting as an object of the concert class to test purchase methods
        Concert purchaseTesting = new Concert(12,30,2019, "Jeff Dunham", "Van Andel Arena");



        // buy 10 tickets in the floor section
        purchaseTesting.buyTickets ('F', 10, 1800.0);
        if (purchaseTesting.getTotalSales() != 1800.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 1800.0");
        }

        // buy 10 tickets in the lower section
        purchaseTesting.buyTickets ('L', 10, 999.0);
        if (purchaseTesting.getTotalSales() != 2799.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 2799.0");
        }

        // buy 10 tickets in the upper section
        purchaseTesting.buyTickets ('U', 10, 299.0);
        if (purchaseTesting.getTotalSales() != 3098.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 3098.0");
        }

        // the floor tickets available should be 390 
        if (purchaseTesting.getAvailableFloorTickets() != 390){
            errors++;
            System.out.println("ERROR: " + purchaseTesting.getAvailableFloorTickets() + " available floor tickets should be 390");
        }

        // the lower tickets available should be 290
        if (purchaseTesting.getAvailableLowerTickets() != 290){
            errors++;
            System.out.println("ERROR: " + purchaseTesting.getAvailableLowerTickets() + " available lower tickets should be 390");
        }

        // the upper tickets available should be 290
        if (purchaseTesting.getAvailableLowerTickets() != 290){
            errors++;
            System.out.println("ERROR: " + purchaseTesting.getAvailableUpperTickets() + " available upper tickets should be 490");
        }
        


        // buy 1 ticket in the floor section
        // testing errors in input parameters
        purchaseTesting.buyTickets ('F', -1, 180.0);
        if (purchaseTesting.getTotalSales() != 3098.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 3098.0");
        }

        // buy 1 ticket in the lower section
        // testing errors in input parameters
        purchaseTesting.buyTickets ('L', -1, 99.0);
        if (purchaseTesting.getTotalSales() != 3098.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 3098.0");
        }

        // buy 1 ticket in the upper section
        // testing errors in input parameters
        purchaseTesting.buyTickets ('U', -1, 29.9);
        if (purchaseTesting.getTotalSales() != 3098.0){
            errors++;
            System.out.println("ERROR: Total Sales should be 3098.0");
        }



        // call the method to simulate a company buying 50
        // individual tickets in random sections
        purchaseTesting.simulateCompanyBuyingTickets(200);

        // call the report method
        purchaseTesting.printReport();
        totalErrors = totalErrors + errors;
        System.out.println("purchaseTesting() Complete. Number of errors: " + errors + " Total Errors: " + totalErrors);
        System.out.println("******************************************************");
    }
    







    private void formatTesting() {
        // create formatTesting as an object of the concert class, Using Leap Year
        Concert formatTesting = new Concert("2/29/2020", "Joe Rogan", "Van Andel Arena");
        String f1 = "2/29/2020";
        String f2 = "02/29/2020";
        String f3 = "Feb 29, 2020";
        String f4 = "Feburary 29, 2020";

        // Tests all date formats and checks for errors
        if (formatTesting.formatDate(1) == f1) {
            System.out.println("ERROR: Date \"" + formatTesting.formatDate(1) + "\" format 1 not correct"); 
            ++errors;
        }
        if (formatTesting.formatDate(2) == f2) {
            System.out.println("ERROR: Date \"" + formatTesting.formatDate(2) + "\" format 2 not correct"); 
            ++errors;
        }
        if (formatTesting.formatDate(3) == f3) {
            System.out.println("ERROR: Date \"" + formatTesting.formatDate(3) + "\" format 3 not correct");
            ++errors;
        }
        if (formatTesting.formatDate(4) == f4) {
            System.out.println("ERROR: Date \"" + formatTesting.formatDate(4) + "\" format 4 not correct");
            ++errors;
        }
    
        // Test to make sure Leap Year method is working
        formatTesting.printLeapYear();

        formatTesting.printReport();
        totalErrors = totalErrors + errors;
        System.out.println("formatTesting() Testing Complete. Number of errors: " + errors + " Total Errors: " + totalErrors);
        System.out.println("******************************************************");
    }
}
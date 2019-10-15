import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Random;

public class Concert {
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
    DecimalFormat decimal = new DecimalFormat("00");
    Random rand = new Random();
    private int day;
    private int month;
    private int year;
    private String monthString;
    private String date;
    private String artistName;
    private String venue;
    private int upperTickets;
    private int lowerTickets;
    private int floorTickets;
    private double concertTotalSales;

    // Used for purchasing tickets
    private char ticketType;
    private int numTickets;
    private double pmt;

    private final double UPPER_PRICE = 29.90;
    private final double LOWER_PRICE = 99.0;
    private final double FLOOR_PRICE = 180.0;
    private final int TOTAL_UPPER_TICKETS = 300;
    private final int TOTAL_LOWER_TICKETS = 300;
    private final int TOTAL_FLOOR_TICKETS = 400;
    
    public Concert() {  
        day = 8;
        month = 9;
        year = 2019;
        date = (day + "/" + month + "/" + year);
        artistName = "Jonas Brothers";
        venue = "Van Andel Arena";
        concertTotalSales = 0;
        upperTickets = TOTAL_UPPER_TICKETS;
        lowerTickets = TOTAL_LOWER_TICKETS;
        floorTickets = TOTAL_FLOOR_TICKETS;
    }
    
    public Concert(int day, int month, int year, String artistName, String venue) {
        setDate(day, month, year);
        date = (day + "/" + month + "/" + year);
        this.artistName = artistName;
        this.venue = venue;
        concertTotalSales = 0;
        upperTickets = TOTAL_UPPER_TICKETS;
        lowerTickets = TOTAL_LOWER_TICKETS;
        floorTickets = TOTAL_FLOOR_TICKETS;
    }

    public Concert(String date, String artistName, String venue) {
        this.date = date;
        parseDate(date);
        this.artistName = artistName;
        this.venue = venue;
        concertTotalSales = 0;
        upperTickets = TOTAL_UPPER_TICKETS;
        lowerTickets = TOTAL_LOWER_TICKETS;
        floorTickets = TOTAL_FLOOR_TICKETS;
    }

    private void parseDate(String date) {   // Parse the date into a dates[] array and sets each variable equal to the coresponding index
        Boolean bool = true;
        String[] dates =  date.split("/");
        for (int i = 0; i <= 3; i++) {
            switch(i) {
                case 0: // Makes sure month is above 0
                if (isValid(Integer.parseInt(dates[i]))) month = Integer.parseInt(dates[i]);
                else {
                    bool = false;
                    System.out.println("ERROR: Month is a negative number and is invalid");
                }
                break;

                case 1: // Makes sure day is above 0
                if (isValid(Integer.parseInt(dates[i]))) day = Integer.parseInt(dates[i]);
                else {
                    bool = false;
                    System.out.println("ERROR: Day is a negative number and is invalid");
                }
                break;

                case 2: // Makes sure year is above 0
                if (isValid(Integer.parseInt(dates[i]))) year = Integer.parseInt(dates[i]);
                else {
                    bool = false;
                    System.out.println("ERROR: Year is a negative number and is invalid");
                }
                break;
            }
        }
        if (bool) setDate(month, day, year); // If the date doesnt contain a negative number, check for valid dates in isDateValid() method
        else {
            System.out.println("** Please change the date and restart the program **");
            
        }
    }
    
    public void buyTickets(char ticketType, int numTickets, double pmt) {   // Called to buy tickets, adjusts instance variables accordingly 
        if (isValid(numTickets) && isValid(pmt)) {
            switch(ticketType) {
                case 'U':
                    if ((upperTickets - numTickets) >= 0 && isValid(numTickets)){   // If there are still tickets left
                        if ((UPPER_PRICE * numTickets) <= pmt) {                    // If the price payed is correct
                            concertTotalSales = concertTotalSales + pmt;            // Adds to total concert sales
                            upperTickets = upperTickets - numTickets;               // Subtracts from number of tickets available
                            //System.out.println("Transaction: Number tickets Upper Section: " + numTickets + ", total: " + currency.format(pmt));
                        } else System.out.println("ERROR: " + pmt + " is not enought money for " + numTickets + " tickets in the Upper Section.");
                    }else System.out.println("ERROR: There are only " + upperTickets + " tickets left in the upper section, please pick a different amount of tickets or a different section");
                break;
                case 'L':
                    if ((lowerTickets - numTickets) >= 0 && isValid(numTickets)){   // If there are still tickets left
                        if (pmt >= (LOWER_PRICE * numTickets)) {                    // If the price payed is correct
                            concertTotalSales = concertTotalSales + pmt;            // Adds to total concert sales
                            lowerTickets = lowerTickets - numTickets;               // Subtracts from number of tickets available
                            //System.out.println("Transaction: Number tickets Lower Section: " + numTickets + ", total: " + currency.format(pmt));
                        } else System.out.println("ERROR: " + pmt + " is not enought money for " + numTickets + " tickets in the Lower Section.");
                    }else System.out.println("ERROR: There are only " + lowerTickets + " tickets left in the Lower Section, please pick a different amount of tickets or a different section");
                break;
                case 'F':
                    if ((floorTickets - numTickets) >= 0 && isValid(numTickets)){   // If there are still tickets left
                        if (pmt >= (UPPER_PRICE * numTickets)) {                    // If the price payed is correct
                            concertTotalSales = concertTotalSales + pmt;            // Adds to total concert sales
                            floorTickets = floorTickets - numTickets;               // Subtracts from number of tickets availble
                            //System.out.println("Transaction: Number tickets Floor Section: " + numTickets + ", total: " + currency.format(pmt));
                        } else System.out.println("ERROR: " + pmt + " is not enought money for " + numTickets + " tickets in the Floor Section.");
                    }else System.out.println("ERROR: There are only " + floorTickets + " tickets left in the Floor Section, please pick a different amount of tickets or a different section");
                break;
                default:
                    System.out.println("ERROR: Invalid Ticket Type");           // If ticketType isn't correct, prints ERROR message
            }
        } else System.out.println("Input is less than 0");
    }

    public void setArtist(String artistName) {
        this.artistName = artistName;
    } 

    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    public void setDate(int month, int day, int year) {
        if (isDateValid(month, day, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else System.out.println("ERROR: Date "  + year +  " is not valid");
    }

    public String getArtist() {
        return artistName;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public double getTicketPrice(char ticketType) {
        switch(ticketType) {
        case 'U': return UPPER_PRICE;
        case 'L': return LOWER_PRICE;
        case 'F': return FLOOR_PRICE;
        default : System.out.println("ERROR: Incorrect ticket type"); return 0.0;
        }
    }
    
    public int getAvailableUpperTickets() {
        return upperTickets;
    }
    
    public int getAvailableLowerTickets() {
    return lowerTickets;
    }
    
    public int getAvailableFloorTickets() {
        return floorTickets;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public double getTotalSales() {
        return concertTotalSales;
    }

    public String getDate() {
        return date;
    }

    public void printReport() {     // Prints a report of all concert instance variables
        formatDate(3);
        System.out.println("Concert Report");
        System.out.println("=====================");
        System.out.print("Artist:");
        System.out.println("\t" + artistName);
        System.out.print("Venue:");
        System.out.println("\t" + venue);
        System.out.print("Date:");
        System.out.println("\t" + date);
        System.out.println();
        System.out.println("Tickets sold:");
        System.out.println("=============");
        System.out.print("Upper: " + (TOTAL_UPPER_TICKETS - upperTickets));
        System.out.println("\t" + currency.format((TOTAL_UPPER_TICKETS - upperTickets) * UPPER_PRICE));     // Use currency object to format upper ticket sales
        System.out.print("Lower: " + (TOTAL_LOWER_TICKETS - lowerTickets));
        System.out.println("\t" + currency.format((TOTAL_LOWER_TICKETS - lowerTickets) * LOWER_PRICE));     // Use currency object to format lower ticket sales
        System.out.print("Floor: " + (TOTAL_FLOOR_TICKETS - floorTickets));
        System.out.println("\t" + currency.format((TOTAL_FLOOR_TICKETS - floorTickets) * FLOOR_PRICE));     // Use currency object to format floor ticket sales
        System.out.println("=============");
        System.out.print("Total Sales:");
        System.out.println("\t" + currency.format(concertTotalSales));
    }

    public void printDay() {
        System.out.println("Day: " + day);
    }

    public void printMonth() {
        System.out.println("Month: " + month);
    }
    
    public void printYear() {
        System.out.println("Year: " + year);
    }

    public void printLeapYear() {
        if (isLeapYear(year)) System.out.println(year + " is a leap year");
        else System.out.println(year + " is not a leap year");
    }

    private boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
          } else if (year % 400 == 0) {
            return true;
          } else if (year % 100 == 0) {
            return false;
          } else {
            return true;
          }
    }

    private boolean isDateValid(int month, int day, int year) {
        if ((month >= 1 && month <= 12) && year > 0) {
            switch (month) {

                case 1: // All months with 31 days
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: if (day >= 1 && day <= 31) return true;
                break;

                    
                case 4: // All months with 30 days
                case 6:
                case 9:
                case 11: if (day >= 1 && day <= 30) return true;
                break;

                case 2: // Feburary has differnt days depending on leap year status
                    if (isLeapYear(year) && (day >= 1 && day <= 29)) return true;
                    else if (!isLeapYear(year) && (day >= 1 && day <= 28)) return true;
                break;
            }
        }
        return false;
    }

    private boolean isValid(double num) {
        if (num > 0) return true;
        else return false;
    }

    public String formatDate(int format) {
        switch (format) {
            case 1: return (day + "/" + month + "/" + year);
            case 2: return (decimal.format(day) + "/" + decimal.format(month) + "/" + year);
            case 3: return (monthString(month).substring(0,3) + " " + day + ", " + year);
            case 4: return (monthString(month) + " " + day + ", " + year);
        }
        return "ERROR: Incorrect format type";
    }

    private String monthString(int month) {
        switch (month) {
            case 1:  monthString = "January";       break;
            case 2:  monthString = "February";      break;
            case 3:  monthString = "March";         break;
            case 4:  monthString = "April";         break;
            case 5:  monthString = "May";           break;
            case 6:  monthString = "June";          break;
            case 7:  monthString = "July";          break;
            case 8:  monthString = "August";        break;
            case 9:  monthString = "September";     break;
            case 10: monthString = "October";       break;
            case 11: monthString = "November";      break;
            case 12: monthString = "December";      break;
            default: monthString = "Invalid month"; break;
        }
        return monthString;
    }

	public void simulateCompanyBuyingTickets(int numberTickets) {
        for (int i = 1; i <= numberTickets; ++i) {
            
            switch (rand.nextInt(4)) {
                case 1: ticketType = 'U'; pmt = UPPER_PRICE; break;
                case 2: ticketType = 'L'; pmt = LOWER_PRICE; break;
                case 3: ticketType = 'F'; pmt = FLOOR_PRICE; break;
            }
            numTickets = 1;
            buyTickets(ticketType, numTickets, pmt);
        }
    }

}
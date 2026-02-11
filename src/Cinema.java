import java.util.Scanner;

public class Cinema {
    private final int row;
    private final int seatPerRow;
    private String[][] layoutCinema;
    private int currentIncome = 0;

    public Cinema(int row,int seatPerRow) {
        this.row = row;
        this.seatPerRow = seatPerRow;
        this.layoutCinema = generateLayoutCinema();
    }

    public String[][] generateLayoutCinema() {
        this.layoutCinema = new String[this.row + 1 ][this.seatPerRow + 1];
        this.layoutCinema[0][0] = " ";
        for (var i = 0; i < this.layoutCinema.length; i++) {
            for ( var j = 0 ; j < this.layoutCinema[i].length; j++) {
                if (i == 0 ) {
                    this.layoutCinema[i][j] = String.valueOf(j);
                }
                else {
                    this.layoutCinema[i][j] = "S";
                }
            }
            this.layoutCinema[i][0] = String.valueOf(i) ;
        }
        return this.layoutCinema;
    }

    public void printLayoutCinema() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cinema: \n");
        String[][] layoutCinema = this.layoutCinema;

        for (String[] strings : layoutCinema) {
            for (String seat : strings) {
                sb.append(seat).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public int getTotalPrice() {
        int totalSeat = this.row * this.seatPerRow;
        int frontHalfOfSeat = this.row  / 2 ;
        int backHalfOfSeat = this.row - frontHalfOfSeat;
        return totalSeat < 60 ?  (totalSeat * 10) : ( (frontHalfOfSeat * this.seatPerRow * 10)  + (backHalfOfSeat * this.seatPerRow * 8));
    }

    public void sellPriceBySeatInRow (Scanner scanner) {
        int totalSeat = this.row * this.seatPerRow;
        int frontHalfOfSeat = this.row / 2;
        int rowNumber = 0;
        int seatInRow = 0;

        while (rowNumber < 1 || rowNumber > this.row || seatInRow < 1 || seatInRow > this.seatPerRow) {
            System.out.println("Enter a row number (1-" + this.row + "):");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat in that row (1-" + this.seatPerRow + "):");
            seatInRow = scanner.nextInt();
            if (rowNumber < 1 || rowNumber > this.row || seatInRow < 1 || seatInRow > this.seatPerRow) {
                System.out.println("Wrong input!");
            }
        }

        int priceBySeatInRow = totalSeat >= 60 ? (rowNumber <= frontHalfOfSeat ? 10 : 8) : 10;

        boolean isSellSuccess = setLayoutCinemaBySeatSell(rowNumber, seatInRow);
        if (isSellSuccess) {
            this.currentIncome += priceBySeatInRow;
        }
        System.out.println("Ticket Price: " + priceBySeatInRow + "$");
    }

    public boolean setLayoutCinemaBySeatSell(int row, int seatInRow) {
        boolean isSellSuccess = false ;
        if (this.layoutCinema[row][seatInRow].equals("S")) {
                this.layoutCinema[row][seatInRow] = "B";
                isSellSuccess = true;
            }
            else {
                System.out.println("That ticket has already been purchased!");
            }
            return isSellSuccess;
        }
    public void statisticsCinema () {
        int sellTicket = findSellTicket();
        String percentage = String.format("%.2f", (double) sellTicket / (this.row * this.seatPerRow) * 100) + "%";
        System.out.printf("Number of purchased tickets: %s \n Percentage: %s \n Current income: %s \n Total income: %s \n", sellTicket,percentage,"$" + this.currentIncome, getTotalPrice() + "$");
    }
    private int findSellTicket () {
        int sellTicket = 0 ;
        for (String[] row : this.layoutCinema) {
            for ( String seat : row) {
                if ( seat.equals("B")) sellTicket++;
            }
        }
        return sellTicket;
    }
    public void run(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit");
            System.out.print("Enter your choice: ");
            int menu = scanner.nextInt();
            switch (menu) {
                case 1 -> printLayoutCinema();
                case 2 -> sellPriceBySeatInRow(scanner);
                case 3 -> statisticsCinema();
                case 0 -> {
                    System.out.println("Thank you for using Cinema System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

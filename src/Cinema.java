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
        this.layoutCinema = new String[this.row + 1 ][this.seatPerRow + 1 ];
        for (var i = 0; i < this.layoutCinema.length; i++) {
            for ( var j = 0 ; j < this.layoutCinema[i].length; j++) {
                if (i == 0 ) {
                    this.layoutCinema[i][j] = String.valueOf(j);
                }
                else {
                    this.layoutCinema[i][j] = "S";
                    this.layoutCinema[0][0] = " ";
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

    public void sellPriceBySeatInRow () {
        int totalSeat = this.row * this.seatPerRow;
        int frontHalfOfSeat = this.row  / 2 ;
        int rowNumber = 0;
        int seatInRow = 0;
        Scanner scanner = new Scanner(System.in);

        while ( rowNumber > this.row || seatInRow > this.seatPerRow || rowNumber < 1 || seatInRow < 1) {
            if (rowNumber > this.row || seatInRow > this.seatPerRow ) {
                System.out.println("Wrong input!");
            }
            System.out.println("Enter a row number");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat in row in that row ");
            seatInRow = scanner.nextInt();
        }

        int priceBySeatInRow = totalSeat >= 60 ?  (row <= frontHalfOfSeat ? 10 : 8 ) : 10;

        boolean isSellSuccess =  setLayoutCinemaBySeatSell(rowNumber,seatInRow);
        if (isSellSuccess) {
            this.currentIncome += priceBySeatInRow;
           }
        System.out.println("Ticket Price : " + String.valueOf(priceBySeatInRow + "$"));
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
    public void run() {
        boolean flag = false;
        while (!flag) {
            System.out.println("\n 1.Show the seats \n 2.Buy a ticket \n 3. Statistics \n 0. Exit");

            Scanner scanner = new Scanner(System.in);
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    printLayoutCinema();
                    break;
                case 2:
                    sellPriceBySeatInRow();
                    break;
                case 3:
                    statisticsCinema();
                    break;
                case 0:
                    flag = true;
                    break;
                default:
                    break;
            }
        }
    }
}

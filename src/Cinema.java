import java.util.Arrays;

public class Cinema {
    private int row;
    private int seatPerRow;
    private int totalPrice;

    public int getSeatPerRow() {
        return seatPerRow;
    }

    public void setSeatPerRow(int seatPerRow) {
        this.seatPerRow = seatPerRow;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Cinema(int row,int seatPerRow) {
        this.row = row;
        this.seatPerRow = seatPerRow;
    }

    public String[][] generateLayoutCinema() {
        String[][] layoutCinema = new String[this.row + 1 ][this.seatPerRow + 1 ];
        for (var i = 0; i < layoutCinema.length; i++) {
            for ( var j = 0 ; j < layoutCinema[i].length; j++) {
                if (i == 0 ) {
                    layoutCinema[i][j] = String.valueOf(j);
                }
                else {
                    layoutCinema[i][j] = "S";
                    layoutCinema[0][0] = " ";
                }
            }
            layoutCinema[i][0] = String.valueOf(i) ;

        }
        return layoutCinema;

    }

    public void printLayoutCinema() {
        StringBuilder sb = new StringBuilder();
        String[][] layoutCinema = generateLayoutCinema();

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

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

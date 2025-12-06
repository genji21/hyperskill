import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = 0 ,seatPerRow = 0;
        while ( row > 9 || seatPerRow > 9 || row < 1 || seatPerRow < 1) {
            System.out.println("Enter the number of row");
            row = scanner.nextInt();
            System.out.println("Enter the number of seats in each row");
            seatPerRow = scanner.nextInt();
        }
        Cinema cinema  = new Cinema(row,seatPerRow);
        cinema.run();

    }
}

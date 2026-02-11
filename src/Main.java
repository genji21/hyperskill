import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = 0, seatPerRow = 0;
        while (row < 1 || row > 9 || seatPerRow < 1 || seatPerRow > 9) {
            System.out.println("Enter the number of rows (1-9):");
            row = scanner.nextInt();
            System.out.println("Enter the number of seats in each row (1-9):");
            seatPerRow = scanner.nextInt();
            if (row < 1 || row > 9 || seatPerRow < 1 || seatPerRow > 9) {
                System.out.println("Invalid input! Please enter numbers between 1 and 9.");
            }
        }
        Cinema cinema = new Cinema(row, seatPerRow);
        cinema.run(scanner);
        scanner.close();
    }
}

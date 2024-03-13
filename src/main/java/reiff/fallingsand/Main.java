package reiff.fallingsand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sand sand = new Sand(50, 10);

        sand.randomSand(50);


        System.out.println("Press Enter to make sand fall. Press 'q' to quit.");

        while (true) {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                sand.fall();
                System.out.println(sand);
            } else if (input.equalsIgnoreCase("q")) {
                break;
            }
        }

        scanner.close();
    }

}

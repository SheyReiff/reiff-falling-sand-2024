package reiff.fallingsand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sand sand = new Sand(10, 50);

        sand.randomSand(50);

        displaySand(sand);

        System.out.println("Press Enter to make sand fall. Press 'q' to quit.");

        while (true) {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                sand.fall();
                displaySand(sand);
            } else if (input.equalsIgnoreCase("q")) {
                break;
            }
        }

        scanner.close();
    }

    private static void displaySand(Sand sand) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sand.toString());
    }
}

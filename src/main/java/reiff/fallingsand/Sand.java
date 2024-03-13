package reiff.fallingsand;

import java.util.Random;

public class Sand {
    private int[][] field;
    private Random random = new Random();

    public Sand(int width, int height) {
        field = new int[height][width];
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }


    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * Sets the value in the field to be 1
     */

    public void put(int x, int y) {
        field[y][x] = 1;
    }

    public void fall() {
        // moves all sand down one square
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1) {
                    if (field[y + 1][x] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x] = 1;
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? +1 : -1;
                    int direction2 = rightFirst ? -1 : +1;

                    if (field[y + 1][x + direction1] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction1] = 1;
                    } else if (field[y + 1][x + direction2] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction2] = 1;
                    }
                }
            }
        }
    }

    public void randomSand(int n) {
        int freeSpaces = countFreeSpaces();

        if (freeSpaces == 0) {
            throw new IllegalStateException("Field is completely filled.");
        }

        if (freeSpaces < n) {
            System.out.println("Warning: Not enough free spaces for " + n + " sand particles.");
            n = freeSpaces;
        }

        for (int i = 0; i < n; i++) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(field[0].length);
                int y = random.nextInt(field.length);
                if (field[y][x] == 0) {
                    put(x, y);
                    placed = true;
                }
            }
        }
    }

    private int countFreeSpaces() {
        int count = 0;
        for (int[] row : field) {
            for (int cell : row) {
                if (cell == 0) {
                    count++;
                }
            }
        }
        return count;
    }

}






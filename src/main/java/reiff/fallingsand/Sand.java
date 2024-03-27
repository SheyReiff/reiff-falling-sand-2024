package reiff.fallingsand;

import java.util.Random;

public class Sand {
    private int[][] field;
    private Random random = new Random();

    public Sand(int width, int height) {
        this(width, height, new Random());
    }

    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
    }

    public int getWidth() {
        return field[0].length;
    }

    public int getHeight() {
        return field.length;
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

    public void resize(int width, int height) {

        int[][] newField = new int[height][width];
        for (int y = 0; y < Math.min(field.length, newField.length); y++) {
            System.arraycopy(field[y], 0, newField[y], 0, Math.min(field[y].length, newField[y].length));
        }
        field = newField;
    }

    public void load(String s) {
        int y = 0;
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\n' -> {
                    y++;
                    x = 0;
                }
                case '1' -> {
                    field[y][x] = 1;
                    x++;
                }
                default -> {
                    field[y][x] = 0;
                    x++;
                }
            }
        }
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


    /**
     * Moves the sand form x1, y1, to x2, y2
     *
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @return probability that an empty spot in the rectangle will be sand
     */

    public void put(int startX, int startY, int width, int height, double probability) {

        for (int y = startY; y < startY + width; y++) {
            for (int x = startX; x < startX + height; x++) {
                if (random.nextDouble() <= probability) {
                    put(x, y);
                }
            }
        }
    }

    /**
     * Moves the sand form x1, y1, to x2, y2
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return true if the move was successful, otherwise false
     */
    public boolean move(int x1, int y1, int x2, int y2) {
        if (inBounds(x2, y2) && isSand(x1, y1) && !isSand(x2, y2)) {
            field[y1][x1] = 0;
            field[y2][x2] = 1;
            return true;
        }
        return false;
    }

    public boolean inBounds(int x, int y) {
        return 0 <= x && x < field[y].length;
    }

    public void fall() {
        // moves all sand down one square
        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {
                if (isSand(x, y)) {
                    moveSandDown(x, y);
                }
            }
        }
    }

    private void moveSandDown(int x, int y) {
        // move down
        if (move(x, y, x, y + 1)) {
            return;
        }

        // choose either left or right
        int direction = random.nextBoolean() ? +1 : -1;

        // move diagonally down in one direction
        if (move(x, y, x + direction, y + 1)) {
            return;
        }

        // move diagonally down in the other direction
        move(x, y, x - direction, y + 1);
    }


    public boolean isSand(int x, int y) {
        return field[y][x] == 1;
    }

    public void randomSand(int n) {

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

}






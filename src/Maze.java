
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {
    private static final int ROAD = 0;
    private static final int WALL = 1;
    private static final int START = 2;
    private static final int EXIT = 3;
    private static final int PATH = 4;

    private int[][] maze;
    private boolean[][] visited;
    private Coordinates start;
    private Coordinates end;

    public Maze(File maze) throws FileNotFoundException {
        String fileText = "";
        try (Scanner input = new Scanner(maze)) {
            while (input.hasNextLine()) {
                fileText += input.nextLine() + "\n";
            }
        }
        initializeMaze(fileText);
    }

    private void initializeMaze(String text) {
        if (text == null || (text = text.trim()).length() == 0) {
            throw new IllegalArgumentException("empty lines data");
        }

        String[] lines = text.split("[\r]?\n");
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];

        for (int row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() + " but should be " + getWidth() + ")");
            }

            for (int col = 0; col < getWidth(); col++) {
                if (lines[row].charAt(col) == '#')
                    maze[row][col] = WALL;
                else if (lines[row].charAt(col) == 'A') {
                    maze[row][col] = START;
                    start = new Coordinates(row, col);
                } else if (lines[row].charAt(col) == 'B') {
                    maze[row][col] = EXIT;
                    end = new Coordinates(row, col);
                } else
                    maze[row][col] = ROAD;
            }
        }
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Coordinates getEntry() {
        return start;
    }

    public Coordinates getExit() {
        return end;
    }

    public boolean isExit(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isWall(int row, int col) {
        return maze[row][col] == WALL;
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isValidLocation(int row, int col) {
        return row >= 0 && row < getHeight() && col >= 0 && col < getWidth();
    }

    public void printPath(List<Coordinates> path) {
        int[][] tempMaze = Arrays.stream(maze)
                .map(int[]::clone)
                .toArray(int[][]::new);
        for (Coordinates coordinate : path) {
            if (isStart(coordinate.getX(), coordinate.getY()) || isExit(coordinate.getX(), coordinate.getY())) {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = PATH;
        }
        System.out.println("Shortest path = " + (shortestPath(tempMaze) + 1)); //This +1 is because the ENDING was not being considered part of the path.
    }

    public int shortestPath(int[][] maze) {
        int shortestPath = 0;
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (maze[row][col] != ROAD && maze[row][col] != WALL && maze[row][col] != START && maze[row][col] != EXIT) {
                    shortestPath += 1;
                }
            }
        }
        return shortestPath;
    }

}

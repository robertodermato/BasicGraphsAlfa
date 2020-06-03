import java.io.File;
import java.util.List;

//Usa as classes Coordinates, Maze e ServiceLogic

public class AppAlex {

        public static void main(String[] args) throws Exception {

            File maze1 = new File("");

            maze1 = new File("caso1_cohen.txt");
            execute(maze1);

            maze1 = new File("caso2_cohen.txt");
            execute(maze1);

            maze1 = new File("caso3_cohen.txt");
            execute(maze1);

            maze1 = new File("caso4_cohen.txt");
            execute(maze1);

            maze1 = new File("caso5_cohen.txt");
            execute(maze1);

            maze1 = new File("caso6_cohen.txt");
            execute(maze1);

            maze1 = new File("caso7_cohen.txt");
            execute(maze1);

        }

        private static void execute(File file) throws Exception {
            Maze maze = new Maze(file);
            System.out.println("Arquivo: " + file);
            bfs(maze);
        }

        private static void bfs(Maze maze) {
            ServiceLogic bfs = new ServiceLogic();
            List<Coordinates> path = bfs.solve(maze);
            maze.printPath(path);
        }


}

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class TestNQueens {


    @Test
    void testNQueenWithObstacles(){
        try {
            int n = 2;
            List<Coordinate> obstacles = new ArrayList<>();
            obstacles.add(new Coordinate(1,0));
            obstacles.add(new Coordinate(0,1));
            NQueenWithObstacles solutionClass = new NQueenWithObstacles();
            List<List<Coordinate>> solutions1 = solutionClass.mostQueenWithObstacles(n,obstacles,2);
            List<List<Coordinate>> solutions2 = new ArrayList<>();
            List<Coordinate> solution = new ArrayList<>();
            solution.add(new Coordinate(0,0));
            solution.add(new Coordinate(1,1));
            assertEquals(solutions1,solutions2);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

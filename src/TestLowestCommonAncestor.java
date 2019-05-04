import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLowestCommonAncestor {

    @Test
    void testLowestCommonAncestor(){
        try {
        TreeNode root = new TreeNode(0);
        String buildOrder = "8 5 3 2 1 # # # 4 # # 6 # 7 # # 13 9 # 11 # # 17 15 14 # # 16 # # #";
        root = root.build(buildOrder);
        LowestCommonAncestor l = new LowestCommonAncestor();
        TreeNode one = new TreeNode(10);
        TreeNode two = l.find(root,7);
        TreeNode solution = l.lowestCommonAncestorIII(root,one,two);
        assertEquals(null, solution);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

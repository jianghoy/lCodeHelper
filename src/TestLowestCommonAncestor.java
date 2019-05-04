import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLowestCommonAncestor {

    @Test
    void testLowestCommonAncestor(){
        TreeNode root = new TreeNode(0);
        String buildOrder = "[8,5,3,2,1,null,null,null,4,null,null,6,null,7]";
        root = root.build(buildOrder);
        LowestCommonAncestor l = new LowestCommonAncestor();
        //LowestCommonAncestor l = new LowestCommonAncestor();
        TreeNode one = new TreeNode(10);
        TreeNode two = l.find(root,7);
        try {
            assertEquals(null, l.lowestCommonAncestorIII(root,one,two));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

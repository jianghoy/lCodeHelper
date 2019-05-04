import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLowestCommonAncestor {

    @Test
    void testLowestCommonAncestor(){
        TreeNode root = new TreeNode(0);
        String buildOrder = "[8,5,3,2,1,null,null,null,4,null,null,6";
        buildOrder = buildOrder + ",null,7,null,null,13,9,null,11,null,null,17,15,14,null,null,16";
        buildOrder = buildOrder + ",null,null,null]";
        root = root.build(buildOrder);
        LowestCommonAncestor l = new LowestCommonAncestor();
        TreeNode one = new TreeNode(19);
        TreeNode two = l.find(root,7);
        try {
            assertEquals(null, l.lowestCommonAncestorIII(root,one,two));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

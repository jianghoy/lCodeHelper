import org.testng.annotations.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class TestTreeNode {

    @Test
    void testStringToList() {

      /**List<Integer> expectedList = new ArrayList<>();
      expectedList.add(1);
      expectedList.add(2);
      expectedList.add(3);**/
      TreeNode node = new TreeNode(0);
      TreeNode node2 = new TreeNode(0);
      try {
         //assertEquals(expectedList, node.stringToList(testInput));
          String testInput = "[[8,9,10,11]]";
          String testInput2 = "8 9 10 11";
         assertEquals(node.stringToList(testInput),node2.stringToListII(testInput2));
         testInput = "[1,null,3,null,null,null,7]";
         testInput2 = "1 # 3 # 7";
         node = node.build(testInput);
         node2 = node2.buildII(testInput2);
         assertTrue(node.treeEquals(node2));

      }
      catch (Exception e) {
          e.printStackTrace();
      }

    }
}

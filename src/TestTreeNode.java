import org.testng.annotations.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestTreeNode {

    @Test
    void testStringToList() {
      String testInput = new String("[[1,2,3]]");
      List<Integer> expectedList = new ArrayList<>();
      expectedList.add(1);
      expectedList.add(2);
      expectedList.add(3);
      TreeNode node = new TreeNode(0);
      try {
         assertEquals(expectedList, node.stringToList(testInput));
      }
      catch (Exception e) {
          e.printStackTrace();
      }

    }
}

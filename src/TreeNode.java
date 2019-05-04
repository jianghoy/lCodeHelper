import java.util.*;

public class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
      this.key = key;
    }



    /**
     * compare the key value between two (TreeNode type) objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return key == treeNode.key ;
    }

    /**
     * compare this tree (i.e. Tree with root as TreeNode this) with
     * another tree (i.e. Tree with root as TreeNode node) using recursion
     * at each node we use the equals method above (this is also the recursion
     * rule)
     * @param node another tree root to compare
     * @return true if the two tree are equal; false otherwise
     */
    public boolean treeEquals(TreeNode node){
        if (!this.equals(node)) {
            return false;
        }
        boolean solution = true;
        if (this.left != null) {
            solution = this.left.treeEquals(node.left);
        }
        if (!solution || this.right == null) {
            return  solution;
        }
        return this.right.treeEquals(node.right);

    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public TreeNode build(String string){
        try {
            if (string.contains("[") || string.contains("]") || string.contains(",")) {
                return  build(stringToList(string));
            } else {
                return buildII(string);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Iterator<TreeNode> preOrderIterator() {
        return new PreOrderIterator(this);
    }


    /**
     * @Author: Jianghong Ying
     * @Date: Apr.1st, 2019
     * @Email: YJianghong@gmail.com
     *
     * convert a string of integers representing Binary Tree
     * level order traversal in Laicode style 1 into a list of
     * Integers
     * i.e.:
     * string : "[1,2,3]"
     * tree:
     *     1
     *   /  \
     *  2    3
     *
     *  string : "[1,null,3,null,null,null,7]"
     *  tree:
     *      1
     *       \
     *        3
     *         \
     *          7
     *
     * @param string
     * @return list
     */
    public List<Integer> stringToList(String string) throws Exception {
        if (string == null) {
            throw new Exception("string is null");
        }
        String[] numbers = string.split("[\\[\\],]+");
        List<Integer> solution = new ArrayList<>();
        int j = 0;
        for (int i=0;i<numbers.length;i++) {
            if (numbers[i].length() == 0) {
                continue;
            }
            try {
                solution.add(j,Integer.parseInt(numbers[i]));
            } catch (Exception e){
                solution.add(j,null);
            }
            j++;
        }
        return solution;
    }

    /**
     * @Author: Jianghong Ying
     * @Date: Apr.1st, 2019
     * @Email: YJianghong@gmail.com
     *
     * convert a string of integers representing Binary Tree
     * level order traversal in Laicode style 2 into a list of
     * Integers
     * e.g:
     * string : "1 2 3"
     * tree:
     *     1
     *   /  \
     *  2    3
     *
     *  string : "1 # 3 # 7"
     *  tree:
     *      1
     *       \
     *        3
     *         \
     *          7
     *
     * @param string
     * @return list
     */
    public List<Integer> stringToListII(String string) throws Exception {
        if (string == null) {
            throw new Exception("string is null");
        }
        String[] numbers = string.split("[ ]+");
        List<Integer> solution = new ArrayList<>();
        int j = 0;
        for (int i=0;i<numbers.length;i++) {
            if (numbers[i].length() == 0) {
                continue;
            }
            try {
                solution.add(j,Integer.parseInt(numbers[i]));
            } catch (Exception e){
                solution.add(j,null);
            }
            j++;
        }
        return solution;
    }
    /**
     * @Author: Jianghong Ying
     * @Date: Apr.1st, 2019
     * @Email: YJianghong@gmail.com
     *
     * Build tree according to list of Integers in laicode style 1
     *
     * @param list: a list of Integers representing tree traversal in
     *              level order, i.e. LaiCode style 1, with null representing
     *              null TreeNode
     * @return TreeNode root: the root of new built tree
     * @throws Exception: throw when list is null
     */
    public TreeNode build(List<Integer> list) throws Exception {
        if (list == null) {
            throw new Exception("List is null");
        } else if (list.isEmpty()) {
            return null;
        } else {
            List<TreeNode> tree = new ArrayList<>();
            for (int i=0;i<list.size();i++) {
                Integer treeValue = list.get(i);
                if (treeValue == null) {
                    tree.add(i,null);
                    continue;
                }
                TreeNode child = new TreeNode(treeValue);
                tree.add(i,child);

                // link child back to suitable parent node
                if (i > 0) {
                    TreeNode parent = tree.get((i-1)/2);
                    if ((i-1) % 2 == 0) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }
            }
            return tree.get(0);
        }

    }


    public TreeNode buildII(String string) throws Exception {
        return buildII(stringToListII(string));
    }

    /**
     * @Author: Jianghong Ying
     * @Date: Apr.1st, 2019
     * @Email: YJianghong@gmail.com
     *
     * Build tree according to list of Integers in laicode style 2
     *
     * @param list: a list of Integers representing tree traversal in
     *              level order but insert only in valid positions, skipping impossible
     *              ones , i.e. LaiCode style 2, with # representing
     *              null TreeNode
     * @return TreeNode root: the root of new built tree
     * @throws Exception: throw when list is null
     */
    public TreeNode buildII(List<Integer> list) throws Exception {
        if (list == null) {
            throw new Exception("List is null");
        } else if (list.isEmpty()) {
            return null;
        }
        List<TreeNode> prev = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        if (list.get(0) == null) {
            throw new Exception("First element (root) cannot be non-integer");
        }
        TreeNode root = new TreeNode(list.get(0));
        prev.add(root);
        int numOfLevel = 1;
        int parentIndex = 0;
        int leftChildIndicator = 0;
        // if leftChildIndicator = 0, insert child into left of parent
        // if = 1, insert into right
        for (int i = 1; i < list.size();i++){
            if (i == 0){

            } else {
                TreeNode child = list.get(i)!= null ? new TreeNode(list.get(i)) : null;
                curr.add(child);
                while (parentIndex > prev.size()-1 || prev.get(parentIndex) == null || parentIndex > Math.pow(2,numOfLevel)-1){
                    parentIndex++;
                    leftChildIndicator = 0;
                    if (parentIndex > Math.pow(2,numOfLevel)-1 || parentIndex > prev.size()-1) {
                        prev = curr;
                        curr = new ArrayList<>();
                        parentIndex = 0;
                        numOfLevel++;
                    }
                }
                if (leftChildIndicator == 0){
                    prev.get(parentIndex).left =child;
                    leftChildIndicator++;
                } else {
                    prev.get(parentIndex).right = child;
                    leftChildIndicator = 0;
                    parentIndex++;
                }

            }
        }
        return root;
    }

    private class PreOrderIterator implements Iterator<TreeNode> {
        Deque<TreeNode> stack;

        public PreOrderIterator(TreeNode root) {
          stack = new ArrayDeque<>();
          if (root != null) {
              stack.addFirst(root);
          }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            if (this.hasNext()) {
                TreeNode cur = stack.removeFirst();
                if (cur.right != null) {
                    stack.addFirst(cur.right);
                }
                if (cur.left != null) {
                    stack.addFirst(cur.left);
                }
                return cur;

            }
            return null;
        }
    }

}

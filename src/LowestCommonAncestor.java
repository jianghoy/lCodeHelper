public class LowestCommonAncestor {

        int count = 0;
        public TreeNode lowestCommonAncestorIII(TreeNode root,
                                                TreeNode one, TreeNode two) {
            TreeNode sol = lca(root, one, two);
            return count >= 2 ? sol : null;
        }

        private TreeNode lca(TreeNode root, TreeNode one, TreeNode two) {
            if (root == null) {
                return root;
            }
            if (root == one || root == two) {
                count++;
            }
            TreeNode l = lca(root.left, one, two);
            TreeNode r = lca(root.right, one, two);

            if (root == one || root == two || (l != null && r != null)) {
                return root;
            } else {
                return l != null ? l : r;
            }

        }

        public TreeNode find(TreeNode root , int key){
            if (root == null || root.key == key){
                return root;
            }
            TreeNode solution = find(root.left,key);
            return solution != null ? solution:find(root.right,key);
        }
    }



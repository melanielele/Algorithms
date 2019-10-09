/* ------------------------------------------------------------
This homework is completed by Zisong Guo and Melanie Zhao without asking and consulting others except TA.
    Collaboration statement:
  */
class TreeNode {
    String key; // key
    String value; // value
    TreeNode left; // left child
    TreeNode right; // right child
    TreeNode parent; // parent node
    boolean color; // color of the node

    /* ------------------------------------------------------------
      We initialize a tree node with its key and its value.
      ------------------------------------------------------------ */
    public TreeNode(String key, String value, boolean color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }


    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
    public boolean getColor() {
        return this.color;
    }

    public TreeNode getLeft() {
        return this.left;
    }
    public TreeNode getRight() {
        return this.right;
    }
    public TreeNode getParent() {
        return this.parent;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return  key + ":" + value + ":" + (color ? "RED" : "BLACK");
    }

}

public class RBtree {
    public RBtree(){}
    TreeNode root; // the root of red black tree
    final boolean RED = true; // if a node is true, it is a red node
    final boolean BLACK = false; // if a node is false, it is a black node



    /* ------------------------------------------------------------
              SEARCH FOR RB TREE
    ------------------------------------------------------------ */
    public String search(String key) {
        if(root == null) {
            return null;
        }
        TreeNode result = find(root, key);

        if(result == null) {
            return null;
        }
        return result.getValue();
    }
    /* ------------------------------------------------------------
  In method find we use recursion to go through all nodes
   in the tree to locate the node with same name we want to find.
   ------------------------------------------------------------ */
    public TreeNode find(TreeNode root, String key) {
        if(root == null) {
            return null;
        }
        if(root.key.compareTo(key) > 0) {
            return find(root.getLeft(), key);
        }else if(root.key.compareTo(key) < 0) {
            return find(root.getRight(), key);
        }else {
            return root;
        }

    }
    /* ------------------------------------------------------------
               ADD FOR RB TREE
------------------------------------------------------------ */
    //the add method is an original binary search tree search method
    //we use setparents to set parents for the whole tree, and insertionfix up to fix the tree to be a red black tree.
    // since the root of a tree is always black, we set the root at end to be black.
    public void add(String key, String value) {
        if(key == null || value == null) return;
        root = put(root, key, value);
        setParents(root);
        TreeNode x = find(root, key);
        InsertionFixUp(x);
        root.color = BLACK;


    }

    public TreeNode put(TreeNode x, String key, String value) {
        if(x == null) return new TreeNode(key, value, RED);
        int cmp = key.compareTo(x.getKey());
        if      (cmp < 0) {
            x.left  = put(x.left, key, value);//recursively change order the left subtree

        }
        else if (cmp > 0) {
            x.right = put(x.right, key, value);//recursively change order the right subtree

        }
        else  {
            x.setValue(key);
            return x;
        }

        return x;
    }
    // in insertion fix up we use 4 cases to fix the tree to be a red black tree.
    public void InsertionFixUp(TreeNode x) {

        while(x.parent != null && x.parent.color == RED) {

            if(x.parent == x.parent.parent.left) {
                TreeNode y = x.parent.parent.right; // y is x's uncle
                // this is the case that uncle is red
                // we flip uncle and parent to be black and grandparent to be red
                if(y != null && y.color == RED) {
                    x.parent.color = BLACK;
                    y.color = BLACK;
                    x.parent.parent.color  = RED;
                    x = x.parent.parent;
                }else {
                    if(x == x.parent.right) { // this is LR case
                        x = x.parent;
                        RotateLeft(x);// we first change it to LL case
                    }
                    // if it is in LL case, we rotate right its grandparent. set parent to be black and grandparent to be red.
                    x.parent.parent.color = RED;
                    x.parent.color = BLACK;
                    RotateRight(x.parent.parent);
                }
            }else {// it is symmetric for left case and right case.
                TreeNode y = x.parent.parent.left;
                if(y!= null && y.color == RED) {
                    x.parent.color = BLACK;
                    y.color = BLACK;
                    x.parent.parent.color  = RED;
                    x = x.parent.parent;
                }else {
                    if(x == x.parent.left) {
                        x = x.parent;
                        RotateRight(x);
                    }
                    x.parent.color = BLACK;
                    x.parent.parent.color = RED;
                    RotateLeft(x.parent.parent);
                }
            }
        }
    }
 	/* ------------------------------------------------------------
           TO STRING FOR RED BLACK TREE
------------------------------------------------------------ */

    public String toString() {
        if(root == null) {
            return "( )";
        }
        return recursion(root);
    }
    public String recursion(TreeNode x) {

        return "("+ ((x!=null && x.getColor() == RED)? "*" + x.key + ":" + x.value:"") + ((x!=null && x.getColor() == BLACK)? "#" + x.key + ":" + x.value:"")
                + ((x.left != null)?recursion(x.left):" (empty left)") + ((x.right != null)?recursion(x.right):" (empty right)") +")";
    }
    /* ------------------------------------------------------------
     HELPER METHODS FOR RED BLACK TREE
------------------------------------------------------------ */
    public void RotateLeft(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        if(y.left != null)  y.left.parent = x;
        y.parent = x.parent;
        if(x.parent == null) {
            root = y;
        }else if(x == x.parent.left){
            x.parent.left = y;
        }else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    public void RotateRight(TreeNode x) {
        TreeNode y = x.left;
        x.left = y.right;
        if(y.right != null) y.right.parent = x;
        y.parent = x.parent;
        if(x.parent == null) {
            root = y;
        }else if(x == x.parent.left) {
            x.parent.left = y;
        }else {
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }
    /* ------------------------------------------------------------
    We use setParents to find parents for all notes. if a node
    has left child, it is its left child's children. if a node
    has right child, it is its right child's children.
    ------------------------------------------------------------ */
    public void setParents(TreeNode root) {

        if(root == null)
            return;
        if(root.left != null) {

            root.left.parent = root;
            setParents(root.left);
        }
        if(root.right != null) {
            root.right.parent = root;
            setParents(root.right);
        }
    }

    public void remove(String key) {
        if(key == null || root == null) {// if name or root is null, we return
            System.out.println("1");
            return;
        }
        TreeNode x = find(root, key);
        if(x == null) {// if we cannot find the node, return
            return;
        }
        else {
            deletion(x);
        }
    }

    /* ------------------------------------------------------------
  We use transplant method to transplant two nodes when we need to delete a node
  and swap the location of these two, keep track of parents also
  ------------------------------------------------------------ */
    public void Transplant(TreeNode u, TreeNode v) {
        System.out.println("trans: " + v + " to " + u);
        if (u.parent==null) {
            root = v;
        }
        else if (u==u.parent.left) {
            u.parent.left = v;
        }
        else {
            u.parent.right = v;
        }
        v.parent = u.parent;//occur unconditonally
        if (v.key.equals("null") && v.parent != null) {
            if (v.parent.left == v) {
                v.parent.left = null;
            } else {
                v.parent.right = null;
            }
        }
    }

    private void fillNull(TreeNode p, TreeNode s, boolean isLeftChild) {
        if (s == null) {
            s = new TreeNode("null", "null", false);
            s.parent = p;
            if (isLeftChild) {
                p.left = s;
            } else {
                p.right = s;
            }
        }
    }


    /* ------------------------------------------------------------
  We use deletion method to delete the specfic node
  ------------------------------------------------------------ */
    public void deletion (TreeNode z) {
        System.out.println("delete " + z);
        TreeNode x;
        TreeNode y = z;
        boolean ycolor_original = y.color;
        //these two cases are when the nodes deleted have fewer than 2 childrren
        if (z.left==null) {//when it has right child, transplant right child and the node to delete
            fillNull(z, z.right, false);
            x=z.right;
            Transplant(z,z.right);
        }
        else if (z.right==null) {//when it has left child, transplant left child and the node to delete
            fillNull(z, z.left, true);
            x = z.left;
            Transplant(z,z.left);
        }
        //this case is when z has two children
        else {
            y = minNode(z.right);//find the smallest node in right subtree
            ycolor_original = y.color;

            //bug is here!!!
            //we cannot assign null's parent so there is a null pointer exception
            //but i don't know how to fix
            //y and x are correct!!
            this.fillNull(y, y.right, false);
            x = y.right;//if x is null
            y.right.parent = y;
            if (y.parent==z) {
                System.out.println(x);
                x.parent = y;
            }
            else {
                Transplant(y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            Transplant(z,y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (ycolor_original==BLACK && !x.key.equals("null")) {
            DeletionFixup(x);
        }
    }

    public void DeletionFixup(TreeNode x) {
        System.out.println("Delete fix up " + x);
        while (x!=root && x.color==BLACK) {
            if(x==x.parent.left) {
                TreeNode w=x.parent.right;
                if(w.color==RED) {//case 1 :x's sibling w is red
                    w.color=BLACK;
                    x.parent.color=RED;
                    RotateLeft(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color==BLACK && w.right.color ==BLACK) {//case 2:when node w is black, w 's child are all black
                    w.color = RED;
                    x=x.parent;
                }
                else if (w.right.color ==BLACK) {//case 3:when node w is black, w left is red, w right is black
                    w.left.color=BLACK;
                    w.color = RED;
                    RotateRight(w);
                    w = x.parent.right;
                }
                w.color = x.parent.color;//case 4: when node w is black, right is red
                x.parent.color =BLACK;
                w.right.color = BLACK;
                RotateLeft(x.parent);
                x = root;
            }
            else {//this is the symmetric case !
                TreeNode w=x.parent.left;
                if(w.color==RED) {
                    w.color=BLACK;
                    x.parent.color=RED;
                    RotateRight(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color==BLACK && w.left.color ==BLACK) {
                    w.color = RED;
                    x=x.parent;
                }
                else if (w.left.color ==BLACK) {
                    w.right.color=BLACK;
                    w.color = RED;
                    RotateLeft(w);
                    w = x.parent.left;
                }
                w.color = x.parent.color;
                x.parent.color =BLACK;
                w.left.color = BLACK;
                RotateRight(x.parent);
                x = root;
            }
        }
        x.color =BLACK;
    }

    public void print(TreeNode x) {
        System.out.println(x + ": left=" + x.left + ", right=" + x.right + ", parent=" + x.parent);
        if (x.left != null) {
            print(x.left);
        }
        if (x.right != null) {
            print(x.right);
        }
    }

    /* ------------------------------------------------------------
    method minNode is used to find the minimum node on the left side
    of the delete node's right node.
    ------------------------------------------------------------ */
    public TreeNode minNode(TreeNode x) {
        while (x.left!=null) {
            x = x.left;
        }
        return x;


    }

    public TreeNode maxNode(TreeNode x) {
        if(x.right == null) {
            return x;
        }else {
            return maxNode(x.right);
        }


    }
    /* ------------------------------------------------------------
                  MAIN METHOD
------------------------------------------------------------ */
    public static void main(String[] args) {
        RBtree test = new RBtree();
        test.add("3", "email3");
        test.add("1", "email1");
        test.add("2", "email2");
        test.add("6", "email6");
        test.add("5", "email5");
        test.add("4", "email4");
        test.add("9", "email9");
        test.add("0", "email0");
        test.add("8", "email8");
//        test.add(null, "34ddsj@adsf.due");
//        test.add("i", null);
//        test.add(null, null);
        test.remove("3");
        test.remove("1");
//        test.print(test.root);
        test.remove("5");
//        test.remove("8");
        test.remove("2");
        test.remove("0");
        test.remove("6");
        test.remove("4");
        test.remove("8");
        test.remove("9");


        //System.out.println(test.toString());
        //System.out.println(test.search("1"));
        //System.out.println(test.search("8"));
        //System.out.println(test.search("7"));

    }
}

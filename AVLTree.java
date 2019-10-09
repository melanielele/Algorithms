/* ------------------------------------------------------------
This homework is completed by Zisong Guo and Melanie Zhao without asking and consulting others except TA.
    Collaboration statement:
    https://www.geeksforgeeks.org/iterative-quick-sort/
    https://algs4.cs.princeton.edu/22mergesort/
------------------------------------------------------------ */
class TreeNode {
		String name; // key
		String email; // value
		TreeNode left; // left child
		TreeNode right; // right child
		TreeNode parent; // parent node
		int height; // height of this node in the tree
		
		/* ------------------------------------------------------------
	      We initialize a tree node with its key and its value.
	      ------------------------------------------------------------ */
		public TreeNode(String name, String email) {
			this.name = name;
			this.email = email;
			this.height = getHeight(this);
		}
		
		/* ------------------------------------------------------------
	      We use recursion to find height of the node. If the node
	      has no children, its height is 1. If the node has only left children,
	      we find its left children's height and plus 1. The same algorithm 
	      for only right children. If it has two children, we find the
	      max height of its left and right children and plus 1. 
	      ------------------------------------------------------------ */
		 public int getHeight(TreeNode x){
			if(x.left == null && x.right == null) {
				return 1;
			} else if (x.left == null && x.right != null) {
				return 1 + getHeight(x.right);
			} else if(x.left != null && x.right == null) {
				return 1 + getHeight(x.left);
			}else {
				return 1+ Math.max(getHeight(x.left), getHeight(x.right));
			}
		
	    }
		/* ------------------------------------------------------------
	      We find the balance number of a node by BlanceNum(). If the 
	      node only has right child, its balance number is 0 - its left
	      child's height. If only left child, its balance number is its
	      right child's height - 0. If it has both child, it balance num
	      is height of right child minus height of its left child.
	      ------------------------------------------------------------ */
		public int BalanceNum() {
			if (this.getLeft() !=null&&this.getRight()==null) {
				return 0 - this.getLeft().height;
			}
			else if (this.getLeft() ==null&&this.getRight()!=null) {
				return this.getRight().height;
			}
			else if(this.getLeft() ==null&&this.getRight()==null) {
				return 0;
			}
			else {
				return this.getRight().height-this.getLeft().height;
			}
			
		}
		
		public String getKey() {
			return this.name;
		}
		
		public String getValue() {
			return this.email;
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
	   public int getHeight() {
			return this.height;
		}
		
		public void setValue(String email) {
			this.email = email;
		}
		
		public String toString() {
			return  name + ":" + email + ":" + height;
		}
		
}	
public class AVLTree {
	
	 TreeNode root;//the root of an AVLtree
	
	public AVLTree() {}
	
	 public int getHeight(TreeNode x){
			if(x.left == null && x.right == null) {
				return 1;
			} else if (x.left == null && x.right != null) {
				return 1 + getHeight(x.right);
			} else if(x.left != null && x.right == null) {
				return 1 + getHeight(x.left);
			}else {
				return 1+ Math.max(getHeight(x.left), getHeight(x.right));
			}
	 }
	
	public void add(String name, String email) {//use this put method to locate the certain node as root
		if(name == null || email == null) {
			return;
		}
		root = put(root,name,email);
		setParents(root);
	}
	
	public TreeNode put(TreeNode x, String name, String email) {//to recursively call this method to put a node in proper location 
		
		if (x == null) {
            x = new TreeNode(name,email);
            x.height = getHeight(x);
            return x;
		}
		else {
		
			
		int cmp = name.compareTo(x.getKey());
		if      (cmp < 0) {
			x.left  = put(x.left, name, email);//recursively change order the left subtree
		   
		}
	    else if (cmp > 0) {
	    	    x.right = put(x.right, name, email);//recursively change order the right subtree
		     
	    }
	    else  {            
	    	 	x.setValue(email);
	    		return x;
	    }//not changes on the structure of the whole tree, only the value changed, so return 
	    	       // x.size = 1 + size(x.left) + size(x.right);
		}
		
		
		x.height = getHeight(x);//update the newly added node height
	    
		
	
		return rootBalance(x);
		
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
	
	/* ------------------------------------------------------------
    we remove a node.  if the node does not have a child, we 
    simply make its parent no longer has this child. If the node
    has only one child, we set its child's parent as its parent and
    its parent next is its child. If the node has two children, we 
    find the min value on its right and replace it with the node. then
    we delete that min value node.
    ------------------------------------------------------------ */
	public void remove(String name) {
		if(name == null || root == null) {// if name or root is null, we return
			return;
		}
		TreeNode x = find(root, name);
		if(x == null) {// if we cannot find the node, return
			return;
		}
		if(x.left == null && x.right == null) { // if the node has no child
			if(x.parent == null) {
				x = null;
			}else if(x.parent.left == x) {
				x.parent.left = null;
			}else {
				x.parent.right = null;
			}
		}else if(x.left != null && x.right == null) {// if it only has left child
			if(x.parent == null) {
				root = x.left;
			}else if(x.parent.left == x) {
				x.parent.left = x.left;
				x.left.parent = x.parent;				
			}else {
				x.parent.right = x.left;
				x.left.parent = x.parent;				
			}
				
		}else if(x.left == null && x.right != null) {// if it only has right child
			if(x.parent == null) {
				root = x.right;
			}else if(x.parent.left == x) {
				x.parent.left = x.right;
				x.right.parent = x.parent;				
			}else {
				x.parent.right = x.right;
				x.right.parent = x.parent;				
			}
		}else if(x.left != null && x.right != null) { // if it has two children
			TreeNode min = minNode(x.right);
			x.name = min.name;
			x.email = min.email;
			if(min.parent.left == min) {
				min.parent.left = null;
			}else {
				min.parent.right = null;
			}
		}
		calcHeight(root); // explain later
		balanceTree(root); // explain later
		
		
		}
	/* ------------------------------------------------------------
    method balanceTree is balancing the whole tree by using recursion
    if node is the root, we use rootbalance since we need to refresh
    to the new root. if it is not a root, we use ToBalance to balance
    every node in the tree.
    ------------------------------------------------------------ */
	public void balanceTree(TreeNode x) {
		if(x == root) {
		   x = rootBalance(x);
		}
		else {
		 
		  ToBalance(x);
		}
		if(x.left != null)
			balanceTree(x.left);
		if(x.right != null)
			balanceTree(x.right);
		}
	/* ------------------------------------------------------------
    method calcHeight is used to calculate the height of every
    node in the tree. we simply use recursion to go through every
    node to calculate its height.
    ------------------------------------------------------------ */
	public void calcHeight(TreeNode root){
		if(root == null)
			return;
		else {
			root.height = getHeight(root);
			if(root.left != null) 
				calcHeight(root.left);
			if(root.right != null)
			    calcHeight(root.right);
		}
	}
	/* ------------------------------------------------------------
    method minNode is used to find the minimum node on the left side
    of the delete node's right node.
    ------------------------------------------------------------ */
    public TreeNode minNode(TreeNode x) {
		if(x.left == null) {
			return x;
		}else {
			return minNode(x.left);
		}
		
		
	}
	
    /* ------------------------------------------------------------
    method search is used to search a node by its name. 
    ------------------------------------------------------------ */
	public String search(String name) {
		if(root == null) {
			return null;
		}
	    TreeNode result = find(root, name);
	   
	    if(result == null) {
			return null;
		}
		return result.getValue();
	}
	 /* ------------------------------------------------------------
   In method find we use recursion to go through all nodes
    in the tree to locate the node with same name we want to find. 
    ------------------------------------------------------------ */
	public TreeNode find(TreeNode root, String name) {
		if(root == null) {
			return null;
		}
		if(root.name.compareTo(name) > 0) {
			return find(root.getLeft(), name);
		}else if(root.name.compareTo(name) < 0) {
			return find(root.getRight(), name);
		}else {
			return root;
		}
		
	}
	
	public TreeNode rootBalance(TreeNode x) {
		
		if (x.BalanceNum() > 1) {//positive balance at the root = 2
			if (x.right.BalanceNum()<0) {
				x.right = RotateRight(x.right);//negative imbalance at right child, right rotation on the right child
			}//and then rotate it to left
			x = RotateLeft(x);
		}//no negative imbalance at right child, make it rotate to left 
		
	   else if (x.BalanceNum() <-1) {//negative balance at the root = -2, which means left has too many elements
			if (x.left.BalanceNum()>0) {
				x.left = RotateLeft(x.left);
			}
			x = RotateRight(x);		
		}		
		return x;
	}
	
	public void ToBalance(TreeNode x) {
		
		if (x.BalanceNum() > 1) {//positive balance at the root = 2
			if (x.right.BalanceNum()<0) {
				x.right = RotateRight(x.right);//negative imbalance at right child, right rotation on the right child
			}//and then rotate it to left
			System.out.println(x.BalanceNum());
			x = RotateLeft(x);
		}//no negative imbalance at right child, make it rotate to left 
		
	   else if (x.BalanceNum() <-1) {//negative balance at the root = -2, which means left has too many elements
			if (x.left.BalanceNum()>0) {
				
				x.left = RotateLeft(x.left);
			}
			x = RotateRight(x);		
		}		
	}
	
	public TreeNode RotateRight(TreeNode x) {//rotate the root to the right, x is the old root and y is the new root!
		  TreeNode y = x.left;
		  x.left = y.right;
	      y.right = x;
	      y.parent = x.parent;
	      if(x.parent != null)
	 	     x.parent.left = y;
	      x.parent = y;
	      if(x.left != null)
	      x.left.parent = x;
	      x.height=getHeight(x);//update the new position and new height
	      y.height=getHeight(y);
	      return y;
		
	}
	public TreeNode RotateLeft(TreeNode x) {
		 TreeNode y = x.right;
		 x.right = y.left;
		 y.left = x;
		 y.parent = x.parent;
		 if(x.parent != null)
	     x.parent.right = y;
		 x.parent = y;
		 if(x.right != null)
		 x.right.parent = x;
		 x.height=getHeight(x);//update the new position and new height
	     y.height=getHeight(y);
	     return y;
		
		
	}
	
	public String toString() {
		if(root == null) {
			return "( )";
		}
		return recursion(root);
	}
	
	public static String recursion(TreeNode x) {
		
		return "("+ ((x!=null)?x.name + ":" + x.email:"") + ((x.left != null)?recursion(x.left):" (empty left)") + 
				((x.right != null)?recursion(x.right):" (empty right)") +")";
	}
	public static void main(String[] args) {
		/*
		AVLTree avl = new AVLTree();
		avl.add("a", "12345@emory.edu");
		avl.add("b","asdfkjh@emory.edu");
		avl.add("c","6782@asdfh");
		avl.add("d", "asdfkjh");
		avl.add("h", "adskfjhert");
		//avl.add("a", "newemailadress");
		//avl.add("i", "34ddsj@adsf.due");
		//avl.add(null, "34ddsj@adsf.due");
		//avl.add("i", null);
		//avl.add(null, null);
		
		//System.out.println(avl.search("h"));
		//System.out.println(avl.search("a"));
		//System.out.println(avl.search("m"));
		//System.out.println();
		
		
	
		System.out.println(avl.toString());
	    System.out.println(avl.root.getKey());
	    
	    System.out.println(avl.root.getRight().getRight().getKey());
	    System.out.println(avl.root.getRight().getRight().getParent().getKey());
			*/
		AVLTree test = new AVLTree();
		test.add("1", "email1");
		test.add("2", "email2");
		test.add("3", "email3");
		test.add("4", "email4");
		test.add("5", "email5");
		test.add("6", "email6");
		test.add("8", "email8");
		test.add("9", "email9");
		test.add("0", "email0");
		test.add(null, "34ddsj@adsf.due");
		test.add("i", null);
		test.add(null, null);
		
		
		System.out.println(test.toString());
		System.out.println(test.search("1"));
		
		
		test.remove("4");
		System.out.println(test.toString());
	
		

		
		
		
		
		
		
	}
		

		
		
	}
	

	



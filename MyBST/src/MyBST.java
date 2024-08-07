
public class MyBST {
	
	private BSTNode root;
	
	private class BSTNode{
		Integer val;
		BSTNode left, right;
		
		public BSTNode(Integer val) {
			this.val = val;
			left = right = null;
		}
		
		public String toString() {return "" + this.val; }
	}
	
	public MyBST() {
		root = null;
	}
	
	public boolean contains(Integer n) {
		return containsHelper(n, root);
	}
	
	private boolean containsHelper(Integer n, BSTNode curNode) {
		if(curNode == null) {
			return false;
		}
		
		if(n == curNode.val) {
			return true;
		}
		
		if(n < curNode.val) {
			return containsHelper(n, curNode.left);
		}
		
		return containsHelper(n, curNode.right);
	}
	
	public void insert(Integer n) {
		root = insertHelper(n, root);
		return;
	}
	
	private BSTNode insertHelper(Integer n, BSTNode curNode) {
		if(curNode == null) {
			curNode = new BSTNode(n);
			return curNode;
		}
		
		if(n < curNode.val) {
			curNode.left = insertHelper(n, curNode.left);
		}
		else {
			curNode.right = insertHelper(n, curNode.right);
		}
		return curNode;
	}
	
	public int size() {
		return sizeHelper(root);
	}
	
	private int sizeHelper(BSTNode curNode) {
		if(curNode == null) {
			return 0;
		}

		return 1 + sizeHelper(curNode.left) + sizeHelper(curNode.right);
	}
	
	public Integer getMax() {
		return getMaxHelper(root);
	}
	
	private Integer getMaxHelper(BSTNode curNode) {
		if(curNode == null) {
			return null;
		}
		
		if(curNode.left == null && curNode.right == null) {
			return curNode.val;
		}
		Integer greatestRight = curNode.val;
		Integer greatestLeft = curNode.val;
		
		if(curNode.left != null) {
			greatestLeft = getMaxHelper(curNode.left);
		}
		
		if(curNode.right != null) {
			greatestRight = getMaxHelper(curNode.right);
		}
		
		if(greatestRight > greatestLeft) {
			return greatestRight;
		}
		
		return greatestLeft;
		
	}
	
	public Integer getMin() {
		return getMinHelper(root);
	}
	
	private Integer getMinHelper(BSTNode curNode) {
		if(curNode == null) {
			return null;
		}
		
		if(curNode.left == null && curNode.right == null) {
			return curNode.val;
		}
		Integer greatestRight = curNode.val;
		Integer greatestLeft = curNode.val;
		
		if(curNode.left != null) {
			greatestLeft = getMaxHelper(curNode.left);
		}
		
		if(curNode.right != null) {
			greatestRight = getMaxHelper(curNode.right);
		}
		
		if(greatestRight < greatestLeft) {
			return greatestRight;
		}
		
		return greatestLeft;
		
	}
	
	public void inOrder() {
		inOrderHelper(root);
		return;
	}
	
	public void inOrderHelper(BSTNode curNode) {
		if(curNode == null) {
			return;
		}
		
		inOrderHelper(curNode.left);
		System.out.print(curNode.val + " ");
		inOrderHelper(curNode.right);
		return;
	}
	
	public void print() {
		printHelper(root, 0);
		return;
	}
	
	public void printHelper(BSTNode curNode, int curLevel) {
		if(curNode == null) {
			return;
		}
		
		printHelper(curNode.right, curLevel + 1);
		for(int e = 0; e < curLevel; e++) {
			System.out.print("    ");
		}
		System.out.println(curNode.val);
		printHelper(curNode.left, curLevel + 1);
		
		return;
	}
	
	public void delete(Integer n) {
		root = deleteHelper(root, n);
	}
	
	public BSTNode deleteHelper(BSTNode curNode, Integer n) {
		if(curNode == null) {
			return curNode;
		}
		
		if(curNode.val == n) {
			if(curNode.left == null && curNode.right == null) {
				return null;
			}
			
			if(curNode.left == null) {
				return curNode.right;
			}
			
			if(curNode.right == null) {
				return curNode.left;
			}
			
			curNode.val = getMinHelper(curNode.right);
			curNode.right = deleteHelper(curNode.right, getMinHelper(curNode.right));
			return curNode;
		}
		
		if(n < curNode.val) {
			curNode.left = deleteHelper(curNode.left, n);
		}
		else {
			curNode.right = deleteHelper(curNode.right, n);
		}
		
		return curNode;
	}
}

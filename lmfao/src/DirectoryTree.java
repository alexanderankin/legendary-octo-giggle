import java.util.UUID;
class DirectoryTree {
	private static final int MAX_DEPTH = 100;
	private DirectoryNode root;
	private DirectoryNode cursor;

	public DirectoryTree () {
		try {
			this.root = new DirectoryNode("root", false);
		}
		catch (Exception e) {
			System.out.println("\n\n\tPLZFIXME\n\n");
			System.exit(0);
		}
		this.resetCursor();
	}

	// not sure what this is for yet
	public void resetCursor () {
		this.cursor = this.root;
	}

	/**
	 * This method finds an immediate subdirectory of the cursor and changes to it.
	 * 
	 * @throws NotADirectoryException when the argument does not match an immediate subdirectory
	 */
	public void changeDirectory (String name) throws NotADirectoryException {
		if (cursor.getLeft().getName().equals(name)) cursor = cursor.getLeft();
		if (cursor.getMiddle().getName().equals(name)) cursor = cursor.getMiddle();
		if (cursor.getRight().getName().equals(name)) cursor = cursor.getRight();
		throw new NotADirectoryException();
	}

	public String presentWorkingDirectory () {
		String result = "";
		// deep copy
		// DirectoryNode localCursor = new DirectoryNode(
		// 	cursor.getName(), cursor.getIsFile(), cursor.getId());
		DirectoryNode localCursor = new DirectoryNode();
		try {
			localCursor = new DirectoryNode(
				cursor.getName(), cursor.getIsFile(), cursor.getId());
		}
		catch (Exception e) {
			System.out.println("\n\n\tPLZFIXME\n\n");
			System.exit(0);
		}
		
		while (!localCursor.equals(this.root)) {
			
			result = localCursor.getName() + result;
			result = "/" + result;
			localCursor = findParent(localCursor);
		}
		return "root" + result;
		//return "/" + result; // this is visually preferable for 
									// users of the linux file system
	}

	/**
	 * This method finds the parent of a Directory Node, it is a helper method for
	 * the presentWorkingDirectory method. It iterates through a flattened list which
	 * contains all files and folders in the tree and returns the first it finds which 
	 * contains the child as one of its children.
	 * 
	 * @param dn the DirectoryNode to find the parent of
	 * @return its parent
	 */
	private DirectoryNode findParent(DirectoryNode child) {
		if (child == null) return null; // boilerplate???
		
		for (DirectoryNode dn : flattenTree()) {
			if (dn.getLeft() != null /*&& !dn.getLeft().getIsFile()*/ && dn.getLeft().equals(child)) return dn;
			if (dn.getMiddle() != null /*&& !dn.getMiddle().getIsFile()*/ && dn.getMiddle().equals(child)) return dn;
			if (dn.getRight() != null /*&& !dn.getRight().getIsFile()*/ && dn.getRight().equals(child)) return dn;
		}

		System.out.println("\n\n\tThis child is not in the tree\n\n");
		System.out.println("\n\n\tNode: " + child.toString() + "\n\n");

		System.exit(0);
		return null;
	}
	/**
	 * This method flattens the tree into an array, traversal is supposed to be level order,
	 * but this behavior is not guaranteed because I don't know what I'm doing.
	 * 
	 * This method looks for the tree in the root member field of 'this'.
	 * 
	 * @return the flattened tree
	 */
	private DirectoryNode[] flattenTree() {
		DirectoryNode[] result = new DirectoryNode[0];
		
		int levelLength = 1;
		DirectoryNode[] curLayer = {root};
		while (levelLength > 0) {
			levelLength = 0;
			for (DirectoryNode dn : curLayer) {
				levelLength += getArrOfChildren(dn).length;
				for (DirectoryNode child : getArrOfChildren(dn)) {
					result = addElToArr(result, child);
				}
			}
			curLayer = getArrOfChildren(curLayer);
		}

		return result;
	}
	/**
	 * This method returns the children of a node in an array.
	 * 
	 * @param dn the parent directory node
	 * @return array of children of node dn
	 */
	private DirectoryNode[] getArrOfChildren (DirectoryNode dn) {
		DirectoryNode[] result = new DirectoryNode[0];
		if (dn.getLeft() != null) {result = addElToArr(result, dn.getLeft());}
		if (dn.getMiddle() != null) {result = addElToArr(result, dn.getMiddle());}
		if (dn.getRight() != null) {result = addElToArr(result, dn.getRight());}
		return result;
	}
	/**
	 * This method returns the children of a node in an array.
	 * 
	 * @param dn[] the array of parent directory nodes
	 * @return array of children of nodes in dn[]
	 */
	private DirectoryNode[] getArrOfChildren (DirectoryNode[] dns) {
		DirectoryNode[] result = new DirectoryNode[0];
		for (DirectoryNode dn : dns) {
			if (dn.getLeft() != null) {result = addElToArr(result, dn.getLeft());}
			if (dn.getMiddle() != null) {result = addElToArr(result, dn.getMiddle());}
			if (dn.getRight() != null) {result = addElToArr(result, dn.getRight());}
		}
		return result;
	}
	/**
	 * This method attempts to make up for lack of real collection types in this project
	 * 
	 */
	private DirectoryNode[] addElToArr (DirectoryNode[] oldAr, DirectoryNode newDN) {
		DirectoryNode[] result = new DirectoryNode[oldAr.length + 1];
		System.arraycopy(oldAr, 0, result, 0, oldAr.length);
		result[result.length - 1] = newDN;
		return result;
	}

	/**
	 * Lists the contents of the current directory by iterating through the 
	 * cursors children.
	 * 
	 * @return String representation of things in current directory.
	 */
	public String listDirectory () {
		String result = "";
		for (DirectoryNode child : getArrOfChildren(cursor)) {
			result += child.getName() + " ";
		}
		return result;
	}

	public void makeDirectory (String name) throws IllegalArgumentException, FullDirectoryException {
		if (getArrOfChildren(cursor).length == 3) {
			throw new FullDirectoryException();
		}
		
		// IllegalArgumentException is thrown here
		DirectoryNode newNode = new DirectoryNode();
		try {
			newNode = new DirectoryNode(name, false);
		}
		catch (Exception e) {
			System.out.println("\n\n\tPLZFIXME\n\n");
			System.exit(0);
		}
		
		if (cursor.getLeft() == null) {cursor.setLeft(newNode); return; }
		if (cursor.getMiddle() == null) {cursor.setMiddle(newNode); return; }
		if (cursor.getRight() == null) {cursor.setRight(newNode); return; }
	}

	public void makeFile (String name) throws IllegalArgumentException, FullDirectoryException {
		if (getArrOfChildren(cursor).length == 3) {
			throw new FullDirectoryException();
		}
		
		// IllegalArgumentException is thrown here
		DirectoryNode newNode = new DirectoryNode();
		try {
			newNode = new DirectoryNode(name, true);
		}
		catch (Exception e) {
			System.out.println("\n\n\tPLZFIXME\n\n");
			System.exit(0);
		}

		if (cursor.getLeft() == null) {cursor.setLeft(newNode); return; }
		if (cursor.getMiddle() == null) {cursor.setMiddle(newNode); return; }
		if (cursor.getRight() == null) {cursor.setRight(newNode); return; }
	}	

}

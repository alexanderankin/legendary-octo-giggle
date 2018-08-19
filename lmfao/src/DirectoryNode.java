import java.util.UUID;
/**
 * This class represents all the nodes of the filesystem tree. DirectoryNodes
 * can be files or folders, depending on this.isFile, and throws exceptions for
 * validating file names, not exceeding three leafs per folder.
 * 
 * In addition to the required member fields, an id field is also implemented in
 * order to compare nodes.
 * 
 */
class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private boolean isFile;

	private UUID id;

	/**
	 * The constructor and setter for name need to validate the name of the node.
	 * 
	 * The constructor overloading is designed to be able to create a DN with no 
	 * args and still have a uuid, with args and still have a random uuid, but alse
	 * allows for the constructor to be passed a uuid (for purposes of cloning).
	 * 
	 * @throws BadFileNameException when the file name wont pass validation
	 */
	public DirectoryNode (String name, boolean isFile) throws BadFileNameException {
		if (!(validateName(name))) throw new BadFileNameException();
		this.name = name;
		this.isFile = isFile;
		this.id = java.util.UUID.randomUUID();
	}
	public DirectoryNode (String name, boolean isFile, UUID id) throws BadFileNameException {
		if (!(validateName(name))) throw new BadFileNameException();
		this.name = name;
		this.isFile = isFile;
		this.id = id;
	}
	public DirectoryNode () {
		this.id = UUID.randomUUID();
	}

	/**
	 * This method validates the string of the name to make sure
	 * that it does not contain any whitespace.
	 * 
	 * @return whether or not the file/directory name is good
	 */
	private boolean validateName (String name) {
		boolean result = true;
		for (char c : name.toCharArray()) {
			if (Character.isWhitespace(c)) result = false;
		}
		return result;
	}

	public String getName () {
		return this.name;
	}

	public void setName (String name) throws BadFileNameException {
		if (!(validateName(name))) throw new BadFileNameException();
		this.name = name;
	}

	public DirectoryNode getLeft () {return left;}
	public DirectoryNode getMiddle () {return middle;}
	public DirectoryNode getRight () {return right;}
	public boolean getIsFile () {return isFile;}
	public void setLeft (DirectoryNode left) {this.left = left;}
	public void setMiddle (DirectoryNode middle) {this.middle = middle;}
	public void setRight (DirectoryNode right) {this.right = right;}
	public void setIsFile (boolean isFile) {this.isFile = isFile;}
	public UUID getId () {return id;}

	/**
	 * This method attempts to assign newNode to a null pointer
	 * 
	 * @throws NotADirectoryException when the object is a file instance of Directory Node.
	 * @throws FullDirectoryException when no nodes are null.
	 */
	public void addChild (DirectoryNode newNode) throws NotADirectoryException, FullDirectoryException {
		
		if (left != null) left = newNode;
		if (middle != null) middle = newNode;
		if (right != null) right = newNode;
		throw new FullDirectoryException();
	}

	/**
	 * This method attempts to find a Node that has a name matching the argument 
	 * and assign that Node's pointer to null (dereferencing it for garbage
	 * collection).
	 * 
	 * @throws NoSuchFileOrDirectoryException when no nodes match the name argument.
	 */
	public void remove (String name) throws NoSuchFileOrDirectoryException {
		if (left.getName().equals(name)) left = null;
		if (middle.getName().equals(name)) middle = null;
		if (right.getName().equals(name)) right = null;
		throw new NoSuchFileOrDirectoryException();
	}

	public boolean equals (DirectoryNode dn) {
		return dn.getId().equals(this.id);
	}

	public String toString () {
		return "(\"name\": " + this.name + ", \"id:\" " + this.id + ")";
	}
}
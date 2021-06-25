/**
 * LazyBinarySearchTree
 */
public class LazyBinarySearchTree {

    private TreeNode root;
    private int size;

    private class TreeNode
    {
    	int element;
    	boolean isDeleted;
        TreeNode leftChild;
        TreeNode rightChild;
    		
        TreeNode(int e)
        {
    		element = e;
    	}
    }

    public LazyBinarySearchTree ()
    {
        size = 0;
    }
    
    public LazyBinarySearchTree (int rootElement)
    {
        this.root = new TreeNode(rootElement);
        incrementSize();
    }

    public boolean insert (int element) throws IllegalArgumentException 
    {
        if(element<1 || element>99)
        {
           throw new IllegalArgumentException(String.format("element: %d must be between 1 and 99", element));
        }

        if (root == null)
        {
            root = new TreeNode(element);
            incrementSize();
            return true; 
        }

        return recursiveInsert (element, root);
    }

    private boolean recursiveInsert(int element, TreeNode current)
    {
        if (element == current.element)
        {
            current.isDeleted = false;
            return false;
        }

        if (element < current.element)
        {
            if (current.leftChild == null)
            {
                current.leftChild = new TreeNode(element);
                incrementSize();
                return true;
            }

            return recursiveInsert (element, current.leftChild);
        }

        if (current.rightChild == null)
        {
            current.rightChild = new TreeNode(element);
            incrementSize();
            return true;
        }

        return recursiveInsert (element, current.rightChild);
    }

    public boolean delete (int element) throws IllegalArgumentException
    {
        if(element<1 || element>99)
        {
           throw new IllegalArgumentException(String.format("element: %d must be between 1 and 99", element));
        }

        return recursiveDelete (element, root);
    }

    private boolean recursiveDelete (int element, TreeNode node)
    {
        if (node == null)
        {
            return false;
        }

        if (element == node.element)
        {
            boolean currentStatus = node.isDeleted;
            node.isDeleted = true;
            return !currentStatus;
        }

        if (element < node.element)
        {
            return recursiveDelete (element, node.leftChild);
        }

        return recursiveDelete (element, node.rightChild);
    }

    public int findMin()
    {
        if (root == null)
        {
            return -1;
        }

        return recursiveFindMin(root);
    }

    private int recursiveFindMin (TreeNode node)
    {
        if (node == null)
        {
            return -1;
        }

        if (leftSubtreeExists (node))
        {
            return recursiveFindMin (node.leftChild);
        }

        if (!node.isDeleted)
        {
            return node.element;
        }

        if (rightSubtreeExists (node))
        {
            return recursiveFindMin (node.rightChild);
        }

        return -1;
    }

    public int findMax()
    {
        if (root == null)
        {
            return -1;
        }

        return recursiveFindMax(root);
    }

    private int recursiveFindMax(TreeNode node)
    {
        if (node == null)
        {
            return -1;
        }

        if (rightSubtreeExists (node))
        {
            return recursiveFindMax (node.rightChild);
        }

        if (!node.isDeleted)
        {
            return node.element;
        }

        if (leftSubtreeExists (node))
        {
            return recursiveFindMax (node.leftChild);
        }

        return -1;
    }

    private boolean rightSubtreeExists (TreeNode node)
    {
        if (node == null)
        {
            return false;
        }

        if (node.rightChild == null)
        {
            return false;
        }

        if (!node.rightChild.isDeleted)
        {
            return true;
        }

        return leftSubtreeExists(node.rightChild) || rightSubtreeExists (node.rightChild);
    }

    private boolean leftSubtreeExists (TreeNode node)
    {
        if (node == null)
        {
            return false;
        }

        if (node.leftChild == null)
        {
            return false;
        }

        if (!node.leftChild.isDeleted)
        {
            return true;
        }

        return leftSubtreeExists(node.leftChild) || rightSubtreeExists (node.leftChild);
    }

    public boolean contains (int element) throws IllegalArgumentException
    {
        if(element<1 || element>99)
        {
           throw new IllegalArgumentException(String.format("element: %d must be between 1 and 99", element));
        }

        return recursiveContains (element, root);
    }

    private boolean recursiveContains (int element, TreeNode node)
    {
        if (node == null)
        {
            return false;
        }

        if (element == node.element)
        {
            if (node.isDeleted)
            {
                return false;
            }

            return true;
        }

        if (element < node.element)
        {
            return recursiveContains (element, node.leftChild);
        }

        return recursiveContains (element, node.rightChild);
    }

    public String toString()
    {
        return preOrderRecursiveString (root);
    }

    private String preOrderRecursiveString (TreeNode node)
    {
        if (node == null)
        {
            return "";
        }
        
        StringBuilder returnString = new StringBuilder(" ");
        if (node.isDeleted)
        {
            returnString.append("*");
        }

        returnString.append(node.element);
        returnString.append(preOrderRecursiveString (node.leftChild));
        returnString.append(preOrderRecursiveString (node.rightChild));

        return returnString.toString();
    }

    public int size ()
    {
        if (root == null)
        {
            return -1;
        }

        return size;
    }

    public int height ()
    {
        return calculateHeight (root);
    }

    public int calculateHeight (TreeNode node)
    {
        if (node == null)
        {
            return -1;
        }

        return 1 + Math.max (calculateHeight (node.leftChild), calculateHeight (node.rightChild));
    }

    private void incrementSize()
    {
        size++;
    }
}
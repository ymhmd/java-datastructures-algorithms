package tress;

public class BST {

    class BSTNode {
        int value;
        BSTNode right;
        BSTNode left;
    }

    private int size;

    private BSTNode root;

    public BST () {
        size = 0;
    }

    public void insert (int value) {
        root = insertRec(root, value);
        size++;
    }

    BSTNode insertRec(BSTNode startingNode, int value) {
        if (startingNode == null) {
            BSTNode node = new BSTNode();
            node.value = value;
            startingNode = node;
            return startingNode;
        }

        if (value > startingNode.value) {
            startingNode.right = insertRec(startingNode.right, value);
        } else if (value < startingNode.value) {
            startingNode.left = insertRec(startingNode.left, value);
        }

        return startingNode;
    }

    public BSTNode search (int value) {
        return searchRec(root, value);
    }

    public BSTNode searchRec (BSTNode startingNode, int value) {
        if (startingNode == null) return null;

        if (startingNode.value == value) return startingNode;

        if (value > startingNode.value) {
            startingNode = searchRec(startingNode.right, value);
        } else if(value < startingNode.value) {
            startingNode = searchRec(startingNode.left, value);
        }
        return startingNode;
    }

    public BSTNode deleteRec(BSTNode startingNode, int value) {
        if (startingNode == null) return null;

        if (value < startingNode.value) {
            startingNode.left = deleteRec(startingNode.left, value);
        } else if (value > startingNode.value) {
            startingNode.right = deleteRec(startingNode.right, value);
        } else {
            if (startingNode.right == null) {
                return startingNode.left;
            } else if (startingNode.left == null) {
                return startingNode.right;
            } else {
                startingNode.value = minValue(startingNode.right);

                startingNode.right = deleteRec(startingNode.right, startingNode.value);
            }
        }

        return startingNode;
    }

    public int minValue(BSTNode startingNode) {
        int min = startingNode.value;

        while (startingNode.left != null) {
            startingNode = startingNode.left;
            min = startingNode.value;
        }
        return min;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isLeaf(BSTNode node) {
        return node.right == null && node.left == null;
    }


    public int kthSmallest(BSTNode root, int k) {
        int i = 0;
        while (i < k - 1) {
            int min = minValue(root);
            root = deleteRec(root, min);
            i++;
        }

        return minValue(root);
    }



    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(10);
        bst.insert(3);
        bst.insert(11);
        bst.insert(2);
        bst.insert(133);

        System.out.println(bst.search(10).value);
        System.out.println(bst.search(100098));

        System.out.println(bst.kthSmallest(bst.root, 5));
    }

}

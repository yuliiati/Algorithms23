import java.util.LinkedList;
import java.util.Queue;

public class BalancedTreeImpl implements BalancedTree {
    private TreeNode root;
    private int size;

    public BalancedTreeImpl() {
        root = null;
        size = 0;
    }

    @Override
    public void add(int data) {
        if (root == null) {
            root = new TreeNode(data);
            size++;
        } else {
            addNode(root, data);
        }
    }

    private void addNode(TreeNode currentNode, int data) {
        if (data < currentNode.data) {
            if (currentNode.leftNode == null) {
                currentNode.leftNode = new TreeNode(data);
                size++;
            } else {
                addNode(currentNode.leftNode, data);
            }
        } else if (data > currentNode.data) {
            if (currentNode.rightNode == null) {
                currentNode.rightNode = new TreeNode(data);
                size++;
            } else {
                addNode(currentNode.rightNode, data);
            }
        }
    }

    @Override
    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(TreeNode currentNode, int data) {
        if (currentNode == null) {
            return false;
        }

        if (data == currentNode.data) {
            return true;
        } else if (data < currentNode.data) {
            return searchNode(currentNode.leftNode, data);
        } else {
            return searchNode(currentNode.rightNode, data);
        }
    }

    @Override
    public void remove(int data) {
        removeNode(null, root, data);
    }

    private void removeNode(TreeNode parentNode, TreeNode currentNode, int data) {
        if (currentNode == null) {
            return;
        }

        if (data == currentNode.data) {
            if (currentNode.leftNode == null && currentNode.rightNode == null) {
                if (parentNode == null) {
                    root = null;
                } else if (parentNode.leftNode == currentNode) {
                    parentNode.leftNode = null;
                } else {
                    parentNode.rightNode = null;
                }
            } else if (currentNode.leftNode == null) {
                if (parentNode == null) {
                    root = currentNode.rightNode;
                } else if (parentNode.leftNode == currentNode) {
                    parentNode.leftNode = currentNode.rightNode;
                } else {
                    parentNode.rightNode = currentNode.rightNode;
                }
            } else if (currentNode.rightNode == null) {
                if (parentNode == null) {
                    root = currentNode.leftNode;
                } else if (parentNode.leftNode == currentNode) {
                    parentNode.leftNode = currentNode.leftNode;
                } else {
                    parentNode.rightNode = currentNode.leftNode;
                }
            } else {
                TreeNode successor = findMinNode(currentNode.rightNode);
                currentNode.data = successor.data;
                removeNode(currentNode, currentNode.rightNode, successor.data);
            }

            size--;
        } else if (data < currentNode.data) {
            removeNode(currentNode, currentNode.leftNode, data);
        } else {
            removeNode(currentNode, currentNode.rightNode, data);
        }
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.leftNode != null) {
            node = node.leftNode;
        }
        return node;
    }

    @Override
    public int getDepth() {
        return calculateDepth(root);
    }

    private int calculateDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = calculateDepth(node.leftNode);
        int rightDepth = calculateDepth(node.rightNode);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void traversalWidth() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");

            if (node.leftNode != null) {
                queue.add(node.leftNode);
            }

            if (node.rightNode != null) {
                queue.add(node.rightNode);
            }
        }

        System.out.println();
    }

    @Override
    public void traversalDepth() {
        traversalDepth(root);
        System.out.println();
    }

    private void traversalDepth(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        traversalDepth(node.leftNode);
        traversalDepth(node.rightNode);
    }

    private static class TreeNode {
        private int data;
        private TreeNode leftNode;
        private TreeNode rightNode;

        public TreeNode(int data) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
        }
    }
}

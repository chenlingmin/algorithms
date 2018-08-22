package com.chenlm.struct;

/**
 * 左倾红黑树 2-3 树
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " dosn't exist!");
        }
        node.value = value;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }


    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value); // 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = node.right.color = BLACK;
//        if (node != null) {
//            node.color = node.color == RED ? BLACK : RED;
//            flipColors(node.left);
//            flipColors(node.right);
//        }
    }


    /*
    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
   */
    private Node leftRotate(Node node) {

        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /* 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    */
    private Node rightRotate(Node node) {

        Node x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color  = RED;
        return x;
    }



    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }


    public static void main(String[] args) {
        RBTree<String, Integer> rbTree = new RBTree<>();
        rbTree.add("a", 1);
        rbTree.add("b", 1);
        rbTree.add("c", 1);
        rbTree.add("d", 1);
        rbTree.add("e", 1);
        rbTree.add("h", 1);
        rbTree.add("i", 1);
        rbTree.add("j", 1);
        rbTree.add("k", 1);
        rbTree.add("l", 1);
        rbTree.add("m", 1);
        rbTree.add("n", 1);
        rbTree.add("o", 1);
        rbTree.add("p", 1);
        rbTree.add("q", 1);
        System.out.println(rbTree);
    }
}

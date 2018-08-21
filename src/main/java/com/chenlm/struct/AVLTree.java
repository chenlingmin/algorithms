package com.chenlm.struct;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return 0 == this.size;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (null != node) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        } else {
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    private Node add(Node node, K key, V value) {
        if (null == node) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced: " + balanceFactor);
//        }

        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 & getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /*
    对节点 y 进行向右旋转操作，返回旋转操后的新的根节点 x

              y                              x
             / \                           /   \
            x   T4     向右旋转 (y)        z     y
           / \       - - - - - - - ->    / \   / \
          z   T3                       T1  T2 T3 T4
         / \
       T1   T2
     */
    private Node rightRotate(Node y) {

        Node x = y.left;
        Node t3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = t3;

        // 更新 height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /* 对节点y进行向左旋转操作，返回旋转后新的根节点x
          y                             x
        /  \                          /   \
       T1   x      向左旋转 (y)       y     z
           / \   - - - - - - - ->   / \   / \
         T2  z                     T1 T2 T3 T4
            / \
           T3 T4
    */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
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

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
//                successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced: " + balanceFactor);
//        }

        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // RR
        if (balanceFactor < -1 & getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    private Node minimum(Node node) {
        if (node.left == node) {
            return node;
        }
        return minimum(node.left);
    }

    @Deprecated
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

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int height;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }
    }

    public static void main(String[] args) {
        AVLTree<String, Integer> stringIntegerAVLTree = new AVLTree<>();
        stringIntegerAVLTree.add("a", 3);
        stringIntegerAVLTree.add("b", 3);
        stringIntegerAVLTree.add("c", 3);
        stringIntegerAVLTree.add("d", 3);
        stringIntegerAVLTree.add("e", 3);
        stringIntegerAVLTree.add("f", 3);
        stringIntegerAVLTree.add("g", 3);
        stringIntegerAVLTree.add("h", 3);
        stringIntegerAVLTree.add("i", 3);
        stringIntegerAVLTree.add("j", 3);
        stringIntegerAVLTree.add("k", 3);
        stringIntegerAVLTree.remove("a");
        stringIntegerAVLTree.remove("k");
        stringIntegerAVLTree.remove("g");

        System.out.println(stringIntegerAVLTree);
        System.out.println(stringIntegerAVLTree.isBalanced());
        System.out.println(stringIntegerAVLTree.isBST());
    }
}

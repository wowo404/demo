package org.liu.tree;

public class TestBinarySortTree {

    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        tree.insert(6, 6);
        tree.insert(8, 8);
        tree.insert(9, 9);
        tree.insert(5, 5);
        tree.insert(11, 11);
        tree.insert(7, 7);
        tree.insert(4, 4);
        tree.insert(1, 1);
        tree.insert(3, 3);
        tree.insert(10, 10);
        tree.insert(12, 12);

        System.out.println(tree.delete(8));
        System.out.println(tree.get(8));
    }

}

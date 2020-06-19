package org.liu.tree;

public class TestBinarySortTree {

    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        tree.add(60, 60);
        tree.add(80, 80);
        tree.add(20, 20);
        tree.add(150, 150);
        tree.add(75, 75);
        tree.add(40, 40);
        tree.add(10, 10);
        tree.add(160, 160);
        tree.add(100, 100);
        tree.add(77, 77);
        tree.add(70, 70);
        tree.add(50, 50);
        tree.add(30, 30);
        tree.add(15, 15);
        tree.add(5, 5);

        System.out.println(tree.delete(20));
        System.out.println(tree.get(20));
    }

}

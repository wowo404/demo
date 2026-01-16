package org.liu.designpatterns.structuralschema.composite;

public class Tree {

    private TreeNode root = null;

    public Tree(String name) {
        root = new TreeNode(name);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}

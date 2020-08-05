package algorithm.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * 平衡二叉树
 */
public class AVLTree {

    private Node root;
    private int size;

    /**
     * 插入或覆盖value
     *
     * @param key
     * @param value
     * @return 新增时返回null，覆盖时返回原value
     */
    public Object insert(Integer key, Object value) {
        if (null == root) {
            root = new Node(key, value);
            size++;
            return null;
        }
        Node cNode = root;Object oldValue;
        while (true) {
            if (key > cNode.key) {
                if (cNode.right == null) {
                    cNode.right = new Node(key, value);
                    cNode.right.parent = cNode;
                    break;
                } else {
                    cNode = cNode.right;
                }
            } else if (key.equals(cNode.key)) {
                if (!value.equals(oldValue = cNode.value)) {
                    cNode.value = value;
                    return oldValue;
                }
            } else {
                if (cNode.left == null){
                    cNode.left = new Node(key, value);
                    cNode.left.parent = cNode;
                    break;
                } else {
                    cNode = cNode.left;
                }
            }
        }
        size++;
        return null;
    }

    /**
     * 删除
     *
     * @param key
     * @return 被删除的value
     */
    public Object delete(Integer key) {

        size--;
        return null;
    }

    /**
     * 查询
     *
     * @param key
     * @return 存在则返回value，不存在返回null
     */
    public Object get(Integer key) {
        return null;
    }

    /**
     * @return 根节点的值
     */
    public Object rootValue(){
        return root.value;
    }

    /**
     * @return 树的总节点数
     */
    public int size() {
        return size;
    }

    @Getter
    @Setter
    class Node {
        private Integer key;
        private Object value;
        private int depth;//节点深度
        private int balanceFactor;//平衡因子，左子树高度减去右子树高度，一颗平衡二叉树的平衡因子只能是-1，0，1
        private Node parent;
        private Node left;
        private Node right;

        Node(Integer key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

}

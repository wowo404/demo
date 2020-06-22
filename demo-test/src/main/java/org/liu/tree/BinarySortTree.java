package org.liu.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * 二叉排序树（Binary Sort Tree），又称二叉查找树（Binary Search Tree），亦称二叉搜索树
 */
public class BinarySortTree {
    private Node root;
    private int size;

    /**
     * 插入或覆盖value
     *
     * @param key
     * @param value
     * @return 新增时返回null，覆盖时返回原value
     */
    public Object add(Integer key, Object value) {
        if (null == key || null == value) {
            throw new IllegalArgumentException();
        }
        if (null == root) {
            root = new Node(key, value);
        } else {
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
                    if (cNode.left == null) {
                        cNode.left = new Node(key, value);
                        cNode.left.parent = cNode;
                        break;
                    } else {
                        cNode = cNode.left;
                    }

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
     * @return 被删除的value，key不存在则返回null
     */
    public Object delete(Integer key) {
        if (null == key) {
            throw new IllegalArgumentException();
        }
        if (null == root) {
            return null;
        }
        return delete(root, key);
    }

    private Object delete(Node node, Integer key) {
        Object oldValue = null;
        do {
            if (key > node.key) {
                node = node.right;
            } else if (key.equals(node.key)) {
                if (node.parent.left.equals(node)) {//删除的是node.parent左侧树的结点
                    if (null == node.left && null == node.right) {//没有叶子，直接删除就可以，放最后操作
                        node.parent.left = null;
                    } else if (null == node.right) {//被删除节点没有右子树
                        node.parent.left = node.left;//把被删除节点的左子树提上来就可以
                    } else if (null == node.left) {//被删除节点没有左子树
                        node.parent.left = node.right;//把被删除节点的右子树提上来就可以
                    } else {
                        if (null != node.right.left) {
                            //被提升节点的左子节点怎么办??挂到叔叔节点下最大的那个节点下
                            Node max = findMax(node.left);
                            node.right.left.parent = max;
                            max.right = node.right.left;
                            node.right.left = node.left;//被提升节点的左子节点要修改为被删除节点的左子节点
                        }
                        node.left.parent = node.right;
                        node.right.parent = node.parent;
                        node.parent.left = node.right;
                    }
                } else if (node.parent.right.equals(node)) {//删除的是node.parent右侧树的结点
                    if (null == node.left && null == node.right) {//没有叶子，直接删除就可以，放最后操作
                        node.parent.right = null;
                    } else if (null == node.right) {//被删除节点没有右子树
                        node.parent.right = node.left;//把被删除节点的左子树提上来就可以
                    } else if (null == node.left) {//被删除节点没有左子树
                        node.parent.right = node.right;//把被删除节点的右子树提上来就可以
                    } else {
                        if (null != node.right.left) {
                            //被提升节点的左子节点怎么办??
                            Node max = findMax(node.left);
                            node.right.left.parent = max;
                            max.right = node.right.left;
                            node.right.left = node.left;//被提升节点的左子节点要修改为被删除节点的左子节点
                        }
                        node.left.parent = node.right;//被删除节点的左子节点的父节点修改为新的被提升的节点
                        node.right.parent = node.parent;//被提升节点的父节点要修改为原被删除节点的父节点
                        node.parent.right = node.right;//替换要放到最后
                    }
                }

                oldValue = node.value;
                node = null;
            } else {
                node = node.left;
            }
        } while (node != null);
        if (null != oldValue) {
            size--;
        }
        return oldValue;
    }

    private void rotateLeft(Node node){

    }

    private void rotateRight(Node node){

    }

    private Node findMax(Node node) {
        if (null != node) {
            if (null != node.right) {
                return findMin(node.right);
            } else {
                return node;
            }
        }
        return null;
    }

    private Node findMin(Node node) {
        if (null != node) {
            if (null != node.left) {
                return findMin(node.left);
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 查询
     *
     * @param key
     * @return 存在则返回value，不存在返回null
     */
    public Object get(Integer key) {
        if (null == key) {
            throw new IllegalArgumentException();
        }
        Node node = get(root, key);
        return null == node ? null : node.value;
    }

    private Node get(Node node, Integer key) {
        if (null != node) {
            if (key > node.key) {
                return get(node.right, key);
            } else if (key.equals(node.key)) {
                return node;
            } else {
                return get(node.left, key);
            }
        }
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
        private Node parent;
        private Node left;
        private Node right;

        Node(Integer key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}

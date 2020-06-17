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
    public Object insert(Integer key, Object value) {
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
                Node newNode = null;
                //找回右子树中的最小节点
                Node min = findMin(node.right);
                if (null != min) {
                    min.parent = node.parent;
                    min.left = node.left;
                    if (null == min.right) min.right = node.right;
                    newNode = min;
                } else {
                    //找出左子树中的最大节点
                    Node max = findMax(node.left);
                    if (null != max) {
                        max.parent = node.parent;
                        if (null == max.left) max.left = node.left;
                        max.right = node.right;
                        newNode = max;
                    }
                }
                if (node.parent.left.equals(node)) {
                    node.parent.left = newNode;
                } else if (node.parent.right.equals(node)) {
                    node.parent.right = newNode;
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

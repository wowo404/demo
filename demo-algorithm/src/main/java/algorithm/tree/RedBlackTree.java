package algorithm.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * 红黑树
 * https://www.cnblogs.com/tiancai/p/9072813.html
 */
public class RedBlackTree {

    private Node root;
    private int size;

    public Object add(Integer key, Object value){
        //1.根据key的比对，查找到空节点，放入，如果key存在，则覆盖，返回旧的value
        //2.自平衡：红黑判断，左旋，右旋
        return null;
    }

    public Object delete(Integer key){
        //1.根据key的比对，查找对应节点，删除，返回被删除节点的value，如果key不存在，返回null
        //2.自平衡：红黑判断，左旋，右旋
        return null;
    }

    public Object get(Integer key){

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
        private boolean red;//是否红色节点
        private Node parent;
        private Node left;
        private Node right;

        Node(Integer key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

}

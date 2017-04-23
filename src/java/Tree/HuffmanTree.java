package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 65401 on 2017/3/8.
 * 哈夫曼树
 */
public class HuffmanTree {

    public static Node createTree(List<Node> list){
        //节点带权路径长度
        int sum=0;
        //list中至少两个以上节点
        while(list.size()>1){
            //排序
            Collections.sort(list);
            //获取权值最小的两个节点
            Node left=list.get(0);
            Node right=list.get(1);
            //生成新节点，新节点的权值为两个子节点的权值之和
            Node parent=new Node(null,left.getWeight()+right.getWeight());
            sum+=left.getWeight()+right.getWeight();
            parent.setLeft(left);
            parent.setRight(right);
            //删除权值最小的两个节点
            list.remove(left);
            list.remove(right);
            //将新生成的节点添加到集合中
            list.add(parent);
        }
        if(sum!=0){
            System.out.println("summary: "+sum);
        }
        return list.get(0);
    }

    //广度遍历
    public static void breadth(Node node){
        ArrayDeque<Node> array=new ArrayDeque<>();
        array.add(node);

        while(!array.isEmpty()){
            Node newNode=array.remove();
            System.out.print(newNode.getWeight()+" ");
            if(newNode.getLeft()!=null){
                array.add(newNode.getLeft());
            }
            if(newNode.getRight()!=null){
                array.add(newNode.getRight());
            }
        }

    }

    public static void main(String[] args) {
        List<Node> list=new ArrayList<>();
        list.add(new Node("a",9));
        list.add(new Node("b",5));
        list.add(new Node("c",4));
        list.add(new Node("d",2));

        createTree(list);
        System.out.print("breadth order: ");
        breadth(createTree(list));
    }
}

class Node implements Comparable<Node>{
    private Object data;
    private int weight;//    权重
    private Node left;
    private Node right;

    public Node(Object data,int weight){
        this.data=data;
        this.weight=weight;
    }

    public int getWeight(){
        return weight;
    }

    public Node setLeft(Node left){
        return this.left=left;
    }

    public Node setRight(Node right){
        return this.right=right;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public Object getData(){
        return data;
    }

    @Override
    public String toString() {
        return "data: "+this.data+"; weight: "+this.weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.getWeight();
    }
}

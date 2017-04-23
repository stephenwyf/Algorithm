package Graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by 65401 on 2017/3/18.
 * 迪杰斯特拉(Dijkstra)算法
 * 计算最短路径
 */
public class Dijkstra {

    public class Node{
        private String name;
        private Map<Node, Integer>child=new HashMap<>();//  子节点
        private Node(String name){
            this.name=name;
        }
        public Map<Node, Integer> getChild() {
            return child;
        }

    }

    private Set<Node> open=new HashSet<>();//       open用于存储未遍历的点
    private Set<Node> close=new HashSet<>();//      close用来存储已遍历的节点
    private Map<String,Integer> path=new HashMap<>();//        封装路径距离


    private void dijkstra(Node start){
        Node nearestNode=getShortestPath(start);//  距离start最近的顶点

        System.out.print(start.name+":"+path.get(start.name)+"  ");
        if(nearestNode==null){//        没有邻接点
            return ;
        }
        //已经访问过
        close.add(nearestNode);
        open.remove(nearestNode);
        //nearestNode邻接的所有顶点
        Map<Node,Integer> map=nearestNode.getChild();
        //遍历邻接的所有的键
        for(Node child: map.keySet()){
            if(open.contains(child)){
                int i1=path.get(nearestNode.name);//        之前一个顶点的距离
                int i2=map.get(child);//        当前顶点附近没访问过的顶点的距离
                int distance=i1+i2;
                int i3=path.get(child.name);
                //证明还有更短的路径就是distance
                //改变path里到各个顶点的权值
                if(i3>distance){
                    path.put(child.name,distance);
                }
            }
        }
        //递归
        dijkstra(nearestNode);
    }


    //找到离查找的顶点最近的顶点
    private Node getShortestPath(Node node){
        Node res=null;
        int min=Integer.MAX_VALUE;
        Map<Node, Integer> map=node.getChild();
        //遍历邻接的所有的键
        for(Node tmp:map.keySet()){
            if(open.contains(tmp)){
                int distance=map.get(tmp);
                if(distance<min){//比最小的还小，就是我们需要的
                    min=distance;
                    res=tmp;
                }
            }
        }
        return res;
    }

    /*
                                        V3
                                    7   |    3
                                V1     2|       V6
                             1  |   5   |   6  |    7
                        V0     3|       V4    2|        V8
                             5  |   1   |   9  |    4
                               V2      3|      V7
                                    7   |   5
                                        V5
     */
    //把顶点关联起来
    private Node build(){
        Node node0=new Node("V0");
        Node node1=new Node("V1");
        Node node2=new Node("V2");
        Node node3=new Node("V3");
        Node node4=new Node("V4");
        Node node5=new Node("V5");
        Node node6=new Node("V6");
        Node node7=new Node("V7");
        Node node8=new Node("V8");

        node0.getChild().put(node1, 1);//V0--V1 权值1
        node0.getChild().put(node2, 5);//V0--V2 权值5

        node1.getChild().put(node0, 1);
        node1.getChild().put(node2, 3);
        node1.getChild().put(node3, 7);
        node1.getChild().put(node4, 5);

        node2.getChild().put(node0, 5);
        node2.getChild().put(node1, 3);
        node2.getChild().put(node4, 1);
        node2.getChild().put(node5, 7);

        node3.getChild().put(node1, 7);
        node3.getChild().put(node4, 2);
        node3.getChild().put(node6, 3);

        node4.getChild().put(node1, 5);
        node4.getChild().put(node2, 1);
        node4.getChild().put(node3, 2);
        node4.getChild().put(node5, 3);
        node4.getChild().put(node6, 6);
        node4.getChild().put(node7, 9);

        node5.getChild().put(node2, 7);
        node5.getChild().put(node4, 3);
        node5.getChild().put(node7, 5);


        node6.getChild().put(node3, 3);
        node6.getChild().put(node4, 6);
        node6.getChild().put(node7, 2);
        node6.getChild().put(node8, 7);


        node7.getChild().put(node4, 9);
        node7.getChild().put(node6, 2);
        node7.getChild().put(node8, 4);

        node8.getChild().put(node6, 7);
        node8.getChild().put(node7, 4);

        //初始节点放入close
        close.add(node0);
        //初始节点放入close 其他节点放入open
        open.add(node1);
        open.add(node2);
        open.add(node3);
        open.add(node4);
        open.add(node5);
        open.add(node6);
        open.add(node7);
        open.add(node8);

        path.put("V1", 1);//V0--V1
        path.put("V2", 5);//V0--V2
        path.put("V3",Integer.MAX_VALUE);//V0--V3,因为没有直线连接，索引用最大值
        path.put("V4", Integer.MAX_VALUE);//V0--V4
        path.put("V5", Integer.MAX_VALUE);//V0--V5
        path.put("V6", Integer.MAX_VALUE);//V0--V6
        path.put("V7", Integer.MAX_VALUE);//V0--V7
        path.put("V8", Integer.MAX_VALUE);//V0--V8

        return node0;
    }

    @Test
    public void test(){
        Node node=this.build();
        dijkstra(node);
    }
}

package Graph;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/12.
 * 邻接表
 * 用数组和链表来表示图
 */
public class ListArrayGraph {
    //顶点数组
    private VNode[] top;
    //顶点
    private class VNode{
        Object data;
        ENode firstEdge;//      顶点下的第一条边
    }
    //边
    private class ENode{
        ENode nextEdge;//       指向下一个边
        int VIndex;//       指向下一个顶点的索引
    }

    @Test
    public void test(){
        String[] vexs = {"V0", "V1", "V2", "V3"};
        String[][] edges = new String[][]{//有向图，全部表示节点之间的关系即可
                {"V0", "V3"},
                {"V1", "V0"},
                {"V1", "V2"},
                {"V2", "V0"},
                {"V2", "V1"}
        };

        //初始化顶点
        top=new VNode[vexs.length];
        for(int i=0;i<vexs.length;i++){

            VNode node=new VNode();
            node.data=vexs[i];
            node.firstEdge=null;
            top[i]=node;
        }

        //初始化边
        for(int i=0;i<edges.length;i++){
            ENode node=new ENode();
            int vex=this.getPosition(edges[i][0]);//        顶点
            int nextvex=this.getPosition(edges[i][1]);//       指向的下一个顶点
            node.VIndex=nextvex;
            //顶点没有挂上边
            if(top[vex].firstEdge==null){
                top[vex].firstEdge=node;
            }else{
                this.getEndOfList(top[vex].firstEdge,node);
            }
        }
        this.print();
    }

    //查找顶点数组中的索引
    private int getPosition(String s){
        for(int i=0;i<top.length;i++){
            if(top[i].data==s){
                return i;
            }
        }
        return -1;
    }

    //把新的边放到边链表的最后一个
    private void getEndOfList(ENode firstEdge,ENode newEdge){
        ENode temp=firstEdge;
        while(temp.nextEdge!=null){
            temp=temp.nextEdge;
        }
        temp.nextEdge=newEdge;
    }

    //打印
    private void print(){
        for(int i=0;i<top.length;i++){
            System.out.print(top[i].data);
            //有边
            if(top[i].firstEdge!=null){
                ENode node=top[i].firstEdge;
                System.out.println(": "+top[node.VIndex].data);
                //不止一个边
                while(node.nextEdge!=null){
                    node=node.nextEdge;
                    System.out.println(top[i].data+": "+top[node.VIndex].data);
                }
            }
        }
    }
}

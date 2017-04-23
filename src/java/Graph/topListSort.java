package Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by 65401 on 2017/3/19.
 * 拓扑排序
 */
public class topListSort {

    //邻接表中表对应的链表的顶点
    public class ENode{
        int itop;//     该边所指向的顶点的位置
        ENode next;//       指向下条弧的指针    (B-A).next=(B-D)
    }
    //邻接表中表的顶点
    public class VNode{
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    }

    /*
        B    >   A    >   G
        |                 ^
        v                 |
        D    >   F    <   C
        |
        v
        E

     */

    private List<VNode> mVexs=new ArrayList<>();  //  顶点数组
    char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};//    顶点数组
    char[][] edges = new char[][]{//    边数组，例如A->G B->A等
            {'A', 'G'},
            {'B', 'A'},
            {'B', 'D'},
            {'C', 'G'},
            {'C', 'F'},
            {'D', 'E'},
            {'D', 'F'}
    };


    //找到顶点的索引
    private int getPosition(char ch){
        //优化查找
        int i=vexs.length-1;
        vexs[0]=ch;//       设置哨兵
        while(vexs[i]!=ch){
            i--;
        }
        return i;
    }

    /**
     * 把新节点挂在链的最后一个
     * @param list:老节点
     * @param last:新节点
     */
    private void linkLast(ENode list,ENode last){
        ENode p=list;
        while(p.next!=null){
            p=p.next;
        }
        p.next=last;
    }


    //排序
    private void sort(){
        //入度数组
        int in[]=new int[mVexs.size()];
        //装入入度为零的顶点
        Queue<Integer> queue=new LinkedList<>();
        //统计每个节点的入度数，并放入到入度数组中
        ENode eNode=null;
        for(int i = 0; i < mVexs.size(); i++){
            eNode=mVexs.get(i).firstEdge;
            while(eNode!=null){
                //顶点被遍历一次该顶点的入度+1
                in[eNode.itop]++;
                eNode=eNode.next;
            }
        }
        for(int i=0;i<mVexs.size();i++){
            //入度为零
            if(in[i]==0){
                queue.offer(i);//       找到入度为零的顶点加入queue中
            }
        }

        int index=0;
        char[] result=new char[mVexs.size()];
        //拆边        排序
        while(!queue.isEmpty()){
            //取出入度为零的点
            int i=queue.poll();
            result[index++]=mVexs.get(i).data;
            //入度为零的点指向的顶点
            ENode enode=mVexs.get(i).firstEdge;
            while(enode!=null){
                in[enode.itop]--;//     循环一次，拆解一次，让其入度--
                if(in[enode.itop]==0){//    入度为0后，代表该节点拆解完毕，
                    queue.offer(enode.itop);//      把当前边对象所指定的节点索引加入队列中 成为下个遍历的对象
                }
                enode=enode.next;
            }
        }

        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
    }


    @Test
    public void test(){
        //形成顶点集合
        for(int i=0;i<vexs.length;i++){
            VNode tmp=new VNode();
            tmp.data=vexs[i];
            tmp.firstEdge = null;
            mVexs.add(tmp);
        }
        //初始化边，形成链
        for(int i=0;i<edges.length;i++){
            char c1=edges[i][0];
            char c2=edges[i][1];
            int i1=getPosition(c1);
            int i2=getPosition(c2);
            //初始化边
            ENode eNode=new ENode();
            eNode.itop=i2;
            //把边两端的顶点关联
            if(mVexs.get(i1).firstEdge==null){
                //当前边中没有关联的节点
                mVexs.get(i1).firstEdge=eNode;
            }else{
                //把新的节点挂在链中的最后一个
                linkLast(mVexs.get(i1).firstEdge,eNode);
            }
        }
        sort();
    }

}

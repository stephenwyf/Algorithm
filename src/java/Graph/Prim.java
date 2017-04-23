package Graph;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/13.
 * Prim算法  最小生成树
 */
public class Prim {

    private char[] mVexs;//     顶点集合
    private int[][] mMatrix;//      邻接矩阵
    //最大值,也就是在邻接矩阵中，两个点如果没有边，就标记最大值
    private static final int INF=Integer.MAX_VALUE;

    //得到顶点索引
    private int getPosition(char c){
        for(int i=0;i<this.mVexs.length;i++){
            if(mVexs[i]==c){
                return i;
            }
        }
        return -1;
    }


    private void prim(int start){
        int index=0;//  结果集索引
        char[] prim=new char[mVexs.length];//   顶点集
        int[] weight=new int[mMatrix.length];//     权值集
        prim[index++]=this.mVexs[start];//      将start的顶点装进顶点集

        //start到所有顶点的位置,即start行的权值
        for(int i=0;i<mVexs.length;i++){
            weight[i]=mMatrix[start][i];
        }
        //找以start开始的点的所有邻接点的权值最小值
        for(int i=0;i<mVexs.length-1;i++){
            int j=0;
            int k=0;
            int min=INF;
            while(j<mVexs.length){

                if(weight[j]!=0&&weight[j]<min){
                    min=weight[j];
                    k=j;
                }
                j++;
            }
            //k是一行中权值最小的索引
            prim[index++]=this.mVexs[k];
            weight[k]=0;//      更改权值，因为他已经访问过了

            //把曾经访问过的顶点到k指定顶点的邻接点的权值挨个更新
            for(j=0;j<mVexs.length;j++){
                if(weight[j]!=0&&mMatrix[k][j]<weight[j]){
                    weight[j]=mMatrix[k][j];
                }
            }
        }

        //计算最小生成树的权值
        int sum=0;
        System.out.print(prim[0]);
        for(int i=1;i<index;i++){
            int min=INF;
            System.out.print(prim[i]);
            int n=getPosition(prim[i]);

            for(int j=0;j<i;j++){//
                int m=getPosition(prim[j]);
                //找到最短距离
                if(mMatrix[n][m]<min){
                    min=mMatrix[n][m];
                }
            }
            sum+=min;
        }
        System.out.println();
        System.out.println("最短路径权值: "+sum);
    }


    @Test
    public void test(){
        //顶点
        char[] mVexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        this.mVexs=mVexs;
        /*
        邻接矩阵
        0 是自己到自己的点的标记
        INF最大值,也就是在邻接矩阵中，两个点如果没有边，就标记最大值
        */
        int matrix1[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
         /*A*/ {   0,  12, INF, INF, INF,  16,  14},
         /*B*/ {  12,   0,  10, INF, INF,   7, INF},
         /*C*/ { INF,  10,   0,   3,   5,   6, INF},
         /*D*/ { INF, INF,   3,   0,   4, INF, INF},
         /*E*/ { INF, INF,   5,   4,   0,   2,   8},
         /*F*/ {  16,   7,   6, INF,   2,   0,   9},
         /*G*/ {  14, INF, INF, INF,   8,   9,   0}
        };
        this.mMatrix=matrix1;
        prim(0);
    }
}

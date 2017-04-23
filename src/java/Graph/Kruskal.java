package Graph;

/**
 * Created by 65401 on 2017/3/16.
 * Kruskal算法    最小生成树
 */
public class Kruskal {

    private class EdgeData{
        char start;//       边的起点
        char end;//         边的终点
        int weight;//       权值

        public EdgeData(char start,char end,int weight){
            this.start=start;
            this.end=end;
            this.weight=weight;
        }
    }

    private int edgeCount;//        边的数量
    private char[] tops;//       顶点集合
    private int[][] mMatrix;//      邻接矩阵
    private static final int INF=Integer.MAX_VALUE;//       最大值

    public Kruskal(char[] vexs,int[][] matrix){
        tops=new char[vexs.length];
        //初始化顶点
        for (int i = 0; i < tops.length; i++) {
            tops[i] = vexs[i];
        }
        mMatrix=new int[vexs.length][vexs.length];
        //初始化mMatrix 邻接矩阵
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                mMatrix[i][j] = matrix[i][j];
            }
        }
        //统计边的个数
        for (int i = 0; i < mMatrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (mMatrix[i][j] != INF) {//有效
                    edgeCount++;
                }
            }
        }
    }

    //得到顶点索引
    private int getPosition(char ch) {
        for(int i=0;i<this.tops.length;i++){
            if(ch==this.tops[i]){
                return i;
            }
        }
        return -1;
    }

    //获取图中的边
    private EdgeData[] getEdge(){
        EdgeData[] edata=new EdgeData[edgeCount];
        int index=0;
        for(int i = 0; i < this.tops.length; i++){
            for(int j = i + 1; j < this.tops.length; j++){
                if (this.mMatrix[i][j] < INF) {
                    EdgeData d = new EdgeData(this.tops[i], this.tops[j], this.mMatrix[i][j]);
                    edata[index++] = d;
                }
            }
        }
        return edata;
    }

    //将边按大小排序
    private void sortEdge(EdgeData[] edges,int elen){
        //冒泡排序
        for(int i=0;i<elen;i++){
            for(int j=i;j<elen;j++){
                EdgeData temp=null;
                if(edges[i].weight>edges[j].weight){
                    temp=edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
    }

    //判断是否产生回路的方法
    private int getEnd(int[] tends,int i){
        //i是起点的索引，找到其i指定的终点值tends[i]
        while(tends[i]!=0){
            i=tends[i];
        }
        return i;
    }

    private void kruskal(){
        int index=0;//      结果集索引
        int[] tends=new int[tops.length];//最小生成树中每个边的终点的索引   用于判断是否产生回路

        EdgeData[] rets=new EdgeData[edgeCount];
        //获取图中的边
        EdgeData[] ret=getEdge();
        this.sortEdge(ret,ret.length);//       按大小将边排序
        //判断是否产生回路
        for(int i=0;i<ret.length;i++){
            //得到边的起始点和终点
            int start=getPosition(ret[i].start);
            int end=getPosition(ret[i].end);
            //起点和终点最终指向的点的索引
            int p1=getEnd(tends,start);
            int p2=getEnd(tends,end);
            //没有形成回路
            if(p1!=p2){
                tends[p1]=p2;
                rets[i]=ret[i];
            }
        }

        //统计其权值和
        int sum=0;
        //图的路径数量
        System.out.println(rets.length);
        for( int i=0;i<rets.length;i++){
            if(rets[i]!=null){
                sum+=rets[i].weight;
            }
        }

        for( int i=0;i<rets.length;i++){
            if(rets[i]!=null){
                //路径
                System.out.printf("%s--%s  ",rets[i].start,rets[i].end);
            }
        }
        System.out.println();
        System.out.print("the weight :"+sum);
    }


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                       /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
				/*A*/ {0, 12, INF, INF, INF, 16, 14},
				/*B*/ {12, 0, 10, INF, INF, 7, INF},
				/*C*/ {INF, 10, 0, 3, 5, 6, INF},
				/*D*/ {INF, INF, 3, 0, 4, INF, INF},
				/*E*/ {INF, INF, 5, 4, 0, 2, 8},
				/*F*/ {16, 7, 6, INF, 2, 0, 9},
				/*G*/ {14, INF, INF, INF, 8, 9, 0}
        };
        Kruskal kruskal=new Kruskal(vexs,matrix);
        kruskal.kruskal();
    }
}

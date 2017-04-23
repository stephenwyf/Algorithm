package Graph;

/**
 * Created by 65401 on 2017/3/10.
 * 无向图的邻接矩阵表示法
 */
public class AdjacencyMatrix {

    private char [] mVexs;//        顶点集合
    private int[][] mMatrix;//     邻接矩阵

    public AdjacencyMatrix(char [] mVexs,char [][] edges){
        this.mVexs=mVexs;
        //初始化邻接矩阵
        this.mMatrix=new int[edges.length][edges.length];
        for(int i=0;i<edges.length;i++){
            int p1=this.getPosition(edges[i][0]);
            int p2=this.getPosition(edges[i][1]);
            this.mMatrix[p1][p2]=1;
        }
    }

    //根据顶点得到在顶点集合的索引
    private int getPosition(char c){
        //遍历顶点集合
        for(int i=0;i<mVexs.length;i++){
            if(mVexs[i]==c){
                return i;
            }
        }
        return -1;//        没找到
    }

    //打印邻接数组
    private void print(){
        for(int i=0;i<this.mVexs.length;i++){
            for(int j=0;j<this.mVexs.length;j++){
                System.out.print(mMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char [] mVexs={'A', 'B', 'C', 'D'};//   输入的顶点集合
        char[][] edges = new char[][]{//    输入的无向图的边集合
                {'A', 'B'},{'B','A'},
                {'B', 'C'}, {'C', 'B'},
                {'C', 'D'},{'D', 'C'},
                {'A', 'D'},{'D','A'},
                {'A', 'C'},{'C', 'A'}
        };
        AdjacencyMatrix matrix=new AdjacencyMatrix(mVexs,edges);
        matrix.print();
    }
}

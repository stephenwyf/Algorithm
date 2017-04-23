package Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 65401 on 2017/3/11.
 * 带权值的有向图的邻接矩阵
 */
public class AdjacencyMatrixB {

    private String[] mVexs;//      顶点集合
    private String[][] mMatrix;//      邻接矩阵
    private static Map<String,String> map=new HashMap<>();//       权值

    public AdjacencyMatrixB(String[] mVexs,String[][] edges){
        this.mVexs=mVexs;
        //初始化邻接矩阵
        this.mMatrix=new String[edges.length][edges.length];
        for(int i=0;i<edges.length;i++){
            int p1=this.getPosition(edges[i][0]);
            int p2=this.getPosition(edges[i][1]);
            this.mMatrix[p1][p2]=insertValue(mVexs[p1]+mVexs[p2]);//        得到权值
        }
    }

    //根据顶点得到在顶点集合的索引
    private int getPosition(String c){
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
                if(i==j){//     顶点到自身权值为0
                    mMatrix[i][j]="0";
                }else if(mMatrix[i][j]==null){//       没有路径权值为无穷
                    mMatrix[i][j]="-";
                }
                System.out.print(mMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    //通过路径得到权值
    private String insertValue(String s){
        return map.get(s);
    }


    public static void main(String[] args) {
        String[] vexs={"V0", "V1","V2","V3"};//     顶点集合
        String[][]  edges=new String[][]{
                {"V0","V3"},{"V1","V0"},
                {"V2","V0"},{"V1","V2"},
                {"V2","V1"}
        };
        //加入权值
        map.put(null,"0");
        map.put("V0V3","9");
        map.put("V1V0","2");
        map.put("V2V0","7");
        map.put("V1V2","1");
        map.put("V2V1","1");
        AdjacencyMatrixB matrixB=new AdjacencyMatrixB(vexs,edges);
        matrixB.print();
    }
}

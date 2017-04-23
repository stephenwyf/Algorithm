package Graph;

/**
 * Created by 65401 on 2017/3/12.
 * 深度优先遍历(深度遍历邻接矩阵)(递归)
 * 广度优先遍历(深度遍历邻接矩阵)(queue)
 */
public class GraphSearch {

    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    public GraphSearch(char[] Vexs,char[][] edges){
        //邻接矩阵
        this.mVexs=new char[Vexs.length];
        for(int i=0;i<Vexs.length;i++){
            mVexs[i]=Vexs[i];
        }
        this.mMatrix=new int[Vexs.length][Vexs.length];
        for(int i=0;i<edges.length;i++){
            int p1=this.getPosition(edges[i][0]);
            int p2=this.getPosition(edges[i][1]);
            this.mMatrix[p1][p2]=1;
        }
    }
    //查找顶点数组中的索引
    private int getPosition(char s){
        for(int i=0;i<mVexs.length;i++){
            if(mVexs[i]==s){
                return i;
            }
        }
        return -1;
    }
    //邻接矩阵打印
    private void print(){
        System.out.println("邻接矩阵图的遍历:");
        for(int i=0;i<mMatrix.length;i++){
            for(int j=0;j<mMatrix.length;j++){
                System.out.print(" "+mMatrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     *DepthFirstSearch
     *DepthFirstSearch()
     *firstVertex
     *nextVertex
     */
    //深度优先遍历
    private void DepthFirstSearch(){
        //被访问过的标记集合 true：已经 false：没有被访问
        boolean[] visited=new boolean[mVexs.length];
        //初始将所有顶点定义为false
        for(int i=0;i<visited.length;i++){
            visited[i]=false;
        }
        //当顶点没访问过   开始遍历矩阵
        for(int i=0;i<this.mVexs.length;i++){//     从第一行开始往下遍历
            if(!visited[i]){
                //跳到递归函数
                DepthFirstSearch(i,visited);
            }
        }
        System.out.println();
    }
    //遍历矩阵
    private void DepthFirstSearch(int index,boolean[] visited){
        //将此顶点定义为已经访问过并打印
        visited[index]=true;
        System.out.print(this.mVexs[index]);
        //找到处在同一行的第一个邻接点
        int i=this.firstVertex(index);
        //找到邻接点
        while(i>=0){
            //没有访问过
            if(!visited[index]){
                //递归
                DepthFirstSearch(index,visited);
            }
            //访问下一个邻接点
            i=this.nextVertex(index,i);
        }
    }
    //第一个邻接点
    private int firstVertex(int index){
        for(int i=0;i<this.mVexs.length;i++){
            //从同一行开始往下看
            if(this.mMatrix[index][i]==1){//       存在邻接点
                return i;
            }
        }
        return -1;//    没找到
    }
    //找到下一行的下一个邻接点
    private int nextVertex(int index,int i){
        for(int j=i+1;j<mVexs.length;j++){
            if(this.mMatrix[index][j]==1){
                return j;
            }
        }
        return -1;
    }

    /**
     * BreadFirstSearch
     * firstVertex()
     * nextVertex()
     * 用queue写广度遍历
     */
    //广度优先遍历
    private void BreadFirstSearch(){
        int head=0;
        int rear=0;
        boolean [] visited=new boolean[this.mVexs.length];
        //存储广度遍历顶点的index
        int [] queue=new int[this.mVexs.length];
        for(int i=0;i<visited.length;i++){//由于一开始并没有访问，所以都设为假
            visited[i]=false;
        }
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){//代表没被访问
                visited[i]=true;
                System.out.print(this.mVexs[i]);
                queue[rear++]=i;//进入的是已经访问过后的索引号
            }
            while(head!=rear){
                int j=queue[head++];
                int k=this.firstVertex(j);//        找出这一行的第一个邻接点
                //找到邻接点
                while(k>=0){
                    if(!visited[k]){//      没有被访问过
                        visited[k]=true;
                        System.out.print(this.mVexs[k]);
                        queue[rear++]=k;
                    }
                    //找到这一行的其他邻接点
                    k=this.nextVertex(j,k);
                }
            }
        }
        System.out.println();
    }




    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D','E','F','G','H','I'};
        char[][] edges = new char[][]{//边是个二维数组
                {'A', 'B'}, {'A', 'F'},

                {'B', 'G'}, {'B', 'C'}, {'B', 'I'}, {'B', 'A'},

                {'C', 'B'}, {'C', 'I'}, {'C', 'D'},

                {'D', 'C'}, {'D', 'I'}, {'D', 'G'}, {'D', 'H'}, {'D', 'E'},

                {'E', 'H'}, {'E', 'F'}, {'E', 'D'},

                {'F', 'G'}, {'F', 'A'}, {'F', 'E'},

                {'G', 'H'}, {'G', 'D'}, {'G', 'B'}, {'G', 'F'},

                {'H', 'G'}, {'H', 'D'}, {'H', 'E'},

                {'I', 'B'}, {'I', 'C'}, {'I', 'D'}

        };
        GraphSearch search=new GraphSearch(vexs,edges);
        search.print();
        System.out.println("DepthFirstSearch");
        search.DepthFirstSearch();
        System.out.println("BreadFirstSearch");
        search.BreadFirstSearch();
    }
}

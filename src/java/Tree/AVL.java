package Tree;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/22.
 * AVL tree(平衡二叉排序树)
 */
public class AVL {

    public class AVLTreeNode{
        int key;//      键值
        int height;//       当前节点中子节点的最大高度
        AVLTreeNode left;
        AVLTreeNode right;
        //初始化
        public AVLTreeNode(int key,AVLTreeNode left,AVLTreeNode right){
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    private  int arr[]= {3,2,1,4,5,6,7,10,9,8};
    private AVLTreeNode mRoot;//        根节点

    @Test
    public void test(){
        for(int i=0;i<this.arr.length;i++){
            mRoot=this.insert(mRoot,arr[i]);
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        this.midOrder(mRoot);
        System.out.println();
        this.preOrder(mRoot);
    }


    private AVLTreeNode insert(AVLTreeNode node,int key){
        //如果没有节点，则创建个节点
        if(node==null){
            node=new AVLTreeNode(key,null,null);

        }else{
            //在左子树上
            if(key<node.key){
                node.left=this.insert(node.left,key);

                //计算是否打破平衡
                if(this.height(node.left)-this.height(node.right)==2){//    打破平衡
                    //LL
                    if(key<node.left.key){
                        node=this.LLrotation(node);
                    }else{//    LR
                        node=this.LRrotation(node);
                    }
                }

            }else if(key>node.key){//       在右子树上
                node.right=this.insert(node.right,key);

                //计算是否打破平衡
                if(this.height(node.right)-this.height(node.left)==2){//        打破平衡
                    //RR
                    if(key>node.right.key){
                        node=this.RRrotation(node);
                    }else{//        RL
                        node=this.RLrotation(node);
                    }
                }

            }else{//        键值已存在
                System.out.println("the key is exist");
                return null;
            }
        }
        //改变深度
        node.height=this.max(height(node.left),height(node.right))+1;
        return node;
    }


    //获取节点的高度
    private int height(AVLTreeNode node){
        if(node!=null){
            return node.height;
        }
        return 0;
    }

    //比较左右子树的高度
    private int max(int a,int b ){
        return a>b ? a:b;
    }



     /*
	 * LL：左左对应的情况(左单旋转--右顺时针旋转)。
	 *         2
	 *     A                     B
	 *    /  1                 /   \
	 *   B   -->>LL--->>      X     A
	 *  /  \                       /
	 * X    C                     C
	 * 返回值：旋转后的根节点
	 * (插入的节点是左子树的左边节点)
	 */
    private AVLTreeNode LLrotation(AVLTreeNode k2){//       k2对应的是A
        AVLTreeNode k1=k2.left;//       k1对应的是B
        k2.left=k1.right;
        k1.right=k2;

        k2.height=this.max(height(k2.left),height(k2.right))+1;
        k1.height=this.max(height(k1.left),height(k1.right))+1;
        return k1;
    }


    /*
	 * RR：右右对应的情况(右单旋转)。
	 *
	 *      -2                              0
	 *     A                               B
	 *      \ -1                         /0  \0
	 *       B         -->>RR--->>      A     X
	 *     /  \ 0                        \
	 *    C    X                          C
	 *
	 *(插入节点是右子树的右边节点)
	 * 返回值：旋转后的根节点
	 */
    private AVLTreeNode RRrotation(AVLTreeNode k2){//      k2对应的A
        AVLTreeNode k1=k2.right;//      k1对应的是B
        k2.right=k1.left;
        k1.left=k2;

        k2.height=this.max(height(k2.left),height(k2.right))+1;
        k1.height=this.max(height(k1.left),height(k1.right))+1;
        return k1;
    }


     /*
	 * LR：左右对应的情况(左双旋转)。--对应先RR-再LL
	 *(插入节点是左子树的右边节点)
	 * 返回值：旋转后的根节点
	 *
	 *        A             A               X
	 *      /             /               /  \
	 *     B   =》RR     X  =》LL        B    A
	  *    \            /
	  *     X          B
	 */
    private AVLTreeNode LRrotation(AVLTreeNode k2){//       k2对应的是A
        k2.left=this.RRrotation(k2.left);
        return this.LLrotation(k2);
    }


    /*
	 * RL：右左对应的情况(右双旋转)。对应 LL-RR
	 *(插入节点是右子树的左边节点)
	 * 返回值：旋转后的根节点
	 *               A           A                    X
	 *               \            \                  /  \
	 *               B   => LL     X   =>    RR     A    B
	 *              /               \
	 *             X                 B
	 *
	 */
    private AVLTreeNode RLrotation(AVLTreeNode k2){//       k2对应的是A
        k2.right=this.LLrotation(k2.right);
        return this.RRrotation(k2);
    }


    //DLR(前序遍历)
    private void preOrder(AVLTreeNode node){
        if(node!=null){
            System.out.print(node.key+" ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //LDR(中序遍历)
    private void midOrder(AVLTreeNode node){
        if(node!=null){
            midOrder(node.left);
            System.out.print(node.key+" ");
            midOrder(node.right);
        }
    }
}

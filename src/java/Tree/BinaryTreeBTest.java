package Tree;

/**
 * Created by 65401 on 2017/3/7.
 * 二叉树链表存储
 */
public class BinaryTreeBTest {
    public static void main(String[] args) {
        BinaryTreeB treeB=new BinaryTreeB(0);
        BinaryTreeB.TreeNode t1=treeB.addNode(treeB.root,1,true);
        BinaryTreeB.TreeNode t2=treeB.addNode(treeB.root,2,false);
        BinaryTreeB.TreeNode t3=treeB.addNode(t1,3,true);
        BinaryTreeB.TreeNode t4=treeB.addNode(t2,4,false);
        System.out.println(treeB.getDeep(treeB.root));
    }

}

class BinaryTreeB{

    public class TreeNode{
        Object data;
        TreeNode left;//        该节点的左节点地址
        TreeNode right;//       该节点的右节点地址

        public TreeNode(Object data){
            this.data=data;
        }

        public TreeNode(Object data,TreeNode left,TreeNode right){
            this.data=data;
            this.left = left;
            this.right = right;
        }
    }


    //创建根节点
    public TreeNode root;
    public BinaryTreeB(Object data){
        root=new TreeNode(data);
    }
    //查询左节点
    public Object left(TreeNode parent){
        if(parent==null){
            System.out.println("the node is not exist");
            return null;
        }
        return parent.left==null?null:parent.left.data;
    }
    //查询右节点
    public Object right(TreeNode parent){
        if(parent==null){
            System.out.println("the node is not exist");
            return null;
        }
        return parent.right==null?null:parent.right.data;
    }

    /**
     * 增加节点
     * @param parent:父节点
     * @param data：数据
     * @param isLeft：是否为左节点
     * @return
     */
    public TreeNode addNode(TreeNode parent,Object data,boolean isLeft){
        if(parent==null){
            System.out.println("the node is not exist");
            return null;
        }
        //左节点已经存在
        if(isLeft &&parent.left!=null){
            System.out.println("the left node is exist");
            return null;
        }
        //右节点已经存在
        if(!isLeft &&parent.right!=null){
            System.out.println("the right node is exist");
            return null;
        }
        TreeNode newNode=new TreeNode(data);
        if(isLeft){
            parent.left=newNode;
        }else{
            parent.right=newNode;
        }
        return newNode;
    }

    /**
     * 获取节点的深度
     * @param node：父节点
     * @return
     */
    public int getDeep(TreeNode node){
        if(node==null){
            return 0;
        }
        //节点下没有子树
        if(node.left==null&&node.right==null){
            return 1;
        }else{
            //记录其所有左、右子树中较大的深度
            int leftNode=getDeep(node.left);
            int rightNode=getDeep(node.right);
            int deep=leftNode>rightNode?leftNode:rightNode;
            return deep+1;
        }
    }

}

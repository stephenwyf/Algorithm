package Tree;

import org.junit.Test;

/**
 * Created by 65401 on 2017/3/8.
 * 二叉查找树
 */
public class BinarySearchTree {


    private int array[]={5,2,1,3,4,6};

    @Test
    public void test(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
            Node node=new Node(array[i],null,null,null);
            insert(node);
        }
        System.out.println();
        midOrder(root);
        System.out.println(root.data);
        System.out.println();
        Node node=search(root,2);

        delete1(node);
        midOrder(root);


    }

    private Node root;//    根节点
    public class Node{
        int data;
        Node left;
        Node right;
        Node parent;
        public Node(int data,Node parent,Node left,Node right){
            this.data=data;
            this.parent=parent;
            this.left=left;
            this.right=right;
        }
    }

    //前序DLR
    private void perOrder(Node node){
        if(node!=null){
            System.out.print(node.data+" ");
            perOrder(node.left);
            perOrder(node.right);
        }
    }

    //中序LDR
    private void midOrder(Node node){
        if(node!=null){
            midOrder(node.left);
            System.out.print(node.data+" ");
            midOrder(node.right);
        }
    }

    //后序LRD
    private void postOrder(Node node){
        if(node!=null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data+" ");
        }
    }

    //找到node下子树的最大节点
    private Node maximum(Node node){
        if(node!=null){

            while(node.right!=null){
                node=node.right;
            }
        }
        return node;
    }

    //找到node下子树的最小节点
    private Node minimum(Node node){
        if(node!=null){

            while (node.left!=null){
                node=node.left;
            }
        }
        return node;
    }

    //前驱节点
    private Node predecessor(Node node){
        if(node==null){
            System.out.println("the node is not exist");
            return null;
        }
        //存在左孩子
        if(node.left!=null){
            return maximum(node.left);
        }
        //没有左孩子
        if(node.parent==null){
            System.out.println("the node's parent is not exist");
            return null;
        }
        //找到其父节点
        Node nodeParent=node.parent;
        //当它父节点不为空且是左节点时
        while(nodeParent!=null&&node==nodeParent.left){
            node=nodeParent;
            nodeParent=nodeParent.parent;
        }
        //为右节点时直接返回父节点
        return nodeParent;
    }

    //后继节点
    private Node successor(Node node){
        if(node==null){
            System.out.println("the node is not exist");
            return null;
        }
        //存在右节点
        if(node.right!=null){
            return minimum(node.right);
        }
        //没有右孩子
        if(node.parent==null){
            System.out.println("the node's parent is not exist");
            return null;
        }
        //找到其父节点
        Node nodeParent=node.parent;
        //当它父节点不为空且是右节点时
        while(nodeParent!=null&&node==nodeParent.right){
            node=nodeParent;
            nodeParent=nodeParent.parent;
        }
        //为左节点时直接返回父节点
        return nodeParent;
    }

    //插入节点
    private void insert(Node newNode){
        Node temp=null;//       找到的插入节点的父节点
        Node node=root;
        int com;
        //找到插入的新节点的父节点所在的位置
        while(node!=null){
            temp=node;
            com=node.data-newNode.data;

            if(com>0){
                node=node.left;
            }else if(com<0){
                node=node.right;
            }else{
                return;//       二叉查找树不允许节点相同
            }
        }
        //temp节点为新节点的父节点
        newNode.parent=temp;
        //没有根节点
        if(temp==null){
            root=newNode;
        }else{
            com=newNode.data-temp.data;
            if(com>0){
                temp.right=newNode;
            }else if(com<0){
                temp.left=newNode;
            }else{
                return;
            }
        }
    }

    //递归实现  查找二叉树中的键值data的节点
    private Node search(Node node,int data){
        if(node==null){
            return node;
        }
        int com=data-node.data;
        if(com<0){
            return search(node.left,data);
        }else if(com>0){
            return search(node.right,data);
        }else {
            return node;
        }
    }

    //删除节点
    private Node delete(Node node){

        Node deleteNode=null;
        //叶子节点
        if(node.left==null&&node.right==null){
            deleteNode=node;
            if(deleteNode.parent==null){//      为根节点
                root=null;
            }else if(deleteNode==deleteNode.parent.left){
                deleteNode.parent.left=null;
            }else if(deleteNode==deleteNode.parent.right){
                deleteNode.parent.right=null;
            }
            return deleteNode;
        }
        //只有一个子节点
        if(node.left==null){//      只有右节点
            deleteNode=node;
            Node temp=deleteNode.right;
            if(deleteNode.parent==null){
                root=temp;
                temp.parent=null;
            }else{
                deleteNode.parent.right=temp;
                temp.parent=deleteNode.parent;
            }
        }else if(node.right==null){//     只有左节点
            deleteNode=node;
            Node temp=deleteNode.left;
            if(deleteNode.parent==null){
                root=temp;
                temp.parent=null;
            }else{
                deleteNode.parent.left=temp;
                temp.parent=deleteNode.parent;
            }
        } else{//       有两个子节点

            deleteNode=predecessor(node);//     找到前驱节点
            Node temp=null;
            if(deleteNode.left!=null){
                temp=deleteNode.left;
            }else{
                temp=deleteNode.right;
            }
            if(deleteNode==deleteNode.parent.left){
                deleteNode.parent.left=temp;
            }else{
                deleteNode.parent.right=temp;
            }
            node.data=deleteNode.data;
        }

        return deleteNode;
    }

    //删除节点 (简化版)
    private Node delete1(Node node){
        Node deleteNode=null;
        Node temp=null;

        if(node.left==null || node.right==null){
            deleteNode=node;
        }else{//        有两个节点
            deleteNode=successor(node);//       找到前驱节点
        }
        //      temp作为删除节点的后面的子节点
        if(deleteNode.left!=null){
            temp=deleteNode.left;
        }else{
            temp=deleteNode.right;
        }
        //      要删除的节点有子节点
        if(temp!=null){
            temp.parent=deleteNode.parent;
        }
        //删除节点是根节点
        if(deleteNode.parent==null){
            root=deleteNode;
        }else if(deleteNode==deleteNode.parent.left){//     要删除节点是左节点
            deleteNode.parent.left=temp;
        }else{//            要删除节点是右节点
            deleteNode.parent.right=temp;
        }
        //      当删除节点有两个子结点时，把前驱节点的值赋给要删除节点
        if(deleteNode!=node){
            node.data=deleteNode.data;//        此时deletNode是要删除节点的前驱节点
        }

        return deleteNode;
    }
}

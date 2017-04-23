package Tree;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * Created by 65401 on 2017/3/8.
 * 根据BinaryTreeB的链式存储作查询
 */
public class BinaryTreeTestC {

    public static void main(String[] args) {
        BinaryTreeB treeB=new BinaryTreeB(0);
        BinaryTreeC treeC=new BinaryTreeC();
        BinaryTreeB.TreeNode t1=treeB.addNode(treeB.root,1,true);
        BinaryTreeB.TreeNode t2=treeB.addNode(treeB.root,2,false);
        BinaryTreeB.TreeNode t3=treeB.addNode(t1,3,true);
        BinaryTreeB.TreeNode t4=treeB.addNode(t2,4,false);
        BinaryTreeB.TreeNode t5=treeB.addNode(t2,5,true);

        treeC.DLR(treeB.root);
        System.out.println("=======DLR");
        treeC.LDR(treeB.root);
        System.out.println("=======LDR");
        treeC.LRD(treeB.root);
        System.out.println("=======LRD");
        treeC.depthOrderTraversal(treeB.root);
        System.out.println("=======depthOrder");
        treeC.levelOrderTraversal(treeB.root);
        System.out.println("=======levelOrder");

    }
}

class BinaryTreeC{

    //先序遍历DLR
    public void DLR(BinaryTreeB.TreeNode node){

        System.out.print(node.data+" ");
        if(node.left!=null){
            DLR(node.left);
        }
        if(node.right!=null){
            DLR(node.right);
        }
    }

    //中序遍历LDR
    public void LDR(BinaryTreeB.TreeNode node){

        if(node.left!=null){
            LDR(node.left);
        }
        System.out.print(node.data+" ");
        if(node.right!=null){
            LDR(node.right);
        }
    }

    //后序遍历LRD
    public void LRD(BinaryTreeB.TreeNode node){

        if(node.left!=null){
            LRD(node.left);
        }
        if(node.right!=null){
            LRD(node.right);
        }
        System.out.print(node.data+" ");
    }

    //用Stack进行深度遍历
    public void depthOrderTraversal(BinaryTreeB.TreeNode node){
        Stack<BinaryTreeB.TreeNode> stack=new Stack<>();
        stack.push(node);//     先压根节点

        while(!stack.isEmpty()){

            BinaryTreeB.TreeNode newNode=stack.pop();
            System.out.print(newNode.data+" ");
            //stack先进后出
            if(newNode.right!=null){//  先根 再左 再右 DLR，如果颠倒此句，就是DRL
                stack.push(newNode.right);
            }
            if(newNode.left!=null){
               stack.push(newNode.left);
            }
        }
    }

    //广度遍历  采用队列
    public void levelOrderTraversal(BinaryTreeB.TreeNode node){
        //数组双向队列
        ArrayDeque<BinaryTreeB.TreeNode> array=new ArrayDeque<>();
        array.add(node);

        while(!array.isEmpty()){
            BinaryTreeB.TreeNode newNode=array.remove();
            System.out.print(newNode.data+" ");
            //队列先进先出
            if(newNode.left!=null){//  先根 再左 再右 DLR，如果颠倒此句，就是DRL
                array.add(newNode.left);
            }
            if(newNode.right!=null){
                array.add(newNode.right);
            }
        }
    }
}

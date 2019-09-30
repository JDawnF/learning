package datastructure;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 通过递归实现二叉树的遍历
 */
public class BinaryTreeNodeByRecusion {
    private static class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> inputList) {
        TreeNode node = null;
        if (inputList == null || inputList.isEmpty()) {
            return null;
        }
        Integer data = inputList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }

    // 前序遍历：根节点 -> 左节点 ->右节点
    public static void preOrderTraveral(TreeNode node){
        if (node==null)
            return;
        System.out.println(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    public static void inOrderTraveral(TreeNode node){
        if (node==null)
            return;
        preOrderTraveral(node.leftChild);
        System.out.println(node.data);
        preOrderTraveral(node.rightChild);
    }

    public static void postOrderTraveral(TreeNode node){
        if (node==null)
            return;
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
        System.out.println(node.data);
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList=new LinkedList<Integer>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode node = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTraveral(node);
        System.out.println("中序遍历");
        inOrderTraveral(node);
        System.out.println("后序遍历");
        postOrderTraveral(node);
    }
}

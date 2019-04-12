package com.offer;

import java.util.Arrays;

/**
 * 重建二叉树
 * <p>
 * <p>
 * 题目：
 * 输入某某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果都不含重复的数字。
 * 例如，输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建出二叉树并输出它的头结点。
 */
public class Test6 {

    public BinaryTreeNode constructBinTree2(int[] preorder, int[] inorder) {
        // 判断输入数据
        if (preorder == null || preorder.length == 0 ||
                inorder == null || inorder.length == 0)
            return null;
        return constructBinTree(preorder, 0, preorder.length, inorder, 0, preorder.length);
    }

    // todo, 确定边界是关键点
    private BinaryTreeNode constructBinTree(int[] preorder, int ps, int pe,
                                            int[] inorder, int is, int ie) {
        // 递归终止条件，当开始位置大于结束位置时，则没有要处理的数据
        if (ps >= pe || is >= ie) return null;

        // 从前序遍历序列中取出根结点
        int rootId = preorder[ps];
        BinaryTreeNode root = new BinaryTreeNode(rootId);

        // 根结点在中序遍历序列中的位置
        int idxOfRoot = -1;
        for (int i = is; i < ie; i++) {
            if (inorder[i] == rootId) {
                idxOfRoot = i;
                break;
            }
        }


        if (idxOfRoot == -1) {
            System.out.println(String.format("ps, pe = %d, %d, preorder = %s", ps, pe, Arrays.toString(preorder)));
            System.out.println(String.format("is, ie = %d, %d, inorder = %s", is, ie, Arrays.toString(inorder)));
            throw new RuntimeException("先序遍历序列和中序遍历序列不匹配.");
        }

        // 递归构建当前根结点的左子树，左子树的结点个数是 idxOfRoot-is
        // 先序遍历序列中，当前根结点的左子树的范围是 [ps+1, ps+(idxOfRoot-is)+1)，根据起始位置和偏移量计算范围
        // 中序遍历序列中，当前根结点的左子树的范围是 [is, idxOfRoot)
        BinaryTreeNode leftChildNode = constructBinTree(preorder, ps + 1, ps + idxOfRoot - is + 1, inorder, is, idxOfRoot);
        root.setLeftChildNode(leftChildNode);

        // 递归构建当前根结点的右子树，右子树的结点个数是 ie-idxOfRoot-1
        // 先序遍历序列中，当前结点的右子树的范围是 [pe-(ie-idxOfRoot-1), pe)，根据终止位置和偏移量计算范围
        // 中序遍历序列中，当前结点的右子树的范围是 [idxOfRoot+1, ie)
        BinaryTreeNode rightChildNode = constructBinTree(preorder, pe - ie + idxOfRoot + 1, pe, inorder, idxOfRoot + 1, ie);
        root.setRightChildNode(rightChildNode);

        return root;
    }

    public void previsit(BinaryTreeNode root) {
        if (root == null) return;
        System.out.print(String.format("%s \t", root.getValue()));
        previsit(root.getLeftChildNode());
        previsit(root.getRightChildNode());
    }

    public void invisit(BinaryTreeNode root) {
        if (root == null) return;
        invisit(root.getLeftChildNode());
        System.out.print(String.format("%s \t", root.getValue()));
        invisit(root.getRightChildNode());
    }

    public void postvisit(BinaryTreeNode root) {
        if (root == null) return;
        postvisit(root.getLeftChildNode());
        postvisit(root.getRightChildNode());
        System.out.print(String.format("%s \t", root.getValue()));
    }

    public void visit(BinaryTreeNode root) {
        System.out.print("先序遍历结果：");
        previsit(root);
        System.out.println();
        System.out.print("中序遍历结果：");
        invisit(root);
        System.out.println();
        System.out.print("后序遍历结果：");
        postvisit(root);
        System.out.println("\n---------------------");
    }

    /**
     * 普通二叉树
     */
    public void test1() {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = constructBinTree2(preorder, inorder);
        visit(root);
    }

    /**
     * 完全二叉树
     */
    public void test5() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        BinaryTreeNode root = constructBinTree2(preorder, inorder);
        visit(root);
    }

    /**
     * 所有结点都没有右孩子
     */
    public void test2() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {5, 4, 3, 2, 1};
        BinaryTreeNode root = constructBinTree2(preorder, inorder);
        visit(root);
    }

    /**
     * 所有结点都没有左孩子
     */
    public void test3() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5};
        BinaryTreeNode root = constructBinTree2(preorder, inorder);
        visit(root);
    }


    /**
     * 只有一个结点
     */
    public void test4() {
        int[] preorder = {1};
        int[] inorder = {1};
        BinaryTreeNode root = constructBinTree2(preorder, inorder);
        visit(root);
    }

    /**
     * 空指针
     */
    public void test6() {
        BinaryTreeNode root = constructBinTree2(null, null);
        visit(root);
    }

    /**
     * 前序遍历和中序遍历不匹配
     */
    public void test7() {
        int[] preorder = {1, 3, 2, 4, 7, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = constructBinTree2(preorder, inorder);
        visit(root);
    }


    public static void main(String[] args) {
        Test6 test = new Test6();
        test.test1();
        test.test2();
        test.test3();
        test.test4();
        test.test5();
        test.test6();
        test.test7();
    }


}


class BinaryTreeNode {
    private int value;  // id of BinaryTreeNode, unique
    private BinaryTreeNode leftChildNode;
    private BinaryTreeNode rightChildNode;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode(int value, BinaryTreeNode leftChildNode, BinaryTreeNode rightChildNode) {
        this.value = value;
        this.leftChildNode = leftChildNode;
        this.rightChildNode = rightChildNode;
    }

    public int getValue() {
        return this.value;
    }

    public BinaryTreeNode getLeftChildNode() {
        return this.leftChildNode;
    }

    public BinaryTreeNode getRightChildNode() {
        return this.rightChildNode;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeftChildNode(BinaryTreeNode leftChildNode) {
        this.leftChildNode = leftChildNode;
    }

    public void setRightChildNode(BinaryTreeNode rightChildNode) {
        this.rightChildNode = rightChildNode;
    }
}
package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.treeAndGraph.prep.TreeNode;

public class FirstCommonAncestor {
    public static void main(String[] args) {

        TreeNode<Integer> root = new TreeNode<>(20);
        root.setParent(null);
        root.setLeft(new TreeNode<>(10));
        root.getLeft().setParent(root);
        root.setRight(new TreeNode<>(30));
        root.getRight().setParent(root);
        root.getLeft().setLeft(new TreeNode<>(5));
        root.getLeft().getLeft().setParent(root.getLeft());
        root.getLeft().setRight(new TreeNode<>(15));
        root.getLeft().getRight().setParent(root.getLeft());
        root.getLeft().getRight().setRight(new TreeNode<>(17));
        root.getLeft().getRight().getRight().setParent(root.getLeft().getRight());
        root.getLeft().getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().getLeft().getLeft().setParent(root.getLeft().getLeft());
        root.getLeft().getLeft().setRight(new TreeNode<>(7));
        root.getLeft().getLeft().getRight().setParent(root.getLeft().getLeft());

        TreeNode<Integer> rootp = root.getLeft().getLeft().getLeft();
        TreeNode<Integer> rootq = root.getRight();
        TreeNode<Integer> result = commonAnc(root, rootq, rootp);
        if (result == null) {
            System.out.println("There is no common ancestor");
        } else {
            System.out.println("There is a common ancestor");
        }
    }

    /**
     * Solution 1
     * This is finding the common ancestor by comparing the height of each of the
     * nodes. When one node's height is deeper, then we try to close the height gap
     * and we start to travel upwards of each of the nodes until we find a common ancestor.
     * <p>
     * <p>
     * Time Complexity = O(h) where h is the height of the deeper treeNode
     * Space Complexity = O(h) because of the stack used
     **/
    static TreeNode<Integer> findCommonAncestor(TreeNode<Integer> q, TreeNode<Integer> p) {
        //base case
        if (q == null || p == null)
            return null;

        int qHeight = findHeight(q);
        int pHeight = findHeight(p);
        int deltaHeight = qHeight - pHeight;
        TreeNode<Integer> deeperNode = (deltaHeight > 0) ? q : p; //deeper node
        TreeNode<Integer> shallowerNode = (deltaHeight > 0) ? p : q; //shallower node
        deltaHeight = Math.abs(deltaHeight);
        while (deltaHeight != 0 && deeperNode != null) {
            deeperNode = deeperNode.getParent();
            deltaHeight--;
        }

        //the two nodes are at the same level
        while (deeperNode != shallowerNode && deeperNode != null && shallowerNode != null) {
            deeperNode = deeperNode.getParent();
            shallowerNode = shallowerNode.getParent();
        }
        return (shallowerNode == null || deeperNode == null) ? null : p;
    }

    //find the height of the node
    static int findHeight(TreeNode<Integer> node) {
        //base case
        if (node == null) {
            return -1;
        }

        int height = findHeight(node.getParent());
        return height + 1;
    }

    /**
     * Solution 2
     * This approach is quite the same as above, we do below
     * 1. First, check if the two nodes could be under the root
     * 2. Second, check if the first root covers the second root
     * 3. Third, check if the second root covers the first root
     * 4. Fourth, check the siblings of the first root if it covers the second root
     * <p>
     * We do this in that order
     * <p>
     * Time complexity = O(t) where t is size of the subtree
     * or O(n) where n is the size of nodes in the tree
     **/

    static TreeNode<Integer> findCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        //check if the root covers either of the two nodes
        if (!cover(root, p) || !cover(root, q)) {
            return null;
        } else if (cover(p, q)) {
            return p;
        } else if (cover(q, p)) {
            return q;
        }

        //check the siblings of the node p
        TreeNode<Integer> sibling = siblings(p);
        TreeNode<Integer> parent = p.getParent();
        while (!cover(sibling, q)) {
            sibling = siblings(parent);
            parent = parent.getParent();
        }

        return parent;
    }

    static boolean cover(TreeNode<Integer> root, TreeNode<Integer> other) {
        //base cases
        if (root == null) {
            return false;
        }

        if (root == other)
            return true;

        //check the left and right subtree to search for other node
        return cover(root.getLeft(), other) || cover(root.getRight(), other);
    }

    static TreeNode<Integer> siblings(TreeNode<Integer> root) {
        //base case
        if (root == null || root.getParent() == null) {
            return null;
        }

        TreeNode<Integer> parent = root.getParent();
        return parent.getLeft() == root ? parent.getRight() : parent.getLeft();
    }


    /**
     * Solution 3
     * We can also check this without reference to their parents
     * 1. check if the root covers the first and second nodes
     * 2. Check if they are on the same side of the root
     * if not return the common side
     * <p>
     * Time complexity = O(n)
     **/
    static TreeNode<Integer> commonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        //check if the root covers either of the two nodes
        if (!cover(root, p) || !cover(root, q)) {
            return null;
        }

        return commonAncestorUtil(root, p, q);
    }

    static TreeNode<Integer> commonAncestorUtil(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        //base case
        if (root == null || root == p || root == q) {
            return root;
        }

        //check if p and q is on the right side
        boolean isPOnRightSide = cover(root.getRight(), p);
        boolean isQOnRightSide = cover(root.getRight(), q);

        //check if they are on the different sides
        if (isPOnRightSide != isQOnRightSide) {
            return root;
        }

        TreeNode<Integer> childSide = isPOnRightSide ? root.getRight() : root.getLeft();
        //check the immediate ancestor
        return commonAncestor(childSide, p, q);
    }

    /**
     * Solution 4
     * We optimize the solution 3 a bit. Instead of searching for p and q separately, we
     * can do this in one call.
     * If it it only p existing in the subtree, we return p,
     * If it is only q existing, we return q
     * else we return root.
     **/

    static TreeNode<Integer> commonAnc(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        Result result = isCommonAnc(root, p, q);
        if (result.isAncestorRoot)
            return result.root;

        return null;
    }

    static Result isCommonAnc(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        //base cases
        if (root == null)
            return new Result(null, false);

        if (root == p && root == q) {
            return new Result(root, true);
        }

        Result resultP = isCommonAnc(root.getLeft(), p, q);
        if (resultP.isAncestorRoot) //common root found
            return resultP;
        Result resultQ = isCommonAnc(root.getRight(), p, q);
        if (resultQ.isAncestorRoot) //common root found
            return resultQ;

        if (resultP.root != null && resultQ.root != null) {
            return new Result(root, true);
        }else if (root == p || root == q){
            //we have found one of them
            boolean isAncestor = resultP.root != null || resultQ.root != null;
            return new Result(root,isAncestor);
        }else{
            return new Result(resultP.root != null? resultP.root : resultQ.root,false);
        }

    }


    static class Result {
        TreeNode<Integer> root;
        boolean isAncestorRoot;

        public Result(TreeNode<Integer> root, boolean isAncestorRoot) {
            this.root = root;
            this.isAncestorRoot = isAncestorRoot;
        }
    }

}

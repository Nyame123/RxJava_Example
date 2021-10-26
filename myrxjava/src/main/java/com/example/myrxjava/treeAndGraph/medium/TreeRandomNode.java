package com.example.myrxjava.treeAndGraph.medium;

import java.util.Random;

/**
 * This class returns a random node from a tree.
 * Operations like insertion and find are also performed
 **/
public class TreeRandomNode {

    private static TreeNodeWithSize<Integer> root;

    public static void main(String[] args) {
        //root = new TreeNodeWithSize<>(5);
        /**
         *             5
         *         /      \
         *        4        9
         *       / \      / \
         *      3       6   10
         *     /
         *    2
         * */

        insertItem(5);
        insertItem(4);
        insertItem(9);
        insertItem(3);
        insertItem(2);
        insertItem(6);
        insertItem(10);
        deleteItem(5);

        TreeNodeWithSize<Integer> treeNodeWithSize = getRandomNode();


        System.out.println(treeNodeWithSize == null ? 0 : treeNodeWithSize.data);
    }


    //get the Random Node in the tree
    static TreeNodeWithSize<Integer> getRandomNode() {
        //base case
        if (root == null)
            return null;

        //generate the random index between 0 and root size
        Random random = new Random();
        int index = random.nextInt(root.size);

        //grab the node that match the index
        return root.getIthNode(index);

    }

    //insert item in the tree
    static void insertItem(Integer d) {
        if (root == null) {
            root = new TreeNodeWithSize<>(d);
        } else {
            root.insertItem(d);
        }
    }

    //delete item
    static boolean deleteItem(Integer d) {
        if (root == null) {
            return false;
        } else {
            if (root.data.equals(d)) {
                return deleteRoot();
            } else {
                return root.isDeleted(d, root);
            }
        }
    }

    //Time complexity = O(log(n))
    private static boolean deleteRoot() {
        TreeNodeWithSize<Integer> oldRoot = root;
        if (root.getLeft() != null && root.getRight() != null) {
            TreeNodeWithSize<Integer> rootRight = root.getRight();
            root = root.getLeft();
            root.insertItem(rootRight);
        } else if (root.getLeft() != null) {
            root = root.getLeft();
        } else {
            root = root.getRight();
        }
        oldRoot = null;

        return true;
    }


}

class TreeNodeWithSize<T extends Comparable<T>> {
    //size of the node
    int size = 0;
    T data;
    TreeNodeWithSize<T> left;
    TreeNodeWithSize<T> right;

    TreeNodeWithSize(T data) {
        this.data = data;
        size = 1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //find the data
    public TreeNodeWithSize<T> findNode(T d) {
        //base case
        if (d.equals(data)) {
            return this;
        } else if (d.compareTo(data) < 0) {
            return getLeft() == null ? null : getLeft().findNode(d);
        } else {
            return getRight() == null ? null : getRight().findNode(d);
        }
    }

    //delete data at index;
    public boolean isDeleted(T d, TreeNodeWithSize<T> parent) {
        //base case
        if (d.compareTo(data) < 0) {
            if (getLeft() == null) { //item to be deleted not found
                return false;
            }
            boolean result = getLeft().isDeleted(d, this);
            if (result) {
                setSize(size - 1);
            }
            return result;
        } else if (d.compareTo(data) > 0) {
            if (getRight() == null) { //item to be deleted not found
                return false;
            }
            boolean result = getRight().isDeleted(d, this);
            if (result) {
                setSize(size - 1);
            }
            return result;
        } else {
            if (parent.getRight().data.compareTo(d) == 0) {
                //check if the node to be deleted is all set
                TreeNodeWithSize<T> rightNodeToDelete = parent.getRight();
                if (rightNodeToDelete.getRight() != null && rightNodeToDelete.getLeft() != null) {
                    parent.setRight(rightNodeToDelete.getRight());
                    parent.getRight().setLeft(rightNodeToDelete.getLeft());
                    //increment the size of the right of the parent since it is bearing another child
                    parent.getRight().setSize(parent.getRight().size + 1);
                } else if (rightNodeToDelete.getRight() != null) {
                    parent.setRight(rightNodeToDelete.getRight());

                } else if (rightNodeToDelete.getLeft() != null) {
                    parent.setRight(rightNodeToDelete.getLeft());
                } else {
                    parent.setRight(null);
                }

            } else {
                //check if the node to be deleted is all set
                TreeNodeWithSize<T> leftNodeToDelete = parent.getLeft();
                if (leftNodeToDelete.getRight() != null && leftNodeToDelete.getLeft() != null) {
                    parent.setLeft(leftNodeToDelete.getLeft());
                    parent.getLeft().setRight(leftNodeToDelete.getRight());
                    //increment the size of the left of the parent since it is bearing another child
                    parent.getLeft().setSize(parent.getLeft().size + 1);
                } else if (leftNodeToDelete.getRight() != null) {
                    parent.setLeft(leftNodeToDelete.getRight());

                } else if (leftNodeToDelete.getLeft() != null) {
                    parent.setLeft(leftNodeToDelete.getLeft());
                } else {
                    parent.setLeft(null);
                }
            }


            return true;
        }
    }

    //insert data at index;
    public void insertItem(T d) {
        //base case
        if (d.compareTo(data) <= 0) {
            if (getLeft() == null) {
                setLeft(new TreeNodeWithSize<>(d));
            } else {
                getLeft().insertItem(d);
            }
        } else {
            if (getRight() == null) {
                setRight(new TreeNodeWithSize<>(d));
            } else {
                getRight().insertItem(d);
            }
        }

        //increment the size count;
        size++;
    }

    //insert node at index;
    public boolean insertItem(TreeNodeWithSize<T> node) {
        //base case
        if (node.getData().compareTo(data) <= 0) {
            if (getLeft() == null) {
                setLeft(node);
                size += node.size;
                return true;
            } else {
               boolean result = getLeft().insertItem(node);
               if (result)
                   size += node.size;
               return result;
            }
        } else {
            if (getRight() == null) {
                setRight(node);
                //increment the size count;
                size += node.size;
                return true;
            } else {
               boolean result = getRight().insertItem(node);
                if (result)
                    size += node.size;

                return result;
            }
        }




    }

    //get data at index
    //Time complexity = O(D) where D = depth of the tree
    //or O(Log(n)) on balanced trees
    //space complexity = O(D)
    public TreeNodeWithSize<T> getIthNode(int index) {
        //check the left size
        int leftSize = getLeft() == null ? 0 : getLeft().size;
        if (index < leftSize) { //consider elements on the left side
            return getLeft().getIthNode(index);
        } else if (index == leftSize) {
            return this;
        } else {
            return getRight().getIthNode(index - (leftSize + 1));
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNodeWithSize<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNodeWithSize<T> left) {
        this.left = left;
    }

    public TreeNodeWithSize<T> getRight() {
        return right;
    }

    public void setRight(TreeNodeWithSize<T> right) {
        this.right = right;
    }
}

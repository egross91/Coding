/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    // AC
    boolean checkBST(Node root) {
        if (root == null) { return false; }
        
        List<Integer> inOrderTraversal = new ArrayList<Integer>();
        
        getInOrderTraversal(root, inOrderTraversal);
        
        // Observation is that an in-order traversal of the Binary Search Tree will have all elements sorted.
        return (doCheckBST(inOrderTraversal));
        
        
        // boolean hasNoDupes = doCheckBSTDupes(root, new HashSet<Integer>());
        // return (hasNoDupes && doCheckBST(inOrderTraversal));
        
        /*
        boolean leftTreeValid  = doCheckBST(root.left, root.data, true);
        boolean rightTreeValid = doCheckBST(root.right, root.data, false);
                
        return (hasNoDupes && leftTreeValid && rightTreeValid);
        */
    }

    boolean doCheckBSTDupes(Node current, Set<Integer> seen) {
        if (current == null) { return true; }
        if (!seen.add(current.data)) { return false; }
        
        return (doCheckBSTDupes(current.left, seen) && doCheckBSTDupes(current.right, seen));
    }

    void getInOrderTraversal(Node current, List<Integer> nodeValues) {
        if (current.left != null) {
            getInOrderTraversal(current.left, nodeValues);
        }
        
        nodeValues.add(current.data);
        
        if (current.right != null) {
            getInOrderTraversal(current.right, nodeValues);
        }
    }

    boolean doCheckBST(List<Integer> inOrderNodeData) {
        int previous = Integer.MIN_VALUE;
        
        for (int i = 0; i < inOrderNodeData.size(); ++i) {
            int currentData = inOrderNodeData.get(i);
            
            if (previous >= currentData) {
                return false;
            }
            
            previous = currentData;
        }
        
        return true;
    }

    // Doesn't pass Test 13. Didn't bother to look.
    /*boolean doCheckBST(Node current, int rootValue, boolean inLeftSubtree) {
        if (current == null) { return true; }
        
        boolean leftLessThan     = true;
        boolean rightGreaterThan = true;
        boolean isValid          = true;
        
        if (current.left != null) {
            int leftData = current.left.data;
            
            isValid      = (isValid && 
                            doCheckBST(current.left, rootValue, inLeftSubtree) &&
                            doCheckBST(current.left, leftData, true));
            leftLessThan = (leftData < current.data);
            
            if (inLeftSubtree) {
                leftLessThan = (leftLessThan && (leftData < rootValue));
            }
            else {
                leftLessThan = (leftLessThan && (leftData > rootValue));
            }
        }
        if (current.right != null) {
            int rightData = current.right.data;
            
            isValid          = (isValid && 
                                doCheckBST(current.right, rootValue, inLeftSubtree) &&
                                doCheckBST(current.right, rightData, false));
            rightGreaterThan = (rightData > current.data);

            if (inLeftSubtree) {
                rightGreaterThan = (rightGreaterThan && (rightData < rootValue));
            }
            else {
                rightGreaterThan = (rightGreaterThan && (rightData > rootValue));
            }
        }
                
        return (isValid && leftLessThan && rightGreaterThan);
    }*/

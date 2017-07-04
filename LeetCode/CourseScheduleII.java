// AC
public class Solution {
    private int[] depsArray;
    private int depsPtr;
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.depsArray = null; this.depsArray = new int[numCourses];
        this.depsPtr   = 0;
        
        List<List<Integer>> dependencies = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) { dependencies.add(new ArrayList<Integer>()); }
        
        for (int i = 0; i < prerequisites.length; ++i) {
            int course = prerequisites[i][0];
            int dep    = prerequisites[i][1];
            
            dependencies.get(course).add(dep);
        }
        
        boolean[] visited      = new boolean[numCourses];
        Set<Integer> satisfied = new HashSet<Integer>();
        
        for (int i = 0; i < numCourses; ++i) {
            if (dependencies.get(i).size() == 0) {
                this.depsArray[this.depsPtr++] = i;
                satisfied.add(i);
            }
        }
        
        // System.out.println(this.depsPtr);
        if (this.depsPtr == 0) { return new int[0]; }
        
        for (int i = 0; i < numCourses; ++i) {
            doFindOrder(numCourses, dependencies, satisfied, i, visited);
        }
        
        // System.out.printf("%d | %s%n", this.depsPtr, Arrays.toString(this.depsArray));
        
        return (this.depsPtr == numCourses) ? this.depsArray : new int[0];
    }
    
    private void doFindOrder(int N, List<List<Integer>> deps, Set<Integer> satisfied, int current, boolean[] visited) {
        if (satisfied.contains(current)) { return; }
        
        List<Integer> currentDeps = deps.get(current);
        visited[current] = true;
        
        for (int i = 0; i < currentDeps.size(); ++i) {
            int dep = currentDeps.get(i);
            
            if (!satisfied.contains(dep)) {
                if (!visited[dep]) {
                    doFindOrder(N, deps, satisfied, dep, visited);
                    
                    if (!satisfied.contains(dep)) {
                        visited[current] = false;
                        return;
                    }
                } else {
                    visited[current] = false;
                    return;
                }
            }
        }
        
        satisfied.add(current);
        this.depsArray[this.depsPtr++] = current;
    }
}
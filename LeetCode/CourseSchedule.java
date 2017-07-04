// AC
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> dependencies = new ArrayList<>();
        
        for (int i = 0; i < numCourses; ++i) { dependencies.add(new ArrayList<Integer>()); }
        
        for (int i = 0; i < prerequisites.length; ++i) {
            int course = prerequisites[i][0];
            int dep    = prerequisites[i][1];
            
            dependencies.get(course).add(dep);
        }
        
        Set<Integer> satisfied = new HashSet<Integer>();
        
        for (int i = 0; i < dependencies.size(); ++i) {
            if (dependencies.get(i).size() == 0) {
                satisfied.add(i);
            }
        }
        
        // Don't bother searching if there aren't any satisfied dependencies - w/o this got TLE.
        if (satisfied.size() == 0) { return false; }
        
        for (int i = 0; i < numCourses; ++i) {
            doCanFinish(numCourses, dependencies, satisfied, i, new boolean[numCourses]);
        }
        
        return satisfied.size() == numCourses;
    }
    
    private void doCanFinish(int N, List<List<Integer>> deps, Set<Integer> satisfied, int current, boolean[] visited) {
        if (satisfied.contains(current)) { return; }
        
        List<Integer> currentDeps = deps.get(current);
        
        visited[current] = true;
        
        // Check the current value for its dependencies to see if they're satisfied, or can be satisfied.
        for (int i = 0; i < currentDeps.size(); ++i) {
            int dep = currentDeps.get(i);
            
            if (satisfied.contains(dep)) { continue; }
            
            if (!visited[dep]) {
                doCanFinish(N, deps, satisfied, dep, visited);
                
                // Might have satisfied the dependencies after doing some dfs.
                if (!satisfied.contains(dep)) {
                    visited[current] = false;
                    return;
                }
            } else if (visited[dep]) {
                visited[current] = false;
                return;
            }
        }
        
        satisfied.add(current);
    }
}
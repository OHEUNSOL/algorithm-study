import java.util.*;

class Edge implements Comparable<Edge>{
    int to;
    int cost;
    
    Edge(int to, int cost) {
        this.to=to;
        this.cost=cost;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.cost-e.cost;
    }
}

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        List<List<Edge>> arr = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            arr.add(new ArrayList<>());
        }
        
        for(int[] cost : costs) {
            arr.get(cost[0]).add(new Edge(cost[1], cost[2]));
            arr.get(cost[1]).add(new Edge(cost[0], cost[2]));
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        int[] visited = new int[n];
        
        pq.add(new Edge(0,0));
        
        int L=0;
        int count=0;
        
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            
            if(visited[e.to]==0) {
                visited[e.to]=1;
                count+=e.cost;
                L++;
                for(Edge next : arr.get(e.to)) {
                    if(visited[next.to]==0) {
                        pq.add(new Edge(next.to, next.cost));
                    }
                }
            }
            
            if(L==n) {
                return count;
            }
        }
        
        return answer;
    }
}
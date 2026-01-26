import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        int answer = 0;
        int max=0;
        
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        
        for(int i=0; i<=n; i++) {
            map.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        
        dist[1]=0;
        
        int L = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            L++;
            for(int i=0; i<size; i++) {
                int current = queue.poll();
                
                for(int next : map.get(current)) {
                    if(dist[next]==-1) {
                        queue.offer(next);
                        dist[next] = L;
                        max = Math.max(L, max);
                    }
                }
            }
        }
        
        for(int d : dist) {
            if(d==max) answer++;
        }
        
        return answer;
    }
}
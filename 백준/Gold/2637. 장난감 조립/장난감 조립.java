import java.io.*;
import java.util.*;


public class Main
{
    static class Edge {
        int to, k;
        Edge(int to, int k) { this.to = to; this.k = k; }
    }
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Edge>> g = new ArrayList<>();
	    long answer = 0;
		
		for(int i=0; i<N+1; i++) {
		    g.add(new ArrayList<>());
		}
		
		int[] indegree = new int[N+1];
		
		for(int i=0; i<M; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int X = Integer.parseInt(st.nextToken());
		    int Y = Integer.parseInt(st.nextToken());
		    int K = Integer.parseInt(st.nextToken());
		    
		    g.get(Y).add(new Edge(X, K));
		    
		    indegree[X]++;
		}
		
		// dp[i][b] = i를 만들 때 기본부품 b가 필요한 개수
        // N<=100 이라 int로 충분(그래도 안전하게 long 권장)
        long[][] dp = new long[N + 1][N + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                dp[i][i] = 1; // 기본부품 i를 만들 때 i 1개 필요(자기 자신)
            }
        }
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(Edge e : g.get(cur)) {
                indegree[e.to]--;
                
                for(int i=1; i<=N; i++) {
                    dp[e.to][i] += e.k * dp[cur][i];
                }
                
                if(indegree[e.to]==0) q.offer(e.to);
            }
        }
        
        for(int i=1; i<=N; i++) {
            if(dp[i][i]==1) {
                System.out.println(i+" "+dp[N][i]);
            }
        }
        
	}
}

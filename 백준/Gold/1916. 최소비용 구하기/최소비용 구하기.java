import java.util.*;
import java.io.*;

public class Main
{
    static class Edge implements Comparable<Edge> {
        int v, sum;
        
        Edge(int v, int sum) {
            this.v=v;
            this.sum=sum;
        }
        
        public int compareTo(Edge n) {
            return this.sum-n.sum;
        }
    }
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<List<Edge>> list = new ArrayList<>();
		
		for(int i=0; i<N+1; i++) {
		    list.add(new ArrayList<>());
		}
		
		
		for(int i=0; i<M; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int s = Integer.parseInt(st.nextToken());
		    int e = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    
		    list.get(s).add(new Edge(e, c));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(s, 0));
		
		dist[s]=0;
		
		while(!pq.isEmpty()) {
		    Edge n = pq.poll();
		    
		    int nowsum = n.sum;
		    int nowv = n.v;
		    
		    if(dist[nowv]<nowsum) continue;
		    
		    for(Edge edge : list.get(nowv)) {
		        if(dist[edge.v]>nowsum+edge.sum) {
		            dist[edge.v]=nowsum+edge.sum;
		            pq.offer(new Edge(edge.v, nowsum+edge.sum));
		        }
		    }
		    
		}
		
		System.out.print(dist[e]);
	}
}

import java.util.*;
import java.io.*;

public class Main
{
    static class Node implements Comparable<Node> {
        int v, cost;
        
        Node(int v, int cost) {
            this.v=v;
            this.cost=cost;
        }
        
        public int compareTo(Node n) {
            return this.cost-n.cost;
        }
    }
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<List<Node>> list = new ArrayList<>();
		
		for(int i=0; i<N+1; i++) {
		    list.add(new ArrayList<>());
		}
		
		
		for(int i=0; i<M; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int s = Integer.parseInt(st.nextToken());
		    int e = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    
		    list.get(s).add(new Node(e, c));
		    list.get(e).add(new Node(s, c));
		}
		
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		visited[1]=true;
		
		pq.addAll(list.get(1));
		
		
		int sum=0;
		while(!pq.isEmpty()) {
		    Node n = pq.poll();
		    
		    if(visited[n.v]==true) continue;
		    
		    visited[n.v]=true;
		    
		    sum+=n.cost;
		    
		    for(Node o : list.get(n.v)) {
		        if(!visited[o.v]) pq.offer(o);
		    }
		}
		
		System.out.print(sum);
	}
}

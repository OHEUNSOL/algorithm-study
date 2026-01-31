import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    int vex;
    int cost;
    
    Edge(int vex, int cost) {
        this.vex=vex;
        this.cost=cost;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.cost-e.cost;
    }
}

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int n = Integer.parseInt(st.nextToken());
		    int d = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    
		    ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
		    
		    for(int j=0; j<=n; j++) {
		        arr.add(new ArrayList<>());
		    }
		    
		    for(int j=0; j<d; j++) {
		        st = new StringTokenizer(br.readLine());
		        
		        int a = Integer.parseInt(st.nextToken());
		        int b = Integer.parseInt(st.nextToken());
		        int s = Integer.parseInt(st.nextToken());
		        
		        arr.get(b).add(new Edge(a, s));
		    }
		    
		    PriorityQueue<Edge> pq = new PriorityQueue<>();
		    int[] dist = new int[n+1];
		    Arrays.fill(dist, Integer.MAX_VALUE);
		    
		    
		    pq.offer(new Edge(c,0));
		    dist[c]=0;
		    
		    while(!pq.isEmpty()) {
		        Edge e = pq.poll();
		        
		        if(dist[e.vex]<e.cost) continue;
		        
		        for(Edge next : arr.get(e.vex)) {
		            if(dist[next.vex]>e.cost+next.cost) {
		                dist[next.vex]=e.cost+next.cost;
		                pq.offer(new Edge(next.vex, e.cost+next.cost));
		            }
		        }
		    }
		    
		    int ans1=n;
		    int ans2=0;
		    
		    for(int w=1; w<dist.length; w++) {
		        if(dist[w]==Integer.MAX_VALUE) {
		            ans1--;
		            continue;
		        }
		        
		        ans2=Math.max(ans2, dist[w]);
		    }
		    
		    System.out.println(ans1+" "+ans2);
		}
	}
}

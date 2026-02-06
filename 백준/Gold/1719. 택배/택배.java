import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
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

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
		
		for(int i=0; i<=n; i++) {
		    arr.add(new ArrayList<>());
		}
		
		for(int i=0; i<m; i++) {
		    st = new StringTokenizer(br.readLine());
		    
		    int x = Integer.parseInt(st.nextToken());
		    int y = Integer.parseInt(st.nextToken());
		    int z = Integer.parseInt(st.nextToken());
		    
		    arr.get(x).add(new Edge(y, z));
		    arr.get(y).add(new Edge(x, z));
		}
		
		for(int i=1; i<=n; i++) {
		    int[] dist = new int[n+1];
		    
		    int[] firstPoint = new int[n+1];
		    
		    
		    Arrays.fill(dist, Integer.MAX_VALUE);
		    
		    PriorityQueue<Edge> pq = new PriorityQueue<>();
		    
		    dist[i]=0;
		    
		    pq.offer(new Edge(i,0));
		    
		    while(!pq.isEmpty()) {
		        Edge cur = pq.poll();
		        
		        if(dist[cur.to]<cur.cost) continue;
		        
		        for(Edge next : arr.get(cur.to)) {
		            if(dist[next.to]>cur.cost+next.cost) {
		                dist[next.to] = cur.cost+next.cost;
		                pq.offer(new Edge(next.to, dist[next.to]));
		                if(i==cur.to) firstPoint[next.to]=next.to;
		                else {
		                    firstPoint[next.to]=firstPoint[cur.to];
		                }
		            }
		        }
		    }
		    
		    for(int x=1; x<=n; x++) {
		        if(firstPoint[x]==0) System.out.print("- ");
		        else System.out.print(firstPoint[x]+" ");
		    }
		    
		    System.out.println();
		    
		}
		
	}
}

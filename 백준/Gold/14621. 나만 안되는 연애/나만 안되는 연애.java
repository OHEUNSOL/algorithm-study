import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(st.nextToken());
		
		int answer=0;
		
		st = new StringTokenizer(br.readLine());
		
		boolean[] kind = new boolean[N+1];
		
		ArrayList<ArrayList<Edge>> arr = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
		    arr.add(new ArrayList<>());
		}
		
		for(int i=1; i<=N; i++) {
		    String str = st.nextToken();
		    
		    if(str.equals("W")) kind[i]=true;
		}
		
		for(int i=0; i<M; i++) {
		    st = new StringTokenizer(br.readLine());
		    int u = Integer.parseInt(st.nextToken());
		    int v = Integer.parseInt(st.nextToken());
		    int d = Integer.parseInt(st.nextToken());
		    
		    arr.get(u).add(new Edge(v, d));
		    arr.get(v).add(new Edge(u, d));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[N+1];
		
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()){
		    Edge cur = pq.poll();
		    
		    int v = cur.vex;
		    
		    if(!check[v]) {
		        check[v]=true;
		        
		        answer += cur.cost;
		        
		        for(Edge e : arr.get(v)) {
		            int a = e.vex;
		            int b = e.cost;
		            
		            if(kind[a]==kind[v] || check[a]) continue;
		            
		            pq.offer(new Edge(a, b));
		        }   
		    }
		}
		
		for(int i=1; i<=N; i++) {
		    if(!check[i]) answer = -1;
		}
		
		System.out.println(answer);
	}
}

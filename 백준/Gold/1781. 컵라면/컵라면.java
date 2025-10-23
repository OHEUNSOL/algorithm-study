import java.util.*;
import java.io.*;

public class Main
{   
    static class P implements Comparable<P> {
        int dead;
        int count;
        
        P(int dead, int count) {
            this.dead=dead;
            this.count=count;
        }
        
        @Override
        public int compareTo(P p) {
            return p.dead-this.dead;
        }
    }
	public static void main(String[] args) throws IOException{
		long answer=0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		P[] arr = new P[N];
		
		for(int i=0; i<N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    arr[i]=new P(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr);
		
		int L=N;
		int i=0;
		
		while(i<N && L>0) {
		    while(i<N && arr[i].dead==L) {
		        pq.add(arr[i].count);
		        i++;
		    }
		    
		    if(!pq.isEmpty()) {
		        answer += (long) pq.poll();
		    }
		    
		    L--;
		    
		}
		
		while(L>0) {
		    if(!pq.isEmpty()) {
		        answer += (long) pq.poll();
		    }
		    
		    L--;
		}
		
		System.out.print(answer);
	}
}

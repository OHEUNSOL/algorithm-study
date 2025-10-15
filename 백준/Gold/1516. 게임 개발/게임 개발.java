import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[] time;
    static int[] dp;
    static ArrayList<ArrayList<Integer>> ready;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N+1];
		dp = new int[N+1];
		ready = new ArrayList<>();
		
		for(int i=0; i<=N; i++) {
		    ready.add(new ArrayList<>());
		}

		for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            time[i] = Integer.parseInt(st.nextToken());
            
            while (true) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) break;
                ready.get(i).add(x);
            }
		}
		
		for(int i=1; i<=N; i++){
		    if(ready.get(i).isEmpty()) {
		        dp[i]=time[i];
		    }
		}
		
		for(int i=1; i<=N; i++){
	        int best=0;
    	    for(int n : ready.get(i)) {
    	        best = Math.max(best, calc(n));
    	    }
    	    System.out.println(best+time[i]);
		}

	}
	
	static int calc(int num) {
	    if(dp[num] != 0) return dp[num];

	    int best=0;
	    for(int n : ready.get(num)) {
	        best = Math.max(best, calc(n));
	    }
	    
	    return dp[num]=best+time[num];
	}

}

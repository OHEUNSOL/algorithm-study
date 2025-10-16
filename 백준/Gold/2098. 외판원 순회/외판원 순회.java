import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[][] arr;
    static int[][] dp;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[1<<N][N];
		
		for(int i=0; i<N; i++){
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int j=0; j<N; j++) {
		        arr[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		System.out.print(DFS(1<<0, 0));
	}
	
	static int DFS(int mask, int cur) {
	    if(dp[mask][cur]>0) {
	        return dp[mask][cur];
	    }
	    
	    if(mask==((1<<N)-1)) {
	        if(arr[cur][0]!=0) return dp[mask][cur]=arr[cur][0];
	        else {
	            return dp[mask][cur]=Integer.MAX_VALUE;
	        }
	    }
	    
	    int minCost=Integer.MAX_VALUE;
	    
	    for(int i=0; i<N; i++) {
	        if(arr[cur][i]!=0 && !((mask | 1<<i)==mask)) {
	            int child = DFS(mask | 1<<i, i);
	            if(child==Integer.MAX_VALUE) continue;
	            minCost=Math.min(minCost, child+arr[cur][i]);
	        }
	    }
	    
	    return dp[mask][cur]=minCost;
	}
	
}

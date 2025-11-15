import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[][] board;
    static int P;
    static int[] dp;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		dp = new int[1<<N];
		Arrays.fill(dp, -1);
		
		for(int i=0; i<N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int j=0; j<N; j++) {
		        board[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		String str = br.readLine();
		int init = 0;
		
		for(int i=0; i<N; i++) {
		    if(str.charAt(i)=='Y') {
		        init = init | 1<<i;
		    }
		}
		
		P = Integer.parseInt(br.readLine());
		
		int on = Integer.bitCount(init);
        if (P == 0) {
            System.out.println(0);
            return;
        }
        if (on == 0) {
            System.out.println(-1);
            return;
        }
        if (on >= P) {
            System.out.println(0);
            return;
        }

		System.out.print(dfs(init));   
	}
	
	static int dfs(int mask) {
	    
	    if(dp[mask]!=-1) return dp[mask];
	    
	    if(Integer.bitCount(mask)==P) {
	        dp[mask]=0;
	        return 0;
	    }
	    
	    Set<Integer> zeros = new TreeSet<>();
	    
	    for (int i = 0; i < N; i++) {
            if ((mask & (1 << i)) == 0) {
                zeros.add(i);
            }
        }
        
        int minValue=Integer.MAX_VALUE;
        
	    for(int i : zeros) {
	        for(int j=0; j<N; j++) {
	            if(j==i || zeros.contains(j)) continue;
	            
	            minValue = Math.min(minValue, dfs(mask|1<<i)+board[j][i]);
	        }
	    }
	    
	    return dp[mask]=minValue;
	}

}
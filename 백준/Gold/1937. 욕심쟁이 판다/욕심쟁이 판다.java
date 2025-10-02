import java.util.*;
import java.io.*;

public class Main
{
    static int n;
    static int[][] map;
    static int[][] dp;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];

		for(int i=0; i<n; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int j=0; j<n; j++) {
		        map[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		int answer=0;
		
		for(int i=0; i<n; i++) {
		    for(int j=0; j<n; j++) {
		        answer=Math.max(answer,DFS(i,j));
		    }
		}
		
		System.out.print(answer);
	}
	
	static int DFS(int x, int y) {
	    if(dp[x][y]>=1) {
	        return dp[x][y];
	    }
	    
	    else{
	        dp[x][y]=1;
	        
	        for(int i=0; i<4; i++) {
	            int nx = x+dx[i];
	            int ny = y+dy[i];
	            
	            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
	            
	            if(map[nx][ny]>map[x][y]) {
	                dp[x][y]=Math.max(DFS(nx, ny)+1, dp[x][y]);
	            }
	        }
	    }
	    
	    return dp[x][y];
	}
}

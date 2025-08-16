import java.util.*;
import java.io.*;

public class Main
{
    static int M;
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		
		for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1); // 아직 계산 안 된 상태 표시
        }
		
		for(int i=0; i<M; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<N; j++) {
		        map[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		DFS(0,0);
		
		System.out.print(dp[0][0]);

		
	}
	
	private static int DFS(int x, int y) {
	    if(dp[x][y]>=0) {
            return dp[x][y];
	    }
	    
	    if(x==M-1 && y==N-1) {
	        return 1;
	    }
	    
	    else {
	        dp[x][y]=0;
	        
	        for(int i=0; i<4; i++) {
	            int nx = x+dx[i];
	            int ny = y+dy[i];
	            
	            if(nx>=0 && nx<M && ny>=0 && ny<N && map[nx][ny]<map[x][y]) {
	                dp[x][y] += DFS(nx, ny);
	            }
	        }
	    }
	    
	    return dp[x][y];
	}
}
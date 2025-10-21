import java.util.*;

class Solution {
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static int M, N;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        map = new int[n][m];
        dp = new int[n][m];
        M=m;
        N=n;
        
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        for(int[] p : puddles) {
            map[p[1]-1][p[0]-1]=1;
        }
        
        answer = DFS(0,0);
        
        return answer%1000000007;
    }
    
    static int DFS(int x, int y) {
        if(dp[x][y]!=-1) {
            return dp[x][y];
        }
        
        if(x==N-1 && y==M-1) {
            return 1;
        }
        
        dp[x][y]=0;
        
        for(int i=0; i<2; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny]!=1) {
                dp[x][y]+=DFS(nx, ny);
                dp[x][y]%=1000000007;
            }
        }
        
        return dp[x][y]%1000000007;
    }
}
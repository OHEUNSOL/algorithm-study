import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visiting;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean infinite = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        dp = new int[N][M];
        visiting = new boolean[N][M];
        
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
        

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int ans = dfs(0, 0);
        System.out.println(infinite ? -1 : ans);
    }
    
    private static int dfs(int x, int y) {
        
        if(dp[x][y]!=-1) return dp[x][y];
        
        if (visiting[x][y]) {
            infinite = true;
            return 0;
        }
        
        visiting[x][y] = true;
        
        int len = board[x][y]-'0';
        
        int max=0;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i]*len;
            int ny = y + dy[i]*len;
            
            if(nx<0 || nx>=N || ny<0 || ny>=M || board[nx][ny]=='H') {
                max = Math.max(max, 1);
                continue;
            }
            
            max = Math.max(max, dfs(nx, ny)+1);

        }
        
        visiting[x][y] = false;
        
        dp[x][y] = max;
        
        return dp[x][y];
    }
}
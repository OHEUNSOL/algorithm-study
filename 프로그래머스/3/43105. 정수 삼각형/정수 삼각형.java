import java.util.*;

class Solution {
    static int[][] dp;
    static int N;
    static int[][] tri;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        N = triangle.length;
        tri = triangle;
        
        dp = new int[N][N];
        
        answer = DFS(0,0);
        
        return answer;
    }
    
    static int DFS(int x, int y) {
        if(dp[x][y]>0) {
            return dp[x][y];
        }
        
        if(x==N-1) {
            return dp[x][y]=tri[x][y];
        }
        
        int maxValue=0;
        
        for(int i=0; i<2; i++) {
            maxValue = Math.max(maxValue, tri[x][y]+DFS(x+1, y+i));
        }
        
        return dp[x][y]=maxValue;
        
    }
}
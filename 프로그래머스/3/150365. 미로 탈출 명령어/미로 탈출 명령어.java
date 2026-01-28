import java.util.*;

class Solution {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String[] dir = {"d", "l", "r", "u"}; 
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder answer = new StringBuilder();
        x--;
        y--;
        r--;
        c--;
        if(!possible(x, y, r, c, k)) return "impossible";
        
        int L=k;
        
        while(L>0){
            for(int i=0; i<4; i++) {
                int nx=x+dx[i];
                int ny=y+dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m || !possible(nx,ny,r,c,L-1)) continue;
                answer.append(dir[i]);
                x=nx;
                y=ny;
                L--;
                break;
            }
        }
        
        return answer.toString();
    }
    
    private boolean possible(int x, int y, int r, int c, int k) {
        int distance = Math.abs(x-r)+Math.abs(y-c);
        if(distance>k || (k - distance) % 2 == 1) return false;
        return true;
    }
    
}
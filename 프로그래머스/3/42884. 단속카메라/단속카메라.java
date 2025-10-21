import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (a, b) -> a[1]-b[1] );
        
        int i = 0;
        int j = 1;
        
        while(i<routes.length && j<routes.length) {
            int out = routes[i][1];
            
            while(i<routes.length && j<routes.length) {
                if(routes[j][0] <= out) {
                    j++;
                }
                
                else {
                    answer++;
                    i=j;
                    j=i+1;
                    break;
                }
                
            }
            
        }
        
        return answer;
    }
}
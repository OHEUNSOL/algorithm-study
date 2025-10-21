import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        int N = times.length;
        Arrays.sort(times);
        
        long low = 0;
        long high = (long) times[N-1]*n;
        
        
        while(low<high) {
            long mid = (high+low)/2;
            
            long result = calc(mid, times, n);
            
            if(result>=n) {
                answer = Math.min(mid, answer);
                high = mid;
            }
            
            else {
                low = mid+1;
            }
        }
        
        
        return answer;
    }
    
    static long calc(long mid, int[] times, long n) {
        long result=0;
        
        for(long t : times) {
            result += mid/t;
        }
        
        return result;
    } 
}
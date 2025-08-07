import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works) {
            queue.offer(work);
        }   
        
        for(int i=0; i<n; i++) {
            
            int next = queue.poll();
            
            if(next==0) return 0;
            
            queue.offer(next-1);
        }
        
        for(int i=0; i<works.length; i++) {
            int next = queue.poll();
            answer+=next*next;
        }
        
        return answer;
    }
}
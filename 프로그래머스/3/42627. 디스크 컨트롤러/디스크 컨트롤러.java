import java.util.*;

class Job implements Comparable<Job> {
    int id;
    int duration;
    int start;
    
    Job(int id, int duration, int start) {
        this.id=id;
        this.duration=duration;
        this.start=start;
    }
    
    @Override
    public int compareTo(Job j) {
        int cmp = Integer.compare(this.duration, j.duration);
        if(cmp!=0) return cmp;
        
        cmp = Integer.compare(this.start, j.start);
        if(cmp!=0) return cmp;
        
        return Integer.compare(this.id, j.id);
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        int len = jobs.length;
        
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0], b[0]));
        
        PriorityQueue<Job> pq = new PriorityQueue<>();
        
        int time = 0;
        int idx=0;
        while(idx < len || !pq.isEmpty()) {
            while(idx<len && jobs[idx][0]<=time) {
                pq.offer(new Job(idx, jobs[idx][1], jobs[idx][0]));
                idx++;
            }
            
            if(!pq.isEmpty()) {
                Job j = pq.poll();
                time += j.duration;
                answer+= time-j.start;
            }
            
            else time++;
        }
        
        return answer/len;
    }
}
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(n>s) return new int[] {-1};
        
        int init = s/n;
        
        int alpha = s%n;
        
        for(int i=n-1; i>=0; i--) {
            if(alpha>0) {
                answer[i] = init+1;
                alpha--;
            }
            else {
                answer[i] = init;
            }
        }
        
        
        return answer;
    }
}
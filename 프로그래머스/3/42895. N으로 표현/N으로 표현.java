import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        
        if(N==number) return 1;
        
        for(int i=0; i<=8; i++) {
            dp.add(new HashSet<>());
        }        

        dp.get(1).add(N);
        
        for(int i=2; i<=8; i++) {
            int a=0;
            int b=0;
            
            int init=0;
            int o=1;
            for(int z=1; z<=i; z++) {
                init += N*o;
                o*=10;
            }
            dp.get(i).add(init);
            
            // 두 수 고르기
            for(int j=1; j<i; j++) {
                for(int w=1; w<i; w++) {
                    if(j+w==i) {
                        a=j;
                        b=w;
                        
                        for(int x : dp.get(a)) {
                            for(int y : dp.get(b)) {
                                dp.get(i).add(x+y);
                                dp.get(i).add(x-y);
                                dp.get(i).add(y-x);
                                dp.get(i).add(x*y);
                                if(y!=0) {
                                    dp.get(i).add(x/y);
                                }
                                if(x!=0) {
                                    dp.get(i).add(y/x);
                                }
                            }
                        }
                    }
                }
            }
            
            if(dp.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}
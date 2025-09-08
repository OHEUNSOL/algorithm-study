import java.util.*;
import java.io.*;

public class Main
{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		int[] ans = new int[N];
		
		Deque<Integer> stack = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
		    nums[i] = Integer.parseInt(st.nextToken());
		}

		
		for(int i=N-1; i>=0; i--) {
		    if(stack.isEmpty()) {
		        ans[i]=-1;
		        stack.push(nums[i]);
		    }
		    
		    else {
		        while(stack.size()>0 && stack.peek()<=nums[i]) {
		            stack.pop();
		        }
		        
		        if(stack.isEmpty()) {
		            ans[i]=-1;
		            stack.push(nums[i]);
		        }
		        
		        else {
		            ans[i]=stack.peek();
		            
		            stack.push(nums[i]);
		        }
		    }
		}
		
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(ans[i]).append(' ');
        System.out.print(sb.toString());
	}
}

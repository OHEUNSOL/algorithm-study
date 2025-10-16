import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
		    arr[i]=Integer.parseInt(st.nextToken());
		}
		
		if(arr[0]>=S) {
		    System.out.print(1);
		    return;
		}
		
		int lp=0;
		int rp=1;
		int sum=arr[0]+arr[1];
		int result=Integer.MAX_VALUE;
		
		while(lp<=rp && rp<N) {
		    if(sum>=S) {
		        result=Math.min(result, rp-lp+1);
		        sum-=arr[lp];
		        lp++;
		    }
		    
		    else {
		        if(rp==N-1) {
		            break;
    		    }
    		    rp++;
    		    sum+=arr[rp];
		    }
		}
		
		if(result==Integer.MAX_VALUE) {
		    System.out.print(0);
		}
		else {
		    System.out.print(result);
		}
	}
}

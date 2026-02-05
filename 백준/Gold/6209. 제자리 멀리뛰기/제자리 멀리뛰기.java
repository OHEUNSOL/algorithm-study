import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] dis = new int[n];
		
		for(int i=0; i<n; i++) {
		    dis[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(dis);
		
		int minValue = 0;
		int maxValue = d;
		int result = 0;
		
		if(n==0) {
		    System.out.println(d);
		    return;
		}
		
		while(minValue<=maxValue) {
		    int mid = (minValue+maxValue)/2;
		    
		    if(calc(mid, dis, d)>m) {
		        maxValue = mid-1;
		    }
		    else {
		        minValue = mid+1;
		        result = mid;
		    }
		}
		
		System.out.print(result);
	}
	
	private static int calc(int mid, int[] dis, int d) {
	    int start=0;
	    int result=0;
	    for(int i=0; i<dis.length; i++) {
	        if(dis[i]-start<mid) {
	            result++;
	        }
	        else start = dis[i];
	    }
	    
	    if(d-dis[dis.length-1]<mid) {
	        result++;
	    }
	    
	    return result;
	}
}

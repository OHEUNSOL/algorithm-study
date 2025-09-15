import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		
		for(int i=0; i<n; i++) {
		    coin[i]=Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1];
		
		for(int i=0; i<n; i++) {
		    for(int j=coin[i]; j<=k; j++) {
		        if(j==coin[i]) dp[j] +=1;
		        dp[j] += dp[j-coin[i]];
		    }
		}
		
		System.out.print(dp[k]);
	}
}

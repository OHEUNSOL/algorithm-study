import java.util.*;
import java.io.*;

public class Main
{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int R = str1.length();
		
		int C = str2.length();
		
		int[][] dp = new int[R+1][C+1];
		
		int total=0;

		
		for(int i=1; i<=R; i++) {
		    for(int j=1; j<=C; j++) {
		        if(str1.charAt(i-1)==str2.charAt(j-1)) {
		            dp[i][j]=dp[i-1][j-1]+1;
		        }
		        
		        else {
		            dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
		        }
		        
		        total = Math.max(total, dp[i][j]);
		    }
		}
		
		System.out.print(total);
		
	}
}

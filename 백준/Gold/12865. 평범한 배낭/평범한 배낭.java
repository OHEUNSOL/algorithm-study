import java.util.*;
import java.io.*;

public class Main
{

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<int[]> items = new ArrayList<>();
		int[] dp = new int[K+1];
		
		for(int i=0; i<N; i++) {
		    st = new StringTokenizer(br.readLine());
		    items.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		for(int i=0; i<N; i++) {
		    int weight = items.get(i)[0];
		    int price = items.get(i)[1];
		    
		    for(int j=K; j>=weight; j--) {
		        dp[j]=Math.max(dp[j], dp[j-weight]+price);
		    }
		}
		
		System.out.println(dp[K]);
	}
}

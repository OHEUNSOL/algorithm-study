import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] list = new int[N];
		int[] asc = new int[N];
		int[] des = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
		    list[i]=Integer.parseInt(st.nextToken());
		}
		
		asc[0]=1;
		des[N-1]=1;
		
		for(int i=1; i<N; i++) {
		    int maxNum=0;
		    for(int j=0; j<i; j++) {
		        if(list[j]<list[i]) {
		            maxNum=Math.max(maxNum, asc[j]);
		        }
		    }
		    asc[i]=maxNum+1;
		}
		
		for(int i=N-2; i>=0; i--) {
		    int maxNum=0;
		    for(int j=N-1; j>i; j--) {
		        if(list[j]<list[i]) {
		            maxNum=Math.max(maxNum, des[j]);
		        }
		    }
		    des[i]=maxNum+1;
		}
		
		int result=0;
		
		for(int i=0; i<N; i++) {
		    result=Math.max(result, asc[i]+des[i]-1);
		}
		
		System.out.print(result);
		
	}
}

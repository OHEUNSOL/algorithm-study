import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long answer=0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] listA = new int[2*m];
		int[] listB = new int[2*n];
		
		int[] sumA = new int[1000001];
		int[] sumB = new int[1000001];
		
		for(int i=0; i<m; i++) {
		    listA[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=m; i<2*m; i++) {
		    listA[i]=listA[i-m];
		}
		
		for(int i=0; i<n; i++) {
		    listB[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=n; i<2*n; i++) {
		    listB[i]=listB[i-n];
		}
		
		sumA[0]=1;
		sumB[0]=1;
		

		int size=0;
		
		while(size<m) {
		    int sum=0;
		    int lt=0;
		    int rt=size;
		    
		    for(int i=0; i<=rt; i++) {
		        sum+=listA[i];
		    }
		    
		    sumA[sum]++;
		    
		    while(rt<m-1+size && size<m-1) {

    		    sum+=listA[++rt];
    		    sum-=listA[lt++];
    		    
    		    sumA[sum]++;
		    }

		    size++;
		}
		
		size=0;
		
		while(size<n) {
		    int sum=0;
		    int lt=0;
		    int rt=size;
		    
		    for(int i=0; i<=rt; i++) {
		        sum+=listB[i];
		    }
		    
		    sumB[sum]++;
		    
		    while(rt<n-1+size && size<n-1) {
		    
    		    sum+=listB[++rt];
    		    sum-=listB[lt++];
    		    
    		    sumB[sum]++;
		    }

		    size++;
		}
		
		for(int i=0; i<=N; i++) {
    	    answer += sumA[i]*sumB[N-i];
    	}
	
	    System.out.print(answer);
	}
	
	
}

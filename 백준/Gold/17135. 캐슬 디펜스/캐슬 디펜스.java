import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int M;
    static int D;
    static int[][] map;
    static int answer=0;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		
		for(int i=0; i<N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<M; j++) {
		        map[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		int[] check = new int[3];
		
		comb(0,0, check);
		
		System.out.print(answer);
		
	}

	public static void comb(int L, int s, int[] check) {
	    if(L==3) {
	        answer = Math.max(answer, calc(check));
	        return;
	    }
	    
	    else {
	        for(int i=s; i<M; i++) {
	            check[L]=i;
	            comb(L+1, i+1, check);
	        }
	    }
	}
	
	public static int calc(int[] check) {
	    int count=0;
	    int[][] copy = new int[N][M];
	    
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();  // 각 행을 clone
        }
        
        boolean isEnd = false;
        
        // 적이 없어질때까지
        while(isEnd == false) {

            isEnd=true;
            
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(copy[i][j]==1) isEnd=false;
                }
            }
            
            if(isEnd==true) break;
            
            Set<List<Integer>> kill = new HashSet<>();
    	    
    	    // 궁수 턴
    	    for(int i=0; i<3; i++) {
    	        int ax=N;
    	        int ay=check[i];
    	        int dis=Integer.MAX_VALUE;
    	        int kx=-1;
    	        int ky=-1;
    	        for(int x=0; x<N; x++) {
    	            for(int y=0; y<M; y++) {
    	                if(copy[x][y]==0) continue;
    	                
    	                int dist = Math.abs(x-ax)+Math.abs(y-ay);
    	                
    	                if(dist<dis && D>=dist) {
    	                    kx=x;
    	                    ky=y;
    	                    dis=Math.abs(x-ax)+Math.abs(y-ay);
    	                }
    	                
    	                if(dist==dis && D>=dist && y<ky) {
    	                    kx=x;
    	                    ky=y;
    	                }

    	                
    	            }
    	        }
    	        
    	        if(kx!=-1 && ky!=-1) {
    	            kill.add(Arrays.asList(kx, ky));
    	        }
    	        
    	    }
    	    
    	    //적 제외
    	    for (List<Integer> pos : kill) {
                int x = pos.get(0);
                int y = pos.get(1);
                copy[x][y] = 0;
                count++;
            }
    	    
    	    //적 이동
    	    for(int i=N-1; i>0; i--) {
    	        for(int j=0; j<M; j++) {
    	            copy[i][j]=copy[i-1][j];
    	        }
    	    }
    	    
    	    for(int i=0; i<M; i++) {
    	        copy[0][i]=0;
    	    }
        }
	    
	    return count;
	}
}

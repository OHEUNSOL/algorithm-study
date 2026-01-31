import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int K = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		int[][] check = new int[N][N];
		
		//오, 아래, 왼, 위
		int[] dx = new int[] {0,1,0,-1};
		int[] dy = new int[] {1,0,-1,0};
		
		for(int i=0; i<K; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    
		    map[a-1][b-1] = -1;
		    
		}
		
		int L = Integer.parseInt(br.readLine());
		
		Map<Integer, Character> hm = new HashMap<>();
		
		for(int i=0; i<L; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    int a = Integer.parseInt(st.nextToken());
		    String str = st.nextToken();
		    char b = str.charAt(0);
		    
		    hm.put(a, b);
		}
		
		int t=0;
		int x=0;
		int y=0;
		int n=0;
		int m=0;
		int d=0;
		
		while(t<=10000) {
		    t++;
		    
		    int nx = x+dx[d];
		    int ny = y+dy[d];
		    
		    if(nx<0 || nx>=N || ny<0 || ny>=N || (nx==n && ny==m) || map[nx][ny]==2) break;
		    check[x][y]=d;
		    
		    
		    x=nx;
		    y=ny;
		    
		    if(map[x][y]==-1) {
		        map[x][y]=0;
		    }
		    else {
		        int nd = check[n][m];
		        
		        map[n][m]=0;
		        
		        n = n+dx[nd];
		        m = m+dy[nd];
		       
		    }
		    
		    map[x][y]=2;
		    
		    if(hm.containsKey(t)) {
		        char v = hm.get(t);
		        
		        if(v=='D') d=(d+1)%4;
		        else d=(d+3)%4;
		    }
		}
		
		System.out.print(t);
	}
}

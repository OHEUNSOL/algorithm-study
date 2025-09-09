import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int M;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] map;
    static int count=1;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
		    String str = br.readLine();
		    for(int j=0; j<M; j++) {
		        map[i][j]=str.charAt(j)-'0';
		    }
		}
		
		if (N == 1 && M == 1) { System.out.print(1); return; }
		
		if(BFS()) {
		    System.out.print(count);
		}
		
		else {
		    System.out.print(-1);
		}
	}
	
	public static boolean BFS() {
	    // 벽 부순 상태의 여부를 표현하기 위해 3차원 배열 사용
	    int[][][] visited = new int[N][M][2];
	    
	    Queue<int[]> queue = new LinkedList<>();
	    
	    queue.offer(new int[] {0,0,0});
	    
	    visited[0][0][0]=1;
	    
	    
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        count++;
	        for(int i=0; i<size; i++) {
	            int[] q = queue.poll();
	            
	            for(int j=0; j<4; j++) {
	                int nx = q[0]+dx[j];
	                int ny = q[1]+dy[j];
	                
	                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
	                
	                if(nx==N-1 && ny==M-1) {
	                    return true;
	                }
	                
	                //이전에벽을 부순 상태이면
	                if(q[2]==1) {
	                    if(map[nx][ny]==0 && visited[nx][ny][1]==0) {
	                        visited[nx][ny][1]=1;
	                        queue.offer(new int[] {nx,ny,1});
	                    }
	                }
	                
	                else{
	                    if(map[nx][ny]==0 && visited[nx][ny][0]==0) {
	                        visited[nx][ny][0]=1;
	                        queue.offer(new int[] {nx,ny,0});
	                    }
	                    
	                    if(map[nx][ny]==1 && visited[nx][ny][1]==0) {
	                        visited[nx][ny][1]=1;
	                        queue.offer(new int[] {nx,ny,1});
	                    }
	                }
	                
	            }
	            
	        }
	        
	    }
	    
	    return false;
	}
}

import java.util.*;
import java.io.*;

public class Main
{
    static int N;
    static int[][] world;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int result=Integer.MAX_VALUE;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		world = new int[N][N];
		
		for(int i=0; i<N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int j=0; j<N; j++) {
		        world[i][j]=Integer.parseInt(st.nextToken());
		    }
		}
		
		int land = 2;
		
		// 대륙별 값 다르게 지정(2~)
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		        if(world[i][j]==1) {
		            world[i][j]=land;
		            DFS(i, j, land);
		            land++;
		        }
		    }
		}
		
		// 거리 측정
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		        if(world[i][j]>0) {
		            int count = BFS(i, j, world[i][j]);
		            if(count==0) continue;
		            
		            result = Math.min(count, result);
		        }
		    }
		}
		
		System.out.println(result);
		
	}
	public static int BFS(int x, int y, int land) {
	    int count=-1;
	    
	    Queue<int[]> queue = new LinkedList<>();
	    
	    int[][] ch = new int[N][N];
	    
	    queue.offer(new int[] {x, y});
	    
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        
	        for(int j=0; j<size; j++) {
	            int[] p = queue.poll();
	            for(int i=0; i<4; i++) {
    	            int nx = p[0]+dx[i];
    	            int ny = p[1]+dy[i];
    	            
    	            if(nx>=0 && nx<N && ny>=0 && ny<N && world[nx][ny]>0 && world[nx][ny] != land) {
    	                return count+1;
    	            }
    	            
    	            if(nx>=0 && nx<N && ny>=0 && ny<N && world[nx][ny]==0 && ch[nx][ny]==0) {
    	                ch[nx][ny]=1;
    	                queue.offer(new int[] {nx, ny});
    	                
    	            }
	            }
	        }
	        
	        count++;

	    }
	    
	    return count;
	    
	}
	
	public static void DFS(int x, int y, int land) {
        for(int i=0; i<4; i++) {
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(nx>=0 && nx<N && ny>=0 && ny<N && world[nx][ny]==1) {
                world[nx][ny]=land;
                DFS(nx, ny, land);
            }
        }
	}
}

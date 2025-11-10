import java.util.*;
import java.io.*;



public class Main
{
    static int N;
    static int M;
    static char[][] board;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][][][] visited;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		// 현재 열쇠 상태, 이미 이동한 문, 현재 x, 현재 y
		visited = new int[1<<6][1<<6][N][M];
		
		board = new char[N][M];
		
		int start_x=0;
		int start_y=0;
		
		for(int i=0; i<N; i++) {
		    String str = br.readLine();
		    for(int j=0; j<M; j++) {
		        board[i][j] = str.charAt(j);
		        if(board[i][j]=='0') {
		            start_x=i;
		            start_y=j;
		        }
		    }
		}
		
		System.out.print(BFS(start_x, start_y));
		
	}
	
	static int BFS(int sx, int sy) {
	    Queue<int[]> queue = new LinkedList<>();
	    
	    queue.offer(new int[] {0,0,sx,sy});
	    
	    int count=0;
	    
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        count++;
	        for(int i=0; i<size; i++) {
	            int[] cur = queue.poll();
	            
	            int key = cur[0];
	            int door = cur[1];
	            int x = cur[2];
	            int y = cur[3];
	            
	            for(int j=0; j<4; j++) {
	                int nx = x+dx[j];
	                int ny = y+dy[j];
	                
	                
	                if(nx<0 || nx>=N || ny<0 || ny>=M || board[nx][ny]=='#' || visited[key][door][nx][ny]==1) continue;
	                
	                if(board[nx][ny]=='1') {
	                    return count;
	                }
	                
	            
	                if(board[nx][ny]=='.' || board[nx][ny]=='0') {
	                    queue.offer(new int[] {key,door,nx,ny});
	                    visited[key][door][nx][ny]=1;
	                }
	                
	                if(Character.isLowerCase(board[nx][ny])) {
	                    int curKey = key | 1<<((int)board[nx][ny]-97);
	                    if(visited[curKey][door][nx][ny]==0) {
	                        visited[curKey][door][nx][ny]=1;
	                        queue.offer(new int[] {curKey,door,nx,ny});
	                    }
	                }
	                
	                if(Character.isUpperCase(board[nx][ny])) {
	                    boolean hasKey = (key | (1<<((int)board[nx][ny]-65)))==key;
	                    int curDoor = door | (1<<((int)board[nx][ny]-65));
	                    if(hasKey && visited[key][curDoor][nx][ny]==0) {
	                        visited[key][curDoor][nx][ny]=1;
	                        queue.offer(new int[] {key,curDoor,nx,ny});
	                    }
	                }
	                
	            }
	        }
	    }
	    
	    return -1;
	}
}

import java.util.*;
import java.io.*;

public class Main
{
    static int R;
    static int C;
    static char[][] board;
    static int[] dx={0,0,1,-1};
    static int[] dy={1,-1,0,0};
    static int time=0;
    static int fcount=0;
    static Queue<int[]> queuef = new LinkedList<>();
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		
		
		int jx=0;
		int jy=0;
		
		for(int i=0; i<R; i++) {
		    String line = br.readLine();
		    for(int j=0; j<C; j++) {
		        board[i][j] = line.charAt(j);
		        
		        if(board[i][j]=='J') {
		            jx=i;
		            jy=j;
		        }
		        
		        if(board[i][j]=='F') {
		            fcount++;
		            queuef.offer(new int[] {i,j});
		        }
		    }
		}
		
		if(BFS(jx, jy)==true) {
		    System.out.print(time);
		}
		
		else {
		    System.out.print("IMPOSSIBLE");
		}
		
	}
	
	public static boolean BFS(int jx, int jy) {
	    Queue<int[]> queuej = new LinkedList<>();
	    
	    queuej.offer(new int[] {jx, jy});
	    
		while(!queuej.isEmpty()) {
		    int sizej = queuej.size();
		    
		    time++;
		    
		    //지훈이 이동
		    for(int i=0; i<sizej; i++) {
		        int[] j = queuej.poll();
		        
		        //불이 이미 번졌을 경우
		        if(board[j[0]][j[1]]=='F') {
		            continue;
		        }

		        for(int w=0; w<4; w++) {
		            int nx = j[0]+dx[w];
		            int ny = j[1]+dy[w];
		            
		            // 탈출한 경우
		            if(nx<0 || nx>=R || ny<0 || ny>=C) {
		                return true;
		            }
		            
		            if(nx>=0 && nx<R && ny>=0 && ny<C && board[nx][ny]=='.') {
		                board[nx][ny]='*';
		                queuej.offer(new int[] {nx, ny});
		            }
		        }
		    }
		    
		    int sizef = queuef.size();
		    
		    //불 번지기
		    for(int i=0; i<sizef; i++) {
		        int[] f = queuef.poll();

		        for(int w=0; w<4; w++) {
		            int nx = f[0]+dx[w];
		            int ny = f[1]+dy[w];
		            
		            if(nx>=0 && nx<R && ny>=0 && ny<C && board[nx][ny]!='#' && board[nx][ny]!='F') {
		                board[nx][ny]='F';
		                queuef.offer(new int[] {nx, ny});
		            }
		        }
		    }

		}
		
		return false;
		
	}
}

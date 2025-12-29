import java.util.*;
import java.io.*;

public class Main
{
    static char[][] board;
    static int[][] check;
    static int answer=0;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new char[5][5];
		check = new int[5][5];
		
		for(int i=0; i<5; i++) {
		    String str = br.readLine();
		    for(int j=0; j<5; j++) {
		        board[i][j] = str.charAt(j);
		    }
		}

		comb(0,0,0);
		
		System.out.println(answer);

	}
	
	static void comb(int L, int s, int count) {
	    if(7-L+count<4) return;
	    if(L==7){
	        if(count>=4 && connect()) {
	            answer++;
	        }
	        return;
	    }
	    
	    for(int i=s; i<25; i++) {
	        check[i/5][i%5]=1;
	        if(board[i/5][i%5]=='S') comb(L+1, i+1, count+1);
	        else comb(L+1, i+1, count);
	        check[i/5][i%5]=0;
	    }
	}
	
	static boolean connect() {
	    Queue<int[]> queue = new LinkedList<>();
	    boolean[][] visited = new boolean[5][5];
	    int sum=0;

	    
	    for(int i=0; i<5; i++) {
	        for(int j=0; j<5; j++) {
	            if(check[i][j]==1) {
	                queue.offer(new int[] {i,j});
	                visited[i][j]=true;
	                sum++;
	                
	                while(!queue.isEmpty()) {
	                    int[] q = queue.poll();
	                    for(int w=0; w<4; w++) {
	                        int nx = q[0]+dx[w];
	                        int ny = q[1]+dy[w];
	                        if(nx>=0 && nx<5 && ny>=0 && ny<5 && check[nx][ny]==1 && visited[nx][ny]==false){
	                            sum++;
	                            visited[nx][ny]=true;
	                            queue.offer(new int[] {nx, ny});
	                        }
	                    }
	                }
	                
	                if(sum == 7) return true;
	                else return false;
	            }
	            
	        }
	    }
	    
	    return true;
	}
}

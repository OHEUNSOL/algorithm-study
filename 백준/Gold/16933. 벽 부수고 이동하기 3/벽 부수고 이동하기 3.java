import java.util.*;
import java.io.*;
class Person {
    int x;
    int y;
    int k;
    
    
    Person(int x, int y, int k) {
        this.x=x;
        this.y=y;
        this.k=k;
    }
}

public class Main
{
	static int N;
	static int M;
	static int K;
	static int[][] board;
	static int[][][][] check;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		check = new int[N][M][K+1][2];
		
		for(int i=0; i<N; i++) {
		    String str = br.readLine();
		    for(int j=0; j<M; j++) {
		        board[i][j]=str.charAt(j)-'0';
		    }
		}
		
		
		System.out.print(BFS());
	}
	
	static int BFS() {
	    if (N == 1 && M == 1) {
	        return 1;
	    }
	    
	    Queue<Person> queue = new LinkedList<>();
	    
	    queue.offer(new Person(0,0,K));
	    
	    check[0][0][K][1]=1;
	    
	    int answer=1;
	    int day = 1;
	    
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        if(day==0) day=1;
	        else day=0;
	        answer++;
	        
	        for(int j=0; j<size; j++) {
	            Person cur = queue.poll();
	            
	            if (check[cur.x][cur.y][cur.k][day]==0) {
                    check[cur.x][cur.y][cur.k][day]=1;
                    queue.offer(new Person(cur.x, cur.y, cur.k));
    	        }
	        
    	        for(int i=0; i<4; i++) {
    	            int nx = cur.x+dx[i];
    	            int ny = cur.y+dy[i];
    	            
    	            if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
    	            
    	            if(nx==N-1 && ny==M-1) {
    	                return answer;
    	            }
    	            
    	            
    	            // 벽이면
    	            if(board[nx][ny]==1) {
    	                if(cur.k>0) {
    	                    // 도착한 시점 밤이면 바로 벽 부시기
    	                    if(day==0 && check[nx][ny][cur.k-1][day]==0) {
    	                        check[nx][ny][cur.k-1][day]=1;
    	                        queue.offer(new Person(nx, ny, cur.k-1));
    	                    }
    	                }
    	            }
    	            
    	            // 벽이 아니면 그냥 가기
    	            else {
    	                if(check[nx][ny][cur.k][day]==0) {
    	                    check[nx][ny][cur.k][day]=1;
    	                    queue.offer(new Person(nx, ny, cur.k));
    	                }
    	            }
    	        }
	        }
	        
	    }
	    
	    return -1;
	}
}

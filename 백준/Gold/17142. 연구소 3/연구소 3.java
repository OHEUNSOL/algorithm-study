import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    static int[][] board;
    static ArrayList<int[]> virus;
    static int[] combi;
    static int time;
    static int[] dx={0,0,1,-1};
    static int[] dy={1,-1,0,0};
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		virus = new ArrayList<>();
		combi=new int[M];
		time= Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<N; j++) {
		        board[i][j] = Integer.parseInt(st.nextToken());
		        if(board[i][j]==2) {
		            board[i][j]=-3;
		            virus.add(new int[] {i, j});
		        }
		        if(board[i][j]==1) {
		            board[i][j]=-1;
		        }
		    }
		}
		
		
		combination(0, 0);
		
		if(time==Integer.MAX_VALUE) System.out.print(-1);
		else System.out.print(time);
	}
	
	static void combination(int s, int L) {
	    if(L==M) {
	        time = Math.min(time, play());
	    }
	    
	    else {
	        for(int i=s; i<virus.size(); i++) {
	            combi[L]=i;
	            combination(i+1, L+1);
	        }
	    }
	}
	
	static int play() {
	    int[][] copyBoard = new int[N][N];
	    
	    for(int i=0; i<N; i++) {
	        copyBoard[i] = board[i].clone();
	    }
	    
	    Queue<int[]> queue = new LinkedList<>();
	    
	    for(int c : combi) {
	        queue.offer(virus.get(c));
	        copyBoard[virus.get(c)[0]][virus.get(c)[1]]=-2;
	    }
	    
	    int count=0;
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        count++;
	        for(int i=0; i<size; i++) {
	            int[] q = queue.poll();
	            int x = q[0];
	            int y = q[1];
	            for(int j=0; j<4; j++) {
	                int nx = x+dx[j];
	                int ny = y+dy[j];
	                if(nx>=0 && nx<N && ny>=0 && ny<N) {
	                    if(copyBoard[nx][ny]==0) {
	                        copyBoard[nx][ny]=count;
	                        queue.offer(new int[] {nx,ny});
	                    }
	                    if(copyBoard[nx][ny]==-3) {
	                        copyBoard[nx][ny]=-2;
	                        queue.offer(new int[] {nx,ny});
	                    }
	                }
	            }
	        }
	    }
	    
	    int maxNum=0;
	    
	    for(int i=0; i<N; i++) {
	        for(int j=0; j<N; j++) {
	            if(copyBoard[i][j]==0) return Integer.MAX_VALUE;
	            maxNum=Math.max(maxNum, copyBoard[i][j]);
	        }
	    }
	    return maxNum;

	}


}

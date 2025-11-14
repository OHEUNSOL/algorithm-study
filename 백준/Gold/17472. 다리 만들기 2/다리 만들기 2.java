import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int vex;
    int cost;
    
    Edge(int vex, int cost) {
        this.vex=vex;
        this.cost=cost;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.cost-e.cost;
    }
}

public class Main
{
    static int N;
    static int M;
    static int[][] board;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] dist;
    static int n;
    static ArrayList<ArrayList<Edge>> list = new ArrayList<>();
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for(int i=0; i<N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<M; j++) {
		        board[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		//섬 라벨링(2부터)
		int num = 1;
		for(int i=0; i<N; i++) {
		    for(int j=0; j<M; j++) {
		        if(board[i][j]==1) {
		            num++;
		            label(i,j, num);
		        }
		    }
		}
		
		//섬 개수
		n = num-1;
		
		dist = new int[n+2][n+2];
		for(int i=0; i<n+2; i++) {
		    Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		for(int i=0; i<n+2; i++) {
		    list.add(new ArrayList<>());
		}
		
		//다리 측정
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 1) {              // 0=바다, 1=미사용, 2이상=섬
                    calc(i, j, board[i][j]);
                }
            }
        }
		
		//간선 배열 생성
		for (int i = 2; i <= n+1; i++) {
            for (int j = 2; j <= n+1; j++) {
                if (i == j) continue;
                if (dist[i][j] == Integer.MAX_VALUE) continue;
                list.get(i).add(new Edge(j, dist[i][j]));
                // 양방향: list.get(j).add(new Edge(i, dist[i][j]));
            }
        }
		
		//prim
		int[] visited = new int[n+2];
		int sum = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		visited[2]=1;
		for(Edge e : list.get(2)) {
		    pq.offer(e);
		}
		
		while(!pq.isEmpty()) {
		    Edge e = pq.poll();
		    
		    if(visited[e.vex]==1) continue;
		    
		    visited[e.vex]=1;
		    sum+=e.cost;
		    for(Edge ne : list.get(e.vex)) {
		        pq.offer(ne);
		    }
		}
		
		for (int i = 2; i <= n+1; i++) {
            if (visited[i] == 0) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum);

	}
	
	static void calc(int x, int y, int start) {
	    Queue<int[]> queue = new LinkedList<>();
	    
	    int[][] visited = new int[N][M];
	    
	    queue.offer(new int[] {x,y});
	    
	    while(!queue.isEmpty()) {
	        int[] q = queue.poll();
	        
	        for(int i=0; i<4; i++) {
    	        int nx = q[0]+dx[i];
    	        int ny = q[1]+dy[i];
    	        
    	        if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
    	        
    	        if(visited[nx][ny]==0 && board[nx][ny]==start) {
    	            visited[nx][ny]=1;
    	            queue.offer(new int[] {nx, ny});
    	        }
    	        
    	        if(board[nx][ny]==0) {
    	            int count=0;
    	            while(true) {
    	                count++;
    	                int nnx = nx+dx[i]*count;
    	                int nny = ny+dy[i]*count;
    	                if(nnx<0 || nnx>=N || nny<0 || nny>=M || board[nnx][nny]==start) break;
    	                
    	                //다른 섬 만나면
    	                if(board[nnx][nny]!=0) {
    	                    int next = board[nnx][nny];
    	                    // 체크
    	                    if(dist[start][next]>count && count>=2) {
    	                        dist[start][next]=count;
    	                    }
    	                    
    	                    break;
    	                }
    	            }
    	        }
	        }
	    }
	}
	
	
	static void label(int x, int y, int num) {
	    Queue<int[]> queue = new LinkedList<>();
	    
	    board[x][y]=num;
	    
	    queue.offer(new int[] {x,y});
	    
	    while(!queue.isEmpty()) {
	        int[] q = queue.poll();
	        
	        for(int i=0; i<4; i++) {
    	        int nx = q[0]+dx[i];
    	        int ny = q[1]+dy[i];
    	        if(nx>=0 && nx<N && ny>=0 && ny<M && board[nx][ny]==1) {
    	            board[nx][ny]=num;
    	            queue.offer(new int[] {nx, ny});
    	        }
	        }
	    }
	   
	}
}

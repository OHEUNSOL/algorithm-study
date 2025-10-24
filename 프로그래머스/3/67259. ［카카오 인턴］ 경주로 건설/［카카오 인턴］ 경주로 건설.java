import java.util.*;

class Edge implements Comparable<Edge>{
    int x;
    int y;
    int d;
    int cost;
    
    Edge(int x, int y, int d, int cost) {
        this.x=x;
        this.y=y;
        this.d=d;
        this.cost=cost;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.cost-e.cost;
    }
}

class Solution {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        
        int N = board.length;
        
        int[][][] dist = new int[N][N][4];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for(int i=0; i<4; i++) {
            int nx = dx[i];
            int ny = dy[i];
            
            if(nx<0 || nx>=N || ny<0 || ny>=N|| board[nx][ny]==1) continue;
            
            dist[nx][ny][i]=100;
            pq.offer(new Edge(nx, ny, i, 100));
        }
        
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            
            if(dist[e.x][e.y][e.d]<e.cost) continue;
            
            for(int i=0; i<4; i++) {
                int nx = e.x+dx[i];
                int ny = e.y+dy[i];
                
                if(nx<0 || nx>=N || ny<0 || ny>=N || board[nx][ny]==1) continue;
                
                int value = (e.d==i)?100:600;
                
                if(dist[nx][ny][i]>e.cost+value) {
                    dist[nx][ny][i]=e.cost+value;
                    pq.offer(new Edge(nx, ny, i, e.cost+value));
                }
            }
            
            for(int i=0; i<4; i++) {
                answer = Math.min(answer, dist[N-1][N-1][i]);
            }
        }
        
        return answer;
    }
}
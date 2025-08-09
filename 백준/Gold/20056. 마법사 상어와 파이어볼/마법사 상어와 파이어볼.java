import java.util.*;
import java.io.*;

class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;
    
    Fireball(int r, int c, int m, int s, int d) {
        this.r=r;
        this.c=c;
        this.m=m;
        this.s=s;
        this.d=d;
    }
}

public class Main
{
    static int N;
    static int M;
    static int K;
    static List<Fireball>[][] map;
    static List<Fireball> list;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    
	public static void main(String[] args) throws IOException{
	    int answer=0;
	    
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		list = new ArrayList<Fireball>();
		
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		        map[i][j]=new ArrayList<Fireball>();
		    }
		}
		
		for(int i=0; i<M; i++) {
		    st = new StringTokenizer(br.readLine());
		    
		    int r = Integer.parseInt(st.nextToken())-1;
		    int c = Integer.parseInt(st.nextToken())-1;
		    int m = Integer.parseInt(st.nextToken());
		    int s = Integer.parseInt(st.nextToken());
		    int d = Integer.parseInt(st.nextToken());
		    
		    list.add(new Fireball(r,c,m,s,d));
		    
		}
		
		for(int k=0; k<K; k++) {
		    for(Fireball fireball : list) {
		    fireball.r = (fireball.r + fireball.s * dr[fireball.d])%N;
		    fireball.c = (fireball.c + fireball.s * dc[fireball.d])%N;
		    
		    if(fireball.r<0) fireball.r+=N;
		    if(fireball.c<0) fireball.c+=N;
		    
		    map[fireball.r][fireball.c].add(fireball);
		    }
		
        	for(int i=0; i<N; i++) {
        	    for(int j=0; j<N; j++) {
        	        if(map[i][j].size()<2) {
        	            map[i][j].clear();
        	            continue;
        	        }
        	        
        	        int sm=0;
        	        int ss=0;
        	        int r=0;
        	        int c=0;
        	        boolean odd=true;
        	        boolean even=true;
        	        int len = map[i][j].size();
        	        
        	        for(Fireball fireball : map[i][j]) {
        	            sm += fireball.m;
        	            ss += fireball.s;
        	            
        	            r=fireball.r;
        	            c=fireball.c;
        	            if(fireball.d%2==0) odd=false;
        	            if(fireball.d%2==1) even=false;
        	            
        	            list.remove(fireball);
        	        }
        	        
        	        sm = sm/5;
        	        ss = ss/len;
        	        
        	        map[i][j].clear();
        	        
        	        if(sm==0) continue;
        	        
        	        int x=0;
        	        
        	        if(odd==false && even==false) x=1;
        	        
        	        list.add(new Fireball(r, c, sm, ss, 0+x));
        	        list.add(new Fireball(r, c, sm, ss, 2+x));
        	        list.add(new Fireball(r, c, sm, ss, 4+x));
        	        list.add(new Fireball(r, c, sm, ss, 6+x));
        	       
        	    }
        	}
		}
		
		for(Fireball fireball : list) {
		    answer+=fireball.m;
		}
		
		System.out.print(answer);
		
	}
}
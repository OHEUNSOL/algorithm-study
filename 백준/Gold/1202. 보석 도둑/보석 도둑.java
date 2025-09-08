import java.util.*;
import java.io.*;

class Jewel implements Comparable<Jewel> {
    int weight;
    int price;
    
    Jewel(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
    
    //가격 높고, 무게 낮은 순으로
    @Override
    public int compareTo(Jewel j) {
        return this.weight-j.weight;
    }
}

public class Main
{
    static int N;
    static int K;
    static int C;
    static Jewel[] jewels;
    static int[] bags;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		jewels = new Jewel[N];
		
		bags = new int[K];

		for(int i=0; i<N; i++) {
		    st = new StringTokenizer(br.readLine());
		    jewels[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<K; i++) {
		    bags[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(jewels);
		Arrays.sort(bags);
		
		int idx=0;
		
		long total=0;
		
		for(int bag : bags) {
		    while(idx<N && jewels[idx].weight <= bag) {
		        pq.add(jewels[idx].price);
		        idx++;
		    }
		    
		    if(!pq.isEmpty()) {
		        total += pq.poll();
		    }

		}
		
		System.out.println(total);

		
	}
}

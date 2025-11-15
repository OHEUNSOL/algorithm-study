import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] board;
    static int[][] check;    // 대각선 공격 카운트
    static int[] best = new int[2]; // best[0] = 색 0, best[1] = 색 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        check = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 0);
        dfs(0, 1, 0, 1);

        System.out.println(best[0] + best[1]);
    }

    // check[x][y]에 +v / -v 해서 공격 카운트 관리
    static void update(int x, int y, int v) {
        check[x][y] += v;

        int num = 1;
        for (int i = 0; i < N; i++) {
            int lux = x - num;
            int luy = y - num;
            if (lux >= 0 && lux < N && luy >= 0 && luy < N) {
                check[lux][luy] += v;
            }

            int rdx = x + num;
            int rdy = y + num;
            if (rdx >= 0 && rdx < N && rdy >= 0 && rdy < N) {
                check[rdx][rdy] += v;
            }

            int ldx = x + num;
            int ldy = y - num;
            if (ldx >= 0 && ldx < N && ldy >= 0 && ldy < N) {
                check[ldx][ldy] += v;
            }

            int rux = x - num;
            int ruy = y + num;
            if (rux >= 0 && rux < N && ruy >= 0 && ruy < N) {
                check[rux][ruy] += v;
            }

            num++;
        }
    }

    // color: 0 또는 1 ( (x + y) % 2 )
    static void dfs(int x, int y, int count, int color) {
        // 열 끝나면 다음 행으로 이동, 그 행에서 해당 색의 시작 y를 잡아줌
        if (y >= N) {
            x += 1;
            if (x >= N) {
                best[color] = Math.max(best[color], count);
                return;
            }
            int ny = ((x + color) % 2 == 0 ? 0 : 1);
            dfs(x, ny, count, color);
            return;
        }

        // (x, y)가 현재 color 색인지 보장됨 (시작 y와 y+2 이동으로 맞춰줌)

        // 1) (x, y)에 비숍을 놓는 경우
        if (board[x][y] == 1 && check[x][y] == 0) {
            update(x, y, 1);
            dfs(x, y + 2, count + 1, color); // 같은 색 칸만 보니까 y+2
            update(x, y, -1);
        }

        // 2) (x, y)에 비숍을 놓지 않는 경우
        dfs(x, y + 2, count, color);
    }
}
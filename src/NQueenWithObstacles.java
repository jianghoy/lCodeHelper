import java.util.*;

public class NQueenWithObstacles {

    public List<List<Coordinate>> mostQueenWithObstacles(int n, List<Coordinate> obstacles){
        return mostQueenWithObstacles(n,obstacles,0);
    }

    public List<List<Coordinate>> mostQueenWithObstacles(int n, List<Coordinate> obstacles,int minNumOfQueeens){
        int line = 0;
        int start = 0;
        List<VirtualLine> virtualLines = new ArrayList<>();
        int[][] board = new int[n][n];
        for (Coordinate obstacle: obstacles){
            while (line < obstacle.x) {
                virtualLines.add(new VirtualLine(line,start,n-1));
                line++;
                start = 0;
            }
            if (obstacle.y-1 >= start){
                virtualLines.add(new VirtualLine(line,start,obstacle.y-1));
            }
            start = obstacle.y+1;
            board[obstacle.x][obstacle.y] = -1;
        }
        PriorityQueue<List<Coordinate>> pq = new PriorityQueue<>(new Comparator<List<Coordinate>>() {
            @Override
            public int compare(List<Coordinate> o1, List<Coordinate> o2) {
                return o1.size()-o2.size();
            }
        });
        dfs(virtualLines,new ArrayList<>(),board,0,pq);
        List<List<Coordinate>> solutions = new ArrayList<>();
        while (pq.size() > 0) {
            List<Coordinate> solution = pq.remove();
            if (solution.size() < minNumOfQueeens) {
                break;
            }
            solutions.add(solution);
        }
        return solutions;

    }

    private void dfs(List<VirtualLine> virtualLines,List<Coordinate> config,int[][] board,int level ,PriorityQueue<List<Coordinate>> pq){
        if (level == virtualLines.size()) {
            List<Coordinate> solution = new ArrayList<>();
            for (Coordinate coordinate: config){
                solution.add(coordinate);
            }
            pq.add(solution);
            return;
        }

        dfs(virtualLines,config,board,level+1,pq);
        int start = virtualLines.get(level).s;
        int end = virtualLines.get(level).e;
        for (int y = start; y <= end; y++){
            Coordinate newQueen = new Coordinate(virtualLines.get(level).x,y);
            if (isValid(board,newQueen)){
                board[newQueen.x][newQueen.y] = 1;
                config.add(newQueen);
                dfs(virtualLines,config,board,level+1,pq);
                board[newQueen.x][newQueen.y] = 1;
                config.remove(config.size()-1);
            }
        }
    }

    private boolean isValid(int[][] board,Coordinate newQueen){
        int x = newQueen.x-1;
        int y = newQueen.y;
        while (x>=0) {
            if (board[x][y] == 1) {
                return  true;
            }
            if (board[x][y] == -1){
                break;
            }
            x--;
        }
        x = newQueen.x-1;
        y = newQueen.y-1;
        while (x >=0 && y >= 0 ){
            if (board[x][y] == 1) {
                return false;
            }
            if (board[x][y] == -1) {
                break;
            }
            x--;
            y--;
        }
        x = newQueen.x-1;
        y = newQueen.y+1;
        while (x >=0 && y < board.length ){
            if (board[x][y] == 1) {
                return false;
            }
            if (board[x][y] == -1) {
                break;
            }
            x--;
            y++;
        }
        return true;
    }

    class VirtualLine {
        int x;
        int s;
        int e;
        public VirtualLine(int x,int s,int e){
            this.x = x;
            this.s = s;
            this.e = e;
        }
    }
}

class Coordinate{
    int x;
    int y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
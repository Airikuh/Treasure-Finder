package treasurefinder;

import java.util.LinkedList;
import java.util.Queue;


public class TreasureFinder {

public static int FindTreasure1(char[][] map){
                if((map == null) || (map.length == 0)){
            return 0;
        }
            int x = map.length;
            int y = map[0].length;
            boolean[][] visited = new boolean[x][y];
            return(FindTreasure1Helper(map, 0, 0, x, y, visited));
    
}

public static int FindTreasure1Helper(char[][] map, int i, int j,int x, int y, boolean[][] visited ){
    int doors=0;    
    if((i >= 0) && (j >= 0) && (i < x) && (j < y) && (!visited[i][j]) && (map[i][j] != 'B')){
        doors++;
        if(map[i][j] == 'X'){
             System.out.println("TREASURE FOUND!!");
                    return doors;        
        }
            visited[i][j] = true;
        int up = 1 + FindTreasure1Helper(map,i - 1, j, x, y, visited);
        doors++;
        int down = 1 + FindTreasure1Helper(map,i + 1, j, x, y, visited);
                doors++;
        int right = 1 + FindTreasure1Helper(map,i, j + 1, x, y, visited);
                doors++;
        int left  = 1 + FindTreasure1Helper(map,i, j - 1, x, y, visited);
        doors++;
        }
        return doors;
    
    
    }


    public static int FindTreasure2(char[][] map){
        int doors = 0;
        Queue<Coordinate> q = new LinkedList();
        
               
        if((map == null) || (map.length == 0)){
            return 0;
        }
       
        q.add(new Coordinate(0,0));
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[0][0] = true;
        
        int[][] whichDirection = new int[][]{
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}};
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int i = 0; i < size; i++){
                Coordinate coordinate = q.poll();
                int x = coordinate.x;
                int y = coordinate.y;
                
                if(map[x][y] == 'X'){
                    System.out.println("TREASURE FOUND!!");
                    return doors;
                }
                for(int[] locate : whichDirection){
                    int updateX = x + locate[0];
                    int updateY = y + locate[1];
                    if((updateX >= 0) && (updateX < map.length) && (updateY >= 0) && (updateY < map[0].length) && (map[updateX][updateY] != 'B') && (!visited[updateX][updateY])){
                        q.add(new Coordinate(updateX, updateY));
                        visited[updateX][updateY] = true;
                                doors++;
                    }
                }
            }
        }
        return 0;
        
        
    }
  
    public static void main(String[] args) {
        char[][] map = new char[][]{
            {'O','O','B','O','O','O','O'},
            {'B','O','O','O','O','O','B'},
            {'O','O','O','B','O','O','O'},
            {'B','O','O','O','O','O','O'},
            {'O','X','O','O','O','B','O'},
            {'O','O','B','B','O','O','B'}
            
        };
        
        
                int result1 = FindTreasure1(map);
        System.out.println("USING FindTreasure2, Went Through " + result1 + " Doors");
      
        
        
        int result2 = FindTreasure2(map);
        System.out.println("USING FindTreasure2, Went Through " + result2 + " Doors");
      
             
        
        
    }
    
    
    static class Coordinate{
        int x;
        int y;
        
        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

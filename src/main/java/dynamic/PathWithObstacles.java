package dynamic;

import java.util.ArrayList;
import java.util.List;

public class PathWithObstacles {

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        List<List<Integer>> res = new ArrayList<>();
        pathWithObstacles(obstacleGrid , 0 , 0 , new ArrayList<List<Integer>>() , res);
        return res;
    }
    void pathWithObstacles(int[][] obstacleGrid , int row , int col , List<List<Integer>> path ,List<List<Integer>> res  ){
        if (!res.isEmpty()){
            return;
        }
        if(row +1 == obstacleGrid.length && col + 1 == obstacleGrid[0].length && obstacleGrid[row][col] == 0){
            List<Integer> node = new ArrayList<>();
            node.add(row);
            node.add(col);
            path.add(node);
            res.addAll(path);
            return ;
        }
        if( row == obstacleGrid.length || col == obstacleGrid[0].length || obstacleGrid[row][col] == 1){
            return;
        }else{
            List<Integer> node = new ArrayList<>();
            node.add(row);
            node.add(col);
            path.add(node);
            pathWithObstacles(obstacleGrid , row , col + 1 , path  ,res );
            pathWithObstacles(obstacleGrid , row + 1 , col  , path  ,res );
            path.remove(node);
        }
    }

    public static void main(String[] args) {
        int num[][] = {
                {0,0,0},{0,1,0},{0,0,0}
        };
        System.out.println(new PathWithObstacles().pathWithObstacles(num));
    }
}

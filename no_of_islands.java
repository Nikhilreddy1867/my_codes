import java.util.*;
class DSU{
    int[] parent;
    int[] rank;
    DSU(int n){
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=0;
        }
    }
    int findPar(int node){
        if(node==parent[node]){
            return node;
        }
        return parent[node]=findPar(parent[node]);
    }
    void unionRank(int u,int v){
        int new_u=findPar(u);
        int new_v=findPar(v);
        if(rank[new_u]>rank[new_v]){
            parent[new_v]=new_u;
        }
        else if(rank[new_u]<rank[new_v]){
            parent[new_u]=new_v;
        }
        else{
            parent[new_u]=new_v;
            rank[new_v]++;
        }
    }
}
class Solution {

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        // Your code here
        int[][] vis=new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                vis[i][j]=0;
            }
        }
        List<Integer> ans=new ArrayList<>();
        DSU ds=new DSU(rows*cols);
        int[] delRow={-1,0,1,0};
        int[] delCol={0,1,0,-1};
        int count=0;
        for(int k=0;k<operators.length;k++){
            int row=operators[k][0];
            int col=operators[k][1];
            if(vis[row][col]==1){
                ans.add(count);
                continue;
            }
            vis[row][col]=1;
            count++;
            for(int i=0;i<4;i++){
                int new_row=row+delRow[i];
                int new_col=col+delCol[i];
                int num=row*cols+col;
                int new_num=new_row*cols+new_col;
                if(new_row>=0 && new_col>=0 && new_col<cols && new_row<rows){
                    if (vis[new_row][new_col] == 1) {   // check land first
                        if (ds.findPar(num) != ds.findPar(new_num)) {
                        ds.unionRank(num, new_num);
                        count--;
                        }
                    }
                }
                
            }
            ans.add(count);
            
            
        }
        return ans;
        
    }
}
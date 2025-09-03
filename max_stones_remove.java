import java.util.*;
class DSU{
    int[] parent;
    int[] size;
    DSU(int n){
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
            size[i]=1;
        }
    }
    int findPar(int node){
        if(node==parent[node]){
            return node;
        }
        return parent[node]=findPar(parent[node]);
    }
    void unionBySize(int u,int v){
        int new_u=findPar(u);
        int new_v=findPar(v);
        if(new_v==new_u) return;
        if(size[new_u]<size[new_v]){
            parent[new_u]=new_v;
            size[new_v]+=size[new_u];
        }
        else{
            parent[new_v]=new_u;
            size[new_u]+=size[new_v];
        }
    }
}
class Solution {
    public int removeStones(int[][] stones) {
        
        int maxCol=0;
        int maxRow=0;
        int n=stones.length;
        for(int i=0;i<stones.length;i++){
            int row=stones[i][0];
            int col=stones[i][1];
            maxCol=Math.max(maxCol,col);
            maxRow=Math.max(maxRow,row);
        }
        HashSet<Integer> nik=new HashSet<>();
        DSU ds =new DSU(maxCol+maxRow+2);
        for(int i=0;i<stones.length;i++){
            int row=stones[i][0];
            int col = stones[i][1] + maxRow + 1;
            ds.unionBySize(row,col);
            nik.add(row);
            nik.add(col);
        }
        int count=0;
        for(int it:nik){
            if(ds.findPar(it)==it){
                count++;
            }
        }
        return n-count;
    }
}
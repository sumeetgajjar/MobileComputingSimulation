package MCProj;

import java.util.Arrays;

public class Graph
{
    boolean a[][];
    public static int x[];
    int m=3;
    int n=7;
    public boolean solnFound=false;
    public int countCells;
    
    public Graph()
    {
        a=new boolean[n][n];
        x=new int[n];
        a[0][1]=a[1][0]=true;
        a[0][2]=a[2][0]=true;
        a[0][3]=a[3][0]=true;
        a[0][4]=a[4][0]=true;
        a[0][5]=a[5][0]=true;
        a[0][6]=a[6][0]=true;
        a[1][2]=a[2][1]=true;
        a[1][6]=a[6][1]=true;
        a[2][3]=a[3][2]=true;
        a[3][4]=a[4][3]=true;
        a[4][5]=a[5][4]=true;
        a[5][6]=a[6][5]=true;
        
    }

    public void graphcolor(int r)
    {
        for(int i=0;i<m;i++)
        {
            x[r]=i;
            if(correctColor(r))
            {
                if(r+1<countCells)
                    graphcolor(r+1);
                else
                {
                    System.out.println("Solution is "+Arrays.toString(x));
                    solnFound=true;
                }
            }
            
            if(solnFound)
                break;
        }
    }
    boolean correctColor(int r)
    {
        for(int i=0;i<r;i++)
        {
            if(x[i]==x[r]&&a[i][r]==true)
               return false;
        }
        return true;
    }   
}

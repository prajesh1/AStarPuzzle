import edu.princeton.cs.algs4.Stack;

public final class Board  // final: Board is immutable
{  
	private int[][] blocks;
	private int N;
	private int moveNumber; //how many previous moves
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    {
    	if(blocks.length!=blocks[0].length)
    		throw new IllegalArgumentException("N*M matrix not accepted, pls try with N*N");
    	this.N=blocks.length;
    	this.moveNumber = 0; //Initialization
    	
    	this.blocks = new int[N][N];
    	for(int i=0;i<this.N;i++)   //makes blocks immutable
    		for(int j=0;j<this.N;j++) // (where blocks[i][j] = block in row i, column j)
    			this.blocks[i][j] = blocks[i][j];
    }
                                           
    public int dimension()                 // board dimension N
    {
    	return this.N;
    }
    public int hamming()                   // number of blocks out of place
    {
    	int count = this.moveNumber;
    	for(int i=0;i<this.N;i++)   
    		for(int j=0;j<this.N;j++)     		 			
    			if(this.blocks[i][j]!=(i*N)+j+1&&this.blocks[i][j]!=0) // if doesnt match increase score
    				count++;                       //dont count blank =0
    	
    	return count;    		
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	int count = this.moveNumber;       
    	for(int i=0;i<this.N;i++)   
        for(int j=0;j<this.N;j++) 
        {
          if(this.blocks[i][j]!=0)  //dont count blank =0
            
          count = count + Math.abs((this.blocks[i][j]-1)/this.N -i)
                        + Math.abs((this.blocks[i][j]-1)/this.N -j);
        }
    	return count;
    }
    public boolean isGoal()                // is this board the goal board?
    {
      for(int i=0;i<this.N;i++)   
        for(int j=0;j<this.N;j++) 
        {
          if(this.blocks[i][j]==0)
          {
            if(i!=N-1||j!=N-1)          
              return false;
          }
          else if((this.blocks[i][j]-1)%this.N!=i||(this.blocks[i][j]-1)%this.N!=j)
              return false;              
        }
      return true;      
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
          Board twinBoard = new Board(this.blocks);
          int fi,fj,si,sj;
          if(twinBoard.blocks[0][0]==0)
          {
            fi=0;fj=1;si=0;sj=2;
          }
          else if(twinBoard.blocks[0][1]==0)
          {
            fi=0;fj=0;si=0;sj=2;
          }
          else
          {
            fi=0;fj=0;si=0;sj=1;
          }         
          swap(twinBoard.blocks,si,sj,fi,fj);
          return twinBoard;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
      if(this==y)
        return true;
      if(this.getClass()!=y.getClass())
        return false;
      Board that = (Board)y;
      if(this.dimension()!=that.dimension())
        return false;
      
      for(int i=0;i<this.N;i++)   
        for(int j=0;j<this.N;j++)
          if(this.blocks[i][j]!=that.blocks[i][j])
            return false;
      
      return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
    	int posOfBlanki=-1,posOfBlankj=-1;
    	outerloop:
    	for(int i=0;i<this.N;i++)   
            for(int j=0;j<this.N;j++)
              if(this.blocks[i][j]!=0)
              {
            	  posOfBlanki=i;
            	  posOfBlankj=j;
            	  break outerloop;//exit from both loops another way to do this by using func just for two loop and returning i & j
              }
    	
    	Stack<Board> stacks = new Stack<Board>();
    	if(posOfBlanki>0)
    	{
    		Board b = new Board(this.blocks);
    		b.moveNumber = this.moveNumber +1;
    		swap(b.blocks,posOfBlanki,posOfBlankj,posOfBlanki-1,posOfBlankj);
    		stacks.push(b);
    	}
    	if(posOfBlanki<N-1)
    	{
    		Board b = new Board(this.blocks);
    		b.moveNumber = this.moveNumber +1;
    		swap(b.blocks,posOfBlanki,posOfBlankj,posOfBlanki+1,posOfBlankj);
    		stacks.push(b);
    	}
    	if(posOfBlankj>0)
    	{
    		Board b = new Board(this.blocks);
    		b.moveNumber = this.moveNumber +1;
    		swap(b.blocks,posOfBlanki,posOfBlankj,posOfBlanki,posOfBlankj-1);
    		stacks.push(b);
    	}
    	if(posOfBlanki<N-1)
    	{
    		Board b = new Board(this.blocks);
    		b.moveNumber = this.moveNumber +1;
    		swap(b.blocks,posOfBlanki,posOfBlankj,posOfBlanki,posOfBlankj+1);
    		stacks.push(b);
    	}
    	return stacks;    
    }
    private void swap(int[][]arr, int i, int j, int a, int b)
    {
    	int temp=arr[i][j];
    	arr[i][j]=arr[a][b];
    	arr[a][b]=temp;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
    	 StringBuilder s = new StringBuilder();
    	    s.append(N + "\n");
    	    for (int i = 0; i < N; i++) {
    	        for (int j = 0; j < N; j++) {
    	            s.append(String.format("%2d ", this.blocks[i][j]));
    	        }
    	        s.append("\n");
    	    }
    	    return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
      
    }
}
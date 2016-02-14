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
    			if(this.blocks[i][j]!=(i*N)+j+1) // if doesnt match increase score
    				count++;
    	if(this.blocks[N-1][N-1]==0) // to correct extra calculation done for blank in previous step
    		count--;
    	return count;    		
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	int count = this.moveNumber;       
    	for(int i=0;i<this.N;i++)   
        for(int j=0;j<this.N;j++) 
        {
          if(this.blocks[i][j]==0)
            count = count + N-1-i + N-1-j;
          else
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
          if(twinBoard.blocks[0][1]==0)
          {
            fi=0;fj=0;si=0;sj=2;
          }
          if(twinBoard.blocks[0][2]==0)
          {
            fi=0;fj=0;si=0;sj=1;
          }
          int temp = twinBoard.blocks[si][sj];
          twinBoard.blocks[si][sj]=twinBoard.blocks[fi][fj];
          twinBoard.blocks[fi][fj]=temp;
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
    
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
     re 
    }

    public static void main(String[] args) // unit tests (not graded)
    {
      
    }
}
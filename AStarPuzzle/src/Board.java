public final class Board  // final: Board is immutable
{  
	private int[][] blocks;
	private int N;
	private int moveNumber; //how many previous moves
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    {
    	if(blocks.length!=blocks[i].length)
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
    	
    }
    public boolean isGoal()                // is this board the goal board?
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y)        // does this board equal y?
    public Iterable<Board> neighbors()     // all neighboring boards
    public String toString()               // string representation of this board (in the output format specified below)

    public static void main(String[] args) // unit tests (not graded)
}
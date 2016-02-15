import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private Node finalNode;
	private boolean solvable=true;
	
	private class Node implements Comparable<Node>
	{
		Board board;
		Node previousnode;
		private int moves;
		public Node(Board board, Node previousnode, int moves) {
			this.board = board;
			this.previousnode = previousnode;
			this.moves = moves;
		}
		@Override
		public int compareTo(Node that) {
			return  this.board.hamming()+this.moves-that.board.hamming()-that.moves;
		}
		
	}
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
    	MinPQ<Node> minPQ = new MinPQ<Node>();
    	Node node = new Node(initial,null,0);
    	minPQ.insert(node);
    	//dummy code for twin
    	MinPQ<Node> minPQDummy = new MinPQ<Node>();
    	Node nodeDummy = new Node(initial.twin(),null,0);
    	minPQDummy.insert(nodeDummy);
    	//dummy code for twin ends
    	while(!minPQ.isEmpty()&&!minPQDummy.isEmpty()) //ideally the queue will never empty
    	{
    		Node searchNode = minPQ.delMin();
    		if(searchNode.board.isGoal()) //return in constructor answer reached
    			{
    				finalNode = searchNode;
    				this.solvable = true;
    				return;
    			}
    		for(Board neighbour:searchNode.board.neighbors())
    		{
    			if(neighbour==null) System.out.print("Null ne");
    			if(searchNode.previousnode==null||!neighbour.equals(searchNode.previousnode.board))
    			{
    				Node n = new Node(neighbour,searchNode,searchNode.moves+1);
    				minPQ.insert(n);
    			}
    				
    		}
    		
    		//code for twin dummy 
    		Node searchNodeDummy = minPQDummy.delMin();
    		if(searchNodeDummy.board.isGoal()) //return in constructor answer reached
    			{
    				searchNodeDummy.moves = -1; //not solvable
    				finalNode = searchNodeDummy; 
    				this.solvable = false;
    				return;
    			}
    		for(Board dneighbour:searchNodeDummy.board.neighbors())
    		{
    			if(searchNodeDummy.previousnode==null||!dneighbour.equals(searchNodeDummy.previousnode.board))
    			{
    				Node n = new Node(dneighbour,searchNodeDummy,searchNodeDummy.moves+1);
    				minPQDummy.insert(n);
    			}
    				
    		}
    		//code for twin dummy 
    		
    	}
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
    	return this.solvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
    	return finalNode.moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	if(!this.solvable)
    		return null;
    	Stack<Board> st = new Stack<Board>();
    	Node n = finalNode;    	
    	while(n!=null)
    	{
    		st.push(n.board);
    		n = n.previousnode;
    	}
    	return st;
    }
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
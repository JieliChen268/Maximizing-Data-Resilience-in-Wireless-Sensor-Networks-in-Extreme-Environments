package GraduateProject2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;


public class GsensorNetwork {
	
	private int width_x;
	private int length_y;
	private SensorNode[] Node;   // all sensor nodes list
	private int numOfNode;       // number of Nodes including DG 
	private int numOfDG;         // number of DG 
	private int numOfDataItem;   // number of data item for each DG
	private Scanner input;
	private LinkedList<SensorNode> [] adj; // adjacency list for each node
	private boolean[] visited;             // store visiting result of each node
	private Queue<SensorNode> q = new LinkedList<SensorNode>(); // queue for BFS

	
	public GsensorNetwork () {
	
		input = new Scanner (System.in);
		
		System.out.println("Enter  width x : ");
	    this.width_x = input.nextInt();
	     
	    System.out.println("Enter  length y : ");
	    this.length_y = input.nextInt();
	     
	    System.out.println(width_x + " * " + length_y + " plot is created successfully! ");
	    System.out.println(); 
	    System.out.println(); 
		
		System.out.println("Enter  number of sensor nodes N : ");
	    this.numOfNode = input.nextInt();  
	   
	    this.numOfDG = 1;	
	    
	    System.out.println("Enter number of data item for each DG q : ");
	    this.numOfDataItem = input.nextInt();
	    
	    this.Node = new SensorNode[numOfNode];
        
	    adj = new LinkedList[numOfNode];
	    
	    visited = new boolean[numOfNode];
	    
	 
	    
	    for (int i = 0; i < numOfNode; i++) {
			visited[i] = false;
		
			adj[i] = new LinkedList();
		}  
	}
	
	/*constructor for testing*/
	public GsensorNetwork (int x, int y) {
		
	    this.width_x = x;
	     
	  
	    this.length_y = y;
	     
	    System.out.println(width_x + " * " + length_y + " plot is created successfully! ");
	    System.out.println();
	    System.out.println();

	    this.numOfNode = 10;

	    this.numOfDG = 1;	

	    this.numOfDataItem = 8;

	    this.Node = new SensorNode[numOfNode]; 
        
	    adj = new LinkedList[numOfNode];
	    
	    visited = new boolean[numOfNode];
	    
	
	    
	    for (int i = 0; i < numOfNode; i++) {
			visited[i] = false;
		
			adj[i] = new LinkedList();
		}  
	}
	
    public void buildNetwork (){
    	
    	 SensorNode n = new SensorNode();
    	//SensorNode n = new SensorNode(90000000,10000000,25); //only for testing
   	    System.out.println("n transmission range : " + n.getTransmissionRange());
   	  /*create non-DG nodes*/
	    for (int i = 0; i < numOfNode - numOfDG; i++) {
	    	Node[i] = new SensorNode(n.getA(), n.getB(), n.getTransmissionRange());
	    	Node[i].setID(i);
	    	Node[i].setStorageCapacity(n.getStorageCapacity());
	    	Node[i].setInitialEnergy(n.getA(), n.getB());
	    	Node[i].setxPosition(width_x);
	    	Node[i].setxPosition(length_y);
	    }
	    
	    /*print out Non - DG Nodes*/
	    for (int i = 0; i < numOfNode - numOfDG; i++) {
	    	 System.out.println("Node " + i + " transmission range : " + Node[i].getTransmissionRange());
	    	 System.out.println("Node(non-DG) " + Node[i].getID() + " is created successfully! ");
	    	 System.out.println();
	    	 System.out.println();
	    }
	    
	    
	    /*create DG nodes*/
	   
	    
	    for (int i = numOfNode - numOfDG; i < numOfNode; i ++) {
	    	Node[i] = new SensorNode(n.getA(), n.getB(), n.getTransmissionRange());
	    	Node[i].setID(i);
	    	Node[i].setDG(true);
	    	Node[i].setInitialEnergy(n.getA(), n.getB());
	    	Node[i].setNumOfDataItem(numOfDataItem);
	    	Node[i].setxPosition(width_x);
	    	Node[i].setxPosition(length_y);
	    }
	    
	    /*print out DG Nodes*/
	    for (int i = numOfNode - numOfDG; i < numOfNode; i ++) {
	    	 System.out.println("Node " + i + " transmission range : " + Node[i].getTransmissionRange());
	    	 System.out.println("Node (DG) " + Node[i].getID() + " is created successfully! ");
	    	 System.out.println();
	    	 System.out.println();
	    }	
	    
    	
    }

		  
	public void creatingAdjacencyList() {

		  /*create adjacency list for each sensor node*/
		        System.out.println("---------create adjacency list for each sensor node---------- ");
				for (int i = 0; i < numOfNode; i++) {
					
					System.out.println("Node " + i + " adjacency list : ");
					//System.out.println("Node " + i + " Transmission Range: " + Node[i].getTransmissionRange());
					for (int j = 0; j < numOfNode; j++) {
						Edge e = new Edge(Node[i], Node[j]);
						
						//System.out.print("edge " + i + j + " Length :" + e.getLength() + "\t");
						//System.out.println("edge " + i + j + " isExist :" + e.isExist());
						if (e.isExist()) {
							
							if (i != j) {
								
								adj[i].add(Node[j]); /* add node to adjacency list */
								System.out.print( j + " -> ");
							}
						}
					}
					System.out.println();
					System.out.println();
				}
				
			   for (int i = 0; i < adj.length; i++) {
				   System.out.print("adj[" + i +"] : ");
				   for (int j = 0; j < adj[i].size(); j++) {
					   System.out.print(adj[i].get(j).getID() + " ");  
				   }
				   System.out.println(); 
			   }
			   
			   System.out.println(); 
			   System.out.println(); 
	}
		  
		  
	
	public boolean isConnected () {

		    	/* BFS traverse from Node[0] */

		    	/*add the first Node in the queue, and mark it as visited */
		    	q.add(Node[0]);
		    	visited[0] = true;
		    
		    	
		        int index= 0; SensorNode s; 
		    	while ( !q.isEmpty()) { 
		    	    /*Dequeue a vertex from queue and print it*/
		    		s = q.remove();
		//    	    System.out.println("pop Node id : " + s.getID() + " "); 
		    	    
		    	    for (int i = 0; i < numOfNode; i++) {
		    	    	if (s.equals(Node[i])) {
		    	    		index = i;
		    	    	}
		    	    }
		//    		System.out.print("index : " + index + " (");
		    		
	/*	    		for (int i = 0; i < adj[index].size(); i++) {
		    			System.out.print(adj[index].get(i).getID() + " ");
		    		}
		    		System.out.print(")");
		    		System.out.println();
	*/	    		
		    	    /* Get all adjacent vertices of the dequeued vertex s
		             If a adjacent has not been visited, then mark it
		             visited and enqueue it  */
		    	    ListIterator<SensorNode> it = adj[index].listIterator();
		    	    
		    	    while (it.hasNext())  {
		                SensorNode n = it.next();
	//	                System.out.println("it.next() id :" + n.getID());
		                if (!visited[n.getID()])
		                {
		                    visited[n.getID()] = true;
		                    q.add(n);
		                    
		                }
		            }
		    	   System.out.println(); 
		    	}
		    	
                for (int i = 0; i < numOfNode; i++) {
    //            	System.out.println("visited[" + i +"] = " + visited[i]);
                	
                	if (visited[i] == false) {
                		System.out.println("This sensor network is not connected!");
                		System.out.println();
                		return false;
                		
                	}	
		    	}
                
                System.out.println("This sensor network is connected!");
                System.out.println();  
                return true;
     }
	
	
	/* Output 3: If connected, ask for input of two nodes u and v, output the energy
	 *  consumption of sending a data from u to v using shortest path between them.
     */

    /*Dijkstra Shortest Path algorithm to get shortest path from source to target*/
	
	public List<SensorNode> computeshortestPaths() {
		
		/*user input source and target Node*/
		input = new Scanner (System.in);
		System.out.println("Input the source Node id: ");
		int s = input.nextInt();
		//System.out.println("Node[" + s + "]" + "id :" + Node[s].getID());
		SensorNode source = Node[s];
	

		System.out.println("Input the target Node id: ");
		int t = input.nextInt();
		SensorNode target = Node[t];
		
        source.minDistance = 0;
        
        PriorityQueue<SensorNode> vertexQueue = new PriorityQueue<SensorNode>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
        	
            SensorNode u = vertexQueue.poll();
            int index = -1 ;
            /*get index of u in Node[] list*/
            for (int i = 0; i < numOfNode; i++) {
            	if (Node[i].equals(u)) {
            		index = i;
            	}
            }

            // Visit each edge exiting u
            for (int i = 0; i < adj[index].size(); i++) {
            	
                SensorNode v = adj[index].get(i);
 //               System.out.println("v id : " + v.getID());
                
                Edge e = new Edge (Node[index],v);
 //               System.out.println("e length : " + e.getLength());
                
                int weight = e.getLength();
                int distanceThroughU = (int) (u.minDistance + weight);
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
 
        List<SensorNode> path = new ArrayList<SensorNode>();
        for (SensorNode v = target; v != null; v = v.previous)
            path.add(v);

        Collections.reverse(path);
        return path; 
      }
	
	
	public double computingEnergyConsumption() {
		    List<SensorNode> path =  computeshortestPaths();
	/*	    System.out.print("Shortest Path is : ");
		    for (int i = 0; i < path.size(); i++) {
		    	System.out.print( path.get(i).getID() + "->");
		    } */
		    System.out.println();
		    
		    double engeryConsumption = 0;
		    double[] Et = new double[path.size() - 1];
		    Edge[] e = new Edge[path.size() - 1];
		    
		    for (int i = 0; i < Et.length; i++) {
		    	e[i] = new Edge(path.get(i),path.get(i + 1));
	//	    	System.out.println("e[i] length: " + e[i].getLength());
		    	Et[i] =  (2 * 100 * 400 
		    			+ 100 * 0.001 * 400 * e[i].getLength() * e[i].getLength());
		    	engeryConsumption += Et[i];
		    }

		return engeryConsumption;
	}
	
	
	
	public double computingEnergyConsumption(SensorNode source, SensorNode target) {

        source.minDistance = 0;
        
        PriorityQueue<SensorNode> vertexQueue = new PriorityQueue<SensorNode>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
        	
            SensorNode u = vertexQueue.poll();
            int index = -1 ;
            /*get index of u in Node[] list*/
            for (int i = 0; i < numOfNode; i++) {
            	if (Node[i].equals(u)) {
            		index = i;
            	}
            }

            // Visit each edge exiting u
            for (int i = 0; i < adj[index].size(); i++) {
            	
                SensorNode v = adj[index].get(i);
//                System.out.println("v id : " + v.getID());
                
                Edge e = new Edge (Node[index],v);
 //               System.out.println("e length : " + e.getLength());
                
                int weight = e.getLength();
                int distanceThroughU = (int) (u.minDistance + weight);
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
 
        List<SensorNode> path = new ArrayList<SensorNode>();
        for (SensorNode v = target; v != null; v = v.previous)
            path.add(v);

        Collections.reverse(path);
     
        
   
	    
	    double engeryConsumption = 0;
	    double[] Et = new double[path.size() - 1];
	    Edge[] e = new Edge[path.size() - 1];
	    
	    for (int i = 0; i < Et.length; i++) {
	    	e[i] = new Edge(path.get(i),path.get(i + 1));
	//    	System.out.println("e[i] length: " + e[i].getLength());
	    	Et[i] =  (2 * 100 * 400 
	    			+ 100 * 0.001 * 400 * e[i].getLength() * e[i].getLength());
	    	engeryConsumption += Et[i];
	    }

	    return engeryConsumption;
	
	}
        
        
        

	public int getNumOfNode() {
		return numOfNode;
	}

	public void setNumOfNode(int numOfNode) {
		this.numOfNode = numOfNode;
	}

	public int getNumOfDG() {
		return numOfDG;
	}

	public void setNumOfDG(int numOfDG) {
		this.numOfDG = numOfDG;
	}

	public int getNumOfDataItem() {
		return numOfDataItem;
	}

	public void setNumOfDataItem(int numOfDataItem) {
		this.numOfDataItem = numOfDataItem;
	}

	public SensorNode[] getNode() {
		return Node;
	}

	public void setNode(SensorNode[] node) {
		this.Node = node;
	}
	
	
	
    public void Network_Based_Algorithm () {
    	
	
    	/* put DG in the last position of Node list*/
    	SensorNode temp;
    	SensorNode DG = null;
    	for (int i = 0; i < Node.length; i++) {
   		     if (Node[i].isDG()) {
   		    	 System.out.println("Node[" + Node[i].getID() + "] is DG.");
   		    	 DG = Node[i];
   		    	 temp = Node[i];
   		    	 Node[i] = Node[Node.length - 1];
   		    	 Node[Node.length - 1] = temp;
   		     }
   	     }
    	
    	System.out.println();

    	SensorNode[] nonDGNode = new SensorNode[Node.length - 1]; 
    	
    	for (int i = 0; i < nonDGNode.length; i++) {
    		nonDGNode[i] = Node[i];
    	}
    	
    
    	
    	sorting_By_InitialEnergy(nonDGNode);
    	
    	int k = 0; 
    	int sum = 0;
    	
    	for (int i = 0; i < nonDGNode.length; i++) {
    		 sum += nonDGNode[i].getStorageCapacity();
    		 if (numOfDataItem > sum) {
    			 k++;
    		 } else {
    			 
    			 break;
    		 }
    	}
    	System.out.println(" K : " + k);
    	
    	double[] Eremain = new double[nonDGNode.length];
    	int[] storedDataItem = new int[nonDGNode.length];
    	int dataItemRemain = numOfDataItem;
    	double totalenergyConsumption = 0;
    	for (int i = 0; i < k; i++) {
    		Eremain[i] = nonDGNode[i].getInitialEnergy() - 
    				nonDGNode[i].getStorageCapacity() * computingEnergyConsumption(DG,nonDGNode[i]);
    		dataItemRemain -= nonDGNode[i].getStorageCapacity();
    		
    		totalenergyConsumption += computingEnergyConsumption(DG,nonDGNode[i]);
    		
    		storedDataItem[i] = nonDGNode[i].getStorageCapacity();
    	}
    	Eremain[k] = dataItemRemain * computingEnergyConsumption(DG,nonDGNode[k]);
    	totalenergyConsumption += computingEnergyConsumption(DG,nonDGNode[k]);
    	storedDataItem[k] = dataItemRemain;
    	
    	System.out.println("The total energy consumption for Network_Based_Algorithm is: " 
    	+ totalenergyConsumption + "nJ");
    	System.out.println();
    	
    	double EResilienceLevel = 0;
    	for (int i = 0; i < nonDGNode.length; i++) {
    		EResilienceLevel += Eremain[i] * storedDataItem[i];
    	}
    	System.out.println("The total Resilience level for Network_Based_Algorithm is : " + EResilienceLevel);
    	System.out.println();
    }
    
    
    
    
    public void Node_Based_Algorithm () {
    	
    	/* put DG in the last position of Node list*/
    	SensorNode temp;
    	SensorNode DG = null;
    	for (int i = 0; i < Node.length; i++) {
   		     if (Node[i].isDG()) {
   		    	 System.out.println("Node[" + Node[i].getID() + "] is DG.");
   		    	 DG = Node[i];
   		    	 temp = Node[i];
   		    	 Node[i] = Node[Node.length - 1];
   		    	 Node[Node.length - 1] = temp;
   		     }
   	     }
    	
    	System.out.println();

    	SensorNode[] nonDGNode = new SensorNode[Node.length - 1]; 
    	
    	for (int i = 0; i < nonDGNode.length; i++) {
    		nonDGNode[i] = Node[i];
    	}

    	double[] Eremain = new double[nonDGNode.length];
    	
    	int[] storedDataItem = new int[nonDGNode.length];
    	
    	int dataItemRemain = numOfDataItem;
    	
    	double totalenergyConsumption = 0;
    	int k = 0; 
    	int sum = 0;
    	double EResilienceLevel = 0;
    	
    	sorting_By_InitialEnergy(nonDGNode);
    	
    	while (dataItemRemain > 0) {
    		
    		
    		/* get k */
    		for (int i = 0; i < nonDGNode.length; i++) {
       		     sum += nonDGNode[i].getStorageCapacity();
       		     if (numOfDataItem > sum) {
       			       k++;
       		     } else {
       			 
       			 break;
       		     }
       	    }
    		
    		 while (k > 0 ) {
    			 
        		Eremain[0] = nonDGNode[0].getInitialEnergy() - 
        				nonDGNode[0].getStorageCapacity() * computingEnergyConsumption(DG,nonDGNode[0]);
        		dataItemRemain -= nonDGNode[0].getStorageCapacity();
        		
        		totalenergyConsumption += computingEnergyConsumption(DG,nonDGNode[0]);
        		
        		storedDataItem[0] = nonDGNode[0].getStorageCapacity()
        				;
        		EResilienceLevel += Eremain[0] * storedDataItem[0];
        		
        		nonDGNode[0].setInitialEnergy((int) Eremain[0]);
        		
        		sorting_By_InitialEnergy(nonDGNode);
        		
        		k--;
        	}	
    		
    	}
    
    	
    	Eremain[0] = dataItemRemain * computingEnergyConsumption(DG,nonDGNode[0]);
    	
    	totalenergyConsumption += computingEnergyConsumption(DG,nonDGNode[0]);
    	
    	//storedDataItem[0] = dataItemRemain;
    	
    	System.out.println("The total energy consumption for Node_Based_Algorithm is: " 
    	+ totalenergyConsumption + "nJ");
    	System.out.println();
    	
    	

    	
    	System.out.println("The total Resilience level for Node_Based_Algorithm is : " + EResilienceLevel);
    	System.out.println();
 
    }
    
    public void Data_Based_Algorithm () {
    	
    	double energy=0;
		SensorNode generator=Node[0];
		double remainEnergy=0;
		 for(int i=0;i<Node.length;i++)
		    {
		    	remainEnergy+=Node[i].getInitialEnergy();
		    }
		
		for(int i=0;i<Node.length;i++)   // go over the array and find out the generator Node
    	{
		if(Node[i].getStorageCapacity()==0)
			generator=Node[i];
		
    	}
		PriorityQueue<SensorNode> myQueue= new PriorityQueue<SensorNode>();	
		for(int i=0;i<Node.length;i++)   // go over the Node List;
		{
			if(Node[i].getStorageCapacity()>0)   // check the capacity, if it is not zero, add to the priority queue
                myQueue.add(Node[i]);
			
		
		}
		
	//	System.out.println("The priority Queue"+myQueue.toString());
		System.out.println();
		System.out.println("-------------------------------------------");
		//numOfDataItem's iteration, 
		for(int i=0;i<numOfDataItem;i++)
		{   int newCapacity=myQueue.peek().getStorageCapacity();
		    SensorNode aNode;
			
		    aNode=myQueue.poll();   // get the element with the highest energy
		    aNode.setStorageCapacity(newCapacity-1);   // capacity--
		    aNode.setNumOfDataItem(aNode.getNumOfDataItem()+1);  // number of data Item++
		    energy += computingEnergyConsumption(generator, aNode);    // get total energy consumed
		  //  System.out.println("target : "+aNode.getID());
		    if(aNode.getStorageCapacity()>0 && aNode.getInitialEnergy()>0)
		    	myQueue.add(aNode);
	    	//System.out.println("The total energy consumption for Data_Based_Algorithm is: "+energy + "nJ");
		}
		System.out.println("The total energy consumption for Data_Based_Algorithm is: "+energy + "nJ");
		System.out.println();
		  remainEnergy-=energy;
		    
	   	 System.out.println("The total Resilience level for Data_Based_Algorithm is : "+remainEnergy);	
	   	System.out.println();
    	
    }
    
    public void Benefit_Based_Algorithm () {
    	
    	/* put DG in the last position of Node list*/
    /*	SensorNode temp;
    	SensorNode DG = null;
    	for (int i = 0; i < Node.length; i++) {
   		     if (Node[i].isDG()) {
   		    	 System.out.println("Node[" + Node[i].getID() + "] is DG.");
   		    	 DG = Node[i];
   		    	 temp = Node[i];
   		    	 Node[i] = Node[Node.length - 1];
   		    	 Node[Node.length - 1] = temp;
   		     }
   	     }
    	
    	System.out.println();

    	SensorNode[] nonDGNode = new SensorNode[Node.length - 1]; 
    	
    	for (int i = 0; i < nonDGNode.length; i++) {
    		nonDGNode[i] = Node[i];
    	}
    	
        double[] Eremain = new double[nonDGNode.length];
    	
    	int[] storedDataItem = new int[nonDGNode.length];
    	
    	int dataItemRemain = numOfDataItem;
    	
    	double totalenergyConsumption = 0;
    	int k = 0; 
    	int sum = 0;
    	double EResilienceLevel = 0;  
    	
    	for (int j = 1; j < numOfDataItem; j++) {
    		
    		for (int i = 0; i < nonDGNode.length; i++ ) {
    			
    			for (int k2 = 1; k2 < nonDGNode[0].getStorageCapacity(); k2++) {
    				
    			}
    		}
    	}*/
    	
    	double energy=0;
		int count=numOfDataItem;
		SensorNode generator=Node[0],betaPeek;
		for(int i=0;i<Node.length;i++)   // go over the array and find out the generator Node
    	{
		if(Node[i].getStorageCapacity()==0)
			generator=Node[i];
		
    	}
		PriorityQueue<SensorNode> betaQueue= new PriorityQueue<SensorNode>();	
		
		for(int i=0;i<numOfDataItem;i++)   // go over the Node items;
		{
			for(int k=0;k<Node.length;k++)   // go over the Node List;
			{
				if(Node[k].getStorageCapacity()>0)   // check the capacity, if it is not zero, add to the priority queue
					betaQueue.add(Node[k]);
				
			
			}
			int peekCap= betaQueue.peek().getStorageCapacity();
			int peekDI= betaQueue.peek().getNumOfDataItem();
			    betaPeek=betaQueue.poll();
			betaQueue.peek().setStorageCapacity(peekCap-1);
			betaQueue.peek().setNumOfDataItem(peekDI-1);
			
	       energy+=computingEnergyConsumption(generator,betaPeek);    // get total energy consumed
	       
	       if(peekCap>0)
	    	   betaQueue.add(betaPeek);
	       
//	        System.out.println("target NNNNNNNNNNNN: "+betaPeek.getID());
    	//System.out.println("The total energy consumption Benefit_Based_Algorithm is: "+energy + "nJ");
    	
		
		}
		
		System.out.println("The total energy consumption Benefit_Based_Algorithm is: "+energy + "nJ");
		System.out.println();
		
		 double remainEnergy=0;
		 for(int i=0;i<Node.length;i++)
		    {
		    	remainEnergy+=Node[i].getInitialEnergy();
		    }	
		 remainEnergy-=energy;
		    
	   	 System.out.println("The total energy consumption Benefit_Based_Algorithm is: "+remainEnergy);	
	   	System.out.println();
    }
    
    
    
       private void sorting_By_InitialEnergy (SensorNode[] n) {
    	   for (int i = 1; i < n.length; i++) {
    		   
    		   SensorNode temp = n[i];
    		   int j;
    		   
    		   for (j = i - 1; j >= 0 && temp.getInitialEnergy() > n[j].getInitialEnergy(); j--) {
    			   n[j + 1] = n[j];
    		   }
    		   n[j + 1] = temp;
    	   }
       }
	
}

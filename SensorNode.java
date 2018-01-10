package GraduateProject2;
import java.util.*;


public class SensorNode implements Comparable<SensorNode> {
	
	private int ID;
	private boolean isDG;
	private int xPosition;
	private int yPosition;
	private int numOfDataItem;     //number of data Item of each DG :q
	private int initialEnergy;     // initial energy of each node: e
	private int storageCapacity;   // storage capacity of each non-DG: m
	private int TransmissionRange; // transmission range: Tr
	private int a;                 // lower range of  initial Energy
	private int b;                 // upper range of  initial Energy
	
	public double minDistance = Double.POSITIVE_INFINITY;
	public SensorNode previous;
 
    public SensorNode() {
	    this.ID = -1;
	    
    	this.isDG = false;
    	
    	xPosition = (int) (Math.random() * 50) + 1;
    	yPosition = (int) (Math.random() * 50) + 1;
    	
    	this.numOfDataItem = 0;
    	
    	Scanner input = new Scanner(System.in);
    	System.out.println("Enter lower range of  initial Energy a : ");
		this.a = input.nextInt();
		System.out.println("Enter upper range of  initial Energy b : ");
		this.b = input.nextInt();
		this.initialEnergy = (int) (Math.random() * (b - a) + a) + 1;
		
		System.out.println("Enter Transmission Range Tr : ");
		this.TransmissionRange = input.nextInt();
		
		System.out.println("Enter storage capacity of each non-DG m : ");
		this.storageCapacity = input.nextInt();
		
	     this.previous = null;
    	}
    
    /*for testing*/
    public SensorNode(int a, int b, int Tr) {
    	this.ID = -1;
 	    this.a = a;
 	    this.b = b;
 	    
     	this.isDG = false;
     	
     	this.initialEnergy = (int) (Math.random() * (b - a) + a) + 1;
     	this.TransmissionRange = Tr;
     	
     	this.xPosition = (int) (Math.random() * 50) + 1;
     	this.yPosition = (int) (Math.random() * 50) + 1;
     	
     	this.numOfDataItem = 0;
     	
     	this.storageCapacity = 3;
     	
     
        this.previous = null;
    	
    }
    
    /*public SensorNode(int id,int x,int y, int intitialEnergy, int Tr) {
        ID = id;
        this.xPosition = x;
        this.xPosition = y;
    
        this.previous = null;
        
        
        this.isDG = false;

     	this.numOfDataItem = 0;
     	
     	this.storageCapacity = 0;
    }*/
    
   
   /* public SensorNode(int x, int y, int TransmissionRange) {
    	 this.ID = -1;
 	    
     	this.isDG = false;
     	
     	xPosition = x;
     	yPosition = y;
     	
     	this.numOfDataItem = 0;
     	
     	
     	this.initialEnergy = (int) (Math.random() * (10000000 - 90000000) + 90000000) + 1;
 		
 		
 		this.TransmissionRange = TransmissionRange;
 		
 		
 		this.storageCapacity =3;
 		
 		
 		
 	     this.previous = null;
    } */
    
	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(int x) {
		this.xPosition = (int) (Math.random() * x) + 1;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(int y) {
		this.yPosition = (int) (Math.random() * y) + 1;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isDG() {
		return isDG;
	}

	public void setDG(boolean isDG) {
		this.isDG = isDG;
	}

	public int getNumOfDataItem() {
		return numOfDataItem;
	}

	public void setNumOfDataItem(int numOfDataItem) {
		this.numOfDataItem = numOfDataItem;
	}

	public int getInitialEnergy() {
		return initialEnergy;
	}

	public void setInitialEnergy(int a, int b) {
		this.initialEnergy = (int) (Math.random() * (b - a) + a) + 1;
	}
	
	
	public void setInitialEnergy(int initialEnergy) {
		this.initialEnergy = initialEnergy;
	}
	

	public int getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public int getTransmissionRange() {
		return TransmissionRange;
	}

	public void setTransmissionRange(int transmissionRange) {
		TransmissionRange = transmissionRange;
	}

	

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	
	@Override
	public String toString() {
		return "SensorNode [ID=" + ID + ", isDG=" + isDG + ", xPosition=" + xPosition + ", yPosition=" + yPosition
				+ ", numOfDataItem=" + numOfDataItem + ", initialEnergy=" + initialEnergy + ", storageCapacity="
				+ storageCapacity + ", TransmissionRange=" + TransmissionRange + ", a=" + a + ", b=" + b
				+ ", minDistance=" + minDistance + ", previous=" + previous + "]";
	}

	public int compareTo(SensorNode otherNode) {
        return Double.compare(minDistance, otherNode.minDistance);
        
    
    }
	
}

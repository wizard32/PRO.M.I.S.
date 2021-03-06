/*///////////////////////////////////////////////////////////////////////
**  This class creates Vertices and stores their state(healthy,infected        **
**suscptible) in a boolean array(state),in order to keep track of their          **
**state during the simulation.	 							       **
///////////////////////////////////////////////////////////////////////*/

//import java.util.Vector;

public class Node {
    protected int incubation;
    protected int securityLevel;
    protected int vpointer;
    protected int massHits;


    final int bufferSize = 10;
    final int incomingSize = 320;
    final float  minActiveNodesP = 0.11f;

    protected double lRate = 0;
    protected double gRate = 0;

    protected boolean p2p;

    protected int[]  logBuffer;
    protected double[]  incomingBuffer;

    protected boolean[] state;

    final float  tHigh = 0.40f;
    final float  tLow =  -0.40f;


    Node() { }


    /**
     * Constructor of the node. Each node is by default initialized as healthy and susceptible
     */
    Node(int index) {
        int name = index;
        incubation = 1;
        state = new boolean[3];
        massHits = 1;

        state[0] = true;
        state[1] = false;
        state[2] = false;

        logBuffer = new int[bufferSize];

        //Initialize the logBuffer
        for (int i = 0; i < bufferSize; i++) {
            logBuffer[i] = 0;
        }

        incomingBuffer = new double[incomingSize];

        //Initialize the incomingBuffer
        for (int i = 0; i < incomingSize; i++) {
           incomingBuffer[i] = 0;
        }

        this.securityLevel = 1;
    }

    /**
     * Constructor of the node. Each node is by default initialized as susceptible and not immune (removed)
     */
    Node(String vertex) {
        String name = vertex;
        incubation = 1;                                                   
        massHits = 1;
        state = new boolean[3];

        state[0] = true;
        state[1] = false;
        state[2] = false;

        logBuffer = new int[bufferSize];

        //Initialize the logBuffer
        for (int i = 0; i < bufferSize; i++) {
            logBuffer[i] = 0;
        }

        incomingBuffer = new double[incomingSize];

        //Initialize the incomingBuffer
        for (int i = 0; i < incomingSize; i++) {
           incomingBuffer[i] = 0;
        }

        this.securityLevel = 1;
    }


    public void setState(boolean susceptible, boolean infected, boolean immune) {
        state[0] = susceptible;
        state[1] = infected;
        state[2] = immune;
    }

    /*
     *Mutual exclusive setSusceptible function
     */
    public void setSusceptible() {
        state[0] = true;
        state[1] = false;
        state[2] = false;
    }

    /*
    *Mutual exclusive setInfected function
    */
    public void setInfected(int incub) {
        state[0] = false;
        state[1] = true;
        state[2] = false;
        incubation = incub;
    }


    /*
     *Mutual exclusive setInfected function
     *This method is deprecated. Please use setInfected(int incub) instead
     */
    public void setInfected() {
        state[0] = false;
        state[1] = true;
        state[2] = false;
    }

    /*
     *Mutual exclusive setImmune function
     */
    public void setImmune() {
        state[0] = false;
        state[1] = false;
        state[2] = true;
    }

    public void getState() {
        checkSusceptible();
        checkInfected();
        checkImmune();
    }


    protected boolean checkSusceptible() {
        return state[0];
    }

    protected boolean checkInfected() {
        return state[1];
    }

    protected boolean checkImmune() {
        return state[2];
    }

    public void setIncubation(int incub) {
        incubation = incub;
    }

    public void increaseIncubation() {
        incubation++;
    }

    public void decreaseIncubation() {
        incubation--;
    }

    protected int getIncubation() {
        return incubation;
    }

    /**
     *
      * @return  LOCAL intercepted malicious activity ONLY
     */
    protected int getVectorSize() {
        return this.bufferSize;
    }

    /**
     *
      * @return  LOCAL intercepted malicious activity ONLY
     */
    protected int getBufferSize() {
        return this.incomingSize;
    }
    protected boolean getP2PMember() {
        return this.p2p;
    }

    protected void setP2PMember() {
        this.p2p = true;
    }


    /**Logs every intercepted attack by each node
     *
     * @param vp virtual pointer indicating the correct position in the array according to the specific cycle
     */
    protected void logAttack(int vp) {
        //increment by one the attack counter
        logBuffer[vp] = logBuffer[vp] + 1;

        //Some debug code - hopefully not needed
        if (vp == this.bufferSize) System.exit(-1);
        //and initialize the circullar list
        if ((vp + 1) == this.bufferSize) {
            //Initialize again the system
            logBuffer[0] = 0;
        } else {
            //or clear the next slot in the matrix
            logBuffer[vp + 1] = 0;
        }
    }

     /**Logs every intercepted a mass number of  attacks in order to comply with discretization
     *
     * @param vp virtual pointer indicating the correct position in the array according to the specific cycle
     */
    protected void logAttack(int vp, int hits) {
        //increment by one the attack counter
        logBuffer[vp] = logBuffer[vp] + hits;

        //Some debug code - hopefully not needed
        if (vp == this.bufferSize) System.exit(-1);
        //and initialize the circullar list
        if ((vp + 1) == this.bufferSize) {
            //Initialize again the system
            logBuffer[0] = 0;
        } else {
            //or clear the next slot in the matrix
            logBuffer[vp + 1] = 0;
        }
    }
    /**Logs all the inoming rates from other P2P members. This not time ordered as the logAttack function but logs every
     * that is sent to it
     *
     * @param rate
     */
    protected void logRate(double rate) {

            //Some debug code - hopefully not needed
            if (!this.getP2PMember()) {
                  System.out.println("Critical Problem with P2P Membership");
                  System.exit(-1);
            }

            if (rate <  0) {
               System.out.println("Where that did that came from? " + rate);
              // System.exit(-1);
            }

            if (rate == -666) {
                //Normaly shouldn't reach this point
                System.out.println("666 Received -- Aborting");
                System.exit(-1);
            }


            if ((this.vpointer + 1) == this.incomingSize) {
                incomingBuffer[this.vpointer] = rate;
                this.vpointer = 0;
                incomingBuffer[this.vpointer] = 0;
            } else {
                incomingBuffer[this.vpointer] = rate;
                //clear the next slot in the matrix
                this.vpointer++;
                incomingBuffer[this.vpointer] = 0;

            }
        }


    public int increaseSecurityLevel() {

    double luckyPatch = Math.random();


        if (this.securityLevel < 5) {
            this.securityLevel++;
            if (((this.securityLevel - 1) * 0.2) > luckyPatch) this.setImmune();
            System.out.println("^^^^^^^^^^^^^^Changing Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return(this.securityLevel);
        } else if (this.securityLevel == 5) {
            //System.out.println("^^^^^^^^^^^^^^Keeping Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return 5;
        }
        else {
            System.out.println("Security Level > 5 <--> Security Level " + this.securityLevel + " <--> Aborting");
            System.exit(-1);
        }
        return -666;
    }

    public int decreaseSecurityLevel() {

         double badWorm = Math.random();

        if (this.securityLevel > 1) {
            this.securityLevel--;
            if (((this.securityLevel - 1) * 0.2) < badWorm) this.setSusceptible();
            System.out.println("^^^^^^^^^^^^^^Changing Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return(this.securityLevel);                                                  
        } else if (this.securityLevel == 1) {
            //System.out.println("^^^^^^^^^^^^^^Keeping Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return 1;
        }
        else {
            System.out.println("Security Level < 0 <--> Security Level " + this.securityLevel + " <--> Aborting");
            System.exit(-1);
        }
        return -666;
    }

    /**
     *
     * @return Return the security level of this node
     */
    public int getSecurityLevel() {

        return this.securityLevel;
    }
    /**Flushes the current Rate  buffer because its data are valid only during the last cycle.
        *
        *
        */
       protected void flushRateBuffer() {
           for (int i = 0; i < this.incomingSize; i++) {
               incomingBuffer[i] = 0;
           }
           //System.out.println("---Flushed Active Members:= " + activeNodes + "---");
       }
    /**Calculates and returns the average (Global) rate in the P2P group
     *
     * @return The global rate
     */
     public double calculateGRate() {
         double tempIBSum = 0;
         double globalRate;
         int activeNodes = 0;
         float workingNodesP = 0;

        for (int i = 0; i < this.incomingSize; i++) {
            //Leave the recent number of attacks out
            //Warning have to do something to skip zeros
           if ((this.incomingBuffer[i] != 0) && (this.incomingBuffer[i] != -666)) {
               activeNodes++;
               tempIBSum += this.incomingBuffer[i];
               //System.out.println("incoming Buffer:" + incomingBuffer[i] + "\tactiveNodes: " + activeNodes + "\ttempIBSum: " + tempIBSum);
               if (this.incomingBuffer[i] <= 0) {
                   System.out.print("Logged negative rate:\t" + incomingBuffer[i]);
                   System.out.println("Is node p2p member? " + this.p2p);
                   System.exit(-1);
               }
           }
        }


        /*THIS WORKS SUFFICIENT TILL NOW BUT TRYING TO MAKE IT MORE AGRESSIVE FOR SCALE-FREE GRAPHS
        if (activeNodes == 0) return 0;
        else globalRate = (tempIBSum / activeNodes);
        */

        workingNodesP =  ((float) activeNodes /(float) this.incomingSize);
        System.out.println("ACTIVE:= " + activeNodes +"\tMINIMUM:= " +  minActiveNodesP + "\tWORKING " + workingNodesP);

        //A minimum number of nodes should participate in order to have trustworthy results.
        if ((activeNodes == 0) || (workingNodesP <= minActiveNodesP) || (workingNodesP == 0) ) {return 0;}
        else globalRate = (tempIBSum / activeNodes);



        if (globalRate > tHigh) {
            this.increaseSecurityLevel();
        } else if (globalRate <= tLow) {
            this.decreaseSecurityLevel();
        }

        return globalRate;
     }

    public double calculateLRate(int vp) {
            int tempSum = 0;
            double mean;
            double pchange;
            int actualSize = 0;
            int vptemp;


            //Calculating the total sum of attacks for the  whole buffer
            //And finding the number of slots-points in which attacks were montored 
            for (int i = 0; i < this.bufferSize; i++) {
                //Leave the recent number of attacks out
                //SHOULD WE USE ALSO THE 0 VALUE - SLOT ?
                if ((i != vp) && (logBuffer[i] != 0)) {
                    tempSum += logBuffer[i];
                    actualSize++;
                }
            }

            //If no malicious activity is loged return -666 to be skipped from the rest of the p2p members
            if ((this.checkInfected()) /*|| (!this.getP2PMember())*/ ) {
                return(-666);
            }

            if (actualSize !=0) {
                mean = tempSum / (actualSize );
                if (vp == 0) {vp = this.bufferSize;}  
                pchange = ((logBuffer[vp - 1] - mean) / mean);

                if (pchange < 0) {
                    System.out.println("pchange: " + pchange + "\tlogBuffer[vp]: " + logBuffer[vp] + "\tmean: " + mean);
                    System.out.println("tempSum: " + tempSum + "\tactualSize: " + actualSize);
                    vptemp = this.bufferSize;
                    System.out.println("*");
                    for (int i = 0; i < this.bufferSize; i++) {
                        System.out.println("\t" + logBuffer[i] + "\t");
                        if (i == vp)  System.out.print("<-");
                    }
                    System.out.println("logBuffer[vp]" + logBuffer[vp] + "\tlogBuffer[vp-1]" + logBuffer[vptemp-1]);
                    System.out.println("Sending Negative is infected? " + this.checkInfected());
                    if (vp == 0) {
                        vptemp = this.bufferSize;
                    }
                }
                return pchange;
            } else {
                return -666;
            }
        }
}

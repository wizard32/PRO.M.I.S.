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
    final int incomingSize = 100;
    final float minActiveNodesP = 0.001f;
    protected double lRate = 0;
    protected double gRate = 0;
    protected boolean cloud;
    protected int[] logBuffer;
    protected double[] incomingBuffer;
    protected boolean[] state;
    final float tHigh = 0.10f;
    final float tLow = -0.40f;

    Node() {
    }

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

    protected boolean getCloudMember() {
        return this.cloud;
    }

    protected void setCloudMember() {
        this.cloud = true;
    }

    /**Logs every intercepted attack by each node
     *
     * @param vp virtual pointer indicating the correct position in the array according to the specific cycle
     */
    protected void logAttack(int vp) {
        //increment by one the attack counter
        logBuffer[vp] = logBuffer[vp] + 1;

        //Some debug code - hopefully not needed
        if (vp == this.bufferSize) {
            System.exit(-1);
        }
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
        if (vp == this.bufferSize) {
            System.exit(-1);
        }
        //and initialize the circullar list
        if ((vp + 1) == this.bufferSize) {
            //Initialize again the system
            logBuffer[0] = 0;
        } else {
            //or clear the next slot in the matrix
            logBuffer[vp + 1] = 0;
        }
    }

    /**Logs all the incoming rates from other P2P members. This not time ordered as the logAttack function but logs every
     * that is sent to it
     *
     * @param rate
     */
    protected void logRate(double rate) {

        //Some debug code - hopefully not needed
        if (!this.getCloudMember()) {
            System.out.println("Critical Problem with Cloud Membership");
            System.exit(-1);
        }

        if (rate == -666) {
            //Normaly shouldn't reach this point
            System.out.println("666 Received -- Aborting");
            System.exit(-1);
        }


        if ((this.vpointer + 1) == this.incomingSize) {
            incomingBuffer[this.vpointer] = rate;
            this.vpointer = 0;
            //incomingBuffer[this.vpointer] = 0;
        } else {
            incomingBuffer[this.vpointer] = rate;
            //clear the next slot in the matrix
            this.vpointer++;
           // incomingBuffer[this.vpointer] = 0;

        }
    }

    public int increaseSecurityLevel() {

        double luckyPatch = Math.random();


        if (this.securityLevel < 2) {
            this.securityLevel++;
            if (((this.securityLevel - 1) * 0.3) > luckyPatch) {
                this.setImmune();
            }
            System.out.println("^^^^^^^^^^^^^^Changing Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return (this.securityLevel);
        } else if (this.securityLevel == 2) {
            System.out.println("^^^^^^^^^^^^^^Keeping Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return 2;
        } else {
            System.out.println("Security Level > 5 <--> Security Level " + this.securityLevel + " <--> Aborting");
            System.exit(-1);
        }
        return -666;
    }

    public int decreaseSecurityLevel() {

        double badWorm = Math.random();

        if (this.securityLevel > 1) {
            this.securityLevel--;
            if (((this.securityLevel - 1) * 0.2) < badWorm) {
                this.setSusceptible();
            }
            System.out.println("^^^^^^^^^^^^^^Changing Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return (this.securityLevel);
        } else if (this.securityLevel == 1) {
            //System.out.println("^^^^^^^^^^^^^^Keeping Security Level to " + this.securityLevel + "^^^^^^^^^^^^^^");
            this.flushRateBuffer();
            return 1;
        } else {
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

    /**Calculates and returns the average (Global) rate in the Cloud group
     *
     * @return The global rate
     */
    public double calculateGRate() {
        double tempIBSum = 0;
        double globalRate;
        int activeNodes = 0;
        float workingNodesP = 0;
        double peirce = 0;


        /* for (int i = 0; i < this.incomingSize; i++) {
        //Leave the recent number of attacks out
        //Warning have to do something to skip zeros
        if ((this.incomingBuffer[i] != 0) && (this.incomingBuffer[i] != -666)) {
        activeNodes++;
        tempIBSum += this.incomingBuffer[i];
        //System.out.println("incoming Buffer:" + incomingBuffer[i] + "\tactiveNodes: " + activeNodes + "\ttempIBSum: " + tempIBSum);
        if (this.incomingBuffer[i] <= 0) {
        System.out.print("Logged negative rate:\t" + incomingBuffer[i]);
        System.out.println("Is node cloud member? " + this.cloud);
        System.exit(-1);
        }
        }
        }*/
        for (int i = 0; i < this.incomingSize; i++) {
            //Leave the recent number of attacks out
            //Warning have to do something to skip zeros
            activeNodes++;
            tempIBSum += this.incomingBuffer[i];
            //System.out.println("incoming Buffer:" + incomingBuffer[i] + "\tactiveNodes: " + activeNodes + "\ttempIBSum: " + tempIBSum);
            if (this.incomingBuffer[i] == -666) {
                System.out.print("Logged negative rate:\t" + incomingBuffer[i]);
                System.out.println("Is node cloud member? " + this.cloud);
                //System.exit(-1);
            }
        }

        /*THIS WORKS SUFFICIENT TILL NOW BUT TRYING TO MAKE IT MORE AGRESSIVE FOR SCALE-FREE GRAPHS
        if (activeNodes == 0) return 0;
        else globalRate = (tempIBSum / activeNodes);
         */

        workingNodesP = ((float) activeNodes / (float) this.incomingSize);
        System.out.println("@ACTIVE:= " + activeNodes + "\tMINIMUM:= " + minActiveNodesP + "\tWORKING " + workingNodesP);

        //A minimum number of nodes should participate in order to have trustworthy results.
        if ((activeNodes == 0) || (workingNodesP <= minActiveNodesP) || (workingNodesP == 0)) {
            return 0;
        } else {
            globalRate = (tempIBSum / activeNodes);
        }


        if (globalRate > tHigh) {
            this.increaseSecurityLevel();
        } else if (globalRate <= tLow) {
            this.decreaseSecurityLevel();
        }

        //tempory to check
        peirce = peircecritirion(this.incomingBuffer, globalRate);
        System.out.println("new rate = " + peirce + " =? globalRate = " + globalRate);
        return globalRate;
    }

  public double calculateLRate(int vp) {
		int tempSum = 0;
		double mean;
		double pchange;
		int actualSize = 0;
		int vptemp;


		//Calculating the total sum of attacks for the  whole buffer
		//And finding the number of slots-points in which attacks were monitored
		for (int i = 0; i < this.bufferSize; i++) {
			//Leave the recent number of attacks out
			//SHOULD WE USE ALSO THE 0 VALUE - SLOT ?
			if ((i != vp) && (logBuffer[i] != 0)) {
				tempSum += logBuffer[i];
				actualSize++;
			}
		}

		//If no malicious activity is loged return -666 to be skipped from the rest of the cloud members
		if ((this.checkInfected()) /*|| (!this.getCloudMember())*/) {
			return (-666);
		}

		if (actualSize != 0) {
			mean = tempSum / (actualSize);
			if (vp == 0) {
				vp = this.bufferSize;
			}
			/*if (vp == this.bufferSize) {
				vp = 1;
			}*/
			pchange = ((logBuffer[vp - 1] - mean) / mean);

			if (pchange < 0) {
				System.out.println("pchange: " + pchange + "\tlogBuffer[vp-1]: " + logBuffer[vp - 1] + "\tmean: " + mean);
				System.out.println("tempSum: " + tempSum + "\tactualSize: " + actualSize);
				vptemp = this.bufferSize;
				System.out.println("*");
				for (int i = 0; i < this.bufferSize; i++) {
					if (i == vp) {
						System.out.print("<-");
					}
					System.out.println("\t" + logBuffer[i] + "\t");
				}
				System.out.println("logBuffer[vp]" + logBuffer[vp-1] + "\tlogBuffer[vp-1] " + logBuffer[vptemp - 1]);
				//System.out.println("Sending Negative is infected? " + this.checkInfected());
				if (vp == 0) {
					vptemp = this.bufferSize;
				}
			}
			return pchange;
		} else {
			return -666;
		}
	}

	public double peircecritirion(double pierceBuffer[], double globalRate) {
		double globalAcceptedRate = 0;
		double statMeasurement = 0;
		double deviationSum = 0;
		double deviation = 0;
		double R = 0;
		double max = 0;
		double maxAllowedDeviation = 0;
		int statNodes = 0;
		boolean dropped = false;
		double globalAcceptedSum = 0;
		//Statistics to avoid outliers

		for (int i = 0; i < pierceBuffer.length; i++) {
			//Leave the recent number of attacks out
			//Warning have to do something to skip zeros
			//System.out.println("@pierceBuffer[i]= " + pierceBuffer[i] + " i= " + i);
			if ((pierceBuffer[i] != 0)) {
				statNodes++;
				statMeasurement = Math.pow((pierceBuffer[i] - globalRate), 2);
				deviationSum += statMeasurement;
				//System.out.println("@Standard Deviation= " + pierceBuffer[i] + " - " + globalRate + " = " + (pierceBuffer[i] - globalRate) + " ^2 = " + Math.pow((pierceBuffer[i] - globalRate),2));
				//System.out.println("@=? " + pierceBuffer[i] + " statNodes = " + statNodes);
			}
		}

		//as we have no non-zero data just return the global rate
		if (statNodes == 0) {
			return globalAcceptedRate;
		}

		//check if the deviation is correct. Assuming yes
		if (statNodes > 0) {
			deviation = (Math.sqrt(deviationSum) / statNodes);
			System.out.println("&Deviaton = " + deviation + " devitionSum= " + deviationSum + " sqrtDeviation= " + (Math.sqrt(deviationSum)) + " statNodes= " + statNodes);
		}

		for (int i = 0; i < pierceBuffer.length; i++) {
			//Leave the recent number of attacks out
			//Warning have to do something to skip zeros
			//Find R
			R = (Math.abs(pierceBuffer[i] - globalRate)) / deviation;
			//System.out.println("@R = " + R + " grate = " + globalRate + " deviation = " + deviation + " checking node " + i + " pierceBuffer[i] = " + pierceBuffer[i]);
			if (R > max) {
				max = R;
				System.out.println("&New max = " + max);
				maxAllowedDeviation = deviation * max;
				System.out.println("&Max allowed= " + maxAllowedDeviation + " deviation= " + deviation + " max= " + max);
			}
		}

		statNodes = 0;
		for (int i = 0; i < pierceBuffer.length; i++) {
			//Leave the recent number of attacks out
			//Warning have to do something to skip zeros
			//if ((pierceBuffer[i] != 0) && (pierceBuffer[i] != -666)) {
			globalAcceptedSum += pierceBuffer[i];
			statNodes++;
			if (pierceBuffer[i] > max) {
				//Dropping outlier
				System.out.println("&Dropping " + pierceBuffer[i] + " => max= " + max);
				pierceBuffer[i] = 0;
				dropped = true;
			}
			if (pierceBuffer[i] < 0) {
				System.out.print("Logged negative rate:\t" + pierceBuffer[i]);
				System.out.println("Is node cloud member? " + this.cloud);
				//System.exit(-1);
			}
			//}
		}
		globalAcceptedRate = (globalAcceptedSum / statNodes);
		if (dropped == true) {
			peircecritirion(pierceBuffer, globalAcceptedRate);
		}
		return globalAcceptedRate;
	}
}

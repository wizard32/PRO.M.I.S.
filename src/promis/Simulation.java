/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class Simulation {

    protected static double vita;
    protected static double delta;
    protected static int incubation;
    protected static int maxIterationNumber;
    protected static int numOfSusceptible = 0;
    protected static int numOfInfected = 0;
    protected static int numOfImmune = 0;
    protected static int numOfVertices = 0;
    protected static int numOfInitialInfected = 0;
    protected static int numOfInitialImmune;
    protected static int numOfP2PMembers;
    protected static int level1 = 0;
    protected static int level2 = 0;
    protected static int level3 = 0;
    protected static int level4 = 0;
    protected static int level5 = 0;

    protected static long seed = 0;

    static double tempRate = 0;
    static double averageRate = 0;
    static double lastRate = 0;
    static double secondRate = 0; 

    protected static boolean selfdestruct = false;

    //Time related variables
    static final int HOURS_PER_DAY = 24;
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int MILLISECONDS_PER_SECOND = 1000;

    static String debugFileName;
    static String plotFileName;
    static String secondPlotFileName;
    static String thirdPlotFileName;
    static String splotFileName;
    static String statisticsFileName;
    static String verifyResultsFileName;
    static String checkVirusFileName;
    static String infectionProgressFileName;
    static String rateFileName;
    static String siRateFileName;
    static String p2pFileName;
    static String successFileName;


    static String classType;
    static String[] sa;
    static String outputsFilePath = "../other/";


    static Random rgenerator;
    static Graph generalGraph;
    
    
    /**
     * Set the birth rate of the worm during the simulation process
     */
    protected static void setVita(double v) {
        Simulation.vita = v;
    }

    /**
     * Get  the birth rate of the worm during the simulation process
     */
    protected static double getVita() {
        return Simulation.vita;
    }


    /**
     * Set the maximum nuber of time slots (simulation cycles) for the simulation process
     */
    protected static void setMaxIterationNumber(int m) {
        Simulation.maxIterationNumber = m;
    }


    /**
     * Get the maximum nuber of time slots (simulation cycles) for the simulation process
     */
    protected static int getMaxIterationNumber() {
        return Simulation.maxIterationNumber;
    }

    /*
     *Set the incubation parameter (E in most epidimiological models) for  the simulation process
     */
    protected static void setEnviromentaltIncubation(int incub) {
        Simulation.incubation = incub;
    }

    /*
     *Get the incubation parameter (E in most epidimiological models) for  the simulation process
     */
    protected static int getEnviromentaltIncubation() {
        return (Simulation.incubation);
    }


    /*
     *Set the number of the susceptible population
     */
    protected static void setNumberOfSusceptible(int s) {
        Simulation.numOfSusceptible = s;
    }

    /*
     *Get the number of the susceptible population
     */
    protected static int getNumberOfSusceptible() {
        return (Simulation.numOfSusceptible);
    }


    /*
     *Decrease by one the number of the susceptible population.
     *It is possible onty to leave from this stage
     */
    protected static void decreaseNumberOfSusceptible() {
        Simulation.numOfSusceptible--;
    }

    /*
     *Increase by one the number of the susceptible population.
     *For debugging
     */
    protected static void increaseNumberOfSusceptible() {
        Simulation.numOfSusceptible++;
    }

    /*
     *Set the number of the infected population
     */
    protected static void setNumberOfInfected(int inf) {
        Simulation.numOfInfected = inf;
    }

    /*
     *Get the number of the infected population
     */
    protected static int getNumberOfInfected() {
        return (Simulation.numOfInfected);
    }

    /*
     *Set the number of the initial infected population
     */
    protected static void setNumberOfInitialInfected(int inInf) {
        Simulation.numOfInitialInfected = inInf;
    }

    /*
     *Get the number of the initial infected population
     */
    protected static int getNumberOfInitialInfected() {
        return (Simulation.numOfInitialInfected);
    }
    
    /*
     *Increase by one  number of the infected population
     */
    protected static void increaseNumberOfInfected() {
        Simulation.numOfInfected++;
    }

    /*
     *Decrease by one  the number of the infected population
     */
    protected static void decreaseNumberOfInfected() {
        Simulation.numOfInfected--;
    }

    /*
    *Set the number of the immune population
    */
    protected static void setNumberOfImune(int im) {
        Simulation.numOfImmune = im;
    }

    /*
    *Get the number of the infected population
    */
    protected static int getNumberOfImmune() {
        return (Simulation.numOfImmune);
    }

    /*
    *Increase by one the number of the infected population
    */
    protected static void increaseNumberOfImmune() {
        Simulation.numOfImmune++;
    }


    protected static boolean getSelfdestruct() {
        return (Simulation.selfdestruct);
    }
    
    

    protected static void setSelfdestruct(boolean b) {
        Simulation.selfdestruct = b;
    }

    protected static void setNumOfVertices(int inf) {
        Simulation.numOfVertices = inf;
    }

    protected static int getNumOfVertices() {
        return (Simulation.numOfVertices);
    }

    protected static void increaseP2PMembers() {
        Simulation.numOfP2PMembers++;
    }

    protected static void decreaseP2PMembers() {
        Simulation.numOfP2PMembers--;
    }

    protected static int getNumOfP2PMembers() {
        return Simulation.numOfP2PMembers;
    }


    /*
       **FEED METHODS FOR VIRUSES--NUMBER OF NODES IS SPECIFIED BY THE USER BUT THEY ARE RANDOMLY SELECTED***
     */
	
	//Appropriate warning annotation to suppress this kind of warning
	//@SuppressWarnings("unchecked")
	
    public static void semiRandomVirusFeeder(/*Graph generalGraph,*/ int ininfe, int immune, int p2p) {
        String tmp;
        int nodeIndex;
        int numOfInfNodes;
        Integer nodeInteger;
        Node nameOfNode;
        int NumOfVertices;
        //Vector v,z,p;
        boolean found;
        boolean checked;
        boolean p2pMember;

        NumOfVertices = Simulation.getNumOfVertices();
        Vector<Integer> v = new Vector<Integer>();
        Vector<Integer> z = new Vector<Integer>();
        Vector<Integer> p = new Vector<Integer>();


        //First handle the pre-infected nodes
        if (ininfe <= 0) {
            do {
                System.out.println("Please enter the number of the preinfected nodes: ");
                tmp = SimpleIO.readLine();
                numOfInfNodes = Integer.parseInt(tmp);
            } while (numOfInfNodes >= NumOfVertices);
        } else {
            numOfInfNodes = ininfe;
        }

		
        while (v.size() <= numOfInfNodes) {
            found = false;
            nodeIndex = (int) (NumOfVertices * rgenerator.nextDouble());
            if (v.size() == 0) {
                v.addElement(new Integer(nodeIndex));
                continue;
            }
            for (int e = 0; e < v.size(); e++) {
                nodeInteger = (Integer) (v.elementAt(e));
                if (nodeIndex == (nodeInteger.intValue())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                v.addElement(new Integer(nodeIndex));
                nameOfNode = generalGraph.getVertex(nodeIndex);
                nameOfNode.setInfected();
                Simulation.decreaseNumberOfSusceptible();
                Simulation.increaseNumberOfInfected();
                System.out.println("Infecting node: " + nodeIndex);
                System.out.println("Current Number of Infected:= " + Simulation.getNumberOfInfected());
            }
        }
        //Doing that in order to construct correctly statistics-plots file names
        //Simulation.setNumberOfInitialInfected(Simulation.getNumberOfInfected());

        System.out.println("<-------------------------------------------------------------------------------------->");

        //Then handle the immune nodes
        if (immune <= 0) {
            do {
                System.out.println("Please enter the number of the immune nodes: ");
                tmp = SimpleIO.readLine();
                immune = Integer.parseInt(tmp);
            } while ((immune + numOfInfNodes) >= NumOfVertices);
        }

        while (z.size() <= immune) {
            checked = false;
            nodeIndex = (int) (NumOfVertices * rgenerator.nextDouble());

            nameOfNode = generalGraph.getVertex(nodeIndex);

            if (nameOfNode.checkInfected()) {
               continue;
            }

            if (z.size() == 0) {
                z.addElement(new Integer(nodeIndex));
                continue;
            }
            for (int e = 0; e < z.size(); e++) {
                nodeInteger = (Integer) (z.elementAt(e));
                if (nodeIndex == (nodeInteger.intValue())) {
                    checked = true;
                    break;
                }
            }                                                                       

            if (!checked) {
                z.addElement(new Integer(nodeIndex));
                nameOfNode = generalGraph.getVertex(nodeIndex);
                nameOfNode.setImmune();
                Simulation.decreaseNumberOfSusceptible();
                Simulation.increaseNumberOfImmune();
                System.out.println("Immunizing node: " + nodeIndex);
                System.out.println("Current Number of Immune:= " + Simulation.getNumberOfImmune());
            }
        }
        System.out.println("<-------------------------------------------------------------------------------------->");
        p = new Vector<Integer>();
        System.out.println("Number of P2P members: " + p2p);

        while (p.size() <= p2p) {
            p2pMember = false;
            nodeIndex = (int) (NumOfVertices * rgenerator.nextDouble());

             nameOfNode = generalGraph.getVertex(nodeIndex);

            if (nameOfNode.checkInfected())/*|| nameOfNode.getP2PMember())*/ {
               continue;
            }

            if (p.size() == 0) {
                p.addElement(nodeIndex);
                continue;
            }
            for (int e = 0; e < p.size(); e++) {
                nodeInteger = (Integer) p.elementAt(e);
                if (nodeIndex == nodeInteger) {
                    p2pMember = true;
                    System.out.println("Node: " + nodeIndex + " already p2p member");
                    break;
                }
            }

            if (!p2pMember) {
                p.addElement(nodeIndex);
                nameOfNode = generalGraph.getVertex(nodeIndex);
                nameOfNode.setP2PMember();
                System.out.println("New p2p member <-> node: " + nodeIndex);
                Simulation.increaseP2PMembers();
                System.out.println("Current Number of p2p members:= " + Simulation.getNumOfP2PMembers());
                System.out.println("Pending: " + (p2p - Simulation.getNumOfP2PMembers()) );
            }
        }
        System.out.println("<-------------------------------------------------------------------------------------->");
    }

    /*
      **FEED METHOD FOR VIRUSES--NUMBER OF NODES AND WHO THEY ARE, ARE RANDOMLY SELECTED***
      */
      
	//Appropriate warning annotation to suppress this kind of warning
	//@SuppressWarnings("unchecked") 
	
    public static void randomVirusFeeder(/*Graph generalGraph*/) {
        int numOfInfNodes;
        int immune;
        int p2pGroupSize;
        int nodeIndex;
        Integer nodeInteger;
        Node nameOfNode;
        //Vector v;
        //Vector z;
        //Vector p;
        boolean found;
        boolean checked;
        boolean p2pMember;
        int NumOfVertices = Simulation.getNumOfVertices();
        
        Vector<Integer> v = new Vector<Integer>();
        Vector<Integer> z = new Vector<Integer>();
        Vector<Integer> p = new Vector<Integer>();

        System.out.println("Number of Vertices" + NumOfVertices);

        //Infect randomly a random number of nodes
        //Simulation.setNumOFinitialInfected(numOfInfNodes);
        v = new Vector<Integer>();
        numOfInfNodes = (int) (NumOfVertices * rgenerator.nextDouble());
        System.out.println("Number of the preinfected nodes: " + numOfInfNodes);

        while (v.size() <= numOfInfNodes) {
            found = false;
            nodeIndex = (int) (NumOfVertices * rgenerator.nextDouble());
            if (v.size() == 0) {
                v.addElement(new Integer(nodeIndex));
                continue;
            }
            for (int e = 0; e < v.size(); e++) {
                nodeInteger = (Integer) (v.elementAt(e));
                if (nodeIndex == (nodeInteger.intValue())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                v.addElement(new Integer(nodeIndex));
                nameOfNode = generalGraph.getVertex(nodeIndex);
                nameOfNode.setInfected();
                Simulation.decreaseNumberOfSusceptible();
                Simulation.increaseNumberOfInfected();
                System.out.println("Preinfected node=:\t" + nodeIndex);
            }
        }
        //Doing that in order to construct correctly statistics-plots file names
        Simulation.setNumberOfInitialInfected(Simulation.getNumberOfInfected());

        //Infect randomly a random number of nodes
        System.out.println("<-------------------------------------------------------------------------------------->");


        z = new Vector<Integer>();
       // do {
            immune = (int) ((NumOfVertices - Simulation.getNumberOfInfected()) * rgenerator.nextDouble());
            System.out.println("Number of immune nodes: " + numOfInfNodes);
       // } while ((immune + numOfInfNodes) >= NumOfVertices);

        while (z.size() <= immune) {
            checked = false;
            nodeIndex = (int) (NumOfVertices * rgenerator.nextDouble());

            nameOfNode = generalGraph.getVertex(nodeIndex);

            if (nameOfNode.checkInfected()) {
               continue;
            }

            if (z.size() == 0) {
                z.addElement(new Integer(nodeIndex));
                continue;
            }

            for (int e = 0; e < z.size(); e++) {
                nodeInteger = (Integer) (z.elementAt(e));
                if (nodeIndex == (nodeInteger.intValue())) {
                    checked = true;
                    break;
                }
            }

            if (!checked) {
                z.addElement(new Integer(nodeIndex));
                nameOfNode = generalGraph.getVertex(nodeIndex);
                nameOfNode.setImmune();
                Simulation.decreaseNumberOfSusceptible();
                Simulation.increaseNumberOfImmune();
                System.out.println("Immunizing node: " + nodeIndex);
                System.out.println("Current Number of Immune:= " + Simulation.getNumberOfImmune());
            }
        }

        System.out.println("<-------------------------------------------------------------------------------------->");
        p = new Vector<Integer>();
        p2pGroupSize = (int) (NumOfVertices * rgenerator.nextDouble());
        System.out.println("Number of P2P members: " + p2pGroupSize);

        while (p.size() <= p2pGroupSize) {
            p2pMember = false;
            nodeIndex = (int) (NumOfVertices * rgenerator.nextDouble());

             nameOfNode = generalGraph.getVertex(nodeIndex);

            if (nameOfNode.checkInfected())/*|| nameOfNode.getP2PMember())*/ {
               continue;
            }

            if (p.size() == 0) {
                p.addElement(nodeIndex);
                continue;
            }
            for (int e = 0; e < p.size(); e++) {
                nodeInteger = (Integer) p.elementAt(e);
                if (nodeIndex == nodeInteger) {
                    p2pMember = true;
                    System.out.println("Node: " + nodeIndex + " already p2p member");
                    break;
                }
            }

            if (!p2pMember) {
                p.addElement(nodeIndex);
                nameOfNode = generalGraph.getVertex(nodeIndex);
                nameOfNode.setP2PMember();
                System.out.println("New p2p member <-> node: " + nodeIndex);
                Simulation.increaseP2PMembers();
                System.out.println("Current Number of p2p members:= " + Simulation.getNumOfP2PMembers());
                System.out.println("Pending: " + (p2pGroupSize - Simulation.getNumOfP2PMembers()) );
            }
        }
    }


    /**
     * This method is used to check the above virus feeders methods and prints and prints results on screen
     * and on file (by returnig a string with the desirable info)!!
     */
    static String checkVirus(/*Graph generalGraph*/) {
            Node nameOfNode;
            System.out.println("Printing detailed information ");
            int NumOfVertices = Simulation.getNumOfVertices();
            String s = "\nPrinting detailed information \n";

            for (int i = 0; i < NumOfVertices; i++) {
                nameOfNode = generalGraph.getVertex(i);
                if (nameOfNode.checkSusceptible()) {
                    System.out.println("Node \t" + i + "\t susceptible =:" + nameOfNode.checkSusceptible());
                    s = s + "Node \t" + i + "\t susceptible =:" + nameOfNode.checkSusceptible();
                } else if (nameOfNode.checkInfected()) {
                    System.out.println("Node \t" + i + "\t infected =:" + nameOfNode.checkInfected());
                    s = s + "Node \t" + i + "\t infected =:" + nameOfNode.checkInfected();
                } else if (nameOfNode.checkImmune()) {
                    System.out.println("Node \t" + i + "\t immunized =:" + nameOfNode.checkImmune());
                    s = s + "Node \t" + i + "\t immunized =:" + nameOfNode.checkImmune();
                }

                if (nameOfNode.getIncubation() > 0) {
                    System.out.print("        **Incubation period:    " + nameOfNode.getIncubation());
                    System.out.println();
                    s = s + "        **Incubation period:    " + nameOfNode.getIncubation() + "\n";
                } else {
                    s = s + "\n";
                }
            }
            return s;
        }

    
    /**
     * Checking method that is used to verify the results of every iteration of aour simulation model)
     * and prints results on screen and on file (by returnig a string with the desirable info)
     */
    static String verifyResults(/*Graph generalGraph*/) {

        int activeNodes;
        String s = "\n";
        int susceptible =0;
        int immune = 0;
        int infected = 0;


         Node nodeO;

        try {
            for (int o = 0; o < numOfVertices; o++) {
                        nodeO = generalGraph.getVertex(o);
                        if (nodeO.checkImmune()) immune++;
                        if (nodeO.checkSusceptible()) susceptible++;
                        if (nodeO.checkInfected()) infected++;
            }
            Simulation.setNumberOfImune(immune);
            Simulation.setNumberOfSusceptible(susceptible);
            Simulation.setNumberOfInfected(infected);

        } catch (Exception e) {
            System.err.println("Decrease all incubation");
        }


        activeNodes = Simulation.getNumberOfSusceptible() + Simulation.getNumberOfInfected() + Simulation.getNumberOfImmune();
        if (activeNodes != Simulation.getNumOfVertices() /*|| (Simulation.getNumberOfSusceptible() < 0)*/) {

            System.out.println("-----------------------------------------------------------------------------------------");
            s = s + "-----------------------------------------------------------------------------------------\n";
            System.out.println("CorruptedResults");
            s = s + "CorruptedResults\n";
            System.out.println("Active Nodes(Population):  " + activeNodes);
            s = s + "Active Nodes(Population):  " + activeNodes + "\n";
            System.out.println("Total Nodes(Population):   " + Simulation.getNumOfVertices());
            s = s + "Total Nodes(Population):   " + Simulation.getNumOfVertices() + "\n";
            s = s + "*Susceptible Nodes: " + Simulation.getNumberOfSusceptible() + "\n*Infected Nodes: " + Simulation.getNumberOfInfected()
                    + "\n*Immune Nodes: " + Simulation.getNumberOfImmune() + "\n*P2PMembers: " + Simulation.getNumOfP2PMembers();
            System.exit(-1);

        } else {
            System.out.println("-----------------------------------------------------------------------------------------");
            s = s + "-----------------------------------------------------------------------------------------\n";
            System.out.println("Results verified ");
            s = s + "Results verified \n";
            System.out.println("Active Nodes(Population):  " + activeNodes + " Total Nodes(Population):   " + Simulation.getNumOfVertices());
            s = s + "Active Nodes(Population):  " + activeNodes + " \tTotal Nodes(Population):   " + Simulation.getNumOfVertices() + "\n";
            s = s + "*Susceptible Nodes: " + Simulation.getNumberOfSusceptible() + "\nInfected Nodes: " + Simulation.getNumberOfInfected()
                    + "\n*Immune Nodes: " + Simulation.getNumberOfImmune() + "\n*P2PMembers: " + Simulation.getNumOfP2PMembers();
        }


        if ((Simulation.getNumberOfInfected() + Simulation.getNumberOfImmune()) == numOfVertices) {
            System.out.println("The whole susceptible population is Infected---Terminating");
            s = s + "\nThe whole susceptible population is Infected---Terminating\n";
            //Simulation.setSelfdestruct(true); //WARNINIG TEMPORARY FOR EXPERIMENTAL REASONS
        }
        return s;


    }


    static void calculateElapsedTime(long milliseconds) {
        /* value to break down into hours, minutes, seconds, milliseconds */
        long togo = milliseconds; /* milliseconds */

        int millis = (int) (togo % MILLISECONDS_PER_SECOND);
        /* /= is just shorthand for togo = togo / 1000 */
        togo /= MILLISECONDS_PER_SECOND;

        int seconds = (int) (togo % SECONDS_PER_MINUTE);
        togo /= SECONDS_PER_MINUTE;

        int minutes = (int) (togo % MINUTES_PER_HOUR);
        togo /= MINUTES_PER_HOUR;

        int hours = (int) (togo % HOURS_PER_DAY);
        int days = (int) (togo / HOURS_PER_DAY);

        System.out.println("Total computational time: " + days + "d  " + hours + "h  " + minutes + "m  " + seconds + "s  " + millis + "ms ");
    }


    /*
     *Checking method that counts total number of nodes with different states (infected, immune and susceptible)
     *and prints results on screen and on file (by returnig a string with the desirable info)
     */
    static String printStatistics() {

        String s = "\n";

        System.out.println("<----------------------------------------------------------------------------------------->");
        s = s + "<----------------------------------------------------------------------------------------->\n";
        System.out.println("Initial Infected population:   " + Simulation.getNumberOfInitialInfected());
        s = s + "Initial Infected population:   " + Simulation.getNumberOfInitialInfected() + "\n";
        System.out.println("Total number of  susceptible population:   " + Simulation.getNumberOfSusceptible());
        s = s + "Total number of  susceptible population:   " + Simulation.getNumberOfSusceptible() + "\n";
        System.out.println("Total number of  infected population:   " + Simulation.getNumberOfInfected());
        s = s + "Total number of  infected population:   " + Simulation.getNumberOfInfected() + "\n";
        System.out.println("Total number of  immune population:   " + Simulation.getNumberOfImmune());
        s = s + "Total number of  immune population:   " + Simulation.getNumberOfImmune() + "\n";
        System.out.println("Total number of  P2P members:   " + Simulation.getNumOfP2PMembers());
        s = s + "Total number of number of  P2P members:   " + Simulation.getNumOfP2PMembers() + "\n";
        System.out.println("Total population:  " + Simulation.getNumOfVertices());
        s = s + "Total population:  " + Simulation.getNumOfVertices() + "\n";
        System.out.println("<----------------------------------------------------------------------------------------->");
        s = s + "<----------------------------------------------------------------------------------------->\n";

        return s;

    }

    public static void decreaseNodeIncubation(Node n) {


        if (n.getIncubation() > 0) {
            n.decreaseIncubation();
        } else if (n.getIncubation() < 0) {
            System.err.println("Incubation below 0 - Error Code 2");
            System.exit(-1);
        }

    }

    
    /**
     *Decrease the incubation of all infected nodes by 1. 
     **/
    public static void decreaseAllIncubation(/*Graph generalGraph*/) {
        Node nodeO;
                        
        try {
            for (int o = 0; o < numOfVertices; o++) {
                        nodeO = generalGraph.getVertex(o);
                        if (nodeO.checkInfected()) decreaseNodeIncubation(nodeO);
                    }
        
        } catch (Exception e) {
            System.err.println("Decrease all incubation");
        }
    }

    public static void flashGRBuffer(/*Graph generalGraph*/) {
        Node nodeO;

        for (int i = 0; i < numOfVertices; i++) {
                        nodeO = generalGraph.getVertex(i);
                        nodeO.flushRateBuffer();
         }
    }


     /**
     *Calculate the average Local malicious activity. Each node calculates its local malicious activity rate even
      * if it isn't a P2P member
     **/
    public static double calculateLocalAverageRate(/*Graph generalGraph,*/ int index) {
        Node nodeO;
        double averageLocal = 0;


        for (int o = 0; o < numOfVertices; o++) {
                    nodeO = generalGraph.getVertex(o);
                    if (!nodeO.checkInfected() && (nodeO.calculateLRate(index) != -666)) {
                        averageLocal += nodeO.calculateLRate(index);

                        if (nodeO.calculateLRate(index) < 0) {
                            System.out.print("\t" + nodeO.calculateLRate(index) + "index:= " + index + "\t\t");
                            System.exit(-1);
                        }
                        //System.out.println("\t" + nodeO.calculateLRate(index));
                    } //else System.out.print("\t" + nodeO.calculateLRate(index) + "index:= " + index + "\t\t");
                    //System.out.println("$$%%%%%%%%%%%%%%%%%%%%%%%%\tindex= " + index + "****************************&&&&&&&&&&&&&&&&&&&&");
        }
        if (numOfVertices == Simulation.getNumberOfInfected()) return 0;
        averageLocal = averageLocal / (numOfVertices - Simulation.getNumberOfInfected());
        return averageLocal;
    }

     /**
     *Calculate the average Local malicious activity, but has to be a P2P member
     **/
    public static double calculateGlobalAverageRate(/*Graph generalGraph*/) {
        Node nodeO;
        double averageGlobal = 0;
        int activeNodes = 0;


        for (int o = 0; o < numOfVertices; o++) {
                    nodeO = generalGraph.getVertex(o);
                    if ((!nodeO.checkInfected()) && (nodeO.getP2PMember())) {
                        averageGlobal += nodeO.calculateGRate();
                        System.out.println("\tnode " + nodeO.calculateGRate() + "\taverageGlobal " + averageGlobal);
                        activeNodes++;
                    }
        }
        System.out.println("!!! Average Global:= " + averageGlobal + "\t ActiveNodes:= " + activeNodes + " !!!");
        if (numOfVertices == Simulation.getNumberOfInfected()) return 0;
        averageGlobal = averageGlobal / activeNodes;
        return averageGlobal;
    }

    public static void promisSL(/*Graph generalGraph*/) {
        Node nodeO;
        level1 = 0;
        level2 = 0;
        level3 = 0;
        level4 = 0;
        level5 = 0;
        int currentLevel;

        for (int o = 0; o < numOfVertices; o++) {
            nodeO = generalGraph.getVertex(o);
            if (!nodeO.checkInfected() && (nodeO.getP2PMember()) ) {
                currentLevel = nodeO.securityLevel;
                //System.out.println("`````````````Node " + o + "\tSecurity Level " + nodeO.securityLevel);
                switch (currentLevel) {
                    case 1 : level1++; break;
                    case 2 : level2++; break;
                    case 3 : level3++; break;
                    case 4 : level4++; break;
                    case 5 : level5++; break;
                    default : System.out.println("Unknown security level <--> Aborting"); System.exit(-1);
                }
            }
        }
        System.out.println("///////////////////////////////////////////////////////////////////////////");
        System.out.println("L1 Nodes: " + level1 + "\tL2 Nodes: " + level2 + "\tL3 Nodes: " + level3 + "\tL4 Nodes: " + level4 + "\tL5 Nodes: " + level5);
        System.out.println("///////////////////////////////////////////////////////////////////////////");
    }
    
    public static void writeData(String randomFileName, int k,  double grate, long startTimer, double global, double si/*, Graph generalGraph*/) {
        double thirdRate;
        double secondLastRate = 0;
        
        int NumOfVertices = Simulation.getNumOfVertices();
        int initInfected = Simulation.getNumberOfInitialInfected();
        float infRate;
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        long intermediate = System.currentTimeMillis();
        long duration = intermediate - startTimer;


        nf.setMaximumFractionDigits(5);
        nf.setMinimumFractionDigits(5);
        nf.setGroupingUsed(false);
        
        //Initializes variables for printing results on file
        try {
            
            verifyResultsFileName = outputsFilePath + "verification/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "VerifyResults.txt";
            FileWriter verifyResultsFile = new FileWriter(verifyResultsFileName, true);

            statisticsFileName = outputsFilePath + "statistics/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "PrintStatistcs.txt";
            FileWriter statisticsFile = new FileWriter(statisticsFileName, true);

            plotFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "Gplot.txt";
            FileWriter plotFile = new FileWriter(plotFileName, true);
            
            secondPlotFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "Gplot2.txt";
            FileWriter secondPlotFile = new FileWriter(secondPlotFileName, true);
            
            thirdPlotFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "GRate.txt";
            FileWriter thirdPlotFile = new FileWriter(thirdPlotFileName, true);

            splotFileName = outputsFilePath + "/splots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "Splot.txt";
            FileWriter splotFile = new FileWriter(splotFileName, true);

            rateFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "LRate.txt";
            FileWriter rateFile = new FileWriter(rateFileName, true);

            siRateFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "SI.txt";
            FileWriter siFile = new FileWriter(siRateFileName, true);

            p2pFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "P2P.txt";
            FileWriter p2pFile = new FileWriter(p2pFileName, true);

            //successFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "success.txt";
            //FileWriter successFile = new FileWriter(successFileName);


            
            
            infRate = ((float) Simulation.getNumberOfInfected() / NumOfVertices);
            //second differential equation
            if (lastRate !=0 ) {
                secondRate = (infRate - lastRate);
            } else secondRate = 0;
            //third differential equation
            thirdRate = (infRate * Simulation.getNumOfVertices()) - (lastRate);
            lastRate = infRate;
            secondLastRate = secondRate;
            
            verifyResultsFile.write("\nSimulation Cycle: " + k + Simulation.verifyResults(/*generalGraph*/));
            statisticsFile.write("\nSimulation Cycle: " + k + Simulation.printStatistics());
            plotFile.write(k + "\t" + nf.format(infRate) +  "\n");
            secondPlotFile.write(k + "\t" + nf.format(secondRate) +  "\n");
            thirdPlotFile.write(k + "\t" + nf.format(global) +  "\n");
            splotFile.write(k + "\t" + nf.format(infRate) + "\t" + (duration/1000) + "\n");
            rateFile.write(k + "\t" + nf.format(grate) +  "\n");
            siFile.write(k + "\t" + nf.format(si) + "\n");
            p2pFile.write(k + "\t" + level1 + "\t" + level2 + "\t" + level3 + "\t" + level4 + "\t" + level5 + "\t\n");

            statisticsFile.close();
            plotFile.close();
            secondPlotFile.close();
            thirdPlotFile.close();
            splotFile.close();
            rateFile.close();
            siFile.close();
            p2pFile.close();
       } catch (IOException ioe) {
            System.err.println("Misbehavior of data files");
        }
                
    }
    
    
    /*
     *The main SI simulation model
     */
    public static void simulationEngine(/*Graph generalGraph*/) {
        try {

            Node nodeI;
            Node nodeJ = null;
            Node nodeT = generalGraph.getVertex(999);

            double vita;
            double randomSample;
            double globalRate = 0;
            //double totalRate = 0;
            double tempRate;
            double siRate = 0;

            long startTimer;
            long stoppedTimer;
            long duration;
            long intermediate;

            int numOfIterations;
            int vectorPos;     //Is for LOCAL intercepted malicious activity ONLY
            int vectorSize;   //Is for LOCAL intercepted malicious activity ONLY
            int BufferPos;    //Is for GLOBAL intercepted malicious activity ONLY
            int BufferSize;   //Is for GLOBAL intercepted malicious activity ONLY
            int NumOfVertices = Simulation.getNumOfVertices();
            int initInfected;
            int discretization = 1;
            int cycle = 0;
            int tempDiscr = -1;
            int x = (int) (100 * Math.random());


            String randomFileName = String.valueOf(x);


            vita = Simulation.getVita();
            numOfIterations = Simulation.getMaxIterationNumber();

            initInfected = Simulation.getNumberOfInitialInfected();

            Simulation.getNumberOfSusceptible();
            Simulation.setNumberOfSusceptible((numOfVertices - (initInfected + Simulation.getNumberOfImmune())));

            /*
             *Initializes variables for printing results on file
             **/
            verifyResultsFileName = outputsFilePath + "verification/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "VerifyResults.txt";
            FileWriter verifyResultsFile = new FileWriter(verifyResultsFileName);

            statisticsFileName = outputsFilePath + "statistics/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "PrintStatistcs.txt";
            FileWriter statisticsFile = new FileWriter(statisticsFileName);

            plotFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "Gplot.txt";
            FileWriter plotFile = new FileWriter(plotFileName);

            secondPlotFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "Gplot2.txt";
            FileWriter secondPlotFile = new FileWriter(secondPlotFileName);
            
            thirdPlotFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "GRate.txt";
            FileWriter thirdPlotFile = new FileWriter(thirdPlotFileName);
            
            rateFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "LRate.txt";
            FileWriter rateFile = new FileWriter(rateFileName);

            splotFileName = outputsFilePath + "/splots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "Splot.txt";
            FileWriter splotFile = new FileWriter(splotFileName);

            siRateFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "SI.txt";
            FileWriter siFile = new FileWriter(siRateFileName);

            p2pFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "P2P.txt";
            FileWriter p2pFile = new FileWriter(p2pFileName);

            //successFileName = outputsFilePath + "/plots/" + NumOfVertices + "N" + initInfected + "p" + randomFileName + "success.txt";
            //FileWriter successFile = new FileWriter(successFileName);


            startTimer = System.currentTimeMillis();

            writeData(randomFileName, cycle, averageRate, startTimer, globalRate, siRate/*, generalGraph*/);


            for (int k = 0; k < (numOfIterations * discretization); k++) {
                verifyResultsFile = new FileWriter(verifyResultsFileName, true);
                statisticsFile = new FileWriter(statisticsFileName, true);
                plotFile = new FileWriter(plotFileName, true);
                p2pFile = new FileWriter(p2pFileName, true);
                tempDiscr++;
                cycle = k / discretization;
                System.out.println("Simulation Cycle: " + cycle);
                tempRate = 0;

                for (int i = 0; i < NumOfVertices; i++) {
                    nodeI = generalGraph.getVertex(i);
                    if ((nodeI.checkInfected()) && (nodeI.getIncubation() == 0)) {
                        for (int j = 0; j < NumOfVertices; j++) {
                            nodeJ = generalGraph.getVertex(j);
                            if (generalGraph.isAdjacent(nodeI, nodeJ)) {
                                randomSample = rgenerator.nextDouble();
                                if ((randomSample <=  ((vita / discretization)/*/(nodeJ.securityLevel)*/)) && (!nodeJ.checkInfected()) && (!nodeJ.checkImmune())) {
                                    nodeJ.setInfected();
                                    Simulation.decreaseNumberOfSusceptible();
                                    Simulation.increaseNumberOfInfected();
                                    nodeJ.setIncubation(1);
                                    System.out.println("Infection Succeded from Node " + i + " to Node " + j);
                                } else {
                                    vectorSize = nodeJ.getVectorSize();
                                    vectorPos = ((cycle) % vectorSize);
                                    //if fails to infect then log the attack
                                        if (!nodeJ.checkInfected()) {
                                            if (tempDiscr == discretization) {
                                                //We use the massHits to minimize problems with discretization.
                                                nodeJ.logAttack(vectorPos, nodeJ.massHits);
                                                nodeJ.massHits = 1;
                                            } else nodeJ.massHits++;
                                    }
                                }
                            }
                        }
                    } else {
                        if (nodeI.getP2PMember() && (!nodeI.checkInfected())) {
                            for (int j = 0; j < NumOfVertices; j++) {
                                nodeJ = generalGraph.getVertex(j);
                                if ((generalGraph.isAdjacent(nodeI, nodeJ)) && (nodeJ.getP2PMember()) && (!nodeJ.checkInfected()))  {
                                    vectorSize = nodeJ.getVectorSize();
                                    vectorPos = ((cycle) % vectorSize);
                                    if (tempDiscr == discretization) {
                                        tempRate = nodeI.calculateLRate(vectorPos);
                                        if (tempRate != -666) nodeJ.logRate(tempRate);
                                    }
                                }
                            }
                        }
                    }
                }

                //DEBUG
                if (!nodeT.checkImmune()) {
                    nodeT.setImmune();
                    Simulation.decreaseNumberOfSusceptible();
                    Simulation.increaseNumberOfImmune();
                }

                if (nodeT.checkInfected()) {
                    System.out.println("Observer Infected - Terminating");
                    System.exit(-1);
                }

                if (!nodeT.getP2PMember()) {
                   nodeT.setP2PMember();
                   Simulation.increaseP2PMembers();
                   System.out.println("Observer turned to a  P2P Member");
                   //System.exit(-1);
                }
                vectorSize = nodeT.getVectorSize();
                vectorPos = ((cycle) % vectorSize);

                averageRate = calculateLocalAverageRate(vectorPos);
                if (tempDiscr == discretization) globalRate = calculateGlobalAverageRate();
                //globalRate = nodeT.calculateGRate();

                decreaseAllIncubation();

                siRate = ((((NumOfVertices - Simulation.getNumberOfImmune()) * Simulation.getNumberOfInitialInfected())/ (Simulation.getNumberOfInitialInfected() +
                        (NumOfVertices - (Simulation.getNumberOfInitialInfected()) +
                        Simulation.getNumberOfImmune()) * Math.exp(-vita * NumOfVertices * cycle))) / (NumOfVertices - Simulation.getNumberOfImmune()));
                //siRate = ((997/ ((100) + (997) * Math.exp(-vita * NumOfVertices * cycle))) /997 );
                System.out.println("NumOfVertices= " + NumOfVertices + "\tInitialInfected= " + Simulation.getNumberOfInitialInfected() + "\tvita:= " + vita);

                System.out.println("-------------------------------------------------------------------------------------");
                promisSL();

                intermediate = System.currentTimeMillis();
                duration = intermediate - startTimer;
                calculateElapsedTime(duration);

                System.out.println("//////Average Local RATE:= " + averageRate + "//////");
                System.out.println("######Global Rate:= " + globalRate +" ######'");

                if (tempDiscr == discretization) {
                    writeData(randomFileName, cycle, averageRate, startTimer, globalRate, siRate);
                    System.out.println("++++++++++++++++Data Files updated++++++++++++++++");
                    tempDiscr = 0;
                }
                
                
                Simulation.flashGRBuffer();
                
                
                System.out.println("Num of Infected= " + Simulation.getNumberOfInfected());
                System.out.println("Infection Rate=: " + (double) Simulation.getNumberOfInfected() / NumOfVertices);
                if (Simulation.getSelfdestruct()) {
                    System.out.println("PROMIS Simulator Finished");
                    System.exit(0);
                }

                if ((cycle > 10) && (averageRate == 0)) {
                    System.out.println("PROMIS Simulator Finished -- Average Rate " + averageRate);
                    //System.exit(0);
                }
            }

            stoppedTimer = System.currentTimeMillis();
            duration = stoppedTimer - startTimer;
            calculateElapsedTime(duration);

            statisticsFile.close();
            verifyResultsFile.close();
            plotFile.close();
            splotFile.close();
            secondPlotFile.close();
            thirdPlotFile.close();
            siFile.close();
            p2pFile.close();
            //successFile.close();
        } catch (IOException e) {
            System.out.println("I/O Exception " + e);
        }

    }




    /*
     *This method reads the graph that we created from BuildGraph class and returns it back to main
     */
    public static Graph getGraphTopology() throws IOException {
        String[] sa;
        /*Graph*/ generalGraph = new Graph();

        try {
            FileReader f;
            BufferedReader br;
            StringTokenizer st;
            String s;
            int vertex1;
            int vertex2;
            int NumOfVertices;


            f = new FileReader("../GraphTopology.txt");
            br = new BufferedReader(f);

            while ((s = br.readLine()) != null) {
                if (s.startsWith("#")) {
                    if (s.indexOf("Nodes") != -1) {
                        //Create the info file
                        sa = s.split(" ");
                        NumOfVertices = (Integer.parseInt(sa[1]));

                        Simulation.setNumOfVertices(NumOfVertices);
                        System.out.println("Number of vertices \t" + Simulation.getNumOfVertices());
                        generalGraph = new Graph(Simulation.getNumOfVertices());
                    } else if (s.indexOf("Class") != -1) {
                        sa = s.split(" ");
                        classType = sa[1];
                    }
                } else {
                    st = new StringTokenizer(s);
                    vertex1 = Integer.parseInt(st.nextToken());
                    while (st.hasMoreTokens()) {
                        vertex2 = Integer.parseInt(st.nextToken());
                        generalGraph.addEdge(vertex1, vertex2);
                    }
                }
            }


            System.out.println("EOF");
            f.close();
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find graph topology");
            System.exit(-1);
        }
        return generalGraph;
    }



    public static void main(String[] args) {
        String userInput2;
        String s;
        int m;
        int nodeIndex;
        int tempNode = -1;
        int lineCounter = 0;
        FileReader f;
        BufferedReader br;
        StringTokenizer st;
        Node nameOfNode;
        Date date;
        double v;
        int p2pNodes = 0;

        try {

            f = new FileReader("../bin/config.txt");
            br = new BufferedReader(f);
            /*Graph*/ generalGraph = getGraphTopology();

            System.out.println("Configuration file found - Parsing \n");
			System.out.println("<--Welcome to Promis(ing) Algorithm-->");
            while ((s = br.readLine()) != null) {
                ++lineCounter;
                st = new StringTokenizer(s);
                if (lineCounter == 1) {
                    seed = Integer.parseInt(st.nextToken());
                    if (seed == -1) {
                        date = new Date();
                        seed = date.getTime();
                        rgenerator = new Random(seed);
                    } else
                        rgenerator = new Random(seed);
                } else if (lineCounter == 2) {
                    v = (new Double(st.nextToken()));
                    Simulation.setVita(v);
                } else if (lineCounter == 3) {
                    m = (new Integer(st.nextToken()));
                    Simulation.setMaxIterationNumber(m);
                } else if (lineCounter == 4) {
                    userInput2 = st.nextToken();
                    if (userInput2.equals("auto")) {
                        randomVirusFeeder(/*generalGraph*/);
                    } else if (userInput2.equals("semi")) {
                        s = br.readLine();
                        ++lineCounter;
                        st = new StringTokenizer(s);
                        numOfInitialInfected = (new Integer(st.nextToken()));
                        while (br.readLine() != null) {
                            System.out.println("Skipping another Line");
                            ++lineCounter;
                            s = br.readLine();
                            System.out.println("s:= " + s);
                            if (s.equalsIgnoreCase("immune")) {
                                ++lineCounter;
                                s = br.readLine();
                                st = new StringTokenizer(s);
                                numOfInitialImmune = (new Integer(st.nextToken()));
                                //break;
                                continue;
                            }
                            if (s.equalsIgnoreCase("p2p")) {
                                ++lineCounter;
                                s = br.readLine();
                                st = new StringTokenizer(s);
                                p2pNodes = (new Integer(st.nextToken()));
                                break;
                            }
                        }
                        semiRandomVirusFeeder(/*generalGraph,*/ numOfInitialInfected, numOfInitialImmune, p2pNodes);
                    } else if (userInput2.equals("manual")) {
                        s = br.readLine();
                        ++lineCounter;
                        st = new StringTokenizer(s);
                        numOfInitialInfected = (new Integer(st.nextToken()));
                        s = br.readLine();
                        ++lineCounter;
                        sa = s.split(" ");
                        if (sa.length != numOfInitialInfected) {
                            System.out.println("Different number of preinfected nodes, than actually parsed");
                            System.out.println("Found: " + sa.length + " Expected: " + numOfInitialInfected);
                            System.out.println("Exiting");
                            System.exit(-1);
                        }

                        for (int i = 0; i < sa.length; i++) {
                            nodeIndex = new Integer(sa[i]);
                            if (tempNode >= nodeIndex) {
                                System.out.println("Arrange nodes in the correct order");
                                System.exit(-1);
                            }
                            tempNode = nodeIndex;
                            nameOfNode = generalGraph.getVertex(nodeIndex);
                            nameOfNode.setInfected();
                            Simulation.decreaseNumberOfSusceptible();
                            Simulation.increaseNumberOfInfected();
                            System.out.println("Preinfected node=:\t" + nodeIndex);
                        }
                        System.out.println("<----------------------------------------------------------------------->");

                        s = br.readLine();
                        ++lineCounter;
                        if (!s.equals("immune")) {
                            System.out.println("Waiting for immune command - Found: " + s);
                            System.exit(-1);
                        }
                        s = br.readLine();
                        ++lineCounter;
                        st = new StringTokenizer(s);
                        numOfInitialImmune = Integer.parseInt(st.nextToken());
                        s = br.readLine();
                        ++lineCounter;
                        sa = s.split(" ");
                        if (sa.length != numOfInitialImmune) {
                            System.out.println("Different number of immune nodes, than actually parsed");
                            System.out.println("Found: " + sa.length + " Expected: " + numOfInitialInfected);
                            System.out.println("Exiting");
                            System.exit(-1);
                        }

                         System.out.println();
                         tempNode = -1;
                         for (int i = 0; i < sa.length; i++) {
                            nodeIndex = Integer.parseInt(sa[i]);
                            if (tempNode >= nodeIndex) {
                                System.out.println("Arrange nodes in the correct order");
                                System.exit(-1);
                            }
                            tempNode = nodeIndex;
                            nameOfNode = generalGraph.getVertex(nodeIndex);
                            if (nameOfNode.checkInfected()) {
                                System.out.println("Node " + nodeIndex + " already infected");
                                System.out.println("Please update your configuration file and try again");
                                System.exit(-1);
                            }
                            nameOfNode.setImmune();
                            Simulation.decreaseNumberOfSusceptible();
                            Simulation.increaseNumberOfImmune();
                            System.out.println("Immunizing node=:\t" + nodeIndex);
                        }
                        System.out.println("<----------------------------------------------------------------------->");
                        s = br.readLine();
                        ++lineCounter;
                        if (!s.equalsIgnoreCase("p2p")) {
                            System.out.println("Waiting for p2p command - Found: " + st);
                            System.exit(-1);
                        }
                        System.out.println("Command found: " + s);
                        s = br.readLine();
                        ++lineCounter;
                        st = new StringTokenizer(s);
                        p2pNodes = Integer.parseInt(st.nextToken());
                        s = br.readLine();
                        ++lineCounter;
                        sa = s.split(" ");
                        if (sa.length != p2pNodes) {
                            System.out.println("Different number of p2p, than actually parsed");
                            System.out.println("Found: " + sa.length + " Expected: " + p2pNodes);
                            System.out.println("Exiting");
                            System.exit(-1);
                        }

                        System.out.println();
                        tempNode = -1;
                        for (int i = 0; i < sa.length; i++) {
                            nodeIndex = Integer.parseInt(sa[i]);
                            if (tempNode >= nodeIndex) {
                                System.out.println("Arrange nodes in the correct order");
                                System.exit(-1);
                            }
                            tempNode = nodeIndex;
                            nameOfNode = generalGraph.getVertex(nodeIndex);
                             if (nameOfNode.checkInfected()) {
                                System.out.println("Node " + nodeIndex + " already infected");
                                System.out.println("Please update your configuration file and try again");
                                System.exit(-1);
                            }
                            nameOfNode.setP2PMember();
                            Simulation.increaseP2PMembers();
                            System.out.println("P2P member node=:\t" + nodeIndex);
                        }
                    }
                }
            }

            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException ie) {
                System.out.println("Thread messed up");
            }
            simulationEngine(/*generalGraph*/);

        } catch (FileNotFoundException fnfe) {
            try {
                /*Graph*/ generalGraph = getGraphTopology();
                System.out.println("Configuration file not found");
                System.exit(-1);
            } catch (IOException ioe) {
                System.out.println("I/O Exception in FNF Exception");
            }
        } catch (IOException e) {
            System.err.println("I/O Exception" + e);
        }
    }
}

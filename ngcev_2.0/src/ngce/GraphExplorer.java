/**
 * @author Vasileios Vlachos<br>
 */

/*
Copyright (C) Vasileios Vlachos, Vassiliki Vouzi
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
with the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

	* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimers.
	* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimers in the documentation
and/or other materials provided with the distribution.
	* Neither the names of <Name of Development Group, Name of Institution>, nor
the names of its contributors may be used to endorse or promote products derived
from this Software without specific prior written permission.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE CONTRIBUTORS
OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS WITH THE SOFTWARE.
*/

//Try to replace each word with a number
import java.io.*;
import java.util.*;
import java.text.*;

/**
 * This program extracts the probability distribution function of the graph built and
 *  constructs the title for the gnuplot probability distribution function plot, containing 
 *  all the required information to regenerate the specific graph.<br>
 */
public class GraphExplorer {
	/**
	 * 	Extracts the probability distribution function of the graph built and
	 *  constructs the title for the gnuplot probability distribution function plot, containing 
	 *  all the required information to regenerate the specific graph.<br>
	 */
    public static void prostProcess() {
        FileReader rf;
        FileReader er;
        FileWriter fw;
        FileWriter info;
        FileWriter title;
        BufferedReader br;
        BufferedReader pbr;
        BufferedReader nbr ;
        String s;
        String sline;
        String type = null;
        String[] sa;
        String gnuTitle;
        String time;
        Module m;
        Vector<Module> v = new Vector<Module>();
        int[] appMatrix = null;
        int edges = -1;
        int seed = -1;
        int initial = -1;
        int mi = -1;
        int nodes = -1;
        int app = -1;
        int unconnected = -1;
        int totalConnections = -1;
        int neighbors =-1;
        int nu = -1;
        long id;
        float version = -1;
        float mu;
        float probability = -1;
        double prob = -1;
        boolean checkLattice = false;
        boolean latticeCorrupted = false;
        boolean found = false;
        Date date;
        long start = 0;
        long stop;
        long duration;
        GregorianCalendar calendar;
        SimpleDateFormat sdf;

        NumberFormat nf = NumberFormat.getNumberInstance();

        try {
            //Find the source file and create a proper BufferReader
            rf = new FileReader("../GraphTopology.txt");
            br = new BufferedReader(rf);
            info = new FileWriter("../other/info.txt");

            System.out.println("Start processing");
            System.out.println("**********************************");
            date = new Date();
            start = date.getTime();

            //Parse source file
            while ((s = br.readLine()) != null) {
                //System.out.println("Parsing line:= "+s);

                while (s.startsWith("#")) {

                    //Create the info file
                    info.write(s+"\n");
                    if (s.indexOf("Nodes") != -1) {
                      sa = s.split(" ");
                      nodes = new Integer(sa[1]).intValue();
                      System.out.println("Number of nodes: "+nodes);
                    } else if (s.indexOf("Class") != -1) {
                      sa = s.split(" ");
                      type = sa[1];
                      if (sa[1].equals("FixedGraph")) {
                            System.out.println("Checking Lattice");

                            s = br.readLine();
                            sa = s.split(" ");
                            neighbors  = new Integer(sa[1]).intValue();
                            System.out.println("Connectivity: "+neighbors);
                            checkLattice = true;
                            info.write(s+"\n");
                      }
                    }
                    if (s.indexOf("Seed") != -1) {
                        sa = s.split(" ");
                        seed = new Integer(sa[1]).intValue();
                    }
                    if (s.indexOf("Edges") != -1) {
                        sa = s.split(":= ");
                        edges = new Integer(sa[1]).intValue();
                    }
                    if (s.indexOf("Initial") != -1) {
                        sa = s.split(" ");
                        initial = new Integer(sa[1]).intValue();
                    }
                    if (s.indexOf("Version") != -1) {
                        sa = s.split(" ");
                        version = new Float(sa[1]).floatValue();
                    }
                    if (s.indexOf("Probability") != -1) {

                        //s.replace(",", ".");
                        sa = s.split(" ");
                        sa[1] = sa[1].replace(',', '.');
                        System.out.println("sa[1]:=" + s);
                        probability = new Float(sa[1]).floatValue();
                    }
                    if (s.indexOf("m:=") != -1) {
                        sa = s.split(" ");
                        mi = new Integer(sa[1]).intValue();
                    }
                    s = br.readLine();
                }
                info.close();

                //Initialize the occurances matrix for latter use
                appMatrix = new int[nodes];
                for (int i = 0; i < nodes; i++) {
                    appMatrix[i] = 0;
                }

                //Split each line to discrete nodes
                sa = s.split("\t");

                //parse and process only the left one
                m = new Module();
                m.moduleId = new Integer(sa[0]).intValue();
                //System.out.println("Checking node: " + m.moduleId);

                //add the first one node to the vector anyway
                if (v.isEmpty()) {
                    m.moduleSum = 0;     //HEISENBUG
                    v.add(m);
                }

                //Find if the corresponding has been already identified
                for (int j = 0; j < v.size(); j++) {
                    Module nModule = new Module();
                    nModule = (Module) v.elementAt(j);
                    //System.out.println("Comparing "+nModule.moduleName+" "+m.moduleName);
                    if  (nModule.moduleId == m.moduleId) {
                        v.remove(j);
                        nModule.moduleSum++;
                        found = true;
                        v.add(nModule);
                        break;
                    } else found = false;
                }

                if (!found) {
                    m.moduleSum = 1;
                    v.add(m);
                    found = false;
                }
            }
            //System.out.println();
            br.close();

            } catch (FileNotFoundException fnfe) {
                System.err.println("File Not Found!\nPlease check the path and the filename");
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                System.err.println("Input Output Exception");
                ioe.printStackTrace();
            }
            System.out.println("Finished pre-processing");

            /*
            * Find all unconnected nodes (e.g. with 0 connections)
            */
            unconnected = nodes - v.size();
            appMatrix[0] = unconnected;

            for (int i = 1; i < nodes; i++) {
                app = 0;
                for (int k = 0; k < v.size(); k++) {
                    Module n = new Module();
                    n = (Module) v.elementAt(k);
                    //System.out.println("Node: " + n.moduleId + " Connections: " + n.moduleSum
                    //                   + " checked for " + i + "\tconnections");
                    if (n.moduleSum == i) {
                        app++;
                        totalConnections++;
                        appMatrix[i] = app;
                        //System.out.println("True");
                    }
                 }
            }

            System.out.println("\n");
            System.out.println();

            //Calculate the probabilities.
            nf.format(prob);
            nf.setMinimumFractionDigits(5);
            nf.setMaximumFractionDigits(5);

            System.out.println("Start post-processing");
            try {
                rf = new FileReader("../GraphTopology.txt");
                pbr = new BufferedReader(rf);
                fw = new FileWriter("../other/NGCE_Output");
                title = new FileWriter("../other/plotGraph.plt");

                while ((s = pbr.readLine()) != null) {
                //System.out.println("Parsing line:= "+s);
                    while (s.startsWith("#")) {
                        fw.write(s+"\n");
                        s = pbr.readLine();
                    }
                }

                for (int i = (nodes -1) ; i >= 0; i--) {
                    if (appMatrix[i] > 0) {
                        prob = ((double) appMatrix[i] / (double) nodes);
                        System.out.println("connections: " + i + "\tnodes: " + appMatrix[i]
                                           + "\tprobability: " + nf.format(prob) );
                        sline = i + "\t" + (nf.format(prob)).replace(',', '.') + "\n";
                        fw.write(sline);
                        if ((i != neighbors) && (checkLattice == true)) {
                            latticeCorrupted = true;
                        }
                    }
                }


                //Print the parsed values
                System.out.println();
                System.out.println("Graph type: " + type);
                if (nodes != -1) {
                    System.out.println("Nodes: " + nodes);
                }
                if (edges != -1) {
                    System.out.println("Edges: " + edges);
                }
                if (mi != -1) {
                    System.out.println("m: " + mi);
                }
                if (initial != -1) {
                    System.out.println("Initial " + initial);
                }
                if (neighbors != -1) {
                    System.out.println("Connectivity: " + neighbors);
                }
                if (probability != -1) {
                    System.out.println("Probability: " + probability);
                }
                if (seed != -1) {
                    System.out.println("Seed: " + seed);
                }
                if (version != -1) {
                    System.out.println("Version: " + version);
                }

                //Construct the GNUPlot file
                sdf = new SimpleDateFormat("dd.MM.yy");
                time = sdf.format(new java.util.Date()).toString();
                //System.out.println(sdf.format(new java.util.Date()));

                //create a seperate id for each file
                calendar = new GregorianCalendar();
                date = new Date();
                calendar.setTime(date);
                id = calendar.getTimeInMillis();

                System.out.println("Date is " + time);

                gnuTitle = "graph:ngce?modeltype=" + type + "&nodes=" + nodes;
                //date.get

                if (type.equals("ERGraph")) {
                    title.write("set title \"" + gnuTitle + "&probability=" + probability + "&edges=" + edges +
                                "&seed=" + seed + "&version=" + version + "\"\n");
                    title.write("set ylabel \'Connectivity Probability\'\n");
                    title.write("set xlabel \'Nodes\' \n");
                    title.write("set term postscript eps\n");
                    title.write("set output \"../Plots/" + id +".eps\"\n");
                    fw.close();
                    er = new FileReader("../other/NGCE_Output");
                    nbr = new BufferedReader(er);
                    while ((s = nbr.readLine()) != null) {
                        if (s.startsWith("#")) {
                            s = nbr.readLine();
                        } else {
                            sa = s.split("\t");
                            nu = new Integer(sa[0]).intValue();
                            System.out.println("nu:= " + nu);
                            break;
                        }
                    }

                    mu = probability * nodes * 2;
                    title.write("mu= " + mu + "; n= " + nu + "; set samples n+1; set xrange [0:n];\n");
                    title.write("i(x)=int(x+.1)\n");
                    title.write("Poisson(x)=mu**x*exp(-mu)/i(x)!\n");
                    title.write("set ylabel \'Connectivity Probability\'\n");
                    title.write("set xlabel \'Nodes\' \n");
                    title.write("plot \"NGCE_Output\", Poisson(x)\n");
                    title.write("set term x11\n");
                    title.write("plot \"NGCE_Output\", Poisson(x)\n");

                    title.write("pause 15 \"Hit return to continue\"");

                } else if (type.equals("Homogenous")) {
                    title.write("set title \"" + gnuTitle + "&version=" + version + "\"\n");
                    title.write("set ylabel \'Connectivity Probability\'\n");
                    title.write("set xlabel \'Nodes\' \n");
                    title.write("set term postscript eps\n");
                    title.write("set output \"../Plots/" + id +".eps\"\n");
                    title.write("plot \"NGCE_Output\"\n");
                    title.write("set term x11\n");
                    title.write("plot \"NGCE_Output\"\n");

                    title.write("pause 15 \"Hit return to continue\"");

                    //echo "set title \"$string&version=$version\"" > plotGraph.plt

                } else if (type.equals("FixedGraph")) {
                    title.write("set title \"" + gnuTitle + "&neighbours=" + neighbors + "&seed=" + seed +
                                "&version=" + version + "\"\n");
                    title.write("set ylabel \'Connectivity Probability\'\n");
                    title.write("set xlabel \'Nodes\' \n");
                    title.write("set term postscript eps\n");
                    title.write("set output \"../Plots/" + id +".eps\"\n");
                    title.write("plot \"NGCE_Output\"\n");
                    title.write("set term x11\n");
                    title.write("plot \"NGCE_Output\"\n");

                    title.write("pause 15 \"Hit return to continue\"");

                } else if (type.equals("ScaleFreeGraph")) {
                    title.write("set title \"" + gnuTitle + "&edges=" + edges + "&seed=" + seed +
                                "&version=" + version + "\"\n");
                    title.write("set ylabel \'Connectivity Probability\'\n");
                    title.write("set xlabel \'Nodes\' \n");
                    title.write("set term postscript eps\n");
                    title.write("set output \"../Plots/" + id +".eps\"\n");
                    title.write("set logscale\n");
                    title.write("plot \"NGCE_Output\"\n");
                    title.write("set term x11\n");
                    title.write("plot \"NGCE_Output\"\n");

                    title.write("pause 15 \"Hit return to continue\"");

                } else if (type.equals("FullScaleFreeGraph")) {
                    title.write("set title \"" + gnuTitle + "&initialNodes=" + initial + "&m=" + mi
                                + "&seed=" + seed + "&version=" + version + "\"\n");
                    title.write("set term postscript eps\n");
                    title.write("set output \"../Plots/" + id +".eps\"\n");
                    title.write("set logscale\n");
                    title.write("set ylabel \'Connectivity Probability\'\n");
                    title.write("set xlabel \'Nodes\' \n");

                    title.write("plot \"NGCE_Output\"\n");
                    title.write("set term x11\n");
                    title.write("plot \"NGCE_Output\"\n");

                    title.write("pause 15 \"Hit return to continue\"");

                } else {
                    System.out.println("Unknown Graph - Error");
                    System.exit(-1);
                }

                stop = date.getTime();
                duration = stop - start;
                title.write("#Analysis Completed in: " + (double) (duration) / 1000 + " seconds");
                pbr.close();
                fw.close();
                title.close();

                if (latticeCorrupted == true) {
                    System.err.println("Lattice corrupted, please try again with a different seed");
                }
            } catch (IOException ioe) {
                System.err.println("Input/Output Exception");
                ioe.printStackTrace();
            }

    }
	/**
	 * Main method is the method used for calling GraphExplorer.class.
	*/
    public static void main(String[] args) {
        GraphExplorer.prostProcess();
    }
}

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

import java.io.*;
import java.util.Date;

/**
 * This program builts the necessary .NET files which are used as input to the Pajek 
 * application, in order to visualize the structure of the graph built.<br>
 */
public class PajekOut {
	/**
	 * This method prints the necessary .NET files which are used as input to the Pajek 
 	 * application, in order to visualize the structure of the graph built.
	 */
    public static void convert() {
        FileReader rf;
        FileWriter fw;
        BufferedReader br;
        String s;
        String[] sa = null;
        Date date;
        int nodes = -1;
        int vertex;
        long start;
        long stop;
        long duration;

        System.out.println("Start processing");
        System.out.println("**********************************");
        date = new Date();
        start = date.getTime();

        try {
            rf = new FileReader("../GraphTopology.txt");
            br = new BufferedReader(rf);

            fw = new FileWriter("../PGraph.net");

            System.out.println("Converting to Pajek compatible format\nPlease be patient");
            while ((s = br.readLine()) != null) {
                //System.out.println("Parsing line:= "+s);
                if (s.startsWith("#")) {
                    if (s.indexOf("Nodes") != -1) {
                        //Create the info file
                        sa = s.split(" ");
                        nodes = (new Integer(sa[1]).intValue());
                        //System.out.println("Number of nodes: " + nodes);
                        fw.write("*Vertices " + nodes + "\n");
                        fw.write("*Edges\n");
                    }
                } else {
                    sa = s.split("\\p{Space}");
                    if (new Integer(sa[0]).intValue() == 0) {
                        sa[0] = "" + nodes;
                        fw.write(sa[0] + " " + sa[1] + "\n");
                    } else if (new Integer(sa[1]).intValue() == 0) {
                        sa[1] = "" + nodes;
                        fw.write(sa[0] + " " + sa[1] + "\n");
                    } else {
                        fw.write(sa[0] + " " + sa[1] + "\n");
                    }
                }
                //System.out.println("" + sa[0] + "\t" + sa[1] + "\n");
            }
            stop = date.getTime();
            duration = stop - start;
            System.out.println("File transformed in " + (double) (duration / 1000));
            fw.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println("File Not Found Exception");
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            System.err.println("Input/Output Exception");
            ioe.printStackTrace();
        }
    }

	/**
	 * Main method is the method used for calling PajekOut.class.
	 */
    public static void main(String[] args) {
        PajekOut.convert();
    }
}

//Try to replace each word with a number
import java.io.*;
import java.util.*;
import java.text.*;

public class Explorer {

    public static void main(String args[]) {
        FileReader rf;
        FileWriter fw;
        BufferedReader br;
        String s,sa[];
        Double parsedData;
        double in,out;
        Module m;
        int lines=0;
        Vector v = new Vector();
        boolean found=false;
        Formatter formatter;

        long fileLength;

        try {

        	if (args.length != 2) {
					System.out.println("Usage: java Explorer <source directory> <source file>");
					System.exit(-1);
			}
            //Find the source file and create a proper BufferReader    :/home/vbill/Code/Promis/other/sf1thres
            rf = new FileReader("//home/vsvlachos/Code/Promis/other/" + args[0] + "/" + args[1]);
            br = new BufferedReader(rf);
			formatter = new Formatter();

            /*// Get default locale
			Locale locale = Locale.getDefault();

			// Set the default locale to pre-defined locale
			Locale.setDefault(Locale.ENGLISH);

			// Set the default locale to custom locale
			locale = new Locale("el", "GR");
			Locale.setDefault(locale);

			NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

            //NumberFormat nf = NumberFormat.getInstance();*/

            //Parse source file
            while ((s = br.readLine()) != null) {
                System.out.println("Parsing line:= " + s);

                //Split each line to discrete strings
                sa=s.split("\t");
                //System.out.println(sa[0]);
                //System.out.println(s);
                if (sa.length != 2) {
                    System.out.println("Error in data file: " + s + "sa.length:= " + sa.length);
                    System.exit(-1);
                }


                System.out.println(sa[0]);
                m = new Module();
                parsedData = Double.valueOf(sa[0]);
                in = parsedData.doubleValue();
                m.moduleInput = in;

                parsedData = Double.valueOf(sa[1]);
                out = parsedData.doubleValue();
                m.moduleOutput = out;

                //Add the first element anyway - temporary solution
                if (v.isEmpty()) {
                    m.counter++;
                    v.add(m);
                    continue;
                }

                //Find if the corresponding has been already identified
                for (int j=0; j < v.size(); j++) {
                    Module nModule =new Module();
                    nModule=(Module)v.elementAt(j);
                    //System.out.println("Comparing "+nModule.moduleName+" "+m.moduleName);
                    if  (nModule.moduleInput == m.moduleInput) {
                        System.out.println("Module Input already identified: " + nModule.moduleInput);
                        found = true;
                        nModule.counter++;
                        nModule.moduleOutput += m.moduleOutput;
                        break;
                    } else found = false;
                }
                if (!found) {
                    //System.out.println("New module identified:= "+m.moduleName);
                    m.counter++;
                    System.out.println("New module identified:= " + m.moduleInput + " ->Setting counter to " + m.counter);
                    v.add(m);
                    found = false;
                    //System.out.println("string["+i+"]: "+m.moduleName+" key["+i+"]: "+m.moduleKey);
                }
            }
            //System.out.println();
            lines++;

            System.out.println("Finished pre-processing");
            for (int k=0; k < v.size(); k++) {
                    Module n =new Module();
                    n=(Module)v.elementAt(k);
                    n.mean = n.moduleOutput / n.counter;
                    System.out.println("Input: " + n.moduleInput + " Total Output: " + n.moduleOutput + " Measurements: "
                            + n.counter + " Mean: "  + n.mean);
             }

            System.out.println("Total lines:= "+lines);
            br.close();

            //Starting post processing

            System.out.println("Start post-processing");
            fw = new FileWriter("//home/vsvlachos/Code/Promis/other/hm1thres/java.dat");
            System.out.println("Finished pre-processing");
            for (int k=0; k < v.size(); k++) {
                    Module n =new Module();
                    n=(Module)v.elementAt(k);
                    fw.write(n.moduleInput + "\t" + n.mean + "\n");
             }
            fw.close();
            System.out.println("Program terminated normally");



        } catch (FileNotFoundException fnf) {System.out.println(fnf);}
            catch (IOException ioe) {ioe.printStackTrace();}
    }
}

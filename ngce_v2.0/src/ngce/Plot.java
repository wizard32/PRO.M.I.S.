/** 
 * @author Vassiliki Vouzi<br>
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
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
/**
 * Class Plot plots the post-processing results (distribution plots).It is called only
 * from the GUI of NGCE tool (NGCEframe.java).<br>
 * IT IS A BETA VERSION.
 * 
 */
public class Plot extends JPanel {

		protected static final int windowX=520;
		protected static final int windowY=440;

		protected static Vector<Integer> verticeNo=new Vector<Integer>();
		protected static Vector<Double> prob=new Vector<Double>();
		protected static String s;
		protected static String so[];
		protected static String type = null;
		protected static int seed = -1;
		protected static int initial = -1;
		protected static int mi = -1;
		protected static int nodes = -1;
		protected static int app = -1;
		protected static int unconnected = -1;
		protected static int totalConnections = -1;
		protected static int neighbors =-1;
		protected static int nu = -1;
		protected static int edges = -1;		
		protected static long id;
		protected static float version = -1;
		protected static float mu;
		protected static float probability = -1;
		protected static float maxY = -1;
		protected static float maxX=-1;
		protected static float minX=Long.MAX_VALUE;
		protected static float minY = Long.MAX_VALUE;
		protected static boolean checkLattice = false;
		protected static boolean latticeCorrupted = false;
	//	protected static Vector Poisson=new Vector();
		protected static float[] Poisson;
		


		/**
		* Creates a new instance of Plot
		*/
	   public void paintComponent(Graphics comp) {

		   Graphics2D comp2D=(Graphics2D)comp;
		   comp2D.setColor(Color.white);
		   Rectangle2D.Float background=new Rectangle.Float(0F,0F,(float)getSize().width,(float)getSize().height);
		   comp2D.fill(background);
		   comp2D.setColor(Color.black);
		   BasicStroke pen=new BasicStroke(1F,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND);
		   comp2D.setStroke(pen);
		   Line2D.Float lnvert=new Line2D.Float(60F,40F,60F,380F);
		   Line2D.Float lnhorz=new Line2D.Float(60F,380F,440F,380F);
		   comp2D.draw(lnvert);
		   comp2D.draw(lnhorz);
		   comp2D.setFont( new Font("Serif", Font.BOLD, 15));
		   comp2D.drawString(type+"(nodes:="+Integer.toString(nodes)+")",20F,20F);
		   comp2D.setColor(Color.blue);		
		   comp2D.setFont( new Font("Dialog", Font.PLAIN, 11));
		   comp2D.drawString("Seed:="+seed,410F,30F);
		   comp2D.drawString("Edges:="+edges,410F,50F);
		   comp2D.drawString("Initial:="+initial,410F,70F);
		   //comp2D.drawString("Version:="+version,410F,90F);
		   comp2D.drawString("Probability:="+probability,410F,90F);
		   comp2D.drawString(Float.toString(minY),20F,380F);
		   comp2D.drawString(Float.toString(maxY),20F,50F);
		   comp2D.drawString(Float.toString((maxY-minY)/2),20F,210F);
		   comp2D.drawString(Float.toString(maxX),430F,400F);
		   comp2D.drawString(Float.toString((maxX-minX)/2),215F,400F);
		   comp2D.setColor(Color.red);
		   BasicStroke pointPen=new BasicStroke(3F,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		   comp2D.setStroke(pointPen);
		   float x=-1;
		   float y=-1;

		   for(int n=0;n<verticeNo.size();n++)
		   {

				if ((type.equals("ScaleFreeGraph"))||(type.equals("FullScaleFreeGraph")) )
				{
					x=(((((Integer) verticeNo.elementAt(n)).floatValue())*380)/maxX)/*(maxX-minX)*/;
					y=(float)(((Math.exp(((float)(((Double)prob.elementAt(n)).doubleValue()))))*340)/maxY)/*(maxY-minY)*/;
					Line2D.Float point=new Line2D.Float(x+60F,380F-y,x+61F,379F-y);
					comp2D.draw(point);
					comp2D.setStroke(pen);
					comp2D.setColor(Color.black);
					Line2D.Float xAxis=new Line2D.Float(x+60F,382F,x+60F,378F);
					Line2D.Float yAxis=new Line2D.Float(58F,380F-y,62F,380F-y);
					
					comp2D.draw(xAxis);
					comp2D.draw(yAxis);
					comp2D.setStroke(pointPen);
					comp2D.setColor(Color.red);
				}
				else
				{
					x=((((Integer) verticeNo.elementAt(n)).floatValue())*380)/(maxX-minX);
					y=(((float)(((Double)prob.elementAt(n)).doubleValue()))*340)/(maxY-minY);
					Line2D.Float point=new Line2D.Float(x+60F,380F-y,x+61F,379F-y);
					comp2D.draw(point);
					comp2D.setStroke(pen);
					comp2D.setColor(Color.black);
					Line2D.Float xAxis=new Line2D.Float(x+60F,382F,x+60F,378F);
					Line2D.Float yAxis=new Line2D.Float(58F,380F-y,62F,380F-y);
					
				//	comp2D.drawString(Float.toString((float)((((Double)prob.elementAt(n)).doubleValue()))),20F,380F-y);
					
					comp2D.draw(xAxis);
					comp2D.draw(yAxis);
					comp2D.setStroke(pointPen);
					comp2D.setColor(Color.red);
				}
				
				//Line2D.Float point=new Line2D.Float(x+60F,y,x+61F,y);
				
				
		   }
		  
		   
		  
		   
			comp2D.setStroke(pen);
			comp2D.setColor(Color.black);
			float yA1=0,yA2=0,xA1=0,xA2=0;
			if(type.equals("ERGraph")) 
			{
		   		for (int i=0; i<(maxX);i++)
				{
					xA1=((i*380)/(maxX-minX));
					//yA1=((Float)Poisson.elementAt(i)).floatValue();
					yA1=((Poisson[i]*340)/(maxY-minY));
					xA2=(((i+1)*380)/(maxX-minX));
					//yA2=((Float)Poisson.elementAt(i+1)).floatValue();
					yA2=((Poisson[i+1]*340)/(maxY-minY));
					
					Line2D.Float PoissonDraw=new Line2D.Float(xA1+60F,380F-yA1,xA2+60F,380F-yA2);
					//Line2D.Float PoissonDraw=new Line2D.Float(xA1+60F,380F-(yA1*640F),xA2+60F,380F-(yA2*640F));
					comp2D.draw(PoissonDraw);
				}
			}
		
	   }

		protected  static void createAndShowPlot() {
		
			getPoints();
			JFrame.setDefaultLookAndFeelDecorated(true);

			/*Create and set up the window.*/

			JFrame frame = new JFrame("NGCE " + BuildGraph.version + " Athens University of Economics and Business" +

									  " --- Software Engineering and Security Group ");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


			/*Create and set up the content pane.*/
			JComponent newContentPane = new Plot();
			newContentPane.setOpaque(true);
			frame.setSize(windowX,windowY);
			frame.setContentPane(newContentPane);
			frame.setResizable(true);
			frame.setVisible(true);


		}

	protected static void getPoints(){

		try {
				//Find the source file and create a proper BufferReader				
				FileReader rf = new FileReader("../other/NGCE_Output");				
				BufferedReader br = new BufferedReader(rf);
				//StringTokenizer st = new StringTokenizer(s);
				int i=0;
				while ((s = br.readLine()) != null) {
					while (s.startsWith("#")) {

						 if (s.indexOf("Nodes") != -1) {
						 so = s.split(" ");
						 nodes = new Integer(so[1]).intValue();						
					   } else if (s.indexOf("Class") != -1) {
						 so = s.split(" ");
						 type = so[1];
						 if (so[1].equals("FixedGraph")) {							  

							   s = br.readLine();
							   so = s.split(" ");
							   neighbors  = new Integer(so[1]).intValue();						
							   checkLattice = true;
						 }
					   }
					   if (s.indexOf("Seed") != -1) {
						   so = s.split(" ");
						   seed = new Integer(so[1]).intValue();
					   }
					   if (s.indexOf("Edges") != -1) {
						   so = s.split(":= ");
						   edges = new Integer(so[1]).intValue();
					   }
					   if (s.indexOf("Initial") != -1) {
						   so = s.split(" ");
						   initial = new Integer(so[1]).intValue();
					   }
					   if (s.indexOf("Version") != -1) {
						   so = s.split(" ");
						   version = new Float(so[1]).floatValue();
					   }
					   if (s.indexOf("Probability") != -1) {

						   //s.replace(",", ".");
						   so = s.split(" ");
						   so[1] = so[1].replace(',', '.');
						   
						   probability = new Float(so[1]).floatValue();
					   }
					   if (s.indexOf("m:=") != -1) {
						   so = s.split(" ");
						   mi = new Integer(so[1]).intValue();
					   }
					   s = br.readLine();
				   }

					so = s.split("\t");

					verticeNo.add(i,new Integer(Integer.parseInt(so[0])));

					prob.add(i,new Double(Double.parseDouble(so[1])));
					//System.out.println(verticeNo.elementAt(i)+"\t"+prob.elementAt(i));
					i++;
					s = br.readLine();
				}
			
				for (int k=0;k<verticeNo.size();k++){
					maxX=Math.max(((Integer)verticeNo.elementAt(k)).intValue(),maxX);
					minX=Math.min(((Integer)verticeNo.elementAt(k)).intValue(),minX);
				}

				for (int k=0;k<prob.size();k++){
					if ((type.equals("ScaleFreeGraph"))||(type.equals("FullScaleFreeGraph")) )
					{
						maxY=Math.max((float)Math.exp(((Double)prob.elementAt(k)).floatValue()),maxY);
						minY=Math.min((float)Math.exp(((Double)prob.elementAt(k)).floatValue()),minY);
					}
					else{
						maxY=Math.max(((Double)prob.elementAt(k)).floatValue(),maxY);
						minY=Math.min(((Double)prob.elementAt(k)).floatValue(),minY);
					}
				}
				
				if(type.equals("ERGraph")) 
				{
					Poisson();
					
				}
				
				
				
			}
			catch(Exception eio){
				System.out.println("Problem:\t"+eio);
			}

	}
	public static void main(String [] args)
	{
		Plot.createAndShowPlot();
	}
	

	static int factorial (int n){
		if (n==0)
		{
			return (1);
		}
		else {
			return (n*factorial(n-1));
		} 
	
	}
	
	static void Poisson(){
		//float yPois=0;		   
		float lamda = probability * nodes*2;
		
		float previous=0;
		float klasma=0;
		int index=0;
		Poisson=new float[(int)maxX*2];
		
		Poisson[0]=(float)Math.exp(-lamda);
		float max=-1;
		//Poisson.add(0,new Float(Math.exp(-lamda)));
		//System.out.println("Poisson 0:=\t"+Poisson.elementAt(0));
		//index++;
		
		for(int i=1;i<(maxX+20);i++)//for(int i=1;i<(40000);i++)
		{
		   
		  // temp=factorial((int)i);
		   //System.out.println(((Math.exp(-lamda)*Math.pow(lamda,i)))+"\t"+temp);
		  
		   //previous=((Float)Poisson.elementAt(i-1)).floatValue();
		   klasma=(lamda/(i+1));
		   //System.out.println("Poisson "+i+":=\t"+klasma+"***"+previous);
		   
		   Poisson[i]=(Poisson[i-1]*klasma);
		  // Poisson.add(index,new Float(previous*klasma));
		   //Poisson.add(index,new Float((((Math.exp(-lamda)*Math.pow(lamda,i))/temp)*340)/maxY));
		  	max=Math.max(max,Poisson[i]);			
		   //index++;
		
		}
		//System.out.println(max);
	}
	
	
}

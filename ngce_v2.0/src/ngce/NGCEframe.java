/**
 * @author Vassiliki Vouzi<br>
 */

/*Copyright (C) 2004  Vassiliki Vouzi

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

/*
 * Last Modified on 30 June 2004
 *
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

/**
 * Class Ngfframe sets up the GUI Interface which opens a dialog for building graphs<br>
 */
public
class NGCEframe extends JPanel {

    private
    ImageIcon logo = new ImageIcon("../logo.jpg");

    /**
     * Initializes an empty Label componet.
     */
    private
    JLabel emptyLabel = new JLabel("\t");

    /**
     * Initializes form title componet.
     */
    private
    JLabel welcomeLabel = new JLabel("Network Graphs for Computer Epidemiologists", logo, JLabel.CENTER);

    /**
     * Initializes Label which prompts user to enter number of vertices.
     */
    private
    JLabel numofVerticesLabel = new JLabel("Enter number of vertices:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to choose type of graph model.
     */
    private
    JLabel modelTypeLabel = new JLabel("Choose Graph Type:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to enter graph degree parameter, which is used only for Graphs with fixed
     * connectivity.
     */
    private
    JLabel graphDegreeLabel = new JLabel("Graph Degree:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to enter number of edges parameter which is used for Custom Graphs.
     */
    private
    JLabel numOfEdgesLabel = new JLabel("Number of Edges:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to enter probability parameter used for Random Graphs.
     */
    private
    JLabel probLabel = new JLabel("Probability:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to enter seed parameter which is used for almost all graph types.
     */
    private
    JLabel seedLabel = new JLabel("Seed:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to enter Number of Initial Nodes which is used for Scale-Free graph type.
     */
    private
    JLabel numOfInitNodesLabel = new JLabel("Number of Initial Nodes:", JLabel.LEFT);

    /**
     * Initializes Label which prompts user to enter number of initial connections for each node parameter which is used
     * only for Scale-Free graph type.
     */
    private
    JLabel numOfInitConLabel = new JLabel("Initial Connections per Node:", JLabel.LEFT);

    /**
     * Initializes parameters title componet.
     */
    private
    JLabel parameters = new JLabel("PARAMETERS", JLabel.CENTER);

    /**
     * Initializes post-processing title componet.
     */
    private
    JLabel extras = new JLabel("POST PROCESSING OPTIONS", JLabel.CENTER);

    /**
     * Initializes Label which prompts user to enter classpath parameter which is used for pre-prepared graphs.
     */
    private
    JLabel classpathLabel = new JLabel("Classpath:", JLabel.LEFT);

    /**
     * Initializes Java's File Chooser component which is enabled for pre-prepared graphs.
     */
    private
    JFileChooser chooser = new JFileChooser();

    /**
     * This text field is used to enter the classpath of the pre-prepared graph file.
     */
    private
    JTextField classpathField;

    /**
     * This text field is used to enter the number of vertices used for all graph types.
     */
    private
    IntOnlyTextField vetrexNumber;

    /**
     * This text field is used to enter the number of neighbours parameter used for random graph with fixed
     * connectivity.
     */
    private
    IntOnlyTextField graphDegree;

    /**
     * This text field is used to enter the number of edges parameter used for Custom graph types
     */
    private
    IntOnlyTextField numOfEdgesTextField;

    /**
     * This text field is used to enter Number of Initial Nodes which is used for Scale-Free graph type.
     */
    private
    IntOnlyTextField numOfInitNodesTextField;

    /**
     * Initializes Label which prompts user to enter number of initial connections for each node parameter which is used
     * only for Scale-Free graph type.
     */
    private
    IntOnlyTextField numOfInitConTextField;

    /**
     * This text field is used to enter the seed parameter used for almost all graph types.
     */
    private
    IntOnlyTextField seedTextField;

    /**
     * This text field is used to enter the probability parameter used for random graph types, except random with fixed
     * connectivity.
     */
    private
    DoubleOnlyTextField probText;


    /**
     * In this radio button matrix all graph types are stored.
     */
    private
    JRadioButton[] graphModel = new JRadioButton[7];

    /**
     * Used to group graph types radio buttons.
     */
    private
    ButtonGroup group = new ButtonGroup();


    /**
     * Used to group custom graph types radio buttons.
     */
    private
    ButtonGroup customGroup = new ButtonGroup();

    /**
     * The check connectivity check box.Used only for random graphs with fixed connectivity.
     */
    private
    JCheckBox postProcs = new JCheckBox("Check Connectivity");

    /**
     * The incremental Growth check box.Used only for scale-free graphs.
     */
    private
    JCheckBox incrgrowth = new JCheckBox("Incremental Growth");

    /**
     * The preferential Attachement check box.Used only for scale-free graphs.
     */
    private
    JCheckBox prefattach = new JCheckBox("Preferential Attachement");

    /**
     * The incremental Growth check box.Used only for scale-free graphs.
     */
    private
    JCheckBox incrgrowth1 = new JCheckBox("Incremental Growth");

    /**
     * The preferential Attachement check box.Used only for scale-free graphs.
     */
    private
    JCheckBox prefattach1 = new JCheckBox("Preferential Attachement");


    /**
     * Used to print text messages and errors during program running.
     */
    private
    TextArea printResults = new TextArea("", 19, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);

    /**
     * This button component is used to start Java's File Chooser Component.
     */
    private
    JButton browseButton = new JButton("Browse");

    /**
     * This button component is used to start building the graph.
     */
    private
    JButton runButton = new JButton("GENERATE");

    /**
     * This button component is used to exit and close the NGCE gui interface and terminate the program.
     */
    private
    JButton exitButton = new JButton("EXIT");

    /**
     * This button component is used to start visualization shell scripts.
     */
    private static
    JButton graphButton = new JButton("Visualize");

    /**
     * This button component is used to start anlizing scripts to extract the preferred parameters.
     */
    private static
    JButton analyzeButton = new JButton(" Analyze ");

    /**
     * Initializes and the layout of the form.
     */
    private
    GridBagLayout gridbag = new GridBagLayout();

    /**
     * Initializes the constraints of the GridBagLayout of the form.
     */
    private
    GridBagConstraints constraints = new GridBagConstraints();


    /**
     * Initializes Menu Bar
     */
    private
    JMenuBar menubar = new JMenuBar();

    /**
     * Initializes Mail Menu
     */
    private
    JMenu menu = new JMenu("Menu");

    /**
     * Initializes Help Menu
     */
    private
    JMenu helpMenu = new JMenu("Help");

    /**
     * Initializes Help Menu Item
     */
    private
    JMenuItem help = new JMenuItem("Contact");

    /**
     * Initializes About Menu Item
     */
    private
    JMenuItem about = new JMenuItem("About");

    /**
     * Initializes Close Menu Item used to exit and close the NGCE gui interface and terminate the program.
     */
    private
    JMenuItem close = new JMenuItem("Close");

    /**
     * Used to temporary store the input of the number of vertices text field
     */
    private
    int numberOfVertices = 0;

    /**
     * Used to temporary store the input of the graph degree text field
     */
    private
    int degreeOfGraph = 0;

    /**
     * Used to temporary store the choice of graph model type.
     */
    private
    String typeOfModel;

    /**
     * Used to temporary store the input of the number of edges text field
     */
    private
    int edgesNumber = 0;

    /**
     * Used to temporary store the input of the probability text field
     */
    private
    double probability = 0;

    /**
     * Used to temporary store the input of the classpath text field
     */
    private
    String ppClasspath = null;

    /**
     * Used to temporary store the input of the number of initial nodes text field
     */
    private
    int initNodes = 0;

    /**
     * Used to temporary store the input of the number of initial connections text field
     */
    private
    int initCon = 0;

    /**
     * Used to count the line number of the form.
     */
    private
    int lineNum = 0;

    private
    int seed = 0;

    /**
     * Used during the checking of the system platform.Takes true value when the operating system is Linux and false
     * when the operating system is Windows.
     */
    private static
    boolean Linux = false;

    /**
     * Set title font type.
     */
    private
    Font titleFont = new Font("Dialog", Font.BOLD, 27);

    /**
     * Set prompting labels font type.
     */
    private
    Font promptFont = new Font("Dialog", Font.BOLD, 13);

    /**
     * Set probability label font type.
     */
    private
    Font probFont = new Font("Dialog", Font.ITALIC, 11);


    /**
     * Used to build the constraints for the GridBagLayout
     */
    protected
    void buildConstraints(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {

        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = wx;
        gbc.weighty = wy;
    }


    /**
     * Creates a new instance of Ngceframe
     */
    public
    NGCEframe() {
        super();

        /*Set Menu*/
        menu.add(about);
        menu.addSeparator();
        menu.add(close);
        helpMenu.add(help);
        menubar.add(menu);
        menubar.add(helpMenu);

        /*Initialize the Panel*/
        JPanel entryPanel = new JPanel(gridbag);


        /* Fix the title constraints*/
        buildConstraints(constraints, 0, lineNum, 4, 1, 100, 20);
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(welcomeLabel, constraints);
        welcomeLabel.setFont(titleFont);
        lineNum++;

        /* Fix the empty Label constraints*/
        buildConstraints(constraints, 0, lineNum, 4, 1, 100, 10);
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(emptyLabel, constraints);
        emptyLabel.setFont(titleFont);
        lineNum++;

        /*Fix the numofVertices Label  constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 40, 2);
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(numofVerticesLabel, constraints);
        numofVerticesLabel.setFont(promptFont);

        /*Fix the numofVertices text field  constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 0, 2);
        constraints.fill = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 10, 0, 10);
        vetrexNumber = new IntOnlyTextField(String.valueOf(BuildGraph.numOfVertices), 7);
        vetrexNumber.setToolTipText("Enter the number of vertices of the graph");
        gridbag.setConstraints(vetrexNumber, constraints);
        //lineNum++;


        /*Fix the printResults text Area*/
        buildConstraints(constraints, 2, lineNum, 2, 13, 40, 2);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);
        gridbag.setConstraints(printResults, constraints);
        printResults.setEditable(false);
        lineNum++;


        /*Fix the model type label  constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 2);
        constraints.insets = new Insets(/*1*/0, 0, 2, 0);
        gridbag.setConstraints(modelTypeLabel, constraints);
        modelTypeLabel.setFont(promptFont);
        lineNum++;

        /*Add the above components to the window*/
        entryPanel.add(welcomeLabel);
        entryPanel.add(emptyLabel);
        entryPanel.add(numofVerticesLabel);
        entryPanel.add(printResults);
        entryPanel.add(vetrexNumber);
        entryPanel.add(modelTypeLabel);

        /*Initialize radio buttons*/
        graphModel[0] = new JRadioButton("Homogenous");
        graphModel[0].setToolTipText("Builds Homogenous Graphs");
        graphModel[1] = new JRadioButton("Random Fixed Graph");
        graphModel[1].setToolTipText("Builds Random Graphs with Fixed Connectinity ");
        graphModel[2] = new JRadioButton("Scale-free");
        graphModel[2].setToolTipText("Builds Scale-Free Graphs");
        graphModel[3] = new JRadioButton("ER Random Graph");
        graphModel[3].setToolTipText("Builds ER Random Graphs");
        graphModel[4] = new JRadioButton("Custom Random Graph");
        graphModel[4].setToolTipText("Builds Custom Random Graphs");
        graphModel[5] = new JRadioButton("Custom Scale-Free Graph");
        graphModel[5].setToolTipText("Builds Custom Scale-Free Graphs");
        graphModel[6] = new JRadioButton("Pre-Prepared Graph");
        graphModel[6].setToolTipText("Choose this Model if the Graph is already builded");


        /*Add radio buttons to a button group,fix their layout constraints
             *  and add them to the panel*/
        for (int i = 0; i < graphModel.length; i++) {

            buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
            constraints.insets = new Insets(0, 0, 0, 0);
            gridbag.setConstraints(graphModel[i], constraints);
            group.add(graphModel[i]);
            lineNum++;

            if ((typeOfModel != null) && (typeOfModel.equals(graphModel[i].getText()))) {
                graphModel[i].setSelected(true);
            }

            entryPanel.add(graphModel[i]);

            if (i == 2) {
                buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
                constraints.insets = new Insets(0, 20, 0, 0);
                gridbag.setConstraints(incrgrowth, constraints);
                entryPanel.add(incrgrowth);
                incrgrowth.setEnabled(false);
                lineNum++;

                buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
                constraints.insets = new Insets(0, 20, 0, 0);
                gridbag.setConstraints(prefattach, constraints);
                entryPanel.add(prefattach);
                prefattach.setEnabled(false);
                lineNum++;

            }
            if (i == 5) {
                buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
                constraints.insets = new Insets(0, 20, 0, 0);
                gridbag.setConstraints(incrgrowth1, constraints);
                entryPanel.add(incrgrowth1);
                incrgrowth1.setEnabled(false);
                lineNum++;

                buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
                constraints.insets = new Insets(0, 20, 0, 0);
                gridbag.setConstraints(prefattach1, constraints);
                entryPanel.add(prefattach1);
                prefattach1.setEnabled(false);
                lineNum++;
            }

        }



        /*Fix the parameters label  constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.insets = new Insets(10, 0, 2, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(parameters, constraints);
        parameters.setFont(promptFont);
        parameters.setForeground(Color.blue);


        /*Fix the EXTRAS label  constraints*/
        buildConstraints(constraints, 3, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(extras, constraints);
        extras.setFont(promptFont);
        extras.setForeground(Color.blue);
        lineNum++;

        /*Fix the graph degree Label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 0, 0);
        gridbag.setConstraints(graphDegreeLabel, constraints);
        graphDegreeLabel.setFont(promptFont);

        /*Fix postProcs radio button constraints and add it to the panel*/
        buildConstraints(constraints, 3, lineNum, 1, 1, 25, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        gridbag.setConstraints(postProcs, constraints);
        entryPanel.add(postProcs);

        /*Fix the graph degree Text field  constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        graphDegree = new IntOnlyTextField(String.valueOf(BuildGraph.neighbors), 7);
        graphDegree.setToolTipText("Enter number of edges you want each node to have(Used only for Fixed Random)");
        gridbag.setConstraints(graphDegree, constraints);
        lineNum++;

        /*Fix the degree Label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(numOfEdgesLabel, constraints);
        numOfEdgesLabel.setFont(promptFont);

        /*Fix analyze button constraints and add it to the panel*/
        buildConstraints(constraints, 3, lineNum, 1, 3, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(analyzeButton, constraints);

        /*Fix the degree Text field  constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        numOfEdgesTextField = new IntOnlyTextField(String.valueOf(BuildGraph.numOfEdges), 7);
        numOfEdgesTextField.setToolTipText("Enter number of edges you want each node to have(Used only for Custom Graphs)");
        gridbag.setConstraints(numOfEdgesTextField, constraints);
        ++lineNum;

        /*Fix number of initial nodes label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(numOfInitNodesLabel, constraints);
        numOfInitNodesLabel.setFont(promptFont);

        /*Fix number of initial nodes text field constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 20, 0);
        numOfInitNodesTextField = new IntOnlyTextField(String.valueOf(BuildGraph.numOfInitNodes), 7);
        numOfInitNodesTextField.setToolTipText("Enter Number of Initial Nodes for Scale-Free");
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(numOfInitNodesTextField, constraints);
        lineNum++;

        /*Fix graph button constraints and add it to the panel*/
        buildConstraints(constraints, 3, lineNum + 1, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(graphButton, constraints);

        /*Fix number of initial connection per node label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(numOfInitConLabel, constraints);
        numOfInitConLabel.setFont(promptFont);

        /*Fix number of initial nodes text field constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 20, 0);
        numOfInitConTextField = new IntOnlyTextField(String.valueOf(BuildGraph.m), 7);
        numOfInitConTextField.setToolTipText("Enter Number of Initial Connections for each node. Used only for Scale-Free");
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(numOfInitConTextField, constraints);
        lineNum++;

        /*Fix probability label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(probLabel, constraints);
        probLabel.setFont(promptFont);

        /*Fix probability text field constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 20, 0);
        probText = new DoubleOnlyTextField(String.valueOf(BuildGraph.prob), 7);
        probText.setToolTipText("Enter Probability");
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(probText, constraints);
        probText.setToolTipText("Probability should be between 0 and 1");
        lineNum++;

        /*Fix the seed Label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(seedLabel, constraints);
        seedLabel.setFont(promptFont);

        /*Fix the seed Text field  constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.WEST;
        seedTextField = new IntOnlyTextField(String.valueOf(BuildGraph.numOfVertices), 7);
        seedTextField.setToolTipText("Enter the seed to feed the random number generator");
        gridbag.setConstraints(seedTextField, constraints);
        lineNum++;

        /*Fix the classpath label constraints*/
        buildConstraints(constraints, 0, lineNum, 1, 1, 50, 0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 2, 0, 0);
        gridbag.setConstraints(classpathLabel, constraints);

        /*Fix the classpath text field constraints*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        classpathField = new JTextField(BuildGraph.ffpath);
        classpathField.setToolTipText("Enter the classpath of the folder you have saved your graph");
        gridbag.setConstraints(classpathField, constraints);

        /*Fix Browser Button constraints*/
        buildConstraints(constraints, 2, lineNum, 1, 1, 50, 0);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(browseButton, constraints);
        browseButton.setToolTipText("Press the button to select the file from a file browser");
        lineNum++;

        /*Add the parameters and extras components to the panel*/
        entryPanel.add(parameters);
        entryPanel.add(extras);

        entryPanel.add(probLabel);
        entryPanel.add(probText);
        probText.setEnabled(false);
        probText.setBackground(Color.lightGray);

        entryPanel.add(analyzeButton);

        entryPanel.add(graphDegreeLabel);
        entryPanel.add(graphDegree);
        graphDegree.setEnabled(false);
        graphDegree.setBackground(Color.lightGray);

        entryPanel.add(numOfInitNodesLabel);
        entryPanel.add(numOfInitNodesTextField);
        numOfInitNodesTextField.setEnabled(false);
        numOfInitNodesTextField.setBackground(Color.lightGray);
        entryPanel.add(numOfInitConLabel);
        entryPanel.add(numOfInitConTextField);
        numOfInitConTextField.setEnabled(false);
        numOfInitConTextField.setBackground(Color.lightGray);
        entryPanel.add(graphButton);

        entryPanel.add(numOfEdgesLabel);
        entryPanel.add(numOfEdgesTextField);
        numOfEdgesTextField.setEnabled(false);
        numOfEdgesTextField.setBackground(Color.lightGray);

        entryPanel.add(seedLabel);
        entryPanel.add(seedTextField);
        seedTextField.setEnabled(false);
        seedTextField.setBackground(Color.lightGray);

        entryPanel.add(classpathLabel);
        entryPanel.add(classpathField);
        classpathField.setEditable(true);
        classpathField.setOpaque(true);
        classpathField.setEnabled(false);
        classpathField.setBackground(Color.lightGray);
        entryPanel.add(browseButton);
        browseButton.setEnabled(false);

        graphButton.setEnabled(false);
        analyzeButton.setEnabled(false);

        /*Fix ok button constraints and add it to the panel*/
        buildConstraints(constraints, 1, lineNum, 1, 1, 0, 0);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(25, 20, 1, 10);
        gridbag.setConstraints(runButton, constraints);
        entryPanel.add(runButton);

        /*Fix cancel button constraints and add it to the panel*/
        buildConstraints(constraints, 2, lineNum, 1, 1, 50, 0);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(25, 20, 1, 0);
        gridbag.setConstraints(exitButton, constraints);
        entryPanel.add(exitButton);

        /* Set the panel */
        add(entryPanel);

        fileExist();

        /*Set Close menu item Listener*/
        close.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*Set About menu item Listener*/
        about.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "NGCE\n" + "\nSystem Design and Development: Vasileios Vlachos" + "\nGraphical User Interface Development: Vassiliki Vouzi", "About us", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        help.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "" + "Thank you for using our tool! Your comments,\n" + "suggestions, and questions are highly  appreciated!\n" + "Please contact us at vbill at aueb.gr", "Contact", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /*Set Browse Buttons Listener*/
        browseButton.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = chooser.showOpenDialog(NGCEframe.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    classpathField.setText(file.getAbsolutePath()); //getName());
                }
            }
        });

        graphButton.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                try {
					PajekOut.convert();                   
                } catch (Exception ioe) {
                    System.err.println("I/O Problem ");
                   printResults.append("I/O Problem");
                    ioe.printStackTrace();
                }
            }

        });

        analyzeButton.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                try {
					GraphExplorer.prostProcess();
                    
                } catch (Exception ioe) {
                    System.err.println("I/O Problem ");
                    printResults.append("I/O Problem");

                    ioe.printStackTrace();
                }
            }

        });
        /*Set Homogenous radio button's listener*/
        graphModel[0].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                typeOfModel = graphModel[0].getText();
                graphDegree.setEnabled(false);
                numOfEdgesTextField.setEnabled(false);
                graphDegree.setBackground(Color.lightGray);
                numOfEdgesTextField.setBackground(Color.lightGray);
                probText.setEnabled(false);
                probText.setBackground(Color.lightGray);
                seedTextField.setEnabled(false);
                seedTextField.setBackground(Color.lightGray);
                classpathField.setEnabled(false);
                classpathField.setBackground(Color.lightGray);
                browseButton.setEnabled(false);
                postProcs.setEnabled(false);
                fileExist();
                vetrexNumber.setEnabled(true);
                vetrexNumber.setBackground(Color.white);
                incrgrowth.setEnabled(false);
                prefattach.setEnabled(false);
                numOfInitNodesTextField.setEnabled(false);
                numOfInitNodesTextField.setBackground(Color.lightGray);
                numOfInitConTextField.setEnabled(false);
                numOfInitConTextField.setBackground(Color.lightGray);
                incrgrowth1.setEnabled(false);
                prefattach1.setEnabled(false);

            }
        });

        /*Set Lattice's radio button's listener*/
        graphModel[1].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                typeOfModel = graphModel[1].getText();
                graphDegree.setEnabled(true);
                numOfEdgesTextField.setEnabled(false);
                graphDegree.setBackground(Color.white);
                numOfEdgesTextField.setBackground(Color.lightGray);
                probText.setEnabled(false);
                probText.setBackground(Color.lightGray);
                seedTextField.setEnabled(true);
                seedTextField.setBackground(Color.white);
                classpathField.setEnabled(false);
                classpathField.setBackground(Color.lightGray);
                browseButton.setEnabled(false);
                postProcs.setEnabled(true);
                fileExist();
                vetrexNumber.setEnabled(true);
                vetrexNumber.setBackground(Color.white);
                incrgrowth.setEnabled(false);
                prefattach.setEnabled(false);
                numOfInitNodesTextField.setEnabled(false);
                numOfInitNodesTextField.setBackground(Color.lightGray);
                numOfInitConTextField.setEnabled(false);
                numOfInitConTextField.setBackground(Color.lightGray);
                incrgrowth1.setEnabled(false);
                prefattach1.setEnabled(false);

            }
        });

        /*Set BA Scale-free radio button's listener*/
        graphModel[2].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                typeOfModel = graphModel[2].getText();
                graphDegree.setEnabled(false);
                numOfEdgesTextField.setEnabled(false);
                graphDegree.setBackground(Color.lightGray);
                numOfEdgesTextField.setBackground(Color.lightGray);
                probText.setEnabled(false);
                probText.setBackground(Color.lightGray);
                seedTextField.setEnabled(true);
                seedTextField.setBackground(Color.white);
                classpathField.setEnabled(false);
                classpathField.setBackground(Color.lightGray);
                browseButton.setEnabled(false);
                postProcs.setEnabled(false);
                fileExist();
                vetrexNumber.setEnabled(true);
                vetrexNumber.setBackground(Color.white);
                incrgrowth.setEnabled(true);
                prefattach.setEnabled(true);
                numOfInitNodesTextField.setEnabled(true);
                numOfInitNodesTextField.setBackground(Color.white);
                numOfInitConTextField.setEnabled(true);
                numOfInitConTextField.setBackground(Color.white);
                incrgrowth1.setEnabled(false);
                prefattach1.setEnabled(false);
            }
        });

        /*Set ER Random Graph radio button's listener*/
        graphModel[3].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                typeOfModel = graphModel[3].getText();
                graphDegree.setEnabled(false);
                numOfEdgesTextField.setEnabled(false);
                graphDegree.setBackground(Color.lightGray);
                numOfEdgesTextField.setBackground(Color.lightGray);
                probText.setEnabled(true);
                probText.setBackground(Color.white);
                seedTextField.setEnabled(true);
                seedTextField.setBackground(Color.white);
                classpathField.setEnabled(false);
                classpathField.setBackground(Color.lightGray);
                browseButton.setEnabled(false);
                postProcs.setEnabled(false);
                fileExist();
                vetrexNumber.setEnabled(true);
                vetrexNumber.setBackground(Color.white);
                incrgrowth.setEnabled(false);
                prefattach.setEnabled(false);
                numOfInitNodesTextField.setEnabled(false);
                numOfInitNodesTextField.setBackground(Color.lightGray);
                numOfInitConTextField.setEnabled(false);
                numOfInitConTextField.setBackground(Color.lightGray);
                incrgrowth1.setEnabled(false);
                prefattach1.setEnabled(false);

            }
        });

        graphModel[4].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                graphDegree.setEnabled(false);
                numOfEdgesTextField.setEnabled(true);
                graphDegree.setBackground(Color.lightGray);
                numOfEdgesTextField.setBackground(Color.white);
                probText.setEnabled(true);
                probText.setBackground(Color.white);
                seedTextField.setEnabled(false);
                seedTextField.setBackground(Color.lightGray);
                classpathField.setEnabled(false);
                classpathField.setBackground(Color.lightGray);
                browseButton.setEnabled(false);
                postProcs.setEnabled(false);
                fileExist();
                vetrexNumber.setEnabled(true);
                vetrexNumber.setBackground(Color.white);
                incrgrowth.setEnabled(false);
                prefattach.setEnabled(false);
                numOfInitNodesTextField.setEnabled(false);
                numOfInitNodesTextField.setBackground(Color.lightGray);
                numOfInitConTextField.setEnabled(false);
                numOfInitConTextField.setBackground(Color.lightGray);
                incrgrowth1.setEnabled(true);
                prefattach1.setEnabled(true);
                typeOfModel = graphModel[4].getText();
                seedTextField.setEnabled(true);
                probText.setEnabled(true);
                probText.setBackground(Color.white);
                seedTextField.setBackground(Color.white);

            }
        });

        /*Set Custom Graph radio button's listener*/
        graphModel[5].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                graphDegree.setEnabled(false);
                numOfEdgesTextField.setEnabled(true);
                graphDegree.setBackground(Color.lightGray);
                numOfEdgesTextField.setBackground(Color.white);
                probText.setEnabled(true);
                probText.setBackground(Color.white);
                seedTextField.setEnabled(false);
                seedTextField.setBackground(Color.lightGray);
                classpathField.setEnabled(false);
                classpathField.setBackground(Color.lightGray);
                browseButton.setEnabled(false);
                postProcs.setEnabled(false);
                fileExist();
                vetrexNumber.setEnabled(true);
                vetrexNumber.setBackground(Color.white);
                incrgrowth.setEnabled(false);
                prefattach.setEnabled(false);
                numOfInitNodesTextField.setEnabled(false);
                numOfInitNodesTextField.setBackground(Color.lightGray);
                numOfInitConTextField.setEnabled(false);
                numOfInitConTextField.setBackground(Color.lightGray);
                incrgrowth1.setEnabled(true);
                prefattach1.setEnabled(true);
                typeOfModel = graphModel[5].getText();
                probText.setEnabled(false);
                seedTextField.setEnabled(true);
                seedTextField.setBackground(Color.white);
                probText.setBackground(Color.lightGray);
                numOfInitNodesTextField.setEnabled(true);
                numOfInitNodesTextField.setBackground(Color.white);
                numOfInitConTextField.setEnabled(true);
                numOfInitConTextField.setBackground(Color.white);

            }
        });
        /*Set Pre-prepared radio button's listener*/
        graphModel[6].addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                typeOfModel = graphModel[4].getText();
                graphDegree.setEnabled(false);
                graphDegree.setBackground(Color.lightGray);
                numOfEdgesTextField.setEnabled(false);
                numOfEdgesTextField.setBackground(Color.lightGray);
                probText.setEnabled(false);
                probText.setBackground(Color.lightGray);
                seedTextField.setEnabled(false);
                seedTextField.setBackground(Color.lightGray);
                classpathField.setEnabled(true);
                classpathField.setBackground(Color.white);
                postProcs.setSelected(true);
                browseButton.setEnabled(true);
                graphButton.setEnabled(true);
                analyzeButton.setEnabled(true);
                postProcs.setEnabled(false);
                incrgrowth.setEnabled(false);
                prefattach.setEnabled(false);
                numOfInitNodesTextField.setEnabled(false);
                numOfInitNodesTextField.setBackground(Color.lightGray);
                numOfInitConTextField.setEnabled(false);
                numOfInitConTextField.setBackground(Color.lightGray);
                incrgrowth1.setEnabled(false);
                prefattach1.setEnabled(false);

            }
        });

        /*Set Custom Graph sub-radio button's listener*/
        /*customGraphs[0].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                typeOfModel = customGraphs[0].getText();
                seedTextField.setEnabled(true);
                probText.setEnabled(true);
                probText.setBackground(Color.white);
                seedTextField.setBackground(Color.white);
            }
        });*/

        /*customGraphs[1].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                typeOfModel = customGraphs[1].getText();
                probText.setEnabled(false);
                seedTextField.setEnabled(true);
                probText.setBackground(Color.lightGray);
            }
        });*/
        /*Set Incremental Growth Check Box Action Listener*/
        incrgrowth.addItemListener(new ItemListener() {
            public
            void itemStateChanged(ItemEvent e) {

                if ((prefattach.isSelected() == true) && (incrgrowth.isSelected() == false)) {
                    numOfInitNodesTextField.setEnabled(false);
                    numOfInitNodesTextField.setBackground(Color.lightGray);
                    numOfInitConTextField.setEnabled(false);
                    numOfInitConTextField.setBackground(Color.lightGray);
                }
                if ((incrgrowth.isSelected() == true) && (prefattach.isSelected() == true)) {
                    numOfInitNodesTextField.setEnabled(true);
                    numOfInitNodesTextField.setBackground(Color.white);
                    numOfInitConTextField.setEnabled(true);
                    numOfInitConTextField.setBackground(Color.white);
                }

            }
        });

        /*Set Preferential Attachement Check Box Action Listener*/
        prefattach.addItemListener(new ItemListener() {
            public
            void itemStateChanged(ItemEvent e) {
                if ((incrgrowth.isSelected() == false) && (prefattach.isSelected() == true)) {
                    numOfInitNodesTextField.setEnabled(false);
                    numOfInitNodesTextField.setBackground(Color.lightGray);
                    numOfInitConTextField.setEnabled(false);
                    numOfInitConTextField.setBackground(Color.lightGray);
                }
                if ((incrgrowth.isSelected() == true) && (prefattach.isSelected() == true)) {
                    numOfInitNodesTextField.setEnabled(true);
                    numOfInitNodesTextField.setBackground(Color.white);
                    numOfInitConTextField.setEnabled(true);
                    numOfInitConTextField.setBackground(Color.white);
                }

            }
        });
        /*Set Incremental Growth Check Box Action Listener*/
        incrgrowth1.addItemListener(new ItemListener() {
            public
            void itemStateChanged(ItemEvent e) {
                if ((prefattach1.isSelected() == true) && (incrgrowth1.isSelected() == false)) {
                    numOfInitNodesTextField.setEnabled(false);
                    numOfInitNodesTextField.setBackground(Color.lightGray);
                    numOfInitConTextField.setEnabled(false);
                    numOfInitConTextField.setBackground(Color.lightGray);
                    numOfEdgesTextField.setEnabled(true);
                    numOfEdgesTextField.setBackground(Color.white);
                }
                if ((incrgrowth1.isSelected() == true) && (prefattach1.isSelected() == true)) {
                    numOfInitNodesTextField.setEnabled(true);
                    numOfInitNodesTextField.setBackground(Color.white);
                    numOfInitConTextField.setEnabled(true);
                    numOfInitConTextField.setBackground(Color.white);
                    numOfEdgesTextField.setEnabled(false);
                    numOfEdgesTextField.setBackground(Color.lightGray);
                }
                if ((incrgrowth1.isSelected() == true) && (prefattach1.isSelected() == false)) {
                    numOfEdgesTextField.setEnabled(false);
                    numOfEdgesTextField.setBackground(Color.lightGray);
                    numOfInitNodesTextField.setEnabled(false);
                    numOfInitNodesTextField.setBackground(Color.lightGray);
                    numOfInitConTextField.setEnabled(true);
                    numOfInitConTextField.setBackground(Color.white);

                }
            }
        });

        /*Set Preferential Attachement Check Box Action Listener*/
        prefattach1.addItemListener(new ItemListener() {
            public
            void itemStateChanged(ItemEvent e) {
                if ((prefattach1.isSelected() == true) && (incrgrowth1.isSelected() == false)) {
                    numOfInitNodesTextField.setEnabled(false);
                    numOfInitNodesTextField.setBackground(Color.lightGray);
                    numOfInitConTextField.setEnabled(false);
                    numOfInitConTextField.setBackground(Color.lightGray);
                    numOfEdgesTextField.setEnabled(true);
                    numOfEdgesTextField.setBackground(Color.white);
                }
                if ((incrgrowth1.isSelected() == true) && (prefattach1.isSelected() == true)) {
                    numOfInitNodesTextField.setEnabled(true);
                    numOfInitNodesTextField.setBackground(Color.white);
                    numOfInitConTextField.setEnabled(true);
                    numOfInitConTextField.setBackground(Color.white);
                    numOfEdgesTextField.setEnabled(false);
                    numOfEdgesTextField.setBackground(Color.lightGray);
                }
                if ((incrgrowth1.isSelected() == true) && (prefattach1.isSelected() == false)) {
                    numOfEdgesTextField.setEnabled(false);
                    numOfEdgesTextField.setBackground(Color.lightGray);
                    numOfInitNodesTextField.setEnabled(false);
                    numOfInitNodesTextField.setBackground(Color.lightGray);
                    numOfInitConTextField.setEnabled(true);
                    numOfInitConTextField.setBackground(Color.white);

                }
            }
        });




        /*	Set OK button's listener: Build the desired Graph by using
         * 	the retrieved settings.*/
        runButton.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {


                ppClasspath = classpathField.getText();

                numberOfVertices = vetrexNumber.getValue();

                if (typeOfModel != null) {

                    if (typeOfModel.equals("Random Fixed Graph")) {
                        degreeOfGraph = graphDegree.getValue();
                    }
                    if (typeOfModel.equals("Custom Random Graph")) {
                        edgesNumber = numOfEdgesTextField.getValue();
                    }
                    if (typeOfModel.equals("Custom Scale-Free Graph")) {
                        if ((!incrgrowth1.isSelected()) && (prefattach1.isSelected())) {
                            edgesNumber = numOfEdgesTextField.getValue();
                        }
                        if ((incrgrowth1.isSelected()) && (prefattach1.isSelected())) {
                            initCon = numOfInitConTextField.getValue();
                            initNodes = numOfInitNodesTextField.getValue();
                        }
                        if ((incrgrowth1.isSelected()) && (!prefattach1.isSelected())) {
                            initCon = numOfInitConTextField.getValue();
                            initNodes = numberOfVertices;
                        }
                    }
                    if (typeOfModel.equals("Custom Random Graph")) {
                        probability = probText.getValue();
                    }
                    if (typeOfModel.equals("ER Random Graph")) {
                        probability = probText.getValue();
                    }
                    if (typeOfModel.equals("Scale-free")) {
                        if ((incrgrowth.isSelected()) || (prefattach.isSelected())) {
                            initCon = numOfInitConTextField.getValue();
                            initNodes = numOfInitNodesTextField.getValue();
                        }
                    }
                    if ((!typeOfModel.equals("Homogenous")) || (!typeOfModel.equals("Pre-Prepared Graph"))) {
                        BuildGraph.seed = seedTextField.getValue();
                    }
                }



                /* Validate the retrieved settings. */
                if (validParam()) {
                    runButton.setEnabled(false);

                    printResults.append("Please wait...\n");
                    printResults.append(typeOfModel + " Graph is being generated...\n");

                    BuildGraph.numOfVertices = numberOfVertices;


                    if (typeOfModel.equalsIgnoreCase("Homogenous")) {
                        BuildGraph.typeOfmodel = "H";
                    } else if (typeOfModel.equalsIgnoreCase("Random Fixed Graph")) {
                        BuildGraph.typeOfmodel = "F";
                        BuildGraph.neighbors = degreeOfGraph;
                    } else if (typeOfModel.equalsIgnoreCase("Scale-free")) {

                        if ((prefattach.isSelected()) && (!incrgrowth.isSelected())) {
                            BuildGraph.typeOfmodel = "S";
                        }
                        if ((incrgrowth.isSelected()) && (prefattach.isSelected())) {
                            BuildGraph.typeOfmodel = "A";
                            BuildGraph.m = initCon;
                            BuildGraph.numOfInitNodes = initNodes;
                        }

                    } else if (typeOfModel.equalsIgnoreCase("ER Random Graph")) {
                        BuildGraph.typeOfmodel = "E";
                        BuildGraph.prob = probability;
                    } else if (typeOfModel.equalsIgnoreCase("Custom Random Graph")) {
                        BuildGraph.typeOfmodel = "C";
                        BuildGraph.prob = probability;
                        BuildGraph.numOfEdges = edgesNumber;
                    } else if (typeOfModel.equalsIgnoreCase("Custom Scale-Free Graph")) {

                        if (prefattach1.isSelected() && (!incrgrowth.isSelected())) {
                            BuildGraph.typeOfmodel = "M";
                            BuildGraph.numOfEdges = edgesNumber;
                        }
                        if ((incrgrowth1.isSelected()) && (prefattach1.isSelected())) {
                            BuildGraph.typeOfmodel = "A";
                            BuildGraph.m = initCon;
                            BuildGraph.numOfInitNodes = initNodes;
                        }
                        if ((incrgrowth1.isSelected()) && (!prefattach1.isSelected())) {
                            BuildGraph.typeOfmodel = "A";
                            BuildGraph.numOfInitNodes = initNodes;
                            BuildGraph.m = initCon;
                        }

                    } else if (typeOfModel.equalsIgnoreCase("Pre-Prepared Graph")) {
                        BuildGraph.typeOfmodel = "B";
                        BuildGraph.ffpath = ppClasspath;
                    }

                    if (postProcs.isSelected()) {
                        BuildGraph.postPrcs = true;
                    }


                    BuildGraph.buildingGraph();

                    printResults.append("Program terminated normally\n");
                    printResults.append("*****************************************************\n");
                    runButton.setEnabled(true);
                    fileExist();

                }

            }
        });
        /* Close the dialog box if the 'cancel' button is pressed. */
        exitButton.addActionListener(new ActionListener() {
            public
            void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }


    /**
     * This method is used to check the validity of the retrieved parameters and opens an Option Panel when retrieved
     * data are invalid.
     */
    protected
    boolean validParam() {
        String errorMsg = new String();

        if (numberOfVertices == 0) {
            errorMsg += "- The numberOfVertices must be nonzero!\n";
        }
        if (typeOfModel == null) {
            errorMsg += "- Type of model must be selected!\n";
        }
        if (typeOfModel != null) {
            if ((degreeOfGraph == 0) && (typeOfModel.equals("Random Fixed Graph"))) {
                errorMsg += "- The graph degree must be nonzero!\n";
            }
            if ((edgesNumber == 0) && (typeOfModel.equals("Custom Random Graph"))) {
                errorMsg += "- Edges Number must be nonzero!\n";
            }
            if ((typeOfModel.equals("Custom Scale-Free Graph") && ((prefattach1.isSelected() == true) && (incrgrowth1.isSelected() == false) && (edgesNumber == 0)))) {
                errorMsg += "- Edges Number must be nonzero!\n";
            }
            if ((probability == 0) && ((typeOfModel.equals("Custom Random Graph")) || (typeOfModel.equals("ER Random Graph")))) {
                errorMsg += "- Probability must be nonzero!\n";
            }
            if (((probability < 0) || (probability > 1)) && ((typeOfModel.equals("Custom Random Graph")) || (typeOfModel.equals("ER Random Graph")))) {
                errorMsg += "- Probability between 0 and 1!\n";
            }
            if (((ppClasspath == null) || (ppClasspath.length() == 0)) && (typeOfModel.equals("Pre-Prepared Graph"))) {
                errorMsg += "- The classpath must be defined!\n";
            }
            if ((((prefattach.isSelected() == false) && (incrgrowth.isSelected() == false)) && (typeOfModel.equals("Scale-free")))) {
                errorMsg += "- AT LEAST ONE of the characteristics of Scale-Free Graph \n\t(Incremental Growth/Preferential Attachement) must be selected!\n";
            }
            if (((prefattach.isSelected() == false) && (incrgrowth.isSelected() == true) && (typeOfModel.equals("Scale-free")))) {
                errorMsg += "- Incremental Growth cannot be solely selected!\n";
            }
            if ((((prefattach1.isSelected() == false) && (incrgrowth1.isSelected() == false)) && (typeOfModel.equals("Custom Scale-Free Graph")))) {
                errorMsg += "- AT LEAST ONE of the characteristics of Custom Scale-Free Graph \n\t(Incremental Growth/Preferential Attachement) must be selected!\n";
            }

        }

        if (errorMsg.length() > 0) {
            JOptionPane.showMessageDialog(null, errorMsg, "Invalid Parameters Detected", JOptionPane.ERROR_MESSAGE);

            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is used to call the constructor of Ngfframe and initialize it with the desired size.
     */
    protected
    void createAndShowNGCEFrame() {

        JFrame.setDefaultLookAndFeelDecorated(true);

        /*Create and set up the window.*/


        JFrame frame = new JFrame("NGCE " + BuildGraph.version + " Athens University of Economics and Business" +

                                  " --- Software Engineering and Security Group ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recognSystem();
        if (Linux) {
            frame.setSize(800, 750);
        } else {
            frame.setSize(710, 765);
        }

        /*Create and set up the content pane.*/
        JComponent newContentPane = new NGCEframe();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setJMenuBar(menubar);
        frame.setResizable(true);
        frame.setVisible(true);

    }

    public static
    void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public
            void run() {
                NGCEframe frame = new NGCEframe();
                frame.createAndShowNGCEFrame();
            }
        });
    }

    /**
     * This method is used to recognize the type of system the users operates in to
     */
    private static
    void recognSystem() {
        String os = System.getProperty("os.name");
        if (os.indexOf("Windows") > -1) {
            Linux = false;
        } else {
            Linux = true;
        }
    }

    /**
     * This method is used to check whether the needed plotting files exist or not
     */
    private static
    void fileExist() {
        File dir = new File("../GraphTopology.txt");
        if (dir.exists()) {
            graphButton.setEnabled(true);
            analyzeButton.setEnabled(true);
        } else {
            graphButton.setEnabled(false);
            analyzeButton.setEnabled(true);
        }

    }

}

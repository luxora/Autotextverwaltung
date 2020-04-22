import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public  class main  {
	 JList kategorien_jlist;
	 static JList bausteine_jlist;
	 
	 
     static JTextArea bausteineta = new JTextArea ();
	 static JLabel statusfield = new JLabel ("--- Status ---");
	 static JTable table;
	    
	    // Array für unsere JList
     static String kategorien[] = new String [40]; // speichert die Kateogrien die es überhaupt gibt ab
     static String[] kategorien_eigeneliste  = new String [600]; // bei mehr nötigen bausteinen velrängern
     															// = speichert die kategorien, die je nach ausgelesener
     															// baustein datei (hier kategorien_gegliedert.txt
     															// fuer die jeweilige ID Nummer des bausteins ausgelesen wurden.
     
     static String bausteine [] = new String [600]; // bei mehr nötigen bausteinen verlängern
	 JTextArea bausteinta;
	 int aktuellebausteinanzahl = 0;
	 
	 
	 static JTextField idfeldfuersuche = new JTextField ();
	 static JTextField aktuellebausteinanzahltf = new JTextField("0"); // speichert aktuelle baustein anzahl fürs hinzufügen v neuen.
	    
	public static void main (String [] args)
	{
		
		JFrame mainframe = new JFrame ("Autotextverwaltung"); // Autotextverwaltung Bausteiner 20.04 - (c) CS LL in Anlehnung an TB
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setBounds(0, 0, 1600, 900);
		// ACHTUNG DEM FRAME KEIN LAYOUT SETZEN SONST GETS NICHT
		
load loadobject = new load ();
		
		JPanel panel_liste = new JPanel();
		//panel_liste.setBounds(0, 0, 1600,900);
		JPanel panel_suche = new JPanel();
		//panel_suche.setBounds(0, 0, 1600,900);
		JPanel panel_addbaustein = new JPanel();
		JPanel panel_importierefremd = new JPanel();
		//panel_addbaustein.setBounds(0, 0, 1600,900);
		// JPanel panel_settings = new JPanel();
		//panel_settings.setBounds(0, 0, 1600,900);
		JPanel panel_about = new JPanel();
	//	panel_about.setBounds(0, 0, 1600,900);

		
	       JTabbedPane tabbedPane = new JTabbedPane
	               (JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );
	    		tabbedPane.setBounds(0, 0, 1600,300);
	    		tabbedPane.setBorder(null);
		tabbedPane.addTab("Suche",panel_suche);
		tabbedPane.addTab("Liste",panel_liste);

		//tabbedPane.addTab("Einstellungen",panel_settings);
		tabbedPane.addTab("Bausteine",panel_addbaustein);
		tabbedPane.addTab("Importiere fremde Bausteine", panel_importierefremd);
		tabbedPane.addTab("About",panel_about);
		
		
		// Layout der panels auf null setzen damit komponenten platzieren möglic:
		panel_liste.setLayout(null);
		panel_suche.setLayout(null);
		panel_addbaustein.setLayout(null);
		//panel_settings.setLayout(null);
	    panel_importierefremd.setLayout(null);
		panel_about.setLayout(null);
		
		try
		{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception looky)		// fuelle kateogrien:

		{
			System.out.println("Default look and feel konnte nicht geladen werden");
		}
		
	/**		PANEL LISTE: **/
		
			
	//  hole und lade kateogrien, d.h. überpunkte gliederungen:
			 BufferedReader inb = null;
			   try {
				   File speicherfile2 = new File("kategorien_liste.txt");

			   
			       inb = new BufferedReader(new FileReader(speicherfile2));
			       String zeileab = null;
			   	   int i = 0;
			       while ((zeileab = inb.readLine()) != null) {
			    	   i = i+1;
			    	   String istring = "(#" +Integer.toString(i) + "#)";
			    	   kategorien [i] = "" + istring + " "  +zeileab + "";
			    	  System.out.println ("\"" + istring + " "  +zeileab + "\",");
			    	  
					   // fuellen in dem schema: String columns[] = { "Baustein ID","Kategorie", "Bausteinname", "Baustein Text" };

			    	  // der erste Parameter steht für das object: hier immer i, da der jeweilige baustein ja immer in einanderers oobejt muss
			    	  // dann der [2] steht für den bausteinnamen, der hier abgefangen wird.
			    	
			    	  
			       
			       } // while
			   } // try
			       catch (Exception sd)
			       {
			    	   
			       }
			   
		File speicherfile2 = new File("unterkategorien_liste.txt");
 
		
		// wenn noch keine DAtei vorhanden, dann gibt es noch keine bausteine, daher aktuellebausteinanzahl auf null:
		if (!speicherfile2.exists())
		{
    	aktuellebausteinanzahltf.setText("0");
		}
		
		
		
		//  hole und lade unterkategorien, d.h. bausteinnamen:
		 BufferedReader inb2 = null;
		   try {
			    speicherfile2 = new File("unterkategorien_liste.txt");

		   
		       inb2 = new BufferedReader(new FileReader(speicherfile2));
		       String zeileab = null;
		   	   int i = 0;
		       while ((zeileab = inb2.readLine()) != null) {
		    	   i = i+1;
		    	   String istring = "(#" +Integer.toString(i) + "#)"; //da die kategorie sonst nicht ausgelsen kann fuer die 
		    	   														// filteruntg in der zweiten listen, muss die nummerierung weg vorne dran.
		    	   bausteine [i] = "" + istring + " "   +zeileab + "";
		    	   bausteine [i] = bausteine[i].replace("    ",""); // ersetze sinnfreue abstände
		    	
		    		   // dann ist es ein baustein, zähle aktuell vorhandene bausteine anzahl hoch um eins
		    		   // um für das hinzufügen die nächste nummer zu haben die der baustein bekommen soll:
		    		  String bstzahl = String.valueOf(i);
		    		   aktuellebausteinanzahltf.setText(bstzahl);
		    		   
		    	   
		    	   
		    	//  System.out.println ("\"" + istring + " "  +zeileab + "\",");
		    	  
		       
		       } // while
		   } // try
		       catch (Exception sd)
		       {
		    	   
		       }
		
		
		   // SCHREIBE GLIEDERUNGSDATEI MIT NUMMERN:
		  try {
			FileWriter fww = new FileWriter ("gliederungsdatei.txt");
			
			// schreibe jeden Baustein schon mal  in Baustein Datei:
			for (int i = 0; i<bausteine.length;i++)
			{
				// schreibe in dem schema:   kategorie;bausteinname;bausteinnummer oder id
				int ipluseins = i;
				String ipluseinsstring = Integer.toString(ipluseins);
				if (bausteine[i] == null)
				{
					// wenn noch gar keine Baustiene vorhanden sind, dann soll er natürlcih auch keine Liste schreiben
					// bzw. keine Gliederung, sonst beginnt ja die Bausteinzählung immer erst bei 300
				}
				else
				{
				fww.write(";" + bausteine[i] + ";" + ipluseins + "\n");
				}
				fww.flush();
				
			}
			
			
		} catch (IOException e1) {
			
		}
		   
		   
		   // GLIEDREUNGSDATEI DER KATEGORIEN
		  // siehe robot / life / baustein.php Datei fuer automatische Ausgabe von ID Nummern und Kategorien
		  // d.h. 0;Allgemeine Grussformeln usw...
		   
		  // GLIEDERUNGSDATEI DER KATEGORIEN 1,ALLGEMEINES GRUSSFORMELN AUSLESEN UND SPEICHERN: 
		  
		// hole fuer jeden Baustein aus der Gliederungsdatei die zugehörige Kateogorie und lade diese:
		    
		  // speichere hier so: [0] =  [Allgemeines Grussformeln], [1] = [grussforlen...] [20] [z.B. Bausteinkategorie];
		 String[] kategorien_der_bausteine_zugeordnet = new String [bausteine.length];

		    
		    
		    File kategorien_gegliedertfile = new File ("kategorien_gegliedert.txt");
		    
		    if ( kategorien_gegliedertfile.exists())
		    {
		    FileReader in; // wenn datei da, lies sie aus und speichere kategorien mit bausteinnumern.
			try {
				in = new FileReader("kategorien_gegliedert.txt");
				 BufferedReader br = new BufferedReader(in);
				    String line = null;
					try {
						line = br.readLine();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				    while (line!=null) {
				        System.out.println(line);
				        
				        // zählt vom letzten Baustein (steht ganz oben, bis nach unten runter zum nullten)
				        // hole Baustein ID:
				        String[] arrOfStr = line.split(";"); 
				        String zwischenspeicher [] = new String [2];

				        zwischenspeicher[0] = arrOfStr[0]; // id nummer
						   zwischenspeicher[1] = arrOfStr[1]; // kategorie

						   int aktuellebausteinidfuerkat = Integer.parseInt(zwischenspeicher[0]); // parse id nummer
				        kategorien_eigeneliste [aktuellebausteinidfuerkat] = zwischenspeicher[1]; // speichere je nach id
				        						// die aktuelle bausteinkateogrien in die eigene bausteinliste namens kategorien_eigeneliste
				     
				        
				        try {
							line = br.readLine();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				    }
				    try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		
		    } // ketegorien gegliedert exists bool ()
	  
	 
		 // KATEGORIEN:
	    
	
	        //JList mit Einträgen wird erstellt
	        JList kategorien_jlist = new JList(kategorien);
	        //JList wird Panel hinzugefügt
	        kategorien_jlist.setBounds(20, 10, 250, 800);
	        

	        JTextField gewaehltekategoriezwischenspeicherntf = new JTextField ();
	        gewaehltekategoriezwischenspeicherntf.setVisible(false);
	        
	        JTextField bausteinefilterntf = new JTextField();
	        bausteinefilterntf.setVisible(false);
	        
	        	        
	        MouseListener mouseListener = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                       String selectedItem = (String) kategorien_jlist.getSelectedValue();// hole gewählte kategorie;
                       // hole ID Nummer:
                       String selecteditemid = stringBetween (selectedItem,"(#","#)"); // extrahiere nr.
                       int num = Integer.parseInt(selecteditemid);
                       int num2 = num;
                      // geht:  JOptionPane.showMessageDialog(null, "Kateogrie nummer gewählt ist:" + num2);
                       String kategoriename_selected = kategorien[num2];
                      // geht:  JOptionPane.showMessageDialog(null, "Kateogrie nummer gewählt ist:" + kategoriename_selected);
                       // ersetze nummer vorneweg:
                       String kategorienummervorneweg = "(#" + num2 + "#)";

                        String kategoriename_selected2 = kategoriename_selected.replace(kategorienummervorneweg, ""); // speichere als kateogrie name selected
                       JOptionPane.showMessageDialog(null, kategoriename_selected2); // extrahierter kateogrie name:
                       
                       gewaehltekategoriezwischenspeicherntf.setText(kategoriename_selected2);
                       bausteinefilterntf.setText("true");
                       
                       
                       
                       // AB HIER FILTER CODE NACH KATEGORIEN AUSWAHL:
           	        
           	        int gefiltertebausteinids [] = new int [bausteine.length];
           	        
           	        for (int i = 0; i<bausteine.length;i++)
           	        {
           	        	if (kategorien_eigeneliste[i] == gewaehltekategoriezwischenspeicherntf.getText()) // also wenn der textarray dem namen
           	        							// der gefilterten kategorie entspricht, dann hole i ab;
           	        	{
           	        		gefiltertebausteinids[i] = i; // hier sind jetzt in gefiltertebausteinids nur die i s also die ids der
           	        		// BAUSTEINE gespeichert, die nach kategorieauswahl gezeigt werden sollen;
           	        	}
           	        }
           	        
           	        // lege neue gefilterte bausteine Liste an:
           	        String [] bausteine_gefiltert = new String [gefiltertebausteinids.length];
           	        for (int i = 0;i<gefiltertebausteinids.length;i++)
           	        {
           	        	bausteine_gefiltert[i] = bausteine[gefiltertebausteinids[i]]; // nur dann setze bausteinnamen; also nur die gefilterten;
           	        }
           	       

           	 
           	        
                       
                      // zeige nur noch gefiltertee inhalte in der bausteine liste an:
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                    }
                }
	        };
	        
	        kategorien_jlist.addMouseListener(mouseListener);
	        panel_liste.add(kategorien_jlist);

	        
	           // BAUSTEINE:
   	        // hier wird im ersten Drittel der Ansicht geprüft, ob und welche Kategorien gewählt wurde, damit 
   	        // nur die zugehörigen Bausteine angezeigt werden:
   	     
	        JList bausteine_jlist = new JList(bausteine);

	        //JList mit Einträgen wird erstellt
   	       if (bausteinefilterntf.getText() == "false") // vorher keine suche daher schreibe liste voll aus.
   	        {
   	        }
   	       else
   	       {}
   	        
	        
	        bausteine_jlist.setBounds(280, 10, 430, 800);
              
            MouseListener mouseListener1 = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                       String selectedItem = (String) bausteine_jlist.getSelectedValue();
                       // hole ID Nummer:
                       String selecteditemid = stringBetween (selectedItem,"(#","#)"); // extrahiere nr.
                       // die nummer muss noch eins runter gezählt werden, da durch den einleitungstext alles verschoben ist:
                       int num = Integer.parseInt(selecteditemid);
                       int num2 = num;
                      // bausteinta.setText(selecteditemid);
                       // hole den Text aus entsprechender Text Datei raus:
                       String bausteintext = "";
                       BufferedReader inb2 = null;
            		   try {
            			   File speicherfile2 = new File("baustein_" + num2 + "_null"+".txt");

            		   
            		       inb2 = new BufferedReader(new FileReader(speicherfile2));
            		       String zeileab = null;
            		   	   int i = 0;
            		       while ((zeileab = inb2.readLine()) != null) {
            		    	   
            		    	   zeileab.replace("<span>","\n");
            		    	   zeileab.replace("</span>","\n");
            		    	   zeileab.replace("<span","\n");
            		    	   zeileab.replace("<span","\n");
            		    	   zeileab.replace("_37_b__20_Text", "");
            		    	   zeileab.replace("<p class=","");
            		    	   zeileab.replace("\">", "");
            		    	  bausteintext = bausteintext + zeileab + "\n";
            		       
            		       } // while
            		   } // try
            		       catch (Exception sd)
            		       {
            		    	   
            		       }
            		   
                       // setze Text ein:
            		   bausteineta.setLineWrap(true);
           			bausteineta.setWrapStyleWord(true);

                      bausteineta.setText(bausteintext);
                       
                     }
                }
            };
            bausteine_jlist.addMouseListener(mouseListener1);
             JScrollPane scroll = new JScrollPane(bausteine_jlist);
            scroll.setBounds(280, 10, 430, 700);
             // JScrollPane scroll = new JScrollPane(bausteine_jlist); geht nicht hier scroll zu adden

           //  panel_liste.add(scrollpane, BorderLayout.CENTER);
            //panel_liste.add(bausteine_jlist);
    		scroll.getViewport().setView(bausteine_jlist);
    		panel_liste.add(scroll);
        	 
	        // SHOW BAUSTEIN TA:
	        Border border = BorderFactory.createLineBorder(Color.BLUE);
	        bausteineta.setBorder(border);
	        bausteineta.setBounds(740, 10,550,600);

	       panel_liste.add(bausteineta);
	       

			JButton beendenbutton = new JButton ("Beenden");
			beendenbutton.setBounds(1100, 640, 250, 30);
			beendenbutton.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}} );
			panel_liste.add(beendenbutton);
			
			DefaultTableModel model;
			
			/** PANEL SUCHE **/
			Object rows[][] = { {"BAUSTEIN ID", "BAUSTEIN KATEGORIE", "BAUSTEIN BEZEICHNUNG ODER NAME", "BAUSTEIN TEXT" }}; // ein leerer Baustein muss drin sein,
			// sonst kann man keine nachladen automatisch
		/*	Object rows[][] = { {"", "S42.01", "Claviculafaktur", "Beschreibung dazu..." }, { "","S42.02", "Cl. 2", "kk" }, { "","S42.03", "Cl 3", "kk" },
			        { "","S42.04", "dies ist die Beschreibung", "" } };*/
			
			/*
			model.addRow(new Object[] {roomnumber_fromnewguestfile,
									   surname_fromnewguestfile,
									   prename_fromnewguestfile,
									   dateofbirth_fromnewguestfile,
									   dateofarrival_fromnewguestfile,
									   dateofdeparture_fromnewguestfile,
									   extraservice_fromnewguestfile,
									   roompayed_fromnewguestfile});
			*/
			
			
		    String columns[] = { "Baustein ID","Kategorie", "Bausteinname", "Baustein Text" };
		
		    
		     model = new DefaultTableModel(rows, columns) {
		      public Class getColumnClass(int column) {
		        Class returnValue;
		        if ((column >= 0) && (column < getColumnCount())) {
		          returnValue = getValueAt(0, column).getClass();
		        } else {
		          returnValue = Object.class;
		        }
		        return returnValue;
		      }
		    };
		    
			
		   

		    
		    
			for(int i=0; i<bausteine.length;i++){
				 
				
				
				//Für jede spalte, gehe jede Zeile durch und speicher sie im Datenarray
				
				String istring  = Integer.toString(i);
				
				model.addRow(new Object[] {istring,kategorien_eigeneliste[i],bausteine[i],"text"});
				
				
			 }
			
			 

			
		
			    table = new JTable(model){  
			        public boolean isCellEditable(int row, int column){  
			            return false;  
			          }  
			        };  ;
			    table.getColumnModel().getColumn(0).setPreferredWidth(10);
			    table.getColumnModel().getColumn(1).setPreferredWidth(300);
			    table.getColumnModel().getColumn(2).setPreferredWidth(500);
			    table.getColumnModel().getColumn(3).setPreferredWidth(600);
			    //table.setEnabled(false);
			    
			    final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			    table.setRowSorter(sorter);
			   			    
			    
			    table.addMouseListener( new MouseAdapter() {
			    	  @Override public void mouseClicked( MouseEvent e ) {
			    	   
			    		  // dies gibt vom unsortierten immer die ID aus:
			    		  int rowAtPoint    = table.rowAtPoint( e.getPoint() );
			    	    int columnAtPoint = table.columnAtPoint( e.getPoint() );
			    	  //  System.out.printf( "%d/%d%n", rowAtPoint, columnAtPoint );
			    	    
			    	 //   System.out.println ("jetzt kommen die sortieren rows nach der suche:");

			    	    //  dieser Teil holt die Spaltennummere also die Baustein ID, auch wenn es sortiert wurde:
			    	    int convertedRowAtPoint = sorter.convertRowIndexToModel( rowAtPoint );
			    	    String rowatpoint = String.valueOf(convertedRowAtPoint);
			    	    
			    	    // System.out.println ("row at the point ist gerade." + rowatpoint + "\n"); // das ist die Id, bloss eins zu hoch,
			    	    				// funktioniert auch nach suche;
			    	    // konvertiere diese id:
			    	    
			    	   // ziehe ihr eins ab:
			    	    int conv_genau = convertedRowAtPoint-1;
			    	    String rowatpoint_genau = String.valueOf(conv_genau);
			    	    // System.out.println ("row at point convertiert genau ist." + rowatpoint_genau + "\n"); // geht wunderbar ;D
			    	    
			    	    /*
			    	    int convertedColumAtPoint = table.convertColumnIndexToModel( 0 );
			    	    System.out.println( sorter.getModel().getValueAt( convertedRowAtPoint,
			    	                                                        convertedColumAtPoint) ); // korrekt;
			    	    String ajj = (String) sorter.getModel().getValueAt( convertedRowAtPoint,
                                convertedColumAtPoint);
			    	    */
			    	    // setze die id rein: geht nicht;
			    	    
			    	    // String idnumber2 = String.valueOf(convertedRowAtPoint);
			    	    // idfeldfuersuche.setText(ajj);
			    	    
			    	    
			    	    // Zeige den Baustein an:
			    	    
			    	    JFrame bausteinframeanzeige = new JFrame ("Baustein");
						bausteinframeanzeige.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						bausteinframeanzeige.setBounds (150,200,700,500);
						bausteinframeanzeige.setLayout(null);
						
						// close this popup:
						JButton closepopup = new JButton("Schliessen");
						closepopup.setBounds(250,430,200,30);
						closepopup.addActionListener(new ActionListener () {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								bausteinframeanzeige.setVisible (false);
								
							}
						});
						bausteinframeanzeige.add(closepopup);
						
			    	   
		           
		                // bausteinta.setText(selecteditemid);
		                 // hole den Text aus entsprechender Text Datei raus:
		                 String bausteintext = "";
		                 BufferedReader inb2 = null;
		      		   try {
		      			   File speicherfile2 = new File("baustein_" + conv_genau + "_null"+".txt");

		      		   
		      		       inb2 = new BufferedReader(new FileReader(speicherfile2));
		      		       String zeileab = null;
		      		   	   int i = 0;
		      		       while ((zeileab = inb2.readLine()) != null) {
		      		    	   
		      		    	   zeileab.replace("<span>","\n");
		      		    	   zeileab.replace("</span>","\n");
		      		    	   zeileab.replace("<span","\n");
		      		    	   zeileab.replace("<span","\n");
		      		    	   zeileab.replace("_37_b__20_Text", "");
		      		    	   zeileab.replace("<p class=","");
		      		    	   zeileab.replace("\">", "");
		      		    	  bausteintext = bausteintext + zeileab + "\n";
		      		       
		      		       } // while
		      		   } // try
		      		       catch (Exception sd)
		      		       {
		      		    	   
		      		       }
						// setze den text auch nach suche wieder in die textarea im listentab:
						JTextArea textareabausteine = new JTextArea ();
						textareabausteine.setText(bausteintext);
						textareabausteine.setBounds(20, 20, 600, 400);
						textareabausteine.setWrapStyleWord(true);
						textareabausteine.setLineWrap(true);
						bausteinframeanzeige.add(textareabausteine);
						
						

						bausteinframeanzeige.setVisible(true);
						
				        // setze Text nach suche wiedeer  in die Zwischenablage ein::
			       		
			       		   StringSelection stringSelection = new StringSelection(bausteintext);
			       		   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			       		   clipboard.setContents(stringSelection, null);
			               	statusfield.setText("Baustein in Zwischenablage kopiert!");

						
						
						
			    	    
			    	    
			    	  }
			    	} );
			    
			
		    		
			    
			    table.addMouseListener(new MouseAdapter() {
			    	  public void mouseClicked(MouseEvent e) {
			    	    if (e.getClickCount() == 1) {
			    	      JTable target = (JTable)e.getSource();
			    	      int row = target.getSelectedRow();
			    	      int column = target.getSelectedColumn();
			    	      
			    	   // hole ID Nummer der Row:
		               	int selectedrow = table.getSelectedRow();
		               	String bausteinid = target.getModel().getValueAt(selectedrow, 0).toString(); // hole Baustein ID
		               	statusfield.setText("ID Nummer des Bausteines ist:" + bausteinid);
		               	idfeldfuersuche.setText(bausteinid);
		               	
		               	
		               	int bausteinidint = Integer.parseInt(bausteinid);
		                  // die nummer muss noch eins runter gezählt werden, da durch den einleitungstext alles verschoben ist:
		                  int num = bausteinidint;
		                  int num2 = num;
		                 // bausteinta.setText(selecteditemid);
		                  // hole den Text aus entsprechender Text Datei raus:
		                  String bausteintext = "";
		                  BufferedReader inb2 = null;
		       		   try {
		       			   File speicherfile2 = new File("baustein_" + num2 + "_null"+".txt");

		       		   
		       		       inb2 = new BufferedReader(new FileReader(speicherfile2));
		       		       String zeileab = null;
		       		   	   int i = 0;
		       		       while ((zeileab = inb2.readLine()) != null) {
		       		    	   
		       		    	   zeileab.replace("<span>","\n");
		       		    	   zeileab.replace("</span>","\n");
		       		    	   zeileab.replace("<span","\n");
		       		    	   zeileab.replace("<span","\n");
		       		    	   zeileab.replace("_37_b__20_Text", "");
		       		    	   zeileab.replace("<p class=","");
		       		    	   zeileab.replace("\">", "");
		       		    	  bausteintext = bausteintext + zeileab + "\n";
		       		       
		       		       } // while
		       		   } // try
		       		       catch (Exception sd)
		       		       {
		       		    	   
		       		       }
		       		   
		                  // setze Text in die Zwischenablage ein::
		       		
		       		   StringSelection stringSelection = new StringSelection(bausteintext);
		       		   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		       		   clipboard.setContents(stringSelection, null);
		               	statusfield.setText("Baustein in Zwischenablage kopiert!");

		       		   
		       		   
		       		   bausteineta.setLineWrap(true);
		       		   bausteineta.setWrapStyleWord(true);
		       		   //  alternativ setze ihn nochmals in die bausteine textarea:
		                 bausteineta.setText(bausteintext);
			    	      
			    	      
			    	      
			    	    }
			    	  }
			    	});
			

			
			    idfeldfuersuche.setBounds (20,50,80,20);
			    idfeldfuersuche.setVisible(false);
			    panel_suche.add(idfeldfuersuche);
			    
			    // der table noch ein scrollpane adden:

			    JScrollPane pane = new JScrollPane(table);
			    pane.setBounds(0, 70, 1600, 700);
			    pane.getViewport().setView(table);
			    panel_suche.add(pane);
			    
		    

			    JPanel panel = new JPanel(new BorderLayout());
			    JLabel label = new JLabel("Filtern");
			    label.setBounds(20, 20, 60, 30);
			    panel_suche.add(label);
			    final JTextField filterText = new JTextField("");
			    filterText.addKeyListener(new KeyListener () {

					@Override
					public void keyPressed(KeyEvent ee) {

				        if (ee.getKeyCode() == KeyEvent.VK_ENTER) {
				        	
				        	 String text = filterText.getText();
						        if (text.length() == 0) {
						          sorter.setRowFilter(null);
						        } else {
						         // sorter.setRowFilter(RowFilter.regexFilter(text));
						        	sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						        	
						        	// nochmal Mouse listener adden:
						        	
						        	//  er setzt hier immer die alten werte ein bzw. nimmt die alten ids der langen liste, ggf. table model updaten nochmals:
						        	int[] positions = table.getSelectedRows();
									//reverse array "positions[]"

									for (int i = 0; i < positions.length; i++)
									{
										int a = table.convertRowIndexToModel(positions[i]);
										System.out.println (a + "\n");
									}
					
						        	
						        	//JOptionPane.showMessageDialog(null, "nummer" + iSelectedRow);
						        	
						        	
						        	
						        	
						        	
						        	fange_gewaehlten_baustein_ab ();
				        	
						        } //else
				        	
				        	
				        } // if ee get key
				   
					}  // void keypressed

					@Override
					public void keyReleased(KeyEvent ee) {
						
					}

					@Override
					public void keyTyped(KeyEvent ee) {
						
					}});
			    filterText.setBounds(80, 20, 250, 30);
			    
			    // add suche auch auf Enter Key hin:
			    
			  
			    
			    
			    panel_suche.add(filterText);
			    
			    
			    
			    
			   // panel_suche.add(panel);
			    JButton button = new JButton("Bausteine mit Suchbegriff filtern");
			    button.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			        String text = filterText.getText();
			        if (text.length() == 0) {
			          sorter.setRowFilter(null);
			        } else {
			         // sorter.setRowFilter(RowFilter.regexFilter(text));
			        	sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			        	
			        	// nochmal Mouse listener adden:
			        	
			        	//  er setzt hier immer die alten werte ein bzw. nimmt die alten ids der langen liste, ggf. table model updaten nochmals:
			        	int[] positions = table.getSelectedRows();
						//reverse array "positions[]"

						for (int i = 0; i < positions.length; i++)
						{
							int a = table.convertRowIndexToModel(positions[i]);
							System.out.println (a + "\n");
						}
		
			        	
			        	//JOptionPane.showMessageDialog(null, "nummer" + iSelectedRow);
			        	
			        	
			        	
			        	
			        	
			        	fange_gewaehlten_baustein_ab ();
			        }
			      }

			
			    });
			    button.setBounds(350,20,270,30);
			    panel_suche.add(button);
			    
			    statusfield.setBounds(630, 20, 350, 30);
			    panel_suche.add(statusfield);
			 
			   
			    
			    JButton bausteinanzeigenbutton = new JButton("Baustein anzeigen");
			    bausteinanzeigenbutton.setBounds(900,20,170,30);
			    panel_suche.add(bausteinanzeigenbutton);
			    bausteinanzeigenbutton.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						JFrame bausteinframeanzeige = new JFrame ("Baustein");
						bausteinframeanzeige.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						bausteinframeanzeige.setBounds (150,200,700,500);
						bausteinframeanzeige.setLayout(null);
						
						// close this popup:
						JButton closepopup = new JButton("Schliessen");
						closepopup.setBounds(250,430,200,30);
						closepopup.addActionListener(new ActionListener () {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								bausteinframeanzeige.setVisible (false);
								
							}
						});
						bausteinframeanzeige.add(closepopup);
						
						 int row = table.getSelectedRow();
			    	      int column = table.getSelectedColumn();
			    	      
			    	   // hole ID Nummer der Row:
		              	int selectedrow = table.getSelectedRow();
		              	String bausteinid = table.getModel().getValueAt(selectedrow, 0).toString(); // hole Baustein ID
		              	statusfield.setText("ID Nummer des Bausteines ist:" + bausteinid);
		              	
		              	int bausteinidint = Integer.parseInt(bausteinid);
		                 // die nummer muss noch eins runter gezählt werden, da durch den einleitungstext alles verschoben ist:
		                 int num = bausteinidint;
		                 int num2 = num;
		                // bausteinta.setText(selecteditemid);
		                 // hole den Text aus entsprechender Text Datei raus:
		                 String bausteintext = "";
		                 BufferedReader inb2 = null;
		      		   try {
		      			   File speicherfile2 = new File("baustein_" + num2 + "_null"+".txt");

		      		   
		      		       inb2 = new BufferedReader(new FileReader(speicherfile2));
		      		       String zeileab = null;
		      		   	   int i = 0;
		      		       while ((zeileab = inb2.readLine()) != null) {
		      		    	   
		      		    	   zeileab.replace("<span>","\n");
		      		    	   zeileab.replace("</span>","\n");
		      		    	   zeileab.replace("<span","\n");
		      		    	   zeileab.replace("<span","\n");
		      		    	   zeileab.replace("_37_b__20_Text", "");
		      		    	   zeileab.replace("<p class=","");
		      		    	   zeileab.replace("\">", "");
		      		    	  bausteintext = bausteintext + zeileab + "\n";
		      		       
		      		       } // while
		      		   } // try
		      		       catch (Exception sd)
		      		       {
		      		    	   
		      		       }
						
						JTextArea textareabausteine = new JTextArea ();
						textareabausteine.setText(bausteintext);
						textareabausteine.setBounds(20, 20, 600, 400);
						textareabausteine.setWrapStyleWord(true);
						textareabausteine.setLineWrap(true);
						bausteinframeanzeige.add(textareabausteine);
						
						

						bausteinframeanzeige.setVisible(true);

					}
	            
			    });
			    
			    JButton bausteinkopierenbutton = new JButton("Copy");
			    bausteinkopierenbutton.setBounds(1080,20,140,30);
			    bausteinkopierenbutton.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						  
			    	      int row = table.getSelectedRow();
			    	      int column = table.getSelectedColumn();
			    	      
			    	   // hole ID Nummer der Row:
		              	int selectedrow = table.getSelectedRow();
		              	String bausteinid = table.getModel().getValueAt(selectedrow, 0).toString(); // hole Baustein ID
		              	statusfield.setText("ID Nummer des Bausteines ist:" + bausteinid);
		              	
		              	int bausteinidint = Integer.parseInt(bausteinid);
		                 // die nummer muss noch eins runter gezählt werden, da durch den einleitungstext alles verschoben ist:
		                 int num = bausteinidint;
		                 int num2 = num;
		                // bausteinta.setText(selecteditemid);
		                 // hole den Text aus entsprechender Text Datei raus:
		                 String bausteintext = "";
		                 BufferedReader inb2 = null;
		      		   try {
		      			   File speicherfile2 = new File("baustein_" + num2 + "_null"+".txt");

		      		   
		      		       inb2 = new BufferedReader(new FileReader(speicherfile2));
		      		       String zeileab = null;
		      		   	   int i = 0;
		      		       while ((zeileab = inb2.readLine()) != null) {
		      		    	   
		      		    	   zeileab.replace("<span>","\n");
		      		    	   zeileab.replace("</span>","\n");
		      		    	   zeileab.replace("<span","\n");
		      		    	   zeileab.replace("<span","\n");
		      		    	   zeileab.replace("_37_b__20_Text", "");
		      		    	   zeileab.replace("<p class=","");
		      		    	   zeileab.replace("\">", "");
		      		    	  bausteintext = bausteintext + zeileab + "\n";
		      		       
		      		       } // while
		      		   } // try
		      		       catch (Exception sd)
		      		       {
		      		    	   
		      		       }
		      		   
		                 // setze Text in die Zwischenablage ein::
		      		
		      		   StringSelection stringSelection = new StringSelection(bausteintext);
		      		   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		      		   clipboard.setContents(stringSelection, null);
		              	statusfield.setText("Baustein in Zwischenablage kopiert!");

		      		   
		      		   
		      		   bausteineta.setLineWrap(true);
		      		   bausteineta.setWrapStyleWord(true);
		      		   //  alternativ setze ihn nochmals in die bausteine textarea:
		                bausteineta.setText(bausteintext);						
					}
			    	
			    });
			    panel_suche.add(bausteinkopierenbutton);
			  
	    	      
			    
			    
		
			    /*
			    JOptionPane.showMessageDialog(null, "mouse klicked");
	                    if (e.getClickCount() == 1) {
	                    	// hole ID Nummer der Row:
	                    	int selectedrow = table.getSelectedRow();
	                    	String bausteinid = table.getModel().getValueAt(selectedrow, 0).toString(); // hole Baustein ID
		                	JOptionPane.showMessageDialog(null, "baustein id ist" + bausteinid);

	                    	int bausteinidint = Integer.parseInt(bausteinid);
	                       // die nummer muss noch eins runter gezählt werden, da durch den einleitungstext alles verschoben ist:
	                       int num = bausteinidint;
	                       int num2 = num;
	                      // bausteinta.setText(selecteditemid);
	                       // hole den Text aus entsprechender Text Datei raus:
	                       String bausteintext = "";
	                       BufferedReader inb2 = null;
	            		   try {
	            			   File speicherfile2 = new File("baustein_" + num2 + "_null"+".txt");

	            		   
	            		       inb2 = new BufferedReader(new FileReader(speicherfile2));
	            		       String zeileab = null;
	            		   	   int i = 0;
	            		       while ((zeileab = inb2.readLine()) != null) {
	            		    	   
	            		    	   zeileab.replace("<span>","\n");
	            		    	   zeileab.replace("</span>","\n");
	            		    	   zeileab.replace("<span","\n");
	            		    	   zeileab.replace("<span","\n");
	            		    	   zeileab.replace("_37_b__20_Text", "");
	            		    	   zeileab.replace("<p class=","");
	            		    	   zeileab.replace("\">", "");
	            		    	  bausteintext = bausteintext + zeileab + "\n";
	            		       
	            		       } // while
	            		   } // try
	            		       catch (Exception sd)
	            		       {
	            		    	   
	            		       }
	            		   
	                       // setze Text in die Zwischenablage ein::
	            		
	            		   StringSelection stringSelection = new StringSelection(bausteintext);
	            		   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	            		   clipboard.setContents(stringSelection, null);
	            		   
	            		   
	            		   
	            		   bausteineta.setLineWrap(true);
	            		   bausteineta.setWrapStyleWord(true);
	            		   //  alternativ setze ihn nochmals in die bausteine textarea:
	                      bausteineta.setText(bausteintext);
	                       
	                     }
	                }
	            };
	            table.addMouseListener(mouseListener);
			    
			    
			    */
			    
			    
			    
			    
			    
			    panel_suche.setVisible(true);
			
			
			
				/**          PANEL BAUSTEINE HINZUFUEGEN: **/

			    JLabel kategorienlb = new JLabel ("Kategorien:");
			    kategorienlb.setBounds(40,20,300,20);
			    panel_addbaustein.add(kategorienlb);
			    JComboBox kategoriencombo = new JComboBox ();

			    for (int i = 0; i<kategorien.length;i++)
			    {
			    	if (kategorien[i] == "" || kategorien[i] == " " || kategorien[i] == null )
			    	{ // kein eintrag hinzufügen.
			    		
			    	}
			    	else
			    	{
			    		kategoriencombo.addItem(kategorien[i]);
			    	}
			    }
			    kategoriencombo.setBounds (40,40,600,50);
			    panel_addbaustein.add(kategoriencombo);
			    
			    JLabel bausteinnamelb = new JLabel ("Bausteinname:");
			    bausteinnamelb.setBounds(40,100,300,10);
			    panel_addbaustein.add(bausteinnamelb);
			    
			    JTextField bausteinname_tf = new JTextField ();
			    bausteinname_tf.setBounds(40, 120, 600, 30);
			    panel_addbaustein.add(bausteinname_tf);
			    
			    JLabel bausteininhaltlb = new JLabel ("Bausteintext:");
			    bausteininhaltlb.setBounds(40,160,300,20);
			    panel_addbaustein.add(bausteininhaltlb);
			    
			    JTextArea bausteinhinzufuegenta = new JTextArea();
			    bausteinhinzufuegenta.setBounds(40, 190, 600, 420);
			    bausteinhinzufuegenta.setWrapStyleWord(true);
			    bausteinhinzufuegenta.setLineWrap(true);
			    panel_addbaustein.add(bausteinhinzufuegenta);
			    
			    
			    JButton hinzufuegenbutton = new JButton("Eintrag hinzufügen");
			    hinzufuegenbutton.setBounds (40,620,600,50);
			    
			    
			    
			    hinzufuegenbutton.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						// schreibe Baustein nach Vorlage:
						String neuerbausteinname = bausteinname_tf.getText();
						String neuerkategorienname = kategoriencombo.getSelectedItem().toString();
							String selecteditemid_neuekategorie = stringBetween (neuerkategorienname,"(#","#)"); // extrahiere nr.
							String selecteditemid_neuekategorie2 = "(#" +  selecteditemid_neuekategorie + "#)";
						 neuerkategorienname = neuerkategorienname.replace(selecteditemid_neuekategorie2,""); // ersetze nummer vor datei schreiben
						 						
						// erstelle nächste baustein id:
						// int neuebausteinid = bausteine.length + 1; // das funktioniert, wenn schon bausteine vorhanden sind,
						// aber wenn keine vorhanden sind, muss er bei null anfangen.
						System.out.println ("im feld steht aktuelle:" + aktuellebausteinanzahltf.getText());
						int neuebausteinid = Integer.parseInt(aktuellebausteinanzahltf.getText());
						// schreibe in gliederungsdatei:
						// format: ;(#227#)     Zahnrechnung nicht unsere Sätze;227
						try
						{
							PrintWriter pWriter = new PrintWriter(new FileWriter("gliederungsdatei.txt", true), true);
							pWriter.write(";(#" + neuebausteinid + "#)" + neuerbausteinname+";"+neuebausteinid+"\n");
							// setze bausteinid textfeld um eins hoch, damit die id stimmt beim nächsten baustein einfügen:
							int neuebausteinidpluseins_int = neuebausteinid + 1;
							String neuebausteinidpluseins = Integer.toString(neuebausteinidpluseins_int);
							aktuellebausteinanzahltf.setText(neuebausteinidpluseins);
						pWriter.close();
						JOptionPane.showMessageDialog(null, "Baustein wurde erfolgreich gespeichert!");
					
						}
						catch (Exception y)
						{}
						
						// schreiebe kategorien_gegliedert.txt:
						try
						{
							PrintWriter pWriter = new PrintWriter(new FileWriter("kategorien_gegliedert.txt", true), true);
							int neuebausteinidpluseins_int = neuebausteinid + 1;
							String neuebausteinidpluseins = Integer.toString(neuebausteinidpluseins_int);
							pWriter.write(neuebausteinidpluseins + ";" + neuerkategorienname+"\n");
						/*int neuebausteinidpluseins_int = Integer.valueOf(bausteinhinzufuegenta.getText()) + 1;
						String neuebausteinidpluseins = Integer.toString(neuebausteinidpluseins_int);
						aktuellebausteinanzahltf.setText(neuebausteinidpluseins);*/
						pWriter.close();
					
						}
						catch (Exception y)
						{}
						// kategorien_liste ist nicht nötig upzudaten, da man bei neuem baustein hinzufügen keine neue kateogrie hinzufügt.
						
						// schreiebe unterkategorien_liste.txt:
						try
						{
							PrintWriter pWriter = new PrintWriter(new FileWriter("unterkategorien_liste.txt", true), true);
						pWriter.write("    "+neuerbausteinname+"\n");
						pWriter.close();
					
						}
						catch (Exception y)
						{}
						
						
						
						// schriebe baustein datei:
						//baustein_1_null.txt
						try
						{
							int neuebausteinidpluseins_int = neuebausteinid + 1;
							String neuebausteinidpluseins = Integer.toString(neuebausteinidpluseins_int);
						FileWriter fww = new FileWriter("baustein_" + neuebausteinidpluseins + "_null.txt");
						fww.write(bausteinhinzufuegenta.getText());
						fww.flush();
						fww.close();
						}
						catch (Exception eyyyy)
						{}
						
						// leere die eingabe felder für die nächste eingabe:
						// schreibe Baustein nach Vorlage:
						bausteinname_tf.setText("");
						kategoriencombo.setSelectedIndex(0);
						bausteinhinzufuegenta.setText("");
						 
						
						
						
					}
			    	
			    } );
			    
			    panel_addbaustein.add(hinzufuegenbutton);
			    
			    
			    // erkläerungstf:
			    JTextArea erklaerungsta = new JTextArea (""
			    		+ "Eingabefelder links: \n"
			    		+ "Sie können links ganz einfach selbst neue Bausteine in die Bausteinverwaltung aufnehmen. \n"
			    		+ "Geben Sie dafür links die benötigten Werte ein \n"
			    		+ "und klicken Sie auf den Button unten, um diesen in ihre Sammlung aufzunehmen."
			    		+ ""
			    		+ "\n\n"+
			    		"Eingabefelder rechts: \n Sie können rechts neue Kategorien eintragen.\n"+
			    		"fügen Sie die Kategorie einfach an die Stelle durch hinzufügen ein, an der sie\n"+ 
			    		"erscheinen soll.\n"+
			    		"Sie können auch mehrere Kateogrien gleichzeitig hinzufügen. \n" + 
			    		"Schreiben Sie einfach jede Kategorie in das Feld rechts untereinander\n" +
			    		"Eine Kategorie pro Zeile in die Liste."
			    		
			    		);
			    erklaerungsta.setBounds(660,190,340,420);
			    erklaerungsta.setWrapStyleWord(true);
			    erklaerungsta.setLineWrap(true);
			    erklaerungsta.setVisible(true);
			    erklaerungsta.setEditable(false);
			    panel_addbaustein.add(erklaerungsta);
			    
			    // kategorien hinzufügen:
			    
			    
			   JLabel neuekategorie = new JLabel ("neue Kategorien hinzufügen:");
			    neuekategorie.setBounds(1030, 40, 300, 30);
			    panel_addbaustein.add(neuekategorie);
			    
			    JTextArea neuekategoriezumhinzufuegen_tf = new JTextArea ();
			    neuekategoriezumhinzufuegen_tf.setBounds(1030, 120, 300, 490);
			    String inhaltta = "";
			    
			    // bisherige inhalte bzw. kategorien lesen:
			    try
			    {
					    BufferedReader br = new BufferedReader(new FileReader(new File("kategorien_liste.txt")));
						String line = null;
						while((line = br.readLine()) != null) {
							inhaltta = inhaltta + line + "\n";
						}
			    }
			    catch (Exception ww)
			    {}
			    neuekategoriezumhinzufuegen_tf.setText(inhaltta);
			    
			    panel_addbaustein.add(neuekategoriezumhinzufuegen_tf);
			    
			    JButton hinzufuegenbuttonkategorie = new JButton("Kategorie hinzufügen");
			    hinzufuegenbuttonkategorie.setBounds (1030,620,300,50);
			    hinzufuegenbuttonkategorie.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try
						{
						PrintWriter pWriter = new PrintWriter(new FileWriter("kategorien_liste.txt"));
						pWriter.write(neuekategoriezumhinzufuegen_tf.getText());
						pWriter.close();
						JOptionPane.showMessageDialog(null, "Die neue Kategorienliste wurde erfolgreich gespeichert.\n"
								+ "Bitte starten Sie das Programm neu, um sie zu laden.");
						}
						catch (Exception y)
						{}
					}
			    	
			    });
			    panel_addbaustein.add(hinzufuegenbuttonkategorie);
			
			/**          PANEL SETTINGS: **/
		/*	ACHTUNG BUTTON VERSTECKT HALTEN, SOSNT SIND ALLE MUEHEVOLL GMEACHTEN BAUSTEINE WIEDER KAPUTT WEIL UEBERSCHRIEBEN
		 * JButton lade_htmldatei = new JButton ("HTML Katalog konvertieren und einlesen");
    		lade_htmldatei.setBounds(740,640, 340, 20);
    		lade_htmldatei.addActionListener(new ActionListener () {

    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    					loadobject.loadhtml ();
    			}});
    		panel_settings.add(lade_htmldatei);
    		
    		
    		
		*/
			    

			/**          PANEL IMPORTIERE FREMDE BAUSTEINE: **/
			    
			    JTextArea importierefremdta = new JTextArea ();
			    importierefremdta.setBounds(20, 40, 900, 200);
			    
			    importierefremdta.setText(""
			    		+ "Sachbearbeiterin Alpha hat sich über viele Jahre Autotexte angesammelt. \n"
			    		+ "Nun geht sie in den Ruhestand und möchte Sachbearbeiter Beta die Autotexte" + 
			    		"für die weitere Arbeit vererben" + 
			    		"Dies ist möglich, indem Alpha ihre Autotext Dateien also alle baustein_xxx.txt in den Ordner\n"
			    		+ "von Sachbearbeiter Beta kopiert."+
			    		"Weiterhin benennt sie ihre kategorien.txt und die unterkategorien und die liste.txt in "
			    		+ "_fremd.txt um => dann geht sie in den Bausteine importieren Tab und \n" + 
			    		"importiert die Bausteine.");
			    
			    
			    importierefremdta.setLineWrap(true);
			    importierefremdta.setWrapStyleWord(true);
			    importierefremdta.setEditable(false);
			    importierefremdta.setVisible(true);
			    panel_importierefremd.add(importierefremdta);
			
			/**          PANEL ABOUT: **/

			JLabel programmname = new JLabel ("Autotextverwaltung (Version 1.0)");   
			programmname.setBounds(40, 40, 400, 30);
			panel_about.add(programmname);
			
			JLabel copyright = new JLabel (" (c) 2020 - XXX");
			copyright.setBounds(40, 80, 400, 30);
			panel_about.add(copyright);
			
			JLabel developedfor = new JLabel ("Entwickelt aus Dankbarkeit - für:");
			developedfor.setBounds(40, 130, 300, 30);
			panel_about.add(developedfor);
			
			 try {
			    	
			    	Image image = Toolkit.getDefaultToolkit().getImage(main.class.getResource("logo.jpg"));
			    	Icon icon = new ImageIcon(image);
			    	JLabel logolabel = new JLabel(icon);
					logolabel.setBounds(40,150,450,60);
					panel_about.add(logolabel);
				
					  }
			    catch (Exception exk)
			    {JOptionPane.showMessageDialog(null,
			    	    "logo.png",
			    	    "Fehler beim Laden der Grafiken",
			    	    JOptionPane.ERROR_MESSAGE);}
			
	        
	        
	     // Suche rechts:
	/*		JLabel suche_rechts = new JLabel ("Suche nach Textbausteinen:");
			suche_rechts.setBounds(650, 60, 240, 20);
			mainframe.add(suche_rechts);
			
			JLabel suchbegriff_label = new JLabel ("Suchbegriff:");
			suchbegriff_label.setBounds(650, 90, 150, 20);
			mainframe.add(suchbegriff_label);
			
			JTextField suchfeld = new JTextField ();
			suchfeld.setBounds (740,90,130,20);
			mainframe.add(suchfeld);
			
			JButton suchbutton = new JButton ("Suchen");
			suchbutton.setBounds(650,120,150,20);
			mainframe.add(suchbutton);
		*/	
			// Statusleiste unten:
	/*		JLabel statusleiste = new JLabel ("Status:");
			statusleiste.setBounds(30, 530, 500, 20);
			mainframe.add(statusleiste);
			
			
			
			JButton beendenbutton = new JButton ("Beenden");
			beendenbutton.setBounds(1120, 640, 250, 30);
			beendenbutton.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}} );
			mainframe.add(beendenbutton);
			
	        
	        
	      */  
	        

		
        mainframe.add(tabbedPane);

		mainframe.setVisible(true);
		
	
	}
	static public void fange_gewaehlten_baustein_ab ()
	{
	      int row = table.getSelectedRow();
	      int column = table.getSelectedColumn();
	      
	   // hole ID Nummer der Row:
      	int selectedrow = table.getSelectedRow();
      	String bausteinid = table.getModel().getValueAt(selectedrow, 0).toString(); // hole Baustein ID
      	statusfield.setText("ID Nummer des Bausteines ist:" + bausteinid);
      	
      	int bausteinidint = Integer.parseInt(bausteinid);
         // die nummer muss noch eins runter gezählt werden, da durch den einleitungstext alles verschoben ist:
         int num = bausteinidint;
         int num2 = num;
        // bausteinta.setText(selecteditemid);
         // hole den Text aus entsprechender Text Datei raus:
         String bausteintext = "";
         BufferedReader inb2 = null;
		   try {
			   File speicherfile2 = new File("baustein_" + num2 + "_null"+".txt");

		   
		       inb2 = new BufferedReader(new FileReader(speicherfile2));
		       String zeileab = null;
		   	   int i = 0;
		       while ((zeileab = inb2.readLine()) != null) {
		    	   
		    	   zeileab.replace("<span>","\n");
		    	   zeileab.replace("</span>","\n");
		    	   zeileab.replace("<span","\n");
		    	   zeileab.replace("<span","\n");
		    	   zeileab.replace("_37_b__20_Text", "");
		    	   zeileab.replace("<p class=","");
		    	   zeileab.replace("\">", "");
		    	  bausteintext = bausteintext + zeileab + "\n";
		       
		       } // while
		   } // try
		       catch (Exception sd)
		       {
		    	   
		       }
		   
         // setze Text in die Zwischenablage ein::
		
		   StringSelection stringSelection = new StringSelection(bausteintext);
		   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		   clipboard.setContents(stringSelection, null);
      	statusfield.setText("Baustein in Zwischenablage kopiert!");

		   
		   
		   bausteineta.setLineWrap(true);
		   bausteineta.setWrapStyleWord(true);
		   //  alternativ setze ihn nochmals in die bausteine textarea:
        bausteineta.setText(bausteintext);			
	}
	
	static public String stringBetween(String original, String firstSearchString, String secondSearchString)

    {
            if (original == null || firstSearchString == null || secondSearchString == null)
            {
                    return null;
            }
            int start = original.indexOf(firstSearchString) + firstSearchString.length();
            int end = original.indexOf(secondSearchString);
            if (start < 0 || end < 0)
            {
                    return null;
            }
            return original.substring(start, end);
    }
	
	



}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class load {
	
	main mainobject = new main ();
	static String filenamehtmlfile = "KATALOG NAME 2020_04_04.html";
	// ACHTUNG ANPASSEN!
    static String path = "[PFAD INS HOME VERZEICHNIS]"; // in dem alle tausende baustein_123.txt gespeichert weren sollen mit einzelnen bausteinen
    static String dirName = "bausteine";
    static int count_kategorien = 0; // = anzahl der Hauptkategorien = oberkategorien = kategorien;
    static int count_unterkategorien = 0; // = anzahl der unterkategorien = anzahl der bausteine;

    static String [] kategorienamen = new String [1000];
    static String [] unterkategorienamen = new String [1000];
	static String [] bausteintexte  = {};
	
	public void loadxml ()
	{
		// probieren mit ersetzen und string between
		
		// erstelle Arrays mit Kategorien, Textbausteinnamen und den Texten:
				String [] kategorienxml;
				String [] textbausteinexml;
				String [] textexml;
				
				String filenamexml = "Katalogname_gekuerzt_lauffaehig-ja.xml";
				File xmlfile = new File (filenamexml);
				
				// lies gesamten text aus: String gesamterhtmlcode = "";
				String gesamterhtmlcode = "";
				String [] gesamtertbarray = new String[1001];
		        BufferedReader inx = null;
		    try {
		        inx = new BufferedReader(new FileReader(xmlfile));
		        String zeilex = null;
		        while ((zeilex = inx.readLine()) != null) {
		        	System.out.println(zeilex + "\n");
		        	Random rand = new Random();
		        	int n = 0;
		        	// Obtain a number between [0 - 49].
		        	if (zeilex.contains("w:type=\"Word.Bookmark.End\"/>")) // dann ab hier speichern bis zum ende:
		        	{
		        		// 	erstelle neue random zahl und speichere alle folgenden zeilen bis zum  .Start in diesen n rein
		        		n = rand.nextInt(1001);

		        		
		        	}
		        	gesamtertbarray[n] = gesamtertbarray[n] + zeilex;
		        	File f = new File ("baustein_"+ n + ".txt");
		        	
		        	FileWriter fw = new FileWriter (f);
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("\"</w:t></w:r></w:r></w:hlink><w:hlink w:dest=\"","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("w:val=\"Hyperlink\"/></w:rPr><w:r><w:rPr><w:rStyle w:val=\"T8\"/></w:rPr>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("		        	<w:rStyle" , "");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:t></w:r></w:p>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("w:val=\"Hyperlink\"/>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("<w:pStyle w:val=\"_37_b._20_Text\"/>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:rPr><w:r><w:t>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:t></w:r></w:r></w:hlink><w:hlink w:dest=","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:rPr></w:r></w:hlink><w:hlink w:dest=","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("<w:r><w:rPr><w:rStyle","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:t></w:r></w:r></w:hlink></w:p><w:p","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:t></w:r></w:r></w:hlink></w:p><w:p","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("xmlns:fo=\"urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0\"><w:pPr></","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("w:pPr><w:r>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("compatible:1.0\"><w:pPr></w:pPr>","");
		        	gesamtertbarray[n] = gesamtertbarray[n].replace("</w:p><w:p","");
		       

		        	

		        	fw.write(gesamtertbarray[n]);
		        	fw.flush();
		        	fw.close();
		        	
		        	if (zeilex.contains("w:type=\"Word.Bookmark.Start\"))"))
        			{
		        		// dann erhöhe speicher id für neue rand zahl, weil ab hier ist ende des vorherigen textblocks;
			        	n = rand.nextInt(1001);
        			}
		        
		        }}
		    	catch (Exception s)
		    {}
		    
		
		    
		    
		    
		    
		    
		    
	}
	
	
	
	
	
	
	
	
	
	
	public void loadword() {
		
		JOptionPane.showMessageDialog(null, "ihre katalog.txt wird jetzt importiert. \n Funktion loadword speichert jetzt auch txt");
		
		String filename = "textkatalog-ja.txt";
		File htmlfile = new File (filename);
		
		String[] split = new String [1000];
		
		BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader("Textkatalog-ja.txt"));
            String line = null;
            StringBuilder text = new StringBuilder();
            while((line = br.readLine()) != null) {
            	String regex = "-?\\d+(\\.\\d+)?";            	
            	
            			split = line.split("[0-9]"); // [31, 8, 6, 12, 19, 42]	
            	

                   }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch(IOException e) {
                }
            }
        }
        
        /*
        
        System.out.println ("ausgabe");
        for (int i = 0; i<split.length;i++)
        {
        	System.out.println("array nummer." + i + " ist: " + split[i] + "\n");
        }
        
        */
        
        
    
		
		
	
		
		
		
		
		
		// lies gesamten text aus: String gesamterhtmlcode = "";
	/*	String gesamtertxtcode = "";
        BufferedReader inx = null;
    try {
        inx = new BufferedReader(new FileReader(htmlfile));
        String zeilex = null;
        while ((zeilex = inx.readLine()) != null) {
           // System.out.println("Gelesene Zeile: " + zeile);
        	gesamtertxtcode = gesamtertxtcode + zeilex;
        	String regex = "-?\\d+(\\.\\d+)?";
        	// regex = \s \d\d.\s
        	if (zeilex.contains(regex))		// soll alle nummerierten bausteine ausfinding machen ; z.b. 22. XXX // ("\s"zahl + punkt + abstand))
        	{
        		System.out.println ("zeile regex" + zeilex);
        	}
        }}
    	catch (Exception s)
    {}
		
		*/
		
	}

	public void loadhtml() {
		
		System.out.println ("Funktion loadhtml () beginnt. lade...");
		// erstelle Arrays mit Kategorien, Textbausteinnamen und den Texten:
		String [] kategorien;
		String [] textbausteine;
		String [] texte;
		
		File htmlfile = new File (filenamehtmlfile);
		
		// lies gesamten text aus: String gesamterhtmlcode = "";
		String gesamterhtmlcode = "";
        BufferedReader inx = null;
    try {
        inx = new BufferedReader(new FileReader(htmlfile));
        String zeilex = null;
        while ((zeilex = inx.readLine()) != null) {
           // System.out.println("Gelesene Zeile: " + zeile);
        	gesamterhtmlcode = gesamterhtmlcode + zeilex;
        }}
    	catch (Exception s)
    {}
		
		

    // HOLE DIE KATEGORIEN UND DIE UNTERKATEGORIEN:

    old_kategorien_aus_grossem_text_holen (filenamehtmlfile); // holt und speichert die kategorien und die unterkategorien aus der html file
    // in die textdatein kategorie und unterkategorie.
		
    
    // TRAGE DIE KATEGORIEN UND DIE UNTERKATEGORIEN (bausteine) VON DATEI IN JAVA GUI EIN:
    read_kategorien_from_file ("kategorien.txt",true);
    read_kategorien_from_file ("unterkategorien.txt",false);
		        
		    // Ermittel die Textbausteine als lange Texte:
		    hole_textbausteine ();
		
		
		
	} 
	public static void kategorien_aus_inhaltsverzeichnis_abfangen (String htmlfile)
	{
		// hole kategorien aus dem inhaltsverziechnis direkt:
		main mainobject = new main ();
		// System.out.println ("Ermittle alle Kategorien (Gruppen, Oberkategorien)... 209\n");
        BufferedReader in = null;
    try {
        in = new BufferedReader(new FileReader(htmlfile));
        String zeile = null;
        int counter = 0;
        while ((zeile = in.readLine()) != null) {
        	
        	// hole kateogrienamen: aus inhaltsverziechnis:
        	String kategoriename = "";
        	
        	// Oberkategorie Kategorie steht zwischen:
        	// <p style="margin-top: 0.21cm; margin-bottom: 0.21cm; text-transform: uppercase">
        	// und
        	// </a></font></font></p>
        	
        	
        	
        	// adde kategorienamen in combo:
        
		

    		count_kategorien = count_kategorien+1;
    		//kategorienamen[count_kategorien] = kategoriename;
    		//System.out.println ("Kat Nr." + count_kategorien + " ist " + kategoriename+"\n" );

    		// Schreibe die Kategorien in Textfile:
    		File kategorienfile = new File ("kategorien.txt");
    		if (kategorienfile.exists ())
    			{
    				// lösche die alte vorher damit es keine probleme gibt:
    			kategorienfile.delete();
    			}
    		File unterkategorienfile = new File ("unterkategorien.txt");
    		if (unterkategorienfile.exists ())
    			{
    				// lösche die alte vorher damit es keine probleme gibt:
    			unterkategorienfile.delete();
    			}
    		
    		FileWriter fwk1  = new FileWriter (kategorienfile,true);
    		fwk1.write(kategoriename+"\n");
    		fwk1.flush();
    		fwk1.close();
    		
        } // while
    } // try
    catch (Exception yx)
    {
    	System.out.println ("konnte kategorien aus inhaltverszeichnis nicht auslesen.");
    }
		
		
	}
	public static void read_kategorien_from_file (String filename,boolean oberkategorie)
	{
		main mainobject = new main ();
	BufferedReader bufferedReader = null;
    //Der Pfad zur Textdatei
    File file = new File(filename);
    try {
        //Der BufferedReader erwartet einen FileReader. 
        //Diesen kann man im Konstruktoraufruf erzeugen.
        bufferedReader = new BufferedReader(new FileReader(file));
      String line;
      //null wird bei EOF oder Fehler zurueckgegeben
      
      // jetzt muesste man noch eine Zahl hinzufügen, dass man weiss welcher Baustein es ist, den man 
      // dann später auslesen soll:
      int counter = 0;
      while (null != (line = bufferedReader.readLine())) {
        //Zeile auf der Konsole ausgeben
          String counterstring = "("+ inttostring(counter) + ") ";
          counter = counter +1; // erhöhe counter;
    	  if (oberkategorie == true)
    	  {
    		  // fuege oberkateogrie hinzu:
          //	mainobject.kategoriencombo.addItem(counterstring + line);

    	  }
    	  if (oberkategorie == false) // fuege eine unterkateogrie hinzu:
    	  {
            	//mainobject.bausteinecombo.addItem(counterstring + line);

    	  }
    	  

      }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
      if (null != bufferedReader) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    }
	}
	
	// NEUE VARIANTE STATT DER UNTEREN WIRD, GESAMTEN TEXT LESEN IN EINE DATEI MIT EINER ZEILE UND DANN STRING BETWEEN MACHEN:
	public static void old_kategorien_aus_grossem_text_holen (String htmlfile) // alt, funktioniert zwar bis auf manche
	// dafür müsste man nochmal loopen um dien ächste zeile derü berschrift auch noch abzufangen bei <h1 class kategorie western
	// da manche bausteine in die nächste zeile verrutscht sind.
	// viel einfacher geht es aus dem inhaltsverzeichnis des katalogs die kategorien abzufangen.
	{
		main mainobject = new main ();
		// System.out.println ("Ermittle alle Kategorien (Gruppen, Oberkategorien)... 209\n");
        BufferedReader in = null;
    try {
        in = new BufferedReader(new FileReader(htmlfile));
        String zeile = null;
        int counter = 0;
        while ((zeile = in.readLine()) != null) {
           // System.out.println("Gelesene Zeile: " + zeile);
        	
        	
        	// 1. Pruefe ob die Zeile eine Kategorie ist: wenn ja Extrahiere diese KATEGORIE aus dem Code:
        	// Schema <h1 class="kategorie-western"><a name="_Toc33522153"></a>Bescheide
        	// Rücknahme 44 X	</h1>
        	/*if (zeile.contains("<h1"))
        			{
        			// grussformeln:
        		zeile.replace("class=\"kategorie-western\" style=\"page-break-before: always\">","");
        		
        			}
        	*/
        	// der erste hat immer noch ein page-break before: drinnen; alle anderen haben dann kategorie western ohne drin
        	if (zeile.contains("<h1 class=\"kategorie-western\""))
        			/*zeile.contains("<h1 class=\"kategorie-western\">") /*|| zeile.contains("</h1>")*/
        	{
        		System.out.println (zeile + "\n"); // bis hier werden alle kategorien erfasst !
        		
        		// hier passiert es, dass vllt. die kategorie in die nächste Zeile gerutscht ist:
        		// daher nochmal loopen und mit stringBetween die nächste einfangen:
        		
        		String kategoriename = "";
        	/*	if (zeile.contains("</h1>") && !zeile.contains("<h1 class=")) // das ist immer der fall wenn es in die nächste zeile rutscht , weil der string zu lang ist:
        		{
        			 kategoriename = zeile.replace("</h1>", "");
        		}
        		else
        		{
        		 kategoriename = stringBetween(zeile, "\"></a>","</h1>");
        		}*/
        		 kategoriename = stringBetween(zeile, "\"></a>","</h1>");
        		 
        		 if (! zeile.contains("</h1>")) // dann ist es in die nächste Zeile gerutscht
        		 {
        			 // hole nächste Zeile und vervollständige damit kategorie:
        		 }

        		if (kategoriename == null || kategoriename =="")
        		{
        			// keine kategorie adden (leere ist sinnlos)
        		}
        	
    			if (kategoriename.contains("lass=\"kategorie-western\">"))
    			{
    				kategoriename.replace("lass=\"kategorie-western\">", "");
    			}
    		
        			//System.out.println (kategoriename + "\n");
        		/*	kategoriename.replace("lass=","");
        			kategoriename.replace("kategorie-western","");
        			kategoriename.replace("\"","");
        			kategoriename.replace(">","");
        			kategoriename.replace("/ ","");*/
	        		//mainobject.kategoriencombo.addItem(kategoriename);

        		count_kategorien = count_kategorien+1;
        		//kategorienamen[count_kategorien] = kategoriename;
        		//System.out.println ("Kat Nr." + count_kategorien + " ist " + kategoriename+"\n" );

        		// Schreibe die Kategorien in Textfile:
        		FileWriter fwk1  = new FileWriter ("kategorien.txt",true);
        		fwk1.write(kategoriename+"\n");
        		fwk1.flush();
        		fwk1.close();
        		
        		
        	}
        	

            
        	// 2. Pruefe ob die Zeile der Name eines Textbausteins ist d.h. Unterkateogrie:
        	//<p style="margin-top: 0.21cm; page-break-after: avoid"><a name="_Toc33522154"></a>
        	//Ablehnung erneute Überprüfung</p>
           // System.out.println ("Ermittle alle Unterkateogrien (d.h. Textbausteinnamen an sich)... 243\n");

        	if (zeile.contains("<p style=\"margin-top: 0.21cm; page-break-after: avoid\"><a name="))
        	{
        		// manchmal kommt es vor, dass die textbaustein namen also die unterkateogorie über mehrere zeilen geht:
    			// dann sollte der zweite teil in der nächsten Zeile auch noch mit angefügt werden:
    			String zweiterteil_der_zeile = in.readLine()+1;
    			String komplette_zeile_der_unterkategorie = zeile+zweiterteil_der_zeile;
        		String unterkategorie = stringBetween(komplette_zeile_der_unterkategorie, "\"></a>","</p>"); // = textbausteine
        		if (unterkategorie == null || unterkategorie =="")
        		{
        			// keine unterkategorie adden (leere ist sinnlos) = keine leeren textbausteine 
        		}
        		else
        		{
        			unterkategorie.replace(" ", ""); // leere raus
        			
        			
	        		//System.out.println (kategoriename + "\n");
	        		//mainobject.bausteinecombo.addItem(unterkategorie);

        			count_unterkategorien = count_unterkategorien+1; // anzahl der bausteine anhand der bausteinnamen ermitteln 
        			// Schreibe die unter Kategorien in Textfile:
	        		FileWriter fwk2  = new FileWriter ("unterkategorien.txt",true);
	        		fwk2.write(unterkategorie+"\n");
	        		fwk2.flush();
	        		fwk2.close();

        		//unterkategorienamen[count_unterkategorien] = unterkategorie;
        		// (= hier unterkateogrienamen)
        		
        		
        	    // parts.length = anzahl der bausteine müsste gleich count_unterkategorien sein.
        	    // jetzt zugehörigen baustein reinschreiben:
        	    // also je nach count_unterkateogerien dann den parts[count] reinschrieben:
        	 /*   try {
        			fwb.write(parts[count_unterkategorien]);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        		System.out.println("503 konnte datei mit baustein nicht schreiben");
        		}
        	    
        	    */
        	    

        		}
        		
        		
    		    
        	} // if zeile contains
     		    
        
        }	// while
        
    } // try
    catch (Exception y)
    {}
     		    
     		    
	        	// <p style="margin-left: 0.64cm; font-weight: normal"> usw... text </p> sthet immer dazwischen
	        	/*if (zeile.contains("<p style=\"margin-left: 0.64cm; font-weight: normal\">"))
	        	{
	        		// manchmal kommt es vor, dass die textbaustein namen also die unterkateogorie über mehrere zeilen geht:
        			// dann sollte der zweite teil in der nächsten Zeile auch noch mit angefügt werden:
        			String zweiterteil_der_zeile = in.readLine()+1;
        			String komplette_zeile_der_unterkategorie = zeile+zweiterteil_der_zeile;
	        		String unterkategorie = stringBetween(komplette_zeile_der_unterkategorie, "\"></a>","</p>"); // = textbausteine
	        		if (unterkategorie == null || unterkategorie =="")
	        		{
	        			// keine unterkategorie adden (leere ist sinnlos) = keine leeren textbausteine 
	        		}
	        		else
	        		{
	        			unterkategorie.replace(" ", ""); // leere raus
	        			
	        			
		        		//System.out.println (kategoriename + "\n");
	        		mainobject.bausteinecombo.addItem(unterkategorie);
	        		}
	        	}
	        	*/
    System.out.println ("Alle Oberkategorien, Namen erfolgreich ermittelt und eingefügt 239\n");

    System.out.println ("Alle Unterkategorien oder Bausteinnamen erfolgreich eingefügt 267\n");

    
    
	}
	
	public static void hole_textbausteine ()
	{
        System.out.println ("Ermittle die Textbaustein Texte (lange Texte an sich) ... 315\n");

		File htmlfile = new File (filenamehtmlfile);
		
		// String schafft es nicht so viele werte zu speichern, daher zwischenspeichern:
		File txtfile1 = new File("speicher1.txt");
		if (txtfile1.exists() == false)
		{
			try {
				txtfile1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// lies gesamten text aus: String gesamterhtmlcode = "";
		String gesamterhtmlcode = "";
		FileWriter fw = null;
		try {
			fw = new FileWriter(txtfile1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader inx = null;
    try {
        inx = new BufferedReader(new FileReader(htmlfile));
        String zeilex = null;
        
        
        // Erstelle Unterordner für alle Baustein txt Datieen:
        File dir = new File(path + dirName);
        
        if (dir.mkdir()) {
        	//System.out.println ("Die Directory bausteine wurde erfolgreich in " + path + " erstellt! zeile 353");
        } else {
            //System.out.println(dir + " konnte nicht erstellt werden");
        } 
        
        
        
    	
        while ((zeilex = inx.readLine()) != null) {
           // System.out.println("Gelesene Zeile: " + zeile);
        	
        	String zeilex2 = zeilex.replace("<p style=\"margin-top: 0.21cm; page-break-after: avoid\"><a name=\"_Toc", ""); // ersetze alle
        													// einleitenden header bis auf die toc id
        	// String zeilex3 = zeilex2.replace("\"></a>",""); // nochmal lassen, damit man die kategorien spaeter noch weiss und filtern kann
        	String zeilex4 = zeilex2.replace("</p>","");
        	String zeilex5 = zeilex4.replace("        	<li/>","");
        	String zeilex6 = zeilex5.replace("<h1 class=\"kategorie-western\" style=\"page-break-before: always\"><a name=\"_Toc","");
        										// ersetze einleitenden ersten header

        	String zeilex7 = zeilex6.replace("Allgemeines Grussformeln	</h1>","");
        	String zeilex8 = zeilex7.replace("<ol>","");
        	String zeilex9 = zeilex8.replace("<ol>","");
        	String zeilex10 = zeilex9.replace("<p style=\"font-weight: normal\"><br/>\n","");
        	String zeilex11 = zeilex10.replace("<ol>","");
        	String zeilex12 = zeilex11.replace("<p style=\"margin-left: 0.64cm; font-weight: normal\">", "");
        	String zeilex13 = zeilex12.replace("<br/>","");
        	String zeilex14 = zeilex13.replace("<p style=\"font-weight: normal\">","");
        	String zeilex15 = zeilex14.replace("<li/>", "");
        	for (int i = 0; i<1000;i++)
        		{
        			zeilex15 = zeilex15.replace("<ol start=\"" + i + "\">", "");
        		}
        	String zeilex16 = zeilex15.replace("</body>","");
        	String zeilex17 = zeilex16.replace("</html>", "");
        	String zeilex18 = zeilex17.replace("33522","");
        	String zeilex19 = zeilex18.replace("\"></a>\"", "");
        	String zeilex20 = zeilex19.replace("\"<h1 class=\\\"kategorie-western\\\"><a name=\\\"_Toc\"","");
        	String zeilex21 = zeilex20.replace("<p class=\"_37_b__20_Text\">","");
        	String zeilex22 = zeilex21.replace("<span class=\"odfLiEnd\"/> </li>","");
        	String zeilex23 = zeilex22.replace("</span>","");
        	for (int i = 0; i<1000;i++)
    		{
        	    // Lies alle Textbausteine aus und schreibe sie in neue Datei mit Trenner:

    			zeilex23 = zeilex23.replace("</ol>", ";;"); // ;; ist jetzt der neue Trenner fuer den array;
    			
        		
    		}
        	
        	fw.write(zeilex23 + " ");
        	
    
        	

        }
        fw.close();
        }
    	catch (Exception s)
    {}
	
    // 	Alle Bausteine stehen jetzt mit ;; aneinandergereeiht in der speicher1.txt; diese kann nicht geöffnet werden manuell
    // das sie zu zu gross ist.
     txtfile1 = new File("speicher1.txt"); // deklarierung siehe oben genauso

     // Unterordner bausteine erstellen:
   // String dirName = "bausteine";
    
    
    // jetzt alle auslesen und abspeichern in variable:
    String [] parts = new String [1000];
    BufferedReader br = null;
    try {
        br = new BufferedReader(new FileReader(txtfile1));
        String line = null;
        while((line = br.readLine()) != null) {
            // Ganze Zeile:
            // System.out.println(line);               
            parts = line.split(";;");
        
            // ...
        }
    } catch(FileNotFoundException e) {
        e.printStackTrace();
    } catch(IOException e) {
        e.printStackTrace();
    } finally {
        if(br != null) {
            try {
                br.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    int counter = 0;
    for (int i = 0; i<parts.length;i++)
    {
    	 // erstelle für jeden Stein eine Datei.
		counter = counter +1; // erhoehe counter dass wir ids haben:
	    String counterstring = inttostring(counter);
	    String fileName = "baustein_" + counterstring + "_"  + unterkategorienamen[i] + ".txt";
	    File file = new File(path + dirName + fileName); // File erstellen;

	    // erstelle fuer jeden baustein eine datei:
	    FileWriter fwb = null;
		try {
			fwb = new FileWriter (fileName);
			fwb.flush();
			fwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("konnte baustein fw nicht initialisieren 494");
		}
    	
		// schreibe den baustein in der for schleife in den txt_XXX:
    	// System.out.println ("Parttext ist" + parts[i] + "\n //////////// ENDE BAUSTEIN NR. " + i + "//////////////// \n "); // funktioniert!
    	// Schreibe die Bausteine in die zugehörige File:
    	try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(parts[i]);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    
    }
    
    
	
	
	
	


    
    
    
    
    
    
    // jetzt alle Werte auslesen von speicher1.txt und jeden Baustein einzelen in baustein_123.txt ... baustein_xxx.txt baustein 999.txt
    // abspeichern

  
    /*
	
    BufferedReader ina = null;
   try {
       ina = new BufferedReader(new FileReader(txtfile1));
       String zeilea = null;
   	
       while ((zeilea = ina.readLine()) != null) {
       
    	   
    	   if (zeilea.contains(";;"))
    	   {
    		   zeilea.split(";;");
    		   File speicherfile2 = new File("speicherfile2.txt");
    		   FileWriter fww = new FileWriter (speicherfile2);
    		   fww.write(zeilea);
    		   fww.write("\n");
    		   fww.close();
    		   
    	   }
       
       }
       
	
	}
   catch (Exception sffd)
   {
	   
   }
   */
   
   // der doppelte ;; ist jetzt der Trenner zwischen den beiden Textblöcken in speicherfile2.txt:
   // dhaer muss jetzt von ;; bis ;; alles between ausgelesen werden und in arry geespeichert werden;
   
    /*
   BufferedReader inb = null;
   try {
	   File speicherfile2 = new File("speicherfile2.txt");

   
       inb = new BufferedReader(new FileReader(speicherfile2));
       String zeileab = null;
   	
       while ((zeileab = inb.readLine()) != null) {
       
    	   
    	   if (zeileab.contains(";;"))
    	   {
    	   }
       
       } // while
   } // try
       catch (Exception sd)
       {
    	   
       }
   
   */
   
    /*
   // trenne die bausteine am doppelten strichpunkt aus speicherfile2 und gib sie aus:
   String [] [] daten = getTableShowData();
   
   for (int i = 0; i<daten.length;i++)
   {
	   for (int j = 0; j<daten.length;j++)
	   {
		   File speicherfile3 = new File("speicher3.txt");
		   	try {
				FileWriter fw2 = new FileWriter (speicherfile3);
				if (daten[i][j] == null)
				{}
				else
				{
				fw2.write(daten[i][j]);
				fw2.flush();
				fw2.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
   }
   */
	
	} // method hole textbausteine
	
	
	

	public static double inttodouble (int i)
	{
		double d = (double) i;
		return d;
	}
	public static int doubletoint (double d)
	{
		int i = (int) d;
		return i;
	}
	public static String doubletostring (double d)
	{
		String s = String.valueOf(d);
		return s;
	}
	public static double stringtodouble(String s)
	{
		double d;
		if ( s == "")
		{
			d = Double.valueOf("0.0");

		}
		else
		{
		d = Double.valueOf(s);
		}
		return d;
	}
	
	
	
	
	public static String[][] getTableShowData(){
		BufferedReader reader;
		String zeile = null;
		String[] buffer = new String[1000];
		String[][]data = new String[1000][1000];
		int i = 0;

		try{
			reader = new BufferedReader(new FileReader("speicherfile2.txt"));
			zeile = reader.readLine();
			while(zeile != null){
				if (zeile.contains(";;")){
					buffer = zeile.split(";;");
					for(int t=0;t<buffer.length;t++){
						data[i][t] = buffer[t];
						System.out.println ("databuffer:" + data[i][t] + "\n");
					}
					++i;
				}
				zeile = reader.readLine();
			}
 
        }catch(IOException e){
            System.err.println("Data_Reader_Error: " + e);
        }
        
        return data;
	}
	public static String inttostring (int i)
	{
		String s = Integer.toString(i);
		return s;
	}
	public static int stringtoint(String string) {
		int i = Integer.parseInt(string);
		return i;
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

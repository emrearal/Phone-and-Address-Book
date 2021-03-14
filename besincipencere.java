						// TÜM KAYIRLARI YAZDIRMA SINIFI
package adresdefteri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class besincipencere {
	static JFrame f; 
	static JTable jt;
	static JScrollPane sp;
	static int sonsayac,silik;
	
	public static void tumunu () {
		
		try {   // burada txt deki toplam satýr sayýsý bulunuyor
			FileReader input = new FileReader("adresveritabani.txt");
			LineNumberReader count = new LineNumberReader(input);
			sonsayac =((int)count.lines().count());
			count.close();    
		} catch (IOException e1) {
			adresdefteri.durumbildir.setText("Veritabaný Okuma Hatasý");
			e1.printStackTrace();
		}
			
		String [][] data = new String [sonsayac/5][5];
		
		f=new JFrame("Tüm Liste / Sütun Baþlýðýna Basarak Sýralayabilirsiniz ");   
			
		try {
		      File myObj = new File("adresveritabani.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      for (int i = 0; i < sonsayac/5; i++) {     
		    	  String data1 = myReader.nextLine();
		    	  String data2 = myReader.nextLine();
		    	  String data3 = myReader.nextLine();
		    	  String data4 = myReader.nextLine();
		    	  String data5 = myReader.nextLine();
		    	  
		    	if (!data2.equals("x")) {  		    		// boþ olanlarý atla
		    	  data[i-silik][0] = data1;
				  data[i-silik][1] = data2.toUpperCase();
				  data[i-silik][2] = data3;
				  data[i-silik][3] = data4;
			      data[i-silik][4] = data5;
			      
		    	}else {
		    		silik++;
		    		
		    	}
			}
		     
		      myReader.close();
		 
		     }catch (FileNotFoundException k) {
		    	adresdefteri.durumbildir.setText("Okuma Hatasý");
		    	k.printStackTrace();
		     }
		
		String [][] bosluksuzdata = new String [(sonsayac/5)-silik][5];  // silinmiþ boþ hücreleri temizliyoruz
		
		for (int i = 0; i < (sonsayac/5)-silik; i++) {   
			
			bosluksuzdata[i][0] = data[i][0];
			bosluksuzdata[i][1] = data[i][1]; 
			bosluksuzdata[i][2] = data[i][2];
			bosluksuzdata[i][3] = data[i][3];
			bosluksuzdata[i][4] = data[i][4];
            	}
       silik=0;
       sonsayac=0;
       
		String[] column = { "KOD", "AD-SOYAD", "TELEFON","ADRES","E-MAIL" };
		          
		    jt=new JTable(bosluksuzdata,column);
		    jt.setDefaultEditor(Object.class, null);	// tabloya elle düzeltme yapılamasın
		    jt.setBounds(30,40,200,300);   
		    jt.getColumnModel().getColumn(0).setPreferredWidth(10);
		    jt.setAutoCreateRowSorter(true);
		    sp=new JScrollPane(jt);    
		    f.add(sp);          
		    f.setSize(600,400);    
		    f.setVisible(true);    
		
	}

}

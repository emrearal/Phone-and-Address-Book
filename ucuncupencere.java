			// BURASI KAYIT S�LME VEYA DE���T�RME SINIFI
package adresdefteri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ucuncupencere implements ActionListener {
	
	static JLabel baslik2;
	static JButton evet,hayir;
	static int degiskodu,satirsayisi;
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	
	private enum evethayir {
		basevet,bashayir;
	}
	
public static void eminmisin() {
	
	if (adresdefteri.adneymis.equals("")) {
		return;
	}
	yavrudugme= new JButton("Evet");
	yavrudugme2 = new JButton("Hay�r");
	yavru = new JDialog(adresdefteri.frame,"De�i�iklik Onay Ekran�",true); 
	
	baslik2= new JLabel("");
		
	yavru.setSize(300,150); 
	yavru.setLayout(null);
	yavru.setLocation(800,50);
	yavru.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	
	yavru.add(yavrudugme);
	yavru.add(yavrudugme2);
	yavru.add(baslik2);
	
	yavrudugme.setBounds(40,70,80,20);
	yavrudugme.setVisible(true);
	yavrudugme.addActionListener(new ucuncupencere());
	yavrudugme.setActionCommand(evethayir.basevet.name());
	
	yavrudugme2.setBounds(180,70,80,20);
	yavrudugme2.setVisible(true);
	yavrudugme2.addActionListener(new ucuncupencere());
	yavrudugme2.setActionCommand(evethayir.bashayir.name());
	
		if (adresdefteri.a==3) {
			baslik2.setText(adresdefteri.kodneymis+" numaral� kay�t silinsin mi ?" );
		}
		
		if (adresdefteri.a==4) {
			baslik2.setText(adresdefteri.kodneymis+" numaral� kay�t de�i�tirilsin mi ?" );
		}
		
		baslik2.setBounds(55,5,300,70);
			
		yavru.setVisible(true); 
	}
	
@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getActionCommand()==evethayir.basevet.name()) {
			yavru.setVisible(false);
			baslik2.setText("");
			
			kopyatxtac();
			
			return	;
		}
		
		if (e.getActionCommand()==evethayir.bashayir.name()) {
			yavru.setVisible(false);
			baslik2.setText("");
			adresdefteri.ekranitemizle();
			
			return;
		}
		
	}

	private void kopyatxtac() {  //Bu metodda �nce arac� olacak txt �retiliyor. eski veri taban�
								  // arac� veri taban�na yap�lmas� istenen de�i�iklikler ile aktar�l�yor
								 //  eski veri taban� siliniyor , arac� veri taban�n�n ad� eski veri taban� 
								//   olarak de�i�tiriliyor
		
		degiskodu = (Integer.parseInt(adresdefteri.kodneymis));
		
		try {             //arac� veri taban� olu�turuluyor
			File myObj = new File("kopyatxt.txt");  
			if (myObj.createNewFile()) {
		    	  System.out.println("Ge�ici Veritaban� Dosyas� Ba�ar�yla olu�turuldu");
		      } else {
		    	  System.out.println("Ge�ici Veritaban� Dosyas� Zaten Var");
		      }
		    } catch (IOException e2) {
		    	 adresdefteri.durumbildir.setText("Bir Hata Olu�tu");
		      e2.printStackTrace();
		    }
		
	try {
	      File myObj = new File("adresveritabani.txt");
	      Scanner myReader = new Scanner(myObj);
		  
	      FileWriter myWriter = new FileWriter("kopyatxt.txt",true);
	      PrintWriter p = new PrintWriter(new BufferedWriter(myWriter));
	      
	      while (myReader.hasNextLine()) {
	    	     
	    	  satirsayisi=satirsayisi+5;
	    	  	  String data1 = myReader.nextLine();
	    	      String data2 = myReader.nextLine();
	    	      String data3 = myReader.nextLine();
	    	      String data4 = myReader.nextLine();
	    	      String data5 = myReader.nextLine();
	      		 
	      		 if (Integer.parseInt(data1)!=degiskodu) {
	      			
	      			p.println(data1);
	      			p.println(data2);
	      			p.println(data3);
	      			p.println(data4);
	      			p.println(data5);
	      		 } 
	      			
	      		 if ((Integer.parseInt(data1)==degiskodu) && (adresdefteri.a==4))
	      		 
	      		 {
	      			    p.println(adresdefteri.kodneymis);
	      			    p.println(adresdefteri.adneymis.toLowerCase());
	      			    p.println(adresdefteri.telneymis);
	      			    p.println(adresdefteri.adresneymis);
	      			    p.println(adresdefteri.emailneymis);
	      			   
	      			    adresdefteri.a=0;
	      			
	      			 }	
	      			      			 
	      		 if ((Integer.parseInt(data1)==degiskodu) && (adresdefteri.a==3))
		      		 
	      		 {
	      			    p.println(adresdefteri.kodneymis);
	      			    p.println("x");
	      			    p.println("x");
	      			    p.println("x");
	      			    p.println("x");
	      			   
	      			    adresdefteri.a=0;
	      			
	      			 }	
	      			
	      } 
	      myReader.close();
	      p.close();
	      
	      	      
	     }catch (IOException k) {
	    	adresdefteri.durumbildir.setText("Hata");
	    	k.printStackTrace();
		
	     	}
	
	File f= new File("adresveritabani.txt");    //eski veritaban�n� sil
	f.delete();
	
	File g= new File("kopyatxt.txt");    // yenisinin ad�n� eskisi ile de�i�tir
	g.renameTo(f);

	degiskodu=0;
	satirsayisi=0;
 adresdefteri.ekranitemizle();
 adresdefteri.durumbildir.setText("De�i�iklik Ba�ar�yla Uyguland�");
 	
}
}
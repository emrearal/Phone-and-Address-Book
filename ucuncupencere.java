			// BURASI KAYIT SÝLME VEYA DEÐÝÞTÝRME SINIFI
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
	yavrudugme2 = new JButton("Hayýr");
	yavru = new JDialog(adresdefteri.frame,"Deðiþiklik Onay Ekraný",true); 
	
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
			baslik2.setText(adresdefteri.kodneymis+" numaralý kayýt silinsin mi ?" );
		}
		
		if (adresdefteri.a==4) {
			baslik2.setText(adresdefteri.kodneymis+" numaralý kayýt deðiþtirilsin mi ?" );
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

	private void kopyatxtac() {  //Bu metodda önce aracý olacak txt üretiliyor. eski veri tabaný
								  // aracý veri tabanýna yapýlmasý istenen deðiþiklikler ile aktarýlýyor
								 //  eski veri tabaný siliniyor , aracý veri tabanýnýn adý eski veri tabaný 
								//   olarak deðiþtiriliyor
		
		degiskodu = (Integer.parseInt(adresdefteri.kodneymis));
		
		try {             //aracý veri tabaný oluþturuluyor
			File myObj = new File("kopyatxt.txt");  
			if (myObj.createNewFile()) {
		    	  System.out.println("Geçici Veritabaný Dosyasý Baþarýyla oluþturuldu");
		      } else {
		    	  System.out.println("Geçici Veritabaný Dosyasý Zaten Var");
		      }
		    } catch (IOException e2) {
		    	 adresdefteri.durumbildir.setText("Bir Hata Oluþtu");
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
	
	File f= new File("adresveritabani.txt");    //eski veritabanýný sil
	f.delete();
	
	File g= new File("kopyatxt.txt");    // yenisinin adýný eskisi ile deðiþtir
	g.renameTo(f);

	degiskodu=0;
	satirsayisi=0;
 adresdefteri.ekranitemizle();
 adresdefteri.durumbildir.setText("Deðiþiklik Baþarýyla Uygulandý");
 	
}
}
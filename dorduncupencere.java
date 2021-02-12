						//BURASI VERÝ TABANI OLMAMASI DURUMUNDA VERÝTABANI EKLEME SINIFI

package adresdefteri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class dorduncupencere implements ActionListener {
	
	static JButton yavrudugme ;
	static JDialog yavru ;
	static JLabel etiket;
	static JLabel baslik2 ;
	
	
public static void eminmisin() {
	
	yavrudugme= new JButton("DEVAM");
	yavru = new JDialog(adresdefteri.frame,"VT Oluþturma Onayý",true); 
	etiket =new JLabel("Emin Misiniz ?");
	baslik2= new JLabel("Veri Tabaný Yok ve Oluþturulacak" );
	
	yavru.setSize(330,150); 
	yavru.setLayout(null);
	yavru.setLocation(800,50);
	yavru.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	
	etiket.setBounds(10,10,150,20);
			
	yavru.add(yavrudugme);
	yavru.add(baslik2);
	
	yavrudugme.setBounds(90,70,150,20);
	yavrudugme.setVisible(true);
	yavrudugme.addActionListener(new dorduncupencere());
	
	
	
	baslik2.setBounds(70,5,300,70);
	
	yavru.setVisible(true); 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
			yavru.setVisible(false);
			baslik2.setText("");
		
			try {          // TXT DOSYASINI  (VERÝ TABANI ) OLUÞTUR
			      File myObj = new File("adresveritabani.txt");
			      if (myObj.createNewFile()) {
			    	  adresdefteri.durumbildir.setText("Veritabaný Oluþturuldu");
			      } else {
			    	  adresdefteri.durumbildir.setText("Veritabaný Dosyasý Zaten Var");
			      }
			    } catch (IOException e2) {
			    	 adresdefteri.durumbildir.setText("Bir Hata Oluþtu");
			      e2.printStackTrace();
			    }
			
			
			try {   // bos veri tabanýna 1 numaralý ilk kaydý ekle. 
				  FileWriter myWriter = new FileWriter("adresveritabani.txt",true);
			      PrintWriter p = new PrintWriter(new BufferedWriter(myWriter));
			      p.println(1);
			      p.println("hasan emre aral");
			      p.println("+90 544 3760115");
			      p.println("Büyükçekmece / Istanbul");
			      p.println("emre@aral.web.tr");
			      
			      p.close();
			     
			      
			} catch (IOException i) {
			    	adresdefteri.durumbildir.setText("Veritabaný Yazma Hatasý");
			      i.printStackTrace();
			
		
			return	;
		}
		
		}
}

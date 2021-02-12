						//BURASI VER� TABANI OLMAMASI DURUMUNDA VER�TABANI EKLEME SINIFI

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
	yavru = new JDialog(adresdefteri.frame,"VT Olu�turma Onay�",true); 
	etiket =new JLabel("Emin Misiniz ?");
	baslik2= new JLabel("Veri Taban� Yok ve Olu�turulacak" );
	
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
		
			try {          // TXT DOSYASINI  (VER� TABANI ) OLU�TUR
			      File myObj = new File("adresveritabani.txt");
			      if (myObj.createNewFile()) {
			    	  adresdefteri.durumbildir.setText("Veritaban� Olu�turuldu");
			      } else {
			    	  adresdefteri.durumbildir.setText("Veritaban� Dosyas� Zaten Var");
			      }
			    } catch (IOException e2) {
			    	 adresdefteri.durumbildir.setText("Bir Hata Olu�tu");
			      e2.printStackTrace();
			    }
			
			
			try {   // bos veri taban�na 1 numaral� ilk kayd� ekle. 
				  FileWriter myWriter = new FileWriter("adresveritabani.txt",true);
			      PrintWriter p = new PrintWriter(new BufferedWriter(myWriter));
			      p.println(1);
			      p.println("hasan emre aral");
			      p.println("+90 544 3760115");
			      p.println("B�y�k�ekmece / Istanbul");
			      p.println("emre@aral.web.tr");
			      
			      p.close();
			     
			      
			} catch (IOException i) {
			    	adresdefteri.durumbildir.setText("Veritaban� Yazma Hatas�");
			      i.printStackTrace();
			
		
			return	;
		}
		
		}
}

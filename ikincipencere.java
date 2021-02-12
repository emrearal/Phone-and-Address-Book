
  // BURASI YENÝ KAYIT YAZDIRMA PENCERESÝNÝN SINIFI

package adresdefteri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class ikincipencere implements ActionListener {
	
	
	static JButton yavrudugme ;
	static JButton yavrudugme2 ;
	static JDialog yavru ;
	
	static JLabel baslik2 ;
	
	private enum evethayir {
			basevet,bashayir;
		}
		
	public static void eminmisin() {
		yavrudugme= new JButton("Evet");
		yavrudugme2 = new JButton("Hayýr");
		yavru = new JDialog(adresdefteri.frame,"Kayýt Onay Ekraný",true); 
		
		baslik2= new JLabel("" );
		
		String z5 =adresdefteri.adneymis.substring(0,Math.min(adresdefteri.adneymis.length(),15));
		
		baslik2 = new JLabel(z5.toUpperCase()+"...adlý kayýt oluþturulsun mu ?" );
		baslik2.setBounds(25,5,300,70);
		
		yavru.setSize(350,150); 
		yavru.setLayout(null);
		yavru.setLocation(800,50);
		yavru.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		yavru.add(yavrudugme);
		yavru.add(yavrudugme2);
		yavru.add(baslik2);
		yavrudugme.setBounds(40,70,80,20);
		yavrudugme.setVisible(true);
		yavrudugme.addActionListener(new ikincipencere());
		yavrudugme.setActionCommand(evethayir.basevet.name());
		
		yavrudugme2.setBounds(180,70,80,20);
		yavrudugme2.setVisible(true);
		yavrudugme2.addActionListener(new ikincipencere());
		yavrudugme2.setActionCommand(evethayir.bashayir.name());
		
		yavru.setVisible(true); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getActionCommand()==evethayir.basevet.name()) {
			yavru.setVisible(false);
			baslik2.setText("");
			adresdefteri.sonsayac=0;
			
			try {   // burada txt deki toplam satýr sayýsý /5+1 yapýlarak son kayýt numarasý bulunuyor
				FileReader input = new FileReader("adresveritabani.txt");
				LineNumberReader count = new LineNumberReader(input);
				adresdefteri.sonsayac = (((int)count.lines().count()/5)+1);
				count.close();    
			} catch (IOException e1) {
				adresdefteri.durumbildir.setText("Veritabaný Okuma Hatasý");
				e1.printStackTrace();
			}
				
				try {   // kaydý veri tabanýna ekle
					  FileWriter myWriter = new FileWriter("adresveritabani.txt",true);
				      PrintWriter p = new PrintWriter(new BufferedWriter(myWriter));
				      
				      p.println(adresdefteri.sonsayac);
				      p.println(adresdefteri.adneymis.toLowerCase());
				      p.println(adresdefteri.telneymis);
				      p.println(adresdefteri.adresneymis);
				      p.println(adresdefteri.emailneymis);
				      
				      String koddakac = String.valueOf(adresdefteri.sonsayac);
				      adresdefteri.durumbildir.setText(" Veri Kaydedildi" );
				      adresdefteri.etkkod.setText(koddakac);
				      p.close();
				   
				      adresdefteri.sonsayac=0;
				    adresdefteri.kodneymis=koddakac;
				    
				      
				} catch (IOException i) {
				    	adresdefteri.durumbildir.setText("Veritabaný Yazma Hatasý");
				      i.printStackTrace();
				    }
						
			return;
		}
		
		if (e.getActionCommand()==evethayir.bashayir.name()) {
			yavru.setVisible(false);
			baslik2.setText("");
			adresdefteri.ekranitemizle();
			
			return;
		}
		
	}
}

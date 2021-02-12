					// BURASI ANA SINIF // 
package adresdefteri;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class adresdefteri implements ActionListener {

	static JRadioButton yarat,bul,sil,degistir,tumu;
	static JButton secim,cls,sonraki  ;
	static ButtonGroup grup ;
	static JFrame frame; 
	static JPanel panel; 
	static JLabel baslik,etksoyad,etktel,etkadres,etkemail,durumbildir,copyright,etkkod,no,vers;
	static JTextField adsoyad,tel,adres,email; 
	static String adneymis,telneymis,adresneymis,emailneymis,yildizsizyildizli;
	static int a=0,b=0,h=0,m=0,sonsayac=0,satirsayisi=0,yildizlar=0,yildizpozisyon;
	static String[][]nurcanlar = new String[110][5];
	static String kodneymis =new String();
	static char sonkarakter;
	
	private enum actions {
	    tmz,ok,sonr ;
	  }

	public static void ekranioku() {    // Ekranda yaz�lanlar� de�i�kenlere aktar
		
		adneymis = adsoyad.getText().trim();    // .trim metodu ile ba�taki ve sondaki bo�luklardan kurtuluyoruz. 
		telneymis=tel.getText().trim();
		adresneymis=adres.getText().trim();
		emailneymis=email.getText().trim();
	}

	public static void yildizlikayitbulma() {
		
		int n = adneymis.length();
		yildizlar = n - adneymis.replace("*", "").length();  	          // ka� tane yildiz var 
		yildizpozisyon = adneymis.indexOf("*") ;  						  // yildiz sol ba�tan ka��nc� pozisyonda ?
		sonkarakter = adneymis.charAt(n-1);                               //son karakter nedir? 
			
			if (yildizlar>1 ) {                                           //y�ld�z birden fazlaysa
				ekranitemizle();
				durumbildir.setText("Sadece 1 adet y�ld�z kullan�n ");
				ekranioku();
				return;
				}
			
			if  (yildizpozisyon<3) {                                     //y�ld�zdan �nce en az 3 karakter yoksa
				ekranitemizle();
				durumbildir.setText(" Y�ld�z'dan �nce en az 3 karakter olmal�");
				ekranioku();
				return;
			}
			
			 if (sonkarakter!='*') {                                      // y�ld�z son karakter de�ilse
				 ekranitemizle();
				 durumbildir.setText("Y�ld�z en sona gelmeli");
				 ekranioku();
				 return;
				 
			 }
		
		b=0;                                                 // buraya kadar geldiyse hata yoktur. okumaya ba�l�yoruz
		m=0;
		
		yildizsizyildizli=adneymis.substring(0, yildizpozisyon);
		
			try {
			      File myObj = new File("adresveritabani.txt");
			      Scanner myReader = new Scanner(myObj);
	
			      while (myReader.hasNextLine()) {
			    	  
			    	  satirsayisi=satirsayisi+5;
			    	    
			    	      String data1 = myReader.nextLine();
				    	  String data2 = myReader.nextLine();
				    	  String data3 = myReader.nextLine();
				    	  String data4 = myReader.nextLine();
				    	  String data5 = myReader.nextLine();
			    	  if (data2.length() >= yildizsizyildizli.length()) {  //veri aranandan daha k�sa olmas�n
			      		
			    		 if (data2.contains(yildizsizyildizli.toLowerCase())) { // sadece 5'in katlar�n� oku. isimler orada yaz�l�
			      			 
			    			 	nurcanlar[h][0]= data1;
				      			nurcanlar[h][1]= data2;
				      			nurcanlar[h][2]= data3;
				      			nurcanlar[h][3]= data4;
				      			nurcanlar[h][4]= data5;
			      			h++;
			      			b++;
			      			
			    	  }
			      }	 
			    		 
			    		 if (b>99) {
			    			 break;
			    		 }
			      			
			      }  
			      
			  satirsayisi=0; 
			         
			      if (b==0) {
			    	  
			    	  durumbildir.setText("Kay�t Bulunamad�");
			    	  h=0;
			    	  myReader.close();
			    	  return;
			      }
			      durumbildir.setText(b+ " adet kay�t bulundu");
			      h=0;
			      myReader.close();
			 
			     }catch (FileNotFoundException k) {
			    	durumbildir.setText("Okuma Hatas�");
			    	k.printStackTrace();
			     }
			
				adsoyad.setText(nurcanlar[0][1].toUpperCase());    // ilkini yazd�r. di�erlerinin yaz�lmas� i�in "sonraki tu�lanmal�"
				adsoyad.setCaretPosition( 0 );                    //soldan sa�a yazd�r
				tel.setText(nurcanlar[0][2]);
				adres.setText(nurcanlar[0][3]);
				adres.setCaretPosition( 0 );     
				email.setText(nurcanlar[0][4]);		
			    etkkod.setText(nurcanlar[0][0]);
			    kodneymis=nurcanlar[0][0];
		
	}
	
	public static void kisitespit()  {    // Varsa buldu�un e�le�enlerin hepsini 2 boyutlu nurcanlar matrixine yazd�r
		
		b=0;    //e�le�en ki�i say�s�n� s�f�rla. ��nk� ba�tan ba�l�yoruz 
	    m=0;
		
		if (adneymis.length()<4) {    // // ad alan� 4 karakterden az ise i�lem yapma
			durumbildir.setText("Arama ��in En Az 4 Karakter Girin");
			return;
		}
			try {
			      File myObj = new File("adresveritabani.txt");
			      Scanner myReader = new Scanner(myObj);
				      
			      while (myReader.hasNextLine() ) {
			    	  satirsayisi=satirsayisi+5;   
			    	  String data1 = myReader.nextLine();
			    	  String data2 = myReader.nextLine();
			    	  String data3 = myReader.nextLine();
			    	  String data4 = myReader.nextLine();
			    	  String data5 = myReader.nextLine();
			      			
			    		 if (data2.equals(adneymis.toLowerCase()))  { // isim sat�r� e�le�iyor ise ...
			      				
			      			nurcanlar[h][0]= data1;
			      			nurcanlar[h][1]= data2;
			      			nurcanlar[h][2]= data3;
			      			nurcanlar[h][3]= data4;
			      			nurcanlar[h][4]= data5;
			      			h++;
			      			b++;
			      			
			    	  }
			    		 
			    		 if (b>99) {
			    			 break;
			    		 }
			      			
			      } 
			      
			  satirsayisi=0; 
			         
			      if (b==0) {
			    	  
			    	  durumbildir.setText("Bulunamad�. Y�ld�z '*' Kullanmay� Deneyin");
			    	  h=0;
			    	  myReader.close();
			    	  return;
			      }
			      durumbildir.setText(b+ " adet kay�t bulundu");
			      h=0;
			      myReader.close();
			 
			     }catch (FileNotFoundException k) {
			    	durumbildir.setText("Okuma Hatas�");
			    	k.printStackTrace();
			     }
			
				adsoyad.setText(nurcanlar[0][1].toUpperCase());    // ilkini yazd�r. di�erlerinin yaz�lmas� i�in "sonraki tu�lanmal�"
				adsoyad.setCaretPosition( 0 );                    //soldan sa�a yazd�r
				tel.setText(nurcanlar[0][2]);
				adres.setText(nurcanlar[0][3]);
				adres.setCaretPosition( 0 );     
				email.setText(nurcanlar[0][4]);		
			    etkkod.setText(nurcanlar[0][0]);
			    kodneymis=nurcanlar[0][0];
		
		} 
	
	public static void ekranitemizle() {
		
		adneymis="";
	    telneymis="";
	    adresneymis="";
	    emailneymis="";
	    kodneymis="";
	  
	    adsoyad.setText(adneymis);
		tel.setText(telneymis);
		adres.setText(adresneymis);
		email.setText(emailneymis);
		etkkod.setText("");
		
		durumbildir.setText("");
		sonsayac=0;
		b=0;
		yildizlar=0;
		yildizpozisyon=0;
		
	}
	
	public static void databasevarmi() {   // VER�TABANI DOSYASI MEVCUT MU ? 
		
		try {   
			FileReader input = new FileReader("adresveritabani.txt");
			LineNumberReader count = new LineNumberReader(input);
			count.close();    
		} catch (IOException e1) {
			adresdefteri.durumbildir.setText("Veritaban� Mevcut De�il");
			dorduncupencere.eminmisin();
			e1.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		yarat = new JRadioButton("Yeni Kay�t");
		yarat.setBounds(10,90,80,20);
		
		bul  = new JRadioButton("Kay�t Ara");
		bul.setBounds(95,90,80,20);
		
		sil=new JRadioButton("Kay�t Sil");
		sil.setBounds(180,90,80,20);
		
		degistir=new JRadioButton("De�i�tir");
		degistir.setBounds(265,90,80,20);
		
		tumu=new JRadioButton("T�m Kay�tlar");
		tumu.setBounds(350, 90, 120, 20);
		
		secim = new JButton("TAMAM");
		secim.setBounds(155,375,160,30);
		secim.addActionListener(new adresdefteri());
		secim.setActionCommand(actions.ok.name());
		
		cls=new JButton("TEM�ZLE");
		cls.setBounds(20,375,100,30);
		cls.addActionListener(new adresdefteri());
		cls.setActionCommand(actions.tmz.name());
		
		sonraki=new JButton("SONRAK�");
		sonraki.setBounds(340,375,100,30);
		sonraki.addActionListener(new adresdefteri());
		sonraki.setActionCommand(actions.sonr.name());
		
		durumbildir=new JLabel("");
		durumbildir.setBounds(150,335,300,30);
				
		copyright=new JLabel("� 2021 HasanEmreAral");
		copyright.setFont (new Font("Serif", Font.PLAIN, 12));
		copyright.setBounds(340,415, 160,15);
		
		vers=new JLabel("Vers. 1.1w");
		vers.setFont (new Font("Serif", Font.PLAIN, 12));
		vers.setBounds(400,430, 160,15);
		
		etksoyad=new JLabel("AD-SOYAD");
		etksoyad.setBounds(40, 150, 100, 30);
		
		etktel=new JLabel("TELEFON");
		etktel.setBounds(40,200,100,30);
		
		etkadres=new JLabel ("ADRES");
		etkadres.setBounds(40,250,100,30);
			
		etkemail=new JLabel ("E-MAIL");
		etkemail.setBounds(40,300,100,30);
		
		etkkod=new JLabel("");
		etkkod.setBounds(380, 150, 200, 30);
		etkkod.setFont (new Font("Serif", Font.PLAIN, 20));
		
		no=new JLabel("#");
		no.setBounds(370,150,10,30);
		no.setFont (new Font("Serif", Font.PLAIN, 20));
		
		adsoyad=new JTextField();
		adsoyad.setBounds(150, 150, 200, 30);
		
		tel=new JTextField();
		tel.setBounds(150,200,200,30);
		
		adres=new JTextField();
		adres.setBounds(150,250,200,30);
		
		email=new JTextField();
		email.setBounds(150,300,200,30);
		
		grup = new ButtonGroup();
		grup.add(yarat);
		grup.add(bul);
		grup.add(sil);
		grup.add(degistir);
		grup.add(tumu);
		
		baslik = new JLabel("ADRES ve TELEFON DEFTER�");
		baslik.setFont (new Font("Serif", Font.PLAIN, 25));
		baslik.setBounds(75,25,350,30);
		
		frame = new JFrame("K���LER");
		panel = new JPanel();
		
		frame.setSize(475,490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400,150);
		
		panel.setLayout(null);
		frame.add(panel);
		
		panel.add(baslik);
		panel.add(yarat);
		panel.add(bul);
		panel.add(secim);
		panel.add(etksoyad);
		panel.add(etktel);
		panel.add(etkadres);
		panel.add(etkemail);
		panel.add(adsoyad);
		panel.add(tel);
		panel.add(adres);
		panel.add(email);
		panel.add(durumbildir);
		panel.add(cls);
		panel.add(sonraki);
		panel.add(copyright);
		panel.add(etkkod);
		panel.add(no);
		panel.add(sil);
		panel.add(degistir);
		panel.add(vers);
		panel.add(tumu);
	
		frame.setVisible(true);
		
		databasevarmi();
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand()==actions.ok.name()) {
		
		if (yarat.isSelected()) {
			
			ekranioku();
			
			if ((adresdefteri.adneymis.length()<4) ){    // ad alan� 4 karakterden az ise i�lem yapma
				adresdefteri.durumbildir.setText("AD-SOYAD En Az 4 Karakter Olmal�");
				return;
			}	
				
			if ((adneymis.contains("*") )){    // ad alan� 4 karakterden az ise i�lem yapma
				adresdefteri.durumbildir.setText("AD-SOYAD Kayd�nda Y�ld�z Olamaz");
				return;
			}	
			
			if (adresdefteri.adneymis.equals("")) {
				return;
			}
					
			ikincipencere.eminmisin();
			
			return;
		}
		
		if (bul.isSelected()) {
	
			ekranioku();
			if (adneymis.contains("*")) {             // y�ld�z varsa
				yildizlikayitbulma();
				return;
					
			}
			
			kisitespit();
			
			return;
		}
		
		if (sil.isSelected()) {
			
			ekranioku();
			
			if (kodneymis.equals("") )   {      //ekranda kod yoksa i�lem yapma
							
				ekranitemizle();
				return;
				}
				
				a=3;
				ucuncupencere.eminmisin();
				return;	
		}
		
		if (degistir.isSelected() ){
			
			ekranioku();
			
			if (kodneymis.equals("") )   {
							
				ekranitemizle();
				return;
				}
				
				a=4;
				ucuncupencere.eminmisin();
				return;	
		}
		
		
		if (tumu.isSelected()) {
			
			ekranitemizle();
			besincipencere.tumunu();
		}
		
		}
		

	if (e.getActionCommand()==actions.tmz.name()) {
		
		ekranitemizle();
		
	}
	
	if (e.getActionCommand()==actions.sonr.name()) {     //sonraki tu�land�
		
		if (b>1) {                                           // e�er birden fazla bulduysan devam et
			 
			m++;
			
				if (m>=b) {                                 // sonuncuyu da ekrana yazd�rd�ysan s�f�rla ve ba�a d�n. 
					m=0;
					b=0;
					return;
				}
			
			adsoyad.setText(nurcanlar[m][1].toUpperCase()); //birinciden sonrakileri burada her bas��ta ekrana yazd�r. 
			adsoyad.setCaretPosition( 0 );
			tel.setText(nurcanlar[m][2]);
			adres.setText(nurcanlar[m][3]);
			adres.setCaretPosition( 0 );
			email.setText(nurcanlar[m][4]);	
			etkkod.setText(nurcanlar[m][0]);
			kodneymis=nurcanlar[m][0];
		
	}
	}
}
}
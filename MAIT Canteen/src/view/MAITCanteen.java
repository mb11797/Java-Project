package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import modelcontroller.CanteenDatabase;

public class MAITCanteen implements ActionListener {

	private static final Icon Icon = null;
	JFrame window;
	JLabel title;
	
	//ITEMS panel
	JPanel pnlITEMS;
	JButton btnSamosa,btnDOSA,btnChilliPotato,btnTikki,btnPizza,btnFingerChips;
	JLabel lblSamosa,lblDOSA,lblChilliPotato,lblTikki,lblPizza,lblFingerChips;
	JTextField priceSamosa,priceDOSA,priceChilliPotato,priceTikki,pricePizza,priceFingerChips;
	
	//CRUD-Navigation panel
	JPanel pnlCRUD_Navig;
	JLabel itemName,itemPrice;
	JButton btnFirst,btnPrev,btnNext,btnLast;
	JButton btnUpdate,btnSearch,btnClear;
	JTextField txtName,txtPrice;
	
	//Total panel
	JPanel pnlTotal;
	JButton btnTotal;
	JLabel total;
	JTextField txtTotal;
	
	//Database
	CanteenDatabase dbops;
	
	
	public MAITCanteen()
	{
		createGUI();
	}
	
	public void createGUI()
	{
		window = new JFrame("MAIT CANTEEN ver.1.0");
		
	//	window.setShape(new Shape);
		
	//	Setting the background of app
		
		ImageIcon imageRef = new ImageIcon("C:\\Users\\RVBMR\\Pictures\\deep-fried-food-748291_w1020h450c1cx509cy249.jpg");
		JLabel hostImage = new JLabel(imageRef);
	//	hostImage.setSize(null);
		window.setContentPane(hostImage);
		
		
	//	Items panel begins
		
		pnlITEMS = new JPanel(new GridLayout(6,2));
		pnlITEMS.setBorder(BorderFactory.createTitledBorder("ITEMS"));
		pnlITEMS.setBackground(Color.YELLOW);
		
		
		btnSamosa = new JButton("Samosa");
		btnSamosa.setBackground(Color.ORANGE);
		pnlITEMS.add(btnSamosa,BorderLayout.WEST);
		lblSamosa = new JLabel("Rs. 10 x ");
		pnlITEMS.add(lblSamosa);
		priceSamosa =  new JTextField(2);
		priceSamosa.setText("0");
		pnlITEMS.add(priceSamosa,BorderLayout.EAST);
		btnSamosa.addActionListener(this);
		
		btnDOSA = new JButton("DOSA");
		btnDOSA.setBackground(Color.YELLOW);
		pnlITEMS.add(btnDOSA);
		lblDOSA = new JLabel("Rs. 50 x ");
		pnlITEMS.add(lblDOSA);
		priceDOSA =  new JTextField(2);
		priceDOSA.setText("0");
		pnlITEMS.add(priceDOSA);
		btnDOSA.addActionListener(this);
		
		btnChilliPotato = new JButton("Chilli Potato");
		btnChilliPotato.setBackground(Color.RED);
		pnlITEMS.add(btnChilliPotato);
		lblChilliPotato = new JLabel("Rs. 60 x ");
		pnlITEMS.add(lblChilliPotato);
		priceChilliPotato =  new JTextField(2);
		priceChilliPotato.setText("0");
		pnlITEMS.add(priceChilliPotato);
		btnChilliPotato.addActionListener(this);
		
		btnTikki = new JButton("Tikki");
		btnTikki.setBackground(Color.MAGENTA);
		pnlITEMS.add(btnTikki);
		lblTikki = new JLabel("Rs. 20 x ");
		pnlITEMS.add(lblTikki);
		priceTikki =  new JTextField(2);
		priceTikki.setText("0");
		pnlITEMS.add(priceTikki);
		btnTikki.addActionListener(this);
		
		btnPizza = new JButton("Pizza");
		btnPizza.setBackground(Color.ORANGE);
		pnlITEMS.add(btnPizza);
		lblPizza = new JLabel("Rs. 100 x ");
		pnlITEMS.add(lblPizza);
		pricePizza =  new JTextField(2);
		pricePizza.setText("0");
		pnlITEMS.add(pricePizza);
		btnPizza.addActionListener(this);
		
		btnFingerChips = new JButton("Finger Chips");
		btnFingerChips.setBackground(Color.orange);
		pnlITEMS.add(btnFingerChips);
		lblFingerChips = new JLabel("Rs. 40 x ");
		pnlITEMS.add(lblFingerChips);
		priceFingerChips =  new JTextField(2);
		priceFingerChips.setText("0");
		pnlITEMS.add(priceFingerChips);
		btnFingerChips.addActionListener(this);
		
		FlowLayout decorate = new FlowLayout();
	//	GridLayout decorate = new GridLayout();
	//	CardLayout decorate = new CardLayout();
	//	BoxLayout decorate = new BoxLayout(btnChilliPotato, 1);
	//	GroupLayout decorate = new GroupLayout(pnlITEMS);
		window.setLayout(decorate);
		
		pnlITEMS.setLocation(10, 10);
		pnlITEMS.setSize(200, 200);
		
	//	CRUD-Navigation panel begins
		
		pnlCRUD_Navig = new JPanel(new GridLayout(3,3));
		pnlCRUD_Navig.setBorder(BorderFactory.createTitledBorder("Admin Controls"));
		pnlCRUD_Navig.setBackground(Color.YELLOW);
		
		itemName = new JLabel("Item Name ");
		txtName = new JTextField(15);
		pnlCRUD_Navig.add(itemName);
		pnlCRUD_Navig.add(txtName);
		
		itemPrice = new JLabel("Item Price ");
		txtPrice = new JTextField(10);
		pnlCRUD_Navig.add(itemPrice);
		pnlCRUD_Navig.add(txtPrice);
		
		btnFirst = new JButton("<<");
		btnFirst.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnFirst);
		btnFirst.addActionListener(this);
		
		btnPrev = new JButton("<");
		btnPrev.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnPrev);
		btnPrev.addActionListener(this);
		
		btnNext = new JButton(">");
		btnNext.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnNext);
		btnNext.addActionListener(this);
		
		btnLast = new JButton(">>");
		btnLast.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnLast);
		btnLast.addActionListener(this);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		
		btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.setBackground(Color.blue);
		pnlCRUD_Navig.add(btnClear);
		btnClear.addActionListener(this);
		
		
		pnlCRUD_Navig.setSize(100, 100);
		
	//	total panel begins
		
		pnlTotal = new JPanel();
		pnlTotal.setBackground(Color.ORANGE);
		
		btnTotal = new JButton("Total");
		btnTotal.setBackground(Color.blue);
		pnlTotal.add(btnTotal);
		btnTotal.addActionListener(this);
		
		
		total = new JLabel(" Rs. ");
		txtTotal = new JTextField(10);
		pnlTotal.add(total);
		pnlTotal.add(txtTotal);
		
		
		window.add(pnlITEMS,BorderLayout.NORTH);
		window.add(pnlCRUD_Navig,BorderLayout.CENTER);
		window.add(pnlTotal,BorderLayout.SOUTH);
		
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1040, 450);
		window.setVisible(true);
	
	//	Connecting Database 
		dbops = new CanteenDatabase();
		dbops.createConnection();
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getActionCommand() == "Samosa")
		{
			dbops.rcrSamosa(window,priceSamosa);
		}
		else if(ae.getActionCommand() == "DOSA")
		{
			dbops.rcrDOSA(window,priceDOSA);
		}
		else if(ae.getActionCommand() == "Chilli Potato")
		{
			dbops.rcrChilliPotato(window,priceChilliPotato);
		}
		else if(ae.getActionCommand() == "Tikki")
		{
			dbops.rcrTikki(window,priceTikki);
		}
		else if(ae.getActionCommand() == "Pizza")
		{
			dbops.rcrPizza(window,pricePizza);
		}
		else if(ae.getActionCommand() == "Finger Chips")
		{
			dbops.rcrFingerChips(window,priceFingerChips);
		}
		else if(ae.getActionCommand() == "<<")
		{
			dbops.rcrFirst(window,txtName,txtPrice);
		}
		else if(ae.getActionCommand() == "<")
		{
			dbops.rcrPrev(window,txtName,txtPrice);
		}
		else if(ae.getActionCommand() == ">")
		{
			dbops.rcrNext(window,txtName,txtPrice);
		}
		else if(ae.getActionCommand() == ">>")
		{
			dbops.rcrLast(window,txtName,txtPrice);
		}
		else if(ae.getActionCommand() == "Update")
		{
			dbops.rcrUpdate(window,txtName,txtPrice,lblSamosa,lblDOSA,lblChilliPotato,lblTikki,lblPizza,lblFingerChips);
		}
		else if(ae.getActionCommand() == "Search")
		{
			dbops.rcrSearch(window,txtName,txtPrice);
		}
		else if(ae.getActionCommand() == "Clear")
		{
			dbops.rcrClear(window,txtName,txtPrice,priceSamosa,priceDOSA,priceChilliPotato,priceTikki,pricePizza,priceFingerChips,txtTotal);
		}
		else if(ae.getActionCommand() == "Total")
		{
			dbops.rcrTotal(window,priceSamosa,priceDOSA,priceChilliPotato,priceTikki,pricePizza,priceFingerChips,txtTotal);
		}
	}
	
}


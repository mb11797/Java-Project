package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelcontroller.DataHandler;

public class CarDealer implements ActionListener{
	//Container
	JFrame frmMain;
	
	//Sub-Container
	JPanel pnlCRUD,pnlNavig;
	
	//Controls for Container
	JLabel lblCarID,lblCarName,lblCarPrice;
	
	JTextField txtCarID,txtCarName,txtCarPrice;
	
	//Controls for sub-container(panelCRUD)
	JButton btnInsert,btnUpdate,btnDelete,btnSearch,btnClear;
	
	//Controls for sub-container(panelNavigation)
	JButton btnFirst,btnPrev,btnNext,btnLast;
	
	//MenuBar
	JMenuBar menuBar;
	//Menu
	JMenu MyFile,MyEdit;
	//MenuItems
	JMenuItem MyNew,MyOpen;
	JMenuItem MyCopy,MyPaste;
	
	DataHandler dbops;
	
	public CarDealer()
	{
		CreateGUI();
	}
	
	public void CreateGUI()
	{
		frmMain = new JFrame("Car Dealer App Windows ver-1.0");
		
		ImageIcon imageref = new ImageIcon("C:\\Users\\RVBMR\\Pictures\\laferrari-aperta-hero-952x563.jpg");
		JLabel hostimage = new JLabel(imageref);
		frmMain.setContentPane(hostimage);
		
		
//		setting the Menu Bar
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLUE);
		
		MyFile = new JMenu("MyFile");
		MyFile.setBackground(Color.BLUE);
		
		MyEdit = new JMenu("MyEdit");
		MyEdit.setBackground(Color.BLUE);
		
		MyNew = new JMenuItem("MyNew");
		MyNew.setBackground(Color.YELLOW);
		MyNew.addActionListener(this);
		
		MyOpen = new JMenuItem("MyOpen");
		MyOpen.setBackground(Color.MAGENTA);
		MyOpen.addActionListener(this);
		
		MyCopy = new JMenuItem("MyCopy");
		MyCopy.setBackground(Color.YELLOW);
		MyCopy.addActionListener(this);
		
		MyPaste = new JMenuItem("MyPaste");
		MyPaste.setBackground(Color.MAGENTA);
		MyPaste.addActionListener(this);
		
		MyFile.add(MyNew);
		MyFile.add(MyOpen);
		MyEdit.add(MyCopy);
		MyEdit.add(MyPaste);
		
		menuBar.add(MyFile);
		menuBar.add(MyEdit);
		
		frmMain.setJMenuBar(menuBar);
		
		FlowLayout decorate = new FlowLayout();
		frmMain.setLayout(decorate);
		
//		setting the controls for Container
		lblCarID = new JLabel("Car ID");
		txtCarID = new JTextField(15);
		
		lblCarName = new JLabel("Car Name");
		txtCarName = new JTextField(15);
		
		lblCarPrice = new JLabel("Car Price");
		txtCarPrice = new JTextField(15);
		
		//Hosting the controls on to the Container
		frmMain.add(lblCarID);
		frmMain.add(txtCarID);
		
		frmMain.add(lblCarName);
		frmMain.add(txtCarName);
		
		frmMain.add(lblCarPrice);
		frmMain.add(txtCarPrice);
		
		//Panel CRUD begins
		pnlCRUD = new JPanel();
		pnlCRUD.setBorder(BorderFactory.createTitledBorder("CRUD Operations"));
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		
		btnSearch = new JButton("Serach");
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(this);
		
		pnlCRUD.add(btnInsert);
		pnlCRUD.add(btnUpdate);
		pnlCRUD.add(btnDelete);
		pnlCRUD.add(btnSearch);
		pnlCRUD.add(btnClear);
		
		frmMain.add(pnlCRUD);
		
		//Navigation Panel Begins
		pnlNavig = new JPanel();
		pnlNavig.setBorder(BorderFactory.createTitledBorder("Navigation"));
		
		btnFirst = new JButton(" << ");
		btnFirst.addActionListener(this);
		
		btnPrev = new JButton(" < ");
		btnPrev.addActionListener(this);
		
		btnNext = new JButton(" > ");
		btnNext.addActionListener(this);
		
		btnLast = new JButton(" > >");
		btnLast.addActionListener(this);
		
		pnlNavig.add(btnFirst);
		pnlNavig.add(btnPrev);
		pnlNavig.add(btnNext);
		pnlNavig.add(btnLast);
		
		frmMain.add(pnlNavig);
		
		frmMain.setDefaultCloseOperation(frmMain.EXIT_ON_CLOSE);
		frmMain.setSize(952, 563);
		frmMain.setVisible(true);
		
		dbops = new DataHandler();
		
		dbops.CreateConnection();
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent controlRef) {
		// TODO Auto-generated method stub
		if(controlRef.getActionCommand()=="Insert")
		{
			dbops.rcrInsert(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="Update")
		{
			dbops.rcrUpdate(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="Delete")
		{
			dbops.rcrDelete(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="Search")
		{
			dbops.rcrSearch(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="Clear")
		{
			dbops.rcrClear(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="<<")
		{
			dbops.rcrFirst(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="<")
		{
			dbops.rcrPrevious(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()==">")
		{
			dbops.rcrNext(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()==">>")
		{
			dbops.rcrLast(frmMain,txtCarID,txtCarName,txtCarPrice);
		}
		else if(controlRef.getActionCommand()=="MyNew")
		{
//			JFrame profilePage = new JFrame();
//			profilePage.setSize(1000, 10000);
//			frmMain.dispose();
//			profilePage.setVisible(true);
			
			new CarDealer();
//			frmMain.dispose();
		}
		
		
		
		
		
		
	}
	
	

}

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ChemistShop implements ActionListener
{
	//JDBC classes
	Connection con;
	Statement Stmt;
	PreparedStatement pStmt;
	ResultSet virTbl;
	
	//Container
	JFrame FrmPrnt;
	
	//Sub-Container
	JPanel pnlCRUD,pnlNavig;
	
	//Controls for Container ie Frame
	JLabel lblMedName,lblQuant,lblPrice;
	
	JTextField txtMedName,txtQuant,txtPrice;
	
	//Controls for panel crud
	JButton btnInsert,btnDelete,btnSearch,btnUpdate,btnClear;
	
	//Controls for navigation panel
	JButton btnFirst,btnNext,btnPrev,btnLast;
	
	JMenuBar menuBar;
	
	JMenu menuFile,menuEdit;
	
	JMenuItem miNew,miOpen;
	JMenuItem miCopy,miPaste;
	
	String mName,mP,mQ;
	
	public void CreateGUI()
	{
		FrmPrnt = new JFrame();
		FrmPrnt.setTitle("Chemist Shop Version 1.0");
		
		//Giving background image to the frame
		ImageIcon imageRef = new ImageIcon("C:\\Users\\RVBMR\\Pictures\\TheMedicineShoppe.png");
		JLabel hostImage = new JLabel(imageRef);
		FrmPrnt.setContentPane(hostImage);
		
		//FrmPrnt.stContentPane(new JLabel(new ImageIcon("C:\\Users\\RVBMR\\Pictures\\TheMedicineShoppe.png")));
		//in the above way v can save 2 stack memory allocations
		
		//Working the menu bar for our window app
		menuBar = new JMenuBar();
		
		menuFile = new JMenu("MyFile");
		menuEdit = new JMenu("MyEdit");
		
		miNew = new JMenuItem("MyNew	Alt+N");
		miNew.addActionListener(this);
		
		miOpen = new JMenuItem("MyOpen	Ctrl+O");
		miOpen.addActionListener(this);
		
		menuFile.add(miNew);
		menuFile.add(miOpen);
		
		menuBar.add(menuFile);
		
		miCopy = new JMenuItem("MyCopy	Ctrl+C");
		miCopy.addActionListener(this);
		
		miPaste = new JMenuItem("MyPaste	Ctrl+V");
		miPaste.addActionListener(this);
		
		menuEdit.add(miCopy);
		menuEdit.add(miPaste);
		
		menuBar.add(menuEdit);
		
		//FrmPrnt.add(menuBar);
		FrmPrnt.setJMenuBar(menuBar);
		
		//Controls for the frame
		//lblMedName = new JLabel("Medicine Name : ");
		lblMedName = new JLabel();
		lblMedName.setText("Medicine Name : ");
		
		txtMedName = new JTextField(10);
		
		
		lblQuant = new JLabel("Quantity : ");
		txtQuant = new JTextField(10);
		
		lblPrice = new JLabel("Price : ");
		txtPrice = new JTextField(10);
		
		//Adding controls into the container
		FrmPrnt.add(lblMedName);
		FrmPrnt.add(txtMedName);
		FrmPrnt.add(lblQuant);
		FrmPrnt.add(txtQuant);
		FrmPrnt.add(lblPrice);
		FrmPrnt.add(txtPrice);
		
		//Setting the layout
		FlowLayout decorate = new FlowLayout();
		//GridLayout Decorate = new GridLayout(3,2);
		FrmPrnt.setLayout(decorate);
		//FrmPrnt.setLayout(Decorate);
		
		//CRUD panel begins
		pnlCRUD = new JPanel();
		
		pnlCRUD.setBorder(BorderFactory.createTitledBorder("CRUD Operations"));
		
		//Controls for CRUD panel
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(this);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(this);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(this);
		
		pnlCRUD.add(btnInsert);
		pnlCRUD.add(btnUpdate);
		pnlCRUD.add(btnDelete);
		pnlCRUD.add(btnSearch);
		pnlCRUD.add(btnClear);
		
		FrmPrnt.add(pnlCRUD);
		
		//Navigation panel begins
		pnlNavig = new JPanel();
		
		pnlNavig.setBorder(BorderFactory.createTitledBorder("Navigation Operations"));
		
		//Controls for Navigation panel
		btnFirst = new JButton("<<");
		btnFirst.addActionListener(this);
		
		btnPrev = new JButton("<");
		btnPrev.addActionListener(this);
		
		btnNext = new JButton(">");
		btnNext.addActionListener(this);
		
		btnLast = new JButton(">>");
		btnLast.addActionListener(this);
		
		pnlNavig.add(btnFirst);
		pnlNavig.add(btnPrev);
		pnlNavig.add(btnNext);
		pnlNavig.add(btnLast);
		
		FrmPrnt.add(pnlNavig);
		
		
		
		FrmPrnt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrmPrnt.setSize(700, 600);
		FrmPrnt.setVisible(true);	//By default frames ki visibility false hoti hai...so firstly we have to make it true
		
		
		
	} 
	
	public void DatabaseOps()
	{
		//JDBC API Begins
		
		//Step1 : Load the Driver
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			//Step2 : Connect front end with back end
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBChemist", "root", "uniquecoder");
			
			System.out.println("connected successfully");
			
			
			//Step3 : Create a msg to be sent
			String msg = "select * from MedInfo";
			
			//Step4 : Create a messenger
			Stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			//Step5 : Fire the Query
			virTbl = Stmt.executeQuery(msg);
			
			//virTbl.next();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void FillTextBoxes()
	{
		try
		{
			mName = virTbl.getString("MedName");
			String mQ = Integer.toString(virTbl.getInt("MedQuant"));
			String mP = Double.toString(virTbl.getDouble("MedPrice"));
			
			
			//System.out.println(mName+" "+" "+mQ+" "+mP);
			
			txtMedName.setText(mName);
			txtQuant.setText(mQ);
			txtPrice.setText(mP);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent controlRef) {
		// TODO Auto-generated method stub
	
		if(controlRef.getActionCommand() == "Insert")
		{
			rcrInsert();
		}else if(controlRef.getActionCommand() == "Update")
		{
			rcrUpdate();
		}else if(controlRef.getActionCommand() == "Delete")
		{
			rcrDelete();
		}else if(controlRef.getActionCommand() == "Search")
		{
			rcrSearch();
		}else if(controlRef.getActionCommand() == "Clear")
		{
			rcrClear();
		}else if(controlRef.getActionCommand() == "<<")
		{
			rcrFirst();
		}else if(controlRef.getActionCommand() == "<")
		{
			rcrPrev();
		}else if(controlRef.getActionCommand() == ">")
		{
			rcrNext();
		}else if(controlRef.getActionCommand() == ">>")
		{
			rcrLast();
		}
		
	}


	private void rcrInsert() {
		// TODO Auto-generated method stub
		try {
			virTbl.moveToInsertRow();
			String mName = txtMedName.getText();
			int mQuant = Integer.parseInt(txtQuant.getText());
			double mPrice = Double.parseDouble(txtPrice.getText());
			
			virTbl.updateString(1,mName);
			virTbl.updateInt(2, mQuant);
			virTbl.updateDouble(3,mPrice);
			
			virTbl.insertRow();
			
			JOptionPane.showMessageDialog(FrmPrnt, "Record got inserted successfully");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void rcrUpdate() {
		// TODO Auto-generated method stub
		String mName = txtMedName.getText();
		int mQuant = Integer.parseInt(txtQuant.getText());
		Double mPrice = Double.parseDouble(txtPrice.getText());
		
		try
		{
			virTbl.updateString(1, mName);
			virTbl.updateInt(2, mQuant);
			virTbl.updateDouble(3, mPrice);
			
			virTbl.updateRow();
			
			JOptionPane.showMessageDialog(FrmPrnt, "Record got updated successfully");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void rcrDelete() {
		// TODO Auto-generated method stub
		try
		{
			virTbl.deleteRow();
			
			JOptionPane.showMessageDialog(FrmPrnt, "Record got deleted successfully");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	private void rcrSearch() {
		// TODO Auto-generated method stub
		if(txtMedName.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(FrmPrnt, "We need 'MedName' for search to work");
		}else
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				//Step2 : Connect front end with back end
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbchemist","root","uniquecoder");
				
				String searchMsg = "select * from MedInfo where MedName = ?";
				
				pStmt = con.prepareStatement(searchMsg);
				
				//1 is the position value of wild card i.e, ?
				pStmt.setString(1, txtMedName.getText());
				
				virTbl = pStmt.executeQuery();
				
				if(virTbl.next())
				{
					FillTextBoxes();
				}else
				{
					JOptionPane.showMessageDialog(FrmPrnt, txtMedName.getText()+"is not available in our database");
				}
					
					
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}

	private void rcrClear() {
		// TODO Auto-generated method stub
		txtMedName.setText("");
		txtQuant.setText("");
		txtPrice.setText("");
		
	}
	
	
	private void rcrFirst() {
		// TODO Auto-generated method stub
		try
		{
			virTbl.first();
			FillTextBoxes();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void rcrPrev() {
		// TODO Auto-generated method stub
		try
		{
			if(virTbl.previous())
			{
				FillTextBoxes();
			}else
			{
				virTbl.next();
				JOptionPane.showMessageDialog(FrmPrnt, "You are at the first record!!!");
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void rcrNext() {
		// TODO Auto-generated method stub
		try
		{
			if(virTbl.next())
			{
				FillTextBoxes();
			}else
			{
				virTbl.previous();
				JOptionPane.showMessageDialog(FrmPrnt, "You are at the last record!!!");
				
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	private void rcrLast() {
		// TODO Auto-generated method stub
		try {
			virTbl.last();
			FillTextBoxes();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


public class ChemistBusinessTracker {
	public static void main(String args[])
	{
		ChemistShop Chemist = new ChemistShop();
		Chemist.CreateGUI();
		
		Chemist.DatabaseOps();
	}

}

package modelcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CanteenDatabase {

	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet virTbl;
	
	
	public void createConnection() {
		// TODO Auto-generated method stub
		//loading the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//establish the connection between front end and back end
		//dblocation with dbname,username and password
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/maitcanteen","root","uniquecoder");
			
			//query for retrieving info from back end database
			String msg = "select * from items";
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			//firing the query
			virTbl = stmt.executeQuery(msg);
			
		//	con.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	public void FillTextBoxes(JTextField txtItemName,JTextField txtItemPrice)
	{
		try {
			String iName = virTbl.getString("ItemName");
			int iPrice = virTbl.getInt("ItemPrice");
			
			txtItemName.setText(iName);
			txtItemPrice.setText(Integer.toString(iPrice));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void rcrSamosa(JFrame window, JTextField priceSamosa) {
		// TODO Auto-generated method stub
		int qty = Integer.parseInt(priceSamosa.getText());
	//	System.out.println("Connected successfully");
		qty++;
	//	System.out.println("Connected successfully");
		priceSamosa.setText(Integer.toString(qty));
		
		
		
	}


	public void rcrDOSA(JFrame window, JTextField priceDOSA) {
		// TODO Auto-generated method stub
		int qty = Integer.parseInt(priceDOSA.getText());
		//	System.out.println("Connected successfully");
			qty++;
		//	System.out.println("Connected successfully");
			priceDOSA.setText(Integer.toString(qty));
			
	}


	public void rcrChilliPotato(JFrame window, JTextField priceChilliPotato) {
		// TODO Auto-generated method stub
		int qty = Integer.parseInt(priceChilliPotato.getText());
		//	System.out.println("Connected successfully");
			qty++;
		//	System.out.println("Connected successfully");
			priceChilliPotato.setText(Integer.toString(qty));
			
	}


	public void rcrTikki(JFrame window, JTextField priceTikki) {
		// TODO Auto-generated method stub
		int qty = Integer.parseInt(priceTikki.getText());
		//	System.out.println("Connected successfully");
			qty++;
		//	System.out.println("Connected successfully");
			priceTikki.setText(Integer.toString(qty));
	}


	public void rcrPizza(JFrame window, JTextField pricePizza) {
		// TODO Auto-generated method stub
		int qty = Integer.parseInt(pricePizza.getText());
		//	System.out.println("Connected successfully");
			qty++;
		//	System.out.println("Connected successfully");
			pricePizza.setText(Integer.toString(qty));
	}


	public void rcrFingerChips(JFrame window, JTextField priceFingerChips) {
		// TODO Auto-generated method stub
		int qty = Integer.parseInt(priceFingerChips.getText());
		//	System.out.println("Connected successfully");
			qty++;
		//	System.out.println("Connected successfully");
			priceFingerChips.setText(Integer.toString(qty));
		
	}


	public void rcrFirst(JFrame window, JTextField txtName, JTextField txtPrice) {
		// TODO Auto-generated method stub
	
		try {
			
			virTbl.first();
			FillTextBoxes(txtName,txtPrice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	public void rcrPrev(JFrame window, JTextField txtName, JTextField txtPrice) {
		// TODO Auto-generated method stub
		
		try {
			if(virTbl.previous())
			{
				FillTextBoxes(txtName,txtPrice);
			}
			else
			{
				virTbl.next();
				
				JOptionPane.showMessageDialog(window, "You are alredy at the first record");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void rcrNext(JFrame window, JTextField txtName, JTextField txtPrice) {
		// TODO Auto-generated method stub
		try {
			if(virTbl.next())
			{
				FillTextBoxes(txtName,txtPrice);
			}
			else
			{
				virTbl.previous();
				
				JOptionPane.showMessageDialog(window, "You are already at the last record");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public void rcrLast(JFrame window, JTextField txtName, JTextField txtPrice) {
		// TODO Auto-generated method stub
		try {
			virTbl.last();
			
			FillTextBoxes(txtName,txtPrice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public void rcrUpdate(JFrame window, JTextField txtName, JTextField txtPrice, JLabel lblSamosa, JLabel lblDOSA, JLabel lblChilliPotato, JLabel lblTikki, JLabel lblPizza, JLabel lblFingerChips) {
		// TODO Auto-generated method stub
		
		try {
		//	String itemName = txtName.getText();
			int itemPrice = Integer.parseInt(txtPrice.getText());
			String s1 = txtName.getText();
			String s2 = virTbl.getString("ItemName");
			if(s2.equalsIgnoreCase(s1))
			{
		//		virTbl.updateString(1, itemName);
				virTbl.updateInt("ItemPrice", itemPrice);
				
				virTbl.updateRow();
				JOptionPane.showMessageDialog(window, "Entity updated");
				
			}else
			{
				
				JOptionPane.showMessageDialog(window, "You cannot change the Item Name");
				txtName.setText( virTbl.getString("ItemName"));
				txtPrice.setText(Integer.toString(virTbl.getInt("ItemPrice")));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void rcrSearch(JFrame window, JTextField txtName, JTextField txtPrice) {
		// TODO Auto-generated method stub
		if(txtName.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(window, "We need Item Name to run the search");
		}
		else
		{
			try {
				//Step1 : load the driver
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Step2: Connect front end with back end
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maitcanteen","root","uniquecoder");
				
				String searchMsg = "select * from items where ItemName = ?";		//? -- wildcard
				pstmt = con.prepareStatement(searchMsg);
				pstmt.setString(1, txtName.getText());
				virTbl = pstmt.executeQuery();
				
				if(virTbl.next())
				{
					FillTextBoxes(txtName,txtPrice);
				}
				else
				{
					JOptionPane.showMessageDialog(window, txtName.getText()+" is not found ");
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}

	public void rcrClear(JFrame window, JTextField txtName, JTextField txtPrice, JTextField priceSamosa, JTextField priceDOSA, JTextField priceChilliPotato, JTextField priceTikki, JTextField pricePizza, JTextField priceFingerChips, JTextField txtTotal) {
		// TODO Auto-generated method stub
		
		txtName.setText("");
		txtPrice.setText("");
		priceSamosa.setText("0");
		priceDOSA.setText("0");
		priceChilliPotato.setText("0");
		priceTikki.setText("0");
		pricePizza.setText("0");
		priceFingerChips.setText("0");
		txtTotal.setText("0");;
		
		
	}

	public void rcrTotal(JFrame window, JTextField priceSamosa, JTextField priceDOSA, JTextField priceChilliPotato, JTextField priceTikki, JTextField pricePizza, JTextField priceFingerChips, JTextField txtTotal) {
		// TODO Auto-generated method stub
		
		int q[] = new int[6];
		if(priceSamosa.getText().isEmpty()){
			
			q[0] = 0;
			priceSamosa.setText("0");
		
		}
		else{
		
			q[0] = Integer.parseInt(priceSamosa.getText());
		
		}
		
		if(priceDOSA.getText().isEmpty()){
			
			q[1] = 0;
			priceDOSA.setText("0");
			
		}
		else{
		
			q[1] = Integer.parseInt(priceDOSA.getText());
			
		}
		
		if(priceChilliPotato.getText().isEmpty()){
			
			q[2] = 0;
			priceChilliPotato.setText("0");
		
		}
		else{
			
			q[2] = Integer.parseInt(priceChilliPotato.getText());
			
		}
		
		if(priceTikki.getText().isEmpty()){
			
			q[3] = 0;
			priceTikki.setText("0");
			
		}
		else{
			
			q[3] = Integer.parseInt(priceTikki.getText());
			
		}
		
		if(pricePizza.getText().isEmpty()){
			
			q[4] = 0;
			pricePizza.setText("0");
			
		}
		else{
			
			q[4] = Integer.parseInt(pricePizza.getText());
		
		}
		
		if(priceFingerChips.getText().isEmpty()){
			
			q[5] = 0;
			priceFingerChips.setText("0");
		}
		else{
			
			q[5] = Integer.parseInt(priceFingerChips.getText());
			
		}
		
		
		int total = 0;
		
		try {
			
			virTbl.first();
			for(int i = 0;i < 6;i++)
			{
				total += q[i] * virTbl.getInt("ItemPrice");
				virTbl.next();
			}
			virTbl.first();
			txtTotal.setText(Integer.toString(total));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}


}

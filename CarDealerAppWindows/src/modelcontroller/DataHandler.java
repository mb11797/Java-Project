package modelcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DataHandler {
	
	//JDBC begins
	Connection con;
	Statement stmt;
	PreparedStatement pStmt;
	ResultSet virTbl;
	
	public void CreateConnection()
	{
		//Loading the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//eastablish the connection between frontend and backend
			//DBLocation with dbname,username,password
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/CarDealerApp","root","uniquecoder");
			
			//query for retrieving info from back end database
			String msg = "Select * from carinfo";
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//fire the query
			virTbl = stmt.executeQuery(msg);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void FillTextBoxes(JTextField txtCarID,JTextField txtCarName,JTextField txtCarPrice)
	{
		try {
			int carID = virTbl.getInt(1);
			String carName = virTbl.getString(2);
			double carPrice = virTbl.getDouble(3);
			
			txtCarID.setText(Integer.toString(carID));
			txtCarName.setText(carName);
			txtCarPrice.setText(Double.toString(carPrice));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void rcrInsert(JFrame frmMain,JTextField txtCarID,JTextField txtCarName,JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		try {
				virTbl.moveToInsertRow();
			
				int carID = Integer.parseInt(txtCarID.getText());
				String carName = txtCarName.getText();
				Double carPrice = Double.parseDouble(txtCarPrice.getText());
				
				virTbl.updateInt(1, carID);
				virTbl.updateString(2, carName);
				virTbl.updateDouble(3, carPrice);
				
				virTbl.insertRow();
				
				JOptionPane.showMessageDialog(frmMain, "Record inserted");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rcrUpdate(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		
		try {
			int carID = Integer.parseInt(txtCarID.getText());
			String carName = txtCarName.getText();
			Double carPrice = Double.parseDouble(txtCarPrice.getText());
			
			virTbl.updateInt(1, carID);
			virTbl.updateString(2, carName);
			virTbl.updateDouble(3, carPrice);
			
			virTbl.updateRow();
			JOptionPane.showMessageDialog(frmMain, "Record updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void rcrDelete(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		try {
			
			virTbl.deleteRow();
			JOptionPane.showMessageDialog(frmMain, "Record Deleted"); 
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void rcrSearch(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		if(txtCarID.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(frmMain, "Sorry we need Car ID for search to work");
		}
		else
		{
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardealerapp","root","uniquecoder");
				
				String searchMsg = "select * from carinfo where carId = ?";
				
				pStmt = con.prepareStatement(searchMsg);
				
				pStmt.setInt(1 , Integer.parseInt(txtCarID.getText()));
				
				virTbl = pStmt.executeQuery();
				
				
				if(virTbl.next())
				{
					FillTextBoxes(txtCarID,txtCarName,txtCarPrice);
				}
				else
				{
					JOptionPane.showMessageDialog(frmMain,txtCarID.getText().trim()+" is not found in our database");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public void rcrClear(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		txtCarID.setText("");
		txtCarName.setText("");
		txtCarPrice.setText("");
		
	}

	public void rcrFirst(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		try {
			virTbl.first();
			FillTextBoxes(txtCarID,txtCarName,txtCarPrice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void rcrPrevious(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		try {
			if(virTbl.previous())
				{
				FillTextBoxes(txtCarID,txtCarName,txtCarPrice);
				}
			else
			{
				virTbl.next();
				JOptionPane.showMessageDialog(frmMain, "You are at the first record");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public void rcrNext(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		try {
			if(virTbl.next())
			{
				FillTextBoxes(txtCarID,txtCarName,txtCarPrice);
			}
			else
			{
				virTbl.previous();
				JOptionPane.showMessageDialog(frmMain, "You are at the last record");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void rcrLast(JFrame frmMain, JTextField txtCarID, JTextField txtCarName, JTextField txtCarPrice) {
		// TODO Auto-generated method stub
		try {
			virTbl.last();
			FillTextBoxes(txtCarID,txtCarName,txtCarPrice);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	
	
	
	
	
}

package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import login.loginWindow;
import ui.ElectronicVotingApp;

public class Database {
	
	Connection conctn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet virTbl;
	
	JTable table;
	ElectronicVotingApp EV;

	public void createLoginConnection() {
		// TODO Auto-generated method stub
		//Loading the Driver
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			//Eastablish the connection between front end and back end
			//dblocation with dbname,username,password
			
			conctn = DriverManager.getConnection("jdbc:mysql://localhost/eVotingApp","root","uniquecoder");
			
			//query for retrieving info from back end database
			String cmnd = "select * from logintable";
			
			stmt = conctn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			virTbl = stmt.executeQuery(cmnd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void checkLoginCredentials(JFrame lw, JTextField txtUsrname, JTextField txtPwd) {
		// TODO Auto-generated method stub
		
		try {
			createLoginConnection();
			virTbl.first();
			String s1 = txtUsrname.getText().trim();
			String s2 = txtPwd.getText();
			
			String s3 = virTbl.getString("UserName");
			String s4 = virTbl.getString("Password");
			
			if(s1.equals(s3) && s2.equals(s4))
			{
				lw.setVisible(false);
				EV = new ElectronicVotingApp();
				
			}
			else if(!s1.equals(s3) || !s2.equals(s4))
			{
				JOptionPane.showMessageDialog(lw, "Incorrect User Name or Password!!!");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void createConnection() {
		// TODO Auto-generated method stub
		//Loading the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			//establish the connection between front end and back end
			//dblocation with dbname,username,password
			
			conctn = DriverManager.getConnection("jdbc:mysql://localhost/eVotingApp","root","uniquecoder");
			
			//query for retreiving info from back end database
			String cmnd = "select * from 2017elections";
			
			stmt = conctn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			virTbl = stmt.executeQuery(cmnd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void create2012Connection() {
		// TODO Auto-generated method stub
		//Loading the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			//establish the connection between front end and backend
			//dblocation with dbname,username,password
			
			conctn = DriverManager.getConnection("jdbc:mysql://localhost/eVotingApp","root","uniquecoder");
			
			//query for retreiving info from back end database
			String cmnd = "select * from 2012elections";
			
			stmt = conctn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			virTbl = stmt.executeQuery(cmnd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void create2007Connection() {
		// TODO Auto-generated method stub
		//Loading the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			//establish the connection between front end and backend
			//dblocation with dbname,username,password
			
			conctn = DriverManager.getConnection("jdbc:mysql://localhost/eVotingApp","root","uniquecoder");
			
			//query for retreiving info from back end database
			String cmnd = "select * from 2007elections";
			
			stmt = conctn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			virTbl = stmt.executeQuery(cmnd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void clickAAP(JFrame frame, JButton btnBJP, JButton btnCong, JButton btnAAP, JButton btnSP) {
		// TODO Auto-generated method stub
		try {
			createConnection();
			virTbl.first();
			String s1 = btnAAP.getText();
		//	System.out.println(s1);
			for(int i = 0;i < 4;i++)
			{
				String s2 = new String(virTbl.getString("Party"));
			//	System.out.println(s2);
				if(s2.equalsIgnoreCase(s1))
				{
					
					int i1 = virTbl.getInt("Votes");
					i1++;
				//	System.out.println(i1);
				//	i1 = 0;
					virTbl.updateInt("Votes", i1);
					virTbl.updateRow();
					
				}
				
				virTbl.next();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickBJP(JFrame frame, JButton btnBJP, JButton btnCong, JButton btnAAP, JButton btnSP) {
		// TODO Auto-generated method stub
		try {
			createConnection();
			virTbl.first();
			String s1 = btnBJP.getText();
		//	System.out.println(s1);
			for(int i = 0;i < 4;i++)
			{
				String s2 = new String(virTbl.getString("Party"));
			//	System.out.println(s2);
				if(s2.equalsIgnoreCase(s1))
				{
					
					int i1 = virTbl.getInt("Votes");
					i1++;
				//	i1 = 0;
					virTbl.updateInt("Votes", i1);
					virTbl.updateRow();

					
				}

				virTbl.next();

				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void clickCongress(JFrame frame, JButton btnBJP, JButton btnCong, JButton btnAAP, JButton btnSP) {
		// TODO Auto-generated method stub
		try {
			createConnection();
			virTbl.first();
			String s1 = btnCong.getText();
		//	System.out.println(s1);
			for(int i = 0;i < 4;i++)
			{
				String s2 = new String(virTbl.getString("Party"));
			//	System.out.println(s2);
				if(s2.equalsIgnoreCase(s1))
				{
					
					int i1 = virTbl.getInt("Votes");
					i1++;
				//	i1 = 0;
					virTbl.updateInt("Votes", i1);
					virTbl.updateRow();
					
				}
				
				virTbl.next();

				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickSP(JFrame frame, JButton btnBJP, JButton btnCong, JButton btnAAP, JButton btnSP) {
		// TODO Auto-generated method stub
		try {
			createConnection();
			virTbl.first();
			String s1 = btnSP.getText();
		//	System.out.println(s1);
			for(int i = 0;i < 4;i++)
			{
				String s2 = new String(virTbl.getString("Party"));
			//	System.out.println(s2);
				if(s2.equalsIgnoreCase(s1))
				{
					
					
					int i1 = virTbl.getInt("Votes");
					i1++;
				//	i1 = 0;
					virTbl.updateInt("Votes", i1);
					virTbl.updateRow();
					
					
				}
				
				virTbl.next();
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void click2012Result(JFrame frame) {
		// TODO Auto-generated method stub
		
		create2012Connection();
		try {
			virTbl.first();
			String[] columnHeader = {"Party","Votes"};
			String[] party = new String[4];
			String[] votes = new String[4];
			for(int i =0;i < 4;i++)
			{
				
				party[i] = virTbl.getString("Party");
				votes[i] = Integer.toString(virTbl.getInt("Votes"));
				
				virTbl.next();
				
			}
			String[][] rows = {{party[0],votes[0]},{party[1],votes[1]},{party[2],votes[2]},{party[3],votes[3]}};
			table = new JTable(rows,columnHeader);
			table.setBounds(10,10,50,50);
			JScrollPane js = new JScrollPane(table);
			JOptionPane.showMessageDialog(frame,js );
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void click2007Result(JFrame frame) {
		// TODO Auto-generated method stub
		create2007Connection();
		try {
			virTbl.first();
			String[] columnHeader = {"Party","Votes"};
			String[] party = new String[4];
			String[] votes = new String[4];
			for(int i =0;i < 4;i++)
			{
				
				party[i] = virTbl.getString("Party");
				votes[i] = Integer.toString(virTbl.getInt("Votes"));
				
				virTbl.next();
				
			}
			String[][] rows = {{party[0],votes[0]},{party[1],votes[1]},{party[2],votes[2]},{party[3],votes[3]}};
			table = new JTable(rows,columnHeader);
			JScrollPane js = new JScrollPane(table);
			js.setSize(100, 100);
			JOptionPane.showMessageDialog(frame,js );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void checkAdminPermissionRefresh(JFrame lw, JTextField txtUsrname, JTextField txtPwd) {
		// TODO Auto-generated method stub
		try {
			createLoginConnection();
			virTbl.first();
			String s1 = txtUsrname.getText().trim();
			String s2 = txtPwd.getText();
			
			String s3 = virTbl.getString("UserName");
			String s4 = virTbl.getString("Password");
			
			if(s1.equals(s3) && s2.equals(s4))
			{
				lw.setVisible(false);
				createConnection();
				try {
					virTbl.first();
					int i1 = 0;
					for(int i = 0 ; i<4 ; i++)
					{
						virTbl.updateInt("Votes", i1);
						virTbl.updateRow();
						virTbl.next();
						
					}
					
					JOptionPane.showMessageDialog(lw, "All parties are set to zero votes.");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(!s1.equals(s3) || !s2.equals(s4))
			{
				JOptionPane.showMessageDialog(lw, "Incorrect User Name or Password!!!");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkAdminPermissionDeclareResult(JFrame lw, JTextField txtUsrname, JTextField txtPwd) {
		// TODO Auto-generated method stub
		try {
			createLoginConnection();
			virTbl.first();
			String s1 = txtUsrname.getText().trim();
			String s2 = txtPwd.getText();
			
			String s3 = virTbl.getString("UserName");
			String s4 = virTbl.getString("Password");
			
			if(s1.equals(s3) && s2.equals(s4))
			{
				lw.setVisible(false);
				createConnection();
				try {
					virTbl.first();
					String[] columnHeader = {"Party","Votes"};
					String[] party = new String[4];
					String[] votes = new String[4];
					for(int i =0;i < 4;i++)
					{
						
						party[i] = virTbl.getString("Party");
						votes[i] = Integer.toString(virTbl.getInt("Votes"));
						
						virTbl.next();
						
					}
					String[][] rows = {{party[0],votes[0]},{party[1],votes[1]},{party[2],votes[2]},{party[3],votes[3]}};
					table = new JTable(rows,columnHeader);
					JScrollPane js = new JScrollPane(table);
					JOptionPane.showMessageDialog(lw,js );
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(!s1.equals(s3) || !s2.equals(s4))
			{
				JOptionPane.showMessageDialog(lw, "Incorrect User Name or Password!!!");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public void checkAdminPermissionSubmit(JFrame lw, JTextField txtUsrname, JTextField txtPwd) {
//		// TODO Auto-generated method stub
//		try {
//			createLoginConnection();
//			virTbl.first();
//			String s1 = txtUsrname.getText().trim();
//			String s2 = txtPwd.getText();
//			
//			String s3 = virTbl.getString("UserName");
//			String s4 = virTbl.getString("Password");
//			
//			if(s1.equals(s3) && s2.equals(s4))
//			{
//				lw.setVisible(false);
//				EV.SubmitBtnFunctn();
//			}
//			else if(!s1.equals(s3) || !s2.equals(s4))
//			{
//				JOptionPane.showMessageDialog(lw, "Incorrect User Name or Password!!!");
//			}
//			
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	public void clickDeclareResult(JFrame frame) {
		// TODO Auto-generated method stub
		
		loginWindow lw = new loginWindow("DeclareResult");
		
	}

	public void clickRefresh(JFrame frame) {
		// TODO Auto-generated method stub
		
		loginWindow lw = new loginWindow("Refresh");
		
	}
	
//	public void clickSubmit(JFrame frame) {
//		// TODO Auto-generated method stub
//		loginWindow lw = new loginWindow("Submit");
//	}

	
}

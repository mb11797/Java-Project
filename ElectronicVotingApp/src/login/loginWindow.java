package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Database;
import ui.ElectronicVotingApp;

public class loginWindow implements ActionListener{
	
	JFrame lw;
	JPanel pnlLogin;

	JLabel lblUsrname;
	JLabel lblPwd;
	
	JTextField txtUsrname,txtPwd;

	JButton loginButton;
	
	Database db;
	
	
	public loginWindow(String s)
	{

		lw = new JFrame("Administrator Permission");
		
		pnlLogin = new JPanel(new GridLayout(3,2));
	
		lblUsrname = new JLabel(" Admin User Name");
		pnlLogin.add(lblUsrname);
		
		lblPwd = new JLabel("Password");
		pnlLogin.add(lblPwd);
		
		txtUsrname = new JTextField(20);
		pnlLogin.add(txtUsrname);
		
		txtPwd = new JTextField(20);
		pnlLogin.add(txtPwd);
		
		if(s.equalsIgnoreCase("Login"))
		{
			loginButton = new JButton("Login");
		}
		else if(s.equalsIgnoreCase("Refresh"))
		{
			loginButton = new JButton("Confirm Refresh");
		}
		else if(s.equalsIgnoreCase("DeclareResult"))
		{
			loginButton = new JButton("Confirm Declare Result");
		}
//		else if(s.equalsIgnoreCase("Submit"))
//		{
//			loginButton = new JButton("Confirm Submit");
//		}
		
		loginButton.setBackground(Color.BLUE);
		loginButton.addActionListener(this);
		lw.add(loginButton,BorderLayout.SOUTH);
		
		
		lw.add(pnlLogin);
	
	
		lw.setSize(300, 150);
	//	lw.setDefaultCloseOperation(lw.EXIT_ON_CLOSE);
		lw.setVisible(true);
	
		db = new Database();
		
		db.createLoginConnection();
	
	
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
		if(ae.getActionCommand() == "Login")
		{
			db.checkLoginCredentials(lw,txtUsrname,txtPwd);
			
		}
		else if(ae.getActionCommand() == "Confirm Refresh")
		{
			db.checkAdminPermissionRefresh(lw,txtUsrname,txtPwd);
		}
		else if(ae.getActionCommand() == "Confirm Declare Result")
		{
			db.checkAdminPermissionDeclareResult(lw,txtUsrname,txtPwd);
		}
//		else if(ae.getActionCommand() == "Confirm Submit")
//		{
//			db.checkAdminPermissionSubmit(lw,txtUsrname,txtPwd);
//		}
		
	}
}

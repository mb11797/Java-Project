package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import database.Database;
import login.loginWindow;

public class ElectronicVotingApp implements ActionListener {
	
	//container
	JFrame frame;
	
	//sub-containers
	JPanel pnlVoter,pnlPastResults,pnlAdmin;
	
	//controls for sub-container 1(pnlVoter)
//	JPanel pnlParties;												//???????????????????????????????Check point
	JLabel candBJP,candCong,candAAP,candSP;
	JButton btnBJP,btnCong,btnAAP,btnSP;
	
	
	//controls for sub-container-2(pnlPastResults)
	JButton btn2012Results,btn2007Results;
	
	
	
	//controls for sub-container-3(pnlAdmin)
	JButton btnSubmit,btnDeclareResults,btnRefresh;
	
	Database dbase;
	
	public ElectronicVotingApp()
	{
		createGUI();
	}


	public void createGUI() {
		// TODO Auto-generated method stub
		frame = new JFrame();

//		ImageIcon bgImg = new ImageIcon("C:\\Users\\RVBMR\\Pictures\\par1.jpg");
//		JLabel hostImg = new JLabel(bgImg);
//		frame.setContentPane(hostImg);
		
		
		
		
		// Voter panel begins
		pnlVoter = new JPanel(new GridLayout(4, 2));
		pnlVoter.setBorder(BorderFactory.createTitledBorder("VOTER Controls"));
		pnlVoter.setBackground(Color.ORANGE);
		
		candAAP = new JLabel("Arvind Kejriwal");
		btnAAP = new JButton("Aam Aadmi Party-AAP");
		btnAAP.setBackground(Color.ORANGE);
		btnAAP.addActionListener(this);
		pnlVoter.add(candAAP, BorderLayout.WEST);
		pnlVoter.add(btnAAP, BorderLayout.EAST);
		
		candBJP = new JLabel("Narendra Modi");
		btnBJP = new JButton("Bhartiya Janta Party-BJP");
		btnBJP.setBackground(Color.ORANGE);
		btnBJP.addActionListener(this);
		pnlVoter.add(candBJP, BorderLayout.WEST);
		pnlVoter.add(btnBJP, BorderLayout.EAST);
		
		candCong = new JLabel("Rahul Gandhi");
		btnCong = new JButton("Congress-INC");
		btnCong.setBackground(Color.ORANGE);
		btnCong.addActionListener(this);
		pnlVoter.add(candCong, BorderLayout.WEST);
		pnlVoter.add(btnCong, BorderLayout.EAST);
		
		
		candSP = new JLabel("Akhilesh Yadav");
		btnSP = new JButton("Samajwadi Party-SP");
		btnSP.setBackground(Color.ORANGE);
		btnSP.addActionListener(this);
		pnlVoter.add(candSP, BorderLayout.WEST);
		pnlVoter.add(btnSP, BorderLayout.EAST);
		
		frame.add(pnlVoter, BorderLayout.NORTH);
		
		
		
		
		// sub-panel pnlPastResults begins
		pnlPastResults = new JPanel(new GridLayout(2,1));
		pnlPastResults.setBorder(BorderFactory.createTitledBorder("Past Results"));
		pnlPastResults.setBackground(Color.WHITE);
		
		btn2012Results = new JButton("2012 Result");
//		btn2012Results.setBounds(60, 30, 200, 30);
		btn2012Results.setBackground(Color.WHITE);
		btn2012Results.addActionListener(this);
		pnlPastResults.add(btn2012Results,BorderLayout.CENTER);
//		pnlPastResults.add(lbl2012Results,BorderLayout.WEST);		
		
		
		btn2007Results = new JButton("2007 Result");
//		btn2007Results.setBounds(60, 30, 200, 30);
		btn2007Results.setBackground(Color.WHITE);
		btn2007Results.addActionListener(this);
		pnlPastResults.add(btn2007Results,BorderLayout.CENTER);
//		pnlPastResults.add(lbl2007Results,BorderLayout.EAST);		
		
		frame.add(pnlPastResults, BorderLayout.CENTER);
// 		frame.add(pnlPastResults);
		
		
		
		
		// Admin panel begins
		pnlAdmin = new JPanel(new GridLayout(3,1));
		pnlAdmin.setBorder(BorderFactory.createTitledBorder("Admin Controls"));
		pnlAdmin.setBackground(Color.GREEN);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBackground(Color.GREEN);
		btnSubmit.addActionListener(this);
		pnlAdmin.add(btnSubmit);
		
		btnDeclareResults = new JButton("Declare Result");
		btnDeclareResults.setBackground(Color.GREEN);
		btnDeclareResults.addActionListener(this);
		pnlAdmin.add(btnDeclareResults);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(Color.GREEN);
		btnRefresh.addActionListener(this);
		pnlAdmin.add(btnRefresh);
		
		frame.add(pnlAdmin, BorderLayout.SOUTH);
		
		
		
		
		//FRAME FORMALITIES
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		dbase = new Database();
		
		dbase.createConnection();
		
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getActionCommand() == "Aam Aadmi Party-AAP")
		{
	//		System.out.println("Successful AAP");
			dbase.clickAAP(frame,btnBJP,btnCong,btnAAP,btnSP);
			
		}
		else if(ae.getActionCommand() == "Bhartiya Janta Party-BJP")
		{
	//		System.out.println("Successful BJP");
			dbase.clickBJP(frame,btnBJP,btnCong,btnAAP,btnSP);
			
		}
		else if(ae.getActionCommand() == "Congress-INC")
		{
			
	//		System.out.println("Successful Congress");
			dbase.clickCongress(frame,btnBJP,btnCong,btnAAP,btnSP);
			
		}
		else if(ae.getActionCommand() == "Samajwadi Party-SP")
		{
			
	//		System.out.println("Successful SP");
			dbase.clickSP(frame,btnBJP,btnCong,btnAAP,btnSP);
			
		}
		else if(ae.getActionCommand() == "2012 Result")
		{
			dbase.click2012Result(frame);
		}
		else if(ae.getActionCommand() == "2007 Result")
		{
			dbase.click2007Result(frame);
		}
		else if(ae.getActionCommand() == "SUBMIT")
		{
	//		dbase.clickSubmit(frame);
			btnAAP.setEnabled(false);
			btnBJP.setEnabled(false);
			btnCong.setEnabled(false);
			btnSP.setEnabled(false);
	//		btnAAP.removeActionListener(this);
	//		btnBJP.removeActionListener(this);
	//		btnCong.removeActionListener(this);
	//		btnSP.removeActionListener(this);
			
			JOptionPane.showMessageDialog(frame, "No further votes will be counted. ");
		}
		else if(ae.getActionCommand() == "Declare Result")
		{
			dbase.clickDeclareResult(frame);
		}
		else if(ae.getActionCommand() == "Refresh")
		{
			dbase.clickRefresh(frame);
		}
		
	}
	
	public void SubmitBtnFunctn()
	{
//		btnAAP.removeActionListener(this);
//		btnBJP.removeActionListener(this);
//		btnCong.removeActionListener(this);
//		btnSP.removeActionListener(this);
//		
//		JOptionPane.showMessageDialog(frame, "No further votes will be counted. ");
		
//		pnlVoter.remove(btnAAP);
//		pnlVoter.remove(btnBJP);
//		pnlVoter.remove(btnCong);
//		pnlVoter.remove(btnSP);
	}
	
	
	
}

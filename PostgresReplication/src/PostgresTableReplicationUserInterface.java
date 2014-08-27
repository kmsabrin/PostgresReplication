import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


public class PostgresTableReplicationUserInterface extends javax.swing.JFrame {
	
	PostgresTableReplication pgTableReplication;
	
	private JPanel mainPanel;
	private JPanel sourceDbPanel;
	private JPanel targetDbPanel;
	private JPanel fileSelectPanel;
	private JPanel copyPanel;
	private JPanel msgPanel;
	
	
	private JLabel sourceIpLabel;
	private JLabel sourcePortLabel;
	private JLabel sourceDbNameLabel;
	private JLabel sourceUserLabel;
	private JLabel sourcePwLabel;
	
	private JTextField sourceIpTextField;
	private JTextField sourcePortTextField;
	private JTextField sourceDbNameTextField;
	private JTextField sourceUserTextField;
	private JTextField sourcePwTextField;
	
	private JLabel targetIpLabel;
	private JLabel targetPortLabel;
	private JLabel targetDbNameLabel;
	private JLabel targetUserLabel;
	private JLabel targetPwLabel;
	
	private JTextField targetIpTextField;
	private JTextField targetPortTextField;
	private JTextField targetDbNameTextField;
	private JTextField targetUserTextField;
	private JTextField targetPwTextField;
	
	private JLabel tableNameLabel;
	private JTextField tableNameTextField;

	private JTextArea fileNameTextArea;

	private JScrollPane fileNameScrollPane;

	private JButton connectButton;

	private JLabel gapLabel;

	private JButton copyButton;

	private JTextArea msgTextArea;

	private JScrollPane msgScrollPane;
	
	public void addConnectPanelItem(JLabel label, JTextField textField, 
			String labelString, String textFieldString, JPanel parentPanel) {
		label.setText(labelString);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setPreferredSize(new Dimension(110, 30));
		textField.setText(textFieldString);
		textField.setPreferredSize(new Dimension(200, 30));
		parentPanel.add(label);
		parentPanel.add(textField);
	}
	
	public PostgresTableReplicationUserInterface(String title) {
		super("Postgres Table Replication");
		
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		mainPanel.setPreferredSize(new Dimension(800, 630));
		mainPanel.setBackground(Color.LIGHT_GRAY);
		
		sourceDbPanel = new JPanel();
		sourceDbPanel.setPreferredSize(new Dimension(365, 210));
		sourceIpLabel = new JLabel();
		sourcePortLabel = new JLabel();
		sourceDbNameLabel = new JLabel();
		sourceUserLabel = new JLabel();
		sourcePwLabel = new JLabel();
		sourceIpTextField = new JTextField();
		sourcePortTextField = new JTextField();
		sourceDbNameTextField = new JTextField();
		sourceUserTextField = new JTextField();
		sourcePwTextField = new JTextField();
		addConnectPanelItem(sourceIpLabel, sourceIpTextField, "Source IP", "127.0.0.1", sourceDbPanel);
		addConnectPanelItem(sourcePortLabel, sourcePortTextField, "Source Port", "5432", sourceDbPanel);
		addConnectPanelItem(sourceDbNameLabel, sourceDbNameTextField, "Source DB Name", "SourceDB", sourceDbPanel);
		addConnectPanelItem(sourceUserLabel, sourceUserTextField, "Source User Name", "postgres", sourceDbPanel);
		addConnectPanelItem(sourcePwLabel, sourcePwTextField, "Source Password", "nilu01", sourceDbPanel);
		TitledBorder sourceBorder = BorderFactory.createTitledBorder("Source DB");
		sourceBorder.setBorder(new BevelBorder(0));
		sourceDbPanel.setBorder(sourceBorder);
		mainPanel.add(sourceDbPanel);
		
		targetDbPanel = new JPanel();
		targetDbPanel.setPreferredSize(new Dimension(365, 210));
		targetIpLabel = new JLabel();
		targetPortLabel = new JLabel();
		targetDbNameLabel = new JLabel();
		targetUserLabel = new JLabel();
		targetPwLabel = new JLabel();
		targetIpTextField = new JTextField();
		targetPortTextField = new JTextField();
		targetDbNameTextField = new JTextField();
		targetUserTextField = new JTextField();
		targetPwTextField = new JTextField();
		addConnectPanelItem(targetIpLabel, targetIpTextField, "Target IP", "127.0.0.1", targetDbPanel);
		addConnectPanelItem(targetPortLabel, targetPortTextField, "Target Port", "5432", targetDbPanel);
		addConnectPanelItem(targetDbNameLabel, targetDbNameTextField, "Target DB Name", "TargetDB", targetDbPanel);
		addConnectPanelItem(targetUserLabel, targetUserTextField, "Target User Name", "postgres", targetDbPanel);
		addConnectPanelItem(targetPwLabel, targetPwTextField, "Target Password", "nilu01", targetDbPanel);
		TitledBorder targetBorder = BorderFactory.createTitledBorder("Target DB");
		targetBorder.setBorder(new BevelBorder(0));
		targetDbPanel.setBorder(targetBorder);
		mainPanel.add(targetDbPanel);
		
		fileSelectPanel = new JPanel();
		fileSelectPanel.setPreferredSize(new Dimension(365, 190));
		fileNameTextArea = new JTextArea(8, 30);  
		fileNameScrollPane = new JScrollPane(fileNameTextArea);
		fileSelectPanel.add(fileNameScrollPane);
		TitledBorder fileNameBorder = BorderFactory.createTitledBorder("Source DB Tables");
		fileNameBorder.setBorder(new BevelBorder(0));
		fileSelectPanel.setBorder(fileNameBorder);
		mainPanel.add(fileSelectPanel);
		
		copyPanel = new JPanel();
		copyPanel.setPreferredSize(new Dimension(365, 190));
		connectButton = new JButton();
		connectButton.setPreferredSize(new Dimension(320, 40));
		connectButton.setText("CONNECT TO SOURCE AND TARGET DATABASES!");
		copyPanel.add(connectButton);
		gapLabel = new JLabel("");
		gapLabel.setPreferredSize(new Dimension(320, 12));
		copyPanel.add(gapLabel);
		tableNameLabel = new JLabel();
		tableNameTextField = new JTextField();
		addConnectPanelItem(tableNameLabel, tableNameTextField, "Table Name", "my_table", copyPanel);
		copyButton = new JButton();
		copyButton.setPreferredSize(new Dimension(320, 40));
		copyButton.setText("COPY TABLE!");
		copyPanel.add(copyButton);
		TitledBorder copyBorder = BorderFactory.createTitledBorder("Connect/Copy");
		copyBorder.setBorder(new BevelBorder(0));
		copyPanel.setBorder(copyBorder);
		mainPanel.add(copyPanel);
		
		msgPanel = new JPanel();
		msgPanel.setPreferredSize(new Dimension(740, 155));
		msgTextArea = new JTextArea(6, 60);  
		msgScrollPane = new JScrollPane(msgTextArea);
		msgPanel.add(msgScrollPane);
		msgPanel.setBorder(new BevelBorder(0));
		TitledBorder msgBorder = BorderFactory.createTitledBorder("Message");
		msgBorder.setBorder(new BevelBorder(0));
		msgPanel.setBorder(msgBorder);
		mainPanel.add(msgPanel);

		
		connectButton.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	try {
            		
//            		pgTableReplication = new PostgresTableReplication();
            		
            		pgTableReplication = new PostgresTableReplication(
            			sourceIpTextField.getText(), sourcePortTextField.getText(), sourceDbNameTextField.getText(), 
            			sourceUserTextField.getText(), sourcePwTextField.getText(), 
            			targetIpTextField.getText(), targetPortTextField.getText(), targetDbNameTextField.getText(), 
            			targetUserTextField.getText(), targetPwTextField.getText(), 
            			"");
            		
            		pgTableReplication.connectToSourceDb();
            		pgTableReplication.connectToTargetDb();
            		
            		List<String> tableList = pgTableReplication.getTableNames();
            		
            		for(String s: tableList) {
            			fileNameTextArea.append(s+"\n");	
            		}
            		
            		msgTextArea.setText("Successfully connected to source and target databases!");
            	}
            	catch(Exception connectionException) {
            		msgTextArea.setText(connectionException.toString());
            	}
            }
        });
		
		copyButton.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {  
            	try {
            		pgTableReplication.tableName = tableNameTextField.getText();
            		pgTableReplication.getTableDDLfromSourceDb();
            		pgTableReplication.copyTableDDLtoTargetDb();
            		pgTableReplication.cleanUp();
            		
            		msgTextArea.setText("Successfully copied the table!");
            	}
            	catch(Exception copyException) {
            		msgTextArea.setText(copyException.toString());
            	}
            }
        });
	}
	
	public static void main(String[] args)	{	
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		PostgresTableReplicationUserInterface demo = new PostgresTableReplicationUserInterface("");
		demo.pack();
		demo.setVisible(true);
		demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

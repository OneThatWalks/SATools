/*
 * This file is part of SATools, which is licensed under the GNU GPL.
 * 
 * Any use or modification of this file will be in compliance of the
 * GNU GPL v3 or later version(s). Unauthorized distribution or 
 * distributed as "pay-ware" will be handled in the fullest extent
 * of the violators law.
 * 
 * If you have questions contact me at
 * OneThatWalks@live.com.
 */
package onethatwalks.satools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import onethatwalks.threads.GUIManager;

/**
 * This program is a Bukkit Server Wrapper plugin. This plugin will allow the
 * User to administer a Bukkit Server.
 * 
 * @author OneThatWalks
 * 
 * @version 0.4
 * 
 * @serial 1L
 * 
 */
public class SAToolsGUI extends JFrame implements ActionListener, KeyListener {

	/**
	 * Top Variables
	 */
	private static final long serialVersionUID = 1L;
	SATools p;
	GUIManager manager;
	public final Logger log;
	/**
	 * Component Variables
	 */
	private JPanel contentPane;
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenu mnInfo = new JMenu("Info");
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel panel_CONSOLE = new JPanel();
	JPanel panel_FUNCTIONS = new JPanel();
	public JTextArea textArea_console = new JTextArea();
	public JTextField textField_console = new JTextField();
	GridBagLayout gbl_panel_CONSOLE = new GridBagLayout();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	private final JScrollPane scrollPane = new JScrollPane();
	private JMenu mnOptions = new JMenu("Options");
	private JCheckBoxMenuItem mnEnableUpdate = new JCheckBoxMenuItem(
			"Enable Auto Update");
	private JMenuItem mnSubmitFeedback = new JMenuItem("Submit Feedback");
	private JMenuItem mnAbout = new JMenuItem("About");
	private JMenuItem mnVisitThread = new JMenuItem("Visit The Thread");
	public JMenuItem mnCheckForUpdates = new JMenuItem("Check For Updates");
	private JMenu mnWorld = new JMenu("Worlds");
	private JMenu mnPlayer = new JMenu("Players");
	private JMenuItem mnExit = new JMenuItem("Exit");
	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	private final JPanel panel_SERVER = new JPanel();
	public final JPanel panel_Time = new JPanel();
	public final JPanel panel_Weather = new JPanel();
	public final JPanel panel_World = new JPanel();
	public final JPanel panel_Message = new JPanel();
	public JLabel lblPlayer = new JLabel("Player:");
	public JLabel lbl_PLAYER_DATA = new JLabel("NULL");
	public JLabel lblWorld = new JLabel(" World:");
	public JLabel lbl_WORLD_DATA = new JLabel("NULL");
	public JButton btnStop = new JButton("Stop");
	public JLabel lblMemory = new JLabel("Memory Usage:");
	public JProgressBar progressBar_mem;
	public GridBagConstraints gbc_panel_Time = new GridBagConstraints();
	public JLabel lblCurrentTime = new JLabel("Current Time:");
	public JLabel lblTimeData = new JLabel("NULL");
	public JComboBox<Object> comboBox_TimeValues = new JComboBox<Object>();
	public JButton btnChangeTime = new JButton("Set");
	public JLabel lblTimeChangesAre = new JLabel("Time changes are instant");
	public GridBagConstraints gbc_panel_Weather = new GridBagConstraints();
	public JLabel lblCurrentConditions = new JLabel("Current Conditions:");
	public JLabel lblWeatherData = new JLabel("NULL");
	public JComboBox<Object> comboBox_Conditions = new JComboBox<Object>();
	public JButton btnChangeConditions = new JButton("Change");
	public JLabel lblWeatherChangesTend = new JLabel(
			"Weather changes tend to change slower");
	public GridBagConstraints gbc_panel_World = new GridBagConstraints();
	public JLabel lblSelectSomethingTo = new JLabel("Select something to spawn");
	public JComboBox<Object> comboBox_SpawnObject = new JComboBox<Object>();
	public JLabel lblWhere = new JLabel("Where?");
	public JComboBox<Object> comboBox_SpawnLocation = new JComboBox<Object>();
	public JButton btnSpawnObject = new JButton("Spawn");
	public JLabel lblUseTheXyz = new JLabel(
			"Use the x,y,z format with custom locations");
	public GridBagConstraints gbc_panel_Message = new GridBagConstraints();
	public JTextField textField_Message = new JTextField();
	public JComboBox<Object> comboBox_Color = new JComboBox<Object>();
	public JButton btnSendMessage = new JButton("Send");
	public JComboBox<Object> comboBox_Receiver = new JComboBox<Object>();
	public	JLabel lblTypeAMessage = new JLabel("Message to Receiver");
	public final JButton btnFreeMem = new JButton("Free");
	private String[] objects = {"Oak Tree" , "Birch Tree", "Spruce Tree", "Pig", "Cow", "Chicken", "Sheep", "Creeper", "Spider", "Skeleton", "Zombie", "Pigmen", "Blaze", "Slime", "Golem", "Ghast"};

	/**
	 * Create the frame.
	 */
	public SAToolsGUI(SATools instance) {
		super();
		p = instance;
		manager = new GUIManager(p, this);
		log = SATools.log;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p.getServer().shutdown();
			}
		});
		setSize(640, 460);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(getOwner());

		setJMenuBar(menuBar);

		menuBar.add(mnFile);

		menuBar.add(mnInfo);

		menuBar.add(mnOptions);
		// Options
		mnOptions.add(mnEnableUpdate);
		mnEnableUpdate.setEnabled(false);
		mnOptions.add(mnSubmitFeedback);
		mnSubmitFeedback.addActionListener(this);
		// Info
		mnInfo.add(mnAbout);
		mnAbout.addActionListener(this);
		mnInfo.add(mnVisitThread);
		mnVisitThread.addActionListener(this);
		// File
		mnFile.add(mnCheckForUpdates);
		mnCheckForUpdates.addActionListener(this);
		mnFile.add(mnWorld);
		mnFile.add(mnPlayer);
		mnFile.add(mnExit);
		mnExit.addActionListener(this);

		contentPane = new JPanel();
		setContentPane(contentPane);
		textField_console.setColumns(10);
		textField_console.addKeyListener(this);
		contentPane.setLayout(null);
		tabbedPane.setBounds(0, 0, 634, 363);
		contentPane.add(tabbedPane);

		tabbedPane.addTab("Console", null, panel_CONSOLE, null);
		gbl_panel_CONSOLE.columnWidths = new int[] { 0, 0 };
		gbl_panel_CONSOLE.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_CONSOLE.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_CONSOLE.rowWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel_CONSOLE.setLayout(gbl_panel_CONSOLE);

		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_CONSOLE.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(textArea_console);
		textArea_console.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		textArea_console.setEditable(false);

		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 9;
		panel_CONSOLE.add(textField_console, gbc_textField);

		tabbedPane.addTab("Server", null, panel_SERVER, null);
		GridBagLayout gbl_panel_SERVER = new GridBagLayout();
		gbl_panel_SERVER.columnWidths = new int[] { 0, 0 };
		gbl_panel_SERVER.rowHeights = new int[] { 0, 0 };
		gbl_panel_SERVER.columnWeights = new double[] { 1.0, 1.0 };
		gbl_panel_SERVER.rowWeights = new double[] { 1.0, 1.0 };
		panel_SERVER.setLayout(gbl_panel_SERVER);

		gbc_panel_Time.weighty = 1.0;
		gbc_panel_Time.weightx = 1.0;
		gbc_panel_Time.insets = new Insets(5, 5, 5, 5);
		gbc_panel_Time.fill = GridBagConstraints.BOTH;
		gbc_panel_Time.gridx = 0;
		gbc_panel_Time.gridy = 0;
		panel_Time.setBorder(new TitledBorder(null, "Time",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Time.setLayout(null);
		panel_SERVER.add(panel_Time, gbc_panel_Time);

		lblCurrentTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentTime.setBounds(10, 25, 135, 14);
		panel_Time.add(lblCurrentTime);

		lblTimeData.setBounds(155, 25, 139, 14);
		panel_Time.add(lblTimeData);

		comboBox_TimeValues.setEditable(true);
		comboBox_TimeValues.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Midnight", "Morning", "Afternoon", "Evening" }));
		comboBox_TimeValues.setBounds(10, 50, 135, 20);
		panel_Time.add(comboBox_TimeValues);

		btnChangeTime.setBounds(155, 49, 139, 23);
		panel_Time.add(btnChangeTime);

		lblTimeChangesAre.setBounds(10, 132, 284, 14);
		panel_Time.add(lblTimeChangesAre);

		gbc_panel_Weather.weightx = 1.0;
		gbc_panel_Weather.weighty = 1.0;
		gbc_panel_Weather.insets = new Insets(5, 5, 5, 5);
		gbc_panel_Weather.fill = GridBagConstraints.BOTH;
		gbc_panel_Weather.gridx = 0;
		gbc_panel_Weather.gridy = 1;
		panel_Weather.setBorder(new TitledBorder(null, "Weather",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Weather.setLayout(null);
		panel_SERVER.add(panel_Weather, gbc_panel_Weather);

		lblCurrentConditions.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentConditions.setBounds(10, 24, 132, 14);
		panel_Weather.add(lblCurrentConditions);

		lblWeatherData.setBounds(162, 24, 132, 14);
		panel_Weather.add(lblWeatherData);

		comboBox_Conditions.setModel(new DefaultComboBoxModel<Object>(new String[] {
				"Clear", "Rain/Snow", "Storm" }));
		comboBox_Conditions.setBounds(10, 50, 132, 20);
		panel_Weather.add(comboBox_Conditions);

		btnChangeConditions.setBounds(162, 49, 132, 23);
		panel_Weather.add(btnChangeConditions);

		lblWeatherChangesTend.setBounds(10, 132, 284, 14);
		panel_Weather.add(lblWeatherChangesTend);

		gbc_panel_World.insets = new Insets(5, 5, 5, 5);
		gbc_panel_World.weighty = 1.0;
		gbc_panel_World.weightx = 1.0;
		gbc_panel_World.fill = GridBagConstraints.BOTH;
		gbc_panel_World.gridx = 1;
		gbc_panel_World.gridy = 0;
		panel_World.setBorder(new TitledBorder(null, "World",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_World.setLayout(null);
		panel_SERVER.add(panel_World, gbc_panel_World);

		lblSelectSomethingTo.setBounds(10, 25, 284, 14);
		panel_World.add(lblSelectSomethingTo);

		comboBox_SpawnObject.setModel(new DefaultComboBoxModel<Object>(objects ));
		comboBox_SpawnObject.setBounds(10, 50, 284, 20);
		panel_World.add(comboBox_SpawnObject);

		lblWhere.setBounds(10, 81, 46, 14);
		panel_World.add(lblWhere);

		comboBox_SpawnLocation.setEditable(true);
		comboBox_SpawnLocation.setBounds(10, 106, 142, 20);
		panel_World.add(comboBox_SpawnLocation);

		btnSpawnObject.setBounds(162, 105, 132, 23);
		panel_World.add(btnSpawnObject);

		lblUseTheXyz.setBounds(10, 132, 284, 14);
		panel_World.add(lblUseTheXyz);

		gbc_panel_Message.weightx = 1.0;
		gbc_panel_Message.weighty = 1.0;
		gbc_panel_Message.insets = new Insets(5, 5, 5, 5);
		gbc_panel_Message.fill = GridBagConstraints.BOTH;
		gbc_panel_Message.gridx = 1;
		gbc_panel_Message.gridy = 1;
		panel_Message.setBorder(new TitledBorder(null, "Message",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Message.setLayout(null);
		panel_SERVER.add(panel_Message, gbc_panel_Message);

		textField_Message.setBounds(10, 25, 284, 20);
		panel_Message.add(textField_Message);
		textField_Message.setColumns(10);

		comboBox_Color.setBounds(10, 56, 140, 20);
		panel_Message.add(comboBox_Color);

		btnSendMessage.setBounds(160, 55, 134, 23);
		panel_Message.add(btnSendMessage);

		comboBox_Receiver.setBounds(10, 87, 284, 20);
		panel_Message.add(comboBox_Receiver);

		lblTypeAMessage.setBounds(10, 132, 284, 14);
		panel_Message.add(lblTypeAMessage);
		panel_FUNCTIONS.setBounds(0, 362, 634, 49);

		panel_FUNCTIONS.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		contentPane.add(panel_FUNCTIONS);
		GridBagLayout gbl_panel_FUNCTIONS = new GridBagLayout();
		gbl_panel_FUNCTIONS.columnWidths = new int[]{35, 146, 41, 75, 75, 146, 55, 0};
		gbl_panel_FUNCTIONS.rowHeights = new int[]{21, 19, 0};
		gbl_panel_FUNCTIONS.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_FUNCTIONS.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_FUNCTIONS.setLayout(gbl_panel_FUNCTIONS);
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.anchor = GridBagConstraints.EAST;
		gbc_lblPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer.gridx = 0;
		gbc_lblPlayer.gridy = 0;
		panel_FUNCTIONS.add(lblPlayer, gbc_lblPlayer);
		GridBagConstraints gbc_lbl_PLAYER_DATA = new GridBagConstraints();
		gbc_lbl_PLAYER_DATA.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_PLAYER_DATA.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_PLAYER_DATA.gridx = 1;
		gbc_lbl_PLAYER_DATA.gridy = 0;
		panel_FUNCTIONS.add(lbl_PLAYER_DATA, gbc_lbl_PLAYER_DATA);
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridheight = 2;
		gbc_btnStop.gridx = 3;
		gbc_btnStop.gridy = 0;
		panel_FUNCTIONS.add(btnStop, gbc_btnStop);
		btnStop.addActionListener(this);
		GridBagConstraints gbc_lblWorld = new GridBagConstraints();
		gbc_lblWorld.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblWorld.insets = new Insets(0, 0, 0, 5);
		gbc_lblWorld.gridx = 0;
		gbc_lblWorld.gridy = 1;
		panel_FUNCTIONS.add(lblWorld, gbc_lblWorld);
		GridBagConstraints gbc_lbl_WORLD_DATA = new GridBagConstraints();
		gbc_lbl_WORLD_DATA.anchor = GridBagConstraints.NORTH;
		gbc_lbl_WORLD_DATA.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_WORLD_DATA.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_WORLD_DATA.gridx = 1;
		gbc_lbl_WORLD_DATA.gridy = 1;
		panel_FUNCTIONS.add(lbl_WORLD_DATA, gbc_lbl_WORLD_DATA);
		GridBagConstraints gbc_lblMemory = new GridBagConstraints();
		gbc_lblMemory.anchor = GridBagConstraints.WEST;
		gbc_lblMemory.insets = new Insets(0, 0, 0, 5);
		gbc_lblMemory.gridx = 4;
		gbc_lblMemory.gridy = 1;
		panel_FUNCTIONS.add(lblMemory, gbc_lblMemory);
		
				progressBar_mem = new JProgressBar(0,
						(int) manager.r.totalMemory() / 1024);
				progressBar_mem.setStringPainted(true);
				GridBagConstraints gbc_progressBar_mem = new GridBagConstraints();
				gbc_progressBar_mem.anchor = GridBagConstraints.SOUTHWEST;
				gbc_progressBar_mem.insets = new Insets(0, 0, 0, 5);
				gbc_progressBar_mem.gridx = 5;
				gbc_progressBar_mem.gridy = 1;
				panel_FUNCTIONS.add(progressBar_mem, gbc_progressBar_mem);
				
				GridBagConstraints gbc_btnFreeMem = new GridBagConstraints();
				gbc_btnFreeMem.anchor = GridBagConstraints.NORTHWEST;
				gbc_btnFreeMem.gridx = 6;
				gbc_btnFreeMem.gridy = 1;
				panel_FUNCTIONS.add(btnFreeMem, gbc_btnFreeMem);

		manager.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() instanceof JTextField) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (e.getSource() == textField_console) {
					p.sendCommand(textField_console.getText());
					textField_console.setText("");
					textField_console.setFocusable(true);
					this.requestFocusInWindow();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == btnStop) {
				p.getServer().shutdown();
			} else if (e.getSource() == btnFreeMem) {
				p.freeMemory();
			} else if (e.getSource() == btnChangeTime) {
				String input = comboBox_TimeValues.getSelectedItem().toString();
				if (input.equalsIgnoreCase("Midnight")) {
					p.world.setTime(18000);
				} else if (input.equalsIgnoreCase("Morning")) {
					p.world.setTime(0);
				} else if (input.equalsIgnoreCase("Afternoon")) {
					p.world.setTime(6000);
				} else if (input.equalsIgnoreCase("Evening")) {
					p.world.setTime(12000);
				} else {
					try {
						long setTime = Long.parseLong(input);
						p.world.setTime(setTime);
					} catch (NumberFormatException nfe) {
						nfe.printStackTrace();
					}
				}
			} else if (e.getSource() == btnChangeConditions) {
				String input = comboBox_Conditions.getSelectedItem().toString();
				if (input.equalsIgnoreCase("Clear")) {
					p.world.setStorm(false);
					p.world.setThundering(false);
				} else if (input.equalsIgnoreCase("Rain/Snow")) {
					p.world.setStorm(true);
				} else if (input.equalsIgnoreCase("Storm")) {
					p.world.setStorm(true);
					p.world.setThundering(true);
				}
			} else if (e.getSource() == btnSpawnObject) {
				String input = comboBox_SpawnObject.getSelectedItem().toString();
				String input2 = comboBox_SpawnLocation.getSelectedItem().toString();
				spawnObject(input, input2);
			}
		} else if (e.getSource() instanceof JMenuItem) {
			if (e.getSource() == mnVisitThread) {
				p.goToSite(SATools.threadURL);
			} else if (e.getSource() == mnSubmitFeedback) {
				p.goToSite("http://dev.bukkit.org/server-mods/satools/create-ticket/");
			} else if (e.getSource() == mnCheckForUpdates) {
				if (p.updateChecked()) {
					log.info("Update Checked");
				} else {
					log.warning("Could not communicate with the update server");
				}
			} else if (e.getSource() == mnAbout) {
				JOptionPane
						.showMessageDialog(
								null,
								"SATools for Bukkit Server Wrapper\n\n"
										+ "Version: "
										+ p.pdfFile.getVersion()
										+ "\n\u00a9"
										+ "2011 by OneThatWalks. All Rights Reserved."
										+ "This program is licensed under the GNU General Public License",
								"SATools by OneThatWalks",
								JOptionPane.INFORMATION_MESSAGE);
			} else if (e.getSource() == mnExit) {
				p.getServer().shutdown();
			}
		}
	}

	public void spawnObject(String what, String where) {
		switch (what) {
		case "":
			
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}
	
	public void freeMemory() {
		try {
			Runtime r =  Runtime.getRuntime();
			log.info((r.totalMemory() - r
					.freeMemory())/1024 + " Megabytes");
			r.gc();
			log.info((r.totalMemory() - r
					.freeMemory())/1024 + " Megabytes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

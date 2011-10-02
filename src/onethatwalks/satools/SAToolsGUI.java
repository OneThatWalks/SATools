package onethatwalks.satools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
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
import javax.swing.border.EtchedBorder;

import onethatwalks.threads.GUIManager;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;

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
	Plugin p;
	GUIManager manager;
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
	JLabel lblPlayer = new JLabel("Player:");
	JLabel lbl_PLAYER_DATA = new JLabel("NULL");
	JLabel lblWorld = new JLabel(" World:");
	public JLabel lbl_WORLD_DATA = new JLabel("NULL");
	JButton btnStop = new JButton("Stop");
	JLabel lblMemory = new JLabel("Memory Usage:");
	public JProgressBar progressBar_mem;
	public JTextArea textArea = new JTextArea();
	public JTextField textField = new JTextField();
	GridBagLayout gbl_panel_CONSOLE = new GridBagLayout();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel_SERVER = new JPanel();
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

	/**
	 * Create the frame.
	 */
	public SAToolsGUI(Plugin instance) {
		super();
		p = instance;
		manager = new GUIManager(p, this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 460);
		setResizable(false);
		setVisible(true);

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
		textField.setColumns(10);
		textField.addKeyListener(this);
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

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_CONSOLE.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(textArea);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setEditable(false);

		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 9;
		panel_CONSOLE.add(textField, gbc_textField);

		tabbedPane.addTab("Server", null, panel_SERVER, null);
		GridBagLayout gbl_panel_SERVER = new GridBagLayout();
		gbl_panel_SERVER.columnWidths = new int[] { 0 };
		gbl_panel_SERVER.rowHeights = new int[] { 0 };
		gbl_panel_SERVER.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel_SERVER.rowWeights = new double[] { Double.MIN_VALUE };
		panel_SERVER.setLayout(gbl_panel_SERVER);
		panel_SERVER.add(new JLabel("Comming October 4th"),
				new GridBagConstraints(0, 0, 1, 1, 0, 0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(5, 5, 5, 5), 0, 0));
		panel_FUNCTIONS.setBounds(0, 362, 634, 49);

		panel_FUNCTIONS.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		contentPane.add(panel_FUNCTIONS);
		GridBagLayout gbl_panel_FUNCTIONS = new GridBagLayout();
		panel_FUNCTIONS.setLayout(gbl_panel_FUNCTIONS);
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.anchor = GridBagConstraints.EAST;
		gbc_lblPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer.gridx = 0;
		gbc_lblPlayer.gridy = 0;
		panel_FUNCTIONS.add(lblPlayer, gbc_lblPlayer);
		btnStop.addActionListener(this);
		GridBagConstraints gbc_lbl_PLAYER_DATA = new GridBagConstraints();
		gbc_lbl_PLAYER_DATA.anchor = GridBagConstraints.WEST;
		gbc_lbl_PLAYER_DATA.gridwidth = 7;
		gbc_lbl_PLAYER_DATA.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_PLAYER_DATA.gridx = 1;
		gbc_lbl_PLAYER_DATA.gridy = 0;
		panel_FUNCTIONS.add(lbl_PLAYER_DATA, gbc_lbl_PLAYER_DATA);
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.gridheight = 2;
		gbc_btnStop.gridwidth = 4;
		gbc_btnStop.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 8;
		gbc_btnStop.gridy = 0;
		panel_FUNCTIONS.add(btnStop, gbc_btnStop);
		GridBagConstraints gbc_lblWorld = new GridBagConstraints();
		gbc_lblWorld.anchor = GridBagConstraints.EAST;
		gbc_lblWorld.insets = new Insets(0, 0, 0, 5);
		gbc_lblWorld.gridx = 0;
		gbc_lblWorld.gridy = 1;
		panel_FUNCTIONS.add(lblWorld, gbc_lblWorld);
		GridBagConstraints gbc_lbl_WORLD_DATA = new GridBagConstraints();
		gbc_lbl_WORLD_DATA.anchor = GridBagConstraints.WEST;
		gbc_lbl_WORLD_DATA.gridwidth = 7;
		gbc_lbl_WORLD_DATA.insets = new Insets(0, 0, 0, 5);
		gbc_lbl_WORLD_DATA.gridx = 1;
		gbc_lbl_WORLD_DATA.gridy = 1;
		panel_FUNCTIONS.add(lbl_WORLD_DATA, gbc_lbl_WORLD_DATA);
		GridBagConstraints gbc_lblMemory = new GridBagConstraints();
		gbc_lblMemory.gridheight = 2;
		gbc_lblMemory.anchor = GridBagConstraints.EAST;
		gbc_lblMemory.insets = new Insets(0, 0, 0, 5);
		gbc_lblMemory.gridx = 13;
		gbc_lblMemory.gridy = 0;
		panel_FUNCTIONS.add(lblMemory, gbc_lblMemory);

		progressBar_mem = new JProgressBar(0,
				(int) manager.r.totalMemory() / 1024);
		progressBar_mem.setStringPainted(true);
		GridBagConstraints gbc_progressBar_mem = new GridBagConstraints();
		gbc_progressBar_mem.gridheight = 2;
		gbc_progressBar_mem.gridwidth = 4;
		gbc_progressBar_mem.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar_mem.gridx = 14;
		gbc_progressBar_mem.gridy = 0;
		panel_FUNCTIONS.add(progressBar_mem, gbc_progressBar_mem);
		manager.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == btnStop) {
				p.getServer().dispatchCommand(
						new ConsoleCommandSender(p.getServer()), "stop");
			}
		} else if (e.getSource() instanceof JMenuItem) {
			if (e.getSource() == mnVisitThread) {
				((SATools) p).goToSite(SATools.threadURL);
			} else if (e.getSource() == mnSubmitFeedback) {
				((SATools) p)
						.goToSite("http://dev.bukkit.org/server-mods/satools/create-ticket/");
			} else if (e.getSource() == mnAbout) {
				JOptionPane
						.showMessageDialog(
								null,
								"SATools for Bukkit Server Wrapper\n\n"
										+ "Version: "
										+ ((SATools) p).pdfFile.getVersion()
										+ "\n\u00a9"
										+ "2011 by OneThatWalks. All Rights Reserved."
										+ "This program is licensed under the GNU General Public License",
								"SATools by OneThatWalks",
								JOptionPane.INFORMATION_MESSAGE);
			} else if (e.getSource() == mnExit) {
				p.getServer().dispatchCommand(
						new ConsoleCommandSender(p.getServer()), "stop");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& e.getSource() instanceof JTextField) {
			p.getServer().dispatchCommand(
					new ConsoleCommandSender(p.getServer()),
					((JTextField) e.getSource()).getText());
			((JTextField) e.getSource()).setText("");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}

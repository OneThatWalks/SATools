package onethatwalks.satools;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SAToolsGUI_temp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar_TOOLBAR = null;
	private JMenu jMenu_FILE = null;
	private JTabbedPane jTabbedPane_MAIN = null;
	private JScrollPane jScrollPane_MAIN = null;
	private JScrollPane jScrollPane_CONSOLE = null;
	private JPanel jPanel_CONSOLE = null;
	private JTextArea jTextArea_CONSOLE = null;
	private JTextField jTextField_CONSOLE_SEND = null;
	private JPanel jPanel_FUNCTIONS = null;

	/**
	 * This is the default constructor
	 */
	public SAToolsGUI_temp() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		getToolkit();
		Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
		if (res.width < 768 && res.height < 1366) {
			this.setSize(400, 600);
		} else {
			this.setSize(600, 800);
		}
		this.setJMenuBar(getJJMenuBar_TOOLBAR()); // Generated
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH; // Generated
			gridBagConstraints.gridy = 0; // Generated
			gridBagConstraints.weightx = 1.0; // Generated
			gridBagConstraints.weighty = 1.0; // Generated
			gridBagConstraints.gridx = 0; // Generated
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH; // Generated
			gridBagConstraints3.gridy = 1; // Generated
			gridBagConstraints3.weightx = 1.0; // Generated
			gridBagConstraints3.weighty = 0.1; // Generated
			gridBagConstraints3.gridx = 0; // Generated
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJTabbedPane_MAIN(), gridBagConstraints); // Generated
			jContentPane.add(getJPanel_FUNCTIONS(), gridBagConstraints3);
		}
		return jContentPane;
	}

	private JPanel getJPanel_FUNCTIONS() {
		if (jPanel_FUNCTIONS == null) {
			jPanel_FUNCTIONS = new JPanel();
		}
		return jPanel_FUNCTIONS;
	}

	/**
	 * This method initializes jJMenuBar_TOOLBAR
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getJJMenuBar_TOOLBAR() {
		if (jJMenuBar_TOOLBAR == null) {
			jJMenuBar_TOOLBAR = new JMenuBar();
			jJMenuBar_TOOLBAR.add(getJMenu_FILE()); // Generated
		}
		return jJMenuBar_TOOLBAR;
	}

	/**
	 * This method initializes jMenu_FILE
	 * 
	 * @return javax.swing.JMenu
	 */
	private JMenu getJMenu_FILE() {
		if (jMenu_FILE == null) {
			jMenu_FILE = new JMenu();
			jMenu_FILE.setText("File"); // Generated
		}
		return jMenu_FILE;
	}

	/**
	 * This method initializes jTabbedPane_MAIN
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane_MAIN() {
		if (jTabbedPane_MAIN == null) {
			jTabbedPane_MAIN = new JTabbedPane();
			jTabbedPane_MAIN.addTab("Console", null, getJScrollPane_CONSOLE(),
					null); // Generated
			jTabbedPane_MAIN.addTab("Main", null, getJScrollPane_MAIN(), null); // Generated
		}
		return jTabbedPane_MAIN;
	}

	/**
	 * This method initializes jScrollPane_MAIN
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane_MAIN() {
		if (jScrollPane_MAIN == null) {
			jScrollPane_MAIN = new JScrollPane();
		}
		return jScrollPane_MAIN;
	}

	/**
	 * This method initializes jScrollPane_CONSOLE
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane_CONSOLE() {
		if (jScrollPane_CONSOLE == null) {
			jScrollPane_CONSOLE = new JScrollPane();
			jScrollPane_CONSOLE.setViewportView(getJPanel_CONSOLE()); // Generated
		}
		return jScrollPane_CONSOLE;
	}

	/**
	 * This method initializes jPanel_CONSOLE
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_CONSOLE() {
		if (jPanel_CONSOLE == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH; // Generated
			gridBagConstraints2.gridy = 1; // Generated
			gridBagConstraints2.weightx = 1.0; // Generated
			gridBagConstraints2.gridwidth = 2; // Generated
			gridBagConstraints2.gridx = 0; // Generated
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH; // Generated
			gridBagConstraints1.gridy = 0; // Generated
			gridBagConstraints1.weightx = 1.0; // Generated
			gridBagConstraints1.weighty = 1.0; // Generated
			gridBagConstraints1.gridx = 0; // Generated
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			jPanel_CONSOLE = new JPanel();
			jPanel_CONSOLE.setLayout(new GridBagLayout()); // Generated
			jPanel_CONSOLE.add(getJTextArea_CONSOLE(), gridBagConstraints1); // Generated
			jPanel_CONSOLE.add(getJTextField_CONSOLE_SEND(),
					gridBagConstraints2); // Generated
		}
		return jPanel_CONSOLE;
	}

	/**
	 * This method initializes jTextArea_CONSOLE
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea_CONSOLE() {
		if (jTextArea_CONSOLE == null) {
			jTextArea_CONSOLE = new JTextArea();
			jTextArea_CONSOLE.setEditable(false); // Generated
		} // TODO GUI manager update this all the time with the server
			// console...
		return jTextArea_CONSOLE;
	}

	/**
	 * This method initializes jTextField_CONSOLE_SEND
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_CONSOLE_SEND() {
		if (jTextField_CONSOLE_SEND == null) {
			jTextField_CONSOLE_SEND = new JTextField();
			jTextField_CONSOLE_SEND
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyTyped(java.awt.event.KeyEvent e) {
							if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
								// send command TODO
							}
						}
					});
		}
		return jTextField_CONSOLE_SEND;
	}

}

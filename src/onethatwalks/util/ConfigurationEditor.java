package onethatwalks.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import onethatwalks.satools.SAToolsGUI;

import org.bukkit.util.config.Configuration;

public class ConfigurationEditor extends JFrame {

	/**
	 * Config Editor Variables
	 */
	public static final Logger log = Logger.getLogger("Minecraft");
	private static final long serialVersionUID = 1L;
	private JButton jButton_SAVE = null;
	private JComboBox jComboBox_CHECK_UPDATE = null;
	private JComboBox jComboBox_SHOW_CHECK = null;
	private JLabel jLabel_CHECK_UPDATE = null;
	private JLabel jLabel_SHOW_CHECK = null;
	private JPanel jPanel_MAIN = null;
	private DefaultComboBoxModel trueFlase_Model = null; // @jve:decl-index=0:visual-constraint="568,68"

	/**
	 * This method initializes
	 * 
	 */
	public ConfigurationEditor() {
		super();
		initialize();
	}

	/**
	 * This method initializes jButton_SAVE
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_SAVE() {
		if (jButton_SAVE == null) {
			jButton_SAVE = new JButton();
			jButton_SAVE.setBounds(new Rectangle(150, 320, 90, 30)); // Generated
			jButton_SAVE.setText("Save"); // Generated
			jButton_SAVE.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveConfig();
				}
			});
		}
		return jButton_SAVE;
	}

	/**
	 * This method initializes jComboBox_CEHCK_UPDATE
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_CEHCK_UPDATE() {
		if (jComboBox_CHECK_UPDATE == null) {
			jComboBox_CHECK_UPDATE = new JComboBox(getTrueFlase_Model());
			jComboBox_CHECK_UPDATE.setBounds(new Rectangle(210, 50, 155, 20)); // Generated
			jComboBox_CHECK_UPDATE.setSelectedItem(true); // Generated
		}
		return jComboBox_CHECK_UPDATE;
	}

	/**
	 * This method initializes jComboBox_SHOW_CHECK
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_SHOW_CHECK() {
		if (jComboBox_SHOW_CHECK == null) {
			jComboBox_SHOW_CHECK = new JComboBox(getTrueFlase_Model());
			jComboBox_SHOW_CHECK.setBounds(new Rectangle(210, 15, 155, 20)); // Generated
			jComboBox_SHOW_CHECK.setSelectedItem(true);
		}
		return jComboBox_SHOW_CHECK;
	}

	/**
	 * This method initializes jPanel_MAIN
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_MAIN() {
		if (jPanel_MAIN == null) {
			jLabel_CHECK_UPDATE = new JLabel();
			jLabel_CHECK_UPDATE.setBounds(new Rectangle(15, 50, 165, 16)); // Generated
			jLabel_CHECK_UPDATE.setText("Check for updates on start?"); // Generated
			jLabel_SHOW_CHECK = new JLabel();
			jLabel_SHOW_CHECK.setText("Show update box on start?"); // Generated
			jLabel_SHOW_CHECK.setBounds(new Rectangle(15, 15, 158, 20)); // Generated
			jPanel_MAIN = new JPanel();
			jPanel_MAIN.setLayout(null); // Generated
			jPanel_MAIN.add(jLabel_SHOW_CHECK, null); // Generated
			jPanel_MAIN.add(getJComboBox_SHOW_CHECK(), null); // Generated
			jPanel_MAIN.add(getJButton_SAVE(), null); // Generated
			jPanel_MAIN.add(getJComboBox_CEHCK_UPDATE(), null); // Generated
			jPanel_MAIN.add(jLabel_CHECK_UPDATE, null); // Generated
		}
		return jPanel_MAIN;
	}

	/**
	 * This method initializes trueFlase_Model
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getTrueFlase_Model() {
		if (trueFlase_Model == null) {
			trueFlase_Model = new DefaultComboBoxModel();
			trueFlase_Model.addElement(true);
			trueFlase_Model.addElement(false);
		}
		return trueFlase_Model;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(400, 400)); // Generated
		this.setTitle("Configuration - SATools"); // Generated
		this.setContentPane(getJPanel_MAIN()); // Generated
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(this.getOwner());
	}

	protected void saveConfig() {
		File configFile = new File(SAToolsGUI.plugin.getDataFolder(),
				"config.yml");
		Configuration config;
		if (configFile.exists()) {
			config = new Configuration(configFile);
			config.load();
		} else {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			config = new Configuration(configFile);
		}
		log.info("Saving Configuration");
		config.setProperty("Show update check",
				jComboBox_SHOW_CHECK.getSelectedItem());
		config.setProperty("Check for updates",
				jComboBox_CHECK_UPDATE.getSelectedItem());
		config.save();
		this.dispose();
	}

}

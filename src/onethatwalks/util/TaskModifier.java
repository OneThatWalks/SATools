package onethatwalks.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import onethatwalks.satools.SATools;
import onethatwalks.satools.SAToolsGUI;
import onethatwalks.tasker.Task;

public class TaskModifier extends JFrame {

	/**
	 * Modifier Variables
	 */
	public static final Logger log = Logger.getLogger("Minecraft"); // @jve:decl-index=0:
	private static final long serialVersionUID = -5728654482887951174L;
	private DefaultComboBoxModel defaultComboBoxModel_WHEN = null;
	private File file;
	private JButton jButton_SAVE = null;
	private JComboBox jComboBox_WHEN = null;
	private JLabel jLabel_NAME = null;
	private JLabel jLabel_PREVIEW = null;
	private JLabel jLabel_TIME = null;
	private JPanel jPanel_CONTENT = null;
	private JTextArea jTextArea_MODIFY_TASK = null;
	private JTextField jTextField_NAME = null;
	NumberHandler numbers = new NumberHandler();

	/**
	 * This method initializes
	 * 
	 */
	public TaskModifier(File f) {
		super();
		initialize();
		this.file = f;
		ArrayList<String> text = Task.createLocalizedInstructions(f);
		if (text.get(0).contains("#")) {
			text.remove(0);
		}
		for (String line : text) {
			String object = jTextArea_MODIFY_TASK.getText();
			if (object.split(System.getProperty("line.separator")).length > 1) {
				jTextArea_MODIFY_TASK.setText(object
						+ System.getProperty("line.separator") + line);
			} else {
				jTextArea_MODIFY_TASK.setText(line);
			}
		}
	}

	protected void doSave() {
		File mf = new File(SATools.taskScheduler.macroFolder);
		if (!mf.exists()) {
			log.info("No macro folder found, making one instead.");
			mf.mkdir();
		} else {
			log.info("Found dir saving macro");
		}
		File m = new File(SATools.taskScheduler.macroFolder
				+ jTextField_NAME.getText() + ".macro");
		if (m.exists()) {
			if (JOptionPane.showConfirmDialog(null,
					"File found\n Do you wish to overwrite?", "Alert",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					FileWriter fw = new FileWriter(m);
					PrintWriter pw = new PrintWriter(fw);
					pw.println("#" + getMacroTime());
					String[] previewText = jTextArea_MODIFY_TASK.getText()
							.split(System.getProperty("line.separator"));
					for (String text : previewText) {
						if (!text.isEmpty()) {
							pw.println(text);
						}
					}
					log.info("Saved");
					fw.close();
					SATools.taskScheduler.init();
					this.dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				log.warning("Could not save macro. Save aborted");
			}
		} else {
			try {
				file.delete();
				m.createNewFile();
				FileWriter fw = new FileWriter(m);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("#" + getMacroTime());
				String[] previewText = {};
				if (jTextArea_MODIFY_TASK.getText().split(
						System.getProperty("line.separator")).length > 1) {
					previewText = jTextArea_MODIFY_TASK.getText().split(
							System.getProperty("line.seperator"));
				} else {
					previewText[0] = jTextArea_MODIFY_TASK.getText();
				}
				for (String text : previewText) {
					if (!text.isEmpty() || !text.equals(null)) {
						pw.println(text);
					} else {
						log.info("Empty line, skipping");
					}
				}
				log.info("Saved");
				fw.close();
				SATools.taskScheduler.init();
				this.dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method initializes defaultComboBoxModel_WHEN
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_WHEN() {
		if (defaultComboBoxModel_WHEN == null) {
			defaultComboBoxModel_WHEN = new DefaultComboBoxModel();
			defaultComboBoxModel_WHEN.addElement("Midnight");
			defaultComboBoxModel_WHEN.addElement("Morning");
			defaultComboBoxModel_WHEN.addElement("Noon");
			defaultComboBoxModel_WHEN.addElement("Dusk");
		}
		return defaultComboBoxModel_WHEN;
	}

	/**
	 * This method initializes jButton_SAVE
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_SAVE() {
		if (jButton_SAVE == null) {
			jButton_SAVE = new JButton();
			jButton_SAVE.setBounds(new Rectangle(140, 335, 100, 20)); // Generated
			jButton_SAVE.setText("Save"); // Generated
			jButton_SAVE.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					doSave();
				}
			});
		}
		return jButton_SAVE;
	}

	/**
	 * This method initializes jComboBox_WHEN
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_WHEN() {
		if (jComboBox_WHEN == null) {
			jComboBox_WHEN = new JComboBox(getDefaultComboBoxModel_WHEN());
			jComboBox_WHEN.setBounds(new Rectangle(75, 45, 255, 20)); // Generated
			jComboBox_WHEN.setEditable(true); // Generated
			jComboBox_WHEN.setEnabled(true); // Generated
		}
		return jComboBox_WHEN;
	}

	/**
	 * This method initializes jPanel_CONTENT
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_CONTENT() {
		if (jPanel_CONTENT == null) {
			jLabel_TIME = new JLabel();
			jLabel_TIME.setBounds(new Rectangle(15, 45, 38, 16)); // Generated
			jLabel_TIME.setText("When:"); // Generated
			jLabel_NAME = new JLabel();
			jLabel_NAME.setBounds(new Rectangle(15, 15, 38, 16)); // Generated
			jLabel_NAME.setText("Name:"); // Generated
			jLabel_PREVIEW = new JLabel();
			jLabel_PREVIEW.setBounds(new Rectangle(15, 80, 80, 16)); // Generated
			jLabel_PREVIEW.setText("Preview:"); // Generated
			jPanel_CONTENT = new JPanel();
			jPanel_CONTENT.setLayout(null); // Generated
			jPanel_CONTENT.add(getJTextArea_MODIFY_TASK(), null); // Generated
			jPanel_CONTENT.add(jLabel_PREVIEW, null); // Generated
			jPanel_CONTENT.add(getJButton_SAVE(), null); // Generated
			jPanel_CONTENT.add(jLabel_NAME, null); // Generated
			jPanel_CONTENT.add(getJTextField_NAME(), null); // Generated
			jPanel_CONTENT.add(jLabel_TIME, null); // Generated
			jPanel_CONTENT.add(getJComboBox_WHEN(), null); // Generated
		}
		return jPanel_CONTENT;
	}

	/**
	 * This method initializes jTextArea_MODIFY_TASK
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea_MODIFY_TASK() {
		if (jTextArea_MODIFY_TASK == null) {
			jTextArea_MODIFY_TASK = new JTextArea();
			jTextArea_MODIFY_TASK.setBounds(new Rectangle(15, 100, 355, 224)); // Generated
			jTextArea_MODIFY_TASK.setFont(new Font("Dialog", Font.PLAIN, 12)); // Generated
			jTextArea_MODIFY_TASK.setForeground(Color.white); // Generated
			jTextArea_MODIFY_TASK.setBackground(Color.black); // Generated
		}
		return jTextArea_MODIFY_TASK;
	}

	/**
	 * This method initializes jTextField_NAME
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_NAME() {
		if (jTextField_NAME == null) {
			jTextField_NAME = new JTextField();
			jTextField_NAME.setBounds(new Rectangle(75, 15, 255, 20)); // Generated
			jTextField_NAME.setText(SAToolsGUI.jList_SCHEDULE_TASKS
					.getSelectedValue().toString());
		}
		return jTextField_NAME;
	}

	private String getMacroTime() {
		String selection = jComboBox_WHEN.getSelectedItem().toString();
		if (numbers.isNumeric(selection)) {
			return selection;
		} else {
			if (selection.equalsIgnoreCase("midnight")) {
				return "18000";
			} else if (selection.equalsIgnoreCase("morning")) {
				return "0";
			} else if (selection.equalsIgnoreCase("noon")) {
				return "6000";
			} else if (selection.equalsIgnoreCase("dusk")) {
				return "12000";
			} else {
				return null;
			}
		}
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setPreferredSize(new Dimension(400, 400)); // Generated
		this.setMinimumSize(new Dimension(400, 400)); // Generated
		this.setContentPane(getJPanel_CONTENT()); // Generated
		this.setTitle("SATools - Task Editor"); // Generated
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(this.getOwner());
	}

}

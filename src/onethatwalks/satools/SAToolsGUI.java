package onethatwalks.satools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import onethatwalks.satools.SATools.Weather;
import onethatwalks.util.NumberHandler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

/**
 * GUI for SATools
 * 
 * @author OneThatWalks
 */
public class SAToolsGUI extends JFrame {

	// Variables
	private static final long serialVersionUID = 1L;
	public static final Logger log = Logger.getLogger("Minecraft"); // @jve:decl-index=0:
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel_MAIN = null;
	private static SATools plugin;
	private JPanel jPanel_PLAYERS = null;
	private JScrollPane jScrollPane_PLAYERS_PLAYERS = null;
	public static JList jList_PLAYERS_PLAYERS = null;
	public static DefaultListModel DefaultListModel_PLAYERS_PLAYERS = null; // @jve:decl-index=1:visual-constraint="280,1000"
	public static JPanel jPanel_PLAYERS_PLAYER = null;
	public static JPanel jPanel_PLAYERS_MODIFY = null;
	private JLabel jLabel_PLAYERS_PLAYER_LOCATION = null;
	public static JLabel jLabel_PLAYERS_PLAYER_LOCATION_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_ENTITYID = null;
	public static JLabel jLabel_PLAYERS_PLAYER_ENTITYID_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_HEALTH = null;
	public static JLabel jLabel_PLAYERS_PLAYER_HEALTH_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_ITEM = null;
	public static JLabel jLabel_PLAYERS_PLAYER_ITEM_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_DEAD = null;
	public static JLabel jLabel_PLAYERS_PLAYER_DEAD_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_OP = null;
	public static JLabel jLabel_PLAYERS_PLAYER_OP_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_SLEEP = null;
	public static JLabel jLabel_PLAYERS_PLAYER_SLEEP_DATA = null;
	private JLabel jLabel_PLAYERS_PLAYER_SNEAK = null;
	public static JLabel jLabel_PLAYERS_PLAYER_SNEAK_DATA = null;
	public static Player player = null;
	static boolean piAlive = false;
	public static PlayerInfo pi = new PlayerInfo(); // @jve:decl-index=0:
	private JLabel jLabel_PLAYERS_MODIFY_GIVE = null;
	private JComboBox jComboBox_PLAYERS_MODIFY_GIVE = null;
	private JButton jButton_PLAYERS_MODIFY_GIVE_64 = null;
	private JButton jButton_PLAYERS_MODIFY_GIVE_1 = null;
	private JTextField jTextField_PLAYERS_MODIFY_GIVE_INT = null;
	static HashMap<String, Integer> items = new HashMap<String, Integer>(); // @jve:decl-index=0:
	private DefaultComboBoxModel DefaultComboBoxModel_PLAYERS_MODIFY_GIVE = new DefaultComboBoxModel(); // @jve:decl-index=0:visual-constraint="280,837"
	private static int selected_id = 0;
	private JPanel jPanel_MAIN_TIME = null;
	private JPanel jPanel_MAIN_CONSOLE = null;
	private JLabel jLabel_MAIN_CONSOLE_SAY = null;
	private JTextField jTextField_MAIN_CONSOLE_SAY = null;
	private JButton jButton_MAIN_CONSOLE_SAY = null;
	private JLabel jLabel_MAIN_CONSOLE_MESSAGE = null;
	public static JComboBox jComboBox_MAIN_CONSOLE_MESSAGE = null;
	public static DefaultComboBoxModel DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE = null; // @jve:decl-index=2:visual-constraint="280,900"
	private JTextField jTextField_MAIN_CONSOLE_MESSAGE = null;
	private JButton jButton_MAIN_CONSOLE_MESSAGE = null;
	private JLabel jLabel_MAIN_CONSOLE_COMMAND = null;
	private JTextField jTextField_MAIN_CONSOLE_COMMAND = null;
	private JButton jButton_MAIN_CONSOLE_COMMAND = null;
	private JLabel jLabel_MAIN_TIME = null;
	private JLabel jLabel_MAIN_TIME_DATA = null;
	private boolean ttAlive = true;
	TimeTrack tt = new TimeTrack(); // @jve:decl-index=1:
	private JLabel jLabel_MAIN_TIME_SET = null;
	private JButton jButton_MAIN_TIME_SET_MIDNIGHT = null;
	private JButton jButton_MAIN_TIME_SET_MORNING = null;
	private JButton jButton_MAIN_TIME_SET_NOON = null;
	private JButton jButton_MAIN_TIME_SET_DUSK = null;
	private JTextField jTextField_MAIN_TIME_SET_INT = null;
	private JPanel jPanel_MAIN_WEATHER = null;
	private JLabel jLabel_MAIN_WEATHER = null;
	public static JButton jButton_MAIN_WEATHER_CLEAR = null;
	public static JButton jButton_MAIN_WEATHER_STORM = null;
	public static JButton jButton_MAIN_WEATHER_THUNDER = null;
	private JLabel jLabel_MAIN_WEATHER_DATA = null;
	private JLabel jLabel_PLAYERS_MODIFY_HEALTH = null;
	private JButton jButton_PLAYERS_MODIFY_HEALTH_FULL = null;
	private JButton jButton_PLAYERS_MODIFY_HEALTH_KILL = null;
	public static JToggleButton jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS = null;
	private JTextField jTextField_PLAYERS_MODIFY_HEALTH_INT = null;
	private JPanel jPanel_MAIN_SPAWN = null;
	private JLabel jLabel_MAIN_SPAWN = null;
	private JComboBox jComboBox_MAIN_SPAWN = null;
	private DefaultComboBoxModel defaultComboBoxModel_MAIN_SPAWN = null; // @jve:decl-index=0:visual-constraint="820,743"
	private JLabel jLabel_MAIN_SPAWN_WHERE = null;
	public static JComboBox jComboBox_MAIN_SPAWN_LOCATION = null;
	private JButton jButton_MAIN_SPAWN = null;
	public static DefaultComboBoxModel defaultComboBoxModel_MAIN_SPAWN_LOCATION = null; // @jve:decl-index=0:visual-constraint="744,529"
	private JSeparator jSeparator_MAIN_SPAWN = null;
	private JLabel jLabel_MAIN_SPAWN_OBJECT = null;
	private JComboBox jComboBox_MAIN_SPAWN_OBJECT = null;
	private DefaultComboBoxModel defaultComboBoxModel_MAIN_SPAWN_OBJECT = null;
	private JLabel jLabel_MAIN_SPAWN_WHERE_OBJECT = null;
	public static JComboBox jComboBox_MAIN_SPAWN_LOCATION_OBJECT = null;
	public static DefaultComboBoxModel defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT = null;
	private JButton jButton_MAIN_SPAWN_OBJECT = null;
	private JLabel jLabel_MAIN_SPAWN_WARNING = null;
	private String[] objects = { "Tree", "Lightning", "Light post" };
	private JPanel jPanel_SCHEDULE = null;
	private JScrollPane jScrollPane_SCHEDULE_TASKS = null;
	private JLabel jLabel_SCHEDULE_TASKS = null;
	private JPanel jPanel_SCHEDULE_TASKS_INFO = null;
	private JSeparator jSeparator_SCHEDULE_TASKS = null;
	private JSeparator jSeparator_MAIN_SCHEDULE_MODIFY = null;
	private JToggleButton jButton_SCHEDULE_TASKS_ADD = null;
	private JButton jButton_SCHEDULE_TASKS_REMOVE = null;
	private JButton jButton_SCHEDULE_TASKS_MODIFY = null;
	private JLabel jLabel_SCHEDULE_TASKS_MODIFY_NAME = null;
	private JTextField jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA = null;
	private JLabel jLabel_SCHEDULE_TASKS_MODIFY_WHEN = null;
	private JComboBox jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA = null;
	private JLabel jLabel_SCHEDULE_TASKS_MODIFY_TODO = null;
	private JComboBox jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA = null;
	private JList jList_SCHEDULE_TASKS = null;
	private JButton jButton_SCHEDULE_TASKS_MODIFY_ADD = null;
	private JTextPane jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW = null;
	private JLabel jLabel_SCHEDULE_TASKS_PREVIEW = null;
	private DefaultComboBoxModel defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA = null; // @jve:decl-index=0:visual-constraint="749,437"
	private String[] actions = { "serverSay", "playerSay", "setTime",
			"setWeather", "spawnMob", "spawnObject", "givePlayer",
			"playerHealth" };
	private DefaultComboBoxModel defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA = null; // @jve:decl-index=0:visual-constraint="754,352"
	private String[] times = { "midnight", "morning", "noon", "dusk" };
	private JButton jButton_SCHEDULE_TASKS_MODIFY_SAVE = null;
	private JButton jButton_MAIN_RESTART = null;
	public static DefaultListModel defaultListModel_SCHEDULE_TASKS = null;
	private JLabel jLabel_SCHEDULE_TASKS_INFO_NAME = null;
	private JLabel jLabel_SCHEDULE_TASKS_INFO_NAME_DATA = null;
	private JLabel jLabel_SCHEDULE_TASKS_INFO_WHEN = null;
	private JLabel jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA = null;
	private JComboBox jComboBox_MAIN_CONSOLE_SAY_COLOR = null;
	private JComboBox jComboBox_MAIN_CONSOLE_MESSAGE_COLOR = null;
	private DefaultComboBoxModel defaultComboBoxModel_CHAT_COLORS = null; // @jve:decl-index=0:visual-constraint="868,219"
	private ArrayList<String> colors = new ArrayList<String>(); // @jve:decl-index=0:
	private JLabel jLabel_SCHEDULE_TASKS_TIME_SET = null;
	NumberHandler numbers = new NumberHandler(); // @jve:decl-index=0:
	private JPanel jPanel_MAP = null;
	private JPanel jPanel_GREIF = null;
	private JLabel jLabel_MAP_COMINGSOON = null;
	private JLabel jLabel_GREIF_COMINGSOON = null;
	GUIManager gm = new GUIManager();
	boolean gmAlive = false;

	public enum TaskActions {
		serverSay, playerSay, setTime, setWeather, spawnMob, spawnObject, givePlayer, playerHealth
	}

	/**
	 * This is the default constructor
	 */
	public SAToolsGUI(SATools instance) {
		super();
		plugin = instance;
		grabItems();
		initialize();
		if (!tt.isAlive()) {
			tt.start();
			ttAlive = true;
		}
		if (!gm.isAlive()) {
			gm.start();
			gmAlive = true;
		}
	}

	/**
	 * This method grabs the items for minecraft - may need updated every
	 * minecraft update
	 */
	private void grabItems() {
		try {
			// Open the file that is the first
			// command line parameter

			InputStream is = getClass().getResourceAsStream(
					"/resources/ItemList.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] token = strLine.split(",");
				int id = Integer.parseInt(token[0]);
				String item = token[1];
				items.put(item, id);
				DefaultComboBoxModel_PLAYERS_MODIFY_GIVE.addElement(item);
			}
			// Close the input stream
			is.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		try {
			this.setSize(600, 800);
			this.setContentPane(getJContentPane());
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					close();
				}
			});
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("/resources/icon.png")));
			this.setLocationRelativeTo(this.getOwner());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closing operation for SATools
	 * 
	 * @return Closing Operation DISPOSE_ON_CLOSE
	 */
	private void close() {
		if (plugin.getServer().dispatchCommand(
				new ConsoleCommandSender(plugin.getServer()), "stop"))
			;
	}

	/**
	 * Checks the worlds weather conditions
	 * 
	 * @return the current world weather conditions
	 */
	public String checkConditions() {
		try {
			if (SATools.world != null) {
				if (SATools.world.hasStorm()) {
					if (SATools.world.isThundering()) {
						return "Thunder";
					}
					return "Storm";
				} else {
					return "Clear";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Cannot Compute";
	}

	/**
	 * doTime
	 * 
	 * forces server time
	 * 
	 * @param time
	 *            The time to set on the server.
	 */
	void doTime(int time) {
		if (plugin.getServer().dispatchCommand(
				new ConsoleCommandSender(plugin.getServer()),
				"time set " + time)) {
			log.info("Time set to " + time);
		} else {
			log.warning("Failed to set time");
		}
	}

	// Content Pane
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTabbedPane(), null); // Generated
		}
		return jContentPane;
	}

	// Tabs
	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(0, 0, 584, 762)); // Generated
			jTabbedPane.setEnabled(true); // Generated
			jTabbedPane.setName(""); // Generated
			jTabbedPane.addTab("Server Main", null, getJPanel_MAIN(),
					"Main Server Options"); // Generated
			jTabbedPane.addTab("Players", null, getJPanel_PLAYERS(),
					"Players and Player Options"); // Generated
			jTabbedPane.addTab("Schedule Tasks", null, getJPanel_SCHEDULE(),
					"Schedule tasks here!"); // Generated
			jTabbedPane.addTab("Map", null, getJPanel_MAP(), null); // Generated
			jTabbedPane.addTab("Anti-Greif", null, getJPanel_GREIF(), null); // Generated
		}
		return jTabbedPane;
	}

	// JPanels - in order of appearance
	/**
	 * This method initializes jPanel_MAIN
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_MAIN() {
		if (jPanel_MAIN == null) {
			jPanel_MAIN = new JPanel();
			jPanel_MAIN.setLayout(null); // Generated
			jPanel_MAIN.add(getJPanel_MAIN_TIME(), null); // Generated
			jPanel_MAIN.add(getJPanel_MAIN_CONSOLE(), null); // Generated
			jPanel_MAIN.add(getJPanel_MAIN_WEATHER(), null);
			jPanel_MAIN.add(getJPanel_MAIN_SPAWN(), null);
			jPanel_MAIN.add(getJButton_MAIN_RESTART(), null); // Generated
		}
		return jPanel_MAIN;
	}

	/**
	 * This method initializes jPanel_MAIN_CONSOLE
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_MAIN_CONSOLE() {
		if (jPanel_MAIN_CONSOLE == null) {
			jLabel_MAIN_CONSOLE_COMMAND = new JLabel();
			jLabel_MAIN_CONSOLE_COMMAND.setBounds(new Rectangle(15, 165, 525,
					16)); // Generated
			jLabel_MAIN_CONSOLE_COMMAND.setText("Type a command here."); // Generated
			jLabel_MAIN_CONSOLE_MESSAGE = new JLabel();
			jLabel_MAIN_CONSOLE_MESSAGE
					.setBounds(new Rectangle(15, 80, 524, 16)); // Generated
			jLabel_MAIN_CONSOLE_MESSAGE.setText("Send a message to a player."); // Generated
			jLabel_MAIN_CONSOLE_SAY = new JLabel();
			jLabel_MAIN_CONSOLE_SAY.setBounds(new Rectangle(15, 30, 525, 16)); // Generated
			jLabel_MAIN_CONSOLE_SAY
					.setText("Type Something here to make an announcement."); // Generated
			jPanel_MAIN_CONSOLE = new JPanel();
			jPanel_MAIN_CONSOLE.setLayout(null); // Generated
			jPanel_MAIN_CONSOLE.setBounds(new Rectangle(15, 15, 550, 220)); // Generated
			jPanel_MAIN_CONSOLE.setBorder(BorderFactory.createTitledBorder(
					null, "Console", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51))); // Generated
			jPanel_MAIN_CONSOLE.add(jLabel_MAIN_CONSOLE_SAY, null); // Generated
			jPanel_MAIN_CONSOLE.add(getJTextField_MAIN_CONSOLE_SAY(), null); // Generated
			jPanel_MAIN_CONSOLE.add(getJButton_MAIN_CONSOLE_SAY(), null); // Generated
			jPanel_MAIN_CONSOLE.add(jLabel_MAIN_CONSOLE_MESSAGE, null); // Generated
			jPanel_MAIN_CONSOLE.add(getJComboBox_MAIN_CONSOLE_MESSAGE(), null); // Generated
			jPanel_MAIN_CONSOLE.add(getJTextField_MAIN_CONSOLE_MESSAGE(), null); // Generated
			jPanel_MAIN_CONSOLE.add(getJButton_MAIN_CONSOLE_MESSAGE(), null); // Generated
			jPanel_MAIN_CONSOLE.add(jLabel_MAIN_CONSOLE_COMMAND, null); // Generated
			jPanel_MAIN_CONSOLE.add(getJTextField_MAIN_CONSOLE_COMMAND(), null); // Generated
			jPanel_MAIN_CONSOLE.add(getJButton_MAIN_CONSOLE_COMMAND(), null); // Generated
			jPanel_MAIN_CONSOLE
					.add(getJComboBox_MAIN_CONSOLE_SAY_COLOR(), null); // Generated
			jPanel_MAIN_CONSOLE.add(getJComboBox_MAIN_CONSOLE_MESSAGE_COLOR(),
					null); // Generated
		}
		return jPanel_MAIN_CONSOLE;
	}

	/**
	 * This method initializes jPanel_MAIN_TIME
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_MAIN_TIME() {
		if (jPanel_MAIN_TIME == null) {
			jLabel_MAIN_TIME_SET = new JLabel();
			jLabel_MAIN_TIME_SET.setBounds(new Rectangle(15, 60, 56, 20)); // Generated
			jLabel_MAIN_TIME_SET.setText("Set Time"); // Generated
			jLabel_MAIN_TIME_DATA = new JLabel();
			jLabel_MAIN_TIME_DATA.setBounds(new Rectangle(110, 30, 85, 16)); // Generated
			jLabel_MAIN_TIME_DATA.setText("NULL"); // Generated
			jLabel_MAIN_TIME = new JLabel();
			jLabel_MAIN_TIME.setBounds(new Rectangle(15, 30, 85, 16)); // Generated
			jLabel_MAIN_TIME.setText("Current Time:"); // Generated
			jPanel_MAIN_TIME = new JPanel();
			jPanel_MAIN_TIME.setLayout(null); // Generated
			jPanel_MAIN_TIME.setBounds(new Rectangle(15, 245, 550, 100)); // Generated
			jPanel_MAIN_TIME.setBorder(BorderFactory.createTitledBorder(null,
					"Time Options", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51))); // Generated
			jPanel_MAIN_TIME.add(jLabel_MAIN_TIME, null); // Generated
			jPanel_MAIN_TIME.add(jLabel_MAIN_TIME_DATA, null); // Generated
			jPanel_MAIN_TIME.add(jLabel_MAIN_TIME_SET, null); // Generated
			jPanel_MAIN_TIME.add(getJButton_MAIN_TIME_SET_MIDNIGHT(), null); // Generated
			jPanel_MAIN_TIME.add(getJButton_MAIN_TIME_SET_MORNING(), null); // Generated
			jPanel_MAIN_TIME.add(getJButton_MAIN_TIME_SET_NOON(), null); // Generated
			jPanel_MAIN_TIME.add(getJButton_MAIN_TIME_SET_DUSK(), null); // Generated
			jPanel_MAIN_TIME.add(getJTextField_MAIN_TIME_SET_INT(), null); // Generated
		}
		return jPanel_MAIN_TIME;
	}

	/**
	 * This method initializes jPanel_MAIN_TIME
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_MAIN_WEATHER() {
		if (jPanel_MAIN_WEATHER == null) {
			jLabel_MAIN_WEATHER_DATA = new JLabel();
			jLabel_MAIN_WEATHER_DATA.setBounds(new Rectangle(130, 30, 85, 16)); // Generated
			jLabel_MAIN_WEATHER_DATA.setText("NULL"); // Generated
			jLabel_MAIN_WEATHER = new JLabel();
			jLabel_MAIN_WEATHER.setBounds(new Rectangle(15, 30, 105, 16)); // Generated
			jLabel_MAIN_WEATHER.setText("Current Weather:"); // Generated
			jPanel_MAIN_WEATHER = new JPanel();
			jPanel_MAIN_WEATHER.setLayout(null); // Generated
			jPanel_MAIN_WEATHER.setBounds(new Rectangle(15, 360, 550, 100)); // Generated
			jPanel_MAIN_WEATHER.setBorder(BorderFactory.createTitledBorder(
					null, "Weather", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51))); // Generated
			jPanel_MAIN_WEATHER.add(jLabel_MAIN_WEATHER, null); // Generated
			jPanel_MAIN_WEATHER.add(jLabel_MAIN_WEATHER_DATA, null); // Generated
			jPanel_MAIN_WEATHER.add(getJButton_MAIN_WEATHER_CLEAR());
			jPanel_MAIN_WEATHER.add(getJButton_MAIN_WEATHER_STORM());
			jPanel_MAIN_WEATHER.add(getJButton_MAIN_WEATHER_THUNDER());
		}
		return jPanel_MAIN_WEATHER;
	}

	private JPanel getJPanel_MAIN_SPAWN() {
		if (jPanel_MAIN_SPAWN == null) {
			jLabel_MAIN_SPAWN_WARNING = new JLabel();
			jLabel_MAIN_SPAWN_WARNING
					.setBounds(new Rectangle(10, 180, 529, 16)); // Generated
			jLabel_MAIN_SPAWN_WARNING
					.setText("In orrder to use a custom location please enter the location in x,y,z format with no spaces"); // Generated
			jLabel_MAIN_SPAWN_WHERE_OBJECT = new JLabel();
			jLabel_MAIN_SPAWN_WHERE_OBJECT.setBounds(new Rectangle(255, 110,
					11, 20)); // Generated
			jLabel_MAIN_SPAWN_WHERE_OBJECT.setText("to"); // Generated
			jLabel_MAIN_SPAWN_OBJECT = new JLabel("Spawn:");
			jLabel_MAIN_SPAWN_OBJECT.setBounds(new Rectangle(10, 110, 75, 20)); // Generated
			jLabel_MAIN_SPAWN_OBJECT.setText("Spawn:"); // Generated
			jLabel_MAIN_SPAWN_WHERE = new JLabel();
			jLabel_MAIN_SPAWN_WHERE.setBounds(new Rectangle(255, 30, 20, 20)); // Generated
			jLabel_MAIN_SPAWN_WHERE.setText("to"); // Generated
			jPanel_MAIN_SPAWN = new JPanel();
			jPanel_MAIN_SPAWN.setLayout(null); // Generated
			jPanel_MAIN_SPAWN.setBounds(new Rectangle(15, 470, 550, 205)); // Generated
			jPanel_MAIN_SPAWN.setBorder(BorderFactory.createTitledBorder(null,
					"Spawning", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51)));
			jLabel_MAIN_SPAWN = new JLabel("Spawn:");
			jLabel_MAIN_SPAWN.setBounds(new Rectangle(10, 30, 75, 20));
			jPanel_MAIN_SPAWN.add(jLabel_MAIN_SPAWN);

			jPanel_MAIN_SPAWN.add(getJComboBox_MAIN_SPAWN(), null); // Generated
			jPanel_MAIN_SPAWN.add(jLabel_MAIN_SPAWN_WHERE, null); // Generated
			jPanel_MAIN_SPAWN.add(getJComboBox_MAIN_SPAWN_LOCATION(), null); // Generated
			jPanel_MAIN_SPAWN.add(getJButton_MAIN_SPAWN(), null); // Generated
			jPanel_MAIN_SPAWN.add(getJSeparator_MAIN_SPAWN(), null); // Generated
			jPanel_MAIN_SPAWN.add(jLabel_MAIN_SPAWN_OBJECT, null); // Generated
			jPanel_MAIN_SPAWN.add(getJComboBox_MAIN_SPAWN_OBJECT(), null); // Generated
			jPanel_MAIN_SPAWN.add(jLabel_MAIN_SPAWN_WHERE_OBJECT, null); // Generated
			jPanel_MAIN_SPAWN.add(getJComboBox_MAIN_SPAWN_LOCATION_OBJECT(),
					null); // Generated
			jPanel_MAIN_SPAWN.add(getJButton_MAIN_SPAWN_OBJECT(), null); // Generated
			jPanel_MAIN_SPAWN.add(jLabel_MAIN_SPAWN_WARNING, null); // Generated
		}
		return jPanel_MAIN_SPAWN;
	}

	/**
	 * This method initializes jSeparator_MAIN_SPAWN
	 * 
	 * @return javax.swing.JSeparator
	 */
	private JSeparator getJSeparator_MAIN_SPAWN() {
		if (jSeparator_MAIN_SPAWN == null) {
			jSeparator_MAIN_SPAWN = new JSeparator();
			jSeparator_MAIN_SPAWN.setBounds(new Rectangle(10, 95, 530, 10)); // Generated
		}
		return jSeparator_MAIN_SPAWN;
	}

	/**
	 * This method initializes defaultComboBoxModel_MAIN_SPAWN_OBJECT
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_MAIN_SPAWN_OBJECT() {
		if (defaultComboBoxModel_MAIN_SPAWN_OBJECT == null) {
			defaultComboBoxModel_MAIN_SPAWN_OBJECT = new DefaultComboBoxModel();
			for (int i = 0; i < objects.length; i++) {
				defaultComboBoxModel_MAIN_SPAWN_OBJECT.addElement(objects[i]);
			}
		}
		return defaultComboBoxModel_MAIN_SPAWN_OBJECT;
	}

	/**
	 * This method initializes jComboBox_MAIN_SPAWN_OBJECT
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_SPAWN_OBJECT() {
		if (jComboBox_MAIN_SPAWN_OBJECT == null) {
			jComboBox_MAIN_SPAWN_OBJECT = new JComboBox(
					getDefaultComboBoxModel_MAIN_SPAWN_OBJECT());
			jComboBox_MAIN_SPAWN_OBJECT.setBounds(new Rectangle(90, 110, 150,
					20)); // Generated
		}
		return jComboBox_MAIN_SPAWN_OBJECT;
	}

	/**
	 * This method initializes defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT() {
		if (defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT == null) {
			defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT = new DefaultComboBoxModel();
		}
		return defaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT;
	}

	/**
	 * This method initializes jComboBox_MAIN_SPAWN_LOCATION_OBJECT
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_SPAWN_LOCATION_OBJECT() {
		if (jComboBox_MAIN_SPAWN_LOCATION_OBJECT == null) {
			jComboBox_MAIN_SPAWN_LOCATION_OBJECT = new JComboBox(
					getDefaultComboBoxModel_MAIN_SPAWN_LOCATION_OBJECT());
			jComboBox_MAIN_SPAWN_LOCATION_OBJECT.setBounds(new Rectangle(285,
					110, 255, 20)); // Generated
			jComboBox_MAIN_SPAWN_LOCATION_OBJECT.setEditable(true); // Generated
		}
		return jComboBox_MAIN_SPAWN_LOCATION_OBJECT;
	}

	/**
	 * This method initializes jButton_MAIN_SPAWN_OBJECT
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_SPAWN_OBJECT() {
		if (jButton_MAIN_SPAWN_OBJECT == null) {
			jButton_MAIN_SPAWN_OBJECT = new JButton();
			jButton_MAIN_SPAWN_OBJECT
					.setBounds(new Rectangle(10, 140, 530, 30)); // Generated
			jButton_MAIN_SPAWN_OBJECT.setText("Spawn"); // Generated
			jButton_MAIN_SPAWN_OBJECT
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							Location location = null;
							String object = null;
							if (jComboBox_MAIN_SPAWN_OBJECT.getSelectedIndex() != -1) {
								object = jComboBox_MAIN_SPAWN_OBJECT
										.getSelectedItem().toString()
										.toLowerCase();
							}
							if (jComboBox_MAIN_SPAWN_LOCATION_OBJECT
									.getSelectedItem() != null) {
								if (jComboBox_MAIN_SPAWN_LOCATION_OBJECT
										.getSelectedItem() instanceof Player) {
									Player p = (Player) jComboBox_MAIN_SPAWN_LOCATION_OBJECT
											.getSelectedItem();
									Location loc = p.getTargetBlock(null, 30)
											.getLocation();
									location = new Location(SATools.world, loc
											.getBlockX(), loc.getBlockY() + 1,
											loc.getBlockZ());
								} else {
									String text = jComboBox_MAIN_SPAWN_LOCATION_OBJECT
											.getSelectedItem().toString();
									if (text.trim().contains(",")) {
										String[] token = text.split(",");
										if (numbers.isNumeric(token[0])
												&& numbers.isNumeric(token[1])
												&& numbers.isNumeric(token[2])) {
											int x = Integer.parseInt(token[0]
													.trim());
											int y = Integer.parseInt(token[1]
													.trim());
											int z = Integer.parseInt(token[2]
													.trim());
											location = new Location(
													SATools.world, x, y, z);
										} else {
											log.severe("I don't know what to say, you messed up in defining a location bro.");
										}
									}
								}
							} else {
								log.severe("Failed to spawn creature");
							}
							if (location != null && object != null)
								SATools.spawnObject(location, object);
						}
					});
		}
		return jButton_MAIN_SPAWN_OBJECT;
	}

	/**
	 * This method initializes jPanel_PLAYERS
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_PLAYERS() {
		if (jPanel_PLAYERS == null) {
			jPanel_PLAYERS = new JPanel();
			jPanel_PLAYERS.setLayout(null); // Generated
			jPanel_PLAYERS.add(getJScrollPane_PLAYERS_PLAYERS(), null); // Generated
			jPanel_PLAYERS.add(getJPanel_PLAYERS_PLAYER(), null); // Generated
			jPanel_PLAYERS.add(getJPanel_PLAYERS_MODIFY(), null); // Generated
		}
		return jPanel_PLAYERS;
	}

	/**
	 * This method initializes jPanel_PLAYERS_PLAYER
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_PLAYERS_PLAYER() {
		if (jPanel_PLAYERS_PLAYER == null) {
			jLabel_PLAYERS_PLAYER_SNEAK_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_SNEAK_DATA.setBounds(new Rectangle(110, 195,
					140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_SNEAK_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_SNEAK = new JLabel();
			jLabel_PLAYERS_PLAYER_SNEAK
					.setBounds(new Rectangle(10, 195, 95, 20)); // Generated
			jLabel_PLAYERS_PLAYER_SNEAK.setText("Sleeping: "); // Generated
			jLabel_PLAYERS_PLAYER_SLEEP_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_SLEEP_DATA.setBounds(new Rectangle(110, 170,
					140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_SLEEP_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_SLEEP = new JLabel();
			jLabel_PLAYERS_PLAYER_SLEEP
					.setBounds(new Rectangle(10, 170, 95, 20)); // Generated
			jLabel_PLAYERS_PLAYER_SLEEP.setText("Sleeping: "); // Generated
			jLabel_PLAYERS_PLAYER_OP_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_OP_DATA.setBounds(new Rectangle(110, 145,
					140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_OP_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_OP = new JLabel();
			jLabel_PLAYERS_PLAYER_OP.setBounds(new Rectangle(10, 145, 95, 20)); // Generated
			jLabel_PLAYERS_PLAYER_OP.setText("OP Status: "); // Generated
			jLabel_PLAYERS_PLAYER_DEAD_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_DEAD_DATA.setBounds(new Rectangle(110, 120,
					140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_DEAD_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_DEAD = new JLabel();
			jLabel_PLAYERS_PLAYER_DEAD
					.setBounds(new Rectangle(10, 120, 95, 20)); // Generated
			jLabel_PLAYERS_PLAYER_DEAD.setText("Dead: "); // Generated
			jLabel_PLAYERS_PLAYER_ITEM_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_ITEM_DATA.setBounds(new Rectangle(110, 95,
					140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_ITEM_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_ITEM = new JLabel();
			jLabel_PLAYERS_PLAYER_ITEM.setBounds(new Rectangle(10, 95, 95, 20)); // Generated
			jLabel_PLAYERS_PLAYER_ITEM.setText("Current Item: "); // Generated
			jLabel_PLAYERS_PLAYER_HEALTH_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_HEALTH_DATA.setBounds(new Rectangle(110, 70,
					140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_HEALTH_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_HEALTH = new JLabel();
			jLabel_PLAYERS_PLAYER_HEALTH
					.setBounds(new Rectangle(10, 70, 95, 20)); // Generated
			jLabel_PLAYERS_PLAYER_HEALTH.setText("Health: "); // Generated
			jLabel_PLAYERS_PLAYER_ENTITYID_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_ENTITYID_DATA.setBounds(new Rectangle(110,
					45, 140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_ENTITYID_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_ENTITYID = new JLabel();
			jLabel_PLAYERS_PLAYER_ENTITYID.setBounds(new Rectangle(10, 45, 95,
					20)); // Generated
			jLabel_PLAYERS_PLAYER_ENTITYID.setText("Entity ID: "); // Generated
			jLabel_PLAYERS_PLAYER_LOCATION_DATA = new JLabel();
			jLabel_PLAYERS_PLAYER_LOCATION_DATA.setBounds(new Rectangle(110,
					20, 140, 20)); // Generated
			jLabel_PLAYERS_PLAYER_LOCATION_DATA.setText("Null"); // Generated
			jLabel_PLAYERS_PLAYER_LOCATION = new JLabel();
			jLabel_PLAYERS_PLAYER_LOCATION.setText("Player Location: "); // Generated
			jLabel_PLAYERS_PLAYER_LOCATION.setBounds(new Rectangle(10, 20, 95,
					20)); // Generated
			jPanel_PLAYERS_PLAYER = new JPanel();
			jPanel_PLAYERS_PLAYER.setLayout(null); // Generated
			jPanel_PLAYERS_PLAYER.setBounds(new Rectangle(300, 15, 260, 230)); // Generated
			jPanel_PLAYERS_PLAYER.setBorder(BorderFactory.createTitledBorder(
					null, "Player Details", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, null, null)); // Generated
			jPanel_PLAYERS_PLAYER.setVisible(false); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_LOCATION, null); // Generated
			jPanel_PLAYERS_PLAYER
					.add(jLabel_PLAYERS_PLAYER_LOCATION_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_ENTITYID, null); // Generated
			jPanel_PLAYERS_PLAYER
					.add(jLabel_PLAYERS_PLAYER_ENTITYID_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_HEALTH, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_HEALTH_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_ITEM, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_ITEM_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_DEAD, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_DEAD_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_OP, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_OP_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_SLEEP, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_SLEEP_DATA, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_SNEAK, null); // Generated
			jPanel_PLAYERS_PLAYER.add(jLabel_PLAYERS_PLAYER_SNEAK_DATA, null); // Generated
		}
		return jPanel_PLAYERS_PLAYER;
	}

	/**
	 * This method initializes jPanel_PLAYERS_MODIFY
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_PLAYERS_MODIFY() {
		if (jPanel_PLAYERS_MODIFY == null) {
			jLabel_PLAYERS_MODIFY_GIVE = new JLabel();
			jLabel_PLAYERS_MODIFY_GIVE.setBounds(new Rectangle(15, 45, 40, 20)); // Generated
			jLabel_PLAYERS_MODIFY_GIVE.setText("Give: "); // Generated
			jLabel_PLAYERS_MODIFY_HEALTH = new JLabel();
			jLabel_PLAYERS_MODIFY_HEALTH
					.setBounds(new Rectangle(15, 75, 85, 20));
			jLabel_PLAYERS_MODIFY_HEALTH.setText("Set Health: ");
			jPanel_PLAYERS_MODIFY = new JPanel();
			jPanel_PLAYERS_MODIFY.setLayout(null); // Generated
			jPanel_PLAYERS_MODIFY.setBounds(new Rectangle(15, 255, 545, 470)); // Generated
			jPanel_PLAYERS_MODIFY.setBorder(BorderFactory.createTitledBorder(
					null, "Admin Controlls",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Dialog",
							Font.BOLD, 12), new Color(51, 51, 51))); // Generated
			jPanel_PLAYERS_MODIFY.setVisible(false); // Generated
			jPanel_PLAYERS_MODIFY.add(jLabel_PLAYERS_MODIFY_GIVE, null); // Generated
			jPanel_PLAYERS_MODIFY.add(jLabel_PLAYERS_MODIFY_HEALTH, null);
			jPanel_PLAYERS_MODIFY.add(getJComboBox_PLAYERS_MODIFY_GIVE(), null); // Generated
			jPanel_PLAYERS_MODIFY
					.add(getJButton_PLAYERS_MODIFY_GIVE_64(), null); // Generated
			jPanel_PLAYERS_MODIFY.add(getJButton_PLAYERS_MODIFY_GIVE_1(), null); // Generated
			jPanel_PLAYERS_MODIFY.add(getJTextField_PLAYERS_MODIFY_GIVE_INT(),
					null); // Generated
			jPanel_PLAYERS_MODIFY.add(getJButton_PLAYERS_MODIFY_HEALTH_FULL(),
					null);
			jPanel_PLAYERS_MODIFY.add(getJButton_PLAYERS_MODIFY_HEALTH_KILL(),
					null);
			jPanel_PLAYERS_MODIFY.add(
					getJToggleButton_PLAYERS_MODIFY_HEALTH_JESUS(), null);
			jPanel_PLAYERS_MODIFY.add(
					getJTextField_PLAYERS_MODIFY_HEALTH_INT(), null);
		}
		return jPanel_PLAYERS_MODIFY;
	}

	// Init component methods.

	/**
	 * This method initializes jScrollPane_PLAYERS_PLAYERS
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane_PLAYERS_PLAYERS() {
		if (jScrollPane_PLAYERS_PLAYERS == null) {
			jScrollPane_PLAYERS_PLAYERS = new JScrollPane();
			jScrollPane_PLAYERS_PLAYERS.setBounds(new Rectangle(15, 15, 260,
					230)); // Generated
			jScrollPane_PLAYERS_PLAYERS
					.setViewportView(getJList_PLAYERS_PLAYERS()); // Generated
		}
		return jScrollPane_PLAYERS_PLAYERS;
	}

	/**
	 * This method initializes jList_PLAYERS_PLAYERS
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJList_PLAYERS_PLAYERS() {
		if (jList_PLAYERS_PLAYERS == null) {
			jList_PLAYERS_PLAYERS = new JList(
					getDefaultListModel_PLAYERS_PLAYERS());
			jList_PLAYERS_PLAYERS
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(
								javax.swing.event.ListSelectionEvent e) {
							try {
								if (jList_PLAYERS_PLAYERS.getSelectedIndex() != -1) {
									player = plugin.getServer().getPlayer(
											jList_PLAYERS_PLAYERS
													.getSelectedValue()
													.toString());
									if (SATools.gods.contains(player)) {
										jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS
												.setSelected(true);
									} else {
										jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS
												.setSelected(false);
									}
									if (!jPanel_PLAYERS_PLAYER.isVisible()) {
										jPanel_PLAYERS_PLAYER.setVisible(true);
									}
									if (!jPanel_PLAYERS_MODIFY.isVisible()) {
										jPanel_PLAYERS_MODIFY.setVisible(true);
									}
									if (!pi.isAlive()) {
										pi.start();
										piAlive = true;
									}
								} else {
									if (jPanel_PLAYERS_PLAYER.isVisible()) {
										jPanel_PLAYERS_PLAYER.setVisible(false);
									}
									if (jPanel_PLAYERS_MODIFY.isVisible()) {
										jPanel_PLAYERS_MODIFY.setVisible(false);
									}
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jList_PLAYERS_PLAYERS;
	}

	/**
	 * This method initializes jDefaultListModel
	 * 
	 * @return javax.swing.DefaultListModel
	 */
	private ListModel getDefaultListModel_PLAYERS_PLAYERS() {
		if (DefaultListModel_PLAYERS_PLAYERS == null) {
			DefaultListModel_PLAYERS_PLAYERS = new DefaultListModel();
		}
		return DefaultListModel_PLAYERS_PLAYERS;
	}

	private JTextField getJTextField_PLAYERS_MODIFY_HEALTH_INT() {
		if (jTextField_PLAYERS_MODIFY_HEALTH_INT == null) {
			jTextField_PLAYERS_MODIFY_HEALTH_INT = new JTextField();
			jTextField_PLAYERS_MODIFY_HEALTH_INT.setBounds(new Rectangle(410,
					75, 50, 20));
			jTextField_PLAYERS_MODIFY_HEALTH_INT
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyPressed(java.awt.event.KeyEvent e) {
							if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
								if (!jTextField_PLAYERS_MODIFY_HEALTH_INT
										.getText().isEmpty()
										&& numbers
												.isNumeric(jTextField_PLAYERS_MODIFY_HEALTH_INT
														.getText())) {
									player.setHealth(Integer
											.parseInt(jTextField_PLAYERS_MODIFY_HEALTH_INT
													.getText()));
									jTextField_PLAYERS_MODIFY_HEALTH_INT
											.setText("");
								}
							}
						}
					});
		}
		return jTextField_PLAYERS_MODIFY_HEALTH_INT;
	}

	private JToggleButton getJToggleButton_PLAYERS_MODIFY_HEALTH_JESUS() {
		if (jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS == null) {
			jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS = new JToggleButton(
					"Jesus", false);
			jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS.setBounds(new Rectangle(
					320, 75, 80, 20));
			jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS
									.isSelected()) {
								player.setHealth(20);
								if (!SATools.gods.contains(player)) {
									SATools.gods.add(player);
									plugin.getServer().broadcastMessage(
											ChatColor.GOLD
													+ player.getDisplayName()
													+ " is now a god");
								}
							} else {
								if (SATools.gods.contains(player)) {
									SATools.gods.remove(player);
									SATools.godsRemoved.remove(player
											.getDisplayName());
									plugin.getServer()
											.broadcastMessage(
													ChatColor.DARK_RED
															+ player.getDisplayName()
															+ " has been stripped of his god powers");
								}
								player.damage(10);
							}
						}
					});
		}
		return jToggleButton_PLAYERS_MODIFY_HEALTH_JESUS;
	}

	private JButton getJButton_PLAYERS_MODIFY_HEALTH_KILL() {
		if (jButton_PLAYERS_MODIFY_HEALTH_KILL == null) {
			jButton_PLAYERS_MODIFY_HEALTH_KILL = new JButton("Kill");
			jButton_PLAYERS_MODIFY_HEALTH_KILL.setBounds(new Rectangle(240, 75,
					70, 20));
			jButton_PLAYERS_MODIFY_HEALTH_KILL
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e) {
							player.damage(20);
						}
					});
		}
		return jButton_PLAYERS_MODIFY_HEALTH_KILL;
	}

	private JButton getJButton_PLAYERS_MODIFY_HEALTH_FULL() {
		if (jButton_PLAYERS_MODIFY_HEALTH_FULL == null) {
			jButton_PLAYERS_MODIFY_HEALTH_FULL = new JButton("Full Health");
			jButton_PLAYERS_MODIFY_HEALTH_FULL.setBounds(new Rectangle(110, 75,
					120, 20));
			jButton_PLAYERS_MODIFY_HEALTH_FULL
					.addActionListener(new ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							player.setHealth(20);
						}
					});
		}
		return jButton_PLAYERS_MODIFY_HEALTH_FULL;
	}

	/**
	 * This method initializes jComboBox_PLAYERS_MODIFY_GIVE
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_PLAYERS_MODIFY_GIVE() {
		if (jComboBox_PLAYERS_MODIFY_GIVE == null) {
			jComboBox_PLAYERS_MODIFY_GIVE = new JComboBox(
					DefaultComboBoxModel_PLAYERS_MODIFY_GIVE);
			jComboBox_PLAYERS_MODIFY_GIVE.setBounds(new Rectangle(60, 45, 200,
					20)); // Generated
			jComboBox_PLAYERS_MODIFY_GIVE
					.addItemListener(new java.awt.event.ItemListener() {
						public void itemStateChanged(java.awt.event.ItemEvent e) {
							try {
								if (jComboBox_PLAYERS_MODIFY_GIVE
										.getSelectedItem() != null) {
									selected_id = items
											.get(jComboBox_PLAYERS_MODIFY_GIVE
													.getSelectedItem());
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jComboBox_PLAYERS_MODIFY_GIVE;
	}

	/**
	 * This method initializes jButton_PLAYERS_MODIFY_GIVE_64
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_PLAYERS_MODIFY_GIVE_64() {
		if (jButton_PLAYERS_MODIFY_GIVE_64 == null) {
			jButton_PLAYERS_MODIFY_GIVE_64 = new JButton();
			jButton_PLAYERS_MODIFY_GIVE_64.setBounds(new Rectangle(275, 45, 60,
					20)); // Generated
			jButton_PLAYERS_MODIFY_GIVE_64.setText("64"); // Generated
			jButton_PLAYERS_MODIFY_GIVE_64
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (doGiveItem(player, selected_id, 64))
									;
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jButton_PLAYERS_MODIFY_GIVE_64;
	}

	/**
	 * This method initializes jButton_PLAYERS_MODIFY_GIVE_1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_PLAYERS_MODIFY_GIVE_1() {
		if (jButton_PLAYERS_MODIFY_GIVE_1 == null) {
			jButton_PLAYERS_MODIFY_GIVE_1 = new JButton();
			jButton_PLAYERS_MODIFY_GIVE_1.setBounds(new Rectangle(350, 45, 60,
					20)); // Generated
			jButton_PLAYERS_MODIFY_GIVE_1.setText("1"); // Generated
			jButton_PLAYERS_MODIFY_GIVE_1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (doGiveItem(player, selected_id, 1))
									;
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jButton_PLAYERS_MODIFY_GIVE_1;
	}

	public static boolean doGiveItem(Player p, int itemID, int amount) {
		if (p != null) {
			if (itemID != -1) {
				if (amount > 0) {
					short damage = 0;
					if (itemID == items.get("Wool")) {
						String[] colorValues = { "White", "Orange", "Magenta",
								"Light Blue", "Yellow", "Light Green", "Pink",
								"Gray", "Light Gray", "Cyan", "Purple", "Blue",
								"Brown", "Dark Green", "Red", "Black" };
						HashMap<Integer, String> woolColors = new HashMap<Integer, String>();
						for (int i = 0; i < colorValues.length; i++) {
							woolColors.put(i, colorValues[i]);
						}
						String input = (String) JOptionPane.showInputDialog(
								null, "What color?", "Wool Color Selection",
								JOptionPane.INFORMATION_MESSAGE, null,
								woolColors.values().toArray(),
								woolColors.get(0));
						for (int o : woolColors.keySet()) {
							if (woolColors.get(o).equals(input)) {
								damage = (short) o;
								break;
							}
							if (o == colorValues.length - 1) {
								log.severe("Cannot get integer");
								return false;
							}
						}
					}
					org.bukkit.inventory.ItemStack is = new org.bukkit.inventory.ItemStack(
							selected_id, amount, damage);
					p.getInventory().addItem(is);
					{ // The heart of this method
						if (player.getInventory().contains(is)) {
							return true;
						}
						return false;
					}
				} else {
					log.severe("doGiveItem(): no amount or negative count");
					return false;
				}
			} else {
				log.severe("doGiveItem(): no item or invalid type");
				return false;
			}
		} else {
			log.severe("doGiveItem(): no player or invalid player");
			return false;
		}
	}

	/**
	 * This method initializes jTextField_PLAYERS_MODIFY_GIVE_INT
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_PLAYERS_MODIFY_GIVE_INT() {
		if (jTextField_PLAYERS_MODIFY_GIVE_INT == null) {
			jTextField_PLAYERS_MODIFY_GIVE_INT = new JTextField();
			jTextField_PLAYERS_MODIFY_GIVE_INT.setBounds(new Rectangle(425, 45,
					105, 20)); // Generated
			jTextField_PLAYERS_MODIFY_GIVE_INT
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyPressed(java.awt.event.KeyEvent e) {
							try {
								if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
									if (!jTextField_PLAYERS_MODIFY_GIVE_INT
											.getText().isEmpty()
											|| !numbers
													.isNumeric(jTextField_PLAYERS_MODIFY_GIVE_INT
															.getText())) {
										if (doGiveItem(
												player,
												selected_id,
												Integer.parseInt(jTextField_PLAYERS_MODIFY_GIVE_INT
														.getText())))
											;
										jTextField_PLAYERS_MODIFY_GIVE_INT
												.setText("");
									} else {
										log.info("Enter a number between 1-64");
									}
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jTextField_PLAYERS_MODIFY_GIVE_INT;
	}

	/**
	 * This method initializes jTextField_MAIN_CONSOLE_SAY
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_MAIN_CONSOLE_SAY() {
		if (jTextField_MAIN_CONSOLE_SAY == null) {
			jTextField_MAIN_CONSOLE_SAY = new JTextField();
			jTextField_MAIN_CONSOLE_SAY
					.setBounds(new Rectangle(15, 50, 330, 20)); // Generated
			jTextField_MAIN_CONSOLE_SAY
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyPressed(java.awt.event.KeyEvent e) {
							try {
								if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
									jButton_MAIN_CONSOLE_SAY.doClick();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jTextField_MAIN_CONSOLE_SAY;
	}

	/**
	 * This method initializes jButton_MAIN_CONSOLE_SAY
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_CONSOLE_SAY() {
		if (jButton_MAIN_CONSOLE_SAY == null) {
			jButton_MAIN_CONSOLE_SAY = new JButton();
			jButton_MAIN_CONSOLE_SAY.setBounds(new Rectangle(450, 50, 75, 20)); // Generated
			jButton_MAIN_CONSOLE_SAY.setText("Say It!"); // Generated
			jButton_MAIN_CONSOLE_SAY
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (!jTextField_MAIN_CONSOLE_SAY.getText()
										.isEmpty()) {
									plugin.getServer()
											.broadcastMessage(
													ChatColor
															.valueOf(jComboBox_MAIN_CONSOLE_SAY_COLOR
																	.getSelectedItem()
																	.toString())
															+ jTextField_MAIN_CONSOLE_SAY
																	.getText());
									jTextField_MAIN_CONSOLE_SAY.setText("");
								} else {
									log.info("Enter a valid message");
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jButton_MAIN_CONSOLE_SAY;
	}

	/**
	 * This method initializes jComboBox_MAIN_CONSOLE_MESSAGE
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_CONSOLE_MESSAGE() {
		if (jComboBox_MAIN_CONSOLE_MESSAGE == null) {
			jComboBox_MAIN_CONSOLE_MESSAGE = new JComboBox(
					getDefaultComboBoxModel_MAIN_CONSOLE_MESSAGE());
			jComboBox_MAIN_CONSOLE_MESSAGE.setBounds(new Rectangle(15, 100,
					210, 20)); // Generated
		}
		return jComboBox_MAIN_CONSOLE_MESSAGE;
	}

	/**
	 * This method initializes DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_MAIN_CONSOLE_MESSAGE() {
		if (DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE == null) {
			DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE = new DefaultComboBoxModel();
		}
		return DefaultComboBoxModel_MAIN_CONSOLE_MESSAGE;
	}

	/**
	 * This method initializes jTextField_MAIN_CONSOLE_MESSAGE
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_MAIN_CONSOLE_MESSAGE() {
		if (jTextField_MAIN_CONSOLE_MESSAGE == null) {
			jTextField_MAIN_CONSOLE_MESSAGE = new JTextField();
			jTextField_MAIN_CONSOLE_MESSAGE.setBounds(new Rectangle(15, 130,
					330, 20)); // Generated
			jTextField_MAIN_CONSOLE_MESSAGE
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyPressed(java.awt.event.KeyEvent e) {
							try {
								if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
									jButton_MAIN_CONSOLE_MESSAGE.doClick();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jTextField_MAIN_CONSOLE_MESSAGE;
	}

	/**
	 * This method initializes jButton_MAIN_CONSOLE_MESSAGE
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_CONSOLE_MESSAGE() {
		if (jButton_MAIN_CONSOLE_MESSAGE == null) {
			jButton_MAIN_CONSOLE_MESSAGE = new JButton();
			jButton_MAIN_CONSOLE_MESSAGE.setBounds(new Rectangle(450, 130, 75,
					20)); // Generated
			jButton_MAIN_CONSOLE_MESSAGE.setText("Send!"); // Generated
			jButton_MAIN_CONSOLE_MESSAGE
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (!jTextField_MAIN_CONSOLE_MESSAGE.getText()
										.isEmpty()
										&& jComboBox_MAIN_CONSOLE_MESSAGE
												.getSelectedIndex() != -1) {
									plugin.getServer()
											.getPlayer(
													jComboBox_MAIN_CONSOLE_MESSAGE
															.getSelectedItem()
															.toString())
											.sendMessage(
													ChatColor
															.valueOf(jComboBox_MAIN_CONSOLE_MESSAGE_COLOR
																	.getSelectedItem()
																	.toString())
															+ jTextField_MAIN_CONSOLE_MESSAGE
																	.getText());
									jTextField_MAIN_CONSOLE_MESSAGE.setText("");
								} else {
									log.info("No Player Selected or no message typed.");
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jButton_MAIN_CONSOLE_MESSAGE;
	}

	/**
	 * This method initializes jTextField_MAIN_CONSOLE_COMMAND
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_MAIN_CONSOLE_COMMAND() {
		if (jTextField_MAIN_CONSOLE_COMMAND == null) {
			jTextField_MAIN_CONSOLE_COMMAND = new JTextField();
			jTextField_MAIN_CONSOLE_COMMAND.setBounds(new Rectangle(15, 185,
					400, 20)); // Generated
			jTextField_MAIN_CONSOLE_COMMAND
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyPressed(java.awt.event.KeyEvent e) {
							try {
								if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
									jButton_MAIN_CONSOLE_COMMAND.doClick();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jTextField_MAIN_CONSOLE_COMMAND;
	}

	/**
	 * This method initializes jButton_MAIN_CONSOLE_COMMAND
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_CONSOLE_COMMAND() {
		if (jButton_MAIN_CONSOLE_COMMAND == null) {
			jButton_MAIN_CONSOLE_COMMAND = new JButton();
			jButton_MAIN_CONSOLE_COMMAND.setBounds(new Rectangle(450, 185, 75,
					20)); // Generated
			jButton_MAIN_CONSOLE_COMMAND.setText("Send!"); // Generated
			jButton_MAIN_CONSOLE_COMMAND
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (!jTextField_MAIN_CONSOLE_COMMAND.getText()
										.isEmpty()) {
									plugin.getServer().dispatchCommand(
											new ConsoleCommandSender(plugin
													.getServer()),
											jTextField_MAIN_CONSOLE_COMMAND
													.getText());
									jTextField_MAIN_CONSOLE_COMMAND.setText("");
								} else {
									log.info("Please enter text in the box to send a message.");
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jButton_MAIN_CONSOLE_COMMAND;
	}

	/**
	 * This method initializes jButton_MAIN_TIME_SET_MIDNIGHT
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_TIME_SET_MIDNIGHT() {
		if (jButton_MAIN_TIME_SET_MIDNIGHT == null) {
			jButton_MAIN_TIME_SET_MIDNIGHT = new JButton();
			jButton_MAIN_TIME_SET_MIDNIGHT.setBounds(new Rectangle(90, 60, 90,
					20)); // Generated
			jButton_MAIN_TIME_SET_MIDNIGHT.setText("Midnight"); // Generated
			jButton_MAIN_TIME_SET_MIDNIGHT
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								doTime(18000);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jButton_MAIN_TIME_SET_MIDNIGHT;
	}

	/**
	 * This method initializes jButton_MAIN_TIME_SET_MORNING
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_TIME_SET_MORNING() {
		if (jButton_MAIN_TIME_SET_MORNING == null) {
			jButton_MAIN_TIME_SET_MORNING = new JButton();
			jButton_MAIN_TIME_SET_MORNING.setBounds(new Rectangle(195, 60, 90,
					20)); // Generated
			jButton_MAIN_TIME_SET_MORNING.setText("Morning"); // Generated
			jButton_MAIN_TIME_SET_MORNING
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							doTime(0);
						}
					});
		}
		return jButton_MAIN_TIME_SET_MORNING;
	}

	/**
	 * This method initializes jButton_MAIN_TIME_SET_NOON
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_TIME_SET_NOON() {
		if (jButton_MAIN_TIME_SET_NOON == null) {
			jButton_MAIN_TIME_SET_NOON = new JButton();
			jButton_MAIN_TIME_SET_NOON
					.setBounds(new Rectangle(300, 60, 65, 20)); // Generated
			jButton_MAIN_TIME_SET_NOON.setText("Noon"); // Generated
			jButton_MAIN_TIME_SET_NOON
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							doTime(6000);
						}
					});
		}
		return jButton_MAIN_TIME_SET_NOON;
	}

	/**
	 * This method initializes jButton_MAIN_TIME_SET_DUSK
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_TIME_SET_DUSK() {
		if (jButton_MAIN_TIME_SET_DUSK == null) {
			jButton_MAIN_TIME_SET_DUSK = new JButton();
			jButton_MAIN_TIME_SET_DUSK
					.setBounds(new Rectangle(380, 60, 65, 20)); // Generated
			jButton_MAIN_TIME_SET_DUSK.setText("Dusk"); // Generated
			jButton_MAIN_TIME_SET_DUSK
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							doTime(12000);
						}
					});
		}
		return jButton_MAIN_TIME_SET_DUSK;
	}

	/**
	 * This method initializes jTextField_MAIN_TIME_SET_INT
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_MAIN_TIME_SET_INT() {
		if (jTextField_MAIN_TIME_SET_INT == null) {
			jTextField_MAIN_TIME_SET_INT = new JTextField();
			jTextField_MAIN_TIME_SET_INT.setBounds(new Rectangle(460, 60, 75,
					20)); // Generated
			jTextField_MAIN_TIME_SET_INT
					.addKeyListener(new java.awt.event.KeyAdapter() {
						public void keyPressed(java.awt.event.KeyEvent e) {
							try {
								if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
									if (!jTextField_MAIN_TIME_SET_INT.getText()
											.isEmpty()) {
										doTime(Integer
												.parseInt(jTextField_MAIN_TIME_SET_INT
														.getText()));
										jTextField_MAIN_TIME_SET_INT
												.setText("");
									} else {
										log.info("No time specified.  Please enter a number between 0 and 24000");
									}
								}
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
							}
						}
					});
		}
		return jTextField_MAIN_TIME_SET_INT;
	}

	private JButton getJButton_MAIN_WEATHER_CLEAR() {
		if (jButton_MAIN_WEATHER_CLEAR == null) {
			jButton_MAIN_WEATHER_CLEAR = new JButton();
			jButton_MAIN_WEATHER_CLEAR.setBounds(new Rectangle(20, 60, 90, 20)); // Generated
			jButton_MAIN_WEATHER_CLEAR.setText("Clear"); // Generated
			jButton_MAIN_WEATHER_CLEAR
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							plugin.setWeather(Weather.CLEAR);
						}
					});
		}
		return jButton_MAIN_WEATHER_CLEAR;
	}

	private JButton getJButton_MAIN_WEATHER_STORM() {
		if (jButton_MAIN_WEATHER_STORM == null) {
			jButton_MAIN_WEATHER_STORM = new JButton();
			jButton_MAIN_WEATHER_STORM
					.setBounds(new Rectangle(120, 60, 90, 20)); // Generated
			jButton_MAIN_WEATHER_STORM.setText("Storm"); // Generated
			jButton_MAIN_WEATHER_STORM
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							plugin.setWeather(Weather.STORM);
						}
					});
		}
		return jButton_MAIN_WEATHER_STORM;
	}

	private JButton getJButton_MAIN_WEATHER_THUNDER() {
		if (jButton_MAIN_WEATHER_THUNDER == null) {
			jButton_MAIN_WEATHER_THUNDER = new JButton();
			jButton_MAIN_WEATHER_THUNDER.setBounds(new Rectangle(220, 60, 90,
					20)); // Generated
			jButton_MAIN_WEATHER_THUNDER.setText("Thunder"); // Generated
			jButton_MAIN_WEATHER_THUNDER
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							plugin.setWeather(Weather.THUNDER);
						}
					});
		}
		return jButton_MAIN_WEATHER_THUNDER;
	}

	/**
	 * This method initializes defaultComboBoxModel_MAIN_SPAWN
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_MAIN_SPAWN() {
		if (defaultComboBoxModel_MAIN_SPAWN == null) {
			defaultComboBoxModel_MAIN_SPAWN = new DefaultComboBoxModel();
			for (int i = 0; i < CreatureType.values().length; i++) {
				defaultComboBoxModel_MAIN_SPAWN.addElement(CreatureType
						.values()[i].getName());
			}
		}
		return defaultComboBoxModel_MAIN_SPAWN;
	}

	/**
	 * This method initializes jComboBox_MAIN_SPAWN_LOCATION
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_SPAWN_LOCATION() {
		if (jComboBox_MAIN_SPAWN_LOCATION == null) {
			jComboBox_MAIN_SPAWN_LOCATION = new JComboBox(
					getDefaultComboBoxModel_MAIN_SPAWN_LOCATION());
			jComboBox_MAIN_SPAWN_LOCATION.setBounds(new Rectangle(285, 30, 255,
					20)); // Generated
			jComboBox_MAIN_SPAWN_LOCATION.setEditable(true); // Generated
		}
		return jComboBox_MAIN_SPAWN_LOCATION;
	}

	/**
	 * This method initializes jButton_MAIN_SPAWN
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_SPAWN() {
		if (jButton_MAIN_SPAWN == null) {
			jButton_MAIN_SPAWN = new JButton();
			jButton_MAIN_SPAWN.setBounds(new Rectangle(10, 60, 530, 30)); // Generated
			jButton_MAIN_SPAWN.setText("Spawn"); // Generated
			jButton_MAIN_SPAWN
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							Location location = null;
							CreatureType creature = null;
							if (jComboBox_MAIN_SPAWN.getSelectedIndex() != -1) {
								creature = CreatureType
										.valueOf(jComboBox_MAIN_SPAWN
												.getSelectedItem().toString()
												.toUpperCase());
							}
							if (jComboBox_MAIN_SPAWN_LOCATION.getSelectedItem() != null) {
								if (jComboBox_MAIN_SPAWN_LOCATION
										.getSelectedItem() instanceof Player) {
									Player p = (Player) jComboBox_MAIN_SPAWN_LOCATION
											.getSelectedItem();
									location = p.getTargetBlock(null, 10)
											.getLocation();
								} else {
									String text = jComboBox_MAIN_SPAWN_LOCATION
											.getSelectedItem().toString();
									if (text.trim().contains(",")) {
										String[] token = text.split(",");
										if (numbers.isNumeric(token[0])
												&& numbers.isNumeric(token[1])
												&& numbers.isNumeric(token[2])) {
											int x = Integer.parseInt(token[0]
													.trim());
											int y = Integer.parseInt(token[1]
													.trim());
											int z = Integer.parseInt(token[2]
													.trim());
											location = new Location(
													SATools.world, x, y, z);
										} else {
											log.severe("I don't know what to say, you messed up in defining a location bro.");
										}
									}
								}
							} else {
								log.severe("Failed to spawn creature");
							}
							if (location != null && creature != null)
								SATools.spawnCreature(location, creature);
						}
					});
		}
		return jButton_MAIN_SPAWN;
	}

	/**
	 * This method initializes defaultComboBoxModel_MAIN_SPAWN_LOCATION
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_MAIN_SPAWN_LOCATION() {
		if (defaultComboBoxModel_MAIN_SPAWN_LOCATION == null) {
			defaultComboBoxModel_MAIN_SPAWN_LOCATION = new DefaultComboBoxModel();
		}
		return defaultComboBoxModel_MAIN_SPAWN_LOCATION;
	}

	/**
	 * This method initializes jComboBox_MAIN_SPAWN1
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_SPAWN() {
		if (jComboBox_MAIN_SPAWN == null) {
			jComboBox_MAIN_SPAWN = new JComboBox(
					getDefaultComboBoxModel_MAIN_SPAWN());
			jComboBox_MAIN_SPAWN.setBounds(new Rectangle(90, 30, 150, 20)); // Generated
		}
		return jComboBox_MAIN_SPAWN;
	}

	// Threads and other classes

	public class TimeTrack extends Thread {
		public void run() {
			try {
				while (ttAlive) {
					jLabel_MAIN_TIME_DATA.setText(Long.toString(SATools.time));
					jLabel_MAIN_WEATHER_DATA.setText(checkConditions());
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				// Sleep Interrupted
			}
		}
	}

	public class GUIManager extends Thread {
		public void run() {
			while (true) {
				if (jList_SCHEDULE_TASKS.getSelectedValue() != null) {
					jLabel_SCHEDULE_TASKS_INFO_NAME_DATA
							.setText(plugin.taskscheduler.getTask(
									jList_SCHEDULE_TASKS.getSelectedValue()
											.toString()).getName());
					jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA.setText(String
							.valueOf(plugin.taskscheduler.getTask(
									jList_SCHEDULE_TASKS.getSelectedValue()
											.toString()).getExecutionTime()));
				}
			}
		}
	}

	public static class PlayerInfo extends Thread {

		public void run() {
			try {
				while (piAlive) {
					if (player != null) { // If player is selected from list
						Location player_loc = player.getLocation();
						{ // Location
							jLabel_PLAYERS_PLAYER_LOCATION_DATA.setText("( "
									+ player_loc.getBlockX() + ", "
									+ player_loc.getBlockY() + ", "
									+ player_loc.getBlockZ() + ")");
						}
						{ // Entity ID
							jLabel_PLAYERS_PLAYER_ENTITYID_DATA.setText(Integer
									.toString(player.getEntityId()));
						}
						{ // Health
							jLabel_PLAYERS_PLAYER_HEALTH_DATA.setText(Integer
									.toString(player.getHealth()));
						}
						{ // Current Item
							jLabel_PLAYERS_PLAYER_ITEM_DATA.setText(player
									.getItemInHand().toString());
						}
						{ // Dead?
							jLabel_PLAYERS_PLAYER_DEAD_DATA.setText(Boolean
									.toString(player.isDead()));
							while (plugin.getServer()
									.getPlayer(player.getDisplayName())
									.isDead()) {
								Thread.sleep(1000);
							}
							player = plugin.getServer().getPlayer(
									player.getDisplayName());
						}
						{ // isOP ?
							jLabel_PLAYERS_PLAYER_OP_DATA.setText(Boolean
									.toString(player.isOp()));
						}
						{ // Sleep
							jLabel_PLAYERS_PLAYER_SLEEP_DATA.setText(Boolean
									.toString(player.isSleeping()));
						}
						{ // Sneak
							jLabel_PLAYERS_PLAYER_SNEAK_DATA.setText(Boolean
									.toString(player.isSneaking()));
						}
					}
					Thread.sleep(1000);
				}

			} catch (Exception e) {
				// Sleep interrupted
			}
		}

	}

	/**
	 * This method initializes jPanel_SCHEDULE
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_SCHEDULE() {
		if (jPanel_SCHEDULE == null) {
			jLabel_SCHEDULE_TASKS_TIME_SET = new JLabel();
			jLabel_SCHEDULE_TASKS_TIME_SET.setBounds(new Rectangle(384, 407,
					189, 16)); // Generated
			jLabel_SCHEDULE_TASKS_TIME_SET
					.setText("This won't show in the preview."); // Generated
			jLabel_SCHEDULE_TASKS_PREVIEW = new JLabel();
			jLabel_SCHEDULE_TASKS_PREVIEW.setBounds(new Rectangle(14, 480, 90,
					16)); // Generated
			jLabel_SCHEDULE_TASKS_PREVIEW.setText("Preview:"); // Generated
			jLabel_SCHEDULE_TASKS_MODIFY_TODO = new JLabel();
			jLabel_SCHEDULE_TASKS_MODIFY_TODO.setBounds(new Rectangle(15, 435,
					38, 20)); // Generated
			jLabel_SCHEDULE_TASKS_MODIFY_TODO.setText("Todo:"); // Generated
			jLabel_SCHEDULE_TASKS_MODIFY_WHEN = new JLabel();
			jLabel_SCHEDULE_TASKS_MODIFY_WHEN.setBounds(new Rectangle(15, 405,
					38, 20)); // Generated
			jLabel_SCHEDULE_TASKS_MODIFY_WHEN.setText("When:"); // Generated
			jLabel_SCHEDULE_TASKS_MODIFY_NAME = new JLabel();
			jLabel_SCHEDULE_TASKS_MODIFY_NAME.setBounds(new Rectangle(15, 375,
					38, 20)); // Generated
			jLabel_SCHEDULE_TASKS_MODIFY_NAME.setText("Name:"); // Generated
			jLabel_SCHEDULE_TASKS = new JLabel();
			jLabel_SCHEDULE_TASKS.setBounds(new Rectangle(15, 15, 108, 16)); // Generated
			jLabel_SCHEDULE_TASKS.setText("Scheduled Tasks"); // Generated
			jPanel_SCHEDULE = new JPanel();
			jPanel_SCHEDULE.setLayout(null); // Generated
			jPanel_SCHEDULE.setEnabled(true); // Generated
			jPanel_SCHEDULE.add(getJScrollPane_SCHEDULE_TASKS(), null); // Generated
			jPanel_SCHEDULE.add(jLabel_SCHEDULE_TASKS, null); // Generated
			jPanel_SCHEDULE.add(getJPanel_SCHEDULE_TASKS_INFO(), null); // Generated
			jPanel_SCHEDULE.add(getJSeparator_SCHEDULE_TASKS(), null); // Generated
			jPanel_SCHEDULE.add(getJSeparator_MAIN_SCHEDULE_MODIFY(), null); // Generated
			jPanel_SCHEDULE.add(getJToggleButton_SCHEDULE_TASKS_ADD(), null); // Generated
			jPanel_SCHEDULE.add(getJButton_SCHEDULE_TASKS_REMOVE(), null); // Generated
			jPanel_SCHEDULE.add(getJButton_SCHEDULE_TASKS_MODIFY(), null); // Generated
			jPanel_SCHEDULE.add(jLabel_SCHEDULE_TASKS_MODIFY_NAME, null); // Generated
			jPanel_SCHEDULE.add(
					getJTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA(), null); // Generated
			jPanel_SCHEDULE.add(jLabel_SCHEDULE_TASKS_MODIFY_WHEN, null); // Generated
			jPanel_SCHEDULE.add(getJComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA(),
					null); // Generated
			jPanel_SCHEDULE.add(jLabel_SCHEDULE_TASKS_MODIFY_TODO, null); // Generated
			jPanel_SCHEDULE.add(getJComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA(),
					null); // Generated
			jPanel_SCHEDULE.add(getJButton_SCHEDULE_TASKS_MODIFY_ADD(), null); // Generated
			jPanel_SCHEDULE.add(getJTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW(),
					null); // Generated
			jPanel_SCHEDULE.add(jLabel_SCHEDULE_TASKS_PREVIEW, null); // Generated
			jPanel_SCHEDULE.add(getJButton_SCHEDULE_TASKS_MODIFY_SAVE(), null); // Generated
			jPanel_SCHEDULE.add(jLabel_SCHEDULE_TASKS_TIME_SET, null); // Generated
		}
		return jPanel_SCHEDULE;
	}

	/**
	 * This method initializes jScrollPane_SCHEDULE_TASKS
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane_SCHEDULE_TASKS() {
		if (jScrollPane_SCHEDULE_TASKS == null) {
			jScrollPane_SCHEDULE_TASKS = new JScrollPane();
			jScrollPane_SCHEDULE_TASKS
					.setBounds(new Rectangle(15, 60, 250, 255)); // Generated
			jScrollPane_SCHEDULE_TASKS
					.setViewportView(getJList_SCHEDULE_TASKS()); // Generated
		}
		return jScrollPane_SCHEDULE_TASKS;
	}

	/**
	 * This method initializes jPanel_SCHEDULE_TASKS_INFO
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_SCHEDULE_TASKS_INFO() {
		if (jPanel_SCHEDULE_TASKS_INFO == null) {
			jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA = new JLabel();
			jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA.setBounds(new Rectangle(60,
					60, 180, 20)); // Generated
			jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA.setText("NULL"); // Generated
			jLabel_SCHEDULE_TASKS_INFO_WHEN = new JLabel();
			jLabel_SCHEDULE_TASKS_INFO_WHEN.setBounds(new Rectangle(15, 60, 38,
					20)); // Generated
			jLabel_SCHEDULE_TASKS_INFO_WHEN.setText("When:"); // Generated
			jLabel_SCHEDULE_TASKS_INFO_NAME_DATA = new JLabel();
			jLabel_SCHEDULE_TASKS_INFO_NAME_DATA.setBounds(new Rectangle(60,
					30, 180, 20)); // Generated
			jLabel_SCHEDULE_TASKS_INFO_NAME_DATA.setText("NULL"); // Generated
			jLabel_SCHEDULE_TASKS_INFO_NAME = new JLabel();
			jLabel_SCHEDULE_TASKS_INFO_NAME.setText("Name:"); // Generated
			jLabel_SCHEDULE_TASKS_INFO_NAME.setBounds(new Rectangle(15, 30, 36,
					20)); // Generated
			jPanel_SCHEDULE_TASKS_INFO = new JPanel();
			jPanel_SCHEDULE_TASKS_INFO.setLayout(null); // Generated
			jPanel_SCHEDULE_TASKS_INFO.setBounds(new Rectangle(310, 60, 255,
					255)); // Generated
			jPanel_SCHEDULE_TASKS_INFO.setBorder(BorderFactory
					.createTitledBorder(null, "Info",
							TitledBorder.DEFAULT_JUSTIFICATION,
							TitledBorder.DEFAULT_POSITION, new Font("Dialog",
									Font.BOLD, 12), new Color(51, 51, 51))); // Generated
			jPanel_SCHEDULE_TASKS_INFO.add(jLabel_SCHEDULE_TASKS_INFO_NAME,
					null); // Generated
			jPanel_SCHEDULE_TASKS_INFO.add(
					jLabel_SCHEDULE_TASKS_INFO_NAME_DATA, null); // Generated
			jPanel_SCHEDULE_TASKS_INFO.add(jLabel_SCHEDULE_TASKS_INFO_WHEN,
					null); // Generated
			jPanel_SCHEDULE_TASKS_INFO.add(
					jLabel_SCHEDULE_TASKS_INFO_WHEN_DATA, null); // Generated
		}
		return jPanel_SCHEDULE_TASKS_INFO;
	}

	/**
	 * This method initializes jSeparator_SCHEDULE_TASKS
	 * 
	 * @return javax.swing.JSeparator
	 */
	private JSeparator getJSeparator_SCHEDULE_TASKS() {
		if (jSeparator_SCHEDULE_TASKS == null) {
			jSeparator_SCHEDULE_TASKS = new JSeparator();
			jSeparator_SCHEDULE_TASKS.setOrientation(JSeparator.VERTICAL);
			jSeparator_SCHEDULE_TASKS
					.setBounds(new Rectangle(285, 60, 10, 256)); // Generated
		}
		return jSeparator_SCHEDULE_TASKS;
	}

	/**
	 * This method initializes jSeparator_MAIN_SCHEDULE_MODIFY
	 * 
	 * @return javax.swing.JSeparator
	 */
	private JSeparator getJSeparator_MAIN_SCHEDULE_MODIFY() {
		if (jSeparator_MAIN_SCHEDULE_MODIFY == null) {
			jSeparator_MAIN_SCHEDULE_MODIFY = new JSeparator();
			jSeparator_MAIN_SCHEDULE_MODIFY.setBounds(new Rectangle(15, 355,
					550, 10)); // Generated
		}
		return jSeparator_MAIN_SCHEDULE_MODIFY;
	}

	/**
	 * This method initializes jButton_SCHEDULE_TASKS_ADD
	 * 
	 * @return javax.swing.JButton
	 */
	private JToggleButton getJToggleButton_SCHEDULE_TASKS_ADD() {
		if (jButton_SCHEDULE_TASKS_ADD == null) {
			jButton_SCHEDULE_TASKS_ADD = new JToggleButton("Add", false);
			jButton_SCHEDULE_TASKS_ADD
					.setBounds(new Rectangle(15, 325, 100, 20)); // Generated
			jButton_SCHEDULE_TASKS_ADD
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (jButton_SCHEDULE_TASKS_ADD.isSelected()) {
								enableTaskToolkit(true); // New Task
							} else {
								enableTaskToolkit(false); // Cancel
							}
						}
					});
		}
		return jButton_SCHEDULE_TASKS_ADD;
	}

	protected void enableTaskToolkit(boolean enabled) {
		jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA.setEnabled(enabled);
		jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA.setEnabled(enabled);
		jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA.setEnabled(enabled);
		jButton_SCHEDULE_TASKS_MODIFY_ADD.setEnabled(enabled);
		jButton_SCHEDULE_TASKS_MODIFY_SAVE.setEnabled(enabled);
	}

	/**
	 * This method initializes jButton_SCHEDULE_TASKS_REMOVE
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_SCHEDULE_TASKS_REMOVE() {
		if (jButton_SCHEDULE_TASKS_REMOVE == null) {
			jButton_SCHEDULE_TASKS_REMOVE = new JButton();
			jButton_SCHEDULE_TASKS_REMOVE.setBounds(new Rectangle(165, 325,
					100, 20)); // Generated
			jButton_SCHEDULE_TASKS_REMOVE.setText("Remove"); // Generated
			jButton_SCHEDULE_TASKS_REMOVE
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (jList_SCHEDULE_TASKS.getSelectedIndex() != -1) {
								plugin.taskscheduler
										.killTask(plugin.taskscheduler
												.getTask(jList_SCHEDULE_TASKS
														.getSelectedValue()
														.toString()));
							}
						}
					});
		}
		return jButton_SCHEDULE_TASKS_REMOVE;
	}

	/**
	 * This method initializes jButton_SCHEDULE_TASKS_MODIFY
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_SCHEDULE_TASKS_MODIFY() {
		if (jButton_SCHEDULE_TASKS_MODIFY == null) {
			jButton_SCHEDULE_TASKS_MODIFY = new JButton();
			jButton_SCHEDULE_TASKS_MODIFY.setBounds(new Rectangle(380, 325,
					115, 20)); // Generated
			jButton_SCHEDULE_TASKS_MODIFY.setText("Modify Task"); // Generated
			jButton_SCHEDULE_TASKS_MODIFY
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							// TODO jFrame with text area to modify the whole
							// thing
							System.out.println("ActionPerformed()");
						}
					});
		}
		return jButton_SCHEDULE_TASKS_MODIFY;
	}

	/**
	 * This method initializes jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA() {
		if (jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA == null) {
			jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA = new JTextField();
			jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA.setBounds(new Rectangle(
					60, 375, 315, 20)); // Generated
			jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA.setEnabled(false); // Generated
		}
		return jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA;
	}

	/**
	 * This method initializes jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA() {
		if (jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA == null) {
			jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA = new JComboBox(
					getDefaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA());
			jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA.setBounds(new Rectangle(
					60, 405, 315, 20)); // Generated
			jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA.setEnabled(false); // Generated
			jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA.setEditable(true); // Generated
		}
		return jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA;
	}

	/**
	 * This method initializes jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA() {
		if (jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA == null) {
			jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA = new JComboBox(
					getDefaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA());
			jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA.setBounds(new Rectangle(
					60, 435, 315, 20)); // Generated
			jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA.setEnabled(false); // Generated
		}
		return jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA;
	}

	/**
	 * This method initializes jList_SCHEDULE_TASKS
	 * 
	 * @return javax.swing.JList
	 */
	private JList getJList_SCHEDULE_TASKS() {
		if (jList_SCHEDULE_TASKS == null) {
			jList_SCHEDULE_TASKS = new JList(
					getdefaultListModel_SCHEDULE_TASKS());
		}
		return jList_SCHEDULE_TASKS;
	}

	/**
	 * This method initializes jButton_SCHEDULE_TASKS_MODIFY_ADD
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_SCHEDULE_TASKS_MODIFY_ADD() {
		if (jButton_SCHEDULE_TASKS_MODIFY_ADD == null) {
			jButton_SCHEDULE_TASKS_MODIFY_ADD = new JButton();
			jButton_SCHEDULE_TASKS_MODIFY_ADD.setBounds(new Rectangle(390, 435,
					59, 20)); // Generated
			jButton_SCHEDULE_TASKS_MODIFY_ADD.setEnabled(false); // Generated
			jButton_SCHEDULE_TASKS_MODIFY_ADD.setText("Add"); // Generated
			jButton_SCHEDULE_TASKS_MODIFY_ADD
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							String action = null;
							if (!jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA
									.getSelectedItem().toString().isEmpty()) {
								action = jComboBox_SCHEDULE_TASKS_MODIFY_TODO_DATA
										.getSelectedItem().toString();
							} else {
								log.warning("Problem adding instruction");
							}
							if (checkAction(action))
								;
						}
					});
		}
		return jButton_SCHEDULE_TASKS_MODIFY_ADD;
	}

	protected boolean checkAction(String action) {
		if (action != null) {
			if (action.equals(actions[0])) { // serverSay
				doAction(TaskActions.serverSay);
				return true;
			} else if (action.equals(actions[1])) { // playerSay
				doAction(TaskActions.playerSay);
				return true;
			} else if (action.equals(actions[2])) { // setTime
				doAction(TaskActions.setTime);
				return true;
			} else if (action.equals(actions[3])) { // setWeather
				doAction(TaskActions.setWeather);
				return true;
			} else if (action.equals(actions[4])) { // spawnMob
				doAction(TaskActions.spawnMob);
				return true;
			} else if (action.equals(actions[5])) { // spawnObject
				doAction(TaskActions.spawnObject);
				return true;
			} else if (action.equals(actions[6])) { // givePlayer
				doAction(TaskActions.givePlayer);
				return true;
			} else if (action.equals(actions[7])) { // playerHealth
				doAction(TaskActions.playerHealth);
				return true;
			}
			return false;
		} else {
			log.warning("Problem adding instruction");
			return false;
		}
	}

	/**
	 * private String[] actions = { "serverSay", "playerSay", "setTime",
	 * "setWeather", "spawnMob", "spawnObject", "givePlayer", "playerHealth" };
	 */
	private boolean doAction(TaskActions action) {
		if (action != null) {
			switch (action) {
			default:
				log.warning("Error adding instruction.");
				return false;
			case serverSay:
				String ss = JOptionPane.showInputDialog(
						"What do you want to say?").toString();
				if (!ss.isEmpty()) {
					String input = (String) JOptionPane.showInputDialog(null,
							"Please select a color", "Color Options",
							JOptionPane.INFORMATION_MESSAGE, null,
							colors.toArray(), "WHITE");
					String text = "serverSay " + ss + " " + input;
					String nl = System.getProperty("line.separator");
					jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.setText(jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
									.getText() + nl + text);
				} else {
					log.warning("Invalid input. Try again!");
					doAction(TaskActions.serverSay);
				}
				return true;
			case playerSay:
				String ps = JOptionPane.showInputDialog(
						"What do you want to say?").toString();
				if (!ps.isEmpty()) {
					String input = (String) JOptionPane.showInputDialog(null,
							"Please select a color", "Color Options",
							JOptionPane.INFORMATION_MESSAGE, null,
							colors.toArray(), "WHITE");
					String text = "playerSay " + ps + " " + input;
					String nl = System.getProperty("line.separator");
					jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.setText(jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
									.getText() + nl + text);
				} else {
					log.warning("Invalid input. Try again!");
					doAction(TaskActions.serverSay);
				}
				return true;
			case setTime:
				String st = JOptionPane
						.showInputDialog(
								"Enter a time.\n  (Where 0 - Morning and 12000 is dusk)")
						.toString();
				if (!st.isEmpty()) {
					String text = "setTime " + st;
					String nl = System.getProperty("line.separator");
					jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.setText(jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
									.getText() + nl + text);
				} else {
					log.warning("Invalid input. Try again!");
					doAction(TaskActions.serverSay);
				}
				return true;
			case setWeather:
				String[] weatherTypes = { "clear", "storm", "thunder" };
				String sw = (String) JOptionPane.showInputDialog(null,
						"Please select the weather", "Weather",
						JOptionPane.INFORMATION_MESSAGE, null, weatherTypes,
						"clear");
				if (!sw.isEmpty()) {
					String text = "setWeather " + sw;
					String nl = System.getProperty("line.separator");
					jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.setText(jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
									.getText() + nl + text);
				} else {
					log.warning("Invalid input. Try again!");
					doAction(TaskActions.serverSay);
				}
				return true;
			case spawnMob:
				String[] creatureTypes = {};
				for (int i = 0; i < defaultComboBoxModel_MAIN_SPAWN.getSize(); i++) {
					creatureTypes[i] = (String) defaultComboBoxModel_MAIN_SPAWN
							.getElementAt(i);
				}
				String sm = (String) JOptionPane.showInputDialog(null,
						"Please select a mob to spawn", "Mob Spawn Dialog",
						JOptionPane.INFORMATION_MESSAGE, null, creatureTypes,
						"creeper");
				if (!sm.isEmpty()) {
					String input = (String) JOptionPane
							.showInputDialog(
									null,
									"Please input a location\n Use the x,y,z format with no spaces",
									"Location", JOptionPane.INFORMATION_MESSAGE);
					String text = "spawnMob " + sm + " " + input;
					String nl = System.getProperty("line.separator");
					jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.setText(jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
									.getText() + nl + text);
				} else {
					log.warning("Invalid input. Try again!");
					doAction(TaskActions.serverSay);
				}
				return true;
			case spawnObject:
				String[] objectTypes = {};
				for (int i = 0; i < defaultComboBoxModel_MAIN_SPAWN_OBJECT
						.getSize(); i++) {
					objectTypes[i] = (String) defaultComboBoxModel_MAIN_SPAWN_OBJECT
							.getElementAt(i);
				}
				String so = (String) JOptionPane.showInputDialog(null,
						"Please select an object", "Object Spawn Dialog",
						JOptionPane.INFORMATION_MESSAGE, null, objectTypes,
						"tree");
				if (!so.isEmpty()) {
					String input = (String) JOptionPane
							.showInputDialog(
									null,
									"Please input a location\n Use the x,y,z format with no spaces",
									"Location", JOptionPane.INFORMATION_MESSAGE);
					String text = "spawnObject " + so + " " + input;
					String nl = System.getProperty("line.separator");
					jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.setText(jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
									.getText() + nl + text);
				} else {
					log.warning("Invalid input. Try again!");
					doAction(TaskActions.serverSay);
				}
				return true;
			case givePlayer:
				return true;
			case playerHealth:
				return true;
			}
		}
		return false;
	}

	/**
	 * This method initializes jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getJTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW() {
		if (jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW == null) {
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW = new JTextPane();
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW.setBounds(new Rectangle(15,
					510, 550, 210)); // Generated
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW.setFont(new Font(
					"Lucida Console", Font.PLAIN, 12)); // Generated
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW.setEditable(true); // Generated
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW.setText(""); // Generated
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW.setForeground(Color.white); // Generated
			jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW.setBackground(Color.black); // Generated
		}
		return jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW;
	}

	/**
	 * This method initializes
	 * defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA() {
		if (defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA == null) {
			defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA = new DefaultComboBoxModel();
			for (int i = 0; i < actions.length; i++) {
				defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA
						.addElement(actions[i]);
			}
		}
		return defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_TODO_DATA;
	}

	/**
	 * This method initializes
	 * defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA() {
		if (defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA == null) {
			defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA = new DefaultComboBoxModel();
			for (int i = 0; i < times.length; i++) {
				defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA
						.addElement(times[i]);
			}
		}
		return defaultComboBoxModel_SCHEDULE_TASKS_MODIFY_WHEN_DATA;
	}

	/**
	 * This method initializes jButton_SCHEDULE_TASKS_MODIFY_SAVE
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_SCHEDULE_TASKS_MODIFY_SAVE() {
		if (jButton_SCHEDULE_TASKS_MODIFY_SAVE == null) {
			jButton_SCHEDULE_TASKS_MODIFY_SAVE = new JButton();
			jButton_SCHEDULE_TASKS_MODIFY_SAVE.setBounds(new Rectangle(465,
					480, 100, 20)); // Generated
			jButton_SCHEDULE_TASKS_MODIFY_SAVE.setEnabled(false); // Generated
			jButton_SCHEDULE_TASKS_MODIFY_SAVE.setText("Save"); // Generated
			jButton_SCHEDULE_TASKS_MODIFY_SAVE
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							saveMacro();
						}
					});
		}
		return jButton_SCHEDULE_TASKS_MODIFY_SAVE;
	}

	private void saveMacro() {
		File mf = new File(plugin.taskscheduler.macroFolder);
		if (!mf.exists()) {
			log.info("No macro folder found, making one instead.");
			mf.mkdir();
		} else {
			log.info("Found dir saving macro");
		}
		File m = new File(plugin.taskscheduler.macroFolder
				+ jTextField_SCHEDULE_TASKS_MODIFY_NAME_DATA.getText().trim());
		if (m.exists()) {
			if (JOptionPane.showConfirmDialog(null,
					"File found\n Do you wish to overwrite?", "Alert",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					FileWriter fw = new FileWriter(m);
					PrintWriter pw = new PrintWriter(fw);
					pw.println("#" + getMacroTime());
					String[] previewText = jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
							.getText().split("\n");
					for (String text : previewText) {
						if (!text.isEmpty()) {
							pw.println(text);
						}
					}
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				log.warning("Could not save macro. Save aborted");
			}
		} else {
			try {
				m.createNewFile();
				FileWriter fw = new FileWriter(m);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("#" + getMacroTime());
				String[] previewText = jTextPane_SCHEDULE_TASKS_MODIFY_PREVIEW
						.getText().split("\n");
				for (String text : previewText) {
					if (!text.isEmpty()) {
						pw.println(text);
					}
				}
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getMacroTime() {
		String selection = jComboBox_SCHEDULE_TASKS_MODIFY_WHEN_DATA
				.getSelectedItem().toString();
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
	 * This method initializes jButton_MAIN_RESTART
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_MAIN_RESTART() {
		if (jButton_MAIN_RESTART == null) {
			jButton_MAIN_RESTART = new JButton();
			jButton_MAIN_RESTART.setBounds(new Rectangle(180, 680, 190, 50)); // Generated
			jButton_MAIN_RESTART.setText("Restart"); // Generated
			jButton_MAIN_RESTART
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (plugin != null) {
								if (plugin.api != null) {
									plugin.api
											.restartServer(
													"Restarting server...",
													"We are restarting, be back in a few...",
													ChatColor.LIGHT_PURPLE);
								} else {
									log.info("SAToolsGUI API is null");
								}
							} else {
								log.info("Plugin null in SAToolsGUI");
							}
						}
					});
		}
		return jButton_MAIN_RESTART;
	}

	/**
	 * This method initializes defaultListModel_SCHEDULE_TASKS
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultListModel getdefaultListModel_SCHEDULE_TASKS() {
		if (defaultListModel_SCHEDULE_TASKS == null) {
			defaultListModel_SCHEDULE_TASKS = new DefaultListModel();
		}
		return defaultListModel_SCHEDULE_TASKS;
	}

	/**
	 * This method initializes jComboBox_MAIN_CONSOLE_SAY_COLOR
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_CONSOLE_SAY_COLOR() {
		if (jComboBox_MAIN_CONSOLE_SAY_COLOR == null) {
			jComboBox_MAIN_CONSOLE_SAY_COLOR = new JComboBox(
					getDefaultComboBoxModel_CHAT_COLORS());
			jComboBox_MAIN_CONSOLE_SAY_COLOR.setBounds(new Rectangle(350, 50,
					90, 20)); // Generated
			jComboBox_MAIN_CONSOLE_SAY_COLOR.setSelectedItem(ChatColor.WHITE
					.name());
		}
		return jComboBox_MAIN_CONSOLE_SAY_COLOR;
	}

	/**
	 * This method initializes jComboBox_MAIN_CONSOLE_MESSAGE_COLOR
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBox_MAIN_CONSOLE_MESSAGE_COLOR() {
		if (jComboBox_MAIN_CONSOLE_MESSAGE_COLOR == null) {
			jComboBox_MAIN_CONSOLE_MESSAGE_COLOR = new JComboBox(
					getDefaultComboBoxModel_CHAT_COLORS());
			jComboBox_MAIN_CONSOLE_MESSAGE_COLOR.setBounds(new Rectangle(350,
					130, 90, 20)); // Generated
			jComboBox_MAIN_CONSOLE_MESSAGE_COLOR
					.setSelectedItem(ChatColor.WHITE.name());
		}
		return jComboBox_MAIN_CONSOLE_MESSAGE_COLOR;
	}

	/**
	 * This method initializes defaultComboBoxModel_CHAT_COLORS
	 * 
	 * @return javax.swing.DefaultComboBoxModel
	 */
	private DefaultComboBoxModel getDefaultComboBoxModel_CHAT_COLORS() {
		if (defaultComboBoxModel_CHAT_COLORS == null) {
			defaultComboBoxModel_CHAT_COLORS = new DefaultComboBoxModel();
			for (ChatColor c : ChatColor.values()) {
				defaultComboBoxModel_CHAT_COLORS.addElement(c.name());
			}
		}
		return defaultComboBoxModel_CHAT_COLORS;
	}

	/**
	 * This method initializes jPanel_MAP
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_MAP() {
		if (jPanel_MAP == null) {
			jLabel_MAP_COMINGSOON = new JLabel();
			jLabel_MAP_COMINGSOON.setBounds(new Rectangle(4, 4, 569, 177)); // Generated
			jLabel_MAP_COMINGSOON.setFont(new Font("Dialog", Font.BOLD, 48)); // Generated
			jLabel_MAP_COMINGSOON.setHorizontalAlignment(SwingConstants.CENTER); // Generated
			jLabel_MAP_COMINGSOON.setText("Comming Soon!!!"); // Generated
			jPanel_MAP = new JPanel();
			jPanel_MAP.setLayout(null); // Generated
			jPanel_MAP.add(jLabel_MAP_COMINGSOON, null); // Generated

		}
		return jPanel_MAP;
	}

	/**
	 * This method initializes jPanel_GREIF
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_GREIF() {
		if (jPanel_GREIF == null) {
			jLabel_GREIF_COMINGSOON = new JLabel();
			jLabel_GREIF_COMINGSOON.setBounds(new Rectangle(5, 5, 566, 192)); // Generated
			jLabel_GREIF_COMINGSOON
					.setHorizontalAlignment(SwingConstants.CENTER); // Generated
			jLabel_GREIF_COMINGSOON.setText("Comming Soon!!!"); // Generated
			jLabel_GREIF_COMINGSOON.setFont(new Font("Dialog", Font.BOLD, 48)); // Generated
			jPanel_GREIF = new JPanel();
			jPanel_GREIF.setLayout(null); // Generated
			jPanel_GREIF.add(jLabel_GREIF_COMINGSOON, null); // Generated
		}
		return jPanel_GREIF;
	}
}

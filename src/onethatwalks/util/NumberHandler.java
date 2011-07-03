package onethatwalks.util;

/**
 * Handles numbers ;P
 * 
 * @author OneThatWalks
 * 
 */
public class NumberHandler {

	/**
	 * Checks for a float in a string
	 * 
	 * @param text
	 *            The text to check for numbers
	 * @return whether the text is a number
	 */
	public static boolean isFloat(String text) {
		try {
			Float.parseFloat(text);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Checks for a long in a string
	 * 
	 * @param text
	 *            The text to check for numbers
	 * @return whether the text is a number
	 */
	public static boolean isLong(String text) {
		try {
			Long.parseLong(text);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Checks for a number in a string
	 * 
	 * @param text
	 *            The text to check for numbers
	 * @return whether the text is a number
	 */
	public static boolean isShort(String text) {
		try {
			Short.parseShort(text);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Checks for a double in the text
	 * 
	 * @param text
	 *            String to check
	 * @return true is the text contains a double, otherwise false
	 */
	public boolean isDouble(String text) {
		try {
			Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks for a number in a string
	 * 
	 * @param text
	 *            The text to check for numbers
	 * @return whether the text is a number
	 */
	public boolean isNumeric(String text) {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}

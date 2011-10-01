package onethatwalks.util;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

public class LogHandler extends Handler {

	JTextArea console;

	public LogHandler(JTextArea textArea) {
		super();
		console = textArea;
		setFormatter(new Formatter() {

			@Override
			public String format(LogRecord arg0) {
				StringBuffer buf = new StringBuffer(1000);
				buf.append(new java.util.Date());
				buf.append(' ');
				buf.append(arg0.getLevel());
				buf.append(':');
				buf.append(formatMessage(arg0));
				buf.append('\n');
				return buf.toString();
			}
		});
	}

	@Override
	public void close() throws SecurityException {
		console.setText("");
	}

	@Override
	public void flush() {
		console.setText("");
	}

	@Override
	public void publish(LogRecord record) {
		if (!isLoggable(record))
			return;

		console.append(getFormatter().format(record));
	}

}

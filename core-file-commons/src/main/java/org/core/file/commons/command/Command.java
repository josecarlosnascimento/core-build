package org.core.file.commons.command;

import java.util.List;

public interface Command {

	int execute(List<String> args);
	
	void showHelp();
	
	String getCommandName();
	
}

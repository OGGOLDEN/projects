/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.command;

/**
 * Command class encapsulates an Incident's transitions through the ServiceWolf FSM
 * Each command has a CommandValue, which indicates which state an incident should transition to,
 * commandInformation, which passes info such as the incident owner or updated status details, and
 * commandMessage, which is just a message that gets added to the incident's message log
 * 
 * @author magolden
 */
public class Command {

	/** Command value */
	private CommandValue commandValue;
	
	/** Command info such as reason for state change, owner, and developer */
	private String commandInformation;
	
	/** Message given by command */
	private String commandMessage;
	
	/**
	 * Creates a Command object with a command value, info, and a message
	 * @param command one of the enumerated CommandValue pseudo-object
	 * @param commandInformation Command info such as reason for state change, owner, and developer
	 * @param commandMessage Message given by command
	 * @throws IllegalArgumentException with the message "CommandValue cannot be null" if the CommandValue is null
	 * @throws IllegalArgumentException with the message "commandMessage cannot be null or empty" if the message is null or empty
	 * @throws IllegalArgumentException with the message "commandInformation cannot be null or empty" if a command that needs commandInformation is null or empty
	 * @throws IllegalArgumentException with the message "commandInformation should be null or empty" if a command that needs null commandInformation has data
	 */
	public Command(CommandValue command, String commandInformation, String commandMessage) {
		if (command == null) {
			throw new IllegalArgumentException("CommandValue cannot be null");
		}
		
		if (commandMessage == null || "".equals(commandMessage)) {
			throw new IllegalArgumentException("commandMessage cannot be null or empty");
		}
		
		if ((command == Command.CommandValue.ASSIGN || command == Command.CommandValue.CANCEL || 
				command == Command.CommandValue.HOLD || command == Command.CommandValue.RESOLVE) && 
				(commandInformation == null || "".equals(commandInformation))) {
			throw new IllegalArgumentException("commandInformation cannot be null or empty");
		}
		
		if ((command == Command.CommandValue.INVESTIGATE || command == Command.CommandValue.REOPEN) && 
				(!(commandInformation == null) || "".equals(commandInformation))) {
			throw new IllegalArgumentException("commandInformation should be null or empty");
		}
		
		this.commandValue = command;
		this.commandInformation = commandInformation;
		this.commandMessage = commandMessage;
	}
	
	/**
	 * Returns the current commandValue from the command object
	 * @return CommandValue the current command
	 */
	public CommandValue getCommand() {
		return this.commandValue;
	}
	
	/**
	 * Returns the command information
	 * @return commandInformation command info
	 */
	public String getCommandInformation() {
		return this.commandInformation;
	}
	
	/**
	 * Returns the command message
	 * @return commandMessage message of the command
	 */
	public String getCommandMessage() {
		return this.commandMessage;
	}
	
	/**
	 * Enumeration for CommandValue. A CommandValue is a command applied to an Incidents
	 * ASSIGN - Moves an incident to the In Progress State
	 * HOLD - Moves an incident to the On Hold State
	 * INVESTIGATE - Moves an Incident from On Hold to In Progress
	 * RESOLVE - Moves an incident to the Resolved State
	 * REOPEN - Moves an incident from Resolved to In Progress
	 * CANCEL - Moves an incident to the Canceled State
	 */
	public enum CommandValue { ASSIGN, HOLD, INVESTIGATE, RESOLVE, REOPEN, CANCEL }
}

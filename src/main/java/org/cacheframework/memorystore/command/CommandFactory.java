package org.cacheframework.memorystore.command;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.cacheframework.memorystore.annotations.Command;

/**
 * Command factory produces commands
 * 
 * @author Vijay
 * @version 0.1
 * @since JDK 1.8
 *
 */

public class CommandFactory {
	
	  private final static Map<String, Class<?>> commandClasses = new HashMap<String, Class<?>>();
	  private final static Map<String, ICommand> commands = new HashMap<String, ICommand>();

	  private final static NullCommand nullCommand = new NullCommand();

	  public CommandFactory() {
		  
	  }
	  
	  static {
	    addCommand(new EchoCommand());
	    addCommand(new AddDataCommand());
	    addCommand(new RemoveDataCommand());
	    addCommand(new GetDataCommand());
	  }
	  

	/**
	 * 
	 * @param name
	 * @return ICommand
	 */
	public static ICommand getCommand(String name) {
	    return commands.getOrDefault(name.toLowerCase(), nullCommand);  
	  }
	

	/**
	 * Method to check if command is present in map
	 * 
	 * @param name
	 * @param annotationClass
	 * @return Boolean
	 */
	  public boolean isPresent(String name, Class<? extends Annotation> annotationClass) {
	    return getMetadata(name).isAnnotationPresent(annotationClass);
	  }
	  

	  /**
	   * 
	   * @param name
	   * @return Boolean
	   */
	  public boolean contains(String name) {
	    return commands.get(name) != null;
	  }
	  
	  
	  /**
	   * Adds command class to commands map
	   * @param command
	   */
	  public static void addCommand(ICommand command) {
		commands.put(command.getClass().getAnnotation(Command.class).value().toLowerCase(), new CommandWrapper((ICommand) command));
	  }

	  /**
	   * Adds command class to commands map
	   * 
	   * @param name
	   * @param command
	   */
	  protected void addCommand(String name, ICommand command) {
	    commands.put(name.toLowerCase(), new CommandWrapper((ICommand) command));
	  }
	  
	  /**
	   * Method to get metadata of command
	   * @param name
	   * @return
	   */
	  private Class<?> getMetadata(String name) {
	    return commandClasses.getOrDefault(name.toLowerCase(), Void.class);
	  }

}

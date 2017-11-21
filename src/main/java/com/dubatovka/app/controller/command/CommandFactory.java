package com.dubatovka.app.controller.command;

import com.dubatovka.app.controller.command.impl.GotoIndexCommand;
import com.dubatovka.app.controller.command.impl.GotoPaginationCommand;
import com.dubatovka.app.controller.command.impl.XMLParseCommand;
import com.dubatovka.app.controller.ConfigConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);
    private static HashMap<CommandType, Command> guestCommands = new HashMap<>();
    
    static {
        guestCommands.put(CommandType.GOTO_INDEX, new GotoIndexCommand());
        guestCommands.put(CommandType.XML_PARSE, new XMLParseCommand());
        guestCommands.put(CommandType.GOTO_PAGINATION, new GotoPaginationCommand());
    }
    
    private enum CommandType {
        GOTO_INDEX,
        XML_PARSE,
        GOTO_PAGINATION;
    }
    
    
    private CommandFactory() {
    }
    
    public static Command defineCommand(HttpServletRequest request) {
        String commandTypeName = request.getParameter(ConfigConstant.PARAM_COMMAND_TYPE);
        boolean validCommandTypeName = validateCommandTypeName(commandTypeName);
        Command command;
        
        if (validCommandTypeName) {
            commandTypeName = commandTypeName.trim().toUpperCase();
            CommandType commandType = CommandType.valueOf(commandTypeName);
            command = guestCommands.get(commandType);
            
            if (command == null) {
                logger.log(Level.ERROR, "Command implementation is not defined for command type " + commandType + " Check class: " + CommandFactory.class.getName());
                
                command = new GotoIndexCommand();
            }
        } else {
            logger.log(Level.ERROR, "Request doesn't have command_type parameter or defined command_type parameter is invalid: " + commandTypeName + ". Check web page: " + request.getHeader("referer"));
            
            command = new GotoIndexCommand();
        }
        
        return command;
    }
    
    private static boolean validateCommandTypeName(String commandName) {
        if (commandName == null || commandName.trim().isEmpty()) {
            return false;
        }
        for (CommandType type : CommandType.values()) {
            if (type.toString().equalsIgnoreCase(commandName)) {
                return true;
            }
        }
        return false;
    }
}
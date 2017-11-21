package com.dubatovka.app.controller.command;

import com.dubatovka.app.controller.command.impl.*;
import com.dubatovka.app.controller.command.impl.authorization.LoginCommand;
import com.dubatovka.app.controller.command.impl.authorization.LogoutCommand;
import com.dubatovka.app.controller.command.impl.authorization.RegisterCommand;
import com.dubatovka.app.controller.command.impl.showusers.ShowAllUsersCommand;
import com.dubatovka.app.controller.command.impl.showusers.ShowUsersByCriteriaCommand;
import com.dubatovka.app.controller.command.impl.xmlparse.GotoPaginationCommand;
import com.dubatovka.app.controller.command.impl.xmlparse.XMLParseCommand;
import com.dubatovka.app.entity.JAuctionUser;
import com.dubatovka.app.manager.ConfigConstant;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static com.dubatovka.app.manager.ConfigConstant.ATTR_ROLE;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);
    private static HashMap<CommandType, Command> guestCommands = new HashMap<>();
    private static HashMap<CommandType, Command> playerCommands = new HashMap<>();
    private static HashMap<CommandType, Command> adminCommands = new HashMap<>();
    
    static {
        guestCommands.put(CommandType.GOTO_INDEX, new GotoIndexCommand());
        guestCommands.put(CommandType.REGISTER, new RegisterCommand());
        guestCommands.put(CommandType.LOGIN, new LoginCommand());
        guestCommands.put(CommandType.LOGOUT, new LogoutCommand());
        guestCommands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        
        guestCommands.put(CommandType.SHOW_ALL_USERS, new ShowAllUsersCommand());
        guestCommands.put(CommandType.SHOW_USERS_BY_CRITERIA, new ShowUsersByCriteriaCommand());
        
        guestCommands.put(CommandType.XML_PARSE, new XMLParseCommand());
        guestCommands.put(CommandType.GOTO_PAGINATION, new GotoPaginationCommand());

//        guestCommands.put(CommandType.RECOVER_PASSWORD, new RecoverPasswordCommand());
//        guestCommands.put(CommandType.SEND_SUPPORT, new SendSupportCommand());
//        guestCommands.put(CommandType.GOTO_SUPPORT, new GotoSupportCommand());
//        guestCommands.put(CommandType.GOTO_REGISTER, new GotoRegisterCommand());
//        guestCommands.put(CommandType.GOTO_RECOVER_PASSWORD, new GotoRecoverPasswordCommand());
//        guestCommands.put(CommandType.GOTO_RULES, new GotoRulesCommand());
//        guestCommands.put(CommandType.GOTO_ERROR_500, new GotoError500Command());
//        guestCommands.put(CommandType.BACK_FROM_ERROR, new BackFromErrorCommand());
//        guestCommands.put(CommandType.GOTO_GAME_FRUITS, new GotoGameFruitsCommand());
//        guestCommands.put(CommandType.GOTO_GAME_FRUITS_SETUP, new GotoGameFruitsSetupCommand());
//        guestCommands.put(CommandType.BACK_FROM_GAME, new BackFromGameCommand());
        
        playerCommands.putAll(guestCommands);
        playerCommands.put(CommandType.LOGOUT, new LogoutCommand());
//        playerCommands.put(CommandType.GOTO_MAIN, new GotoMainCommand());
//        playerCommands.put(CommandType.GOTO_STATS, new GotoStatsCommand());
//        playerCommands.put(CommandType.MULTIPART, multipart);
//        playerCommands.put(CommandType.EDIT_PROFILE, new EditProfileCommand());
//        playerCommands.put(CommandType.VERIFY_PROFILE, new VerifyProfileCommand());
//        playerCommands.put(CommandType.VERIFY_EMAIL, new VerifyEmailCommand());
//        playerCommands.put(CommandType.SEND_EMAIL_CODE, new SendEmailCodeCommand());
//        playerCommands.put(CommandType.REPLENISH_ACCOUNT, new ReplenishAccountCommand());
//        playerCommands.put(CommandType.PAY_LOAN, new PayLoanCommand());
//        playerCommands.put(CommandType.TAKE_LOAN, new TakeLoanCommand());
//        playerCommands.put(CommandType.WITHDRAW_MONEY, new WithdrawMoneyCommand());
//        playerCommands.put(CommandType.SHOW_HISTORY, new ShowHistoryCommand());
//        playerCommands.put(CommandType.SET_SATISFACTION, new RateSupportAnswerCommand());
//        playerCommands.put(CommandType.RESET_SATISFACTION, new ResetSupportAnswerRatingCommand());
//        playerCommands.put(CommandType.GOTO_PLAYER_PROFILE, new GotoPlayerProfileCommand());
//        playerCommands.put(CommandType.GOTO_ACCOUNT, new GotoAccountCommand());
//        playerCommands.put(CommandType.GOTO_REPLENISH_ACCOUNT, new GotoReplenishAccountCommand());
//        playerCommands.put(CommandType.GOTO_PAY_LOAN, new GotoPayLoanCommand());
//        playerCommands.put(CommandType.GOTO_TAKE_LOAN, new GotoTakeLoanCommand());
//        playerCommands.put(CommandType.GOTO_WITHDRAW_MONEY, new GotoWithdrawMoneyCommand());
//        playerCommands.put(CommandType.GOTO_VERIFICATION, new GotoVerificationCommand());
//        playerCommands.put(CommandType.GOTO_OPERATION_HISTORY, new GotoOperationHistoryCommand());
//        playerCommands.put(CommandType.GOTO_EMAIL_VERIFICATION, new GotoEmailVerificationCommand());
//        playerCommands.put(CommandType.GOTO_UPLOAD_PASSPORT, new GotoUploadPassportCommand());
        
        adminCommands.put(CommandType.GOTO_INDEX, new GotoIndexCommand());
        adminCommands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
    }
    
    private enum CommandType {
        GOTO_INDEX,
        REGISTER,
        LOGIN,
        LOGOUT,
        CHANGE_LOCALE,
        
        SHOW_ALL_USERS,
        SHOW_USERS_BY_CRITERIA,
        
        XML_PARSE,
        GOTO_PAGINATION;
    }
    
    
    private CommandFactory() {
    }
    
    //TODO Logger c малелькой буквы
    public static Command defineCommand(HttpServletRequest request) {
        String commandTypeName = request.getParameter(ConfigConstant.PARAM_COMMAND_TYPE);
        JAuctionUser.UserRole role = (JAuctionUser.UserRole) request.getSession().getAttribute(ATTR_ROLE);
        boolean validCommandTypeName = validateCommandTypeName(commandTypeName);
        Command command;
        
        if (validCommandTypeName) {
            commandTypeName = commandTypeName.trim().toUpperCase();
            CommandType commandType = CommandType.valueOf(commandTypeName);
            switch (role) {
                case PLAYER:
                    command = playerCommands.get(commandType);
                    break;
                case ADMIN:
                    command = adminCommands.get(commandType);
                    break;
                default:
                    command = guestCommands.get(commandType);
            }
            
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
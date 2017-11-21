package com.dubatovka.app.controller.command.impl.authorization;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.entity.Admin;
import com.dubatovka.app.entity.JAuctionUser;
import com.dubatovka.app.entity.Player;
import com.dubatovka.app.manager.MessageManager;
import com.dubatovka.app.service.impl.JAuctionUserService;
import com.dubatovka.app.validator.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.dubatovka.app.manager.ConfigConstant.*;

public class LoginCommand implements Command {
    
    @Override
    public PageNavigator execute(HttpServletRequest request) {
               
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(ATTR_LOCALE);
        MessageManager messageManager = MessageManager.getMessageManager(locale);
        StringBuilder errorMessage = new StringBuilder();
        PageNavigator navigator;
        
        boolean valid = true;
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        
        if (!FormValidator.validateEmail(email)) {
            errorMessage.append(messageManager.getMessage(MESSAGE_INVALID_EMAIL)).append(MESSAGE_SEPARATOR);
            valid = false;
        }
        if (!FormValidator.validatePassword(password)) {
            errorMessage.append(messageManager.getMessage(MESSAGE_INVALID_PASSWORD)).append(MESSAGE_SEPARATOR);
            valid = false;
        }
        
        if (valid) {
            JAuctionUser user;
            JAuctionUserService JAuctionUserService = new JAuctionUserService();
            user = JAuctionUserService.authorizeUser(email, password);
            if (user != null) {
                session.setAttribute(ATTR_USER, user);
                session.setAttribute(ATTR_ROLE, user.getRole());
                if (user.getClass() == Player.class) {
                    Player player = (Player) user;
                    JAuctionUser.UserRole userRole = JAuctionUser.UserRole.PLAYER;
                    session.setAttribute(ATTR_ROLE, userRole);
                    session.setAttribute(ATTR_PLAYER, player);
                } else {
                    Admin admin = (Admin) user;
                    JAuctionUser.UserRole userRole = JAuctionUser.UserRole.ADMIN;
                    session.setAttribute(ATTR_ROLE, userRole);
                    session.setAttribute(ATTR_ADMIN, admin);
                }
                navigator = PageNavigator.REDIRECT_GOTO_MAIN;
            } else {
                request.setAttribute(ATTR_EMAIL_INPUT, email);
                errorMessage.append(messageManager.getMessage(MESSAGE_LOGIN_MISMATCH)).append(MESSAGE_SEPARATOR);
                request.setAttribute(ATTR_ERROR_MESSAGE, errorMessage.toString().trim());
                navigator = PageNavigator.FORWARD_PAGE_MAIN;
            }
        } else {
            request.setAttribute(ATTR_EMAIL_INPUT, email);
            request.setAttribute(ATTR_ERROR_MESSAGE, errorMessage.toString().trim());
            navigator = PageNavigator.FORWARD_PAGE_MAIN;
        }
        return navigator;
    }
}

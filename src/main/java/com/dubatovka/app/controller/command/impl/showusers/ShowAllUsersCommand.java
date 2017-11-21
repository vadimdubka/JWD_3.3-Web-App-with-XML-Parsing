package com.dubatovka.app.controller.command.impl.showusers;

import com.dubatovka.app.service.ServiceFactory;
import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.entity.User;
import com.dubatovka.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.dubatovka.app.manager.ConfigConstant.ATTR_USERS;

public class ShowAllUsersCommand implements Command {
    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    
    @Override
    public PageNavigator execute(HttpServletRequest request) {
        
        List<User> users = userService.getAllUsers();
        request.setAttribute(ATTR_USERS, users);
        
        return PageNavigator.FORWARD_PAGE_USERS;
    }
}

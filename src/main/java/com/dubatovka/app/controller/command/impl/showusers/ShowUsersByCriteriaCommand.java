package com.dubatovka.app.controller.command.impl.showusers;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.entity.User;
import com.dubatovka.app.service.ServiceFactory;
import com.dubatovka.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.dubatovka.app.manager.ConfigConstant.*;

public class ShowUsersByCriteriaCommand implements Command {
    private static final UserService userService = ServiceFactory.getInstance().getUserService();
    
    @Override
    public PageNavigator execute(HttpServletRequest request) {
        String fName = request.getParameter(PARAM_NAME);
        String lName = request.getParameter(PARAM_SURNAME);
        
        List<User> users = userService.getUsersByFirstAndLastName(fName, lName);
        request.setAttribute(ATTR_USERS, users);
        
        return PageNavigator.FORWARD_PAGE_USERS;
    }
}

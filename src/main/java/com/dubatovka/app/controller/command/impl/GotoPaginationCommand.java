package com.dubatovka.app.controller.command.impl;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.entity.Candy;
import com.dubatovka.app.controller.pagination.PageModel;
import com.dubatovka.app.controller.pagination.PageModelBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.dubatovka.app.controller.ConfigConstant.*;

public class GotoPaginationCommand implements Command {
    @Override
    public PageNavigator execute(HttpServletRequest request) {
        PageNavigator pageNavigator;
        String pageNumber = request.getParameter(PARAM_PAGE_NUMBER);
        HttpSession session = request.getSession();
        Object modelBuilder = session.getAttribute(ATTR_PAGE_MODEL_BUILDER);
        PageModelBuilder<Candy> pageModelBuilder = new PageModelBuilder<>();
        if (modelBuilder.getClass()==pageModelBuilder.getClass()){
            pageModelBuilder = (PageModelBuilder<Candy>) modelBuilder;
            PageModel<Candy> candyPageModel = pageModelBuilder.getPageModel(pageNumber);
            if (candyPageModel != null) {
                request.setAttribute(ATTR_CANDY_PAGE_MODEL, candyPageModel);
                pageNavigator = PageNavigator.FORWARD_PAGE_CANDIES;
            } else {
                request.setAttribute("errorMessage", "Required page is not available");
                pageNavigator = PageNavigator.FORWARD_PAGE_INDEX;
            }
        } else {
            request.setAttribute("errorMessage", "Required page is not available. Incompatible type");
            pageNavigator = PageNavigator.FORWARD_PAGE_INDEX;
        }
        
        return pageNavigator;
    }
}
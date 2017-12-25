package com.dubatovka.app.controller.command.impl;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.controller.pagination.PageModel;
import com.dubatovka.app.controller.pagination.PageModelBuilder;
import com.dubatovka.app.entity.Candy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.dubatovka.app.controller.ConfigConstant.ATTR_CANDY_PAGE_MODEL;
import static com.dubatovka.app.controller.ConfigConstant.ATTR_PAGE_MODEL_BUILDER;
import static com.dubatovka.app.controller.ConfigConstant.PARAM_PAGE_NUMBER;

@SuppressWarnings("unchecked")
public class GotoPaginationCommand implements Command {
    
    @Override
    public PageNavigator execute(HttpServletRequest request) {
        String pageNumber = request.getParameter(PARAM_PAGE_NUMBER);
        HttpSession session = request.getSession();
        Object modelBuilder = session.getAttribute(ATTR_PAGE_MODEL_BUILDER);
        PageNavigator pageNavigator;
        if (modelBuilder.getClass() == PageModelBuilder.class) {
            PageModelBuilder<Candy> pageModelBuilder = (PageModelBuilder<Candy>) modelBuilder;
            PageModel<Candy> candyPageModel = pageModelBuilder.getPageModel(pageNumber);
            pageNavigator = getPageNavigator(request, candyPageModel);
        } else {
            request.setAttribute("errorMessage", "Required page is not available. Incompatible type");
            pageNavigator = PageNavigator.FORWARD_PAGE_INDEX;
        }
        
        return pageNavigator;
    }
    
    private PageNavigator getPageNavigator(HttpServletRequest request, PageModel<Candy> candyPageModel) {
        PageNavigator pageNavigator;
        if (candyPageModel != null) {
            request.setAttribute(ATTR_CANDY_PAGE_MODEL, candyPageModel);
            pageNavigator = PageNavigator.FORWARD_PAGE_CANDIES;
        } else {
            request.setAttribute("errorMessage", "Required page is not available");
            pageNavigator = PageNavigator.FORWARD_PAGE_INDEX;
        }
        return pageNavigator;
    }
}
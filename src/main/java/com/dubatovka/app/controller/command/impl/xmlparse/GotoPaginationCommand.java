package com.dubatovka.app.controller.command.impl.xmlparse;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.entity.candy.Candy;
import com.dubatovka.app.controller.pagination.PageModel;
import com.dubatovka.app.controller.pagination.PageModelBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.dubatovka.app.manager.ConfigConstant.*;

public class GotoPaginationCommand implements Command {
    @Override
    public PageNavigator execute(HttpServletRequest request) {
        String pageNumber = (String) request.getParameter(PARAM_PAGE_NUMBER);
        
        HttpSession session = request.getSession();
        //TODO посмотреть, как исправить приведение типов, и что делать если в в сессии нет атрибута с атким запросом и вернется null
        PageModelBuilder<Candy> pageModelBuilder = (PageModelBuilder<Candy>) session.getAttribute(ATTR_PAGE_MODEL_BUILDER);
        PageModel<Candy> candyPageModel = pageModelBuilder.getPageModel(pageNumber);
        request.setAttribute(ATTR_CANDY_PAGE_MODEL, candyPageModel);
        
        return PageNavigator.FORWARD_PAGE_CANDIES;
    }
}

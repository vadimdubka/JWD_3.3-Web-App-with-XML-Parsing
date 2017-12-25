package com.dubatovka.app.controller.command.impl;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.PageNavigator;
import com.dubatovka.app.entity.Candy;
import com.dubatovka.app.service.ServiceFactory;
import com.dubatovka.app.service.XMLParseService;
import com.dubatovka.app.controller.pagination.PageModelBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.dubatovka.app.controller.ConfigConstant.*;

public class XMLParseCommand implements Command {
    
    @Override
    public PageNavigator execute(HttpServletRequest request) {
        XMLParseService xmlParseService = ServiceFactory.getInstance().getXmlParseService();
        String documentPath = request.getParameter(PARAM_DOCUMENT_PATH);
        String schemaPath = request.getParameter(PARAM_SCHEMA_PATH);
        String parserType = request.getParameter(PARAM_PARSER_TYPE);
        
        List<Candy> candyList = xmlParseService.getCandiesFromXMLDoc(documentPath, schemaPath, parserType);
        PageModelBuilder<Candy> pageModelBuilder = new PageModelBuilder<>();
        pageModelBuilder.buildPagination(candyList);
        HttpSession session = request.getSession();
        session.setAttribute(ATTR_PAGE_MODEL_BUILDER, pageModelBuilder);
        
        return PageNavigator.FORWARD_GOTO_PAGINATION;
    }
}

package com.dubatovka.app.controller;

import com.dubatovka.app.controller.command.Command;
import com.dubatovka.app.controller.command.CommandFactory;
import com.dubatovka.app.controller.command.PageNavigator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dubatovka.app.manager.ConfigConstant.*;

@WebServlet(name = "MainController", urlPatterns = {"/controller"})
public class MainController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query;
        String responseType;
        Command command = CommandFactory.defineCommand(request);
        PageNavigator navigator;
        if (command != null && (navigator = command.execute(request)) != null) {
            query = navigator.getQuery();
            responseType = navigator.getResponseType();
            switch (responseType) {
                case FORWARD:
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(query);
                    dispatcher.forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + query);
                    break;
                default:
                    query = PAGE_INDEX;
                    response.sendRedirect(request.getContextPath() + query);
            }
        } else {
            query = PAGE_INDEX;
            response.sendRedirect(request.getContextPath() + query);
        }
    }
}
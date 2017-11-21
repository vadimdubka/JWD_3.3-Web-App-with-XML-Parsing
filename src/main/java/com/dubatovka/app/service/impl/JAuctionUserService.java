package com.dubatovka.app.service.impl;

import com.dubatovka.app.dao.JAuctionUserDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.Admin;
import com.dubatovka.app.entity.JAuctionUser;
import com.dubatovka.app.entity.Player;
import com.dubatovka.app.manager.JCasinoEncryptor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JAuctionUserService extends AbstractService {
    //TODO переименовать логгер с мальенькой везде
    private static final Logger LOGGER = LogManager.getLogger(JAuctionUserService.class);
    private JAuctionUserDAO jAuctionUserDAO = daoFactory.getjAuctionUserDAO();
    
    public JAuctionUserService() {
    }
    
    public JAuctionUser authorizeUser(String email, String password) {
        JAuctionUser user = null;
        email = email.toLowerCase().trim();
        password = JCasinoEncryptor.encryptMD5(password);
        try {
            user = jAuctionUserDAO.authorizeUser(email, password);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        if (user != null) {
            if (user.getRole() == JAuctionUser.UserRole.PLAYER) {
                user = initPlayer(user);
            } else {
                user = initAdmin(user);
            }
        }
        return user;
    }
    
    public boolean checkPassword(JAuctionUser user, String password) {
        password = JCasinoEncryptor.encryptMD5(password);
        int id = user.getId();
        try {
            return jAuctionUserDAO.checkPassword(id, password);
        } catch (DAOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return false;
    }
    
    Player initPlayer(JAuctionUser user) {
        if (user == null) {
            return null;
        }
        Player player = new Player();
        player.setId(user.getId());
        player.setEmail(user.getEmail());
        player.setRole(JAuctionUser.UserRole.PLAYER);
        player.setRegistrationDate(user.getRegistrationDate());
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        playerService.initPlayerInfo(player);
        return player;
    }
    
    
    private Admin initAdmin(JAuctionUser user) {
        if (user == null) {
            return null;
        }
        Admin admin = new Admin();
        admin.setId(user.getId());
        admin.setEmail(user.getEmail());
        admin.setRole(JAuctionUser.UserRole.ADMIN);
        admin.setRegistrationDate(user.getRegistrationDate());
        return admin;
    }
}
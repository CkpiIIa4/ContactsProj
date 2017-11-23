package dao;

import config.GlobalConfig;

public class ContactDAOFactory
{
    public static ContactDAO getContactDAO() {
        try {
            Class dao = Class.forName(GlobalConfig.getProperty("dao.class"));
            return (ContactDAO)dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
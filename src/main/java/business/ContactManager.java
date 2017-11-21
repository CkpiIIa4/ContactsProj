package business;

import java.util.List;
import entity.Contact;
import dao.ContactDAO;
import dao.ContactDAOFactory;

public class ContactManager {
    private ContactDAO dao;

    public ContactManager() {
        dao = ContactDAOFactory.getContactDAO();
    }

    public Long addContact(Contact contact) {
        return dao.addContact(contact);
    }

    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }

    public void deleteContact(Long contactId) {
        dao.deleteContact(contactId);
    }

    public Contact getContact(Long contactId) {
        return dao.getContact(contactId);
    }

    public List<Contact> findContacts() {
        return dao.findContacts();
    }
}

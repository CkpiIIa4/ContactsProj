package dao;

import entity.Contact;
import java.util.List;

public interface ContactDAO {
    Long addContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(Long contactId);
    Contact getContact(Long contactId);
    List<Contact> findContacts();
}

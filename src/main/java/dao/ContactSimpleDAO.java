package dao;

import entity.Contact;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactSimpleDAO implements ContactDAO
{
    private final List<Contact> contacts = new ArrayList<Contact>();

    Contact c1 = new Contact("Roman","Fotor","+38-066-789-45-46","romanfotor@gmail.com");
    Contact c2 = new Contact("Petro", "Natus", "+38-098-147-25-89", "natuspe@gmail.com");
    Contact c3 = new Contact("Stepan", "Hmara", "+38-095-668-94-51", "stephm@gmail.com");

    @Override
    public Long addContact(Contact contact) {
        Long id = generateContactId();
        contact.setContactId(id);
        contacts.add(contact);
        return id;
    }

    @Override
    public void updateContact(Contact contact) {
        Contact oldContact = getContact(contact.getContactId());
        if(oldContact != null){
            oldContact.setFirstName(contact.getFirstName());
            oldContact.setLastName(contact.getLastName());
            oldContact.setPhone(contact.getPhone());
            oldContact.setEmail(contact.getEmail());
        }
    }

    @Override

    public void deleteContact(Long contactId) {
        for(Iterator<Contact> it = contacts.iterator(); it.hasNext();) {
            Contact cnt = it.next();
            if(cnt.getContactId().equals(contactId)) {
                it.remove();
            }
        }
    }

    @Override
    public Contact getContact(Long contactId) {
        for(Contact contact : contacts) {
            if(contact.getContactId().equals(contactId)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> findContacts() {
        return contacts;
    }

    private Long generateContactId() {
        Long contactId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while(getContact(contactId) != null) {
            contactId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return contactId;
    }
}

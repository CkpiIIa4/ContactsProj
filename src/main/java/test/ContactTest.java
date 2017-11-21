package test;

import business.ContactManager;
import entity.Contact;
import java.util.List;

public class ContactTest {
    public static void main(String[] args) {
        ContactManager cm = new ContactManager();

        Contact c1 = new Contact("Roman","Fotor","+38-066-789-45-46","romanfotor@gmail.com");
        Contact c2 = new Contact("Petro", "Natus", "+38-098-147-25-89", "natuspe@gmail.com");
        Contact c3 = new Contact("Stepan", "Hmara", "+38-095-668-94-51", "stephm@gmail.com");

        System.out.println("ADD CONTACT ==========");
        Long cId1 = cm.addContact(c1);
        Long cId2 = cm.addContact(c2);
        Long cId3 = cm.addContact(c3);
        List<Contact> result1 = cm.findContacts();
        for(Contact c : result1) {
            System.out.println(c);
        }
        System.out.println("UPDATE CONTACT =======");
        Contact change = new Contact(cId1, "Roman", "Fotor", "+38-066-789-45-46","romanfotor@gmail.com");
        cm.updateContact(change);
        List<Contact> result2 = cm.findContacts();
        for(Contact c : result2) {
            System.out.println(c);
        }

        System.out.println("DELETE CONTACT =======");
        cm.deleteContact(cId1);
        List<Contact> result3 = cm.findContacts();
        for(Contact c : result3) {
            System.out.println(c);
        }

        System.out.println("GET CONTACT ==========");
        Contact contact = cm.getContact(cId2);
        System.out.println(contact);


    }
}

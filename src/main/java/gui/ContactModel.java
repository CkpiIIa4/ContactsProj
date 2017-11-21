package gui;

import entity.Contact;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ContactModel extends AbstractTableModel
{
    private static final String[] headers = {"ID", "Name", "Surname", "Email", "Phone number"};

    private final List<Contact> contacts;

    public ContactModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int column) {
        Contact contact = contacts.get(rowIndex);
        switch (column) {
            case  0:
                return contact.getContactId().toString();
            case 1:
                return contact.getFirstName();
            case 2:
                return contact.getLastName();
            case 3:
                return contact.getEmail();
            default:
                return contact.getPhone();
        }
    }
}

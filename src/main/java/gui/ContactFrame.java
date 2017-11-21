package gui;

import business.ContactManager;
import entity.Contact;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ContactFrame extends JFrame implements ActionListener
{
   private static final String LOAD = "LOAD";
   private static final String ADD = "ADD";
   private static final String EDIT = "EDIT";
   private static final String DELETE = "DELETE";

   private final ContactManager contactManager = new ContactManager();
   private final JTable contactTable = new JTable();

   public ContactFrame() {
       contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
       GridBagLayout gridbag = new GridBagLayout();
       GridBagConstraints gbc = new GridBagConstraints();

       gbc.gridwidth = GridBagConstraints.REMAINDER;
       gbc.fill = GridBagConstraints.BOTH;
       gbc.insets = new Insets(5,5,0,5);

       JPanel btnPanel = new JPanel();
       btnPanel.setLayout(gridbag);

       btnPanel.add(createButton(gridbag, gbc, "Update", LOAD));
       btnPanel.add(createButton(gridbag, gbc, "Add", ADD));
       btnPanel.add(createButton(gridbag, gbc, "Edit", EDIT));
       btnPanel.add(createButton(gridbag, gbc, "Delete", DELETE));

       JPanel left = new JPanel();
       left.setLayout(new BorderLayout());
       left.add(btnPanel, BorderLayout.NORTH);
       add(left, BorderLayout.WEST);

       add(new JScrollPane(contactTable), BorderLayout.CENTER);

       setBounds(100, 200, 900, 400);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       loadContact();

   }

   private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
       JButton button = new JButton(title);

       button.setActionCommand(action);
       button.addActionListener(this);
       gridbag.setConstraints(button, gbc);
       return button;
   }

    @Override
    public void actionPerformed(ActionEvent ae) {

       String action = ae.getActionCommand();

       switch (action){
           case LOAD:
               loadContact();
               break;
           case ADD:
               addContact();
               break;
           case EDIT:
               editContact();
               break;
           case DELETE:
               deleteContact();
               break;
       }
    }

    private void loadContact() {
       List<Contact> contacts = contactManager.findContacts();
       ContactModel cm = new ContactModel(contacts);
       contactTable.setModel(cm);
    }

    private void addContact() {
       EditContactDialog ecd = new EditContactDialog();
       saveContact(ecd);
    }

    private void editContact() {
        int sr = contactTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            Contact cnt = contactManager.getContact(id);
            EditContactDialog ecd = new EditContactDialog(ContactManager.getContact(id));
            saveContact(ecd);
        } else {
            JOptionPane.showMessageDialog(this, "You have to select the row for editing");
        }
    }

       private void saveContact(EditContactDialog ecd) {
           if (ecd.isSave()) {
               Contact cnt = ecd.getContact();
               if(cnt.getContactId() != null) {
                   contactManager.updateContact(cnt);
               } else {
                   contactManager.addContact(cnt);
               }
               loadContact();
           }
     }

     private void deleteContact() {
       int sr = contactTable.getSelectedRow();
       if (sr != -1) {
           Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
           contactManager.deleteContact(id);
           loadContact();
       } else {
           JOptionPane.showMessageDialog(this, "You have to select the row for deleting");
       }


     }

}

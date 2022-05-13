/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kuehnenagel.mahmoudTask.dao;

import com.kuehnenagel.mahmoudTask.model.Contact;
import com.kuehnenagel.mahmoudTask.model.ContactPagination;
import java.util.List;

/**
 *
 * @author Work
 */
public interface ContactDAO {

    public List< Contact> getContacts();
    
    public ContactPagination getContactsPaging(int pageNumber, int pageSize, String text);

    public void saveContact(Contact contact);

    public Contact getContact(int id);

    public void deleteContact(int id);

    public int loadContactFile(String file);
}

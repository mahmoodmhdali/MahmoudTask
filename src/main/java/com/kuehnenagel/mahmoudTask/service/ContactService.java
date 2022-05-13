/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kuehnenagel.mahmoudTask.service;

import com.kuehnenagel.mahmoudTask.model.Contact;
import com.kuehnenagel.mahmoudTask.model.ContactPagination;
import java.io.File;
import java.nio.file.FileSystemException;
import java.util.List;

/**
 *
 * @author Work
 */
public interface ContactService {

    public List< Contact> getContacts();
    
    public ContactPagination getContactsPaging(int pageNumber, int pageSize, String text);

    public void saveContact(Contact contact);

    public Contact getContact(int id);

    public void deleteContact(int id);
    
    public int loadContactFile(File file) throws FileSystemException;

}

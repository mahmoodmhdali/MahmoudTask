/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kuehnenagel.mahmoudTask.service;

import com.kuehnenagel.mahmoudTask.dao.ContactDAO;
import com.kuehnenagel.mahmoudTask.model.Contact;
import com.kuehnenagel.mahmoudTask.model.ContactPagination;
import java.io.File;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Work
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private Environment environment;

    @Autowired
    private ContactDAO contactDAO;

    @Override
    @Transactional
    public List< Contact> getContacts() {
        return contactDAO.getContacts();
    }

    @Override
    @Transactional
    public ContactPagination getContactsPaging(int pageNumber, int pageSize, String text) {
        return contactDAO.getContactsPaging(pageNumber, pageSize, text);
    }

    @Override
    @Transactional
    public void saveContact(Contact contact) {
        contactDAO.saveContact(contact);
    }

    @Override
    @Transactional
    public Contact getContact(int id) {
        return contactDAO.getContact(id);
    }

    @Override
    @Transactional
    public void deleteContact(int id) {
        contactDAO.deleteContact(id);
    }

    @Override
    @Transactional
    public int loadContactFile(File file) throws FileSystemException {
        int contactsAdded = 0;
        String newFilePath = "";
        try {
            // Copy file to mysql secure file priv 
            File copiedFile = new File(environment.getRequiredProperty("jdbc.secure_file_priv") + "newFile.csv");
            Files.copy(file.toPath(), copiedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            newFilePath = copiedFile.getAbsolutePath();
            contactsAdded = contactDAO.loadContactFile(newFilePath);
            // Delete file from mysql directory after finish upload
            new File(newFilePath).delete();
        } catch (Exception e) {
            new File(newFilePath).delete();
            throw new FileSystemException("Failed to load contacts");
        }
        return contactsAdded;
    }

}

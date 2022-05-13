/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kuehnenagel.mahmoudTask.dao;

import com.kuehnenagel.mahmoudTask.model.Contact;
import com.kuehnenagel.mahmoudTask.model.ContactPagination;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Work
 */
@Repository
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Contact> getContacts() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> root = cq.from(Contact.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public ContactPagination getContactsPaging(int pageNumber, int pageSize, String text) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        // Querry biulder to count number of contacts so we can get the max number of pages
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Contact> root1 = criteria.from(Contact.class);
        // check if search field is not null so we can select count based on search field
        if (text != null && !text.equals("")) {
            criteria.select(cb.count(root1)).where(cb.like(root1.<String>get("name"), "%" + text + "%"));
        } else {
            criteria.select(cb.count(root1));
        }
        TypedQuery<Long> queryRating = session.createQuery(criteria);
        Long totalResults = queryRating.getSingleResult();

        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> root = cq.from(Contact.class);

        // check if search field is not null so we can select query based on search field
        if (text != null && !text.equals("")) {
            cq.select(root).where(cb.like(root.<String>get("name"), "%" + text + "%"));
        } else {
            cq.select(root);
        }
        Query query = session.createQuery(cq).setFirstResult(pageNumber * pageSize).setMaxResults(pageSize);

        int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) pageSize));

        return new ContactPagination(maxPages, pageNumber, totalResults.intValue(), query.getResultList());
    }

    @Override
    public void deleteContact(int id) {
        Session session = sessionFactory.getCurrentSession();
        Contact contact = session.byId(Contact.class).load(id);
        session.delete(contact);
    }

    @Override
    public void saveContact(Contact contact) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(contact);
    }

    @Override
    public Contact getContact(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Contact contact = currentSession.get(Contact.class, id);
        return contact;
    }

    @Override
    public int loadContactFile(String file) {
        int addedContacts = 0;
        // getting table row counts
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Contact> root1 = criteria.from(Contact.class);
        criteria.select(cb.count(root1));
        TypedQuery<Long> queryRating = session.createQuery(criteria);
        Long totalResults = queryRating.getSingleResult();
        // file will be loaded only if table is empty
        if (totalResults <= 0) {
            Session currentSession = sessionFactory.getCurrentSession();
            Query query = currentSession.createSQLQuery("LOAD DATA INFILE :filename INTO TABLE tbl_contacts FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES (name, photo)")
                    .setString("filename", file);
            addedContacts = query.executeUpdate();
        }
        return addedContacts;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kuehnenagel.mahmoudTask.model;

import java.util.List;

/**
 *
 * @author Work
 */
public class ContactPagination {

    private int maxPages;
    private int currentPage;
    private long totalResults;
    List<Contact> contactInfo;

    public ContactPagination(int maxPages, int currentPage, long totalResults, List<Contact> contactInfo) {
        this.maxPages = maxPages;
        this.currentPage = currentPage;
        this.totalResults = totalResults;
        this.contactInfo = contactInfo;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Contact> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<Contact> contactInfo) {
        this.contactInfo = contactInfo;
    }

}

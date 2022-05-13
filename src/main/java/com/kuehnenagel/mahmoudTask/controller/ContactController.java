/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kuehnenagel.mahmoudTask.controller;

import com.kuehnenagel.mahmoudTask.model.Contact;
import com.kuehnenagel.mahmoudTask.model.ContactPagination;
import com.kuehnenagel.mahmoudTask.service.ContactService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Work
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/list")
    public String listContacts(Model model, @RequestParam(required = false) String pageNumber, @RequestParam(required = false) String text) throws IOException {
        int currentPageNumber = 0;
        if (pageNumber != null) {
            currentPageNumber = Integer.parseInt(pageNumber);
        }
        // check if page number is less than 0 so we can reset the counter
        if (currentPageNumber < 0) {
            currentPageNumber = 0;
        }
        ContactPagination contactPagination = contactService.getContactsPaging(currentPageNumber, 10, text);
        List<Contact> contacts = contactPagination.getContactInfo();
        int maxPages = contactPagination.getMaxPages() - 1;
        // check if page number is greater than max pages so we can reset it to the max pages
        if (currentPageNumber > maxPages) {
            currentPageNumber = maxPages;
        }
        model.addAttribute("maxPages", maxPages);
        model.addAttribute("text", text);
        model.addAttribute("contacts", contacts);
        model.addAttribute("current", currentPageNumber);
        model.addAttribute("next", currentPageNumber == maxPages + 1 ? maxPages + 1 : currentPageNumber + 1);
        model.addAttribute("previous", currentPageNumber == -1 ? -1 : currentPageNumber - 1);
        return "list-contacts";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/contact/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("contactId") int id,
            Model model) {
        Contact contact = contactService.getContact(id);
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    @GetMapping("/delete")
    public String deleteContact(@RequestParam("contactId") int id) {
        contactService.deleteContact(id);
        return "redirect:/contact/list";
    }
}

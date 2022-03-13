package com.techjs.contactmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techjs.contactmvc.ContactMvcApplication;
import com.techjs.contactmvc.model.Contact;
import com.techjs.contactmvc.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/contacts")
	public String contacts(Model model) {
		if (ContactMvcApplication.isAuthenticated) {
			List<Contact> listContacts = contactService.listContacts();
			if (listContacts.isEmpty()) {
				listContacts = null;
			}
			model.addAttribute("contact", new Contact());
			model.addAttribute("contacts", listContacts);
			return "contacts";
		}

		return "redirect:/signin?nonAuth";
	}

	@PostMapping("/contacts")
	public String save(@Valid @ModelAttribute Contact contact, BindingResult result, Model model) {
		if (ContactMvcApplication.isAuthenticated) {
			if (result.hasErrors()) {
				List<Contact> listContacts = contactService.listContacts();
				if (listContacts.isEmpty()) {
					listContacts = null;
				}
				model.addAttribute("contacts", listContacts);
				return "contacts";
			}

			contactService.createContact(contact);
			contacts(model);
			return "contacts";
		}
		return "redirect:/signin?nonAuth";
	}
}

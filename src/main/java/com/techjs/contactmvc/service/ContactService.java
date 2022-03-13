package com.techjs.contactmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.contactmvc.model.Contact;
import com.techjs.contactmvc.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public List<Contact> listContacts() {
		return contactRepository.findAll();

	}

	public Contact createContact(Contact contact) {
		return contactRepository.save(contact);
	}
}

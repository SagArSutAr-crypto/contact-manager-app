package com.techjs.contactmvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techjs.contactmvc.ContactMvcApplication;
import com.techjs.contactmvc.model.User;
import com.techjs.contactmvc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/signin" }, method = RequestMethod.GET)
	public String signin(Model model) {
		model.addAttribute("user", new User());
		return "auth/signin";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userExists", false);
		return "auth/signup";
	}

	@PostMapping("/users")
	public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "auth/signup";
		}
		int createUser = userService.createUser(user);
		if (createUser == 202) {
			model.addAttribute("userExists", true);
			return "auth/signup";
		}
		model.addAttribute("userExists", false);
		return "redirect:/signin";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors())
			return "auth/signin";

		int validateUser = userService.validateUser(user);

		if (validateUser == 200) {
			ContactMvcApplication.isAuthenticated = true;
			return "redirect:/contacts";
		} else if (validateUser == 404) {
			return "redirect:/signin?error";
		} else
			return "redirect:/signin";
	}

	@RequestMapping("/logout")
	public String logout() {
		ContactMvcApplication.isAuthenticated = false;
		return "redirect:/signin?logout";
	}
}

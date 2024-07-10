package com.cg.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.model.Customer;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	// add an initbinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	//---http://localhost:8080/customer//showIndexPage
	@RequestMapping("/showIndexPage")
	public String showIndexPage() {
				
		return "index";
	}
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) 
	{
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	//-----------http://localhost:8080/customer/showForm------------------------------------------
	
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("customer", new com.cg.model.Customer());
		
		return "customer-form";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@Valid 
			@ModelAttribute("customer")
			Customer theCustomer,
			BindingResult theBindingResult) {
		
		if (theBindingResult.hasErrors()) {
			return "customer-form";
		}
		else {
			return "customer-confirmation";
		}
	}
}













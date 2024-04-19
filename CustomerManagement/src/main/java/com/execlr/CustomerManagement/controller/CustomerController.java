package com.execlr.CustomerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.execlr.CustomerManagement.entity.Customer;
import com.execlr.CustomerManagement.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;

//	@PostMapping("/addCustomerByRequestParam")
//	public Customer addCustomer(@RequestParam("fname") String fname,@RequestParam("lname") String lname,@RequestParam("email") String email)
//	{
//		Customer customer=new Customer();
//		customer.setFirstName(fname);
//		customer.setLastName(lname);
//		customer.setEmail(email);
//		
//		return customerservice.addCustomer(customer);
//		
//	}
//	
//	
//	@PostMapping("/addCustomeByRequestBody")
//	public Customer addCustomer(@RequestBody Customer customer)
//	{
//		return customerservice.addCustomer(customer);
//	}
//	
//	@DeleteMapping("/deleteCustomer")
//	public String deleteCustomer(@RequestParam("custid") int custid)
//	{
//		customerservice.deleteCustomer(custid);
//		return "Record Deleted";
//	}
//	
//	@GetMapping("/getAllCustomers")
//	public List<Customer> getAllCustomers()
//	{
//		return customerservice.getAllCustomers();
//	}
//	
//	@GetMapping("/getCustomer")
//	public Customer getCustomer(@RequestParam("custid") int custid)
//	{
//		return customerservice.getCustomer(custid);
//	}
	
	@GetMapping("/customers")
	public String listCustomers(Model model)
	{
		List<Customer> listcustomers=customerservice.getAllCustomers();
		model.addAttribute("customersfe",listcustomers);
		return "customers";
	}
	
	@GetMapping("/customers/new")
	public String createCustomerForm(Model model)
	{
		Customer c1=new Customer();
		model.addAttribute("customer",c1);
		return "create_customer";
		
	}
	
	@GetMapping("/customers/edit/{id}")
	public String editCustomerForm(@PathVariable("id") int custid, Model model)
	{
		Customer custdb=customerservice.getCustomer(custid);
		model.addAttribute("customer",custdb);
		return "edit_customer";
	}
	
	@GetMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable("id") int custid)
	{
		customerservice.deleteCustomer(custid);
		return "redirect:/customers";
	}
	
	@PostMapping("/addcustomer")
	public String addcustomer(@ModelAttribute Customer customer)
	{
		customerservice.addCustomer(customer);
		return "redirect:/customers";
	}
	
	@PostMapping("/updatecustomer/{id}")
	public String updateEmployee(@PathVariable("id") int custid, @ModelAttribute("customer") Customer customer)
	{
		customerservice.updateEmployee(custid,customer);
		return "redirect:/customers";
	}
}

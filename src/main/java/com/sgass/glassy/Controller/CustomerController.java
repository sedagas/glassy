package com.sgass.glassy.Controller;

import com.sgass.glassy.Model.Customer;
import com.sgass.glassy.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
//
//	@Autowired
//	Prescription prescription;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customer(Model model){
    	List<Customer> customerList = customerRepository.findAll();
    	model.addAttribute("customers", customerList);
        return "customer/customer";
    }

    @RequestMapping(value = "/newCustomer")
    public String newCustomer(Model model){
//		if (result.hasErrors()) {
//			return "add-user";
//		}
    	model.addAttribute("customer", new Customer());
    	return "customer/newCustomer";
	}

    @RequestMapping(value="/saveCustomer", method = RequestMethod.POST)
    public String addCustomer(@Valid Customer customer, BindingResult result, Model model){
    	if(result.hasErrors()){
    		model.addAttribute("result",result);
    		return "redirect:/newCustomer";
		}
    	customerRepository.save(customer);
    	model.addAttribute("customers", customerRepository.findAll());
		return "redirect:/customer";
	}

	@RequestMapping(value = "/editCustomer/{id}")
	public String editCustomer(@PathVariable long id, Model model){
    	Customer customer = customerRepository.findById(id)
				.orElseThrow(() ->new IllegalArgumentException("Invalid Customer id" + id));
    	model.addAttribute("customer", customer);
    	return "customer/editCustomer";
	}

	@RequestMapping(value = "/updateCustomer/{id}", method = RequestMethod.POST)
	public String updateCustomer(@PathVariable long id, @Valid Customer customer, Model model, BindingResult result){

    	if(result.hasErrors()){
    		customer.setId(id);
    		return "customer/editCustomer";
		}
    	customerRepository.save(customer);
    	model.addAttribute("customers", customerRepository.findAll());
    	return "redirect:/customer";
	}

	@RequestMapping(value = "/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable long id, Model model){
    	Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid customer id : "+id));

    	customerRepository.delete(customer);
    	model.addAttribute("customers", customerRepository.findAll());
    	return "redirect:/customer";

	}

}

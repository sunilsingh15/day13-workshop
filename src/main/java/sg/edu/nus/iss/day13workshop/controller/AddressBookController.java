package sg.edu.nus.iss.day13workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import sg.edu.nus.iss.day13workshop.model.Contact;
import sg.edu.nus.iss.day13workshop.service.Contacts;
import sg.edu.nus.iss.day13workshop.utility.Utility;

@Controller
@RequestMapping(path = "/")
public class AddressBookController {

    @Autowired
    Utility utility;

    @Autowired
    Contacts service;

    @Value("${data.dir}")
    private String dataDir;
    
    
    // request method to load landing page
    @GetMapping
    public String showAddressBook(Model model) {
        model.addAttribute("contact", new Contact());
        return "addressBook";
    }

    // post method
    @PostMapping("/contact")
    public String saveAddressBook(@Valid Contact contact, BindingResult result, Model model) throws Exception {
        
        if (result.hasErrors()) {
            return "addressBook";
        }

        // custom data validation to check if email is unique and return message if so
        // if (!utility.isUniqueEmail(contact.getEmail())) {
        //     ObjectError err = new ObjectError("globalError", "%s is not available".formatted(contact.getEmail()));
        //     result.addError(err);
        // }

        service.save(contact, model, dataDir);
        model.addAttribute("successMessage", "Contact saved successfully.");

        return "showContact";
    }

    // get mapping to retrieve contact by ID parameter passed in contact/${parameter}
    @GetMapping("/contact/{contactID}")
    public String getContactByID(Model model, @PathVariable String contactID) throws Exception {

        Contact contact = new Contact();

        contact = service.getContactByID(contactID, dataDir);

        if (contact == null) {
            model.addAttribute("errorMessage", "Contact not found!");
            return "error";
        }

        model.addAttribute("contact", contact);
        return "showContact";
    }

    @GetMapping("/list")
    public String getAllContacts(Model model) {

        service.getAllContactsInURI(model, dataDir);
        return "contacts";
    }


}

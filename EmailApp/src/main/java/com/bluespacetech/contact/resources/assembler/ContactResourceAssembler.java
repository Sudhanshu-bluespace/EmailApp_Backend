package com.bluespacetech.contact.resources.assembler;

import com.bluespacetech.contact.controller.ContactController;
import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.resources.ContactResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * The Class ContactResourceAssembler.
 */
public class ContactResourceAssembler extends ResourceAssemblerSupport<Contact, ContactResource>
{

    /**
     * Instantiates a new contact resource assembler.
     */
    public ContactResourceAssembler()
    {
        super(ContactController.class, ContactResource.class);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.hateoas.ResourceAssembler#toResource(java.lang.Object)
     */
    public ContactResource toResource(Contact contact)
    {
        ContactResource contactResource = new ContactResource();
        contactResource.setObjectId(contact.getId());
        contactResource.setVersion(contact.getVersion());
        contactResource.setFirstName(contact.getFirstName());
        contactResource.setLastName(contact.getLastName());
        contactResource.setEmail(contact.getEmail());

        Link link = ((ControllerLinkBuilder) ControllerLinkBuilder.linkTo(ContactController.class)
                .slash(contact.getId())).withSelfRel();
        contactResource.add(link.withSelfRel());
        return contactResource;
    }

    /**
     * Copy contact into.
     *
     * @param sourceContact the source contact
     * @param destinationContact the destination contact
     */
    public static void copyContactInto(Contact sourceContact, Contact destinationContact)
    {
        destinationContact.setFirstName(sourceContact.getFirstName());
        destinationContact.setLastName(sourceContact.getLastName());
        destinationContact.setEmail(sourceContact.getEmail());
    }

    /**
     * Gets the contact from resource.
     *
     * @param contactResource the contact resource
     * @return the contact from resource
     */
    public static Contact getContactFromResource(ContactResource contactResource)
    {
        Contact destinationContact = new Contact();
        destinationContact.setFirstName(contactResource.getFirstName());
        destinationContact.setLastName(contactResource.getLastName());
        destinationContact.setEmail(contactResource.getEmail());

        return destinationContact;
    }
}

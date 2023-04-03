package org.teilen.web.rest.controllers;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.teilen.web.exception.IdException;
import org.teilen.web.exception.NullReferenceException;
import org.teilen.web.model.Organization;
import org.teilen.web.service.OrganizationService;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(OrganizationRestController.URI)
class OrganizationRestController {

    protected final static String URI = "/api/organizations";

    private OrganizationService organizationService;


    @Autowired
    public OrganizationRestController(OrganizationService organizationService) {
        super();
        this.organizationService = organizationService;
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(path = {"", "/", "/all", "/list"}, method = RequestMethod.GET, produces = "application/json")
    public List<Organization> getAllOrganizations() throws Exception {
        return organizationService.getAll();
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public Organization getOrganizationById(@PathVariable("id") String id) throws Exception {
        if (NumberUtils.isParsable(id)) {
            Long organizationId = Long.parseLong(id);
            return organizationService.getById(organizationId);
        } else
            throw new IdException("Organization id " + id + " is not parsable.Must be an integer.");
    }


    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Organization saveOrganization(@RequestBody Organization organization) throws Exception {
        if (organization == null)
            throw new NullReferenceException("Organization reference binded is null.");

        return organizationService.create(organization);
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
    public Organization editOrganization(@PathVariable("id") String id, @RequestBody Organization organization)
            throws Exception {
        if (NumberUtils.isParsable(id)) {
            Long organizationId = Long.parseLong(id);
            if (organization == null)
                throw new NullReferenceException("Organization reference binded is null.");
            organization.setId(organizationId);

            return organizationService.edit(organization);
        } else
            throw new IdException("Organization id " + id + " is not parsable.Must be an integer.");
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
    public void deleteOrganization(@PathVariable("id") String id) throws Exception {
        if (NumberUtils.isParsable(id)) {
            Long organizationId = Long.parseLong(id);
            organizationService.deleteById(organizationId);
            ;
        } else
            throw new IdException("Organization id " + id + " is not parsable.Must be an integer.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping("/*")
    public ResponseEntity<Object> unMappedRequests() throws Exception {
        return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}

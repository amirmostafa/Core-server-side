package com.core.controllers;

import com.core.models.BasicModel;
import com.core.models.RequestListModel;
import com.core.models.RequestModel;
import com.core.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/request")
public class RequestController extends AbstractController{

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BasicModel> create(@RequestBody RequestModel requestModel) throws Exception {
        return handle(requestService.create(requestModel));
    }

    @RequestMapping(value = "/listRequestsByStatus/{status}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<RequestListModel> listRequestsByStatus(@PathVariable("status") String status) throws Exception {
        return handle(requestService.listRequestsByStatus(status));
    }

    @RequestMapping(value = "/listRequestsForCurrentUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<RequestListModel> listRequestsForCurrentUser(@PathVariable("id") long id) {
        return handle(requestService.listRequestsForCurrentUser(id));
    }

    @RequestMapping(value = "/listRequestsForCurrentAssignedUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<RequestListModel> listRequestsForCurrentAssignedUser(@PathVariable("id") long id) {
        return handle(requestService.listRequestsForCurrentAssignedUser(id));
    }

    @RequestMapping(value = "/changeRequestStatus/{status}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BasicModel> changeRequestStatus(@PathVariable("status") String status, @PathVariable("id") long id) {
        return handle(requestService.changeRequestStatus(status, id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BasicModel> delete(@PathVariable("id") long id) {
        return handle(requestService.delete(id));
    }

    @RequestMapping(value = "/getRequest/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<RequestModel> getRequest(@PathVariable("id") long id) {
        return handle(requestService.getRequest(id));
    }

    @PreAuthorize("@UserService.isOwnerUser()")
    @RequestMapping(value = "/assignRequest/{userId}/{id}/{assignedBy}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<BasicModel> assignRequest(@PathVariable("userId") int userId, @PathVariable("id") long id, @PathVariable("assignedBy") int assignedBy) {
        return handle(requestService.assignRequest(userId, id, assignedBy));
    }



}

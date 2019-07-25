package com.core.services;

import com.core.entites.Request;
import com.core.entites.User;
import com.core.models.*;
import com.core.repositories.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.core.services.Constants.REQUEST_STATUS_NEW;

@Service
public class RequestService {

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private UserService userService;

    @Transactional
    public BasicModel create(RequestModel requestModel) {
        Request request = constructRequest(requestModel);
        requestDao.save(request);
        return new BasicModel();
    }

    @Transactional(readOnly = true)
    public RequestListModel listRequestsByStatus(String status) {
        RequestListModel requestListModel = new RequestListModel();
        List<Request> requests;
        if (status.equals("null") || status.equals("undefined")) {
            requests = requestDao.findByOrderByIdDesc();
        } else {
            requests = requestDao.findByStatusOrderByIdDesc(status);
        }
        for (Request request : requests) {
            requestListModel.getRequestModels().add(constructRequestModel(request));
        }
        return requestListModel;
    }

    @Transactional(readOnly = true)
    public RequestListModel listRequestsForCurrentUser(long id) {
        RequestListModel requestListModel = new RequestListModel();
        List<Request> requests = requestDao.findByUserIdOrderByIdDesc(id);
        for (Request request : requests) {
            requestListModel.getRequestModels().add(constructRequestModel(request));
        }
        return requestListModel;
    }


    @Transactional(readOnly = true)
    public RequestListModel listRequestsForCurrentAssignedUser(long id) {
        RequestListModel requestListModel = new RequestListModel();
        List<Request> requests = requestDao.findByAssignedToOrderByModificationDateDesc(id);
        for (Request request : requests) {
            requestListModel.getRequestModels().add(constructRequestModel(request));
        }
        return requestListModel;
    }

    @Transactional
    public BasicModel changeRequestStatus(String status, long id) {
        Request request = requestDao.findFirstById(id);
        request.setStatus(status);
        requestDao.save(request);
        return new BasicModel();
    }

    @Transactional
    public BasicModel assignRequest(long userId, long id, long assignedBy) {
        Request request = requestDao.findFirstById(id);
        request.setAssignedTo(userId);
        request.setAssignedBy(assignedBy);
        request.setStatus(Constants.REQUEST_STATUS_ASSIGNED_TO_DELEGATE);
        requestDao.save(request);
        return new BasicModel();
    }

    private Request constructRequest(RequestModel requestModel) {
        Request request = new Request();
        request.setDate(LocalDate.parse(requestModel.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        request.setTime(requestModel.getTime());
        request.setName(requestModel.getName());
        request.setDeliveryAddress(requestModel.getDeliveryAddress());
        request.setTotalAmount(requestModel.getTotalAmount());
        request.setDescription(requestModel.getDescription());
        request.setStatus(requestModel.getStatus());
        request.setUser(userService.getUserById(requestModel.getUserModel().getId()));
        return request;
    }

    private RequestModel constructRequestModel(Request request) {
        RequestModel requestModel = new RequestModel();
        requestModel.setDate(request.getDate().toString());
        requestModel.setId(request.getId());
        requestModel.setTime(request.getTime());
        requestModel.setName(request.getName());
        requestModel.setDescription(request.getDescription());
        requestModel.setTotalAmount(request.getTotalAmount());
        requestModel.setDeliveryAddress(request.getDeliveryAddress());
        requestModel.setStatus(request.getStatus());
        requestModel.setUserModel(userService.constructUserModel(request.getUser()));
        requestModel.setUserName(requestModel.getUserModel().getName());
        if (request.getAssignedTo() != null) {
            requestModel.setAssignedTo(userService.constructUserModel(userService.getUserById(request.getAssignedTo())));
            requestModel.setAssignedtoName(requestModel.getAssignedTo().getName());
        }
        if (request.getAssignedBy() != null) {
            requestModel.setAssignedBy(userService.constructUserModel(userService.getUserById(request.getAssignedBy())));
        }
        return requestModel;
    }

    public BasicModel delete(long id) {
        Request request = requestDao.findFirstById(id);
        if (request == null) {
            throw new RuntimeException("REQUEST_NOT_EXISTING");
        }
        if (!REQUEST_STATUS_NEW.equals(request.getStatus())) {
            throw new RuntimeException("REQUEST_IS_IN_PROCESSING");
        }
        requestDao.delete(request);
        return new BasicModel();
    }

    public RequestModel getRequest(long id) {
        Request request = requestDao.findFirstById(id);
        if (request == null) {
            throw new RuntimeException("REQUEST_NOT_EXISTING");
        }
        return constructRequestModel(request);
    }
}

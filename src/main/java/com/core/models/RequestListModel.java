package com.core.models;

import java.util.ArrayList;
import java.util.List;

public class RequestListModel extends BasicModel {
    private List<RequestModel> requestModels = new ArrayList<>();

    public List<RequestModel> getRequestModels() {
        return requestModels;
    }

    public void setRequestModels(List<RequestModel> requestModels) {
        this.requestModels = requestModels;
    }
}

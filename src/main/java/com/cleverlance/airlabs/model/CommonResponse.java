package com.cleverlance.airlabs.model;

public class CommonResponse<T> {
    private Object request;
    private T response;

    public CommonResponse() {
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}

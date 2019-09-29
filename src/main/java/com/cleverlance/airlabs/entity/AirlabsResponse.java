package com.cleverlance.airlabs.entity;

public class AirlabsResponse<T> {
    private Object request;
    private T response;

    public AirlabsResponse() {
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

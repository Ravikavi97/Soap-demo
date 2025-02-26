package com.soap.demo.endpoint;

import com.example.calculator.AddRequest;
import com.example.calculator.AddResponse;
import com.soap.demo.service.ExternalApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CalculatorEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/calculator";

    private final ExternalApiClient apiClient;

    @Autowired
    public CalculatorEndpoint(ExternalApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddRequest")
    @ResponsePayload
    public AddResponse addNumbers(@RequestPayload AddRequest request) {
        AddResponse response = new AddResponse();

        // Call external API
        int result = apiClient.callExternalAddService(request.getA(), request.getB());

        response.setResult(result);
        return response;
    }
}

package com.example.vplay.network.models;

import java.util.ArrayList;
import java.util.List;

public class NetworkResponse {
    public static final int QUERY_SUCCESS = 0;
    public static final int NETWORK_ERROR = 1;
    public static final int QUERY_ERROR = 2;

    // Indicates whether network query was successful or not
    // 0 - Query success
    // 1 - Network error
    // 2 - Query error
    private int responseStatus = NETWORK_ERROR;

    // Response messages
    private List<String> responseMessage;

    public NetworkResponse() {}

    public NetworkResponse(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public NetworkResponse(int responseStatus, String responseMessage) {
        this.responseStatus = responseStatus;

        this.responseMessage = new ArrayList<>();
        this.responseMessage.add(responseMessage);
    }

    public NetworkResponse(int responseStatus, List<String> responseMessage) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
    }

    public boolean isError() {
        return responseStatus > QUERY_SUCCESS;
    }

    public String getErrorMessage() {
        if (isError()) {
            if (responseStatus == NETWORK_ERROR) return "Network error";
            if (responseStatus == QUERY_ERROR) {
                String msg = "";
                if (responseMessage != null) {
                    for (String rmsg: responseMessage) msg += rmsg + " ";
                }

                return msg.trim();
            }
        }

        return "";
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public List<String> getResponseMessage() {
        return responseMessage;
    }
}

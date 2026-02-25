package com.ai.service.dto;

public class ChatDto {

    private String query;

    public ChatDto(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

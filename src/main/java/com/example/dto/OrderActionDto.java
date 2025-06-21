/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dto;

public class OrderActionDto {
    private Long orderId;
    private String action; // "confirm" or "refuse"

    public OrderActionDto() {}

    public OrderActionDto(Long orderId, String action) {
        this.orderId = orderId;
        this.action = action;
    }

    // getters & setters

    public Long getOrderId() {
        return orderId;
    }

    public String getAction() {
        return action;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
}

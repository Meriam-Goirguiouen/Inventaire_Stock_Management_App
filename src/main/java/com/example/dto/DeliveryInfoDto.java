/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dto;

public class DeliveryInfoDto {
    private Long orderId;
    private String comments;

    public DeliveryInfoDto() {}

    public DeliveryInfoDto(Long orderId, String comments) {
        this.orderId = orderId;
        this.comments = comments;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getComments() {
        return comments;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    
}


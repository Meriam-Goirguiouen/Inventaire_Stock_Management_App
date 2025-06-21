
package com.example.dto;

public class DocumentDto {

    private Long id;              // Document ID
    private String filename;      // Name of the file, e.g. "bon_commande_123.pdf"
    private String contentType;   // MIME type, e.g. "application/pdf"
    private byte[] data;          // File content as byte array (if loading from DB)
    private Long orderId;         // Related order ID (optional)

    public DocumentDto() {}

    public DocumentDto(Long id, String filename, String contentType, byte[] data, Long orderId) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.data = data;
        this.orderId = orderId;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}


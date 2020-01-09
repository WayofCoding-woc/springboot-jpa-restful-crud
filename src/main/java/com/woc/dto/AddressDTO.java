package com.woc.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 6372027211608804484L;

    private Integer id;

    private String line1;

    private String line2;

    private Integer pin;

    private String type;

    private Integer employeeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", pin=" + pin +
                ", type='" + type + '\'' +
                '}';
    }
}

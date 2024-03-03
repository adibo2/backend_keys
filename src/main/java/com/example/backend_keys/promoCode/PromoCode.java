package com.example.backend_keys.promoCode;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PrmoCode")
public class PromoCode {
    @Id
    //it like auto increment
    @SequenceGenerator(
            name = "PromoCode_id_sequence",
            sequenceName = "PromoCode_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PromoCode_id_sequence"
    )
    @Column(name = "id")
    private Integer id;
    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private int amount;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "expirationDate")
    private Date expirationDate;

    public PromoCode(Integer id, String code, int amount, Date createDate, Date expirationDate) {
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
    }

    public PromoCode() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Pengfei on 2015/12/18.
 */
@Entity
@Table(name = "T_STORE_PRODUCT")
public class StoreProduct implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = -9175217388712447951L;
    private Long id;
    private Long storeId;
    private Long productId;

    @Id
    @SequenceGenerator(name = "storeProductSequence", sequenceName = "SEQ_STORE_PRODUCT")
    @GeneratedValue(generator = "storeProductSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STORE_ID")
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Column(name = "PRODUCT_ID")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}

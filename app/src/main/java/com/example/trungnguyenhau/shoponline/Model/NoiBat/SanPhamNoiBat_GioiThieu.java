package com.example.trungnguyenhau.shoponline.Model.NoiBat;

import java.io.Serializable;

/**
 * Created by TRUNGNGUYENHAU on 4/26/2017.
 */

public class SanPhamNoiBat_GioiThieu implements Serializable {
    String urlImage;
    String nameProduct;
    Double donGia;

    public SanPhamNoiBat_GioiThieu(String urlImage, String nameProduct) {
        this.urlImage = urlImage;
        this.nameProduct = nameProduct;
    }

    public SanPhamNoiBat_GioiThieu(String urlImage, String nameProduct, Double donGia) {
        this.urlImage = urlImage;
        this.nameProduct = nameProduct;
        this.donGia = donGia;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }
}

package com.example.trungnguyenhau.shoponline.Model.GerneralModel;

import java.util.List;

/**
 * Created by TRUNGNGUYENHAU on 4/21/2017.
 */

public class TypeProduct {
    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<TypeProduct> getListDanhMucCon() {
        return listDanhMucCon;
    }

    public void setListDanhMucCon(List<TypeProduct> listDanhMucCon) {
        this.listDanhMucCon = listDanhMucCon;
    }

    int MALOAISP,MALOAICHA;
    String TENLOAISP;
    List<TypeProduct> listDanhMucCon;
}

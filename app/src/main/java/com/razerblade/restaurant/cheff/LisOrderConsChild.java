package com.razerblade.restaurant.cheff;

import java.io.Serializable;

/**
 * Created by waski on 03/05/2018.
 */

public class LisOrderConsChild implements Serializable {
    public String idMakanan;
    public String jumlahMakanan;
    public String harga;
    public String meja;
    public String statusBayar;
    public String statusOrder;
    public LisOrderConsChild(){}

    public LisOrderConsChild(String idMakanan, String jumlahMakanan, String harga, String meja, String statusBayar, String statusOrder) {
        this.idMakanan = idMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.harga = harga;
        this.meja = meja;
        this.statusBayar = statusBayar;
        this.statusOrder = statusOrder;
    }

    public String getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getJumlahMakanan() {
        return jumlahMakanan;
    }

    public void setJumlahMakanan(String jumlahMakanan) {
        this.jumlahMakanan = jumlahMakanan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getMeja() {
        return meja;
    }

    public void setMeja(String meja) {
        this.meja = meja;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}

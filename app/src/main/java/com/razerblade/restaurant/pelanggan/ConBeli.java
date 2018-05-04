package com.razerblade.restaurant.pelanggan;

import java.io.Serializable;

/**
 * Created by waski on 01/05/2018.
 */

public class ConBeli implements Serializable {

    public String idMakanan;
    public String jumlahMakanan;
    public String harga;
    public String meja;
    public String statusOrder;
    public String statusBayar;

    public ConBeli(){

    }

    public ConBeli(String idMakanan, String jumlahMakanan, String harga, String meja, String statusOrder, String statusBayar) {
        this.idMakanan = idMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.harga = harga;
        this.meja = meja;
        this.statusOrder = statusOrder;
        this.statusBayar = statusBayar;
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

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }
}
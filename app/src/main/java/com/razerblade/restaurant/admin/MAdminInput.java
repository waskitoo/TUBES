package com.razerblade.restaurant.admin;

import java.io.Serializable;

/**
 * Created by waski on 21/04/2018.
 */

public class MAdminInput implements Serializable {
    public String key;
    public String image_url;
    public String nama;

    public MAdminInput(String key, String image_url, String nama, String deskripsi, String jenis) {
        this.key = key;
        this.image_url = image_url;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.jenis = jenis;
    }

    public String deskripsi;
    public String jenis;

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}

    public String getImage_url() {return image_url;}

    public void setImage_url(String image_url) {this.image_url = image_url;}

    public String getNama() {return nama;}

    public void setNama(String nama) {this.nama = nama;}

    public String getDeskripsi() {return deskripsi;}

    public void setDeskripsi(String deskripsi) {this.deskripsi = deskripsi;}

    public String getJenis() {return jenis;}

    public void setJenis(String jenis) {this.jenis = jenis;}
}

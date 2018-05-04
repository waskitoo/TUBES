package com.razerblade.restaurant.cheff;

import java.io.Serializable;

/**
 * Created by waski on 03/05/2018.
 */

public class ListOrderCons implements Serializable {
    public String user;

    public  ListOrderCons(){}
    public ListOrderCons(String user) {
        this.user = user;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}

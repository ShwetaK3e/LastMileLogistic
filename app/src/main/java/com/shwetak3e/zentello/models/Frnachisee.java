package com.shwetak3e.zentello.models;

import android.provider.DocumentsContract;

import java.util.List;

/**
 * Created by Pervacio on 5/14/2017.
 */

public class Frnachisee {

    String name;
    String[] pincodes;
    List<Route> route;
    List<People>  deliveryPerson;

    public List<People> getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(List<People> deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPincodes() {
        return pincodes;
    }

    public void setPincodes(String[] pincodes) {
        this.pincodes = pincodes;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }




    public static class Route {

        String route_name;
        String[] pincodes_in_route;



        public String getRoute_name() {
            return route_name;
        }

        public void setRoute_name(String route_name) {
            this.route_name = route_name;
        }

        public String[] getPincodes_in_route() {
            return pincodes_in_route;
        }

        public void setPincodes_in_route(String[] pincodes_in_route) {
            this.pincodes_in_route = pincodes_in_route;
        }


    }

    public static class People {

        String id;
        String name;
        String phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}

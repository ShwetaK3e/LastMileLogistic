package com.shwetak3e.zentello.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Pervacio on 5/10/2017.
 */

public class Parcel {

    String bookingDate;
    float length,width,height;
    float weight;
    String mode;
    String parcel_owner;
    String pick_up_person;
    boolean pick_up;

    public boolean isPick_up() {
        return pick_up;
    }

    public void setPick_up(boolean pick_up) {
        this.pick_up = pick_up;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getParcel_owner() {
        return parcel_owner;
    }

    public void setParcel_owner(String parcel_owner) {
        this.parcel_owner = parcel_owner;
    }

    public String getPick_up_person() {
        return pick_up_person;
    }

    public void setPick_up_person(String pick_up_person) {
        this.pick_up_person = pick_up_person;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    Location origin,destination;



    public class Location{

        LatLng latLng;
        Long pincode;
        String placeName;
        String locality;
        String street_no;
        String city;
        String state;
        String country;

        public LatLng getLatLng() {
            return latLng;
        }

        public void setLatLng(LatLng latLng) {
            this.latLng = latLng;
        }

        public Long getPincode() {
            return pincode;
        }

        public void setPincode(Long pincode) {
            this.pincode = pincode;
        }

        public String getPlaceName() {
            return placeName;
        }

        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public String getStreet_no() {
            return street_no;
        }

        public void setStreet_no(String street_no) {
            this.street_no = street_no;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }


    }
}

package com.cinema.models;

public class FoodData {
    private int fid;
    private String fimage;
    private String fname;
    private String fcategory;
    private int fprice;
    private int quantity = 1; 
    

    private int totalPrice;   

    public FoodData(int fid, String fname, String fimage, int fprice, String fcategory) {
        this.fid = fid;
        this.fimage = fimage;
        this.fname = fname;
        this.fcategory = fcategory;
        this.fprice = fprice;  // unit price
        this.quantity = 1;
        this.totalPrice = fprice; // initial total
    }

    @Override
    public String toString() {
        return "FoodData [fid=" + fid + ", fimage=" + fimage + ", fname=" + fname +
               ", fcategory=" + fcategory + ", fprice=" + fprice + ", quantity=" + quantity + "]";
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.fprice * quantity; // auto-update total
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFimage() {
        return fimage;
    }

    public void setFimage(String fimage) {
        this.fimage = fimage;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFcategory() {
        return fcategory;
    }

    public void setFcategory(String fcategory) {
        this.fcategory = fcategory;
    }

    public int getFprice() {
        return fprice;
    }

    public void setFprice(int fprice) {
        this.fprice = fprice;
    }
}

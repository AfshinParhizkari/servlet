package main.java.com.afshin;

import java.util.Date;

public class Product {

    public void Product()
    {    }

    int id;
    String name;
    int countryid;
    int count;
    Float price;
    Date createdate;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryid() {
        return this.countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date date) {
        this.createdate = date;
    }

}

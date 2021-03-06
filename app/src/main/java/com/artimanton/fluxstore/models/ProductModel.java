package com.artimanton.fluxstore.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProductModel implements Serializable {

    public String image_url;
    public String title;
    public String price;
    public String size;
    public String color;
    public String description;
    public String product_code;
    public String material;
    public String country;
    public String key;

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }



    public ProductModel(){}

    public ProductModel(String image_url, String title, String price, String size, String color, String description, String product_code, String material, String country, String key) {
        this.image_url = image_url;
        this.title = title;
        this.price = price;
        this.size = size;
        this.color = color;
        this.description = description;
        this.product_code = product_code;
        this.material = material;
        this.country = country;
        this.key = key;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "image_url='" + image_url + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", product_code='" + product_code + '\'' +
                ", material='" + material + '\'' +
                ", country='" + country + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("image_url", image_url);
        result.put("title", title);
        result.put("price", price);
        result.put("size", size);
        result.put("color", color);
        result.put("description", description);
        result.put("product_code", product_code);
        result.put("material", material);
        result.put("country", country);
        result.put("key", key);
        return result;
    }
}

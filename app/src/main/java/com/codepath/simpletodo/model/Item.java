package com.codepath.simpletodo.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name="items")
public class Item extends Model{
    @Column(name="description")
    public String description;

    @Column(name="urgency")
    public Urgency urgency;

    public Item() {
        description = "";
    }

    public Item(String desc) {
        description = desc;
    }

    public static List<Item> getAll() {
        return new Select().from(Item.class).execute();
    }

    public String toString() {
        return description;
    }
}

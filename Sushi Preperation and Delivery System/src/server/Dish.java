package server;

import common.Model;
import common.UpdateEvent;
import common.UpdateListener;


import java.util.HashMap;

public class Dish extends Model implements UpdateListener {

    private String name;
    private String description;
    private float price;
    private HashMap<Ingredient,Integer> ingredients;

    public Dish(){

        ingredients = new HashMap<>();

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updated(UpdateEvent updateEvent) {

    }

}

package server;

import common.Model;
import common.UpdateEvent;
import common.UpdateListener;

import java.util.HashSet;
import java.util.Set;

public class Supplier extends Model implements UpdateListener {

    private String name;
    private float distance;
    private Set<Ingredient> soldIngredients;

    public Supplier(String name, float distance){

        this.name = name;
        this.distance = distance;
        soldIngredients = new HashSet<>();

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updated(UpdateEvent updateEvent) {

    }

    private void sells(Ingredient ingredient){
        soldIngredients.add(ingredient);
    }
}

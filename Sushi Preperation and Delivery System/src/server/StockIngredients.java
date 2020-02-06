package server;

import common.UpdateEvent;

import java.util.HashMap;
import java.util.Map;

public class StockIngredients {

    private Map<Ingredient,Integer> ingredientsSupplies = new HashMap<>();

    private int restockThreshold;
    private int restockAmount;

    StockIngredients(){

    }

    void stockIngredient(Ingredient ingredient){
        ingredientsSupplies.put(ingredient,ingredient.getQuantity());
    }


    public Map<Ingredient, Integer> getIngredientsSupplies() {
        return ingredientsSupplies;
    }
}

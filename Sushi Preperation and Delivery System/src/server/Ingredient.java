package server;

import common.Model;
import common.UpdateEvent;
import common.UpdateListener;

public class Ingredient extends Model implements UpdateListener {

    private String name;
    private int quantity;
    private String supplier;
    private boolean liquid;
    StockIngredients stockIngredients = new StockIngredients();

    public Ingredient(String name, int quantity, String supplier, boolean liquid){

        this.name = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.liquid = liquid;

        stockIngredients.stockIngredient(this);

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updated(UpdateEvent updateEvent) {

    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity){
        this.quantity = quantity;
        Ingredient temp = this;
        this.addUpdateListener(new UpdateListener() {
            @Override
            public void updated(UpdateEvent updateEvent) {
                updateEvent = new UpdateEvent(temp, Integer.toString(quantity), getQuantity(),quantity );
                notifyUpdate(updateEvent.property, updateEvent.oldValue, updateEvent.newValue);
            }
        });
    }


}

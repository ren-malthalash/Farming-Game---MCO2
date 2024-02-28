package Gameplay.Gameplay.EventSystem;

import Gameplay.Farm.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map.Entry;

/**
 * Used to determine the passing of a day
 * Notifies all registered observers
 * <p>
 * Uses basic observer-pattern logic
 */
public class EventSystem implements EventSystemInterface {
    private final Hashtable<Events, ArrayList<Object>> observerList;
    private int days;

    public EventSystem() {
        this.observerList = new Hashtable<Events, ArrayList<Object>>();
        this.days = 1;
        register(Events.HARVEST_END, this);
    }


    /**
     * @return int
     */
    public int getDays() {
        return days;
    }

    /**
     * Registers object to the event system
     *
     * @param event    - key to listen to
     * @param observer - object to register
     */
    public void register(Events event, Object observer) {
        if (!observerList.containsKey(event)) {
            observerList.put(event, new ArrayList<Object>(Collections.singletonList(observer)));
        } else if (!observerList.get(event).contains(observer)) {
            observerList.get(event).add(observer);
        }
        //System.out.println(observer.getClass().getSimpleName() + " is Registered to " + event.toString());
    }

    /**
     * Removes a registed object from the event system
     *
     * @param event    - key to unregister from
     * @param observer - object to unregister
     */
    public void unregister(Events event, Object observer) {
        observerList.get(event).remove(observer);
    }


    /**
     * @param observer
     */
    public void unregisterAll(Object observer) {
        for (Entry<Events, ArrayList<Object>> element : observerList.entrySet()) {
            element.getValue().remove(observer);
        }
    }

    public void dayEnd() {
        days++;
        notifyObservers(Events.END_DAY);
    }


    /**
     * @param event
     */
    public void notifyObservers(Events event) {
        //System.out.println(event.toString() + " Observers are being notified");
        Object object;
        for (int i = 0; i < observerList.get(event).size(); i++) {
            object = observerList.get(event).get(i);
            if (object instanceof EventSystemInterface) {
                ((EventSystemInterface) object).eventNotify(event, days, null);
            }
        }
    }


    /**
     * @param event
     * @param payload
     */
    public void notifyObservers(Events event, Object payload) {
        //System.out.println(event.toString() + " Observers are being notified with payload");
        Object object;
        for (int i = 0; i < observerList.get(event).size(); i++) {
            object = observerList.get(event).get(i);
            if (object instanceof EventSystemInterface) {
                ((EventSystemInterface) object).eventNotify(event, days, payload);
            }
        }
    }


    /**
     * @param event
     * @param days
     * @param payload
     */
    @Override
    public void eventNotify(Events event, int days, Object payload) {
        /**
         * The Event System is responsible for cleaning itself from Crop references
         * since Crop objects have registered themselves to the Event System
         * And since registered Crop Objects are temporary
         */
        if (event == Events.HARVEST_END) {
            if (payload instanceof Tile tile) {
                unregisterAll(tile.getCrop());
                tile.removeCrop();
            }
        }
    }


}

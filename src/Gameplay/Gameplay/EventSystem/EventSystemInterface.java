package Gameplay.Gameplay.EventSystem;

/**
 * DaySystemInterface
 */
public interface EventSystemInterface {
    void eventNotify(Events event, int days, Object payload);
}
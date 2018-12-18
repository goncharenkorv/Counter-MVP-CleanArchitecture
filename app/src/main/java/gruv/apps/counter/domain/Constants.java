package gruv.apps.counter.domain;

/**
 * Константы, используемые и во внутренних слоях (домен) и во внешних (активити)
 *
 * @author Goncharenko Ruslan
 */
public class Constants {

    // Перечисление возможных звуков:
    public enum Sound {
        INCREMENT_SOUND, DECREMENT_SOUND, CLEAR_SOUND
    }

    // Продолжительность вибрации в миллисекундах
    public static final long VIBRATION_CLEAR_DURATION = 100; // Milliseconds
    public static final long VIBRATION_ENCREASE_DURATION = 40; // Milliseconds
    public static final long VIBRATION_DECREASE_DURATION = 60; // Milliseconds
}

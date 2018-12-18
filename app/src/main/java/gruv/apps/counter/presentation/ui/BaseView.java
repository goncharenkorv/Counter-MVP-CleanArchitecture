package gruv.apps.counter.presentation.ui;

import android.support.annotation.NonNull;

/**
 * Этот интерфейс предстваляет базовую вью (вид). Все вью (вид) должны имплементировать этот вью (вид)
 *
 * @author Goncharenko Ruslan
 */
public interface BaseView {
    /**
     * Это общий метод для отображения (установления в текстовое поле) счетчика
     */
    void setValue(@NonNull String value);
}

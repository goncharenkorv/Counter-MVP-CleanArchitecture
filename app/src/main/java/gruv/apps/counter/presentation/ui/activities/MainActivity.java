package gruv.apps.counter.presentation.ui.activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import gruv.apps.counter.R;
import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.impl.ThreadExecutor;
import gruv.apps.counter.presentation.presenters.MainPresenter;
import gruv.apps.counter.presentation.presenters.impl.MainPresenterImpl;
import gruv.apps.counter.storage.DataValueRepository;
import gruv.apps.counter.threading.MainThreadImpl;

import butterknife.ButterKnife;

/**
 * Главная активность
 * Содержит текстовое поле (счетчик) и две кнопки (увеличение и уменьшение)
 * Умеет восопроизводить звук и вибрацию
 *
 * @author Goncharenko Ruslan
 */
public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    // текстовое поле со счетчиком:
    @BindView(R.id.counterLabel)
    TextView mTextView;

    // кнопка увеличения:
    @BindView(R.id.incrementButton)
    Button incrementButton;

    // кнопка уменьшения:
    @BindView(R.id.decrementButton)
    Button decrementButton;

    // Вибратор:
    private Vibrator vibrator;

    // Работа со звуком:
    private SoundPool soundPool;
    private SparseIntArray soundsMap;

    // Презентер:
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // кнопка увеличения:
        //incrementButton = (Button) findViewById(R.id.incrementButton);
        // устанавливаем слушателя нажатия:
        incrementButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                increment();
            }
        });

        // кнопка уменьшения:
        //decrementButton = (Button) findViewById(R.id.decrementButton);
        // устанавливаем слушателя нажатия:
        decrementButton.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                decrement();
            }
        });

        //при нажатии на счетчик (на числовое поле) сбрасываем этот счетчик в ноль:
        //mTextView = (TextView) findViewById(R.id.counterLabel);
        mTextView.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                clean();
            }
        });

        // Инициализируем вибратор
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Устанавливаем звуки
        initSound();

        // create a presenter for this view
        mPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new DataValueRepository()
        );
    }

    /**
     * Установление в текстовое поле значение счетчика
     */
    @Override
    public void setValue(@NonNull String value) {
        mTextView.setText(value);
    }

    /**
     * Установление доступности кнопки увеличения
     */
    @Override
    public void setIncrementButtonEnabled(boolean enabled) {
        incrementButton.setEnabled(enabled);
    }

    /**
     * Установление доступности кнопки уменьшения
     */
    @Override
    public void setDecrementButtonEnabled(boolean enabled) {
        decrementButton.setEnabled(enabled);
    }

    /**
     * Вибрация
     *
     * @param duration Продолжительность в миллисекундах
     */
    @Override
    public void vibrate(long duration) {
        vibrator.vibrate(duration);
    }

    /**
     * Проигрываем звук
     */
    @Override
    public void playSound(@NonNull Constants.Sound sound) {
        float volume = getVolume();
        int key = sound.ordinal();
        int soundID = soundsMap.get(key);
        soundPool.play(soundID, volume, volume, 1, 0, 1f);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // методы loadState и resume могут отработать в любой последов-ти, т.к. запускаются в разных потоках
        // поэтому в методе loadState (внутри) происходит независимый вызов update-а

        // Восстанавливаем ранее сохраненное значение счетчика
        mPresenter.loadState(this);

        // Запускаем обновление счетчика
        mPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Сохраняем текущее значение счетчика
        mPresenter.saveState(this);
    }

    /**
     * "Считываем" текущее значение громкости (для музыки)
     */
    private float getVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return (curVolume / maxVolume);
    }

    /**
     * Увеличивем значение переменной value на единицу,
     * если это возможно
     */
    private void increment() {
        mPresenter.increase();
    }

    /**
     * Уменьшаем значение переменной value на единицу,
     * если это возможно
     */
    private void decrement() {
        mPresenter.decrease();
    }

    /**
     * Сбрасываесм счетчик (выставляем значение ноль)
     */
    private void clean() {
        mPresenter.clean();
    }

    /**
     * Инициализируем проигрыватель звуков
     */
    private void initSound() {
        // Устанавливаем звуки
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundsMap = new SparseIntArray(2);
        soundsMap.put(Constants.Sound.INCREMENT_SOUND.ordinal(), soundPool.load(this, R.raw.increment_sound, 1));
        soundsMap.put(Constants.Sound.DECREMENT_SOUND.ordinal(), soundPool.load(this, R.raw.decrement_sound, 1));
        soundsMap.put(Constants.Sound.CLEAR_SOUND.ordinal(), soundPool.load(this, R.raw.clear_sound, 1));
    }
}
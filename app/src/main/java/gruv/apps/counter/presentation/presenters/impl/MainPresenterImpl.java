package gruv.apps.counter.presentation.presenters.impl;

import android.content.Context;

import gruv.apps.counter.domain.Constants;
import gruv.apps.counter.domain.executor.Executor;
import gruv.apps.counter.domain.executor.MainThread;
import gruv.apps.counter.domain.interactors.DecrementButtonEnabledInteractor;
import gruv.apps.counter.domain.interactors.IncrementButtonEnabledInteractor;
import gruv.apps.counter.domain.interactors.SoundVibrateInteractor;
import gruv.apps.counter.domain.interactors.ValueUpdaterInteractor;
import gruv.apps.counter.domain.interactors.impl.CleanValueInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.DecrementButtonEnabledInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.IncrementButtonEnabledInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.ValueDecreaserInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.ValueIncreaserInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.ValueLoadInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.ValueSaveInteractorImpl;
import gruv.apps.counter.domain.interactors.impl.ValueUpdaterInteractorImpl;
import gruv.apps.counter.domain.model.DomainModel;
import gruv.apps.counter.domain.repository.Repository;
import gruv.apps.counter.presentation.presenters.base.AbstractPresenter;
import gruv.apps.counter.presentation.presenters.MainPresenter;
import gruv.apps.counter.presentation.ui.activities.MainActivity;

/**
 * Реализация (имплементация) главного презентера
 *
 * @author Goncharenko Ruslan
 */
public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        ValueUpdaterInteractor.Callback,
        IncrementButtonEnabledInteractor.Callback,
        DecrementButtonEnabledInteractor.Callback,
        SoundVibrateInteractor.Callback {

    private MainPresenter.View mView;
    private Repository mRepository;

    public MainPresenterImpl(Executor executor,
                             MainThread mainThread,
                             View view,
                             Repository repository) {
        super(executor, mainThread);
        mView = view;
        mRepository = repository;
    }

    @Override
    public void resume() {
        update();
    }

    @Override
    public void saveState(Context context) {
        // инициализация интерактора
        ValueSaveInteractorImpl interactor = new ValueSaveInteractorImpl(
                mExecutor,
                mMainThread,
                //this,
                mRepository,
                context
        );

        // запуск интерактора
        interactor.execute();
    }

    @Override
    public void loadState(Context context) {
        // инициализация интерактора
        ValueLoadInteractorImpl interactor = new ValueLoadInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mRepository,
                context
        );

        // запуск интерактора
        interactor.execute();

    }

    @Override
    public void increase() {
        // инициализация интерактора
        ValueIncreaserInteractorImpl interactor = new ValueIncreaserInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                this,
                mRepository
        );

        // запуск интерактора
        interactor.execute();
    }

    @Override
    public void decrease() {
        // инициализация интерактора
        ValueDecreaserInteractorImpl interactor = new ValueDecreaserInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                this,
                mRepository
        );

        // запуск интерактора
        interactor.execute();
    }

    @Override
    public void clean() {
        // инициализация интерактора
        CleanValueInteractorImpl interactor = new CleanValueInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                this,
                mRepository
        );

        // запуск интерактора
        interactor.execute();
    }

    @Override
    public void pause() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void onError(String message) {
    }

    @Override
    public void onValueUpdate(DomainModel domainModel) {
        mView.setValue(domainModel.getValue());
    }

    @Override
    public void onIncrementButtonEnabled(boolean value) {
        mView.setIncrementButtonEnabled(value);
    }

    @Override
    public void onDecrementButtonEnabled(boolean value) {
        mView.setDecrementButtonEnabled(value);
    }

    @Override
    public void vibrate(long duration) {
        mView.vibrate(duration);
    }

    @Override
    public void playSound(Constants.Sound sound) {
        mView.playSound(sound);
    }

    private void update() {
        // Обновляем значение счетчика
        updateValue();

        // Обновляем доступность кнопок:
        updateIncrementButtonEnabled();
        updateDecrementButtonEnabled();
    }

    private void updateValue() {
        // инициализация интерактора
        ValueUpdaterInteractorImpl interactor = new ValueUpdaterInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mRepository
        );

        // запуск интерактора
        interactor.execute();
    }

    private void updateIncrementButtonEnabled() {
        // инициализация интерактора
        IncrementButtonEnabledInteractorImpl interactor = new IncrementButtonEnabledInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mRepository
        );

        // запуск интерактора
        interactor.execute();
    }

    private void updateDecrementButtonEnabled() {
        // инициализация интерактора
        DecrementButtonEnabledInteractorImpl interactor = new DecrementButtonEnabledInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mRepository
        );

        // запуск интерактора
        interactor.execute();
    }
}

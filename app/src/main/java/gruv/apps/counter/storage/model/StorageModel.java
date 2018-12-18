package gruv.apps.counter.storage.model;

/**
 * Простая модель для хранилища (верхний уровень)
 *
 * @author Goncharenko Ruslan
 */
public class StorageModel {

    private int mValue;

    public StorageModel(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    /**
     * Переопределяем метод equals
     * Это, в частности, необходимо для теста ValueUpdaterInteractorTest.onValueUpdateTest()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StorageModel storageModel = (StorageModel) o;
        return storageModel.mValue == mValue;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + '@' + "mValue=" + String.valueOf(mValue);
    }
}

package mitso.v.homework_17.api.interfaces;

public interface ConnectCallback {

    void onSuccess(Object object);
    void onFailure(Throwable throwable);
}

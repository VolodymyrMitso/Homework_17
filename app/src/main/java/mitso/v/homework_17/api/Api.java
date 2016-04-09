package mitso.v.homework_17.api;

import com.loopj.android.http.RequestParams;

import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.response.TodoListResponse;
import mitso.v.homework_17.api.response.UserListResponse;

public class Api {


    public static void getUsers(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConstants.USERS, new UserListResponse(), callback);
    }

//    public static void getUser(int id, ConnectCallback callback) {
//        Connect.getInstance().getRequest(ApiConstants.USER_ID + id, new User(), callback);
//    }

    public static void getTodosByUser(int userId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConstants.TODO_USER_ID_KEY, userId);
        Connect.getInstance().getRequestWithParam(ApiConstants.TODOS, requestParams, new TodoListResponse(), callback);
    }
}

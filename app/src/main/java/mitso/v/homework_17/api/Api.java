package mitso.v.homework_17.api;

import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.User;
import mitso.v.homework_17.api.response.UserListResponse;

public class Api {


    public static void getUsers(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConstants.USERS, new UserListResponse(), callback);
    }

    public static void getUser(int id, ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConstants.USER_ID + id, new User(), callback);
    }
//
//    public static void getPost(int userId, ConnectCallback callback) {
//        RequestParams requestParams = new RequestParams(ApiConst.USER_ID_KEY, userId);
//        Connect.getInstance().getRequestWithParam(ApiConst.POSTS, requestParams, new PostListResponse(), callback);
//    }
}

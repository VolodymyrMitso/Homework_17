package mitso.v.homework_17.api;

import com.loopj.android.http.RequestParams;

import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.response.AlbumListResponse;
import mitso.v.homework_17.api.response.CommentListResponse;
import mitso.v.homework_17.api.response.PhotoListResponse;
import mitso.v.homework_17.api.response.PostListResponse;
import mitso.v.homework_17.api.response.TodoListResponse;
import mitso.v.homework_17.api.response.UserListResponse;

public class Api {


    public static void getUsers(ConnectCallback callback) {
        Connect.getInstance().getRequest(ApiConstants.USERS, new UserListResponse(), callback);
    }

    public static void getTodosByUser(int userId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConstants.TODO_USER_ID_KEY, userId);
        Connect.getInstance().getRequestWithParam(ApiConstants.TODOS, requestParams, new TodoListResponse(), callback);
    }

    public static void getAlbumsByUser(int userId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConstants.ALBUM_USER_ID_KEY, userId);
        Connect.getInstance().getRequestWithParam(ApiConstants.ALBUMS, requestParams, new AlbumListResponse(), callback);
    }

    public static void getPhotosByAlbum(int albumId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConstants.PHOTO_ALBUM_ID_KEY, albumId);
        Connect.getInstance().getRequestWithParam(ApiConstants.PHOTOS, requestParams, new PhotoListResponse(), callback);
    }

    public static void getPostsByUser(int userId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConstants.POST_USER_ID_KEY, userId);
        Connect.getInstance().getRequestWithParam(ApiConstants.POSTS, requestParams, new PostListResponse(), callback);
    }

    public static void getCommentsByPost(int postId, ConnectCallback callback) {
        RequestParams requestParams = new RequestParams(ApiConstants.COMMENT_POST_ID_KEY, postId);
        Connect.getInstance().getRequestWithParam(ApiConstants.COMMENTS, requestParams, new CommentListResponse(), callback);
    }
}

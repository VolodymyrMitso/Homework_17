package mitso.v.homework_17.api;

public class ApiConstants {

    public static final String URL_SERVER                   = "http://jsonplaceholder.typicode.com";

////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String USERS                        = "/users";
    public static final String USER_ID                      = "/users/";

    public static final String USER_ID_KEY                  = "id";
    public static final String USER_NAME_KEY                = "name";
    public static final String USER_USERNAME_KEY            = "username";
    public static final String USER_EMAIL_KEY               = "email";
    public static final String USER_ADDRESS_KEY             = "address";

    public static final String ADDRESS_STREET_KEY           = "street";
    public static final String ADDRESS_SUITE_KEY            = "suite";
    public static final String ADDRESS_CITY_KEY             = "city";
    public static final String ADDRESS_ZIPCODE_KEY          = "zipcode";
    public static final String ADDRESS_GEO_KEY              = "geo";

    public static final String GEO_LAT_KEY                  = "lat";
    public static final String GEO_LNG_KEY                  = "lng";

    public static final String USER_PHONE_KEY               = "phone";
    public static final String USER_WEBSITE_KEY             = "website";
    public static final String USER_COMPANY_KEY             = "company";

    public static final String COMPANY_NAME_KEY             = "name";
    public static final String COMPANY_CATCH_PHRASE_KEY     = "catchPhrase";
    public static final String COMPANY_BS_KEY               = "bs";

////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String TODOS                        = "/todos";
    public static final String TODO_ID                      = "/todos/";

    public static final String TODO_USER_ID_KEY             = "userId";
    public static final String TODO_ID_KEY                  = "id";
    public static final String TODO_TITLE_KEY               = "title";
    public static final String TODO_COMPLETED_KEY           = "completed";

////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String ALBUMS                       = "/albums";
    public static final String ALBUM_ID                     = "/albums/";

    public static final String ALBUM_USER_ID_KEY            = "userId";
    public static final String ALBUM_ID_KEY                 = "id";
    public static final String ALBUM_TITLE_KEY              = "title";

////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String PHOTOS                       = "/photos";
    public static final String PHOTO_ID                     = "/photos/";

    public static final String PHOTO_ALBUM_ID_KEY           = "albumId";
    public static final String PHOTO_ID_KEY                 = "id";
    public static final String PHOTO_TITLE_KEY              = "title";
    public static final String PHOTO_URL_KEY                = "url";
    public static final String PHOTO_THUMBNAIL_URL_KEY      = "thumbnailUrl";

////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String POSTS                        = "/posts";
    public static final String POST_ID                      = "/posts/";

    public static final String POST_USER_ID_KEY             = "userId";
    public static final String POST_ID_KEY                  = "id";
    public static final String POST_TITLE_KEY               = "title";
    public static final String POST_BODY_KEY                = "body";

////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String COMMENTS                     = "/comments";
    public static final String COMMENT_ID                   = "/comments/";

    public static final String COMMENT_POST_ID_KEY          = "postId";
    public static final String COMMENT_ID_KEY               = "id";
    public static final String COMMENT_NAME_KEY             = "name";
    public static final String COMMENT_EMAIL_KEY            = "email";
    public static final String COMMENT_BODY_KEY             = "body";

}
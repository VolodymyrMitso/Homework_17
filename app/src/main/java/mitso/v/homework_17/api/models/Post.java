package mitso.v.homework_17.api.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

import mitso.v.homework_17.api.ApiConstants;
import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.GsonModel;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class Post implements ModelResponse, Serializable, GsonModel {

    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.has(ApiConstants.POST_USER_ID_KEY) && !jsonObject.isNull(ApiConstants.POST_USER_ID_KEY))
                    userId = jsonObject.getInt(ApiConstants.POST_USER_ID_KEY);

                if (jsonObject.has(ApiConstants.POST_ID_KEY) && !jsonObject.isNull(ApiConstants.POST_ID_KEY))
                    id = jsonObject.getInt(ApiConstants.POST_ID_KEY);

                if (jsonObject.has(ApiConstants.POST_TITLE_KEY) && !jsonObject.isNull(ApiConstants.POST_TITLE_KEY))
                    title = jsonObject.getString(ApiConstants.POST_TITLE_KEY);

                if (jsonObject.has(ApiConstants.POST_BODY_KEY) && !jsonObject.isNull(ApiConstants.POST_BODY_KEY))
                    body = jsonObject.getString(ApiConstants.POST_BODY_KEY);
        }
    }

    @Override
    public void updateByClass(Object object) {
        Post post = (Post) object;

        this.userId = post.userId;
        this.id = post.id;
        this.title = post.title;
        this.body = post.body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return  "----- POST INFO -----\n" +
                "----- userId = " + userId + "\n" +
                "----- id = " + id + "\n" +
                "----- title = " + title + "\n" +
                "----- body = " + body;
    }
}
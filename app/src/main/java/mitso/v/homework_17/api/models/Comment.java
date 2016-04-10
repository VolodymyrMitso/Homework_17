package mitso.v.homework_17.api.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

import mitso.v.homework_17.api.ApiConstants;
import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.GsonModel;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class Comment implements ModelResponse, Serializable, GsonModel {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.has(ApiConstants.COMMENT_POST_ID_KEY) && !jsonObject.isNull(ApiConstants.COMMENT_POST_ID_KEY))
                    postId = jsonObject.getInt(ApiConstants.COMMENT_POST_ID_KEY);

                if (jsonObject.has(ApiConstants.COMMENT_ID_KEY) && !jsonObject.isNull(ApiConstants.COMMENT_ID_KEY))
                    id = jsonObject.getInt(ApiConstants.COMMENT_ID_KEY);

                if (jsonObject.has(ApiConstants.COMMENT_NAME_KEY) && !jsonObject.isNull(ApiConstants.COMMENT_NAME_KEY))
                    name = jsonObject.getString(ApiConstants.COMMENT_NAME_KEY);

                if (jsonObject.has(ApiConstants.COMMENT_EMAIL_KEY) && !jsonObject.isNull(ApiConstants.COMMENT_EMAIL_KEY))
                    email = jsonObject.getString(ApiConstants.COMMENT_EMAIL_KEY);

                if (jsonObject.has(ApiConstants.COMMENT_BODY_KEY) && !jsonObject.isNull(ApiConstants.COMMENT_BODY_KEY))
                    body = jsonObject.getString(ApiConstants.COMMENT_BODY_KEY);
        }
    }

    @Override
    public void updateByClass(Object object) {
        Comment comment = (Comment) object;

        this.postId = comment.postId;
        this.id = comment.id;
        this.name = comment.name;
        this.email = comment.email;
        this.body = comment.body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return  "----- COMMENT INFO -----\n" +
                "----- postId = " + postId + "\n" +
                "----- id = " + id + "\n" +
                "----- name = " + name + "\n" +
                "----- email = " + email + "\n" +
                "----- body = " + body;
    }
}

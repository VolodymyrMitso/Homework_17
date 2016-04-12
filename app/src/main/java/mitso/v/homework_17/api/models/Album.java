package mitso.v.homework_17.api.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

import mitso.v.homework_17.api.ApiConstants;
import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.GsonModel;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class Album implements ModelResponse, Serializable, GsonModel {

    private int userId;
    private int id;
    private String title;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.has(ApiConstants.ALBUM_USER_ID_KEY) && !jsonObject.isNull(ApiConstants.ALBUM_USER_ID_KEY))
                    userId = jsonObject.getInt(ApiConstants.ALBUM_USER_ID_KEY);

                if (jsonObject.has(ApiConstants.ALBUM_ID_KEY) && !jsonObject.isNull(ApiConstants.ALBUM_ID_KEY))
                    id = jsonObject.getInt(ApiConstants.ALBUM_ID_KEY);

                if (jsonObject.has(ApiConstants.ALBUM_TITLE_KEY) && !jsonObject.isNull(ApiConstants.ALBUM_TITLE_KEY))
                    title = jsonObject.getString(ApiConstants.ALBUM_TITLE_KEY);
        }
    }

    @Override
    public void updateByClass(Object object) {
        Album album = (Album) object;

        this.userId = album.userId;
        this.id = album.id;
        this.title = album.title;
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

    @Override
    public String toString() {
        return  "----- ALBUM INFO -----\n" +
                "----- userId = " + userId + "\n" +
                "----- id = " + id + "\n" +
                "----- title = " + title;
    }
}

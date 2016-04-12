package mitso.v.homework_17.api.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;

import mitso.v.homework_17.api.ApiConstants;
import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.GsonModel;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class Photo implements ModelResponse, Serializable, GsonModel {

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();
        switch (parser) {
            case Connect.PARSER_JSON:
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.has(ApiConstants.PHOTO_ALBUM_ID_KEY) && !jsonObject.isNull(ApiConstants.PHOTO_ALBUM_ID_KEY))
                    albumId = jsonObject.getInt(ApiConstants.PHOTO_ALBUM_ID_KEY);

                if (jsonObject.has(ApiConstants.PHOTO_ID_KEY) && !jsonObject.isNull(ApiConstants.PHOTO_ID_KEY))
                    id = jsonObject.getInt(ApiConstants.PHOTO_ID_KEY);

                if (jsonObject.has(ApiConstants.PHOTO_TITLE_KEY) && !jsonObject.isNull(ApiConstants.PHOTO_TITLE_KEY))
                    title = jsonObject.getString(ApiConstants.PHOTO_TITLE_KEY);

                if (jsonObject.has(ApiConstants.PHOTO_URL_KEY) && !jsonObject.isNull(ApiConstants.PHOTO_URL_KEY))
                    url = jsonObject.getString(ApiConstants.PHOTO_URL_KEY);

                if (jsonObject.has(ApiConstants.PHOTO_THUMBNAIL_URL_KEY) && !jsonObject.isNull(ApiConstants.PHOTO_THUMBNAIL_URL_KEY))
                    thumbnailUrl = jsonObject.getString(ApiConstants.PHOTO_THUMBNAIL_URL_KEY);
        }
    }

    @Override
    public void updateByClass(Object object) {
        Photo photo = (Photo) object;

        this.albumId = photo.albumId;
        this.id = photo.id;
        this.title = photo.title;
        this.url = photo.url;
        this.thumbnailUrl = photo.thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return  "----- PHOTO INFO -----\n" +
                "----- albumId = " + albumId + "\n" +
                "----- id = " + id + "\n" +
                "----- title = " + title + "\n" +
                "----- url = " + url + "\n" +
                "----- thumbnailUrl = " + thumbnailUrl;

    }
}

package mitso.v.homework_17.api.response;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.ModelResponse;
import mitso.v.homework_17.api.models.Photo;

public class PhotoListResponse implements ModelResponse {
    private ArrayList<Photo> photos;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                photos = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    Photo photo = new Photo();
                    photo.configure(results.getJSONObject(i));
                    photos.add(photo);
                }
                break;
        }
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }
}
package mitso.v.homework_17.api.response;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.ModelResponse;
import mitso.v.homework_17.api.models.Album;

public class AlbumListResponse implements ModelResponse {
    private ArrayList<Album> albums;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                albums = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    Album album = new Album();
                    album.configure(results.getJSONObject(i));
                    albums.add(album);
                }
                break;
        }
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}

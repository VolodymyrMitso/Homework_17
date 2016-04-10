package mitso.v.homework_17.api.response;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.ModelResponse;
import mitso.v.homework_17.api.models.Post;

public class PostListResponse implements ModelResponse {
    private ArrayList<Post> posts;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                posts = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    Post post = new Post();
                    post.configure(results.getJSONObject(i));
                    posts.add(post);
                }
                break;
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
package mitso.v.homework_17.api.response;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.ModelResponse;
import mitso.v.homework_17.api.models.Comment;

public class CommentListResponse implements ModelResponse {
    private ArrayList<Comment> comments;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                comments = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    Comment comment = new Comment();
                    comment.configure(results.getJSONObject(i));
                    comments.add(comment);
                }
                break;
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}

package mitso.v.homework_17.api.response;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import mitso.v.homework_17.api.Connect;
import mitso.v.homework_17.api.interfaces.ModelResponse;

public class TodoListResponse implements ModelResponse {
    private ArrayList<Todo> todos;

    @Override
    public void configure(Object object) throws JSONException, ParseException {
        int parser = Connect.getInstance().getParser();

        JSONArray results = (JSONArray) object;
        switch (parser) {
            case Connect.PARSER_JSON:
                todos = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    Todo todo = new Todo();
                    todo.configure(results.getJSONObject(i));
                    todos.add(todo);
                }
                break;
        }
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public List<Content> parse(String json) {
        List<Content> content = new ArrayList<>();

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray items = jsonObject.getAsJsonArray("items");

        for(JsonElement item: items) {
            String title = item.getAsJsonObject().get("title").getAsString();
            String imageUrl = item.getAsJsonObject().get("image").getAsString();
            content.add(new Content(title, imageUrl));
        }
        return content;
    }
}

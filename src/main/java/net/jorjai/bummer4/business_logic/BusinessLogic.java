package net.jorjai.bummer4.business_logic;

import com.google.gson.Gson;
import net.jorjai.bummer4.domain.Image;
import net.jorjai.bummer4.domain.SearchResponse;
import net.jorjai.bummer4.domain.Tag;
import net.jorjai.bummer4.domain.TagResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BusinessLogic implements BlInterface {

    String url = "https://api.waifu.im";

    public BusinessLogic() {
    }

    private TagResponse getTagResponse() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url + "/tags?full=true")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Process the response
            String responseData = response.body().string();
            System.out.println(responseData);

            Gson gson = new Gson();
            TagResponse tagResponse = gson.fromJson(responseData, TagResponse.class);

            return tagResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tag> getVersatileTags() {
        TagResponse tagResponse = getTagResponse();
        if (tagResponse == null) return new ArrayList<>();
        return tagResponse.getVersatile();
    }

    @Override
    public List<Tag> getNsfwTags() {
        TagResponse tagResponse = getTagResponse();
        if (tagResponse == null) return new ArrayList<>();
        return tagResponse.getNsfw();
    }

    @Override
    public List<Image> search(Tag tag) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url + "/search?tag=" + tag.getName())
                .build();

        // Add searchQuery parameters
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Process the response
            String responseData = response.body().string();
            System.out.println(responseData);

            // Deserialize the response
            Gson gson = new Gson();
            SearchResponse searchResponse = gson.fromJson(responseData, SearchResponse.class);

            return searchResponse.getImages();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
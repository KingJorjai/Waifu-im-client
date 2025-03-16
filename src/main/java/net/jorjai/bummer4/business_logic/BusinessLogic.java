package net.jorjai.bummer4.business_logic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
        List<Tag> tags = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url + "/tags?full=true")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Process the response
            String responseData = response.body().string();
            System.out.println(responseData);

            // Access "versatile" object using gson
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
}
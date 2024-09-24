package com.ecom.flipdeal.utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCaller {
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder().build();

    private static String makeHttpCall(Request request) throws IOException {
        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }

            // Handle potential null body
            if (response.body() == null) {
                throw new IOException("Response body is null");
            }

            return response.body().string();
        }
    }

    private static Request.Builder makeBuilderWith(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder().url(url);

        if (headers != null && !headers.isEmpty()) {
            builder.headers(Headers.of(headers));
        }

        return builder;
    }

    public static String get(String url, Map<String, String> headers) throws IOException {
        Request request = makeBuilderWith(url, headers)
                .get()
                .build();
        return makeHttpCall(request);
    }
}

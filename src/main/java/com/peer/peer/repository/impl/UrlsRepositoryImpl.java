package com.peer.peer.repository.impl;

import com.peer.peer.model.ResponseModel;
import com.peer.peer.repository.UrlsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
@Repository
public class UrlsRepositoryImpl implements UrlsRepository {
    private String https = "https://";
    private String http = "http://";
    private String www = "wwww";

    @Override
    public ResponseModel getTextFromUrl(String url) {
        ResponseModel responseModel;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements textArticle = doc.select("p");
            responseModel = ResponseModel.builder()
                    .url(url)
                    .urlText(textArticle.text())
                    .build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad url" + url);
        }
        return responseModel;
    }

    @Override
    public List<ResponseModel> getTextFromUrls(String url) {
        List<ResponseModel> responseModels = new ArrayList<>();

        return responseModels;
    }

}

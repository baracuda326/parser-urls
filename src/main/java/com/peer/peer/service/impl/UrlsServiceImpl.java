package com.peer.peer.service.impl;

import com.peer.peer.model.CategoryKeyWord;
import com.peer.peer.model.CategoryModel;
import com.peer.peer.model.ResponseFullModel;
import com.peer.peer.model.ResponseModel;
import com.peer.peer.repository.UrlsRepository;
import com.peer.peer.service.UrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
@Service
public class UrlsServiceImpl implements UrlsService {
    private final UrlsRepository urlsRepository;
    private Map<String, List<String>> data;

    @Autowired
    public UrlsServiceImpl(UrlsRepository urlsRepository) {
        this.urlsRepository = urlsRepository;
    }

    @Override
    /**
     * @param urls List<String>
     * @return Iterable<ResponseModel>
     * @throws IllegalArgumentException
     */
    public Iterable<ResponseModel> getTextFromUrl(List<String> urls) {
        if (urls.isEmpty()) throw new IllegalArgumentException("Urls not found or null!");
        List<ResponseModel> responseModels = new ArrayList<>();
        for (String url : urls) {
            responseModels.add(urlsRepository.getTextFromUrl(url));
        }
        return responseModels;
    }

    @Override
    /**
     * @param url String
     * @return List<ResponseModel>
     * @throws IllegalArgumentException
     */
    public List<ResponseFullModel> getKeyFromUrl(String url) {
        if (url.isEmpty()) throw new IllegalArgumentException("Urls not found or null!");
        List<ResponseModel> lists = urlsRepository.getTextFromUrls(url);
        List<ResponseFullModel> listRes = new ArrayList<>();
        for (ResponseModel responseModel : lists) {
            Set<String> keyWords = new HashSet<>();
            for (Map.Entry<String, List<String>> entry : data.entrySet()) {
                List<String> list = entry.getValue();
                for (String value : list) {
                    String text = responseModel.getUrlText().toLowerCase();
                    if (text.contains(value.toLowerCase())) {
                        keyWords.add(value);
                        listRes.add(createResponse(responseModel, entry.getKey(), keyWords));
                    }
                }
            }
        }
        return listRes;
    }

    private ResponseFullModel createResponse(ResponseModel responseModel, String key, Set<String> keyWords) {
        return ResponseFullModel.builder()
                .response(responseModel)
                .category(CategoryModel.builder()
                        .categoryName(key)
                        .keyWords(CategoryKeyWord.builder()
                                .keyWord(keyWords)
                                .build())
                        .build())
                .build();
    }

    @PostConstruct
    void init() {
        List<String> listStarWars = new ArrayList<>(Arrays.asList("star wars", "Star wars", "starwars", "k84"));
        List<String> listBasketball = new ArrayList<>(Arrays.asList("basketball", "nba", "ncaa", "lebron james", "john stokton",
                "anthony davis"));
        data = new HashMap<>();
        data.put("Star Wars", listStarWars);
        data.put("Basketball", listBasketball);
    }
}

package com.peer.peer.service;

import com.peer.peer.model.ResponseFullModel;
import com.peer.peer.model.ResponseModel;

import java.util.List;

public interface UrlsService {
    Iterable<ResponseModel> getTextFromUrl(List<String> urls);

    List<ResponseFullModel> getKeyFromUrl(String url);
}

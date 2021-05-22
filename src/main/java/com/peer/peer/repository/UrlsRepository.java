package com.peer.peer.repository;

import com.peer.peer.model.ResponseModel;

import java.util.List;

public interface UrlsRepository {
    ResponseModel getTextFromUrl(String urls);

    List<ResponseModel> getTextFromUrls(String url);
}

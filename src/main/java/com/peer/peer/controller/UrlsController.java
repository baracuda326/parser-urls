package com.peer.peer.controller;

import com.peer.peer.model.ResponseFullModel;
import com.peer.peer.model.ResponseModel;
import com.peer.peer.service.UrlsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UrlsController {
    private final UrlsService urlsService;

    @Autowired
    public UrlsController(UrlsService urlsService) {
        this.urlsService = urlsService;
    }

    @ApiOperation(value = "Urls parser 1.0")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseModel[].class),
            @ApiResponse(code = 400, message = "Error! Bad request"),
    })
    @PostMapping("get/")
    public Iterable<ResponseModel> getTextFromUrl(@RequestBody List<String> urls) {
        return urlsService.getTextFromUrl(urls);
    }

    @ApiOperation(value = "Urls parser 1.0")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseModel[].class),
            @ApiResponse(code = 400, message = "Error! Bad request"),
    })
    @GetMapping("get/key/{url}")
    public List<ResponseFullModel> getTextFromUrl(@RequestParam(value = "url") String url) {
        return urlsService.getKeyFromUrl(url);
    }
}

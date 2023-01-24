package com.example.art_stationary.Utils.Handler;

import com.example.art_stationary.Model.Categories.Example;

public interface GetCategoriesHandler {
    public void onSuccess(Example example);
    public void onError(String message);
}

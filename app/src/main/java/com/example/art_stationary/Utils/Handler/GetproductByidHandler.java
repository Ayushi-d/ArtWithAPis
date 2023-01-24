package com.example.art_stationary.Utils.Handler;

import com.example.art_stationary.Model.Categories.Example;
import com.example.art_stationary.Model.GetproductbyidModel.GetproductbyidExample;

public interface GetproductByidHandler {
    public void onSuccess(GetproductbyidExample example);
    public void onError(String message);
}

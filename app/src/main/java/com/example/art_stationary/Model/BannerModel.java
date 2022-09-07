package com.example.art_stationary.Model;

public class BannerModel {


        private String bannerImage;

        public BannerModel(String bannerImage) {
            this.bannerImage = bannerImage;
        }

    public BannerModel() {

    }


    public String getImgid() {
            return bannerImage;
        }

        public void setImgid(String bannerImage) {
            this.bannerImage = bannerImage;
        }


}

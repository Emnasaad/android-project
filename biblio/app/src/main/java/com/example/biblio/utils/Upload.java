package com.example.biblio.utils;

import android.net.Uri;

public class Upload {
   String title;
   String uriImage;

    public Upload(String title, String uriImage) {
        this.title = title;
        this.uriImage = uriImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }
}

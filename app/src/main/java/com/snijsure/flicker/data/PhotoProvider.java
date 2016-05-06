package com.snijsure.flicker.data;

import com.snijsure.flicker.model.FlickerData;
import com.snijsure.flicker.model.Photo;

import java.util.Arrays;
import java.util.List;
/**
 * Created by subodhnijsure on 5/4/16.
 */
// This is class that provides dummy data for UX testing
public class PhotoProvider {
    public List<Photo> getPhotoData() {
        return Arrays.asList(
                new Photo("Title1", "https://farm2.staticflickr.com/1702/24815584109_56a4c5467a.jpg"),
                new Photo("Title2", "https://farm2.staticflickr.com/1702/24815584109_56a4c5467a.jpg")
                );
    }
}

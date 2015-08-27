package abk.model;

import android.graphics.Bitmap;

/**
 * Created by edgar on 24/08/15.
 */
public class Category {
    private Long identifier;
    private String name;
    private Bitmap image;

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

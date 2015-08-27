package abk.utilities.adapter;

import abk.activities.R;
import abk.model.Category;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pedreduardo on 22/08/2015.
 */
public class CategorieAdpt extends BaseAdapter {

    //Attributes
    //--------------------------------------------
    private Context context;
    private final List<Category> categories;
    private View gridView;
    //--------------------------------------------

    public CategorieAdpt(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        this.gridView = new View(context);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // get layout from categories_grid.xml
            this.gridView = inflater.inflate(R.layout.categories_grid, null);
            // set value into textview

            setLabelToCategory(position);
            setImageToCategory();
        } else {
            this.gridView = convertView;
        }
        return gridView;
    }

    private void setLabelToCategory(int position) {
        TextView lblCategory = (TextView) gridView.findViewById(R.id.lblCategorie);
        lblCategory.setText(categories.get(position).getName());
    }

    private void setImageToCategory() {
        ImageView imgCategory = (ImageView) gridView.findViewById(R.id.imgCategory);
        Bitmap imgCategoryNormal = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.random_objects);

        final int maxSize = 250;
        int outWidth;
        int outHeight;
        int inWidth = imgCategoryNormal.getWidth();
        int inHeight = imgCategoryNormal.getHeight();
        if (inWidth > inHeight) {
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        Bitmap imgCategoryResized = Bitmap.createScaledBitmap(imgCategoryNormal, outWidth, outHeight, true);

        imgCategory.setImageBitmap(imgCategoryResized);
        imgCategory.setColorFilter(Color.rgb(170, 170, 170), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public int getCount() {
        return this.categories.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

package abk.utilities.adapter;

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

import java.util.ArrayList;
import java.util.List;

import abk.activities.R;
import abk.model.Book;

/**
 * Created by Pedreduardo on 22/08/2015.
 */
public class BookListAdpt extends BaseAdapter {

    //Attributes
    //--------------------------------------------
    private Context context;
    private final List<Book> books;
    private View listView;
    //--------------------------------------------

    public BookListAdpt(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        this.listView = new View(context);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // get layout from categories_grid.xml
            this.listView = inflater.inflate(R.layout.books_list, null);
            // set value into textview

            setLabelToBook(position);
            setImageToBook(position);
            setAuthorToBook(position);
            setDescriptionToBook(position);
        }
        else{
            this.listView = convertView;
        }
        return listView;
    }

    private void setDescriptionToBook(int position) {
        TextView authorDescription = (TextView) listView.findViewById(R.id.txtBookDescription);
        //authorBook.setText(books.get(position).);
    }

    private void setAuthorToBook(int position){
        TextView authorBook = (TextView) listView.findViewById(R.id.txtBookAuthor);
        //authorBook.setText(books.get(position).);
    }

    private void setLabelToBook(int position) {
        TextView lblBook = (TextView) listView.findViewById(R.id.txtBookName);
        lblBook.setText(books.get(position).getName());
    }

    private void setImageToBook(int position) {
        ImageView imgBook = (ImageView) listView.findViewById(R.id.imgBook);
        Bitmap imgBookNormal = books.get(position).getImage();


        final int maxSize = 200;
        int outWidth;
        int outHeight;
        int inWidth = imgBookNormal.getWidth();
        int inHeight = imgBookNormal.getHeight();
        if(inWidth > inHeight){
            outWidth = maxSize;
            outHeight = (inHeight * maxSize) / inWidth;
        } else {
            outHeight = maxSize;
            outWidth = (inWidth * maxSize) / inHeight;
        }

        Bitmap imgCategoryResized = Bitmap.createScaledBitmap(imgBookNormal, outWidth, outHeight, true);

        imgBook.setImageBitmap(imgCategoryResized);
        imgBook.setColorFilter(Color.rgb(170, 170, 170), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public int getCount() {
        return this.books.size();
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

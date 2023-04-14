package com.example.nicolas.ccontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nicolas.ccontrol.add_delete.DeleteCatActivity;
import com.example.nicolas.ccontrol.data_base_control.ControlBD;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

import java.io.File;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    Button getMyImeg;
    //Классы
    ControlBD bdcon = new ControlBD();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int id;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getIntExtra("idTransa",23);

        imageView = (ImageView) findViewById(R.id.imageView);
        getMyImeg = (Button) findViewById(R.id.getMyImg);
        getMyImeg.setOnClickListener(this);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        mCurrentPhotoPath = null;
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_IMAGE, null, "transaction_id = " + id, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int transImg = cursor.getColumnIndex(DatabaseHelper.IMG_COLUM3);
            do{
                mCurrentPhotoPath = cursor.getString(transImg);
            }while (cursor.moveToNext());
        }else mCurrentPhotoPath = null;

        if(mCurrentPhotoPath!=null){
            getMyImeg.setText("Изменить");
            galleryAddPic();
            setPic(mCurrentPhotoPath);
        }
        mSqLiteDatabase.close();
    }

    private File createImageFile() throws IOException {
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        // Create an image file name
        String imageFileName = "imgtransa" + id;
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        if(mCurrentPhotoPath == null){
            mCurrentPhotoPath =  image.getAbsolutePath();
            bdcon.addImg(mSqLiteDatabase, id, mCurrentPhotoPath, "");}
        else{
            mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
            mCurrentPhotoPath = image.getAbsolutePath();
            ContentValues valuese = new ContentValues();
            valuese.put(DatabaseHelper.IMG_COLUM3, mCurrentPhotoPath.toString());
            mSqLiteDatabase.update(mDatabaseHelper.TABLE_IMAGE, valuese, "transaction_id = " + id, null);
        }
        return image;
    }

    @Override
    public void onClick(View v) {
        getMyImeg.setText("Изменить");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic(mCurrentPhotoPath);
            galleryAddPic();
        }
    }

    private void setPic(String Path) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(Path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW/350, photoH/350);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(Path,bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

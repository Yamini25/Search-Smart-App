package com.example.trial;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context ctx;
private Integer image_id[] = { R.drawable.amazon, R.drawable.android, R.drawable.coursera, R.drawable.drive,
		R.drawable.ebay,R.drawable.fb,R.drawable.engg,R.drawable.github,
		R.drawable.insta, R.drawable.linkedin,R.drawable.wordpress,R.drawable.event,R.drawable.overflow,
		R.drawable.quora, R.drawable.slideshare, R.drawable.wiki,R.drawable.knw, R.drawable.ieee};
     public ImageAdapter(Context ctx) {
		// TODO Auto-generated constructor stub
    	 this.ctx=ctx;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return image_id.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub\
		ImageView img;
		if(arg1 == null){
			img= new ImageView(ctx);
			img.setLayoutParams(new GridView.LayoutParams(160,160));
			img.setScaleType(ImageView.ScaleType.CENTER_CROP);
			img.setPadding(8, 8, 8, 8);
		}
		else
		{
			img = (ImageView) arg1;
		}
		img.setImageResource(image_id[arg0]);
		return img;
	}

}

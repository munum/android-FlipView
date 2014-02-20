package se.emilsjolander.flipview;

import android.app.ListActivity;
import se.emilsjolander.flipview.FlipAdapter.Callback;
import se.emilsjolander.flipview.FlipView.OnFlipListener;
import se.emilsjolander.flipview.FlipView.OnOverFlipListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements Callback, OnFlipListener, OnOverFlipListener {
	
	private FlipView mFlipView;
	private FlipAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.outter);
        ArrayList<String> list = new ArrayList<String>();
        list.add("first row");
        list.add("second row");
        list.add("third row");
        list.add("fourth row");
        list.add("fifth row");
        FlippableItemAdapter adapter = new FlippableItemAdapter(this, R.layout.row, list);
        setListAdapter(adapter);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.prepend:
			mAdapter.addItemsBefore(5);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageRequested(int page) {
		mFlipView.smoothFlipTo(page);
	}

	@Override
	public void onFlippedToPage(FlipView v, int position, long id) {
		Log.i("pageflip", "Page: "+position);
//		if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
        if(position > v.getPageCount()-3 && v.getPageCount()<30){
			mAdapter.addItems(5);
		}
	}

	@Override
	public void onOverFlip(FlipView v, OverFlipMode mode,
			boolean overFlippingPrevious, float overFlipDistance,
			float flipDistancePerPage) {
		Log.i("overflip", "overFlipDistance = "+overFlipDistance);
	}

}

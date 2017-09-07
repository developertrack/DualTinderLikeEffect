package developertrack.dualtinderlikeeffect;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class PhotoWarFragment extends AppCompatActivity {


	private int windowwidth, screenCenter, x_cord, y_cord, x, y, Likes = 0;
	private RelativeLayout parentView, secondPrentView;
	private float alphaValue = 0;
	private Button btnSkip;
	int counter = 0;
	ImageView[] m_image;
	ImageView[] m_imagee;
	ImageLoader loader;
	String[] url1={"https://static.wixstatic.com/media/7b5e56_d42a0c16a2e64a72b0221462c555f818~mv2.png","http://wirelessworks.biz/wp-content/uploads/2015/04/android-icon.png","https://pluralsight.imgix.net/paths/path-icons/android-53f8da146d.png","http://www.goinstore.co.uk/wp-content/uploads/2015/05/android.png","http://phandroid.s3.amazonaws.com/wp-content/uploads/2012/11/288554xcitefun-happy-birthday-android-with-cake.png","http://s8.picofile.com/file/8276472168/ir_androidpl_drandroid.png"};
	String[] url2={"http://design4u.in/wp-content/uploads/2015/08/photo.png","https://forums.androidcentral.com/attachments/android-7-0-nougat/233011d1469309058t-android-neko-easter-egg-krinkles.png","http://images.sftcdn.net/images/t_optimized,f_auto/p/1b965136-a4d5-11e6-8e07-00163ed833e7/1483741267/ireparo-for-android-logo.png","http://appslova.com/wp-content/uploads/2016/03/Android-error.png","http://www.thedenkenlabs.com/images/Services/Android-icon.png","http://csis.pace.edu/gerontechnology/sites/gerontechnology/files/android.png"};

	int flag = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		new ImagesDownload().execute();


		secondPrentView = (RelativeLayout)findViewById(R.id.second_layoutview);
		parentView = (RelativeLayout)findViewById(R.id.layoutview);
		parentView.removeAllViews();
		secondPrentView.removeAllViews();
		btnSkip = (Button)findViewById(R.id.btn_skip);

		windowwidth = (PhotoWarFragment.this).getWindowManager()
				.getDefaultDisplay().getWidth();
		screenCenter = windowwidth / 2;

		loader = new ImageLoader(PhotoWarFragment.this);



		btnSkip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (counter >= 0) {
					// load the animation
					Animation slide = null;
					slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
							0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, -5.0f);
					slide.setDuration(800);
					slide.setFillAfter(false);
					slide.setFillEnabled(false);

					Animation slideDown = null;
					slideDown = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 5.2f);

					slideDown.setDuration(800);
					slideDown.setFillAfter(false);
					slideDown.setFillEnabled(false);

					View v1 = parentView.getChildAt(counter);
					v1.startAnimation(slide);
					View v2 = secondPrentView.getChildAt(counter);
					v2.startAnimation(slideDown);
					final int c = counter;
					slideDown.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {
							// TODO Auto-generated method stub
							parentView.removeViewAt(c);
							secondPrentView.removeViewAt(c);
						}
					});
					if (checkEmptyList(counter))
						alertDailog();
					// parentView.removeViewAt(counter);
					// secondPrentView.removeViewAt(counter);
					counter--;
				} else {
					v.setClickable(false);
					alertDailog();
				}
			}
		});

		workingOfFirstGallery();
		workingOfSecondGallery();
		counter = url1.length - 1;
	}

//	class ImagesDownload extends AsyncTask<Void, Void, Void> {
//
//		@Override
//		protected Void doInBackground(Void... params) {
//			// TODO Auto-generated method stub
//
//
//
//
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//
//		}
//
//	}
	/**
	 * Working of first gallery
	 */
	public void workingOfFirstGallery() {
		// int[] myImageList = new int[] { R.drawable.cats, R.drawable.baby1,
		// R.drawable.sachin, R.drawable.cats, R.drawable.puppy };
		m_image = new ImageView[url1.length];
		for (int i = 0; i < url1.length; i++) {
			LayoutInflater inflate = (LayoutInflater) PhotoWarFragment.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			final View m_view = inflate.inflate(R.layout.photwar_custom_layout,
					null);
			m_image[i] = (ImageView) m_view.findViewById(R.id.sp_image);
			LinearLayout m_topLayout = (LinearLayout) m_view
					.findViewById(R.id.sp_color);
			// LinearLayout m_bottomLayout = (LinearLayout) m_view
			// .findViewById(R.id.sp_linh);
			// final RelativeLayout myRelView = new RelativeLayout(this);
			m_view.setLayoutParams(new LayoutParams((windowwidth - 80), 450));
			m_view.setX(40);
			m_view.setY(40);
			m_view.setTag(i);
			// m_image.setBackgroundResource(id1[i]);
			loader.DisplayImage(url1[i], R.drawable.back_loader, m_image[i]);

			if (i == 0) {
				m_view.setRotation(-1);
			} else if (i == 1) {
				m_view.setRotation(-5);

			} else if (i == 2) {
				m_view.setRotation(3);

			} else if (i == 3) {
				m_view.setRotation(7);

			} else if (i == 4) {
				m_view.setRotation(-2);

			} else if (i == 5) {
				m_view.setRotation(5);

			}

			// ADD dynamically like button on image.
			final Button imageLike = new Button(PhotoWarFragment.this);
			imageLike.setLayoutParams(new LayoutParams(100, 50));
			imageLike.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.like));
			imageLike.setX(20);
			imageLike.setY(-250);
			imageLike.setAlpha(alphaValue);
			m_topLayout.addView(imageLike);

			// ADD dynamically dislike button on image.
			final Button imagePass = new Button(PhotoWarFragment.this);
			imagePass.setLayoutParams(new LayoutParams(100, 50));
			imagePass.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.unlike));

			imagePass.setX(260);
			imagePass.setY(-300);
			imagePass.setAlpha(alphaValue);
			m_topLayout.addView(imagePass);

			// Click listener on the bottom layout to open the details of the
			// image.
			// m_bottomLayout.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// startActivity(new Intent(thiscontext, DetailsActivity.class));
			//
			// }
			// });

			// Touch listener on the image layout to swipe image right or left.
			m_topLayout.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					x_cord = (int) event.getRawX();
					y_cord = (int) event.getRawY();

					m_view.setX(x_cord - screenCenter + 40);
					m_view.setY(y_cord - 150);
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							x = (int) event.getX();
							y = (int) event.getY();
							Log.v("On touch", x + " " + y);
							break;
						case MotionEvent.ACTION_MOVE:
							x_cord = (int) event.getRawX(); // Updated for more
							// smoother animation.
							y_cord = (int) event.getRawY();
							m_view.setX(x_cord - x);
							m_view.setY(y_cord - y);
							if (x_cord >= screenCenter) {
								m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
								if (x_cord > (screenCenter + (screenCenter / 2))) {
									imageLike.setAlpha(1);
									if (x_cord > (windowwidth - (screenCenter / 4))) {
										Likes = 2;
									} else {
										Likes = 0;
									}
								} else {
									Likes = 0;
									imageLike.setAlpha(0);
								}
								imagePass.setAlpha(0);
							} else {
								// rotate
								m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
								if (x_cord < (screenCenter / 2)) {
									imagePass.setAlpha(1);
									if (x_cord < screenCenter / 4) {
										Likes = 1;
									} else {
										Likes = 0;
									}
								} else {
									Likes = 0;
									imagePass.setAlpha(0);
								}
								imageLike.setAlpha(0);
							}

							break;
						case MotionEvent.ACTION_UP:
							x_cord = (int) event.getRawX();
							y_cord = (int) event.getRawY();

							Log.e("X Point", "" + x_cord + " , Y " + y_cord);
							imagePass.setAlpha(0);
							imageLike.setAlpha(0);

							if (Likes == 0) {
								// Log.e("Event Status", "Nothing");
								m_view.setX(40);
								m_view.setY(40);
								m_view.setRotation(0);
							} else if (Likes == 1) {
								Log.e("Event Status", "Passed");
								parentView.removeViewAt(counter);
								secondPrentView.removeViewAt(counter);
								if (checkEmptyList(counter))
									alertDailog();
								counter--;
							} else if (Likes == 2) {
								Log.e("Event Status", "Liked");
								flag = 1;
								new LikePhoto().execute();
								int po = counter;
								parentView.removeViewAt(counter);
								secondPrentView.removeViewAt(counter);
								if (checkEmptyList(counter))
									alertDailog();
								counter--;
							}
							break;
						default:
							break;
					}
					return true;
				}
			});

			parentView.addView(m_view);
			// secondPrentView.addView(m_view);

		}
	}
	/**
	 * Working of first gallery
	 */
	public void workingOfSecondGallery() {
		// int[] myImageList = new int[] { R.drawable.cats, R.drawable.baby1,
		// R.drawable.sachin, R.drawable.cats, R.drawable.puppy };
		m_imagee = new ImageView[url1.length];
		for (int i = 0; i < url1.length; i++) {
			LayoutInflater inflate = (LayoutInflater)PhotoWarFragment.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			final View m_view = inflate.inflate(R.layout.photwar_custom_layout,
					null);
			m_imagee[i] = (ImageView) m_view.findViewById(R.id.sp_image);
			LinearLayout m_topLayout = (LinearLayout) m_view
					.findViewById(R.id.sp_color);
			// LinearLayout m_bottomLayout = (LinearLayout) m_view
			// .findViewById(R.id.sp_linh);
			// final RelativeLayout myRelView = new RelativeLayout(this);
			m_view.setLayoutParams(new LayoutParams((windowwidth - 80), 450));
			m_view.setX(40);
			m_view.setY(40);
			m_view.setTag(i);
			// m_image.setBackgroundResource(myImageList[i]);
			loader.DisplayImage(url2[i], R.drawable.back_loader, m_imagee[i]);

			if (i == 0) {
				m_view.setRotation(-1);
			} else if (i == 1) {
				m_view.setRotation(-5);

			} else if (i == 2) {
				m_view.setRotation(3);

			} else if (i == 3) {
				m_view.setRotation(7);

			} else if (i == 4) {
				m_view.setRotation(-2);

			} else if (i == 5) {
				m_view.setRotation(5);

			}

			// ADD dynamically like button on image.
			final Button imageLike = new Button(PhotoWarFragment.this);
			imageLike.setLayoutParams(new LayoutParams(100, 50));
			imageLike.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.like));
			imageLike.setX(20);
			imageLike.setY(-250);
			imageLike.setAlpha(alphaValue);
			m_topLayout.addView(imageLike);

			// ADD dynamically dislike button on image.
			final Button imagePass = new Button(PhotoWarFragment.this);
			imagePass.setLayoutParams(new LayoutParams(100, 50));
			imagePass.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.unlike));

			imagePass.setX(260);
			imagePass.setY(-300);
			imagePass.setAlpha(alphaValue);
			m_topLayout.addView(imagePass);

			// Click listener on the bottom layout to open the details of the
			// image.
			// m_bottomLayout.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// startActivity(new Intent(thiscontext, DetailsActivity.class));
			//
			// }
			// });

			// Touch listener on the image layout to swipe image right or left.
			m_topLayout.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					x_cord = (int) event.getRawX();
					y_cord = (int) event.getRawY();
					int xc = y_cord;
					int xy = x_cord;
					int cen = screenCenter;
					m_view.setX(20);
					m_view.setY(100);
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							x = (int) event.getX();
							y = (int) event.getY();

							int xxx = x;
							int yyy = y;
							Log.v("On touch", x + " " + y);
							break;
						case MotionEvent.ACTION_MOVE:
							x_cord = (int) event.getRawX(); // Updated for more
							// smoother animation.
							y_cord = (int) event.getRawY();
							int xccc = y_cord;
							int xyyy = x_cord;
							m_view.setX(x_cord - 100);
							m_view.setY(y_cord - 700);


							if (x_cord >= screenCenter) {
								m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
								if (x_cord > (screenCenter + (screenCenter / 2))) {
									imageLike.setAlpha(1);
									if (x_cord > (windowwidth - (screenCenter / 4))) {
										Likes = 2;
									} else {
										Likes = 0;
									}
								} else {
									Likes = 0;
									imageLike.setAlpha(0);
								}
								imagePass.setAlpha(0);
							} else {
								// rotate
								m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
								if (x_cord < (screenCenter / 2)) {
									imagePass.setAlpha(1);
									if (x_cord < screenCenter / 4) {
										Likes = 1;
									} else {
										Likes = 0;
									}
								} else {
									Likes = 0;
									imagePass.setAlpha(0);
								}
								imageLike.setAlpha(0);
							}

							break;
						case MotionEvent.ACTION_UP:
							x_cord = (int) event.getRawX();
							y_cord = (int) event.getRawY();

							Log.e("X Point", "" + x_cord + " , Y " + y_cord);
							imagePass.setAlpha(0);
							imageLike.setAlpha(0);

							if (Likes == 0) {
								// Log.e("Event Status", "Nothing");
								m_view.setX(40);
								m_view.setY(40);
								m_view.setRotation(0);
							} else if (Likes == 1) {
								Log.e("Event Status", "Passed");
								parentView.removeViewAt(counter);
								secondPrentView.removeViewAt(counter);
								if (checkEmptyList(counter))
									alertDailog();
								counter--;
							} else if (Likes == 2) {
								int po = counter;
								flag = 2;
								new LikePhoto().execute();
								Log.e("Event Status", "Liked");
								parentView.removeViewAt(counter);
								secondPrentView.removeViewAt(counter);
								if (checkEmptyList(counter))
									alertDailog();
								counter--;
							}
							break;
						default:
							break;
					}
					return true;
				}
			});

			// parentView.addView(m_view);
			secondPrentView.addView(m_view);

		}
	}


	private void alertDailog() {
		final Dialog mDialog = new Dialog(PhotoWarFragment.this);
		mDialog.setContentView(R.layout.custom_alert_layout);
		mDialog.setCancelable(false);
		mDialog.setTitle("Please choose another category.");
		Button btnClose = (Button) mDialog.findViewById(R.id.btn_ok);
		btnClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// finish();

//				android.support.v4.app.Fragment fragment2 = new HomeSubCategoryFragment();
//				Bundle bundle = new Bundle();
//				bundle.putString("id", category_id);
//
//				FragmentManager fragmentManager = getFragmentManager();
//				android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
//						.beginTransaction();
//				// fragmentTransaction.addToBackStack("home");
//				fragmentTransaction.addToBackStack(null);
//				fragmentManager.popBackStack(null,
//						FragmentManager.POP_BACK_STACK_INCLUSIVE);
//				// fragmentTransaction.setCustomAnimations(R.anim.swipe_left,
//				// R.anim.swipe_right);
//				fragmentTransaction.replace(android.R.id.tabcontent, fragment2);
//				fragment2.setArguments(bundle);
//				fragmentTransaction.commit();
//
//				TabWidget tabMenu = (TabWidget) getActivity().findViewById(
//						android.R.id.tabs);
//				tabMenu.setVisibility(View.VISIBLE);
//                new ImagesDownload().execute();


				mDialog.cancel();
			}
		});

		mDialog.show();

	}

	private boolean checkEmptyList(int counter) {
		if (counter == 0)
			return true;
		else
			return false;
	}

	class LikePhoto extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			if (NetworkUtil.getConnectivityStatusString(
					PhotoWarFragment.this).equals("Not connected to Internet")) {

				Toast.makeText(PhotoWarFragment.this,
						"Oops! You aren't connected to Internet.",
						Toast.LENGTH_SHORT).show();

			} else {


			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}

}
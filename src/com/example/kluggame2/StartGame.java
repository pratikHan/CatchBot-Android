package com.example.kluggame2;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.catchbotanimated.R;
import com.example.kluggame2.FragmentAngleUI.GestureListener;
import com.example.kluggame2.FragmentAngleUI.MyDragListener;
import com.example.utils.Timerx.TimerInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.R.integer;
import android.app.Fragment;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextWatcher;
import android.text.style.EasyEditSpan;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug.FlagToString;
import android.view.ViewDebug.FlagToString;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VerticalSeekBar;

public class StartGame extends Fragment implements OnClickListener,
		TimerInterface {

	// test variables
	Button nofoul;
	public static Boolean testflagA = false;
	public static Boolean testflagB = false;

	public static Boolean operatorsflag = false;
	public static Boolean numbersFlag = false;

	public  Boolean flaglevel1 = false;
	public  Boolean flaglevel2 = false;
	public  Boolean flaglevel3 = false;
	public  Boolean flageasy = false;

	private static final int leveleasy = 0;
	private static final int level1 = 1;
	private static final int level2 = 2;
	private static final int level3 = 3;

	private static String equation = "";

	public static String answersarr = "";

	// TextView txtsteps, txtangle,textmaster;
	TextView txtAnswer, textkidanswer, texttarget;
	EditText txtQuestion;
	Button btnlevel1, btnlevel2, btnlevel3, btnleveleasy;
	ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bplus, bminus,
			bmultiply, bdivide, bgoanswer, bgoquestion, bgokid,backmenu;
	Button btnstop, btnclear, btnopwrong, btnnumwrong;
	// VerticalSeekBar seek_bar;
	RelativeLayout layoutquestioner, layoutanaswerer,layoutlevels;

	RelativeLayout operatorsLayout, NumbersLayout;

	LinearLayout operatorsLayout1, NumbersLayout1;

	AndroidUnityInterface uinterface;
	MainActivity act;
	ArrayList<String> mastersum = new ArrayList<String>();
	ArrayList<String> detectivesum = new ArrayList<String>();

	private static final double SWIPE_MIN_DISTANCE = 30;
	private static final String TAG = "START GAME";
	String stepsdata;
	public String sumstring = "";

	TextView t6, t7;

	Animation animation, anim1, anim2, anim3, anim4, anim5, anim6, anim7,
			anim8, anim9, anim10, anim11, anim12;
	Typeface face = null;
	RotateAnimation r;
	int _fromdegrees = 0;

	public StartGame() {
	}

	public StartGame(MainActivity _act) {
		uinterface = AndroidUnityInterface.getInstance();

		act = _act;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_start_game, null);

		face = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/beon.ttf");

		// t5=(TextView)view.findViewById(R.id.labelSettings);

		t7 = (TextView) view.findViewById(R.id.dialoglabel1);

		// t5.setTypeface(face);

		t7.setTypeface(face);

		layoutquestioner = (RelativeLayout) view
				.findViewById(R.id.layoutquestioner);
		layoutanaswerer = (RelativeLayout) view
				.findViewById(R.id.layoutanswerer);
		layoutlevels = (RelativeLayout) view
				.findViewById(R.id.layoutlevels);
		

		operatorsLayout = (RelativeLayout) view
				.findViewById(R.id.operatorslayout);
		NumbersLayout = (RelativeLayout) view.findViewById(R.id.numberslayout);

		operatorsLayout1 = (LinearLayout) view
				.findViewById(R.id.operatorsLayout1);
		NumbersLayout1 = (LinearLayout) view.findViewById(R.id.numbersLayout1);

		btnlevel1 = (Button) view.findViewById(R.id.btnlevel1);
		btnlevel2 = (Button) view.findViewById(R.id.btnlevel2);
		btnlevel3 = (Button) view.findViewById(R.id.btnlevel3);
		btnleveleasy = (Button) view.findViewById(R.id.btneasy);

		b1 = (ImageButton) view.findViewById(R.id.imageButton1);
		b2 = (ImageButton) view.findViewById(R.id.imageButton2);
		b3 = (ImageButton) view.findViewById(R.id.imageButton3);
		b4 = (ImageButton) view.findViewById(R.id.imageButton4);
		b5 = (ImageButton) view.findViewById(R.id.imageButton5);
		b6 = (ImageButton) view.findViewById(R.id.imageButton6);
		b7 = (ImageButton) view.findViewById(R.id.imageButton7);
		b8 = (ImageButton) view.findViewById(R.id.imageButton8);
		b9 = (ImageButton) view.findViewById(R.id.imageButton9);
		b0 = (ImageButton) view.findViewById(R.id.imageButton0);
		
		backmenu= (ImageButton) view.findViewById(R.id.backx);

		bmultiply = (ImageButton) view.findViewById(R.id.imageButtonmultiply);
		bplus = (ImageButton) view.findViewById(R.id.imageButtonadd);
		bminus = (ImageButton) view.findViewById(R.id.imageButtonminus);
		bdivide = (ImageButton) view.findViewById(R.id.imageButtondivide);

		btnstop = (Button) view.findViewById(R.id.btnstop);
		btnopwrong = (Button) view.findViewById(R.id.btnwrongoperator);
		btnnumwrong = (Button) view.findViewById(R.id.btnwrongnumber);
		btnclear = (Button) view.findViewById(R.id.btnclear);

		bgoanswer = (ImageButton) view.findViewById(R.id.btngoanswer);
		bgoquestion = (ImageButton) view.findViewById(R.id.btngoquestion);

		bgokid = (ImageButton) view.findViewById(R.id.btngokid);

		textkidanswer = (TextView) view.findViewById(R.id.textkidanswer);
		txtAnswer = (TextView) view.findViewById(R.id.textviewAnswer);
		txtQuestion = (EditText) view.findViewById(R.id.textviewQuestion);
		texttarget = (TextView) view.findViewById(R.id.texttarget);

		txtQuestion.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);

		btnlevel1.setOnClickListener(this);
		btnlevel2.setOnClickListener(this);
		btnlevel3.setOnClickListener(this);
		btnleveleasy.setOnClickListener(this);
		backmenu.setOnClickListener(this);

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b0.setOnClickListener(this);

		bmultiply.setOnClickListener(this);
		bdivide.setOnClickListener(this);
		bminus.setOnClickListener(this);
		bplus.setOnClickListener(this);

		bgoanswer.setOnClickListener(this);
		bgokid.setOnClickListener(this);
		bgoquestion.setOnClickListener(this);
		btnstop.setOnClickListener(this);
		btnopwrong.setOnClickListener(this);
		btnnumwrong.setOnClickListener(this);
		btnclear.setOnClickListener(this);

		// load animation
		// loadAnimation();

		Log.e(TAG, "State:" + uinterface.state.state);

		uinterface.screen = this;

		TurnChange();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				act.KlugDialogs(act.StartGame, 1);
				act.KlugDialogs(act.WaitHost, 1);
			}

		}, 2000);

		// start animation

		playAnimation();

		return view;
	}

	public void startBotMove() {

		Log.e(TAG, "EndBotMove");
		if (uinterface.state.state == uinterface.detective) {
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					masterMindEntering(false);
					// act.KlugDialogs(act.botMoving, 0);

					observeBot(false);

					Log.e("ZZ", "detective view");

				}

			});

		} else if (uinterface.state.state == uinterface.master) {
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					observeBot(false);

					// act.KlugDialogs(act.botMoving, 0);

					masterMindEntering(false);// --can add detective waiting

					Log.e("ZZ", "master view");

				}

			});

		}
	}

	public void EndBotMove() {

		Log.e(TAG, "EndBotMove");
		if (uinterface.state.state == uinterface.detective) {
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					masterMindEntering(false);
					// act.KlugDialogs(act.botMoving, 1);

					observeBot(true);

					Log.e("ZZ", "detective view");

				}

			});

		} else if (uinterface.state.state == uinterface.master) {
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					observeBot(false);

					act.KlugDialogs(act.botMoving, 1);
					// act.KlugDialogs(act.observebot, 0);

					masterMindEntering(false);// --can add detective waiting

					Log.e("ZZ", "master view");

				}

			});

		}
	}

	public void MasterEnd() {
		if (uinterface.state.state == uinterface.detective) {
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					masterMindEntering(false);

					act.KlugDialogs(act.botMoving, 1);

					Log.e("ZZ", "detective view");

				}

			});

		} else if (uinterface.state.state == uinterface.master) {
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					act.KlugDialogs(act.botMoving, 1);

					masterMindEntering(false);// --can add detective waiting

					Log.e("ZZ", "master view");

				}

			});

		}
	}

	public void Foul_Reinitiate() {

		Log.e("FoulReinitiate", "");
		// act.KlugDialogs(dialogs, status);

		if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is detective");
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

				}

			});

			//
		} else if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is master");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					Log.e("MASTER", "loa");

				
					loadAnimation();
					playAnimation();

					if (operatorsflag && flageasy){
						Log.e("UI","A");
						NumbersLayout1.setVisibility(View.VISIBLE);
						
						NumbersLayout.setVisibility(View.GONE);
					}
					else if(!operatorsflag && !flageasy){
						Log.e("UI","B");
						
						operatorsLayout.setVisibility(View.VISIBLE);
						operatorsLayout1.setVisibility(View.GONE);
						
						NumbersLayout.setVisibility(View.GONE);
						NumbersLayout1.setVisibility(View.GONE);
					}

					if (numbersFlag && flageasy){
						Log.e("UI","C");
						operatorsLayout1.setVisibility(View.VISIBLE);
						operatorsLayout.setVisibility(View.GONE);
					}
					else if(!numbersFlag && !flageasy){
						Log.e("UI","D");
						
						NumbersLayout.setVisibility(View.VISIBLE);
						NumbersLayout1.setVisibility(View.GONE);
						
						operatorsLayout.setVisibility(View.GONE);
						operatorsLayout1.setVisibility(View.GONE);
					}
				}

			});
		}

	}

	public void Foul_WrongPawn(String pawn) {

		Log.e("FoulRaised", "Status is :" + pawn);
		// act.KlugDialogs(dialogs, status);

		Log.e("ZZ", "FOULRAISED");

		if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is gone");
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					act.KlugDialogs(act.Foulgame, 1);
					act.KlugDialogs(act.PlayerWaiting, 0);

				}

			});

			//
		} else if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is visible");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					act.KlugDialogs(act.WrongPawnTouched, 0);

				}

			});
		}

	}

	public void ExitOnBack() {

		act.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				act.KlugDialogs(act.ExitGame, 0);
			}
		});

	}

	public void FoulOutsideArena() {

		Log.e("ZZ", "Stop Bot");

		if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is detective");
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

				}

			});

			//
		} else if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is visible");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					act.KlugDialogs(act.OutsideArena, 0);

				}

			});
		}

	}

	public void Foul_Nopawns() {
	}

	public void masterMindEntering(boolean status) {
	}

	public void observeBot(boolean status1) {
	}

	public void ResetBot() {

		Log.e("Unity", "ResetBOt");
		act.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				act.KlugDialogs(act.resetBot, 0);
			}
		});

		Log.e("BOT HAS STOPPED", "BOT HAS STOPPED");

	}

	public void UpdateUI() {

		Log.e("UpdateUI", "update called");

		if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is detective");

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					if (texttarget != null) {
						String temp = "X";
						for (int i = 0; i < uinterface.list.length; i++) {
							Log.e("ZZ", "changint turn  X "
									+ uinterface.list[i].turn + ":"
									+ uinterface.list[i].foulnumber);

							temp = uinterface.list[i].target;
							Log.e(TAG, "TARGET :" + temp);

						}

						textkidanswer.setText("");
						equation = "";
						texttarget.setText(temp.toString());
						texttarget.setTypeface(face);
					}

				}

			});

			//
		} else if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is master");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.e("turnchange", "master");

					if (txtAnswer != null) {
						String temp = "X";
						for (int i = 0; i < uinterface.list.length; i++) {
							Log.e("ZZ", "changint turn  X "
									+ uinterface.list[i].turn + ":"
									+ uinterface.list[i].equation);

							temp = uinterface.list[i].equation;
							answersarr += temp;
							Log.e(TAG, "Answer is :" + answersarr);

						}

						txtAnswer.setText(temp);

					}

				}

			});
		}

		Log.e("ZZ", "completed turn change" + ":");

	}

	public void TurnChange() {
		Log.e("TurnChange", "Turnchange called");

		Log.e("Unity", "Turnchange");

		Log.e("ZZ", "changing turn " + uinterface.state.state);
		if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is detective");

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					layoutanaswerer.setVisibility(View.GONE);
					layoutquestioner.setVisibility(View.GONE);

				}

			});

			//
		} else if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is master");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.e("turnchange", "master");
					layoutanaswerer.setVisibility(View.GONE);
					layoutquestioner.setVisibility(View.VISIBLE);
					layoutlevels.setVisibility(View.GONE);
					backmenu.setVisibility(View.GONE);
					
				}

			});
		}

		Log.e("ZZ", "completed turn change" + ":");

	}

	public void EndBot() {
		Log.e("RR", "completed commands");
		Log.e("RR", "End BOT called");
		// uinterface.sendUnityMessage(AndroidUnityInterface.EndBot,"");
	}

	public void StartBot() {
		// /TimerStart(1);
		if (uinterface != null) {
			for (int i = 0; i < uinterface.list.length; i++) {
				if (uinterface.list[i].type == 0) {
					Log.e("RR", "found server equations "
							+ uinterface.list[i].equation);
					sumstring = uinterface.list[i].equation;
				}
			}
			if (sumstring != null) {
				Log.e("RR", "sumstring is " + sumstring);
			} else {
				Log.e("RR", "sumstring is null");
			}
			final ArrayList<String> equation = getArrayEquation(sumstring);
			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					act.binterface.play(equation);
					Log.d("RR", "BOT IS PLAYING");
				}

			});

		} else
			Log.e("RR", "BOt IS NOT Startted cuz Uinterface is null");

		// after time ends,sends detective start to all
	}

	public ArrayList<String> getArrayEquation(String list) {
		ArrayList<String> l1 = new ArrayList<String>();

		GsonBuilder gsonb = new GsonBuilder();
		Gson gson = gsonb.create();
		JSONArray j;
		try {
			j = new JSONArray(list);
			String[] l2 = gson.fromJson(j.toString(), String[].class);
			for (int i = 0; i < l2.length; i++) {
				l1.add(l2[i]);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l1;
		// /local =gson.fromJson(j1.toString(),PlayerData.class);

	}

	int progressval = 0;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		String tag = (String) v.getTag();

		if (tag != null) {
			if (tag.equals("0") || tag.equals("1") || tag.equals("2")
					|| tag.equals("3") || tag.equals("4") || tag.equals("5")
					|| tag.equals("6") || tag.equals("7") || tag.equals("8")
					|| tag.equals("9") || tag.equals("/") || tag.equals("*")
					|| tag.equals("-") || tag.equals("+")) {

				equation += tag;
				textkidanswer.setText(equation);

			}

			if (tag.equals("0") || tag.equals("1") || tag.equals("2")
					|| tag.equals("3") || tag.equals("4") || tag.equals("5")
					|| tag.equals("6") || tag.equals("7") || tag.equals("8")
					|| tag.equals("9")) {
				numbersFlag = true;
				operatorsflag = false;

				if (flageasy) {
					operatorsLayout1.setVisibility(View.GONE);
					NumbersLayout1.setVisibility(View.GONE);
				} else {
					operatorsLayout.setVisibility(View.GONE);
					NumbersLayout.setVisibility(View.GONE);
				}

			}

			if (tag.equals("/") || tag.equals("*") || tag.equals("-")
					|| tag.equals("+")) {
				operatorsflag = true;
				numbersFlag = false;

				if (flageasy) {
					operatorsLayout1.setVisibility(View.GONE);
					NumbersLayout1.setVisibility(View.GONE);
				} else {
					operatorsLayout.setVisibility(View.GONE);
					NumbersLayout.setVisibility(View.GONE);
				}

			}
		}

		switch (v.getId()) {

		case R.id.btneasy:

			setGameLevel(leveleasy);

			break;

		case R.id.btnlevel1:

			setGameLevel(level1);

			break;

		case R.id.btnlevel2:

			
			setGameLevel(level2);

			break;
			
		case R.id.btnlevel3:
			
			
			
			setGameLevel(level3);
			
			
			break;
		
		case R.id.backx:
			
			backtomenu();
			break;
			

		case R.id.btnnofoul:
			Log.e(TAG, "Testflag is " + testflagA);

			if (testflagA || testflagB) {

				Log.d(TAG, "testflagCondition is TRUE");

			}
			// TurnChange();
			uinterface.sendUnityMessage(AndroidUnityInterface.TurnEnd, "");

			break;

		case R.id.btnfoul:

			act.KlugDialogs(act.Foulgame, 0);

			break;
		case R.id.imageButtonGuide4:

			// if (mastersum.size()==2) {

			senddata();
			// TimerStart(0);// --TESTING to be renabled
			Log.e(TAG, "calling cancel");
			Log.e("RR", "for masterend x");
			Cancel(0);

			// }

			break;

		case R.id.btngokid:

			// operatorsLayout.setVisibility(View.VISIBLE);
			// NumbersLayout.setVisibility(View.VISIBLE);

			String s = textkidanswer.getText().toString();

			uinterface.local.equation = s;
			uinterface.sendUnityMessage(AndroidUnityInterface.SendAnswer, s);

			break;

		case R.id.btngoquestion:

			String temp = txtQuestion.getText().toString();
			uinterface.local.target = temp;
			uinterface.sendUnityMessage(AndroidUnityInterface.Sendtarget, temp);
			answersarr = "";
			txtAnswer.setText("");

			break;

		case R.id.btngoanswer:

			// StartBot();
			final ArrayList<String> temparr = new ArrayList<String>();
			temparr.add("move1");

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					act.binterface.play(temparr);
					Log.d("RR", "BOT IS PLAYING");
				}

			});

			break;

		case R.id.btnstop:

			final ArrayList<String> temparrx = new ArrayList<String>();
			temparrx.add("move4");

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					uinterface.sendUnityMessage(
							AndroidUnityInterface.FoulReinitiate, "");
					act.binterface.play(temparrx);
					Log.d("RR", "BOT IS PLAYING");
				}

			});

			break;

		case R.id.btnclear:

			Log.e("Buttons", "btn clear");
			txtQuestion.setText(" ");

			break;

		case R.id.btnwrongnumber:

			Log.e("Buttons", "btn wrong number");
			uinterface.sendUnityMessage(AndroidUnityInterface.StateFoulA, "");

			break;

		case R.id.btnwrongoperator:

			Log.e("Buttons", "btn wrong operator");
			uinterface.sendUnityMessage(AndroidUnityInterface.StateFoulB, "");

			break;

		default:
			break;
		}

	}

	

	private void senddata() {
	}

	public void wrongnumber() {

		if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is detective");

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					if (flageasy) {
						NumbersLayout1.setVisibility(View.VISIBLE);
						operatorsLayout1.setVisibility(View.GONE);
					} else {
						NumbersLayout.setVisibility(View.VISIBLE);
						operatorsLayout.setVisibility(View.GONE);
					}
				}

			});

			//
		} else if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is master");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.e("turnchange", "master");

				}

			});
		}

	}

	public void wrongoperator() {

		if (uinterface.state.state == uinterface.master) {
			Log.e("ZZ", "view is detective");

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {

					if (flageasy) {
						NumbersLayout1.setVisibility(View.GONE);
						operatorsLayout1.setVisibility(View.VISIBLE);
					} else {
						NumbersLayout.setVisibility(View.GONE);
						operatorsLayout.setVisibility(View.VISIBLE);
					}
				}

			});

			//
		} else if (uinterface.state.state == uinterface.detective) {
			Log.e("ZZ", "view is master");
			// mainv.setVisibility(View.VISIBLE);

			act.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.e("turnchange", "master");

				}

			});
		}

	}

	@Override
	public void Cancel(int id) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		if (id == 0) {
			// TimerStop(id);

			Log.e(TAG, "XXXXXXXX " + uinterface.local.type + ":"
					+ uinterface.master + ":" + uinterface.local.turn);
			if (uinterface.local.turn == true) {
				// validate equattion

				// timeoutDialog(0);
				// senddata();
				Log.e("Cancel", "Mastersum is" + mastersum);
				String eq = getEquation(mastersum);

				Log.d(TAG, "EQ is" + eq);
				Log.d(TAG, "EQ LENGTH IS" + eq.length());
				Log.d(TAG, "MASTERSUM SIZE IS" + mastersum.size());

				if (mastersum.size() == 2 && mastersum.size() != 0) {
					Log.e(TAG, "master End is calling");
					uinterface.sendUnityMessage(
							AndroidUnityInterface.MasterEnd, eq);
					// mastersum.clear();

				} else {
					mastersum.clear();
					// if the equation is not entered , turn change
					if (mastersum.size() > 2) {

						// uinterface.sendUnityMessage(AndroidUnityInterface.TurnEnd,
						// eq);
						// mastersum.clear();
						// testflagA=true;
						// Log.e(TAG, "Size after clear:"+mastersum);

						Log.e(TAG, "Turnend is called");
					}

				}
				// needs to be turn end

			} else if (uinterface.local.turn == false) {

				Log.d("Cancel", ":Local.turn is false");
			}

		} else {
			Log.d(TAG, "Cancel else");
		}

		// TimerStop(1);
		// /uinterface.sendUnityMessage(AndroidUnityInterface.DetectiveStart,"");

	}

	@Override
	public boolean runTask(int id, int TimeCounter) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getEquation(ArrayList<String> sum) {
		String response = new String();

		ArrayList<String> tmp = new ArrayList<String>();
		int last = 0;
		/*
		 * if (sum != null && sum.size() > 0) { if (sum.size() % 2 != 0) { last
		 * = sum.size(); } else { last = sum.size() - 1; } }
		 */
		last = sum.size();
		for (int i = 0; i < last; i++) {
			tmp.add(sum.get(i));
			Log.e("ZZ", "equation is " + sum.get(i));
		}
		Gson gson = new Gson();

		if (sum != null && tmp.size() > 0)
			response = gson.toJson(tmp);

		return response;
	}

	@Override
	public void Complete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void End(int id) {
		// TODO Auto-generated method stub

	}

	public void loadAnimation() {
		// load the animation

		if (flaglevel2||flaglevel3) {
			Log.e("Animation", "Load Animation");
			animation = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in);
			anim1 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in1);
			anim2 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in2);
			anim3 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in3);
			anim4 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in4);
			anim5 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in5);
			anim6 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in6);
			anim7 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in7);
			anim8 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in8);
			anim9 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in9);
			anim10 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in10);
			anim11 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in11);
			anim12 = AnimationUtils.loadAnimation(this.getActivity(),
					R.anim.fade_in12);
		}

	}

	public void playAnimation() {
		Log.e("Animation", "PlayAnimation");

		
		
		
		
		if (flaglevel2||flaglevel3) {
			b1.startAnimation(animation);

			b2.startAnimation(anim1);

			b3.startAnimation(anim2);

			b4.startAnimation(anim3);

			b5.startAnimation(anim4);

			b6.startAnimation(anim5);

			b7.startAnimation(anim6);

			b8.startAnimation(anim7);

			b9.startAnimation(anim8);

			bminus.startAnimation(anim9);

			bplus.startAnimation(anim10);

			bmultiply.startAnimation(anim11);

			bdivide.startAnimation(anim12);
		}

	}
	
	
	public void stopAnimation(){
		animation.cancel();
		b1.clearAnimation();
		b2.clearAnimation();
		b3.clearAnimation();
		b4.clearAnimation();
		b5.clearAnimation();
		b6.clearAnimation();
		b7.clearAnimation();
		b8.clearAnimation();
		b9.clearAnimation();
		bplus.clearAnimation();
		bdivide.clearAnimation();
		bmultiply.clearAnimation();
		bminus.clearAnimation();
		
		
		
		
//		anim1.cancel();
//		anim2.cancel();
//		anim3.cancel();
//		anim4.cancel();
//		anim5.cancel();
//		anim6.cancel();
//		anim7.cancel();
//		anim8.cancel();
//		anim9.cancel();
//		anim10.cancel();
//		anim11.cancel();
//		anim12.cancel();
		
		
		
		
	}

	public void setGameLevel(int level) {

		Log.e(TAG, "Set Game Level");

		flageasy=false;
		flaglevel1=false;
		flaglevel2=false;
		flaglevel3=false;
		
		btnlevel1.setVisibility(View.GONE);
		btnlevel2.setVisibility(View.GONE);
		btnlevel3.setVisibility(View.GONE);
		btnleveleasy.setVisibility(View.GONE);

		switch (level) {

		case leveleasy:

			layoutanaswerer.setVisibility(View.VISIBLE);
			operatorsLayout.setVisibility(View.GONE);
			NumbersLayout.setVisibility(View.GONE);
			NumbersLayout1.setVisibility(View.VISIBLE);
			
			flageasy = true;

			break;

		case level1:

			
			
			
			b6.setVisibility(View.VISIBLE);
			b7.setVisibility(View.VISIBLE);
			b8.setVisibility(View.VISIBLE);
			b9.setVisibility(View.VISIBLE);
			
			if(animation!=null)
				stopAnimation();
			
			layoutanaswerer.setVisibility(View.VISIBLE);
			NumbersLayout1.setVisibility(View.GONE);
			NumbersLayout.setVisibility(View.VISIBLE);

			flaglevel1 = true;
			
			

			break;

		case level2:

			
			b6.setVisibility(View.GONE);
			b7.setVisibility(View.GONE);
			b8.setVisibility(View.GONE);
			b9.setVisibility(View.GONE);
			
			if(animation!=null)
			stopAnimation();
			
			layoutanaswerer.setVisibility(View.VISIBLE);
			operatorsLayout1.setVisibility(View.GONE);
			NumbersLayout1.setVisibility(View.GONE);
			NumbersLayout.setVisibility(View.VISIBLE);

			
			
			flaglevel2 = true;
			loadAnimation();
			playAnimation();

			
			
			break;

		case level3:

			if(animation!=null)
			stopAnimation();
			
			layoutanaswerer.invalidate();
			NumbersLayout.invalidate();
			
			layoutanaswerer.setVisibility(View.VISIBLE);
			operatorsLayout1.setVisibility(View.GONE);
			NumbersLayout1.setVisibility(View.GONE);
			NumbersLayout.setVisibility(View.VISIBLE);

			flaglevel3 = true;

			loadAnimation();
			playAnimation();
			
			
			
			break;
			
			default:
				break;

		}
	}
	
	private void backtomenu() {
		// TODO Auto-generated method stub
		flageasy=false;
		flaglevel1=false;
		flaglevel2=false;
		flaglevel3=false;
		
		btnlevel1.setVisibility(View.VISIBLE);
		btnlevel2.setVisibility(View.VISIBLE);
		btnlevel3.setVisibility(View.VISIBLE);
		btnleveleasy.setVisibility(View.VISIBLE);
		
		layoutanaswerer.setVisibility(View.GONE);
		
		
		
	}

}

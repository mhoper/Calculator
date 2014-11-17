package com.legendleo.calculator;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	//private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	//private Button btnPlus, btnMinus, btnMutiply, btnDivide, btnPoint, btnDenominator, btnEqual, btnBackspace;
	private TextView tv;
	private String tvValue = "";
	private String tvText = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.tv);
		findViewById(R.id.num0).setOnClickListener(this);
		findViewById(R.id.num1).setOnClickListener(this);
		findViewById(R.id.num2).setOnClickListener(this);
		findViewById(R.id.num3).setOnClickListener(this);
		findViewById(R.id.num4).setOnClickListener(this);
		findViewById(R.id.num5).setOnClickListener(this);
		findViewById(R.id.num6).setOnClickListener(this);
		findViewById(R.id.num7).setOnClickListener(this);
		findViewById(R.id.num8).setOnClickListener(this);
		findViewById(R.id.num9).setOnClickListener(this);
		findViewById(R.id.point).setOnClickListener(this);
		findViewById(R.id.plus).setOnClickListener(this);
		findViewById(R.id.minus).setOnClickListener(this);
		findViewById(R.id.mutiply).setOnClickListener(this);
		findViewById(R.id.divide).setOnClickListener(this);
		findViewById(R.id.denominator).setOnClickListener(this);
		findViewById(R.id.backspace).setOnClickListener(this);
		findViewById(R.id.equal).setOnClickListener(this);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.num0:
			getInputNums("0");
			break;
		case R.id.num1:
			getInputNums("1");
			break;
		case R.id.num2:
			getInputNums("2");
			break;
		case R.id.num3:
			getInputNums("3");
			break;
		case R.id.num4:
			getInputNums("4");
			break;
		case R.id.num5:
			getInputNums("5");
			break;
		case R.id.num6:
			getInputNums("6");
			break;
		case R.id.num7:
			getInputNums("7");
			break;
		case R.id.num8:
			getInputNums("8");
			break;
		case R.id.num9:
			getInputNums("9");
			break;
		case R.id.point: 
			getInputNums(".");
			break;
		case R.id.plus:
			tvText += tvValue + "+";
			tvValue = "";
			break;
		case R.id.minus:
			tvText += tvValue + "-";
			tvValue = "";
			break;
		case R.id.mutiply:
			tvText += tvValue + "*";
			tvValue = "";
			break;
		case R.id.divide:
			tvText += tvValue + "/";
			tvValue = "";
			break;
		case R.id.backspace:
			tvValue = "0";
			tvText = "";
			break;
		}
		tv.setText(tvText + tvValue);
		
		switch (v.getId()) {
		case R.id.equal:
			Double result = getResultNum();
			tv.setText(result.toString());
			
			tvValue = "";
			tvText = result.toString();
			break;

		case R.id.denominator:
			if(getResultNum() == 0){
				tv.setText("Infinity");
				tvValue = "";
				tvText = "";
			}else{
				Double temp = 1/getResultNum();
				tv.setText(temp.toString());
				
				tvValue = "";
				tvText = temp.toString();
			}
			break;
		}
	}
	
	//获取输入的数字及小数点
	private void getInputNums(String s){
		if(tvValue == "" || tvValue.length() <= 0){
			tvValue = "0";
		}
		//当已经输入过小数时，则不允许再次输入
		if(s == "."){
			if(tvValue.indexOf(".") == -1){
				tvValue += ".";
			}
		}else{
			//当首位为0时，自动去掉0
			if(tvValue == "0"){
				tvValue = s;
			}else{
				tvValue += s;
			}
		}
	}
	
	//调用方法
	private Double getResultNum(){

		Calculator cal = new Calculator();
		String s = tvText + tvValue;
		if(s == null || s.length() <= 0){
			s = "0";
		}
		Double result = cal.calculate(s);
		return result;
	}

}

package com.michbuze.burgermemory;

import java.util.Random;

import com.michbuze.burgermemory.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
//import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class MemoryBurgerAndroid extends Activity implements OnClickListener {

	  static int random1;
	  static int random2;    

	  static int step = 0;
	  static int orders[] = new int[99];
	  static Random rand = new Random(); 
	
	  static String[] words = {
		    "un burger", //0
		    "une pizza", //1
		    "des frites", //2
		    "un soda", //3
		    "un hotdog", //4
		    "une glace", //5
		    "un donut", //6
		    "un esquimeau", //7
		    "une tarte"}; //8
	  
	  static ImageButton icon1;
	  static ImageButton icon2;
	  static TextView stepLabel;
	  static AlertDialog ad;

	  public static void set_icon(ImageButton icon, int i)
	  {
		    switch (i)
		    {
		    case 0:
		      icon.setImageResource(R.drawable.i0);
		      break;
		    case 1:
		      icon.setImageResource(R.drawable.i1);
		      break;
		    case 2:
		      icon.setImageResource(R.drawable.i2);
		      break;
		    case 3:
		      icon.setImageResource(R.drawable.i3);
		      break;
		    case 4:
		      icon.setImageResource(R.drawable.i4);
		      break;
		    case 5:
		      icon.setImageResource(R.drawable.i5);
		      break;
		    case 6:
		      icon.setImageResource(R.drawable.i6);
		      break;
		    case 7:
		      icon.setImageResource(R.drawable.i7);
		      break;
		    case 8:
		      icon.setImageResource(R.drawable.i8);
		      break;		      
		    }	  
	  }
	  public static void do_order() {
		    step++;
		    random1 = rand.nextInt(9);
		    random2 = rand.nextInt(9);
		    orders[2 * step - 2] = random1;
		    orders[2 * step - 1] = random2;
		  
		    stepLabel.setText("Le client "  + ((2 * step - 2) + 1)  + " commande " + words[random1]
		    + ".\nLe client "  + ((2 * step - 1) + 1)  + " commande " + words[random2]
		    + ".\nQue servez vous au client " + step + " ?");

		    set_icon(icon1, random1);
		    set_icon(icon2, random2);
		  }

	    public void gameover(int id) {

	    	stepLabel.setText("Perdu !" 
	    	+ "\nLe client "  + step  + " voulait " + words[orders[step - 1]]
		    + "...\nVous lui avez servi " + words[id] + "...");
	    	
		    set_icon(icon1, orders[step - 1]);
		    set_icon(icon2, id);
			    
	        ad = new AlertDialog.Builder(this).create();  
	        ad.setCancelable(false); // This blocks the 'BACK' button  
	        ad.setMessage("Rejouer ?");  
	        ad.setButton("Oui", new DialogInterface.OnClickListener() {  
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	                dialog.dismiss();
	    	        step = 0;
	    	        do_order();
	            }  
	        });  
	        
	      }
	  
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.activity_memory_burger_android);
	        
	        Resources re = getBaseContext().getResources();
	        Drawable drawImage = re.getDrawable(R.drawable.ic_launcher);
	 
	        
		    stepLabel = (TextView) findViewById(R.id.stepLabel);
		    //order1 = (TextView) findViewById(R.id.order1);
		    //order2 = (TextView) findViewById(R.id.order2);
		    		    
		    icon1 = (ImageButton) findViewById(R.id.icon1);
		    icon2 = (ImageButton) findViewById(R.id.icon2);
		    	        
	        ImageButton b0 = (ImageButton) findViewById(R.id.id0);	        
	        b0.setOnClickListener(this);
		    ImageButton b1 = (ImageButton) findViewById(R.id.id1);	        
		    b1.setOnClickListener(this);
		    ImageButton b2 = (ImageButton) findViewById(R.id.id2);	        
		    b2.setOnClickListener(this);

		    ImageButton b3 = (ImageButton) findViewById(R.id.id3);		    
	        b3.setOnClickListener(this);
		    ImageButton b4 = (ImageButton) findViewById(R.id.id4);	        
		    b4.setOnClickListener(this);
		    ImageButton b5 = (ImageButton) findViewById(R.id.id5);	        
		    b5.setOnClickListener(this);

		    ImageButton b6 = (ImageButton) findViewById(R.id.id6);		    
	        b6.setOnClickListener(this);
		    ImageButton b7 = (ImageButton) findViewById(R.id.id7);	        
		    b7.setOnClickListener(this);
		    ImageButton b8 = (ImageButton) findViewById(R.id.id8);	        
		    b8.setOnClickListener(this);
		    
		    do_order();		    
	    }
	
	    
  public void onClick(View v) {
	int id = -1;
		
	switch (v.getId()) {

	  case R.id.id0:
		    id = 0;
			break;
	  case R.id.id1:
		    id = 1;
			break;
	  case R.id.id2:
		    id = 2;
			break;
	  case R.id.id3:
		    id = 3;
			break;
	  case R.id.id4:
		    id = 4;
			break;
	  case R.id.id5:
		    id = 5;
			break;
	  case R.id.id6:
		    id = 6;
			break;
	  case R.id.id7:
		    id = 7;
			break;
	  case R.id.id8:
		    id = 8;
			break;
    }
	if (orders[step - 1] == id) {
		do_order();
	} else {
		gameover(id);
		
	    try {
            synchronized(this){
                wait(1500);
            }
        }
        catch(InterruptedException ex){                    
        }
        
        ad.show();  		        		
	}
  }
}
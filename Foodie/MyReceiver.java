package android.example.foodiemp5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MyReceiver extends BroadcastReceiver {
    private Context context;
    protected static final String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";
    private int randomNum;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String intentAction = intent.getAction();
        displayToast(intentAction);
        Random random     = new Random();
        randomNum     = random.nextInt(4);
    }

    String [] details = new String[4];
    String [] titles  = new String[4];


    public void sendDetails(){

        details[0] = "There are 42 calories in a kiwi. The best part is there is no recipe required, just peel, slice and eat. They go well mixed in with yogurts and other fruits and granola. All you have to do is find your local market and purchase some.";
        details[1] = "They're about 250 calories in a slice of lasagne pizza. To make a lasagna pizza pie you need beef or sausage, clover garlics, dried oregano, dried basil,  mascarpone, ricotta, cottage, or cream cheese, shredded mozzarella cheese,shredded parmesan cheese. Here's a link to a recipe.\n" +
                "https://thesaltymarshmallow.com/amazing-lasagna-pizza-recipe/";
        details[2] = "There is 356 calories in a serving of french fries. The only ingredients needed for french fries are sliced potatoes and canola oil or peanut oil, and if you like salt on your fries then you can add that in the end. Here's a link to a recipe.\n" +
                "https://www.foodnetwork.com/recipes/ree-drummond/perfect-french-fries-recipe2-2120420";
        details[3] = "The calories in a mofongo range on the serving size and type of meat used. The tradition plantain mofongo has about 620 calories. The ingredients are green plantains, garlic paste, pork rinds, and vegetable oil.\n https://www.thespruceeats.com/traditional-mofongo-recipe-2138186";

        titles[0] = "Kiwi";     titles[1] = "Pizza";    titles[2] = "French Fries"; titles[3] = "Mofongo";

        String addDetail = details[randomNum];
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra("title", titles[randomNum]);
        detailIntent.putExtra("details", addDetail);
        context.startActivity(detailIntent);

    }

    private void displayToast(String intentAction) {

        switch (intentAction) {
            case ACTION_CUSTOM_BROADCAST:
                Toast.makeText(context, "Happy cooking "+ titles[randomNum], Toast.LENGTH_SHORT).show();
                sendDetails();
                break;

            case Intent.ACTION_BATTERY_LOW:
                Log.d("BROADCAST", "Low Battery");
                break;
        }

    }
}
package android.example.foodiemp5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Food> FoodData;
    private Context fContext;
    private ImageView mFoodImage;
    private String description;

    /**
     * Constructor that passes in the food data and the context.
     */
    public FoodAdapter(Context context, ArrayList<Food> foodData) {
        this.FoodData = foodData;
        this.fContext = context;
    }

    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View
     */
    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(fContext).
                inflate(R.layout.list_item, parent, false));
    }

    /** Required method that binds the data to the viewholder.  */
    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        // Get current food.
        Food currentFood = FoodData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentFood);
    }

    /** Required method for determining the size of the data set.   */
    @Override
    public int getItemCount() {
        return FoodData.size();
    }

    /** ViewHolder class that represents each row of data in the RecyclerView.  */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;

        /** Constructor for the ViewHolder, used in onCreateViewHolder(). @param itemView The rootview of the list_item.xml layout file.    */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mFoodImage = itemView.findViewById(R.id.foodImage);

            //set listener to view
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        //Binding data to each view
        void bindTo(Food currentFood) {
            // Populate the textviews with data.
            mTitleText.setText(currentFood.getTitle());
            mInfoText.setText(currentFood.getInfo());

            //this gets the image resource from the Sport object and load it into the ImageView using Glide:
            Glide.with(fContext).load(currentFood.getImageResource()).into(mFoodImage);
        }

        @Override
        public void onClick(View v) {
            Food currentFood = FoodData.get(getAdapterPosition());
            Intent detailIntent = new Intent(fContext, DetailActivity.class);
            detailIntent.putExtra("title", currentFood.getTitle());
            detailIntent.putExtra("image_resource", currentFood.getImageResource());

            //create method to get correct details then send it to DetailActivity in .putExtra
            getDetails(currentFood);
            detailIntent.putExtra("details", description);
            fContext.startActivity(detailIntent);
        }

        //method created to choose details to sent to DetailActivity
        private void getDetails(Food currentFood) {
            if (currentFood.getTitle().equalsIgnoreCase("kiwi")) {
                description = "There are 42 calories in a kiwi. The best part is there is no recipe required, just peel, slice and eat. They go well mixed in with yogurts and other fruits and granola. All you have to do is find your local market and purchase some.";
            } else if (currentFood.getTitle().equalsIgnoreCase("pizza")) {
                description = "They're about 250 calories in a slice of lasagne pizza. To make a lasagna pizza pie you need beef or sausage, clover garlics, dried oregano, dried basil,  mascarpone, ricotta, cottage, or cream cheese, shredded mozzarella cheese,shredded parmesan cheese. Here's a link to a recipe.\n" +
                        "https://thesaltymarshmallow.com/amazing-lasagna-pizza-recipe/";
            } else if (currentFood.getTitle().equalsIgnoreCase("french fries")) {
                description = "There is 356 calories in a serving of french fries. The only ingredients needed for french fries are sliced potatoes and canola oil or peanut oil, and if you like salt on your fries then you can add that in the end. Here's a link to a recipe.\n" +
                        "https://www.foodnetwork.com/recipes/ree-drummond/perfect-french-fries-recipe2-2120420";
            } else
                description = "The calories in a mofongo range on the serving size and type of meat used. The tradition plantain mofongo has about 620 calories. The ingredients are green plantains, garlic paste, pork rinds, and vegetable oil.\n https://www.thespruceeats.com/traditional-mofongo-recipe-2138186";
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }

    }

}

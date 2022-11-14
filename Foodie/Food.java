package android.example.foodiemp5;

public class Food {
    //added, represent the details
    private String details;
    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private final int imageResource;


    Food(String title, String info, int imageResource) {
        this.title         = title;
        this.info          = info;
        this.imageResource = imageResource;
    }

    Food(String title, String info, String details, int imageResource) {
        this.title         = title;
        this.info          = info;
        this.details       = details;
        this.imageResource = imageResource;
    }


    public int getImageResource() {
        return imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }
}

package brkckr.first.eleven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;
import java.util.List;

public class CreateLineUpActivity extends AppCompatActivity {

    List<StaticPlayer> playerList;
    LinearLayoutCompat llContainer;

    AppCompatTextView txtClub;
    AppCompatTextView txtCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_line_up);
        llContainer = findViewById(R.id.llContainer);
        txtClub = findViewById(R.id.txtClub);
        txtCoach = findViewById(R.id.txtCoach);

        playerList = createStaticPlayerList();

        createFirstEleven(new StaticFirstEleven("Galatasaray", "Fatih Terim", "4-1-4-1", playerList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.formation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        cleanContainer();
        createFirstEleven(new StaticFirstEleven("Galatasaray", "Fatih Terim", item.getTitle().toString(), playerList));
        return true;
    }


    /**
     * creates sample data
     * <p>
     * starts from the goalkeeper, lining up towards the strikers.
     * works from right to left for each position.
     * To make the placement on the screen properly, you must set the StaticPlayer list according to the above.
     *
     * @return
     */
    private List<StaticPlayer> createStaticPlayerList()
    {
        List<StaticPlayer> playerList = new ArrayList<>();

        playerList.add(new StaticPlayer("Muslera", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_9ofktlxhrtzgvhm5btvvlq6l1.png?v=1.69.0&gis=mk", 1));
        playerList.add(new StaticPlayer("Omar", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_9kv7at589nwkud8a1hmgkspn9.png?v=1.69.0&gis=mk", 3));
        playerList.add(new StaticPlayer("Luyindama", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_b2c57cig5rvn9tnwwlifoewo9.png?v=1.69.0&gis=mk", 27));
        playerList.add(new StaticPlayer("Marcao", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_1ghrcpi1cl0koh36puwblbfmd.png?v=1.69.0&gis=mk", 45));
        playerList.add(new StaticPlayer("Saracchi", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_9qmyubxwvhhotzx7elbuzn7jd.png?v=1.69.0&gis=mk", 36));
        playerList.add(new StaticPlayer("Taylan", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_cvp10zci3p7gg5gi9bmbgj6qd.png?v=1.69.0&gis=mk", 4));
        playerList.add(new StaticPlayer("Feghouli", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_8lk61g6juvnra8cdaq1cgho7p.png?v=1.69.0&gis=mk", 89));
        playerList.add(new StaticPlayer("Belhanda", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_3q5uf1exb4scte1iyeq5aa7yt.png?v=1.69.0&gis=mk", 10));
        playerList.add(new StaticPlayer("Emre", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_c6rzadc9bx74tw06zdubmtc5x.png?v=1.69.0&gis=mk", 54));
        playerList.add(new StaticPlayer("Arda", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_doiy4u54g1mxykz1z19d6tgwl.png?v=1.69.0&gis=mk", 66));
        playerList.add(new StaticPlayer("Falcao", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_8oc3lpz3xttrnwxk1fnnv7owl.png?v=1.69.0&gis=mk", 9));

        return playerList;
    }

    /**
     * creates first eleven
     *
     * @param firstEleven
     */
    private void createFirstEleven(StaticFirstEleven firstEleven)
    {
        setClub(firstEleven.club, firstEleven.formation);
        setCoach(firstEleven.coach);

        addPositions(firstEleven.formation);
        addStaticPlayers(firstEleven);
    }

    /**
     * adds the positions to the field
     * each position means a LinearLayout
     * <p>
     * for 4-1-4-1, adds 5 positions from goalkeeper to striker
     */
    private void addPositions(String formation)
    {
        //adds one more position
        //just wanna make it like that
        //you can change it
        for (int i = 0; i <= getPositionCount(formation); i++)
        {
            LinearLayoutCompat layoutPosition = new LinearLayoutCompat(this);

            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 0);
            layoutParams.weight = 1;
            layoutPosition.setLayoutParams(layoutParams);
            layoutPosition.setGravity(Gravity.CENTER);
            layoutPosition.setOrientation(LinearLayoutCompat.HORIZONTAL);

            layoutPosition.setId(i);

            llContainer.addView(layoutPosition);
        }
    }

    /**
     * adds StaticPlayers to the field. with perticular position
     *
     * @param firstEleven
     */
    private void addStaticPlayers(StaticFirstEleven firstEleven)
    {
        int[] formationArray = getFormationArray(firstEleven.formation.split("-"));

        //starting index
        //end index
        // for ex : 4-1-4-1 means 4 defenders so defender position has 4 StaticPlayers
        // start = 0 was goalkeeper.
        // start = 1 is the first defender index in StaticPlayer list.
        // end = 5 is the last defender index in StaticPlayer list
        int start = 0;
        int end = 0;

        for (int i = 0; i < formationArray.length; i++)
        {
            start = end;
            end += formationArray[i];

            final LinearLayoutCompat positionLayout = llContainer.findViewById(i);

            for (int j = start; j < end; j++)
            {
                //adds staticPlayer container layout to the position layout.
                final LinearLayoutCompat staticPlayerContainerLayout = getStaticPlayerContainerLayout(i);
                positionLayout.addView(staticPlayerContainerLayout);

                final LinearLayoutCompat staticPlayerLayout = getStaticPlayerLayout(firstEleven.staticPlayerList.get(j));

                //adds with delay.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        staticPlayerContainerLayout.addView(staticPlayerLayout);
                    }
                }, 500 + 250 * j);
            }
        }
    }

    private LinearLayoutCompat getStaticPlayerContainerLayout(int index)
    {
        LinearLayoutCompat staticPlayerContainerLayout = new LinearLayoutCompat(this);
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(0, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        staticPlayerContainerLayout.setLayoutParams(layoutParams);
        staticPlayerContainerLayout.setGravity(Gravity.CENTER);

        staticPlayerContainerLayout.setId(index);

        return staticPlayerContainerLayout;
    }

    private LinearLayoutCompat getStaticPlayerLayout(StaticPlayer staticPlayer)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayoutCompat layout = (LinearLayoutCompat) inflater.inflate(R.layout.layout_player, null, false);

        setStaticPlayer(layout, staticPlayer);

        return layout;
    }

    /**
     * sets StaticPlayer's data
     *
     * @param layout
     * @param staticPlayer
     */
    private void setStaticPlayer(LinearLayoutCompat layout,final StaticPlayer staticPlayer)
    {
        AppCompatImageView imgProfilePicture = layout.findViewById(R.id.imgProfilePicture);

        Glide.with(this).load(staticPlayer.profilePictureUrl).transform(new CenterCrop(), new RoundedCorners(10)).into(imgProfilePicture);

        AppCompatTextView txtName = layout.findViewById(R.id.txtName);
        txtName.setText(staticPlayer.name);
        AppCompatTextView txtNumber = layout.findViewById(R.id.txtNumber);
        txtNumber.setText(String.valueOf(staticPlayer.number));

        imgProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =staticPlayer.name;
                Toast.makeText(CreateLineUpActivity.this, "Player:"+name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCoach(String coach)
    {
        txtCoach.setText(coach);
    }

    private void setClub(String club, String formation)
    {
        txtClub.setText(club + " (" + formation + ")");
    }

    private int getPositionCount(String formation)
    {
        int[] formationArray = getFormationArray(formation.split("-"));
        return formationArray.length;
    }

    /**
     * gets formation array as int array
     * first element of the array is the goalkeeper.
     *
     * @param stringArray : formation as string array
     * @return
     */
    private int[] getFormationArray(String[] stringArray)
    {
        int[] numberArray = new int[stringArray.length + 1];
        numberArray[0] = 1; // here is the goalkeeper.

        // adds the numbers from formation (for ex : 4-1-4-1)
        for (int i = 1; i < numberArray.length; i++)
        {
            numberArray[i] = Integer.parseInt(stringArray[i - 1]);
        }

        return numberArray;
    }

    private void cleanContainer()
    {
        llContainer.removeAllViews();
    }
}
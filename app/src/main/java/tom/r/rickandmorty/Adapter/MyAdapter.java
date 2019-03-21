package tom.r.rickandmorty.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;
import tom.r.rickandmorty.Model.Character;
import java.util.List;
import tom.r.rickandmorty.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Character> listCharacters;
    private Context context;

    public MyAdapter(List<Character> listCharacters, Context context) {
        this.listCharacters = listCharacters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.character_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Character character = listCharacters.get(i);

        viewHolder.character_name.setText(""+character.getName());

        //Picasso.get().load(character.getImage()).into(viewHolder.character_image);
    }

    @Override
    public int getItemCount() {
        return listCharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView character_name;
        public ImageView character_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            character_name = (TextView) itemView.findViewById(R.id.character_name);
            character_image = (ImageView) itemView.findViewById(R.id.character_image);
        }
    }
}

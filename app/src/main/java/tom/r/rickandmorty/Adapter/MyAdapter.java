package tom.r.rickandmorty.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import tom.r.rickandmorty.Model.Character;

import java.util.ArrayList;
import java.util.List;
import tom.r.rickandmorty.R;

/**
 * @author Tom
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Character> listCharacters;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int i);
    }

    public void setOnItemClickListener(OnItemClickListener listener){ //modif
        this.mListener = listener;
    }

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

        viewHolder.character_name.setText(character.getName());

        // Use Picasso to load image from API
        Picasso.get()
                .load(character.getImage())
                .into(viewHolder.character_image);
    }

    public void setFilter(List<Character> characters){
        listCharacters = new ArrayList<>();
        listCharacters.addAll(characters);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listCharacters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView character_name;
        public ImageView character_image;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            character_name = itemView.findViewById(R.id.character_name);
            character_image = itemView.findViewById(R.id.character_image);
            linearLayout = itemView.findViewById(R.id.linearLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

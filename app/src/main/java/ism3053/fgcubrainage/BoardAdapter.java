package ism3053.fgcubrainage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardVH> {

    // Left code for this class in for potential updates later on but not utilized due to incorrect
    // placement of layout.

    ArrayList<String> names, scores, gameModes;

    public BoardAdapter(ArrayList<String> names, ArrayList<String> scores, ArrayList<String> gameModes){
        this.names = names;
        this.scores = scores;
        this.gameModes = gameModes;
    }
    @NonNull
    @Override
    public BoardVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        return new BoardVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardVH holder, int position) {
        holder.name.setText(names.get(position));
        holder.score.setText(scores.get(position));
        holder.gameMode.setText(gameModes.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}

class BoardVH extends RecyclerView.ViewHolder{

    TextView name, score, gameMode;
    private BoardAdapter  adapter;

    public BoardVH(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.namePlace);
        score = itemView.findViewById(R.id.score);
        gameMode = itemView.findViewById(R.id.gameMode);
    }

    public BoardVH linkAdapter(BoardAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}

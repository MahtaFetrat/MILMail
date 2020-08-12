package ir.madeinlobby.milmail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {

    private ArrayList<Email> data;
    private LayoutInflater layoutInflater;

    public EmailAdapter(Context context, ArrayList<Email> data) {
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.email_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Email email = data.get(position);
        holder.sender.setText(email.getSender());
        holder.message.setText(email.getSubject());
        Picasso.get().load(email.getImageURL()).resize(150,150).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sender;
        TextView message;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender);
            message = itemView.findViewById(R.id.message);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}

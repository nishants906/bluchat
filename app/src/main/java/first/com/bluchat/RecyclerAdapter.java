package first.com.bluchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Test on 8/20/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context context;
    public int type1=1;
    public int type2=2;
    DBHandler db;

    List<String> recieve;
    List<String> send;



    public RecyclerAdapter(Context context) {
        this.context = context;
        db = new DBHandler(context);
        recieve = db.recieve_messages();
        send = db.send_messages();
        Log.d("position1", String.valueOf(recieve.size()));
        Log.d("position1", String.valueOf(send.size()));

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==type1){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_transfer_left,parent,false);
        RecyclerViewHolder recyclerviewholder=new RecyclerViewHolder(view,viewType);
        return recyclerviewholder;
        }

        else{
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_transfer_right,parent,false);
            RecyclerViewHolder recyclerviewholder=new RecyclerViewHolder(view,viewType);
            return recyclerviewholder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        DBHandler db=new DBHandler(context);

        if (holder.holderid == 1) {
            if(position<(recieve.size()*2)){
            holder.text1.setText(recieve.get(position/2));

                Log.d("position1", recieve.get(position/2));
            Log.d("recievecount", String.valueOf(position));
            }
        }
        else if (holder.holderid == 2) {

            if (position<(send.size()*2)) {
                holder.text2.setText(send.get(position / 2));
                Log.d("sendcount", String.valueOf(position));
                Log.d("position1", send.get(position/2));
            }
        }
    }

    @Override
    public int getItemCount() {
        int total=Integer.valueOf(String.valueOf(recieve.size()))+Integer.valueOf(String.valueOf(send.size()));
        Log.d("total", String.valueOf(total));
        return total;
    }

    @Override
    public int getItemViewType(int position){
        if(position%2==0){
            return type1;
        }
        else {
            return type2;
        }

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView text1;
        TextView text2;
        int holderid;

        public RecyclerViewHolder(View itemView,int viewtype) {
            super(itemView);
            if(viewtype==type1) {
                text1 = (TextView) itemView.findViewById(R.id.textl);
                holderid=1;
            }
            else{
            text2= (TextView) itemView.findViewById(R.id.textr);
            holderid=2;
            }
        }
    }
}

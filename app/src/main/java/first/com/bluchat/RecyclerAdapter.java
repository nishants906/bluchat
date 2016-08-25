package first.com.bluchat;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;


/**
 * Created by Test on 8/20/2016.v
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    DBHandler db;


    List<String> messages = new ArrayList<>();
    List<String> status = new ArrayList<>();

    Context context;

    public RecyclerAdapter(Context context) {

        this.context = context;
        Log.d("contextc", String.valueOf(context));
        db = new DBHandler(context);
        Log.d("aceess12", String.valueOf(db.access_data()));
/*        List<String> mess = new ArrayList<>();
        List<String> response = new ArrayList<>();



        for (int i = 0; i < db.access_data().size(); i++) {

            Log.d("messageoutput", String.valueOf(messages.isEmpty()));
            Log.d("messageoutput", String.valueOf(i));

            mess.add(String.valueOf(db.access_data().get(i).get(0)));

            Log.d("messageoutput", String.valueOf(mess));

            response.add(String.valueOf(db.access_data().get(i).get(2)));

            Log.d("messagesinput", String.valueOf(db.access_data().get(i).get(0)));

            Log.d("messagesinput", String.valueOf(db.access_data().get(i).get(2)));
        }

        messages = mess;
        Log.d("messageoutput", String.valueOf(messages));

        status = response;
        Log.d("messageoutput", String.valueOf(status));
    }*/
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d("viewtypevalue", String.valueOf(viewType));

        if (String.valueOf(viewType).equals("1")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_transfer_left, parent, false);
            RecyclerViewHolder recyclerviewholder = new RecyclerViewHolder(view, viewType);
            return recyclerviewholder;
        } else if (String.valueOf(viewType).equals("2")){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_transfer_right, parent, false);
            RecyclerViewHolder recyclerviewholder = new RecyclerViewHolder(view, viewType);
            return recyclerviewholder;
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_transfer_right, parent, false);
            RecyclerViewHolder recyclerviewholder = new RecyclerViewHolder(view, viewType);
            return recyclerviewholder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        Log.d("holderid", String.valueOf(holder.holderid));
        if (String.valueOf(holder.holderid).equals("1")) {

            holder.text1.setText(db.access_data().get(position).get(0));


            Log.d("recievecount", String.valueOf(position));

        } else if (String.valueOf(holder.holderid).equals("2")) {

            holder.text2.setText(db.access_data().get(position).get(0));
            Log.d("sendcount", String.valueOf(position));

        }
    }

    @Override
    public int getItemCount() {
        int total = db.access_data().size();
        Log.d("totalsize1", String.valueOf(total));
        return total;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("status12", db.access_data().get(position).get(2));
        if (String.valueOf(db.access_data().get(position).get(2)).equals("recieve")) {
            return 1;
        } else if (db.access_data().get(position).get(2).equals("send")) {
            return 2;
        } else
            return 3;

    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;
        int holderid;

        public RecyclerViewHolder(View itemView, int viewtype) {
            super(itemView);
            Log.d("viewtype1", String.valueOf(viewtype));

            if (String.valueOf(viewtype).equals("1")) {
                text1 = (TextView) itemView.findViewById(R.id.textl);
                holderid = 1;
            } else if (String.valueOf(viewtype).equals("2")) {
                text2 = (TextView) itemView.findViewById(R.id.textr);
                holderid = 2;
            }
        }
    }
}

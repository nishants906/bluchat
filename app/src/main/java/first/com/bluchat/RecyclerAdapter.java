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

    public int type1=1;
    public int type2=2;
    DBHandler db;


    List<String> messages=new ArrayList<>();
    List<String> status=new ArrayList<>();
    List<List<String>>data= new ArrayList<List<String>>();

    Context context;

    public RecyclerAdapter(Context context) {

        this.context=context;
        Log.d("contextc",String.valueOf(context));
        db = new DBHandler(context);
        data=db.access_data();
        Log.d("aceess12", String.valueOf(data));
        List<String> mess=new ArrayList<>();
        List<String> response=new ArrayList<>();

        for(int i=0; i<data.size() ;i++ ){

            Log.d("messageoutput",String.valueOf(messages.isEmpty()));
            Log.d("messageoutput",String.valueOf(i));

            mess.add(String.valueOf(data.get(i).get(0)));

            Log.d("messageoutput", String.valueOf(mess));

            response.add(String.valueOf(data.get(i).get(2)));

            Log.d("messagesinput", String.valueOf(data.get(i).get(0)));
        
          Log.d("messagesinput", String.valueOf(data.get(i).get(2)));
        }

        messages=mess;
        Log.d("messageoutput",String.valueOf(messages));

        status=response;
        Log.d("messageoutput",String.valueOf(status));
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

        Log.d("holderid", String.valueOf(holder.holderid));
        if (holder.holderid == 1) {

            holder.text1.setText(messages.get(position));

            Log.d("flag", status.get(position));
            Log.d("recievecount", String.valueOf(position));

        }
        else if (holder.holderid == 2) {

            holder.text2.setText(messages.get(position));
            Log.d("sendcount", String.valueOf(position));
            Log.d("position1", status.get(position));

        }
    }

    @Override
    public int getItemCount() {
        int total=data.size();
        Log.d("totalsize1", String.valueOf(total));
        return total;
    }

    @Override
    public int getItemViewType(int position){
        Log.d("status",status.get(position));
        if(status.get(position) == "recieve"){
            return 1;
        }
        else if(status.get(position) == "send"){
            return 2;
        }
        else
            return 2;

    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView text1;
        TextView text2;
        int holderid;

        public RecyclerViewHolder(View itemView,int viewtype) {
            super(itemView);
            if(viewtype==1) {
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

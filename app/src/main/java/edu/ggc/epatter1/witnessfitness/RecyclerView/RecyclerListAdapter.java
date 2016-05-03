package edu.ggc.epatter1.witnessfitness.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.ggc.epatter1.witnessfitness.Activity.EditExerciseActivity;
import edu.ggc.epatter1.witnessfitness.Model.Exercise;
import edu.ggc.epatter1.witnessfitness.R;
import edu.ggc.epatter1.witnessfitness.RecyclerView.helper.ItemTouchHelperAdapter;
import edu.ggc.epatter1.witnessfitness.RecyclerView.helper.ItemTouchHelperViewHolder;
import edu.ggc.epatter1.witnessfitness.RecyclerView.helper.OnStartDragListener;
import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;


public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>
        implements ItemTouchHelperAdapter {

    private static final String TAG = "RecyclerListAdapter";

    private final List<Exercise> mItems;
    private ExerciseSequence exerciseRoutine;

    private final OnStartDragListener mDragStartListener;

    public RecyclerListAdapter(Context context, OnStartDragListener dragStartListener) {
        mDragStartListener = dragStartListener;

        //mItems.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items)));
        exerciseRoutine = ExerciseSequence.getInstance(context);
        mItems = exerciseRoutine.getExercises();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position).getName());

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                } else {


                    List<String> mExercisesIds = new ArrayList<>();

                    Log.d(TAG, "onTouch: mItem size: " + mItems.size());
                    for (int i = 0; i < mItems.size(); i++) {
                        Log.d(TAG, "onTouch: i:" + i);
                        if (mItems.get(i).getId() == null) {
                            Log.d(TAG, "onTouch: i is null: " + i + " -- " + mItems.get(i));
                            continue;
                        }

                        Log.d(TAG, "onTouch: " + mItems.get(i).toString());
                        Log.d(TAG, "createMainListString: index:" + i + " -- " + mItems.get(i).getId().toString());
                        String uuid = mItems.get(i).getId().toString();

                        mExercisesIds.add(uuid);
                    }

                    //TODO ExerciseSequence.savedIds() needs to be called here
                }
                return false;
            }
        });

        holder.cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "onTouch: cardview touched for item:" + holder.getLayoutPosition());
                    Intent intent = new Intent(v.getContext(), EditExerciseActivity.class);

                    //Fixed this is where the recycler tells which item we pressed to edit.
                    //Fixed Need to replace EditExerciseActivity.KEY_EDIT_POSITION with a string, and then need to handle intent.getExtra on the EditExercise.onCreate()
                    ExerciseSequence.getInstance(v.getContext()).setCurrentExerciseByPostion(holder.getLayoutPosition());
                    //intent.putExtra(EditExerciseActivity.KEY_EDIT_POSITION, holder.getLayoutPosition());
                    v.getContext().startActivity(intent);
                }
                return false;
            }
        });


    }

    /**
     * Required method for interface ItemTouchHelperAdapter
     * @param position The position of the item dismissed.
     *
     */
    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);

        //important to tell adapter we made a change
        // "It’s also important to note that we’re changing the position of the
        // item every time the view is shifted to a new index, and not at the end of a “drop” event."  -- Paul Burke
        notifyItemRemoved(position);

    }

    /**
     * Required method for interface ItemTouchHelperAdapter
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     * @return
     */
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);

        //important to tell adapter we made a change
        // "It’s also important to note that we’re changing the position of the
        // item every time the view is shifted to a new index, and not at the end of a “drop” event."  -- Paul Burke
        notifyItemMoved(fromPosition, toPosition);

        return true;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final TextView textView;
        public final ImageView handleView;
        public final CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            handleView = (ImageView) itemView.findViewById(R.id.handle);
            cardView = (CardView) itemView.findViewById(R.id.item);

            cardView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }

        /**
         * This just changes the background gray when the handleView is selected see ItemTouchHelperViewHolder.
         * No setOnClick() type activities here. See onBindViewHolder() above in the RecyclerListAdapter.java
         */
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        /**
         *  Part of the ItemTouchHelperViewHolder for just changing background
         */
        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }



    }
}

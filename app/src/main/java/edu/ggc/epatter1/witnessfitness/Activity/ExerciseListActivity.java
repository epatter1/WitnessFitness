package edu.ggc.epatter1.witnessfitness.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import edu.ggc.epatter1.witnessfitness.Model.Exercise;
import edu.ggc.epatter1.witnessfitness.Model.ExerciseSequence;
import edu.ggc.epatter1.witnessfitness.R;
/**
 * Attibution to the Big Nerd Ranch book: Ch. 9
 * Also, props to Patrik W. for his assistance on helping me
 * translate my previous ListView to a RecyclerView
 */
import static edu.ggc.epatter1.witnessfitness.R.layout.activity_exercise_sequence_list;

public class ExerciseListActivity extends AppCompatActivity {

    private RecyclerView mExerciseRecyclerView;
    private ExerciseAdapter mAdapter;
    private static String TAG = "ExerciseListActivity";
//    private final View.OnClickListener mOnClickListener = new On

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_exercise_sequence_list);


  // was trying to go to EditExerciseActivity from clicking an item in list

        mExerciseRecyclerView = (RecyclerView) findViewById(R.id.exercise_recycler_view);
        mExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(ExerciseListActivity.this));

        updateUI();
    }

    private void updateUI() {
        ExerciseSequence exerciseSequence = ExerciseSequence.getInstance(ExerciseListActivity.this);
        List<Exercise> exercises = exerciseSequence.getExercises();

        if (mAdapter == null) {
            mAdapter = new ExerciseAdapter(exercises);

            mExerciseRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }


    }

    private class ExerciseHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDescriptionTextview;
        private CheckBox mIsTimed;

        private Exercise mExercise;

        public ExerciseHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_exercise_title_text_view);
            mDescriptionTextview = (TextView)itemView.findViewById(R.id.list_item_exercise_description_text_view);
            mIsTimed = (CheckBox) itemView.findViewById(R.id.list_item_is_timed_check_box);
        }

        public void bindExercise(Exercise exercise) {
            mExercise = exercise;
            mTitleTextView.setText(mExercise.getName());
            mDescriptionTextview.setText(mExercise.getDescription());
            mIsTimed.setChecked(mExercise.isTimed());
        }
    }

    private class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder> {
        private List<Exercise> mExercises;
        private Context context;
//        private static  itemListener;

        public ExerciseAdapter(List<Exercise> exercises) {
            mExercises = exercises;
        }

        @Override
        public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ExerciseListActivity.this);
            View view = layoutInflater.inflate(R.layout.list_item_exercise, parent, false);
            return new ExerciseHolder(view);
        }

        @Override
        public void onBindViewHolder(ExerciseHolder holder, int position) {
            Exercise crime = mExercises.get(position);
            holder.bindExercise(crime);
        }

        @Override
        public int getItemCount() {
            return mExercises.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        updateUI();
    }
}
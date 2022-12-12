package com.bfcai.meals;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.squareup.picasso.Picasso;

import java.util.List;

/*

1- اول حاجه بعمل الكلاس الاساسي لي من بري و بعرف الفانكشن لي موجوده جواه
2- بعمل الكلاس لي جواه لي بعمل فيه الديزاين
3- بعرف في الكلاس الاساسي اذا كنت هستخدم layout وبعرفها و كذلك بعرف الكلاس لي جاي منه البياانات
4- بعمل كونستراكتور للبيانات ده عشان اعرفه انه اجباري يدخلها
5- بعد كدا في الفانكشن omCreate بقوله انهي ديزاين بالظبط لي هعرض فيه البيانات
6- في الفانكشن itemCount بقوله يعرضلي عدد البيانات لي جيالك من api
7- بعمل بقي في الكلاس الداخلي وبعرف العناصر و بقوله كل عنصر بيساوي انهي id في layout
8- بعمل في الفانكشن onBind بقوله يعرضلي بقي الفاريابل تبع الديزاين البيانات لي جايه من الكلاس
9- بروح لصفحه main وبعرف url لي بحيب منه الداتا
10 - بعرف الفانكشن لي هجيب بيها البيانات و بقوله انها من النوع Get عشان هتجيب البانات و هتجبهالي من url كذا
11- بقوله كل البيانات بقي لي في url ده من اول لاخر حاجه موجوده خزنهالي في كلاس لي انا عملته
12 - وبعد ما البيانات تتحزن هنرجع تاني للخطوه 7 بانه يعرضهالي في الديزاين
13 - ولما تحمل كلها وتتعرض هيخفيلي progress bar لي بيلف

*/
public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    // هنا دايما بيانات ثابته للادابتر بقوله حددلي الديزاين لي هظهر فيه الشكل
    // و بردك البيانات لي هتتعرض في الشكل ده وده بجيبها من كلاس بيبقي متعرف فيه كل البيانات
    LayoutInflater inflater;
    List<MealClass> meals;
    private Context context;
    private Activity activity;


    public MealAdapter(Context ctx, List<MealClass> meals, Context baseContext , Activity activity){
        this.context = baseContext;
        this.activity = activity;
        this.inflater = LayoutInflater.from(ctx);
        this.meals = meals;
    }




    // بعد ما عرفت ان هستخدم layout  فوق قولتله بقي انهي layout بالظبط هستخدمها
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_api_layout,parent,false);
        return new ViewHolder(view);
    }

    // بعد ما عرفت العناصر لي جوي الكلاس لي جاي من اللاي اوت
    // بربطها بالادابتر بحيث تتحفظ و تتعرضلي كلها
    // وده باني بقوله كل عنصر بيساوي ايه في class بتاعي
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // bind the data
        /*
        هنا بعد مبيخزن كل قيمه في class
        بقوله بقي اعرضلي القيم المتخزنه في الديزاين لي انا عاملاه

        */
        holder.mealTitle.setText(meals.get(position).getTitle());
        holder.mealPrice.setText(meals.get(position).getprice()+ "$");
        // picasso
        // ده مكتبه خارجيه بستخدمها عشان اعرض الصور بشكل اسرع وبحجم خفيف
        Picasso.get().load(meals.get(position).getCoverImage()).into(holder.mealCoverImage);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddWasafaty.class);
                intent.putExtra("title", String.valueOf(meals.get(holder.getAdapterPosition()).getTitle()));
                intent.putExtra("price", String.valueOf(meals.get(holder.getAdapterPosition()).getprice()));
                activity.startActivityForResult(intent, 1);
            }
        });

    }


    // فانكشن بجيب بيها عدد البيانات لي جايه من api
    @Override
    public int getItemCount() {
        return meals.size();
    }


    // ده بيبقي كلاس داخلي جوي الادابتر
    // لازمته اني بعرف بيه البيانات لي جيالي من الادابتر
    // وبقوله كل عنصر بستخدمه فين في id
    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView mealTitle,mealPrice;
        ImageView mealCoverImage;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealTitle = itemView.findViewById(R.id.songTitle);
            mealPrice = itemView.findViewById(R.id.songArtist);
            mealCoverImage = itemView.findViewById(R.id.coverImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            // handle onClick

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}

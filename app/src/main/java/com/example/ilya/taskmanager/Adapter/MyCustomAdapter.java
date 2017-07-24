package com.example.ilya.taskmanager.Adapter;

/**
 * Created by Ilya on 17.07.2017.
 */

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilya.taskmanager.Model.Task;
import com.example.ilya.taskmanager.R;
/*
public class MyCustomAdapter extends BaseAdapter {


    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Task> objects;

   public MyCustomAdapter(Context context, ArrayList<Task> tasks) {
        ctx = context;
        objects = tasks;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_view_item, parent, false);
        }

        Task t = getTask(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.tvDescr)).setText(t.taskName);

        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(t.image);

        CheckBox cbState = (CheckBox) view.findViewById(R.id.cbListItem);
        // присваиваем чекбоксу обработчик
        cbState.setOnCheckedChangeListener(myCheckChangeList);
        // пишем позицию
        cbState.setTag(position);
        // заполняем данными из task: выбран или нет
        cbState.setChecked(t.state);
        return view;
    }
    // task по позиции
    Task getTask(int position) {
        return ((Task) getItem(position));
    }

    // содержимое task
    ArrayList<Task> getTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task t : objects) {
            // если в выбран
            if (t.state)
                tasks.add(t);
        }
        return tasks;
    }

    // обработчик для чекбоксов
    OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            getTask((Integer) buttonView.getTag()).state = isChecked;
        }
    };
}*/

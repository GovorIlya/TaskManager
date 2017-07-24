package com.example.ilya.taskmanager.fragments;

/**
 * Created by Ilya on 08.07.2017.
 */
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.ilya.taskmanager.Adapter.MyCustomAdapter;
import com.example.ilya.taskmanager.CreateTaskActivity;
import com.example.ilya.taskmanager.EditTaskActivity;
import com.example.ilya.taskmanager.HttpClient;
import com.example.ilya.taskmanager.MainActivity;
import com.example.ilya.taskmanager.Model.Task;
import com.example.ilya.taskmanager.MyService;
import com.example.ilya.taskmanager.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ScreenOne extends Fragment {
    ArrayList<Task> tasks=new ArrayList<>();
    //MyCustomAdapter myCustomAdapter;
    ListView listView;
    List<Task> taskList;
    ArrayAdapter<Task> listViewAdapter;

    private FloatingActionButton fab;

    public ScreenOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_one, container,
                false);

        //startService(new Intent(getBaseContext(), MyService.class));
       // tasks.add(new Task("Task1",R.mipmap.ic_launcher_round,false));
       // tasks.add(new Task("Task2",R.mipmap.ic_launcher_round,true));
        //tasks.add(new Task("Task3",R.mipmap.ic_launcher_round,false));

       // myCustomAdapter=new MyCustomAdapter(getActivity(), tasks);

       // ListView listView=(ListView) rootView.findViewById(R.id.lvFragmentScreenOne);
       // listView.setAdapter(myCustomAdapter);

       // registerForContextMenu(listView);//register listView for context menu

          //  listView.setOnCreateContextMenuListener(this);




        //Без кастомного адаптера
        String[] menuItems={"ListItem1","ListItem2","ListItem3"};

        taskList=new ArrayList<Task>();
        getAll();
        listView=(ListView) rootView.findViewById(R.id.lvFragmentScreenOne);
        listViewAdapter=new ArrayAdapter<Task>(getActivity(), android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(listViewAdapter);

        //listView.setOnCreateContextMenuListener(this);
      //  registerForContextMenu(listView);//register listView for context menu

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Task task=(Task)parent.getAdapter().getItem(position);
               // Toast.makeText(getActivity(),String.valueOf(task.getId()),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(),EditTaskActivity.class);
                    intent.putExtra("id",task.getId());
                    intent.putExtra("beginDate",task.getBeginDate());
                    intent.putExtra("endDate",task.getEndDate());
                    intent.putExtra("period",task.getPeriod());
                    intent.putExtra("description",task.getDescription());
                    startActivity(intent);
            }
        });

        fab=(FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(getActivity(), CreateTaskActivity.class);
                startActivity(intent);
            }
        });



        return rootView;
    }

  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

      //AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.edit:
               Intent intent=new Intent(getActivity(),EditTaskActivity.class);
                startActivity(intent);
                return true;
            case R.id.delete:
                //метод, выполняющий действие при удалении пункта меню
                int position = info.position;

                //Toast.makeText(getActivity(),String.valueOf(),Toast.LENGTH_LONG).show();
                /*HttpClient.deleteTask(String.valueOf(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getActivity(), "Task deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getActivity(), "No connection", Toast.LENGTH_SHORT).show();
                    }
                }); */
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
 /* @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      AdapterView.AdapterContextMenuInfo aMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

      // Получаем позицию элемента в списке
      int position = aMenuInfo.position;

      // Получаем данные элемента списка, тип данных здесь вы должны указать свой!
      //final AdapterData data = adapter.getItem(aMenuInfo.position);

      menu.setHeaderTitle("Заголовок");
      menu.add("Первый элемент").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {
              // дествия по клику меню
              Intent intent=new Intent(getActivity(),CreateTaskActivity.class);
              startActivity(intent);
              return true;
          }
      });
  }*/
 public void getAll(){
    // taskList.clear();

     HttpClient.getAllTask(null,new JsonHttpResponseHandler(){
         @Override
         public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
             try {
                 for (int i=0;i<response.length();i++){
                     Task task=new Task();
                     JSONObject obj=response.getJSONObject(i);
                     task.setId(obj.getInt("id"));
                     task.setBeginDate(obj.getString("bookName"));
                     task.setEndDate(obj.getString("mainReview"));
                     task.setPeriod(obj.getString("reviewAuthor"));
                     task.setDescription(obj.getString("shortReview"));

                     taskList.add(task);
                 }listViewAdapter.notifyDataSetChanged();

             }catch (Exception e){

             }
         }
     });
 }
}

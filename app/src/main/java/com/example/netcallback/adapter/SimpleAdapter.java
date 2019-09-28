package com.example.netcallback.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netcallback.R;

import java.io.IOException;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    //主要谷歌强制开发人员使用ViewHolder来实现 进行统一化
    private LayoutInflater inflater;
    private Context context;
    private List<String> mDatas;
    private int[] imgresId;
    public MediaPlayer mediaPlayer;
    AssetManager assetManager;

    public SimpleAdapter(Context context, List<String> datas,int[] ImgResId){
        //这里适配器是写给主活动互相调用的方法
        this.context=context;
        this.mDatas=datas;
        this.imgresId=ImgResId;
        this.inflater=LayoutInflater.from ( context );

    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );
            textView=itemView.findViewById ( R.id.tv );
            imageView=itemView.findViewById ( R.id.img);
        }
    }

    //创建viewHolder


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup , int i) {
        View view=inflater.inflate ( R.layout.vedio_item_layout,viewGroup,false );
        final MyViewHolder viewHolder=new MyViewHolder ( view );
        //recy子控件设置点击监听
        viewHolder.imageView.setOnLongClickListener ( new View.OnLongClickListener () {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        } );
        viewHolder.imageView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(final View v) {
                final int position = viewHolder.getAdapterPosition ();
                /* @setIcon 设置对话框图标
                 * @setTitle 设置对话框标题
                 * @setMessage 设置对话框消息提示
                 * setXXX方法返回Dialog对象，因此可以链式设置属性
                 */
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(v.getContext ());
                normalDialog.setTitle("音乐");
                normalDialog.setMessage("播放音乐选项");
                normalDialog.setNegativeButton("播放",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText ( v.getContext () , "即将播放背景音乐" + position , Toast.LENGTH_SHORT ).show ();
                                //...To-do
                                //打开Asset目录
                                assetManager = v.getContext ().getAssets ();
                                if (mediaPlayer == null) {
                                    mediaPlayer = new MediaPlayer ();
                                    try {
                                        //打开音乐文件shot.mp3
                                        AssetFileDescriptor assetFileDescriptor = assetManager.openFd ( "music.mp3" );
                                        mediaPlayer.reset ();
                                        //设置媒体播放器的数据资源
//                        mediaPlayer.setDataSource ( MusicActivity.this, Uri.parse(URL) );
                                        mediaPlayer.setDataSource ( assetFileDescriptor.getFileDescriptor () , assetFileDescriptor.getStartOffset () , assetFileDescriptor.getLength () );
                                        mediaPlayer.prepare ();
//                        mediaPlayer.start();
                                    } catch (IOException e) {
                                        e.printStackTrace ();
//                        Log.d("GsonUtils", "IOException" + e.toString());
                                    }
                                }
                                mediaPlayer.start ();

                            }
                        });
                normalDialog.setPositiveButton("暂停",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                                Toast.makeText ( v.getContext () , "背景音乐停止播放" + position , Toast.LENGTH_SHORT ).show ();
                                if (mediaPlayer!=null&&mediaPlayer.isPlaying ()){
                                    mediaPlayer.stop ();
                                    mediaPlayer.release ();
                                    mediaPlayer=null;
                                }
                            }
                        });
                // 显示
                normalDialog.show();


            }
        } );
        viewHolder.textView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText ( v.getContext (),"文字被点击了"+position,Toast.LENGTH_SHORT ).show ();
            }
        } );
        return viewHolder;
    }

    //绑定viewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder , int position) {
        viewHolder.textView.setText ( mDatas.get ( position ) );
        viewHolder.imageView.setImageResource ( imgresId[position] );

    }

    @Override
    public int getItemCount() {
        //得到个数
        //即为数据列的个数
        return mDatas.size ();
    }
}


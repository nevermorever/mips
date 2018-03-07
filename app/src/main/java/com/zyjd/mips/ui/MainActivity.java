package com.zyjd.mips.ui;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zyjd.mips.R;
import com.zyjd.mips.adapter.FragmentViewPagerAdapter;
import com.zyjd.mips.entity.Program;
import com.zyjd.mips.entity.Scene;
import com.zyjd.mips.retrofit.Client;
import com.zyjd.mips.util.SPUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private FragmentViewPagerAdapter adapter;
    private ViewPager viewPager;
    private Program mProgram;
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);

        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());

        uuid = SPUtil.getString(this, "mips_device_uuid", null);

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            Log.i("mips", "有sd卡");
            Log.i("mips", "sd卡总容量为：" + getSDAllSize());

//            try {
//                writeToSdCard();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            isProgramFolderExists("mips");

        } else {
            Log.i("mips", "没有有sd卡");
        }


        // 从sd卡读取配置文件

        Client.getApiService().getProgram()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Program>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Program program) {
                        mProgram = program;
                        makeProgram();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("rems", e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void makeProgram() {

        //  节目json通过合法性校验
        //  将场景对应的json传入fragment
        //  fragment来动态构造布局
        if (programValidation(mProgram)) {
            for (Scene scene : mProgram.getScenes()) {
                AdvFragment advFragment = new AdvFragment();

                Bundle bundle = new Bundle();
                Log.i("rems", scene.getElements().toString());

                bundle.putSerializable("scene", scene);

                advFragment.setArguments(bundle);

                adapter.addFragment(advFragment);
            }

            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);
        } else {
            playDefaultProgram();
        }
    }

    // 播放默认节目
    private void playDefaultProgram() {

    }

    // 检查节目合法性
    // 节目包含一定的场景，每个场景包含一定的元素，否则视为不合法
    public boolean programValidation(Program program) {
        if (program == null) return false;
        if (program.getScenes() == null) return false;
        if (program.getScenes().size() == 0) return false;

        int flag = 0;
        int scene_size = program.getScenes().size();

        for (Scene scene : program.getScenes()) {
            if (scene.getElements() == null) flag++;
            else if (scene.getElements().size() == 0) flag++;
        }
        return !(flag == scene_size);
    }

    public void threadTask() {
        Observable
                .interval(0, 2, TimeUnit.SECONDS)
                .flatMap(new Function<Long, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Long aLong) throws Exception {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
//        Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline())
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    public long getSDAllSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //获取所有数据块数
        long allBlocks = sf.getBlockCount();
        //返回SD卡大小
        //return allBlocks * blockSize; //单位Byte
        //return (allBlocks * blockSize)/1024; //单位KB
        return (allBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    // 将文件写入sd卡
    public void writeToSdCard() throws IOException {
        // 比如可以将一个文件作为普通的文档存储，那么先获取系统默认的文档存放根目录
        File parent_path = Environment.getExternalStorageDirectory();

        // 可以建立一个子目录专门存放自己专属文件
        File dir = new File(parent_path.getAbsoluteFile(), "mips");
        dir.mkdir();

        File file = new File(dir.getAbsoluteFile(), "test.txt");

        Log.d("文件路径", file.getAbsolutePath());

        // 创建这个文件，如果不存在
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);

        String data = "hello,world! Zhang Phil @ CSDN";
        byte[] buffer = data.getBytes();

        // 开始写入数据到这个文件。
        fos.write(buffer, 0, buffer.length);
        fos.flush();
        fos.close();

        Log.d("文件写入", "成功");

    }

    // 文件下载
    public void downloadFile() {

    }

    // 检查是否存在节目资源文件夹，不存在则创建
    public boolean isProgramFolderExists(String strFolder) {

        File file = new File(strFolder);

        if (!file.exists()) {
            if (file.mkdir()) {
                return true;
            } else
                return false;
        }
        return true;
    }

}















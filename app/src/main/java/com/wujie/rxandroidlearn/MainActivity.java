package com.wujie.rxandroidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    ImageView imageCollectorView;
    InetAddress ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ArrayList<File> folders = new ArrayList<>();
//        Observable.from(folders)
//                .flatMap(new Func1<File, Observable<File>>() {
//                    @Override
//                    public Observable<File> call(File file) {
//                        return Observable.from(file.listFiles());
//                    }
//                })
//                .filter(new Func1<File, Boolean>() {
//                    @Override
//                    public Boolean call(File file) {
//                        return file.getName().endsWith(".png");
//                    }
//                })
//                .map(new Func1<File, Bitmap>() {
//                    @Override
//                    public Bitmap call(File file) {
//                        return BitmapFactory.decodeFile(file.getAbsolutePath());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        imageCollectorView.setImageBitmap(bitmap);
//                    }
//                });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("tag", "Completerd!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tag","Error!" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d("tag", "Item: " +s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.d("tag", "Start!");
            }

            @Override
            public void setProducer(Producer p) {
                super.setProducer(p);
            }

            @Override
            public void onCompleted() {
                Log.d("tag", "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tag","Error!" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d("tag", "Item: " +s);
            }
        };

//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
        Observable observable = Observable.just("hahha","ddd","xixi","aaa");
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
            }
        });


        String [] s = {"Heo", "Hiaaa", "dddd"};
        Observable observable2 = Observable.from(s);
        observable2.subscribe(subscriber);
        //observable.subscribe(observer);
        observable.subscribe(subscriber);
        observable1.subscribe(observer);

    }
}

package com.wujie.rxandroidlearn;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    InetAddress ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        int ids = 100;
        final int id = ids;
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("id", id+"");
            }
        });
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
//        Observable observable2 = Observable.from(s);
//        observable2.subscribe();
        Observable.from(s).subscribe(new Action1<String>() {
            @Override

            public void call(String s) {
                Log.d("sssss", s);
            }
        });
        //observable.subscribe(observer);
       // observable.subscribe(subscriber);
       // observable1.subscribe(observer);

        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("tag", "number:"+integer);
                    }
                });
        Integer [] ints = {1,2,3,4,5};
        Observable.from(ints)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e("tag", "numbersss:" + integer);
                    }
                });
        imageView = (ImageView) findViewById(R.id.image_a);
//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Action1<Drawable>() {
////                    @Override
////                    public void call(Drawable drawable) {
////                        imageView.setImageDrawable(drawable);
////                    }
////                });
//        .subscribe(new Observer<Drawable>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Drawable drawable) {
//                imageView.setImageDrawable(drawable);
//            }
//        });
    Observable.just(R.mipmap.ic_launcher)
            .map(new Func1<Integer, Drawable>() {
        @Override
        public Drawable call(Integer integer) {
            return getResources().getDrawable(integer);
        }
    })
            .subscribe(new Action1<Drawable>() {
        @Override
        public void call(Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }
    });
    Student[] students =null;

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("onNextAction", "onNextAction2");
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("onErrorAction", throwable.getMessage());
            }
        };

        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                Log.d("onCompletedAction", "completed");
            }
        };

        Observable<String> observable2 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("ddddd");
                subscriber.onError(new Throwable());
                subscriber.onCompleted();
            }
        });
        observable2.subscribe(onNextAction);
        observable2.subscribe(onNextAction, onErrorAction);
        observable2.subscribe(onNextAction, onErrorAction, onCompletedAction);





    }

    class  mTranstram implements Observable.Transformer<Integer, String> {

        @Override
        public Observable<String> call(Observable<Integer> integerObservable) {
            return null;
        }
    }



    @Override
    protected void onDestroy() {
        Log.d("ssss", "Destroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d("ssss", "Resume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("ssss", "Restart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("ssss", "start");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d("ssss", "Pasuse");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("ssss", "stop");
        super.onStop();
    }

    @Override
    public void finish() {
        Log.d("ssss", "finnish");
        super.finish();
    }

    class Student {

    }

}

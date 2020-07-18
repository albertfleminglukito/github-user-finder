# Github User Finder

This project will help to find to find github user based on keyword you input.
This project use Kotlin as main programming language, MVVM, and Clean Architecture.

Library I use:
- Dependency Injection: Dagger ( [https://developer.android.com/training/dependency-injection/dagger-android]() )
- Http Client: Retrofit2 ( [https://github.com/square/retrofit]() )
- Image Loader: Glide ( [https://github.com/bumptech/glide]() )
- RxJava2 / RxKotlin ( [https://github.com/ReactiveX/RxJava]() )


This project contains unit test, and use jacoco to generate report.
To generate the report and open it on browser, you can run it via terminal by running command below if you have gradle set on your environment:
```sh
$ cd {your_project_directory}/tiket
$ gradle openJacocoReport
```
If it's not working, you can also run it via Android Studio, here's the step:
- Open the project
- On the right toolbar, you can find the gradle menu
- Go to tiket -> Tasks -> reporting -> openJacocoReport

This project also integrate with Travis, but currently it not help to upload the build to app distribution or other place, but only to check whether the committed push successfully pass the test or not. If someday we need to upload some build for each test, we can set it easily.



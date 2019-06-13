# Android Data Binding Samples

This repository contains three samples for demonstrating the use of Android Data Binding Library. All three samples have both Java and Kotlin versions. The three samples are of various difficulty levels. We tried to avoid unnecessary complexities and minimize the use of external libraries, in case some learners might not be familiar with those libraries. But we also wanted to show something more or less realist. 

The first sample, Data Binding with RecyclerView is intentionally kept very simple so that you can concentrate on learning basics of data binding. It contains a single activity with two fragments: First fragment shows a list of items (hardcoded list of products) within a recyclerview, and the second fragment shows the details of the chosen product. In this sample, we demonstrated how to set up data binding in xml layouts, how to access binding instance from Java/Kotlin code, how to use imports and helper methods, how to implement data binding in recyclerview item layouts and how to set an item click listener with data binding

The second one, Data Binding with News Api, again has two fragments, one showing a list of articles and the second one showing details of the chosen article. However this one makes a network call to fetch articles from News Api and uses some Android Jetpack components, like ViewModel and LiveData. Regarding data binding, besides the similar concepts that were used in the first sample, this sample also demonstrates use of custom binding adapters and integration of viewModel and liveData with data binding. 

And the third one, Two-Way Databinding, demonstrates the use of two-way data binding. It is an inventory app, which uses Room for data persistence. This one again uses ViewModel, LiveData and Repository pattern. It also demonstrates use of ObservableFields and converter methods. 


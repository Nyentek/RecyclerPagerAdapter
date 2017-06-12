# RecyclerPagerAdapter

RecyclerPagerAdapter is a class that extends
[PagerAdapter](https://developer.android.com/reference/android/support/v4/view/PagerAdapter.html)
to offer a
[RecyclerView.Adapter](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html)-like
interface with a couple of convenience parameters and methods. Like
[RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html)
and unlike,
[PagerAdapter](https://developer.android.com/reference/android/support/v4/view/PagerAdapter.html),
RecyclerPagerAdapter handles view recycling through the
[ViewHolder](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder.html)
pattern

## Install

Add the following line to your module's `build.gradle` dependencies to use:

```groovy
compile 'com.nyentek.android:recyclerpageradapter:1.x.y'
```

## Usage

Extend `RecyclerPagerAdapter<Item, ViewHolder extends RecyclerPagerAdapter.ViewHolder>` filling in
the generic types to fit your app-specific data and view.

##### `ExamplePagerAdapter`
```java
public class ExamplePagerAdapter extends RecyclerPagerAdapter<String, ExampleViewHolder> {

    private List<String> stringList;

    public ExamplePagerAdapter(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList; // size of items in data set
    }

    @Override
    public String getItem(int position) {
        return stringList.get(position); // return an item at given position in data set
    }

    @Override
    public int getItemViewType(int position) {
        return 0; // return unique integer to represent a view type, 0 in this example for one view type
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        // use viewType if more than one view type to determine what subclass of ExampleViewHolder to return
        // in this example, there is only one view type so no need to use viewType or subclass
        return new ExampleViewHolder(inflater.inflate(R.layout.page_example, parent, false));
    }

    @Override
    public void onBindViewHolder(DemoRecyclerPagerAdapterViewHolder holder, String text) {
        holder.setText(text);
    }
}
```
##### `ExampleViewHolder`
```java
public class ExampleViewHolder extends RecyclerPagerAdapter.ViewHolder {

    private TextView textView;

    public DemoRecyclerPagerAdapterViewHolder(View view) {
        super(view);
        textView = (TextView) view;
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
```
##### `page_example.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
          android:layout_width="match_parent"
          android:layout_height="match_parent" />
```

See `DemoRecyclerPagerAdapter` and `DemoRecyclerPagerAdapterViewHolder` for more examples.

## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/Nyentek/RecyclerPagerAdapter/issues).

## LICENSE

Copyright (c) 2017-present, Nyentek LLC & Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

/**
 * Copyright (c) 2017-present, Nyentek LLC & Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nyentek.android.recyclerpageradapter.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DemoRecyclerPagerAdapterActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

		List<String> imageUrls = new ArrayList<>();
		imageUrls.add("http://lorempixel.com/576/1024/technics");
		imageUrls.add("http://lorempixel.com/576/1024/nature");
		imageUrls.add("http://lorempixel.com/576/1024/abstract");
		imageUrls.add("http://lorempixel.com/576/1024/cats/");
		imageUrls.add("http://lorempixel.com/576/1024/nightlife");
		imageUrls.add("http://lorempixel.com/576/1024/sports");
		imageUrls.add("http://lorempixel.com/576/1024/city");
		imageUrls.add("http://lorempixel.com/576/1024/food");

		ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewPager.setAdapter(new DemoRecyclerPagerAdapter(imageUrls));
	}
}

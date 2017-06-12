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

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nyentek.android.recyclerpageradapter.RecyclerPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoRecyclerPagerAdapter extends RecyclerPagerAdapter<String, DemoRecyclerPagerAdapterViewHolder> {

	private List<String> imageUrls;

	public DemoRecyclerPagerAdapter(List<String> imageUrls) {
		this.imageUrls = new ArrayList<>(imageUrls);
	}

	@Override
	public int getCount() {
		return imageUrls.size();
	}

	@Override
	public String getItem(int position) {
		return imageUrls.get(position);
	}

	@Override
	public DemoRecyclerPagerAdapterViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
		return new DemoRecyclerPagerAdapterViewHolder(inflater.inflate(R.layout.page_demo, parent, false));
	}

	@Override
	public void onBindViewHolder(DemoRecyclerPagerAdapterViewHolder holder, String imageUrl) {
		holder.setPictureUrl(imageUrl);
	}
}

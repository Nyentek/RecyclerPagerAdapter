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

import android.view.View;
import android.widget.ImageView;

import com.nyentek.android.recyclerpageradapter.RecyclerPagerAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DemoRecyclerPagerAdapterViewHolder extends RecyclerPagerAdapter.ViewHolder {

	private ImageView image;
	private View progressBar;

	public DemoRecyclerPagerAdapterViewHolder(View view) {
		super(view);
		image = (ImageView) view.findViewById(R.id.image);
		progressBar = view.findViewById(R.id.progress_bar);
	}

	public void setPictureUrl(final String imageUrl) {
		progressBar.setVisibility(View.VISIBLE);
		Picasso.with(image.getContext())
				.load(imageUrl)
				.into(image, new Callback() {
					@Override
					public void onSuccess() {
						progressBar.setVisibility(View.GONE);
					}

					@Override
					public void onError() {
						progressBar.setVisibility(View.GONE);
						image.setImageResource(android.R.drawable.ic_menu_gallery);
					}
				});
	}
}

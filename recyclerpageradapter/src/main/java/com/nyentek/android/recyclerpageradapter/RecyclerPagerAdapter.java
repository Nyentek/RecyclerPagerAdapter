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
package com.nyentek.android.recyclerpageradapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The RecyclerPagerAdapter extends {@link PagerAdapter} and offers a
 * {@link android.support.v7.widget.RecyclerView.Adapter} like interface.
 *
 * @param <I> the type of app-specific data item for mapping to a view
 * @param <H> the type of {@link ViewHolder} for binding an item to a view
 */
public abstract class RecyclerPagerAdapter<I, H extends RecyclerPagerAdapter.ViewHolder> extends PagerAdapter {

	/**
	 * A ViewHolder describes an item view and metadata about its place within the ViewPager.
	 * <p>
	 * {@link RecyclerPagerAdapter} implementations should subclass ViewHolder and add fields
	 * for caching potentially expensive {@link View#findViewById(int)} results.
	 */
	public static class ViewHolder {

		public final View itemView;
		int itemViewType;

		public ViewHolder(View itemView) {
			this.itemView = itemView;
		}

		/**
		 * @return The view type of this ViewHolder.
		 */
		public final int getItemViewType() {
			return itemViewType;
		}
	}

	private final HashMap<Integer, Stack<H>> reserveHolders;
	private final HashMap<Integer, H> visibleHolders;

	public RecyclerPagerAdapter() {
		reserveHolders = new HashMap<>();
		visibleHolders = new HashMap<>();
	}

	@Override
	public final void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		for (Map.Entry<Integer, H> entry : visibleHolders.entrySet()) {
			if (entry.getKey() < getCount()) {
				onBindViewHolder(entry.getValue(), getItem(entry.getKey()));
			}
		}
	}

	@Override
	public final boolean isViewFromObject(View view, Object object) {
		return view == ((ViewHolder) object).itemView;
	}

	@Override
	public final Object instantiateItem(ViewGroup parent, int position) {
		H holder = visibleHolders.get(position);
		int viewType = getItemViewType(position);
		if (holder == null) {
			Stack<H> holders = getHoldersByViewType(viewType);
			if (holders.empty()) {
				LayoutInflater inflater = LayoutInflater.from(parent.getContext());
				holder = onCreateViewHolder(inflater, parent, viewType);
			} else {
				holder = holders.pop();
			}
		}
		holder.itemViewType = viewType;
		visibleHolders.put(position, holder);

		onBindViewHolder(holder, getItem(position));

		View view = holder.itemView;
		if (holder.itemView.getParent() != null) {
			((ViewGroup) view.getParent()).removeView(view);
		}
		parent.addView(view);

		return holder;
	}

	@Override
	public final void destroyItem(ViewGroup container, int position, Object object) {
		@SuppressWarnings("unchecked")
		H holder = (H) object;
		container.removeView(holder.itemView);
		visibleHolders.remove(position);
		getHoldersByViewType(getItemViewType(position)).push(holder);
	}

	private Stack<H> getHoldersByViewType(int viewType) {
		Stack<H> holders = reserveHolders.get(viewType);
		if (holders == null) {
			holders = new Stack<>();
			reserveHolders.put(viewType, holders);
		}
		return holders;
	}

	protected final H getHolder(int position) {
		return visibleHolders.get(position);
	}

	protected final Collection<H> getHolders() {
		return visibleHolders.values();
	}

	/**
	 * Called when {@link android.support.v4.view.ViewPager} needs an item to bind to a {@link ViewHolder}.
	 *
	 * @param position The position for a given item
	 * @return The item for a given position
	 */
	public abstract I getItem(int position);

	/**
	 * Return the view type of the item at <code>position</code> for the purposes
	 * of view recycling.
	 * <p>
	 * The default implementation of this method returns 0, making the assumption of
	 * a single view type for the adapter. Unlike ListView adapters, types need not
	 * be contiguous. Consider using id resources to uniquely identify item view types.
	 *
	 * @param position position to query
	 * @return integer value identifying the type of the view needed to represent the item at
	 *                 <code>position</code>. Type codes need not be contiguous.
	 */
	public int getItemViewType(int position) {
		return 0;
	}

	/**
	 * Called when {@link android.support.v4.view.ViewPager} needs a new {@link ViewHolder} to represent an item.
	 * <p>
	 * This new ViewHolder should be constructed with a new View that can represent the items.
	 * You can either create a new View manually or inflate it from an XML layout file.
	 * <p>
	 * The new ViewHolder will be used to display items of the adapter using
	 * {@link #onBindViewHolder(H holder, I item)}. Since it will be re-used to display
	 * different items in the data set, it is a good idea to cache references to sub views of
	 * the View to avoid unnecessary {@link View#findViewById(int)} calls.
	 *
	 * @param inflater The {@link LayoutInflater} to use to inflate a view from an XML layout file
	 * @param parent   The {@link ViewGroup) into which the new View will be added after it is bound
	 *                 to an adapter position.
	 * @return A new {@link ViewHolder} that holds a View to represent an item.
	 * @see #getItem(int)
	 * @see #onBindViewHolder(H holder, I item)
	 */
	public abstract H onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

	/**
	 * Called by {@link android.support.v4.view.ViewPager} to display the item for a specific position.
	 * This method should update the contents of the {@link ViewHolder#itemView} to reflect the item at this
	 * position.
	 *
	 * @param holder The {@link ViewHolder} which should be updated to represent the contents of
	 *               the item
	 * @param item   The item that contains the contents to be updated
	 */
	public abstract void onBindViewHolder(H holder, I item);
}

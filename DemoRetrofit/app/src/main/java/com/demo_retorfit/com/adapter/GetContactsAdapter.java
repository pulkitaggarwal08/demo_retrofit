package com.demo_retorfit.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo_retorfit.com.R;
import com.demo_retorfit.com.entity.Contacts;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by pulkit on 11/3/18.
 */
public class GetContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Contacts> contactsArrayList;
    private OnClickContactListener onClickContactListener;
    private Context context;

    public interface OnClickContactListener {
        void onClickContact(int position, Contacts contacts);
    }

    public GetContactsAdapter(Context context, ArrayList<Contacts> contactsArrayList, OnClickContactListener onClickContactListener) {
        this.context = context;
        this.contactsArrayList = contactsArrayList;
        this.onClickContactListener = onClickContactListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contacts, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ContactsHolder holder = (ContactsHolder) viewHolder;

        holder.tvName.setText(contactsArrayList.get(position).getName());
        holder.tvGender.setText(contactsArrayList.get(position).getGender());
        holder.tvEmail.setText(contactsArrayList.get(position).getEmail());
        holder.tvAddress.setText(contactsArrayList.get(position).getAddress());
        holder.ivUser.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

    class ContactsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvGender, tvEmail, tvAddress;
        RelativeLayout rlContact;
        CircleImageView ivUser;

        public ContactsHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvGender = itemView.findViewById(R.id.tv_gender);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvAddress = itemView.findViewById(R.id.tv_address);
            ivUser = itemView.findViewById(R.id.iv_user);
            rlContact = itemView.findViewById(R.id.rl_contact);

            rlContact.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickContactListener.onClickContact(getLayoutPosition(), contactsArrayList.get(getLayoutPosition()));
        }
    }

}

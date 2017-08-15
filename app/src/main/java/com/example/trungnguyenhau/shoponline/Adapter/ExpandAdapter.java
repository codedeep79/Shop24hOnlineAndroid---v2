package com.example.trungnguyenhau.shoponline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.trungnguyenhau.shoponline.Model.GerneralModel.TypeProduct;
import com.example.trungnguyenhau.shoponline.Model.Home.ProccessMenu.ProccessMenuJson;
import com.example.trungnguyenhau.shoponline.R;
import com.example.trungnguyenhau.shoponline.Views.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucFragment;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/10/2017.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<TypeProduct> listProduct;
    private ViewHolder  viewHolder;

    public ExpandAdapter(Context context, List<TypeProduct> listProduct) {
        this.context = context;
        this.listProduct = listProduct;

        ProccessMenuJson proccessMenuJson = new ProccessMenuJson();
        // Lấy danh mục con
        int count = listProduct.size();
        for (int i = 0; i < count;i++){
            int maloaisp = listProduct.get(i).getMALOAISP();
            listProduct.get(i).setListDanhMucCon(proccessMenuJson.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }
    }

    @Override
    public int getGroupCount() {
        return listProduct.size();
    }

    // Mỗi danh mục cha chỉ có danh mục con duy nhất
    @Override
    public int getChildrenCount(int positionParent) {
        if(listProduct.get(positionParent).getListDanhMucCon().size() != 0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Object getGroup(int positionParent) {
        // Lấy danh mục cha
        return listProduct.get(positionParent);
    }

    @Override
    public Object getChild(int positionParent, int positionChild) {
        // Lấy danh mục con
        return listProduct.get(positionParent).getListDanhMucCon().get(positionChild);
    }

    @Override
    public long getGroupId(int positionParent) {
        // Lấy mã sản phẩm danh mục cha
        return listProduct.get(positionParent).getMALOAISP();
    }

    @Override
    public long getChildId(int positionParent, int positionChild) {
        // Lấy mã sản phẩm danh mục con
        return listProduct.get(positionParent).getListDanhMucCon().get(positionChild).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolder{
        TextView txtCategoryName;
        ImageView imgPlusMinus;
    }

    // Giao diện danh mục cha
    @Override
    public View getGroupView(final int positionParent,
                             boolean isExpanded, View view, ViewGroup viewGroup) {
        int countProduct = listProduct.get(positionParent).getListDanhMucCon().size();
        View viewGroupParent = view;
        if (viewGroupParent == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupParent
                    = layoutInflater.inflate(R.layout.custom_layout_menu_group_parent, viewGroup, false);
            viewHolder.txtCategoryName = (TextView) viewGroupParent.findViewById(R.id.textview_category_name);
            viewHolder.imgPlusMinus = (ImageView) viewGroupParent.findViewById(R.id.image_menu_plus);

            viewGroupParent.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) viewGroupParent.getTag();
        }

        viewHolder.txtCategoryName.setText(listProduct.get(positionParent).getTENLOAISP());
        if (countProduct > 0)
        {
            viewHolder.imgPlusMinus.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolder.imgPlusMinus.setVisibility(View.INVISIBLE);
        }

        // Chỉ có sử dụng cách này mới bắt sự kiện xổ xuống hiện dấu - ở menu
        if (isExpanded)
        {
            viewHolder.imgPlusMinus.setBackgroundResource(R.drawable.ic_remove_black_24dp);
            viewGroupParent.setBackgroundResource(R.color.colorGray);
        }
        else
        {
            viewHolder.imgPlusMinus.setBackgroundResource(R.drawable.ic_add_black_24dp);
            viewGroupParent.setBackgroundResource(R.color.colorWhite);
        }

        // Khi ta dùng setOnClickListener thì nó sẽ setOnClickListener cho cả LinearLayout
        // chứ không set cho item con nên dùng setOnTouchListener thì ExpanListview sẽ set cho
        // các item con.
        viewGroupParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSanPhamTheoDanhMucFragment hienThiSanPhamTheoDanhMucFragment = new HienThiSanPhamTheoDanhMucFragment();

                Bundle bundle = new Bundle();

                bundle.putInt("MALOAI",listProduct.get(positionParent).getMALOAISP());
                bundle.putBoolean("KIEMTRA",false);
                bundle.putString("TENLOAI",listProduct.get(positionParent).getTENLOAISP());

                hienThiSanPhamTheoDanhMucFragment.setArguments(bundle);

                // Nghĩa là khi nhấn nút back thì Fragment sẽ trở về Fragment Transaction trước đó
                fragmentTransaction.addToBackStack("HomeActivity");
                fragmentTransaction.replace(R.id.framelayout_home, hienThiSanPhamTheoDanhMucFragment);
                fragmentTransaction.commit();
                return false;
            }
        });
        return viewGroupParent;
    }

    // Giao diện danh mục con
    @Override
    public View getChildView(int positionParent, int positionChild,
                             boolean isExpanded, View view, ViewGroup viewGroup) {
        // LayoutInflater layoutInflater
        //        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // View viewGroupChild
        //        = layoutInflater.inflate(R.layout.custom_layout_menu_group_children, viewGroup, false);

        // TextView txtCategoryName
        //        = (TextView) viewGroupChild.findViewById(R.id.textview_category_name);

        // txtCategoryName.setText(listProduct.
        //        get(positionParent).getListDanhMucCon().get(positionChild).getTENLOAISP());

        // LayoutInflater layoutInflater
        //        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // View viewGroupChild
        //        = layoutInflater.inflate(R.layout.custom_layout_menu_group_children, viewGroup, false);
        // ExpandableListView epListCategory
        //        = (ExpandableListView) viewGroupChild.findViewById(R.id.edListChild);

        // Chỉnh lại kích thước, giao diện thì đã thiết lập ở adapter
        SecondExpandable secondExpandable = new SecondExpandable(context);

        // ExpandAdapter: Sử dụng class ExpandAdapter chính nó để làm adapter group child như vậy thì
        // muốn làm menu bao nhiêu cấp cũng được
        ExpandAdapter secondAdapter = new ExpandAdapter(context, listProduct.get(positionParent).getListDanhMucCon());

        secondExpandable.setAdapter(secondAdapter);
        secondExpandable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpandable;
    }

    // Giao diện danh mục con
    // Chỉnh chiều cao, chiều rộng listview của danh mục con
    @Override
    public boolean isChildSelectable(int positionParent, int positionChild) {
        return false;
    }

    public class SecondExpandable extends ExpandableListView{

        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

            // 900 là chiều cao tối đa, nó điều chỉnh list danh mục lại cho gọn vừa số dòng trả về.
            // Nếu để AT_MOST thì nó sẽ chiếm trọn toàn view chứa nó, nếu bỏ thì nó hiển thị canh đều
            // control sang hai bên
            //widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        }
    }

    // Giao diện con
    public class SecondAdapter extends BaseExpandableListAdapter {
        private List<TypeProduct> listProductChild;

        public SecondAdapter(List<TypeProduct> listProductChild) {
            this.listProductChild = listProductChild;
            ProccessMenuJson proccessMenuJson = new ProccessMenuJson();

            int count = listProductChild.size();
            for (int i = 0; i < count;i++){
                int maloaisp = listProductChild.get(i).getMALOAISP();
                listProductChild.get(i)
                        .setListDanhMucCon(proccessMenuJson.LayLoaiSanPhamTheoMaLoai(maloaisp));
            }
        }


        @Override
        public int getGroupCount() {
            return listProductChild.size();
        }

        // Menu chỉ có một cấp thôi
        @Override
        public int getChildrenCount(int positionParent) {
            if(listProductChild.get(positionParent).getListDanhMucCon().size() != 0){
                return 1;
            }else{
                return 0;
            }
        }

        @Override
        public Object getGroup(int positionParent) {
            // Lấy danh mục cha
            return listProductChild.get(positionParent);
        }

        @Override
        public Object getChild(int positionParent, int positionChild) {
            // Lấy danh mục con
            return listProductChild.get(positionParent).getListDanhMucCon().get(positionChild);
        }

        @Override
        public long getGroupId(int positionParent) {
            // Lấy mã sản phẩm danh mục cha
            return listProductChild.get(positionParent).getMALOAISP();
        }

        @Override
        public long getChildId(int positionParent, int positionChild) {
            // Lấy mã sản phẩm danh mục con
            return listProductChild.get(positionParent).getListDanhMucCon().get(positionChild).getMALOAISP();
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        // Giao diện thư mục cha.
        @Override
        public View getGroupView(int positionParent,
                                 boolean isExpanded, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewGroupParent
                    = layoutInflater.inflate(R.layout.custom_layout_menu_group_parent, viewGroup, false);
            TextView txtCategoryName = (TextView) viewGroupParent.findViewById(R.id.textview_category_name);
            txtCategoryName.setText(listProductChild.get(positionParent).getTENLOAISP());
            return viewGroupParent;
        }

        // Giao diện danh mục con
        @Override
        public View getChildView(int positionParent, int positionChild,
                                 boolean isExpanded, View view, ViewGroup viewGroup) {
            TextView txtCategoryChild = new TextView(context);
            txtCategoryChild.setText(listProductChild.get(positionParent)
                    .getListDanhMucCon().get(positionChild).getTENLOAISP());
            txtCategoryChild.setLayoutParams(new ListView.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            txtCategoryChild.setPadding(10, 10, 10, 10);
            txtCategoryChild.setBackgroundColor(Color.CYAN);
            return txtCategoryChild;
        }

        @Override
        public boolean isChildSelectable(int positionParent, int positionChild) {
            return false;
        }
    }
}

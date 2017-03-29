package com.yongweizhang.bilibili.shopfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.activity.MainActivity;
import com.yongweizhang.bilibili.bean.GoodsBean;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.yongweizhang.bilibili.pay.AliPayUtils;
import com.yongweizhang.bilibili.pay.PayKeys;
import com.yongweizhang.bilibili.shopAdapter.ShoppingCartAdapter;
import com.yongweizhang.bilibili.view.CartStorage;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class TwoFragment extends BaseFragment {

    //商户PID
    public static final String PARTNER = PayKeys.PRIVATE;
    //商户收款账号
    public static final String SELLER = PayKeys.DEFAULT_SELLER;
    //商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = PayKeys.PRIVATE;
    //支付宝公钥
    public static final String RSA_PUBLIC = PayKeys.PUBLIC;


    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;
    @InjectView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @InjectView(R.id.btn_check_out)
    Button btnCheckOut;
    @InjectView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @InjectView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @InjectView(R.id.btn_delete)
    Button btnDelete;
    @InjectView(R.id.btn_collection)
    Button btnCollection;
    @InjectView(R.id.ll_delete)
    LinearLayout llDelete;
    @InjectView(R.id.iv_empty)
    ImageView ivEmpty;
    @InjectView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @InjectView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    private ShoppingCartAdapter adapter;

    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 1;

    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 2;
    private boolean isChecked;
    private Intent intent;
    private List<GoodsBean> list;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.inject(this, view);
        //设置编辑状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //显示去结算布局
        llCheckAll.setVisibility(View.VISIBLE);

        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                int action = (int) v.getTag();

                //2.根据不同状态做不同的处理
                if(action==ACTION_EDIT) {

                    //切换完成状态
                    showDelete();
                }else{

                    //切换成编辑状态
                    hideDelete();
                }
            }
        });

        return view;
    }
    private void hideDelete() {
        //1.设置编辑
        tvShopcartEdit.setTag(ACTION_EDIT);
        //2.显示删除控件
        llDelete.setVisibility(View.GONE);
        //3.隐藏结算控件
        llCheckAll.setVisibility(View.VISIBLE);
        //4.设置文本为完成
        tvShopcartEdit.setText("编辑");
        //5.把所有的数据设置勾选状态
        if(adapter!=null) {

            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
    }

    private void showDelete() {
        //1.设置完成
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        //2.显示删除控件
        llDelete.setVisibility(View.VISIBLE);
        //3.隐藏结算控件
        llCheckAll.setVisibility(View.GONE);
        //4.设置文本为完成
        tvShopcartEdit.setText("完成");
        //5.把所有的数据设置非选择状态
        if(adapter!=null) {

            adapter.checkAll_none(false);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
    }

    @Override
    public void initData() {
        super.initData();

        showData();

    }

    private void showData() {
        list = CartStorage.getInstance(mContext).getAllData();
        if(list!=null && list.size()>0) {

            //购物车有数据
            llEmptyShopcart.setVisibility(View.GONE);

            adapter = new ShoppingCartAdapter(mContext, list,tvShopcartTotal,checkboxAll,checkboxDeleteAll);
            //设置RecyclerView的适配器
            recyclerview.setAdapter(adapter);

            //布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

            //设置点击事件
            adapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
                    //1.设置Bean对象状态取反
                    GoodsBean goodsBean = list.get(position);
                    goodsBean.setChecked(!goodsBean.isChecked());

                    adapter.notifyItemChanged(position);

                    //2.刷新价格
                    adapter.showTotalPrice();

                    //3.校验是否全选
                    adapter.checkAll();

                }
            });

            //校验是否全选
            adapter.checkAll();

        }else{

            //购物车没有数据
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden) {

            showData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
//                Toast.makeText(mContext, "全选", Toast.LENGTH_SHORT).show();
                boolean isChecked = checkboxAll.isChecked();
                //全选和反全选
                adapter.checkAll_none(isChecked);
                //显示总价格
                adapter.showTotalPrice();
                break;
            case R.id.btn_check_out:

                //调支付宝
                AliPayUtils.getInstance().pay(getActivity(), "0.01");
                break;
            case R.id.checkbox_delete_all:
//                Toast.makeText(mContext, "删除全选", Toast.LENGTH_SHORT).show();
                isChecked = checkboxDeleteAll.isChecked();
                //全选和反全选
                adapter.checkAll_none(isChecked);
                //显示总价格
                adapter.showTotalPrice();

                break;
            case R.id.btn_delete:
//                Toast.makeText(mContext, "删除按钮", Toast.LENGTH_SHORT).show();

                adapter.deleteData();
                adapter.checkAll();
                showEempty();

                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:

                intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("checkedid",R.id.rb_home);
                startActivity(intent);
                break;
        }
    }

    /**
     * 没有数据的时候显示
     */
    private void showEempty() {
        if(adapter.getItemCount()==0) {

            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

}

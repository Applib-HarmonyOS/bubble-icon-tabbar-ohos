package com.cuberto.bubbletabsampleapp.slice;


import com.cuberto.bubbleicontabbarohos.util.ResUtil;
import com.cuberto.bubbleicontabbarohos.TabBubbleAnimator;
import com.cuberto.bubbleicontabbarohos.TabLayoutv2;
import com.cuberto.bubbletabsampleapp.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;

import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    TabLayoutv2 tabLayout;
    private String[] titles = new String[]{"Home", "Clock", "Folder", "Menu"};
    private int[] colors = new int[]{ResourceTable.Color_home, ResourceTable.Color_clock, ResourceTable.Color_folder, ResourceTable.Color_menu};
    private Context context;
    private ComponentContainer rootLayout;
    private PageSlider mPager;
    private PageViewAdapter pageViewAdapter;
    private TabBubbleAnimator tabBubbleAnimator;
    private List<PageInfo> mPageViews;

    @Override
    protected void onStart(Intent intent) {

        super.onStart(intent);
        rootLayout = (ComponentContainer)LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);

        tabLayout = (TabLayoutv2)rootLayout.findComponentById(ResourceTable.Id_tablayout);
        mPager = (PageSlider) ResUtil.findComponentById(rootLayout, ResourceTable.Id_slider).get();

        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(RgbColor.fromArgbInt(ResUtil.getColor(getApplicationContext(), ResourceTable.Color_white) ));
        shapeElement.setShape(ShapeElement.RECTANGLE);
        shapeElement.setCornerRadius(10);
        shapeElement.setStroke(3, RgbColor.fromArgbInt(ResUtil.getColor(getApplicationContext(), ResourceTable.Color_black) ));
        mPager.setBackground( shapeElement);

        initPageView( Color.WHITE);
        tabLayout.setupWithPageSlider( mPager);
        tabBubbleAnimator = new TabBubbleAnimator(tabLayout);
        tabBubbleAnimator.addTabItem(titles[0], ResourceTable.Graphic_ic_grid, colors[0]);
        tabBubbleAnimator.addTabItem(titles[1], ResourceTable.Graphic_ic_clock,colors[1]);
        tabBubbleAnimator.addTabItem(titles[2], ResourceTable.Graphic_ic_folder, colors[2]);
        tabBubbleAnimator.addTabItem(titles[3], ResourceTable.Graphic_ic_menu, colors[3]);
        tabBubbleAnimator.setUnselectedColorId(Color.BLACK.getValue());
        tabBubbleAnimator.highLightTab(0);
        mPager.addPageChangedListener( tabBubbleAnimator);

        tabBubbleAnimator.Start();
        setUIContent( rootLayout);
    }

    private void initPageView(Color color) {
        mPageViews = new ArrayList();
        mPageViews.add(new TabComponent(this, titles[0], colors[0]));
        mPageViews.add(new TabComponent(this, titles[1], colors[1]));
        mPageViews.add(new TabComponent(this, titles[2], colors[2]));
        mPageViews.add(new TabComponent(this, titles[3], colors[3]));

        pageViewAdapter = new PageViewAdapter(this, mPageViews);
        mPager.setProvider(pageViewAdapter);
        mPager.setOrientation(Component.HORIZONTAL);
        mPager.setSlidingPossible(true);
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);

    }
}

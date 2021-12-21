package com.cuberto.bubbleicontabbarohos;

import com.cuberto.bubbleicontabbarohos.ResourceTable;
import com.cuberto.bubbleicontabbarohos.util.ResUtil;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.VectorElement;
import ohos.agp.utils.Color;
import java.util.ArrayList;
import java.util.List;

public class TabBubbleAnimator implements PageSlider.PageChangedListener{
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private final List<Integer> mFragmentIconList = new ArrayList<>();
    private final List<Integer> mFragmentColorList = new ArrayList<>();
    private TabLayoutv2 tabLayout;
    private DependentLayout clickedtab;
    private int unselectedColorId = Color.BLACK.getValue();

    public TabBubbleAnimator(TabLayoutv2 tabLayout) {
        this.tabLayout = tabLayout;
    }

    public void addTabItem(String title, int tabIcon, int tabColor) {
        mFragmentTitleList.add(title);
        mFragmentIconList.add(tabIcon);
        mFragmentColorList.add(tabColor);
    }

    public void highLightTab(int position) {
        if (tabLayout != null) {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                DependentLayout tab = tabLayout.getTabAt(i);
                getTabView(i, tab, i == position);
            }
        }
    }

    private void getTabView(int position, DependentLayout tab, boolean isSelected) {

        StackLayout stackLayout =(StackLayout)tab.findComponentById(ResourceTable.Id_innertab);
        Text text =(Text)stackLayout.findComponentById(ResourceTable.Id_tabtext);
        Image bgImage =(Image)tab.findComponentById(ResourceTable.Id_bgImage);

        Image image = (Image)stackLayout.findComponentById(ResourceTable.Id_tabimage);
        VectorElement vectorElement = new VectorElement(tabLayout.getContext(), mFragmentIconList.get(position));
        image.setImageElement( vectorElement);

        if (isSelected) {

            if( null ==clickedtab) {
                text.setVisibility(Component.VISIBLE);
                bgImage.setVisibility( Component.VISIBLE);

                text.setText( "       "+ mFragmentTitleList.get(position));
                ShapeElement shapeElement = new ShapeElement();
                shapeElement.setRgbColor(RgbColor.fromArgbInt(ResUtil.getColor(tabLayout.getContext(), mFragmentColorList.get(position)) ));
                shapeElement.setShape(ShapeElement.RECTANGLE);
                shapeElement.setCornerRadius(120);
                bgImage.setImageElement(shapeElement);
                bgImage.setAlpha((float)0.25);
                clickedtab = tab;
            }
            else if (clickedtab != tab)  //If clicked tab same as current one then do nothing
            {
                clickedtab = tab;
                ValueAnimator mAnimation = ValueAnimator.ofFloat(1, 50);
                mAnimation.setDuration( 400);
                mAnimation.setCurveType(Animator.CurveType.LINEAR);
                mAnimation.setValueUpdateListener( new AnimatorValue.ValueUpdateListener(){

                    @Override
                    public void onUpdate(AnimatorValue animatorValue, float v) {
                        text.setVisibility(Component.VISIBLE);
                        text.setText( " "+ text.getText());

                        ShapeElement shapeElement = new ShapeElement();
                        shapeElement.setRgbColor(RgbColor.fromArgbInt(ResUtil.getColor(tabLayout.getContext(), mFragmentColorList.get(position)) ));
                        shapeElement.setShape(ShapeElement.RECTANGLE);
                        shapeElement.setCornerRadius(120);

                        bgImage.setVisibility( Component.VISIBLE);
                        bgImage.setImageElement(shapeElement);
                        bgImage.setAlpha((float)0.25);
                        clickedtab.invalidate();
                    }
                });

                clickedtab.invalidate();
                mAnimation.start();
            }
        }
        else
        {
            text.setText( mFragmentTitleList.get(position));
            text.setVisibility(Component.INVISIBLE);
            bgImage.setVisibility( Component.INVISIBLE);
        }
    }


    @Override
    public void onPageSliding(int i, float v, int i1) {

    }

    @Override
    public void onPageSlideStateChanged(int i) {

    }

    @Override
    public void onPageChosen(int position) {
        highLightTab(position);
    }


    public void setUnselectedColorId(int unselectedColorId) {
        this.unselectedColorId = unselectedColorId;
    }

    public void Start() {
        tabLayout.start();
    }

}

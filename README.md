# BubbleTabBar

[![GitHub license](https://img.shields.io/badge/license-MIT-lightgrey.svg)](https://raw.githubusercontent.com/Cuberto/bubble-icon-tabbar-android/master/LICENSE)

![Animation](https://raw.githubusercontent.com/Cuberto/bubble-icon-tabbar-android/master/Screenshots/animation.gif)


## Example

To run the example project, clone the repo, and run `MainAbilitySlice`


### As library

```
- inside dependencies of the build.gradle of entry module, use the following code
```
    dependencies {
        //consume library
        implementation fileTree(dir: 'libs', include: ['*.jar', '*.har'])
        ....
    }
```
Sync project and now you can use bubbleicontabbar library


### Manual

Add `TabBubbleAnimator` content of res package to your project

## Usage

Add TabLayout to your xml

```
    <com.cuberto.bubbleicontabbarohos.TabLayoutv2
        ohos:id="$+id:tablayout"
        ohos:weight ="1"
        ohos:height="match_content"
        ohos:width="match_content">

    </com.cuberto.bubbleicontabbarohos.TabLayoutv2>
        
```

Create adapter in your AbilitySlice, add some Component and set PageSlider adapter
```
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

        mPageViews = new ArrayList();
        mPageViews.add(new TabComponent(this, titles[0], colors[0]));
        mPageViews.add(new TabComponent(this, titles[1], colors[1]));
        mPageViews.add(new TabComponent(this, titles[2], colors[2]));
        mPageViews.add(new TabComponent(this, titles[3], colors[3]));

        pageViewAdapter = new PageViewAdapter(this, mPageViews);
        mPager.setProvider(pageViewAdapter);
    }

```

Setup your TabLayout with ViewPager
```
        tabLayout = (TabLayoutv2)rootLayout.findComponentById(ResourceTable.Id_tablayout);
        tabLayout.setupWithPageSlider( mPager);
```

Create TabBubbleAnimator and tabItem as title, image id and color for each fragment
```
        tabBubbleAnimator = new TabBubbleAnimator(tabLayout);
        tabBubbleAnimator.addTabItem(titles[0], ResourceTable.Graphic_ic_grid, colors[0]);
        tabBubbleAnimator.addTabItem(titles[1], ResourceTable.Graphic_ic_clock,colors[1]);
        tabBubbleAnimator.addTabItem(titles[2], ResourceTable.Graphic_ic_folder, colors[2]);
        tabBubbleAnimator.addTabItem(titles[3], ResourceTable.Graphic_ic_menu, colors[3]);
```


Call highlightTab() for 0 position and add tabBubbleAnimator as OnPageChangeListener to ViewPager
```
        tabBubbleAnimator.highLightTab(0);
        mPager.addPageChangedListener( tabBubbleAnimator);
```


## iOS

Similar library [BubbleTabBar](https://github.com/Cuberto/bubble-icon-tabbar) by [Cuberto](https://github.com/Cuberto)

## Author

Cuberto Design, info@cuberto.com

## License

BubbleTabBar is available under the MIT license. See the LICENSE file for more info.

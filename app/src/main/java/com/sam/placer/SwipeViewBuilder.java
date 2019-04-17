package com.sam.placer;

/**
 * Created by janisharali on 26/08/16.
 */
public class SwipeViewBuilder {

    private com.sam.placer.SwipePlaceHolderView mSwipePlaceHolderView;

    /**
     *
     * @param swipePlaceHolderView
     */
    public SwipeViewBuilder(com.sam.placer.SwipePlaceHolderView swipePlaceHolderView) {
        mSwipePlaceHolderView = swipePlaceHolderView;
    }

    /**
     *
     * @param swipeType
     * @return
     */
    public SwipeViewBuilder setSwipeType(int swipeType){
        if(swipeType == com.sam.placer.SwipePlaceHolderView.SWIPE_TYPE_HORIZONTAL){
            mSwipePlaceHolderView.setSwipeType(com.sam.placer.SwipePlaceHolderView.SWIPE_TYPE_HORIZONTAL);
        }
        else if(swipeType == com.sam.placer.SwipePlaceHolderView.SWIPE_TYPE_VERTICAL){
            mSwipePlaceHolderView.setSwipeType(com.sam.placer.SwipePlaceHolderView.SWIPE_TYPE_VERTICAL);
        }
        else{
            mSwipePlaceHolderView.setSwipeType(com.sam.placer.SwipePlaceHolderView.SWIPE_TYPE_DEFAULT);
        }
        return this;
    }

    /**
     *
     * @param viewCount
     * @return
     */
    public SwipeViewBuilder setDisplayViewCount(int viewCount){
        if(viewCount < 0){
            mSwipePlaceHolderView.setDisplayViewCount(SwipePlaceHolderView.DEFAULT_DISPLAY_VIEW_COUNT);
        }else{
            mSwipePlaceHolderView.setDisplayViewCount(viewCount);
        }
        return this;
    }

    /**
     *
     * @param reverse
     * @return
     */
    public SwipeViewBuilder setDisplayReverse(boolean reverse) {
        mSwipePlaceHolderView.setIsReverse(reverse);
        return this;
    }

    /**
     *
     * @param decor
     * @return
     */
    public SwipeViewBuilder setSwipeDecor(SwipeDecor decor) {
        mSwipePlaceHolderView.setSwipeDecor(decor);
        return this;
    }

    /**
     *
     * @param factor
     * @return
     */
    public SwipeViewBuilder setWidthSwipeDistFactor(float factor) {
        if(factor >= 1) {
            mSwipePlaceHolderView.setWidthSwipeDistFactor(factor);
        }
        return this;
    }

    /**
     *
     * @param factor
     * @return
     */
    public SwipeViewBuilder setHeightSwipeDistFactor(float factor) {
        if(factor >= 1) {
            mSwipePlaceHolderView.setHeightSwipeDistFactor(factor);
        }
        return this;
    }

    public SwipeViewBuilder setIsUndoEnabled(boolean enabled) {
        mSwipePlaceHolderView.setIsUndoEnabled(enabled);
        return this;
    }
}

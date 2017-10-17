package com.hjide.cache;

import java.util.concurrent.TimeUnit;

/**
 * author: yagnhua.yi
 * date: 2015/4/13
 * time: 10:30
 */
public enum CachePrefix
{
    WARE("w", "商品缓存前缀", 15 * 24 * 60 * 60, TimeUnit.SECONDS),
    SKU("s", "SKU缓存前缀", 15 * 24 * 60 * 60, TimeUnit.SECONDS),
    WARE_EXT("we_", "ware_ext缓存前缀", 15 * 24 * 60 * 60, TimeUnit.SECONDS),
    VENDER_SALE_ATTR_VALUE("vsav_", "商家销售属性值", 3 * 24 * 60 * 60, TimeUnit.SECONDS),
    WARE_SALE_ATTR_VALUE("wsav_", "商品销售属性值别名", 3 * 24 * 60 * 60, TimeUnit.SECONDS),
    WARE_POSTER("w_p_", "统一海报", 5 * 24 * 60 * 60, TimeUnit.SECONDS),
    WARE_POSTER_RELATION("w_p_p_", "海报关联关系", 5 * 24 * 60 * 60, TimeUnit.SECONDS),
    WARE_SHOP_CATEGORY("wsc_", "商品店内分类", 30 * 24 * 60 * 60, TimeUnit.SECONDS);

    private String key;

    private String desc;

    private int expiredTime;

    private TimeUnit unit;

    private CachePrefix(String key, String desc, int expiredTime, TimeUnit unit)
    {
        this.key = key;
        this.desc = desc;
        this.expiredTime = expiredTime;
        this.unit = unit;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public int getExpiredTime()
    {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime)
    {
        this.expiredTime = expiredTime;
    }

    public TimeUnit getUnit()
    {
        return unit;
    }

    public void setUnit(TimeUnit unit)
    {
        this.unit = unit;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}

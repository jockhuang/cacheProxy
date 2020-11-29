package com.hjide.cache.serialize;

import com.hjide.cache.common.Serialize;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jock on 15/9/1.
 */
public class CategoryDTO extends CategoryBaseDTO implements Serializable, Cloneable {
    // 目录的ID号
    @Serialize
    private Long id;
    // 目录的父ID号
    @Serialize
    private Long fid;
    // 目录的名称
    @Serialize
    private String name;
    // 目录的别名
    @Serialize
    private String aliasName;
    // -1、删除 0、无效 1、有效
    @Serialize
    private Integer status;
    // 越小越靠前
    @Serialize
    private Integer indexId;
    // 分类对应的图片地址，未定义图片尺寸
    @Serialize
    private String logo;
    // 相关备注
    @Serialize
    private String notes;
    // 特征
    @Serialize
    private String features;
    // 目录等级
    @Serialize
    private Integer lev;
    @Serialize
    private Date created;
    @Serialize
    private Date modified;

    private Map<String, String> featureMap;

    @Serialize
    private List<Long> subList;

    @Serialize
    private List<CategoryAttrDTO> attrDTOList;


    public CategoryDTO() {
    }

    public CategoryDTO(Long id, Long fid, String name, String aliasName, Integer status, Integer indexId, Integer lev) {
        this.id = id;
        this.fid = fid;
        this.name = name;
        this.aliasName = aliasName;
        this.status = status;
        this.indexId = indexId;
        this.lev = lev;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;

    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public Map<String, String> getFeatureMap() {
        return featureMap;
    }

    public void setFeatureMap(Map<String, String> featureMap) {
        this.featureMap = featureMap;
    }


    public String getKey() {
        return "CATEGOTY_" + String.valueOf(id);
    }


    /**
     * 类目级别
     */
    public enum LevelEnum {
        first(1, "一级"),
        second(2, "二级"),
        end(3, "三级");

        private static Map<Integer, LevelEnum> levelEnumMap = new HashMap<Integer, LevelEnum>();

        static {
            for (LevelEnum t : LevelEnum.values()) {
                levelEnumMap.put(t.getLevel(), t);
            }
        }

        private final Integer level;
        private final String name;

        LevelEnum(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public static LevelEnum getType(int type) {
            return levelEnumMap.get(type);
        }

        public Integer getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args){
        Field[] fields = CategoryDTO.class.getDeclaredFields();
        for (Field field:fields){
            System.out.println(field.getName());
        }
    }

    public List<Long> getSubList() {
        return subList;
    }

    public void setSubList(List<Long> subList) {
        this.subList = subList;
    }

    public List<CategoryAttrDTO> getAttrDTOList() {
        return attrDTOList;
    }

    public void setAttrDTOList(List<CategoryAttrDTO> attrDTOList) {
        this.attrDTOList = attrDTOList;
    }
}

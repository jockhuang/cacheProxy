package com.hjide.cache.serialize;

import com.hjide.cache.utils.SerializeBean;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuxianjun on 2016/1/22.
 */
public class SerializeBeanTest {

    @Test
    public void getFiled() throws IntrospectionException {
        List<SerializeBean.SerializeField> fields = SerializeBean.getSerializeField(CategoryDTO.class);

        for (SerializeBean.SerializeField field :fields){
            System.out.println(field.field.getName());
        }
    }

    @Test
    public void serialize(){
        CategoryDTO dto = new CategoryDTO();
        dto.setVersion(System.currentTimeMillis());
        dto.setFid(1L);
        dto.setId(2L);
        dto.setIndexId(0);
        dto.setLev(0);
        dto.setName("name");
        List<Long> list = new LinkedList<Long>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        dto.setSubList(list);
        CategoryAttrDTO attrDTO = new CategoryAttrDTO();
        attrDTO.setId(1L);
        attrDTO.setName("1");
        CategoryAttrDTO attrDTO1 = new CategoryAttrDTO();
        attrDTO1.setId(2L);
        attrDTO1.setName("2");
        CategoryAttrDTO attrDTO2 = new CategoryAttrDTO();
        attrDTO2.setId(3L);
        attrDTO2.setName("3");
        List<CategoryAttrDTO> attrDTOList = new LinkedList<CategoryAttrDTO>();
        attrDTOList.add(attrDTO);
        attrDTOList.add(attrDTO1);
        attrDTOList.add(attrDTO2);
        dto.setAttrDTOList(attrDTOList);


        Map<String,String> map =SerializeBean.serializeObj(dto);
        System.out.println(map);

        CategoryDTO bean = new CategoryDTO();
        SerializeBean.deserialization(bean,map);

        System.out.println(bean);
    }
}

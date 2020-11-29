package com.hjide.cache.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
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
        ParserConfig.getGlobalInstance().addAccept("com.hjide.");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
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
        System.out.println("json=");
        String json = JSON.toJSONString(dto);
        System.out.println(json);
        CategoryDTO bean2 = JSON.parseObject(json,CategoryDTO.class);
        System.out.println(bean2);

        CategoryDTO bean = new CategoryDTO();
        SerializeBean.deserialization(bean,map);

        System.out.println(bean);
    }

    @Test
    public void listTest(){
        ParserConfig.getGlobalInstance().addAccept("com.hjide.");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String json = "[{\"@type\":\"com.hjide.cache.serialize.CategoryAttrDTO\",\"id\":1,\"key\":\"CATEGOTY_1\",\"name\":\"1\"},{\"@type\":\"com.hjide.cache.serialize.CategoryAttrDTO\",\"id\":2,\"key\":\"CATEGOTY_2\",\"name\":\"2\"},{\"@type\":\"com.hjide.cache.serialize.CategoryAttrDTO\",\"id\":3,\"key\":\"CATEGOTY_3\",\"name\":\"3\"}]";
        System.out.println(JSON.parseObject(json,List.class));
    }
}

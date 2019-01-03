package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {
    @Insert("replace into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("replace into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    @Options(useGeneratedKeys = true, keyProperty = "categoryId")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type=#{categoryType}")
    ProductCategory selectOneByCategoryType(Integer categoryType);

    @Select("select * from product_category")
    List<ProductCategory> selectAll();

    @SelectProvider(type = ProductCategoryProvider.class, method = "selectByCategoryTypeList")
    List<ProductCategory> selectByCategoryTypeList(List<Integer> list);

    ProductCategory selectByCategoryType2(Integer categoryType);

    class ProductCategoryProvider {
        public String selectByCategoryTypeList(Map map) {
            List<Integer> list = (List<Integer>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("select * from product_category where category_type in(");
            MessageFormat mf = new MessageFormat("#'{'list[{0}]}");
            for (int i = 0; i < list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
            return sb.toString();
        }
    }
}

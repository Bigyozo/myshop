package cn.zfl.myshop.service;
/**
 * @author zhangfl<br       />
 * @program:myshop
 * @Description:高并发下秒杀功能 <br/>
 * @create: 2019/1/1 19:12<br/>
 */

public interface SecKillService {

    /**
     * 查询秒杀活动特价商品的信息
     * @param productId
     * @return
     */
    String querySecKillProductInfo(String productId);

    /**
     * 模拟不同用户秒杀同一商品的请求
     * @param productId
     * @return
     */
    void orderProductMockDiffUser(String productId);

}

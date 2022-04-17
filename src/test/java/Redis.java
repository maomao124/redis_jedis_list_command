import org.junit.jupiter.api.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.ListPosition;

/**
 * Project name(项目名称)：redis_jedis_list_command
 * Package(包名): PACKAGE_NAME
 * Class(类名): Redis
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/17
 * Time(创建时间)： 21:21
 * Version(版本): 1.0
 * Description(描述)： List 命令
 * <p>
 * <p>
 * 命令	            说明
 * BLPOP	        用于删除并返回列表中的第一个元素（头部操作），如果列表中没有元素，就会发生阻塞，直到列表等待超时或发现可弹出元素为止
 * BRPOP	        用于删除并返回列表中的最后一个元素（尾部操作），如果列表中没有元素，就会发生阻塞，直到列表等待超时或发现可弹出元素为止
 * BRPOPLPUSH	    从列表中取出最后一个元素，并插入到另一个列表的头部。如果列表中没有元素，就会发生阻塞，直到等待超时或发现可弹出元素时为止
 * LINDEX	        通过索引获取列表中的元素
 * LINSERT	        指定列表中一个元素在它之前或之后插入另外一个元素
 * LLEN	            用于获取列表的长度
 * LPOP	            从列表的头部弹出元素，默认为第一个元素
 * LPUSH	        在列表头部插入一个或者多个值
 * LPUSHX	        当储存列表的 key 存在时，用于将值插入到列表头部
 * LRANGE	        获取列表指定范围内的元素
 * LREM	            表示从列表中删除元素与 value 相等的元素。count 表示删除的数量，为 0 表示全部移除
 * LSET	            表示通过其索引设置列表中元素的值
 * LTRIM	        保留列表中指定范围内的元素值
 */

public class Redis
{
    static Jedis jedis;

    @BeforeEach
    void setUp()
    {
        System.out.println("--------------------");
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("--------------------");
    }

    @BeforeAll
    static void beforeAll()
    {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
    }

    @AfterAll
    static void afterAll()
    {
        jedis.close();
    }

    @Test
    void lpush()
    {
        /*
        将字符串值添加到存储在 key 的列表的头部 (LPUSH)。
        如果键不存在，则在追加操作之前创建一个空列表。如果键存在但不是列表，则返回错误。
        时间复杂度：O(1)
        */
        long l = jedis.lpush("list1", "1", "2", "3", "4", "5", "6", "7");
        System.out.println(l);
    }

    @Test
    void rpush()
    {
        System.out.println(jedis.rpush("list2", "1", "2", "3", "4"));
    }

    @Test
    void lset()
    {
        String s = jedis.lset("list1", 0, "0");
        System.out.println(s);
    }

    @Test
    void lindex()
    {
        /*
        返回存储在指定键的列表的指定元素。
         0 是第一个元素，1 是第二个元素，依此类推。支持负索引，例如 -1 是最后一个元素，-2 是倒数第二个元素，依此类推。
        如果存储在 key 的值不是列表类型，则返回错误。如果索引超出范围，则返回“nil”回复。
        请注意，即使平均时间复杂度为 O(n)，要求列表的第一个或最后一个元素也是 O(1)。
        时间复杂度：O(n)（其中 n 是列表的长度）
        */
        System.out.println(jedis.lindex("list1", 2));
        System.out.println(jedis.lindex("list1", 6));
        System.out.println(jedis.lindex("list1", 4));
        System.out.println(jedis.lindex("list1", 99));
    }

    @Test
    void linsert()
    {
        //将元素插入存储在 key 的列表中，在参考值透视之前或之后。
        //当key不存在时，认为是一个空列表，不进行任何操作。
        System.out.println(jedis.linsert("list2", ListPosition.AFTER, "1", "123"));
        System.out.println(jedis.linsert("list2", ListPosition.BEFORE, "1", "123"));
    }

    @Test
    void llen()
    {
        /*
        返回存储在指定键处的列表的长度。
        如果键不存在，则返回零（与空列表的行为相同）。
          如果存储在 key 的值不是列表，则返回错误。
          时间复杂度：O(1)
          */
        System.out.println(jedis.llen("list1"));
        System.out.println(jedis.llen("list2"));
    }


}

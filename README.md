```java

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
    /**
     * The Jedis.
     */
    static Jedis jedis;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp()
    {
        System.out.println("--------------------");
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown()
    {
        System.out.println("--------------------");
    }

    /**
     * Before all.
     */
    @BeforeAll
    static void beforeAll()
    {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
    }

    /**
     * After all.
     */
    @AfterAll
    static void afterAll()
    {
        jedis.close();
    }

    /**
     * Lpush.
     */
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

    /**
     * Rpush.
     */
    @Test
    void rpush()
    {
        System.out.println(jedis.rpush("list2", "1", "2", "3", "4"));
    }

    /**
     * Lset.
     */
    @Test
    void lset()
    {
        String s = jedis.lset("list1", 0, "0");
        System.out.println(s);
    }

    /**
     * Lindex.
     */
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

    /**
     * Linsert.
     */
    @Test
    void linsert()
    {
        //将元素插入存储在 key 的列表中，在参考值透视之前或之后。
        //当key不存在时，认为是一个空列表，不进行任何操作。
        System.out.println(jedis.linsert("list2", ListPosition.AFTER, "1", "123"));
        System.out.println(jedis.linsert("list2", ListPosition.BEFORE, "1", "123"));
    }

    /**
     * Llen.
     */
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


    /**
     * Lpop.
     */
    @Test
    void lpop()
    {
        /*
        以原子方式返回并删除列表的第一个 (LPOP) 或最后一个 (RPOP) 元素。
        例如，如果列表包含元素“a”、“b”、“c”，LPOP 将返回“a”并且列表将变为“b”、“c”。
        如果键不存在或列表已经为空，则返回特殊值“nil”。
        */
        System.out.println(jedis.lpop("list2"));
    }

    /**
     * Rpop.
     */
    @Test
    void rpop()
    {
        /*
        以原子方式返回并删除列表的第一个 (LPOP) 或最后一个 (RPOP) 元素。
        例如，如果列表包含元素“a”、“b”、“c”，LPOP 将返回“a”并且列表将变为“b”、“c”。
        如果键不存在或列表已经为空，则返回特殊值“nil”。
        */
        System.out.println(jedis.rpop("list2"));
    }

    /**
     * Lpushx.
     */
    @Test
    void lpushx()
    {
        /*
        在存储在 key 的列表的开头插入指定的值。
        与LPUSH不同 ，当 key 不存在时不会执行任何操作
        */
        System.out.println(jedis.lpushx("list3", "1", "2"));
        System.out.println(jedis.lpushx("list2", "1", "2"));
    }

    /**
     * Rpushx.
     */
    @Test
    void rpushx()
    {
        /*
        在存储在 key 的列表的开头插入指定的值。
        与LPUSH不同 ，当 key 不存在时不会执行任何操作
        */
        System.out.println(jedis.rpushx("list3", "1", "2"));
        System.out.println(jedis.rpushx("list2", "1", "2"));
    }

    /**
     * L range.
     */
    @Test
    void lRange()
    {
        /*
        返回存储在指定键的列表的指定元素。开始和结束是从零开始的索引。
         0 是列表的第一个元素（列表头），1 是下一个元素，依此类推。
        例如 LRANGE foobar 0 2 将返回列表的前三个元素。
        start 和 end 也可以是负数，表示距列表末尾的偏移量。
        例如 -1 是列表的最后一个元素，-2 是倒数第二个元素，依此类推。
        与各种编程语言中的范围函数保持一致
        请注意，如果您有一个从 0 到 100 的数字列表，
        则 LRANGE 0 10 将返回 11 个元素，即包括最右边的项目。
        这可能与您选择的编程语言中范围相关函数的行为一致，
        也可能不一致（想想 Ruby 的 Range.new、Array#slice 或 Python 的 range() 函数）。
        LRANGE 行为与 Tcl 之一一致。
        超出范围的索引
        超出范围的索引不会产生错误：
        如果 start 超过列表的末尾，或者 start > end，则返回一个空列表。
        如果 end 超过列表的末尾，Redis 将像列表的最后一个元素一样威胁它。
        时间复杂度：O(start+n)（其中 n 是范围的长度，start 是起始偏移量）
        */
        System.out.println(jedis.lrange("list1", 2, 5));
        System.out.println(jedis.lrange("list1", 2, 7));
        System.out.println(jedis.lrange("list1", 0, jedis.llen("list1")));
        System.out.println(jedis.lrange("list1", -999, 999));
        System.out.println(jedis.lrange("list1", 999, 7));
        System.out.println(jedis.lrange("list1", 9999, 99999));
    }

    /**
     * Lrem.
     */
    @Test
    void lrem()
    {
        /*
        从列表中删除第一次出现的 value 元素。
        如果 count 为零，则删除所有元素。
        如果计数是负数，则从尾部到头部删除元素，而不是从头部到尾部，这是正常行为。
        因此，例如，计数为 -2 和 hello 作为要从列表中删除的值的 LREM (a,b,c,hello,x,hello,hello) 将离开列表 (a,b,c,hello,x)。
        删除元素的数量以整数形式返回，
        请注意，不存在的键被 LREM 视为空列表，因此针对不存在的键的 LREM 将始终返回 0。
        时间复杂度：O(N)（其中 N 是列表的长度）
        */
        System.out.println(jedis.lrem("list2", 0, "1"));
    }

    /**
     * Ltrim.
     */
    @Test
    void ltrim()
    {
        /*
        修剪现有列表，使其仅包含指定范围的指定元素。开始和结束是从零开始的索引。
        0 是列表的第一个元素（列表头），1 是下一个元素，依此类推。
        例如，LTRIM foobar 0 2 将修改存储在 foobar 键处的列表，以便仅保留列表的前三个元素。
        start 和 end 也可以是负数，表示距列表末尾的偏移量。
        例如 -1 是列表的最后一个元素，-2 是倒数第二个元素，依此类推。
        超出范围的索引不会产生错误：如果 start 超出列表的末尾，
        或者 start > end，则保留一个空列表作为值。如果 end 超过列表的末尾，Redis 将像列表的最后一个元素一样威胁它。
        提示：LTRIM 的明显用途是与 LPUSH/RPUSH 一起使用。
        例如：
        lpush("mylist", "someelement"); ltrim("mylist", 0, 99); *
        上述两个命令将推送列表中的元素，注意列表不会无限制地增长。
        例如，这在使用 Redis 存储日志时非常有用。
        重要的是要注意，当以这种方式使用时，LTRIM 是一个 O(1) 操作，因为在平均情况下，只会从列表的尾部删除一个元素。
        时间复杂度：O(n)（其中 n 是列表的 len - 范围的 len）*/
        System.out.println(jedis.ltrim("list2", 2, 4));
    }
}



```
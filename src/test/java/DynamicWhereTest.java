import dynamic.DynamicWhere;
import org.jooq.Record;
import org.junit.Before;
import org.junit.Test;
import qooj.jooq.generate.tables.records.Test1Record;
import support.TestContext;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qooj.jooq.generate.tables.Test1.TEST1;

/**
 * Created by ooguro on 2017/04/08.
 */
public class DynamicWhereTest {

    @Before
    public void before() throws Exception {
        try (TestContext testContext = new TestContext()) {
            testContext.dslContext()
                    .deleteFrom(TEST1).execute();
        }
    }

    @Test
    public void コードとネームがnullじゃない時に検索条件に入ること() throws Exception {
        try (TestContext testContext = new TestContext()) {
            insert(testContext, new BigDecimal("101"), "テスト1");
            insert(testContext, new BigDecimal("102"), "テスト2");

            DynamicWhere dynamicWhere = new DynamicWhere(testContext.dslContext());
            List<Test1Record> result = dynamicWhere.dynamicFind(new BigDecimal("101"), "テスト1");

            assertThat(result).hasSize(1);
            Record record = result.get(0);
            assertThat(record.getValue(TEST1.CODE)).isEqualTo(new BigDecimal("101"));
            assertThat(record.getValue(TEST1.NAME)).isEqualTo("テスト1");
        }
    }


    @Test
    public void 検索条件に渡すコードの値がnullの時検索条件に入らないこと() throws Exception {
        try (TestContext testContext = new TestContext()) {
            insert(testContext, new BigDecimal("101"), "テスト1");
            insert(testContext, new BigDecimal("102"), "テスト1");
            insert(testContext, new BigDecimal("102"), "テスト2");
            DynamicWhere dynamicWhere = new DynamicWhere(testContext.dslContext());
            List<Test1Record> result = dynamicWhere.dynamicFind(null, "テスト1");
            assertThat(result).hasSize(2);

        }
    }
    @Test
    public void 検索条件に渡すネームの値がnullの時検索条件に入らないこと() throws Exception {
        try (TestContext testContext = new TestContext()) {
            insert(testContext, new BigDecimal("101"), "テスト1");
            insert(testContext, new BigDecimal("101"), "テスト2");
            insert(testContext, new BigDecimal("102"), "テスト2");
            DynamicWhere dynamicWhere = new DynamicWhere(testContext.dslContext());
            List<Test1Record> result = dynamicWhere.dynamicFind(new BigDecimal("101"), null);
            assertThat(result).hasSize(2);

        }
    }
    @Test
    public void 検索条件に渡すコードとネームの値がnullの時検索条件に入らないこと() throws Exception {
        try (TestContext testContext = new TestContext()) {
            insert(testContext, new BigDecimal("101"), "テスト1");
            insert(testContext, new BigDecimal("101"), "テスト2");
            insert(testContext, new BigDecimal("102"), "テスト1");
            insert(testContext, new BigDecimal("102"), "テスト2");
            DynamicWhere dynamicWhere = new DynamicWhere(testContext.dslContext());
            List<Test1Record> result = dynamicWhere.dynamicFind(null, null);
            assertThat(result).hasSize(4);

        }
    }

    private void insert(TestContext testContext, BigDecimal code, String name) {
        testContext.dslContext()
                .insertInto(TEST1,
                        TEST1.CODE, TEST1.NAME)
                .values(
                        code, name)
                .execute();
    }
}

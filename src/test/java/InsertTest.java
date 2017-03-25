import static org.assertj.core.api.Assertions.assertThat;
import static qooj.jooq.generate.tables.Test1.TEST1;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.assertj.core.api.SoftAssertions;
import org.jooq.Record;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;

import support.TestContext;

public class InsertTest {
	@Before
	public void before() throws Exception {
		try (TestContext testContext = new TestContext()) {
			testContext.dslContext()
				.deleteFrom(TEST1).where("code = 101").execute();
		}
	}
	// insert into qooj.test1(code, name, date1, date2, time1) values (1, 'テスト１', <日付>, <日時>, <時刻>);
	@Test
	public void 伝統的な登録できること() throws Exception {
		// 自動生成している(または手動生成)しているテーブルとレコードが無いとINSERTできないよ
		try (TestContext testContext = new TestContext()) {
			Date date = Date.valueOf(LocalDate.of(1970, 6, 10));
			testContext.dslContext()
				.insertInto(TEST1,
					TEST1.CODE, TEST1.NAME, TEST1.DATE1, TEST1.DATE2, TEST1.TIME1)
				.values(
					new BigDecimal("101"), "テスト1", date, new Timestamp(333333L), new Time(555555L))
				.execute();
			Result<Record> result = testContext.dslContext()
				.select().from("qooj.test1")
				.where("code = 101")
				.fetch();

			assertThat(result).hasSize(1);
			Record record = result.get(0);
			SoftAssertions soft = new SoftAssertions();
			soft.assertThat(record.getValue("CODE", BigDecimal.class)).isEqualTo(new BigDecimal("101"));
			soft.assertThat(record.getValue("NAME", String.class)).isEqualTo("テスト1");
			soft.assertThat(record.getValue("DATE1", Date.class)).isEqualTo(date);
			soft.assertThat(record.getValue("DATE2", Timestamp.class)).isEqualTo(new Timestamp(333333L));
			soft.assertThat(record.getValue("TIME1", Time.class)).isEqualTo(new Time(555555L));
			soft.assertAll();
		}
	}
}
